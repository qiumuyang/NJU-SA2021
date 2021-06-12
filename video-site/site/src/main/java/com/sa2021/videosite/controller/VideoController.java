package com.sa2021.videosite.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.sa2021.videosite.model.Video;
import com.sa2021.videosite.model.VideoResponse;
import com.sa2021.videosite.repository.VideoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class VideoController {
    private VideoUtil util;

    @Autowired
    private VideoRepository repository;

    @Autowired
    public VideoController(VideoRepository repository) {
        this.repository = repository;
        this.util = new VideoUtil();
    }

    @GetMapping(value = "/")
    public String index() {
        return "redirect:/video/list";
    }

    @GetMapping(value = "/video/list")
    public String getList(Map<String, Object> model) {
        List<Video> videos = StreamSupport.stream(repository.findAll().spliterator(), false)
                .collect(Collectors.toList());
        model.put("list", videos);
        return "video/list";
    }

    @GetMapping(value = "/video/upload")
    public String getUploadPage(Map<String, Object> model) {
        return "video/upload";
    }

    @PostMapping(value = "/video/upload")
    public String userUploadVideo( //
            @RequestParam("title") String title, //
            @RequestParam("name") String uid, //
            @RequestParam("file") MultipartFile multipartFile,
            Map<String, Object> model) {

        if (multipartFile == null) {
            return "error";
        }

        Video video = null;
        try {
            video = util.handleMultiPart(multipartFile);
        } catch (Exception e) {
            model.put("error", "Bad Request");
            model.put("message", "Unsupported format");
            return "error";
        }
        video.setTitle(title);
        video.setUid((long) new Random().nextInt(1000));

        video = repository.save(video);
        return "redirect:/video/view/" + video.getVid();
    }

    @GetMapping(value = "/video/view/{id}")
    public String playVideo( //
            @PathVariable("id") Long id, //
            @RequestParam(value = "quality", required = false) String quality, //
            Map<String, Object> model) {

        Optional<Video> result = repository.findById(id);
        if (!result.isPresent()) {
            model.put("error", "Not Found");
            model.put("message", "Unable to find video " + id);
            return "error";
        }
        
        Video video = result.get();
        video.setView(video.getView() + 1);
        repository.save(video);

        VideoResponse resp = new VideoResponse(video);
        String url = null;
        if (quality == null) {
            url = resp.getUri();
        } else if (quality.equals("480")) {
            url = resp.getUri480();
        } else if (quality.equals("720")) {
            url = resp.getUri720();
        } else {
            url = resp.getUri();
        }
        model.put("title", video.getTitle());
        model.put("url", url);
        model.put("video", resp);
        return "video/view";
    }
}
