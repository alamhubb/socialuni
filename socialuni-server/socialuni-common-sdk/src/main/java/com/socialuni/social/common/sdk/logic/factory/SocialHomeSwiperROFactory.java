package com.socialuni.social.common.sdk.logic.factory;

import com.socialuni.social.common.sdk.utils.ListConvertUtil;
import com.socialuni.social.common.sdk.dao.DO.SocialuniHomeSwiperDO;
import com.socialuni.social.community.sdk.model.HomeSwiperVO;

import java.util.List;

public class SocialHomeSwiperROFactory {
    public static HomeSwiperVO toVO(SocialuniHomeSwiperDO homeSwiperDO) {
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

    public static List<HomeSwiperVO> toVOS(List<SocialuniHomeSwiperDO> dos) {
        return ListConvertUtil.toList(SocialHomeSwiperROFactory::toVO, dos);
    }
}
