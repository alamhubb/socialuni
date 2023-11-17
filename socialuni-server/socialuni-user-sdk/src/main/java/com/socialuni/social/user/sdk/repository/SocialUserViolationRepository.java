package com.socialuni.social.user.sdk.repository;


import cn.hutool.core.bean.BeanUtil;
import com.socialuni.social.user.sdk.dao.DO.SocialUserViolationDo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SocialUserViolationRepository extends JpaRepository<SocialUserViolationDo, Integer> {
    SocialUserViolationDo findOneByUserId(Integer userId);
    default SocialUserViolationDo savePut(SocialUserViolationDo SocialUserViolationDo){
        return this.save(BeanUtil.toBean(SocialUserViolationDo,SocialUserViolationDo.class));
    }

}

