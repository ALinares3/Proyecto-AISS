package aiss.dailymotionMiner.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import aiss.dailymotionMiner.model.dailymotionModels.User;

public class OwnerService {

    @Autowired
    RestTemplate restTemplate;

    public OwnerService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Value("${dailymotionMiner.baseuri}")
    private String baseUri;

    @Value("${videominer.uri}")
    private String videominerUri;

//GET all
    public List<User> findAllOwners() {
        User[] owners = restTemplate.getForObject(baseUri + "/owners", User[].class);
        return Arrays.asList(owners);
    }

//GET by id
    public User findOwnerById(String id){
        return restTemplate.getForObject(baseUri + "/owners/" + id, User.class);
    }

//POST
    public User createOwnerInVideoMiner(String id, User data){
        return restTemplate.postForObject(videominerUri + "/owners/" + id, data, User.class);
    }
}
