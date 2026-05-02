package aiss.videominer.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

import aiss.videominer.model.Channel;
import aiss.videominer.repository.ChannelRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/videominer/v1")
public class ChannelController {

    @Autowired
    private final ChannelRepository channelRepository;

    public ChannelController(ChannelRepository channelRepository){
        this.channelRepository = channelRepository;
    }

    //TODO: Get all y por id, post, put y delete
    //Supongo que get necesitará un método para peertube y otro para dailymotion
    @GetMapping
	public List<Channel> findAll(){
		return channelRepository.findAll();
	}

	@GetMapping("{/id}")
	public Channel findByOneId(@PathVariable String id){
		Optional<Channel> channel = channelRepository.findById(id);
        return channel.get();
	}

    @ResponseStatus(HttpStatus.CREATED) // 201
    @PostMapping
    public Channel createChannel(@Valid @RequestBody Channel channel){
        Channel createdChannel = channelRepository.save(channel);
        return createdChannel;
    }

    @ResponseStatus(HttpStatus.NO_CONTENT) // 204
    @PutMapping("{/id}")
    public void updateChannel(@Valid @RequestBody Channel updateChannel, @PathVariable String id){
        Optional<Channel> channel = channelRepository.findById(id);
        if(channel.isPresent()){
            Channel _channel = channel.get();
            _channel.setName(updateChannel.getName());
            _channel.setDescription(updateChannel.getDescription());
            channelRepository.save(_channel);
        }
    }
    @ResponseStatus(HttpStatus.NO_CONTENT) // 204
    @DeleteMapping("{/id}")
    public void deleteChannel(@PathVariable String id){
        if(channelRepository.existsById(id)) {
            channelRepository.deleteById(id);
        }
    }
    
}
