package aiss.dailymotionMiner.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import aiss.dailymotionMiner.model.dailymotionModels.Subtitle;

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

//GET all
    public List<Subtitle> findAllSubtitles() {
        Subtitle[] subtitles = restTemplate.getForObject(baseUri + "/subtitles", Subtitle[].class);
        return Arrays.asList(subtitles);
    }

//GET by id
    public Subtitle findSubtitleById(String id){
        return restTemplate.getForObject(baseUri + "/subtitles/" + id, Subtitle.class);
    }

//POST
    public Subtitle createSubtitleInVideoMiner(String id, Subtitle data){
        return restTemplate.postForObject(videominerUri + "/subtitles/" + id, data, Subtitle.class);
    }  
}
