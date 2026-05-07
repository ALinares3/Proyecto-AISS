package aiss.dailymotionMiner.transformer;

import aiss.dailymotionMiner.model.dailyMotionModels.Channel;
import aiss.dailymotionMiner.model.videominerModels.VMChannel;
import aiss.dailymotionMiner.model.videominerModels.VMUser;
import aiss.dailymotionMiner.model.dailyMotionModels.User;
import aiss.dailymotionMiner.model.dailyMotionModels.Video;
import aiss.dailymotionMiner.model.videominerModels.VMVideo;

public class Transformer {

    /*Hay q hacer una transformación por cada modelo q tenemos*/
    public VMChannel transformaChannel(Channel channel) {
        VMChannel vmChannel = new VMChannel(); // instancia de la clase VMChannel
        vmChannel.setId(channel.getId());
        vmChannel.setName(channel.getName());
        vmChannel.setDescription(channel.getDescription());
        vmChannel.setCreatedTime(channel.getCreatedTime()); // tiene q ser String pero es un INTEGER
        return vmChannel;
    }
    public VMUser transformaUser(User user) {
        VMUser vmuser = new VMUser();
        vmuser.setId(user.getId());
        
    }

    public VMVideo transformaVideo(Video video) {
        VMVideo vmvideo = new VMVideo();
        vmvideo.setId(video.getId());
        vmvideo.setName(video.getTitle());
        vmvideo.setDescription(video.getDescription());
        vmvideo.setReleaseTime(video.getCreatedTime());
        vmvideo.setAuthor(video.getOwner());
        vmvideo.setCaptions();
        vmvideo.setComments(video.getTags());
    }
}
