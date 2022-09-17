package com.socialuni.sdk.model.BO;

import com.socialuni.sdk.utils.UnionIdDbUtil;
import com.socialuni.sdk.dao.DO.user.SocialuniUserDO;
import com.socialuni.sdk.dao.DO.user.SocialUserImgDO;
import com.socialuni.sdk.model.RO.UserImgVO;
import com.socialuni.sdk.constant.socialuni.ContentType;
import com.socialuni.sdk.constant.AppConfigConst;
import com.socialuni.sdk.constant.socialuni.ContentStatus;
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

    public UserImgBO(SocialUserImgDO img, SocialuniUserDO user) {
        this.id = UnionIdDbUtil.getUidByUnionIdNotNull(img.getHahaId());
        this.src = img.getSrc();
        this.aspectRatio = img.getAspectRatio();
        this.setWidth((double) 360);
        this.setHeight(this.getWidth() / this.getAspectRatio());
        this.reportNum = img.getReportNum();
    }

    public SocialUserImgDO toUserImgDO(SocialuniUserDO user, String imgUrl) {
        //这里需要记录，变更历史，通过照片有效无效记录，
        SocialUserImgDO userImgDO = new SocialUserImgDO();
        userImgDO.setSrc(imgUrl + this.getSrc());
        userImgDO.setAspectRatio(this.getAspectRatio());
        userImgDO.setQuality(this.getQuality());
        userImgDO.setSize(this.size);
        userImgDO.setUserId(user.getUnionId());
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

    public static List<UserImgVO> userImgDOToVOS(List<SocialUserImgDO> imgDOs, SocialuniUserDO user) {
        return imgDOs.stream().map(item -> new UserImgBO(item, user).toVO()).collect(Collectors.toList());
    }
}
