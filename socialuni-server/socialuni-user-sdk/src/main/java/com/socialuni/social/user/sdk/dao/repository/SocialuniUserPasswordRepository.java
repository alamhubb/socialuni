package com.socialuni.social.user.sdk.dao.repository;


import com.socialuni.social.user.sdk.dao.DO.SocialUserPasswordDO;
import com.socialuni.social.user.sdk.dao.DO.SocialuniUserHugDo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SocialuniUserPasswordRepository extends JpaRepository<SocialUserPasswordDO, Integer> {


}

