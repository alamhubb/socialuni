package com.socialuni.sdk.logic.domain.user;

import com.socialuni.sdk.factory.user.base.SocialMineUserDetailROFactory;
import com.socialuni.sdk.factory.user.img.UserImgDOFactory;
import com.socialuni.sdk.model.DO.user.SocialUserDO;
import com.socialuni.sdk.model.DO.user.SocialUserImgDO;
import com.socialuni.sdk.model.QO.user.SocialUserImgAddQO;
import com.socialuni.sdk.model.RO.user.SocialMineUserDetailRO;
import com.socialuni.sdk.dao.repository.UserImgRepository;
import com.socialuni.sdk.dao.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Slf4j
public class SocialAddUserImgDomain {
    @Resource
    UserImgRepository userImgRepository;
    @Resource
    UserRepository userRepository;

    public SocialMineUserDetailRO addUserImg(SocialUserImgAddQO socialUserImgAddQO, SocialUserDO mineUser) {

        SocialUserImgDO userImgDO = UserImgDOFactory.toUserImgDO(socialUserImgAddQO, mineUser);
        userRepository.save(mineUser);
        userImgRepository.save(userImgDO);

        SocialMineUserDetailRO socialMineUserDetailRO = SocialMineUserDetailROFactory.getMineUserDetail(mineUser);

        return socialMineUserDetailRO;
    }
}
