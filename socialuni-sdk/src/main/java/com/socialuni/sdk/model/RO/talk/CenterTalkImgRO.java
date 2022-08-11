package com.socialuni.sdk.model.RO.talk;

import com.socialuni.sdk.model.RO.community.UniContentIdRO;
import com.socialuni.social.constant.ContentType;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class CenterTalkImgRO extends UniContentIdRO {
    private String id;
    private String src;
    private Double aspectRatio;
//    private Integer size;
    //压缩率
//    private Double quality;


    public CenterTalkImgRO(CenterTalkImgRO centerTalkImgRO) {
        super(ContentType.talkImg, centerTalkImgRO);
        this.src = centerTalkImgRO.getSrc();
        this.aspectRatio = centerTalkImgRO.getAspectRatio();
    }
}
