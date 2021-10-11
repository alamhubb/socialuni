package com.socialuni.center.web.model.BO;

import com.socialuni.center.web.utils.UnionIdDbUtil;
import com.socialuni.social.constant.ContentType;
import com.socialuni.social.entity.model.DO.user.UserDO;
import com.socialuni.social.entity.model.DO.user.UserImgDO;
import com.socialuni.social.sdk.constant.AppConfigConst;
import com.socialuni.social.constant.ContentStatus;
import com.socialuni.social.sdk.model.RO.UserImgVO;
import lombok.Data;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Component
public class UserImgBO {
    private String id;
    @NotBlank
    private String src;
    @NotNull
    private Double aspectRatio;
    private Double height;
    private Double width;
    //压缩率
    private Double quality;
    private Integer size;
    private Integer reportNum;

    public UserImgBO() {
    }

    public UserImgBO(UserImgVO img) {
        this.id = img.getId();
        this.src = img.getSrc();
        this.aspectRatio = img.getAspectRatio();
        this.width = img.getWidth();
        this.height = img.getHeight();
        this.reportNum = img.getReportNum();
    }

    public UserImgBO(UserImgDO img, UserDO user) {
        this.id = UnionIdDbUtil.createUserImgUid(img.getId(), user);
        this.src = img.getSrc();
        this.aspectRatio = img.getAspectRatio();
        this.setWidth((double) 360);
        this.setHeight(this.getWidth() / this.getAspectRatio());
        this.reportNum = img.getReportNum();
    }

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

    /*public IdentityImgDO toIdentityImgDO(UserDO user, String imgUrl) {
        //这里需要记录，变更历史，通过照片有效无效记录，
        IdentityImgDO identityImgDO = new IdentityImgDO();
        identityImgDO.setSrc(imgUrl + this.getSrc());
        identityImgDO.setAspectRatio(this.getAspectRatio());
        identityImgDO.setUserId(user.getId());
        identityImgDO.setCreateTime(new Date());
//        identityImgDO.setStatus(CommonStatus.authFail);
        return identityImgDO;
    }*/

    public UserImgVO toVO() {
        UserImgVO imgVO = new UserImgVO();
        imgVO.setId(this.id);
        imgVO.setSrc(this.src);
        imgVO.setAspectRatio(this.aspectRatio);
        imgVO.setHeight(this.height);
        imgVO.setWidth(this.width);

        imgVO.setQuality(this.quality);
        imgVO.setSize(this.size);
        imgVO.setReportNum(this.reportNum);
        return imgVO;
    }

    public static List<UserImgVO> userImgDOToVOS(List<UserImgDO> imgDOs, UserDO user) {
        return imgDOs.stream().map(item -> new UserImgBO(item, user).toVO()).collect(Collectors.toList());
    }
}
