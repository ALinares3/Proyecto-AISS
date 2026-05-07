package aiss.peertubeMiner.transformer;

import aiss.peertubeMiner.model.peertubeModels.User.User;
import aiss.peertubeMiner.model.videominerModels.VMCaption;
import aiss.peertubeMiner.model.videominerModels.VMChannel;
import aiss.peertubeMiner.model.videominerModels.VMComment;
import aiss.peertubeMiner.model.videominerModels.VMUser;
import aiss.peertubeMiner.model.videominerModels.VMVideo;
import aiss.peertubeminer.model.peertubeModels.Caption.Caption;
import aiss.peertubeminer.model.peertubeModels.Comment.Comment__1;
import aiss.peertubeminer.model.peertubeModels.Channel.Channel;
import aiss.peertubeminer.model.peertubeModels.Video.Video;

public class Transformer { 
    public VMCaption transformaCaption(Caption caption){
        VMCaption vmcaption = new VMCaption();

        vmcaption.setLink(caption.getCaptionPath());
        vmcaption.setLanguage(caption.getLanguage().toString()); // este está en la carpeta de Caption

        return vmcaption;
    }

    public VMUser transformaUser(User user) {
        VMUser vmuser = new VMUser();

        vmuser.setId((long)user.getId());
        vmuser.setName(user.getName());

        return vmuser;
    }

    public VMVideo transformaVideo(Video video) {
        VMVideo vmvideo = new VMVideo();

        vmvideo.setId(video.getId().toString());
        vmvideo.setName(video.getName());
        vmvideo.setDescription(video.getDescription());
        vmvideo.setReleaseTime(video.getPublishedAt());

        return vmvideo;
    }

    public VMComment transformaComment(Comment__1  comment) {
        VMComment vmcomment = new VMComment();

        vmcomment.setText(comment.getText());
        vmcomment.setId(comment.getId().toString());
        vmcomment.setCreatedOn(comment.getCreatedAt());

        return vmcomment;
    }

    public VMChannel transformaChannel(Channel channel) {
        VMChannel vmchannel = new VMChannel();

        vmchannel.setId(channel.getId().toString());
        vmchannel.setName(channel.getName());
        vmchannel.setDescription(channel.getDescription());
        vmchannel.setCreatedTime(channel.getCreatedAt());

        return vmchannel;
    }

}