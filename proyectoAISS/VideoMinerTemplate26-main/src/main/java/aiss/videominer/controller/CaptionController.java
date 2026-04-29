package aiss.videominer.controller;

import org.springframework.beans.factory.annotation.Autowired;

import aiss.videominer.repository.CaptionRepository;
import aiss.videominer.repository.VideoRepository;

public class CaptionController {
    
    @Autowired
    private final VideoRepository videoRepository;
    @Autowired
    private final CaptionRepository captionRepository;

    public CaptionController(VideoRepository videoRepository,CaptionRepository captionRepository){
        this.videoRepository=videoRepository;
        this.captionRepository=captionRepository;
    }

    //TODO:Get all y por id, post, put y delete
}
