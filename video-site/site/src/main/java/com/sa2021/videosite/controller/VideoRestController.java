package com.sa2021.videosite.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.sa2021.videosite.model.Video;
import com.sa2021.videosite.model.VideoResponse;
import com.sa2021.videosite.repository.VideoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;

@RestController
public class VideoRestController {

    private VideoUtil util;

    @Autowired
    private VideoRepository repository;

    @Autowired
    public VideoRestController(VideoRepository repository) {
        this.repository = repository;
        this.util = new VideoUtil();
    }

    @RequestMapping(value = "/video", method = RequestMethod.PUT)
    public ResponseEntity<?> uploadVideo( //
            @RequestParam("title") String title, //
            @RequestParam("uid") Long uid, //
            @RequestParam("file") MultipartFile multipartFile) {

        if (multipartFile == null) {
            return ResponseEntity.badRequest().body("Unrecognized file");
        }

        Video video = null;
        try {
            video = util.handleMultiPart(multipartFile);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        video.setTitle(title);
        video.setUid(uid);
        video.setView(0L);

        repository.save(video);

        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/video", method = RequestMethod.GET)
    public ResponseEntity<?> getVideoList() {
        List<EntityModel<VideoResponse>> videos = StreamSupport.stream(repository.findAll().spliterator(), false)
                .map(video -> EntityModel.of(new VideoResponse(video))) //
                .collect(Collectors.toList());
        return ResponseEntity.ok(CollectionModel.of(videos));
    }

    @RequestMapping(value = "/video/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getVideo( //
            @PathVariable("id") Long id, //
            @RequestParam(value = "quality", required = false) String quality) {
        // return bytes
        Optional<Video> result = repository.findById(id);
        if (!result.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Video video = result.get();
        String uri = null;
        if (quality == null) {
            uri = video.getUri();
        } else if (quality.equals("480")) {
            uri = video.getUri480();
        } else if (quality.equals("720")) {
            uri = video.getUri720();
        } else {
            return ResponseEntity.badRequest().body("Unknown quality");
        }

        byte[] bytes;
        try {
            bytes = util.loadVideo(uri);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData("attachment", util.getFilename(uri));
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return new ResponseEntity<byte[]>(bytes, headers, HttpStatus.OK);
    }
}