package aiss.dailymotionMiner.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import aiss.dailymotionMiner.model.dailymotionModels.Channel;
import aiss.dailymotionMiner.model.videominerModels.VMChannel;
import aiss.dailymotionMiner.transformer.Transformer;

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

    private Transformer transformer;

//GET all
    public List<Channel> findAllChannels() {
        Channel[] channels = restTemplate.getForObject(baseUri + "/channels", Channel[].class);
        return Arrays.asList(channels);
    }
//POST
    public VMChannel createAcountInVideoMiner(String id, Channel data){
        VMChannel channel = transformer.transformaChannel(data);
        return restTemplate.postForObject(videominerUri + "/acounts/" + id, channel, VMChannel.class);
    }
    
}
