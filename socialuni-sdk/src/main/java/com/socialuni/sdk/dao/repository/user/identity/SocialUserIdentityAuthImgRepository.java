package com.socialuni.sdk.dao.repository.user.identity;


import com.socialuni.sdk.model.DO.user.SocialUserIdentityAuthImgDO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SocialUserIdentityAuthImgRepository extends JpaRepository<SocialUserIdentityAuthImgDO, Integer> {
    SocialUserIdentityAuthImgDO findFirstById(Integer id);

    List<SocialUserIdentityAuthImgDO> findByIdIn(List<Integer> ids);
}

