package com.socialuni.social.user.sdk.repository;


import cn.hutool.core.bean.BeanUtil;
import com.socialuni.social.user.sdk.api.SocialUserViolationApi;
import com.socialuni.social.user.sdk.entity.SocialUserViolationDo;
import com.socialuni.social.user.sdk.model.SocialUserViolationModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SocialUserViolationRepository extends SocialUserViolationApi, JpaRepository<SocialUserViolationDo, Integer> {
    SocialUserViolationModel findOneByUserId(Integer userId);
    default SocialUserViolationModel savePut(SocialUserViolationModel socialUserViolationModel){
        return this.save(BeanUtil.toBean(socialUserViolationModel,SocialUserViolationDo.class));
    }

}

