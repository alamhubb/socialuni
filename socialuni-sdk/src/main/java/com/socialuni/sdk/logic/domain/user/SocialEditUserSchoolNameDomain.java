package com.socialuni.sdk.logic.domain.user;

import com.socialuni.sdk.constant.SocialuniConst;
import com.socialuni.sdk.model.DO.tag.TagTypeDO;
import com.socialuni.sdk.model.QO.user.SocialUserSchoolNameEditQO;
import com.socialuni.sdk.model.RO.user.SocialMineUserDetailRO;
import com.socialuni.sdk.dao.repository.UserRepository;
import com.socialuni.sdk.dao.repository.community.SocialCircleRepository;
import com.socialuni.sdk.dao.repository.community.TagTypeRepository;
import com.socialuni.sdk.logic.service.comment.IllegalWordService;
import com.socialuni.sdk.dao.store.SocialCircleRedis;
import com.socialuni.social.web.sdk.exception.SocialParamsException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class SocialEditUserSchoolNameDomain {
    @Resource
    IllegalWordService illegalWordService;
    @Resource
    UserRepository userRepository;

    @Resource
    SocialCircleRepository socialCircleRepository;
    @Resource
    TagTypeRepository tagTypeRepository;
    @Resource
    SocialCircleRedis socialCircleRedis;

    public SocialMineUserDetailRO editUserSchoolName(SocialUserSchoolNameEditQO socialMineUserDetailQO) {
        String schoolName = socialMineUserDetailQO.getSchoolName();

        TagTypeDO tagTypeDO = tagTypeRepository.findFirstByName(SocialuniConst.schoolCircleTypeName);

        if (tagTypeDO == null) {
            throw new SocialParamsException("未找到相关圈子分类");
        }

//        List<SocialCircleDO> circleDOS = socialCircleRedis.getCirclesByTagTypeId(tagTypeDO.getId());


        return null;
    }
}
