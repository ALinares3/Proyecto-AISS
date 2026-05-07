package aiss.peertubeMiner.transformer;

import aiss.peertubeMiner.model.peertubeModels.User.User;
import aiss.peertubeMiner.model.videominerModels.VMCaption;
import aiss.peertubeMiner.model.videominerModels.VMChannel;
import aiss.peertubeMiner.model.videominerModels.VMComment;
import aiss.peertubeMiner.model.videominerModels.VMUser;
import aiss.peertubeMiner.model.videominerModels.VMVideo;
import aiss.peertubeMiner.model.peertubeModels.Caption.Caption;
import aiss.peertubeMiner.model.peertubeModels.Comment.Comment__1;
import aiss.peertubeMiner.model.peertubeModels.Channel.Channel;
import aiss.peertubeMiner.model.peertubeModels.Video.Video;

public class Transformer {

    public VMCaption transformaCaption(Caption caption) {
        VMCaption vmcaption = new VMCaption();
        vmcaption.setLink(caption.getCaptionPath());
        vmcaption.setLanguage(caption.getLanguage().toString()); // este está en la carpeta de Caption

        return vmcaption;
    }

    public VMUser transformaUser(User user) {
        VMUser vmuser = new VMUser();

        vmuser.setName(user.getName());
        vmuser.setName(user.getName());
        vmuser.setUser_link(user.getUrl());
        vmuser.setPicture_link(user.getAvatars().stream().findFirst().orElse(null).toString());

        return vmuser;
    }

    public VMVideo transformaVideo(Video video) {
        VMVideo vmvideo = new VMVideo();
        vmvideo.setName(video.getName());
        vmvideo.setDescription(video.getDescription());
        vmvideo.setReleaseTime(video.getPublishedAt());

        return vmvideo;
    }

    public VMComment transformaComment(Comment__1 comment) {
        VMComment vmcomment = new VMComment();

        vmcomment.setText(comment.getText());
        vmcomment.setCreatedOn(comment.getCreatedAt());

        return vmcomment;
    }

    public VMChannel transformaChannel(Channel channel) {
        VMChannel vmchannel = new VMChannel();
        vmchannel.setName(channel.getName());
        vmchannel.setDescription(channel.getDescription());
        vmchannel.setCreatedTime(channel.getCreatedAt());
        vmchannel.setVideos(null);

        return vmchannel;
    }

}
