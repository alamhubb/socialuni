/*
package com.socialuni.admin.web.model;

import com.qingchi.base.model.talk.TalkImgDO;
import com.qingchi.base.model.user.UserImgDO;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class ImgVO {
    private Integer id;
    private String src;
    private Double aspectRatio;

    public ImgVO() {
    }

    public ImgVO(TalkImgDO talkImgDO) {
        this.id = talkImgDO.getId();
        this.src = talkImgDO.getSrc();
        this.aspectRatio = talkImgDO.getAspectRatio();
    }

    public ImgVO(UserImgDO talkImgDO) {
        this.id = talkImgDO.getId();
        this.src = talkImgDO.getSrc();
        this.aspectRatio = talkImgDO.getAspectRatio();
    }

    public TalkImgDO toDO() {
        TalkImgDO talkImgDO = new TalkImgDO();
        talkImgDO.setSrc(this.src);
        talkImgDO.setAspectRatio(this.aspectRatio);
        talkImgDO.setCreateTime(new Date());
        return talkImgDO;
    }

    public static List<ImgVO> talkImgDOToVOS(List<TalkImgDO> imgDOS) {
        return imgDOS.stream().map(ImgVO::new).collect(Collectors.toList());
    }

    public static List<ImgVO> userImgDOToVOS(List<UserImgDO> imgDOS) {
        return imgDOS.stream().map(ImgVO::new).collect(Collectors.toList());
    }

    public static List<TalkImgDO> talkImgVOToDOS(List<ImgVO> imgVOS) {
        return imgVOS.stream().map(ImgVO::toDO).collect(Collectors.toList());
    }
}
*/
