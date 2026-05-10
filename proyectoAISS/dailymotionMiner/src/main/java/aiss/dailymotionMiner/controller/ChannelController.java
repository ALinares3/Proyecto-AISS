package aiss.dailymotionMiner.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import aiss.dailymotionMiner.model.dailymotionModels.Channel;
import aiss.dailymotionMiner.model.videominerModels.VMChannel;
import aiss.dailymotionMiner.service.ChannelService;

@RestController
@RequestMapping("/channels")
public class ChannelController {

    private final ChannelService service;

    @Value("${dailymotionMiner.maxVideos}")
    private Integer maxVideos;

    @Value("${dailymotionMiner.maxComments}")
    private Integer maxComments;

    public ChannelController(ChannelService service) {
        this.service = service;
    }

    @GetMapping
    public List<Channel> findAllChannels(){
        return service.findAllChannels();
    }

    @PostMapping
    public VMChannel sendChannel(@PathVariable String id){
        return service.createChannelInVideoMiner(id,maxVideos,maxComments);
    }

}
