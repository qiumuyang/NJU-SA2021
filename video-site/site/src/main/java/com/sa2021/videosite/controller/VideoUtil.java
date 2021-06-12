package com.sa2021.videosite.controller;

import com.sa2021.videosite.model.Video;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.UUID;

public class VideoUtil {

    private final static KafkaProducer<String, String> producer;

    private final static String TOPIC = "encode";

    private final static String UNSUPPORT_FMT = "Unsupported file format";

    // private final static String VIDEO_DIR = "F:/sa2021/data/video";
    // replace this with NFS dir
    private final static String VIDEO_DIR = "/video";

    static {
        // init folder
        File dir = new File(VIDEO_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        // init MQ producer
        Properties props = new Properties();
        props.put("bootstrap.servers", "host.docker.internal:9092");
        props.put("retries", 0);
        props.put("key.serializer", StringSerializer.class.getName());
        props.put("value.serializer", StringSerializer.class.getName());
        producer = new KafkaProducer<String, String>(props);
    }

    public Video handleMultiPart(MultipartFile file) throws Exception {
        // save video to storage
        String uri = saveVideo(file);

        // create encode task
        ProducerRecord<String, String> record = new ProducerRecord<String, String>(TOPIC, null, uri);
        producer.send(record);

        return new Video(uri);
    }

    private String saveVideo(MultipartFile file) throws Exception {
        // check filename
        String name = file.getOriginalFilename();
        if (name.lastIndexOf('.') == -1) {
            throw new Exception(UNSUPPORT_FMT);
        }
        String ext = name.substring(name.lastIndexOf('.'));
        if (!ext.equals(".mp4") && !ext.equals(".avi")) {
            throw new Exception(UNSUPPORT_FMT);
        }

        // rename video
        UUID uuid = UUID.randomUUID();
        String file_name = uuid.toString().replace("-", "") + ext;
        File dst = new File(VIDEO_DIR, file_name);
        file.transferTo(dst);

        return dst.getAbsolutePath();
    }

    public byte[] loadVideo(String path) throws Exception {
        File file = new File(path);
        FileInputStream in = new FileInputStream(file);
        byte[] ret = in.readAllBytes();
        in.close();
        return ret;
    }

    public String getFilename(String uri) {
        String name;
        if (uri.lastIndexOf(File.separator) == -1) {
            name = uri;
        } else {
            name = uri.substring(uri.lastIndexOf(File.separator));
        }
        return name;
    }

    public String getUUID(String uri) {
        String name = getFilename(uri);
        if (name.lastIndexOf('.') == -1) {
            return name;
        }
        return name.substring(0, name.lastIndexOf('.') - 1);
    }
}