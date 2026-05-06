package aiss.peertubeMiner.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import aiss.peertubeminer.model.peertubeModels.Comment.Account;

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
    public List<Account> findAllAcounts() {
        Account[] accounts = restTemplate.getForObject(baseUri + "/acounts", Account[].class);
        return Arrays.asList(accounts);
    }

//GET by id
    public Account findAcountById(String id){
        return restTemplate.getForObject(baseUri + "/acounts/" + id, Account.class);
    }

//POST
    public Account createAcountInVideoMiner(String id, Account data){
        return restTemplate.postForObject(videominerUri + "/acounts/" + id, data, Account       .class);
    }
}
