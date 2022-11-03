package com.socialuni.social.user.sdk.model;

import com.socialuni.social.user.sdk.api.SocialuniTextCheckDO;
import com.socialuni.social.common.sdk.entity.SocialuniImgBaseDo;
import lombok.Data;

import java.io.Serializable;


@Data
public class SocialuniUserImgModel extends SocialuniImgBaseDo implements SocialuniTextCheckDO, Serializable {
    //发表商户
    private Boolean hasPeopleImg;
    private Boolean peopleImgIsAdult;
    //ocr后查看是否包含未成年内容
    private Boolean hasUnderageContent;
    private Boolean hasContactInfo;
    private Boolean hasQrCode;

    //do 只有一个构造
    public SocialuniUserImgModel() {
        this.hasPeopleImg = false;
        this.peopleImgIsAdult = false;
        this.hasUnderageContent = false;
        this.hasContactInfo = false;
        this.hasQrCode = false;
    }
}
