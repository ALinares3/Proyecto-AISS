package aiss.videominer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import aiss.videominer.repository.ChannelRepository;
import aiss.videominer.repository.VideoRepository;

@RestController
@RequestMapping("/videominer/v1")
public class VideoController {

    @Autowired
    private final ChannelRepository channelRepository;
    @Autowired
    private final VideoRepository videoRepository;

    public VideoController(ChannelRepository channelRepository, VideoRepository videoRepository) {
        this.channelRepository = channelRepository;
        this.videoRepository = videoRepository;
    }

    //TODO: Get all y por id, post, put y delete
}
