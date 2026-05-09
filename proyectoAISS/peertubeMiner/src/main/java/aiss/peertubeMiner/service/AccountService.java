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
public class AccountService {

    @Autowired
    RestTemplate restTemplate;

    public AccountService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Value("${peertubeMiner.baseuri}")
    private String baseUri;

    @Value("${videominer.uri}")
    private String videominerUri;

    @Autowired
    private Transformer transformer;

//GET all
    public List<Account> findAllAccounts() {
        Account[] accounts = restTemplate.getForObject(baseUri + "/accounts", Account[].class);
        return Arrays.asList(accounts);
    }
//POST
    public VMUser createAccountInVideoMiner(User data){
        VMUser user = transformer.transformaUser(data);
        return restTemplate.postForObject(videominerUri + "/accounts", user, VMUser.class);
    }
}
