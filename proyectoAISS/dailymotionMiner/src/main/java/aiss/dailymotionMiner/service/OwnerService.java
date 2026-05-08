package aiss.dailymotionMiner.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import aiss.dailymotionMiner.model.dailymotionModels.User;
import aiss.dailymotionMiner.model.videominerModels.VMUser;
import aiss.dailymotionMiner.transformer.Transformer;

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

    private Transformer transformer;

//GET all
    public List<User> findAllOwners() {
        User[] owners = restTemplate.getForObject(baseUri + "/owners", User[].class);
        return Arrays.asList(owners);
    }
//POST
    public VMUser createAcountInVideoMiner(User data){
        VMUser user = transformer.transformaUser(data);
        return restTemplate.postForObject(videominerUri + "/acounts/" + user.getId(), user, VMUser.class);
    }
}
