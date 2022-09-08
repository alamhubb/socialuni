package com.socialuni.sdk.model.RO.talk;
import com.socialuni.sdk.model.RO.user.SocialuniContentIdRO;
import lombok.Data;


@Data
public class SocialuniTalkImgRO extends SocialuniContentIdRO {
    private String src;
    private Double aspectRatio;
//    private Integer size;
    //压缩率
//    private Double quality;
}
