package com.socialuni.admin.web.model;

import com.socialuni.social.common.api.enumeration.CommonStatus;
import com.socialuni.social.sdk.dao.DO.SocialuniHomeSwiperDO;
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

    public SocialuniAdminHomeSwiperRO(SocialuniHomeSwiperDO SocialuniHomeSwiperDO) {
        this.id = SocialuniHomeSwiperDO.getId();
        this.enable = SocialuniHomeSwiperDO.getStatus().equals(CommonStatus.enable);
        this.name = SocialuniHomeSwiperDO.getName();
        this.skipType = SocialuniHomeSwiperDO.getSkipType();
        this.skipUrl = SocialuniHomeSwiperDO.getSkipUrl();
        this.standUrl = SocialuniHomeSwiperDO.getStandUrl();
        this.standType = SocialuniHomeSwiperDO.getStandType();
        this.imgUrl = SocialuniHomeSwiperDO.getImgUrl();
        this.topLevel = SocialuniHomeSwiperDO.getTopLevel();
    }

    public SocialuniHomeSwiperDO toModel() {
        SocialuniHomeSwiperDO SocialuniHomeSwiperDO = new SocialuniHomeSwiperDO();
        SocialuniHomeSwiperDO.setName(this.getName());
        SocialuniHomeSwiperDO.setImgUrl(this.getImgUrl());
        SocialuniHomeSwiperDO.setTopLevel(this.getTopLevel());
        SocialuniHomeSwiperDO.setSkipUrl(this.getSkipUrl());
        SocialuniHomeSwiperDO.setSkipType(this.getSkipType());
        SocialuniHomeSwiperDO.setStandUrl(this.getStandUrl());
        SocialuniHomeSwiperDO.setStandType(this.getStandType());
        if (!this.enable) {
            SocialuniHomeSwiperDO.setStatus(CommonStatus.delete);
        }
        SocialuniHomeSwiperDO.setDevId(DevAccountFacade.getDevIdNotNull());
        return SocialuniHomeSwiperDO;
    }

    public SocialuniHomeSwiperDO toModel(SocialuniHomeSwiperDO SocialuniHomeSwiperDODb) {
        SocialuniHomeSwiperDO SocialuniHomeSwiperDO = this.toModel();
        SocialuniHomeSwiperDO.setId(SocialuniHomeSwiperDODb.getId());
        SocialuniHomeSwiperDO.setCreateTime(SocialuniHomeSwiperDODb.getCreateTime());
        SocialuniHomeSwiperDO.setDevId(SocialuniHomeSwiperDODb.getDevId());
        return SocialuniHomeSwiperDO;
    }
}
