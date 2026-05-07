package aiss.dailymotionMiner.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import aiss.dailymotionMiner.model.dailymotionModels.Video;


public class VideoService {

    @Autowired
    RestTemplate restTemplate;

    public VideoService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Value("${dailymotionMiner.baseuri}")
    private String baseUri;

    @Value("${videominer.uri}")
    private String videominerUri;

//GET all
    public List<Video> findAllVideos() {
        Video[] videos = restTemplate.getForObject(baseUri + "/videos", Video[].class);
        return Arrays.asList(videos);
    }

//GET by id
    public Video findVideoById(String id){
        return restTemplate.getForObject(baseUri + "/videos/" + id, Video   .class);
    }

//POST
    public Video createVideoInVideoMiner(String id, Video data){
        return restTemplate.postForObject(videominerUri + "/videos/" + id, data, Video.class);
    }
}
