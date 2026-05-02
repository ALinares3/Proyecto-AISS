package aiss.peertubeMiner.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import aiss.peertubeMiner.model.Caption;

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
//DONE: PUT y DELETE
    public void updateCaptionInVideoMiner(String id, Caption data) {
        HttpEntity<Caption> response = restTemplate.exchange(videominerUri + "/captions/" + id, HttpMethod.PUT, new HttpEntity<>(data), Caption.class);
        Caption updatedCaption = response.getBody();
        //esto supongo q devolverá un 204 asi q no pongo el return
    }

    public void deleteCaptionInVideoMiner(String id) {
        ResponseEntity<Void> response = restTemplate.exchange(videominerUri + "/captions/" + id, HttpMethod.DELETE, null, Void.class);
    }

    
}
