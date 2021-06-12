package com.sa2021.videosite.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Video {

    @Id
    @GeneratedValue
    private Long vid;

    private Long uid;

    private String title;

    private String uri;

    private String uri480;

    private String uri720;

    private Long view;

    public Video(Long uid, String title) {
        this.uid = uid;
        this.title = title;
        this.view = 0L;
    }

    public Video(String uri) {
        this.uri = uri;
        String preffix = uri.substring(0, uri.lastIndexOf('.'));
        String ext = uri.substring(uri.lastIndexOf('.'));
        this.uri480 = preffix + "_480" + ext;
        this.uri720 = preffix + "_720" + ext;
        this.view = 0L;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        return builder.append("[").append(vid).append("]") //
                .append("《").append(title).append("》") //
                .append(" u").append(uid) //
                .append(": ").append(uri).toString();
    }
}