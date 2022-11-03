package com.socialuni.social.user.sdk.api;

import com.socialuni.social.user.sdk.model.SocialuniUserImgModel;

import java.util.List;

public interface SocialuniUserImgInterface {
    SocialuniUserImgModel findOneByUnionId(Integer unionId);

    List<Integer> findUnionIdTop6ByUserIdAndStatusInOrderByCreateTimeDesc(Integer userId, List<String> status);

    List<Integer> findUnionIdTop50ByUserIdAndStatusInOrderByCreateTimeDesc(Integer userId, List<String> status);

    SocialuniUserImgModel savePut(SocialuniUserImgModel userImgDO);

    SocialuniUserImgModel findOneByUnionIdAndStatus(Integer unionId, String status);

    List<SocialuniUserImgModel> findAllByUnionIdIsNull();

    SocialuniUserImgModel getUserImgByUserIdAndSrc(Integer userId, String src);

    SocialuniUserImgModel getUserImgByUserIdAndUnionId(Integer userId, Integer unionId);

    //获取talkImg
    SocialuniUserImgModel findFirstBySrc(String imgUlr);
}

