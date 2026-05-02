package aiss.peertubeMiner.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import aiss.peertubeMiner.model.commentThreads;

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
    public List<commentThreads> findAllcommentThreads() {
        commentThreads[] commentThreads = restTemplate.getForObject(baseUri + "/comment-threads", commentThreads[].class);
        return Arrays.asList(commentThreads);
    }

//GET by id
    public commentThreads findcommentThreadsById(String id){
        return restTemplate.getForObject(baseUri + "/comment-threads/" + id, commentThreads.class);
    }

//POST
    public commentThreads createcommentThreadsInVideoMiner(String id,commentThreads data){
        return restTemplate.postForObject(videominerUri + "/comment-threads/" + id, data, commentThreads.class);
    }

}