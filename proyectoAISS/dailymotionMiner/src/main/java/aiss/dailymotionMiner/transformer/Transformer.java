package aiss.dailymotionMiner.transformer;

import aiss.dailymotionMiner.model.dailymotionModels.Channel;
import aiss.dailymotionMiner.model.dailymotionModels.Subtitle;
import aiss.dailymotionMiner.model.videominerModels.VMChannel;
import aiss.dailymotionMiner.model.videominerModels.VMUser;
import aiss.dailymotionMiner.model.dailymotionModels.User;
import aiss.dailymotionMiner.model.dailymotionModels.Video;
import aiss.dailymotionMiner.model.videominerModels.VMCaption;
import aiss.dailymotionMiner.model.videominerModels.VMVideo;

public class Transformer {

    /*Hay q hacer una transformación por cada modelo q tenemos*/
    public VMChannel transformaChannel(Channel channel) {
        VMChannel vmChannel = new VMChannel(); // instancia de la clase VMChannel
        vmChannel.setName(channel.getName());
        vmChannel.setDescription(channel.getDescription());
        vmChannel.setCreatedTime(channel.getCreatedTime().toString()); // tiene q ser String pero es un INTEGER
        return vmChannel;
    }
    public VMUser transformaUser(User user) {
        VMUser vmuser = new VMUser();
        vmuser.setName(user.getScreenname());
        vmuser.setPicture_link(user.getAvatar720Url());
        vmuser.setUser_link(user.getUrl());

        return vmuser;
    }

    public VMVideo transformaVideo(Video video) {
        VMVideo vmvideo = new VMVideo();
        vmvideo.setName(video.getTitle());
        vmvideo.setDescription(video.getDescription());
        vmvideo.setReleaseTime(video.getCreatedTime().toString());

        return vmvideo;
    }

    public VMCaption transformaCaption(Subtitle subtitle){
        VMCaption vmCaption = new VMCaption();
        vmCaption.setName(subtitle.getUrl());
        vmCaption.setLanguage(subtitle.getLanguage());
        return vmCaption;
    }
}
