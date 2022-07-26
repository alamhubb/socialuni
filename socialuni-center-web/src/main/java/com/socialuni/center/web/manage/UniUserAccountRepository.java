package com.socialuni.center.web.manage;


import com.socialuni.center.web.model.DO.UniUserAccountDO;
import com.socialuni.social.entity.model.DO.user.SocialUserAccountDO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UniUserAccountRepository extends JpaRepository<UniUserAccountDO, Integer> {


    UniUserAccountDO findByDevIdAndThirdUserId(Integer devId, String thirdUserId);

}

