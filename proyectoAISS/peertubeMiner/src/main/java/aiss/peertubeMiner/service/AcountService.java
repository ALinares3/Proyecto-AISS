package aiss.peertubeMiner.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import aiss.peertubeMiner.model.peertubeModels.User.User;
import aiss.peertubeMiner.model.peertubeModels.Video.Account;
import aiss.peertubeMiner.model.videominerModels.VMUser;
import aiss.peertubeMiner.transformer.Transformer;

@Service
public class AcountService {

    @Autowired
    RestTemplate restTemplate;

    public AcountService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Value("${peertubeMiner.baseuri}")
    private String baseUri;

    @Value("${videominer.uri}")
    private String videominerUri;

    private Transformer transformer;

//GET all
    public List<Account> findAllAcounts() {
        Account[] accounts = restTemplate.getForObject(baseUri + "/acounts", Account[].class);
        return Arrays.asList(accounts);
    }
//POST
    public VMUser createAcountInVideoMiner(User data){
        VMUser user = transformer.transformaUser(data);
        return restTemplate.postForObject(videominerUri + "/acounts/" + data.getId(), user, VMUser.class);
    }
}
