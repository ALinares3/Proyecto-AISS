package aiss.peertubeMiner.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import aiss.peertubeMiner.model.peertubeModels.Caption.Caption;

public class CaptionService {

    @Autowired
    RestTemplate restTemplate;

    public CaptionService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Value("${dailymotionMiner.baseuri}")
    private String baseUri;

    @Value("${videominer.uri}")
    private String videominerUri;

//GET all
    public List<Caption> findAllCaptions() {
        Caption[] captions = restTemplate.getForObject(baseUri + "/captions", Caption[].class);
        return Arrays.asList(captions);
    }

//GET by id
    public Caption findCaptionById(String id){
        return restTemplate.getForObject(baseUri + "/captions/" + id, Caption.class);
    }

//POST
    public Caption createCaptionInVideoMiner(String id, Caption data){
        return restTemplate.postForObject(videominerUri + "/captions/" + id, data, Caption.class);
    }
}
