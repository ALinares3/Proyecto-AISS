package aiss.dailymotionMiner.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
    public Channel createChannel(String id,Channel data){
        return restTemplate.postForObject(baseUri + "/channels/" + id, data, Channel.class);
    }

    //Este último sube a la API, pero también hay que mandarlo a videoMiner, ¿con otro POST, en el mismo, si son distintos como hacer que se hagan a la vez?

}
