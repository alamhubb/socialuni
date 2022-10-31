package com.socialuni.social.sdk.model.BO;

import com.socialuni.social.sdk.constant.AppConfigConst;
import com.socialuni.social.sdk.constant.socialuni.ContentStatus;
import com.socialuni.social.tance.sdk.enumeration.SocialuniContentType;
import com.socialuni.social.user.sdk.model.SocialuniUserModel;
import com.socialuni.social.user.sdk.model.SocialuniUserImgModel;
import com.socialuni.social.sdk.model.RO.UserImgVO;
import com.socialuni.social.sdk.facade.SocialuniUnionIdFacede;
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

    public UserImgBO(SocialuniUserImgModel img, SocialuniUserModel user) {
        this.id = SocialuniUnionIdFacede.getUuidByUnionIdNotNull(img.getUnionId());
        this.src = img.getSrc();
        this.aspectRatio = img.getAspectRatio();
        this.setWidth((double) 360);
        this.setHeight(this.getWidth() / this.getAspectRatio());
        this.reportNum = img.getReportNum();
    }

    public SocialuniUserImgModel toUserImgDO(SocialuniUserModel user, String imgUrl) {
        //这里需要记录，变更历史，通过照片有效无效记录，
        SocialuniUserImgModel userImgDO = new SocialuniUserImgModel();
        userImgDO.setSrc(imgUrl + this.getSrc());
        userImgDO.setAspectRatio(this.getAspectRatio());
        userImgDO.setQuality(this.getQuality());
        userImgDO.setSize(this.size);
        userImgDO.setUserId(user.getUnionId());
        userImgDO.setStatus(ContentStatus.enable);
        userImgDO.setCreateTime(new Date());
        userImgDO.setContent(AppConfigConst.img_content);
        userImgDO.setContentType(SocialuniContentType.userImg);
        userImgDO.setReportNum(0);
        userImgDO.setPeopleImgIsAdult(false);
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

    public static List<UserImgVO> userImgDOToVOS(List<SocialuniUserImgModel> imgDOs, SocialuniUserModel user) {
        return imgDOs.stream().map(item -> new UserImgBO(item, user).toVO()).collect(Collectors.toList());
    }
}
