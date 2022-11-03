package com.socialuni.admin.web.model;

import com.socialuni.social.common.sdk.enumeration.CommonStatus;
import com.socialuni.social.sdk.model.model.SocialuniHomeSwiperModel;
import com.socialuni.social.tance.sdk.facade.DevAccountFacade;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SocialuniAdminHomeSwiperRO {
    private Integer id;
    private Boolean enable;
    //跳转到的页面的title
    private String name;
    //跳转的页面
    private String skipType;
    private String skipUrl;
    //备用url，小程序不支持跳转app，可跳转其他页面
    private String standUrl;
    private String standType;
    private String imgUrl;
    private Integer topLevel;

    public SocialuniAdminHomeSwiperRO(SocialuniHomeSwiperModel socialuniHomeSwiperModel) {
        this.id = socialuniHomeSwiperModel.getId();
        this.enable = socialuniHomeSwiperModel.getStatus().equals(CommonStatus.enable);
        this.name = socialuniHomeSwiperModel.getName();
        this.skipType = socialuniHomeSwiperModel.getSkipType();
        this.skipUrl = socialuniHomeSwiperModel.getSkipUrl();
        this.standUrl = socialuniHomeSwiperModel.getStandUrl();
        this.standType = socialuniHomeSwiperModel.getStandType();
        this.imgUrl = socialuniHomeSwiperModel.getImgUrl();
        this.topLevel = socialuniHomeSwiperModel.getTopLevel();
    }

    public SocialuniHomeSwiperModel toModel() {
        SocialuniHomeSwiperModel socialuniHomeSwiperModel = new SocialuniHomeSwiperModel();
        socialuniHomeSwiperModel.setName(this.getName());
        socialuniHomeSwiperModel.setImgUrl(this.getImgUrl());
        socialuniHomeSwiperModel.setTopLevel(this.getTopLevel());
        socialuniHomeSwiperModel.setSkipUrl(this.getSkipUrl());
        socialuniHomeSwiperModel.setSkipType(this.getSkipType());
        socialuniHomeSwiperModel.setStandUrl(this.getStandUrl());
        socialuniHomeSwiperModel.setStandType(this.getStandType());
        if (!this.enable) {
            socialuniHomeSwiperModel.setStatus(CommonStatus.delete);
        }
        socialuniHomeSwiperModel.setDevId(DevAccountFacade.getDevIdNotNull());
        return socialuniHomeSwiperModel;
    }

    public SocialuniHomeSwiperModel toModel(SocialuniHomeSwiperModel socialuniHomeSwiperModelDb) {
        SocialuniHomeSwiperModel socialuniHomeSwiperModel = this.toModel();
        socialuniHomeSwiperModel.setId(socialuniHomeSwiperModelDb.getId());
        socialuniHomeSwiperModel.setCreateTime(socialuniHomeSwiperModelDb.getCreateTime());
        socialuniHomeSwiperModel.setDevId(socialuniHomeSwiperModelDb.getDevId());
        return socialuniHomeSwiperModel;
    }
}
