package com.socialuni.social.community.sdk.api;


import com.socialuni.social.community.sdk.model.SocialuniTagTypeModel;

import java.util.List;
import java.util.Optional;

public interface SocialuniTagTypeApi  {

    SocialuniTagTypeModel savePut(SocialuniTagTypeModel tagTypeDO);

    SocialuniTagTypeModel findFirstByName(String name);

    Optional<?  extends SocialuniTagTypeModel> findById(Integer id);

    List<?  extends SocialuniTagTypeModel> findByStatusOrderByOrderLevelDescTalkCountDesc(String status);
}