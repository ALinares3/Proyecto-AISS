package aiss.peertubeMiner.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import aiss.peertubeminer.model.peertubeModels.Comment.Comment;


public class commentThreadsService {
    
    @Autowired
    RestTemplate restTemplate;

    public commentThreadsService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    @Value("${peertubeMiner.baseuri}")
    private String baseUri;

    @Value("${videominer.uri}")
    private String videominerUri;

//GET all
    public List<Comment> findAllcommentThreads() {
        Comment[] comments = restTemplate.getForObject(baseUri + "/comment-threads", Comment[].class);
        return Arrays.asList(comments);
    }

//GET by id
    public Comment findcommentThreadsById(String id){
        return restTemplate.getForObject(baseUri + "/comment-threads/" + id, Comment.class);
    }

//POST
    public Comment createcommentThreadsInVideoMiner(String id,Comment data){
        return restTemplate.postForObject(videominerUri + "/comment-threads/" + id, data, Comment.class);
    }

}