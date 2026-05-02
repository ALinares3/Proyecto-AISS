package aiss.peertubeMiner.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import aiss.peertubeMiner.model.Acount;

public class AcountService {

    @Autowired
    RestTemplate restTemplate;

    public AcountService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Value("${dailymotionMiner.baseuri}")
    private String baseUri;

    @Value("${videominer.uri}")
    private String videominerUri;

//GET all
    public List<Acount> findAllAcounts() {
        Acount[] acounts = restTemplate.getForObject(baseUri + "/acounts", Acount[].class);
        return Arrays.asList(acounts);
    }

//GET by id
    public Acount findAcountById(String id){
        return restTemplate.getForObject(baseUri + "/acounts/" + id, Acount.class);
    }

//POST
    public Acount createAcountInVideoMiner(String id, Acount data){
        return restTemplate.postForObject(videominerUri + "/acounts/" + id, data, Acount.class);
    }
//DONE: PUT y DELETE
    public void updateAcountInVideoMiner(String id, Acount data) {
        HttpEntity<Acount> response = restTemplate.exchange(videominerUri + "/acounts/" + id, HttpMethod.PUT, new HttpEntity<>(data), Acount.class);
        Acount updatedAcount = response.getBody();
    }

    public void deleteOwnerInVideoMiner(String id) {
        ResponseEntity<Void> response = restTemplate.exchange(videominerUri + "/owners/" + id, HttpMethod.DELETE, null, Void.class);
    }

    
}
