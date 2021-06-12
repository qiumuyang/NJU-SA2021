package com.sa2021.videosite.repository;

import com.sa2021.videosite.model.Video;

import org.springframework.data.repository.CrudRepository;

public interface VideoRepository extends CrudRepository<Video, Long> {

}