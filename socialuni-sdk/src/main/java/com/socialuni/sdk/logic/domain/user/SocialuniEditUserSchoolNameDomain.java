package com.socialuni.sdk.logic.domain.user;

import com.socialuni.sdk.dao.DO.user.SocialuniUserExpandDO;
import com.socialuni.sdk.dao.repository.SocialuniUserExpandRepository;
import com.socialuni.sdk.logic.check.SocialuniSchoolCheck;
import com.socialuni.sdk.model.QO.user.SocialUserSchoolNameEditQO;
import com.socialuni.sdk.dao.repository.UserRepository;
import com.socialuni.sdk.dao.repository.community.SocialCircleRepository;
import com.socialuni.sdk.dao.repository.community.SocialuniTagTypeRepository;
import com.socialuni.sdk.logic.service.comment.IllegalWordService;
import com.socialuni.sdk.dao.store.SocialuniCircleRedis;
import com.socialuni.sdk.model.RO.user.SocialuniMineUserDetailRO;
import com.socialuni.sdk.utils.SocialuniUserUtil;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class SocialuniEditUserSchoolNameDomain {
    @Resource
    IllegalWordService illegalWordService;
    @Resource
    UserRepository userRepository;

    @Resource
    SocialCircleRepository socialCircleRepository;
    @Resource
    SocialuniTagTypeRepository tagTypeRepository;
    @Resource
    SocialuniCircleRedis socialCircleRedis;
    @Resource
    SocialuniSchoolCheck socialuniSchoolCheck;
    @Resource
    SocialuniUserExpandRepository socialuniUserExpandRepository;

    public SocialuniMineUserDetailRO editUserSchoolName(SocialUserSchoolNameEditQO socialMineUserDetailQO) {
        String schoolName = socialMineUserDetailQO.getSchoolName();

        socialuniSchoolCheck.checkSchoolName(schoolName);

        Integer userId = SocialuniUserUtil.getMineUserIdNotNull();

        SocialuniUserExpandDO socialuniUserExpandDO = socialuniUserExpandRepository.findByUserId(userId);

        if (socialuniUserExpandDO == null) {
            socialuniUserExpandDO = new SocialuniUserExpandDO();
        }
        socialuniUserExpandDO.setSchoolName(schoolName);

        socialuniUserExpandRepository.save(socialuniUserExpandDO);

        return null;
    }
}
