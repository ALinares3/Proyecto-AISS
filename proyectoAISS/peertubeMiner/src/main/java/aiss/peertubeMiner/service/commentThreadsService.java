package aiss.peertubeMiner.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import aiss.peertubeMiner.model.peertubeModels.Comment.Comment__1;
import aiss.peertubeMiner.model.videominerModels.VMComment;
import aiss.peertubeMiner.transformer.Transformer;

@Service
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

    private Transformer transformer;

//GET all
    public List<Comment__1> findAllcommentThreads() {
        Comment__1[] comments = restTemplate.getForObject(baseUri + "/comment-threads", Comment__1[].class);
        return Arrays.asList(comments);
    }
//POST
    public VMComment createCommentThreadInVideoMiner(Comment__1 data){
        VMComment comment = transformer.transformaComment(data);
        return restTemplate.postForObject(videominerUri + "/comment-threads/" + data.getId(), comment, VMComment.class);
    }

}