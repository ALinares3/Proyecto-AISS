package aiss.videominer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import aiss.videominer.repository.CommentRepository;
import aiss.videominer.repository.VideoRepository;

@RestController
@RequestMapping("/videominer/v1")
public class CommentController {
    
    @Autowired
    private final VideoRepository videoRepository;
    @Autowired
    private final CommentRepository commentRepository;

    public CommentController(VideoRepository videoRepository,CommentRepository commentRepository){
        this.videoRepository=videoRepository;
        this.commentRepository=commentRepository;
    }

    //TODO:Get all y por id, post, put y delete

}
