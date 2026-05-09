package aiss.peertubeMiner.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import aiss.peertubeMiner.model.peertubeModels.Video.Video;
import aiss.peertubeMiner.model.videominerModels.VMVideo;
import aiss.peertubeMiner.transformer.Transformer;

@Service
public class VideoService {

    @Autowired
    RestTemplate restTemplate;

    public VideoService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Value("${peertubeMiner.baseuri}")
    private String baseUri;

    @Value("${videominer.uri}")
    private String videominerUri;

    @Autowired
    private Transformer transformer;

//GET all
    public List<Video> findAllVideos() {
        Video[] videos = restTemplate.getForObject(baseUri + "/videos", Video[].class);
        return Arrays.asList(videos);
    }
//POST
    public VMVideo createVideoInVideoMiner(Video data){
        VMVideo video = transformer.transformaVideo(data);
        return restTemplate.postForObject(videominerUri + "/videos/" ,video, VMVideo.class);
    }
}
