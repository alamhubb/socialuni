package com.socialuni.social.peiwan.sdk.dao.rep;

import com.socialuni.social.peiwan.sdk.model.DO.SocialuniPeiwanInfoImgDO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SocialuniPeiwanImgRepository extends JpaRepository<SocialuniPeiwanInfoImgDO, Integer> {

    List<SocialuniPeiwanInfoImgDO> findAllByUserIdAndStatusOrderByIdAsc(Integer userId, String status);
}

