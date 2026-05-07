package aiss.dailymotionMiner.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import aiss.dailymotionMiner.model.dailymotionModels.Channel;

public class ChannelService {

    @Autowired
    RestTemplate restTemplate;

    public ChannelService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Value("${dailymotionMiner.baseuri}")
    private String baseUri;

    @Value("${videominer.uri}")
    private String videominerUri;

//GET all
    public List<Channel> findAllChannels() {
        Channel[] channels = restTemplate.getForObject(baseUri + "/channels", Channel[].class);
        return Arrays.asList(channels);
    }

//GET by id
    public Channel findChannelById(String id){
        return restTemplate.getForObject(baseUri + "/channels/" + id, Channel.class);
    }

//POST
    public Channel createChannelInVideoMiner(String id,Channel data){
        return restTemplate.postForObject(videominerUri + "/channels/" + id, data, Channel.class);
    }
}
