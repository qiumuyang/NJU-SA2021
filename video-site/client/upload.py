import os
import requests
import json
from requests_toolbelt import MultipartEncoder


url = 'http://localhost:8080/video'


def get_video_list():
    try:
        r = requests.get(url)
    except:
        return None

    resp: dict = json.loads(r.content.decode('utf8'))
    return resp.get('_embedded', {}).get('videoResponseList', [])


def upload_file(uid: int, title: str, filepath: str) -> int:
    if not os.path.exists(filepath):
        raise FileNotFoundError(f"{filepath} not found")

    fsize = os.path.getsize(filepath)
    if fsize / 1024 / 1024 > 500:
        raise RuntimeError(f"{filepath} too large (>500MB)")

    file, ext = os.path.splitext(filepath)
    fdir, fname = os.path.split(file)

    encoder = MultipartEncoder({
        'uid': str(uid),
        'title': str(title),
        'file': (fname + ext,
                 open(filepath, 'rb'),
                 'application/octet-stream')
    })
    headers = {"Content-Type": encoder.content_type}
    try:
        r = requests.put(url, data=encoder, headers=headers)
    except:
        return -1

    return r.status_code
