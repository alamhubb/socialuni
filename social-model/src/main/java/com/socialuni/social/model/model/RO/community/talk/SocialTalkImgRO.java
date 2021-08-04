package com.socialuni.social.model.model.RO.community.talk;

import com.socialuni.social.model.model.QO.user.SocialId;
import lombok.Data;


@Data
public class SocialTalkImgRO extends SocialId {
    private String src;
    private Double aspectRatio;
//    private Integer size;
    //压缩率
//    private Double quality;
}
