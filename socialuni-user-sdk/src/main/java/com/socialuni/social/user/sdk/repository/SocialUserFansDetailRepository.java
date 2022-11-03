package com.socialuni.social.user.sdk.repository;


import cn.hutool.core.bean.BeanUtil;
import com.socialuni.social.user.sdk.entity.SocialUserFansDetailDo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SocialUserFansDetailRepository extends JpaRepository<SocialUserFansDetailDo, Integer> {
    SocialUserFansDetailDo findByUserId(Integer userId);

    default SocialUserFansDetailDo savePut(SocialUserFansDetailDo SocialUserFansDetailDo){
        return this.save(BeanUtil.toBean(SocialUserFansDetailDo,SocialUserFansDetailDo.class));
    }
}

