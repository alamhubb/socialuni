package com.socialuni.social.model.model;

import lombok.Data;

/**
 * @author qinkaiyuan
 * @date 2020-05-23 17:21
 */
@Data
public class HomeSwiperVO {
    private String name;
    private String skipUrl;
    private String imgUrl;
    private Boolean skip;
    private String skipType;
    private String standUrl;
    private String standType;

    public HomeSwiperVO toVO() {
        HomeSwiperVO homeSwiperVO = new HomeSwiperVO();
        homeSwiperVO.setName(this.name);
        homeSwiperVO.setSkipUrl(this.skipUrl);
        homeSwiperVO.setImgUrl(this.imgUrl);
        homeSwiperVO.setSkip(this.skip);
        homeSwiperVO.setSkipType(this.skipType);
        homeSwiperVO.setStandUrl(this.standUrl);
        homeSwiperVO.setStandType(this.standType);
        return homeSwiperVO;
    }
}
