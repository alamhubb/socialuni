package com.socialuni.social.user.sdk.repository;


import com.socialuni.social.user.sdk.model.DO.SocialUserIdentityAuthImgDO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SocialUserIdentityAuthImgRepository extends JpaRepository<SocialUserIdentityAuthImgDO, Integer> {
    SocialUserIdentityAuthImgDO findFirstById(Integer id);

    List<SocialUserIdentityAuthImgDO> findByIdIn(List<Integer> ids);
}

