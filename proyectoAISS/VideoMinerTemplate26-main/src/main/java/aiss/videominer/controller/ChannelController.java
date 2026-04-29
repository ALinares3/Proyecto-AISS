package aiss.videominer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import aiss.videominer.repository.ChannelRepository;

@RestController
public class ChannelController {

    @Autowired
    private final ChannelRepository channelRepository;

    public ChannelController(ChannelRepository channelRepository){
        this.channelRepository = channelRepository;
    }

    //TODO: Get all y por id, post, put y delete
    //Supongo que get necesitará un método para peertube y otro para dailymotion
    
}
