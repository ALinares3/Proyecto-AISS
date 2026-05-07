package aiss.dailymotionMiner.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import aiss.dailymotionMiner.model.dailymotionModels.Subtitle;
import aiss.dailymotionMiner.model.videominerModels.VMCaption;
import aiss.dailymotionMiner.transformer.Transformer;

public class SubtitleService {

    @Autowired
    RestTemplate restTemplate;

    public SubtitleService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Value("${dailymotionMiner.baseuri}")
    private String baseUri;

    @Value("${videominer.uri}")
    private String videominerUri;

    private Transformer transformer;

//GET all
    public List<Subtitle> findAllSubtitles() {
        Subtitle[] subtitles = restTemplate.getForObject(baseUri + "/subtitles", Subtitle[].class);
        return Arrays.asList(subtitles);
    }
//POST
    public VMCaption createAcountInVideoMiner(String id, Subtitle data){
        VMCaption caption = transformer.transformaCaption(data);
        return restTemplate.postForObject(videominerUri + "/acounts/" + id, caption, VMCaption.class);
    } 
}
