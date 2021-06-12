import os
import sys
import multiprocessing
import subprocess
from time import sleep
from typing import List, Tuple
from kafka import KafkaConsumer

KAFKA_TOPIC = 'encode'                          # topic of kafka
KAFKA_SERVER = 'host.docker.internal:9092'      # server of kafka
PENDING_TIME = 1                  # in seconds
MAX_TASK_COUNT = 4                # max encoding task count
DOCKER_MOUNT = 'F:/sa2021/data'   # mount host dir to docker


def encode(src: str, dst: str, size: Tuple[int, int]) -> None:
    if not os.path.exists(src):
        return

    if sys.platform.startswith('linux'):
        # need to install handbrake first
        # apt-get install handbrake-cli
        handbrake = 'HandBrakeCLI'
    elif sys.platform.startswith('win'):
        cwd = os.path.abspath(os.path.dirname(__file__))
        handbrake = os.path.join(cwd, 'handbrake.exe')
    else:
        print(sys.platform)
        exit(1)

    print(f'[start] {src} {size}')

    w, h = size
    args = [handbrake,
            '-i', src,
            '-o', dst,
            '--width', str(w),
            '--height', str(h)]
    null_dev = open(os.devnull, 'w')
    p = subprocess.Popen(args, stdout=null_dev, stderr=null_dev)

    print(f'[finished] {src} {size}')


def get_args(src: str) -> List[Tuple[str, str, Tuple[int, int]]]:
    prev, ext = os.path.splitext(src)
    if not prev.startswith(DOCKER_MOUNT):
        prev = DOCKER_MOUNT + prev
    return [(f'{prev}{ext}',  # src
             f'{prev}_{resolution[1]}{ext}',  # dst
             resolution  # size
             )
            for resolution in [(1280, 720), (640, 480)]]


def consumer():
    consumer = KafkaConsumer(KAFKA_TOPIC, bootstrap_servers=KAFKA_SERVER)

    for message in consumer:
        # get task filename
        file = message.value.decode('ascii')
        print(
            f"[receive]: {file}, pending tasks: {multiprocessing.active_children()}")

        args = get_args(file)
        # print(args)
        for arg in args:
            # task busy, wait
            while len(multiprocessing.active_children()) >= MAX_TASK_COUNT:
                sleep(PENDING_TIME)

            # create new task
            p = multiprocessing.Process(target=encode, args=arg)
            p.start()


if __name__ == '__main__':
    usage = 'usage: encoder.py [-n N] [-h]\n\n -n N  set max task limit to N'
    if len(sys.argv) == 2 and sys.argv[1] == '-h':
        print(usage)
        exit(0)
    elif len(sys.argv) == 3 and sys.argv[1] == '-n' and sys.argv[2].isdigit():
        MAX_TASK_COUNT = max(int(sys.argv[2]), 1)
        consumer()
    elif len(sys.argv) == 1:
        consumer()
    else:
        print(usage)
        exit(1)
