package com.socialuni.sdk.model.RO.talk;
import com.socialuni.sdk.model.RO.user.SocialuniContentIdRO;
import lombok.Data;


@Data
public class SocialuniTalkImgRO {
    //talkImg依附于talk不单独存在，所以无需id
    private String src;
    private Double aspectRatio;
//    private Integer size;
    //压缩率
//    private Double quality;
}
