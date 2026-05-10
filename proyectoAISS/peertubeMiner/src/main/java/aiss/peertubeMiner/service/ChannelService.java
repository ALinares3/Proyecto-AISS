package aiss.peertubeMiner.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import aiss.peertubeMiner.model.peertubeModels.Channel.Channel;
import aiss.peertubeMiner.model.videominerModels.VMChannel;
import aiss.peertubeMiner.transformer.Transformer;

@Service
public class ChannelService {

    @Autowired
    RestTemplate restTemplate;

    public ChannelService(RestTemplate restTemplate) {
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

    public VMChannel createChannelInVideoMiner(Channel data, @RequestParam(defaultValue = "maxVideos") Integer limitVideos, @RequestParam(defaultValue = "2") Integer limitComments) {
        VMChannel channel = transformer.transformaChannel(data);
        return restTemplate.postForObject(videominerUri + "/channels/?maxVideos=" + limitVideos + "&maxComments=" + limitComments, channel, VMChannel.class);
    }

}
