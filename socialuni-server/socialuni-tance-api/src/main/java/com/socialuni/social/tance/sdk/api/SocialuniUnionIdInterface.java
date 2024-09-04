package com.socialuni.social.tance.sdk.api;

import com.socialuni.social.tance.sdk.model.SocialuniUnionIdModler;

import java.util.List;

/**
 * @author wulinghui
 * @version 1.0
 * @module Socialuni
 * @date 2022/10/9 15:51
 * @since 1.0
 */
public interface SocialuniUnionIdInterface {

    SocialuniUnionIdModler save(SocialuniUnionIdModler uniContentUnionIdDO);

    SocialuniUnionIdModler findByUnionId(Long unionId);

    SocialuniUnionIdModler findByUuId(String uuid);

    List<String> findUuidAllByContentType(String contentTyp);

    List<Long> findAllUnionIdsByContentType(String contentTyp);

}
