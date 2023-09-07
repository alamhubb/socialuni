package com.socialuni.social.peiwan.sdk.dao.rep;

import com.socialuni.social.peiwan.sdk.model.DO.SocialuniPeiwanSkillDO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SocialuniPeiwanSkillRepository extends JpaRepository<SocialuniPeiwanSkillDO, Integer> {

    SocialuniPeiwanSkillDO findFirstBySkillName(String skillName);
}

