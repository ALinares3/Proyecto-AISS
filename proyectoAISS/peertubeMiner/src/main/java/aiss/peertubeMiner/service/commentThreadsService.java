package aiss.peertubeMiner.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import aiss.peertubeminer.model.peertubeModels.Comment.Comment__1;


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
    public List<Comment__1> findAllcommentThreads() {
        Comment__1[] comments = restTemplate.getForObject(baseUri + "/comment-threads", Comment__1[].class);
        return Arrays.asList(comments);
    }

//GET by id
    public Comment__1 findcommentThreadsById(String id){
        return restTemplate.getForObject(baseUri + "/comment-threads/" + id, Comment__1.class);
    }

//POST
    public Comment__1 createcommentThreadsInVideoMiner(String id,Comment__1 data){
        return restTemplate.postForObject(videominerUri + "/comment-threads/" + id, data, Comment__1.class);
    }

}