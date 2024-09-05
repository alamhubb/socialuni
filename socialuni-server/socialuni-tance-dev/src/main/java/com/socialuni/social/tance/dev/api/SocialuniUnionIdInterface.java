package com.socialuni.social.tance.dev.api;

import com.socialuni.social.tance.dev.entity.SocialuniUnionIdDo;

import java.util.List;

/**
 * @author wulinghui
 * @version 1.0
 * @module Socialuni
 * @date 2022/10/9 15:51
 * @since 1.0
 */
public interface SocialuniUnionIdInterface {

    SocialuniUnionIdDo savePut(SocialuniUnionIdDo uniContentUnionIdDO);

    SocialuniUnionIdDo findByUnionId(Long unionId);

    SocialuniUnionIdDo findByUuId(String uuid);

    List<String> findUuidAllByContentType(String contentTyp);

    List<Long> findAllUnionIdsByContentType(String contentTyp);

}
