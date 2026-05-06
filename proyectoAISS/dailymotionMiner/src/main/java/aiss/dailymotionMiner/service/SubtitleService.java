package aiss.dailymotionMiner.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import aiss.dailymotionMiner.model.peertubeModels.Subtitle; // esto es el caption

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
//DONE: PUT y DELETE
    public void updateSubtitleInVideoMiner(String id, Subtitle data) {
        HttpEntity<Subtitle> response = restTemplate.exchange(videominerUri + "/subtitles/" + id, HttpMethod.PUT, new HttpEntity<>(data), Subtitle.class);
        Subtitle updatedSubtitle = response.getBody();
        //esto supongo q devolverá un 204 asi q no pongo el return
    }

    public void deleteSubtitleInVideoMiner(String id) {
        ResponseEntity<Void> response = restTemplate.exchange(videominerUri + "/subtitles/" + id, HttpMethod.DELETE, null, Void.class);
    }

    
}
