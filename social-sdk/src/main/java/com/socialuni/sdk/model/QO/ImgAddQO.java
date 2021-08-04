package com.socialuni.sdk.model.QO;


import com.socialuni.sdk.constant.AppConfigConst;
import com.socialuni.sdk.constant.ContentType;
import com.socialuni.sdk.constant.status.ContentStatus;
import com.socialuni.sdk.model.DO.user.UserDO;
import com.socialuni.sdk.model.DO.user.UserImgDO;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class ImgAddQO {
    @NotBlank
    private String src;
    @NotNull
    private Double aspectRatio;
    //压缩率
    private Double quality;
    private Integer size;

    public UserImgDO toUserImgDO(UserDO user, String imgUrl) {
        //这里需要记录，变更历史，通过照片有效无效记录，
        UserImgDO userImgDO = new UserImgDO();
        userImgDO.setSrc(imgUrl + this.getSrc());
        userImgDO.setAspectRatio(this.getAspectRatio());
        userImgDO.setQuality(this.getQuality());
        userImgDO.setSize(this.size);
        userImgDO.setUserId(user.getId());
        userImgDO.setStatus(ContentStatus.enable);
        userImgDO.setCreateTime(new Date());
        userImgDO.setContent(AppConfigConst.img_content);
        userImgDO.setReportContentType(ContentType.userImg);
        userImgDO.setReportNum(0);
        userImgDO.setIsSelfAuth(false);
        user.setAvatar(userImgDO.getSrc() + "!avatar");
        return userImgDO;
    }
}
