package aiss.videominer.controller;

import org.springframework.beans.factory.annotation.Autowired;

import aiss.videominer.repository.ChannelRepository;
import aiss.videominer.repository.VideoRepository;

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
