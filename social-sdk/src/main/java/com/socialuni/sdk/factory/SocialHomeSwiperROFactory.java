package com.socialuni.sdk.factory;

import com.socialuni.entity.model.DO.HomeSwiperDO;
import com.socialuni.social.model.model.HomeSwiperVO;

import java.util.List;

public class SocialHomeSwiperROFactory {
    public static HomeSwiperVO toVO(HomeSwiperDO homeSwiperDO) {
        HomeSwiperVO homeSwiperVO = new HomeSwiperVO();
        homeSwiperVO.setName(homeSwiperDO.getName());
        homeSwiperVO.setSkipUrl(homeSwiperDO.getSkipUrl());
        homeSwiperVO.setImgUrl(homeSwiperDO.getImgUrl());
        homeSwiperVO.setSkip(homeSwiperDO.getSkip());
        homeSwiperVO.setSkipType(homeSwiperDO.getSkipType());
        homeSwiperVO.setStandUrl(homeSwiperDO.getStandUrl());
        homeSwiperVO.setStandType(homeSwiperDO.getStandType());
        return homeSwiperVO;
    }

    public static List<HomeSwiperVO> toVOS(List<HomeSwiperDO> dos) {
        return ListConvertUtil.toList(SocialHomeSwiperROFactory::toVO, dos);
    }
}
