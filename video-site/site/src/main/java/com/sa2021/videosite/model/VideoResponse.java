package com.sa2021.videosite.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VideoResponse {

    private Long vid;

    private Long uid;

    private String title;

    private String uri;

    private String uri480;

    private String uri720;

    public VideoResponse(Video v) {
        this.vid = v.getVid();
        this.uid = v.getUid();
        this.title = v.getTitle();
        this.uri = "/video/" + this.vid;
        this.uri480 = this.uri + "?quality=480";
        this.uri720 = this.uri + "?quality=720";
    }
}
