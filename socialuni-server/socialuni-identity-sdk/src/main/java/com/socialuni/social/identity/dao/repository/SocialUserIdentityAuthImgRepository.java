package com.socialuni.social.identity.dao.repository;


import com.socialuni.social.identity.dao.DO.SocialUserIdentityAuthImgDO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SocialUserIdentityAuthImgRepository extends JpaRepository<SocialUserIdentityAuthImgDO, Integer> {
    SocialUserIdentityAuthImgDO findFirstById(Integer id);

    List<SocialUserIdentityAuthImgDO> findByIdIn(List<Integer> ids);
}

