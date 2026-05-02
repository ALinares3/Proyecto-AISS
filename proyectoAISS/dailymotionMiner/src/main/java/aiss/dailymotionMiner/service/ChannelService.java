package aiss.dailymotionMiner.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import aiss.dailymotionMiner.model.Channel;

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
    public Channel creatChannelInVideoMiner(String id,Channel data){
        return restTemplate.postForObject(videominerUri + "/channels/" + id, data, Channel.class);
    }
//TODO: PUT y DELETE
    public void updateChannelInVideoMiner(String id, Channel data) {
        HttpEntity<Channel> response = restTemplate.exchange(videominerUri + "/channels/" + id, HttpMethod.PUT, new HttpEntity<>(data), Channel.class);
        Channel updatedChannel = response.getBody();
    }

    public void deleteChannelInVideoMiner(String id) {
        ResponseEntity<Void> response = restTemplate.exchange(videominerUri + "/channels/" + id, HttpMethod.DELETE, null, Void.class);
    }

    
}
