package aiss.dailymotionMiner.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import aiss.dailymotionMiner.model.Owner;

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
    public List<Owner> findAllOwners() {
        Owner[] owners = restTemplate.getForObject(baseUri + "/owners", Owner[].class);
        return Arrays.asList(owners);
    }

//GET by id
    public Owner findOwnerById(String id){
        return restTemplate.getForObject(baseUri + "/owners/" + id, Owner.class);
    }

//POST
    public Owner createOwnerInVideoMiner(String id, Owner data){
        return restTemplate.postForObject(videominerUri + "/owners/" + id, data, Owner.class);
    }
//DONE: PUT y DELETE
    public void updateOwnerInVideoMiner(String id, Owner data) {
        HttpEntity<Owner> response = restTemplate.exchange(videominerUri + "/owners/" + id, HttpMethod.PUT, new HttpEntity<>(data), Owner.class);
        Owner updatedOwner = response.getBody();
    }

    public void deleteOwnerInVideoMiner(String id) {
        ResponseEntity<Void> response = restTemplate.exchange(videominerUri + "/owners/" + id, HttpMethod.DELETE, null, Void.class);
    }

    
}
