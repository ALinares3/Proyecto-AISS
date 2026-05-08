package aiss.peertubeMiner.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import aiss.peertubeMiner.model.peertubeModels.Caption.Caption;
import aiss.peertubeMiner.model.videominerModels.VMCaption;
import aiss.peertubeMiner.transformer.Transformer;

@Service
public class CaptionService {

    @Autowired
    RestTemplate restTemplate;

    public CaptionService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Value("${peertubeMiner.baseuri}")
    private String baseUri;

    @Value("${videominer.uri}")
    private String videominerUri;

    private Transformer transformer;

//GET all
    public List<Caption> findAllCaptions() {
        Caption[] captions = restTemplate.getForObject(baseUri + "/captions", Caption[].class);
        return Arrays.asList(captions);
    }
//POST
    public VMCaption createCaptionInVideoMiner(Caption data){
        VMCaption caption = transformer.transformaCaption(data);
        return restTemplate.postForObject(videominerUri + "/captions/" + data.getId(), caption, VMCaption.class);
    }
}
