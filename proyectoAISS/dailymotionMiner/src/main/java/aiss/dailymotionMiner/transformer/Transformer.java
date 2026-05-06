package aiss.dailymotionMiner.transformer;

import aiss.dailymotionMiner.model.dailyMotionModels.Channel;
import aiss.dailymotionMiner.model.videominerModels.VMChannel;
import aiss.dailymotionMiner.model.videominerModels.VMUser;
import aiss.dailymotionMiner.model.dailyMotionModels.User;

public class Transformer {
    public VMChannel transformaChannel(Channel channel) {
        VMChannel vmChannel = new VMChannel(); // instancia de la clase VMChannel
        vmChannel.setId(channel.getId());
        vmChannel.setName(channel.getName());
        vmChannel.setDescription(channel.getDescription());
        vmChannel.setCreatedTime(channel.getCreatedTime()); // tiene q ser String
        return vmChannel;
    }
    public VMUser transformaUser(User user) {
        VMUser vmuser = new VMUser();
        vmuser.setId(user.getId());
        
    }
}
