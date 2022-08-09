package com.socialuni.center.web.model.RO.talk;

import com.socialuni.center.web.utils.UnionIdDbUtil;
import com.socialuni.social.constant.ContentType;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class CenterTalkImgRO {
    private String id;
    private String src;
    private Double aspectRatio;
//    private Integer size;
    //压缩率
//    private Double quality;


    public CenterTalkImgRO(CenterTalkImgRO centerTalkImgRO) {
        this.id = UnionIdDbUtil.createUidByUid(ContentType.talkImg, centerTalkImgRO.getId());
        this.src = centerTalkImgRO.getSrc();
        this.aspectRatio = centerTalkImgRO.getAspectRatio();
    }
}
