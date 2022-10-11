package com.socialuni.social.community.sdk.api;

import com.socialuni.social.community.sdk.model.SocialuniCircleModel;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SocialCircleApi{

    SocialuniCircleModel savePut(SocialuniCircleModel tagDO);

    //查询热门前10tag
    List<?  extends SocialuniCircleModel> findByStatusOrderByCountDesc(String status, Pageable pageable);
    SocialuniCircleModel findFirstByNameAndStatus(String name, String status);

    SocialuniCircleModel findFirstByIdAndStatus(Integer circleId, String status);

    List<?  extends SocialuniCircleModel> findByStatusAndVisibleGenderOrderByCountDesc(String status, String gender);

    //获取tagTYpe所有子tag
    List<?  extends SocialuniCircleModel> findByTagTypeIdAndStatusOrderByCountDesc(Integer tagTypeId, String status);

    List<?  extends SocialuniCircleModel> findByTagTypeIdAndStatusAndVisibleGenderOrderByCountDesc(Integer tagTypeId, String status, String gender);
}
