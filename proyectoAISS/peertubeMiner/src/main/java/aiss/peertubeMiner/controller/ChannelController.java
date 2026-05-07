package aiss.peertubeMiner.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import aiss.peertubeMiner.model.peertubeModels.Channel.Channel;
import aiss.peertubeMiner.model.videominerModels.VMChannel;
import aiss.peertubeMiner.service.ChannelService;

@RestController
@RequestMapping("/")
public class ChannelController {
    
    private final ChannelService service;

    public ChannelController(ChannelService service) {
        this.service = service;
    }

    @GetMapping
    public List<Channel> findAllChannels(){
        return service.findAllChannels();
    }

    @PostMapping
    public VMChannel sendChannel(Channel channel, String id){
        return service.createChannelInVideoMiner(id, channel);
    }

}
