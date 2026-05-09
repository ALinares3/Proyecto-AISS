package aiss.peertubeMiner.service;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import aiss.peertubeMiner.model.peertubeModels.Channel.Channel;
import aiss.peertubeMiner.model.videominerModels.VMChannel;
import aiss.peertubeMiner.model.videominerModels.VMVideo;
import aiss.peertubeMiner.transformer.Transformer;
import aiss.peertubeMiner.model.peertubeModels.Video.Video;

@Service
public class ChannelService {
    
    @Autowired
    RestTemplate restTemplate;

    public ChannelService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    @Value("${peertubeMiner.baseuri}")
    private String baseUri;

    @Value("${videominer.uri}")
    private String videominerUri;

    @Autowired
    private Transformer transformer;

//GET all
    public List<Channel> findAllChannels() {
        Channel[] channels = restTemplate.getForObject(baseUri + "/video-channels", Channel[].class);
        return Arrays.asList(channels);
    }
//POST
    public VMChannel createChannelInVideoMiner(Channel data){
        VMChannel channel = transformer.transformaChannel(data);
        VideoService videoService = new VideoService(restTemplate);
        List<Video> aux = videoService.findAllVideos();
        List<Video> videos = new ArrayList<>();
        for(Video v:aux) {
            if(v.getChannel().equals(data)) {
                videos.add(v);
            }
        }
        List<VMVideo> vmvideos = new ArrayList<>();
        for(Video video:videos) {
            VMVideo vmvideo = transformer.transformaVideo(video);
            vmvideos.add(vmvideo);
            restTemplate.postForObject(videominerUri + "/videos/" ,vmvideo, VMVideo.class);
        }   
        channel.setVideos(vmvideos);
        return restTemplate.postForObject(videominerUri + "/channels/", channel, VMChannel.class);
    }

}