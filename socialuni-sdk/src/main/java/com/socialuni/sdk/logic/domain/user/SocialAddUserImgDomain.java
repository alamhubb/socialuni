package com.socialuni.sdk.logic.domain.user;

import com.socialuni.sdk.logic.factory.RO.user.SocialuniMineUserDetailROFactory;
import com.socialuni.sdk.logic.factory.user.img.UserImgDOFactory;
import com.socialuni.sdk.dao.DO.user.SocialuniUserDO;
import com.socialuni.sdk.dao.DO.user.SocialUserImgDO;
import com.socialuni.sdk.model.QO.user.SocialUserImgAddQO;
import com.socialuni.sdk.model.RO.user.SocialuniMineUserDetailRO;
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

    public SocialuniMineUserDetailRO addUserImg(SocialUserImgAddQO socialUserImgAddQO, SocialuniUserDO mineUser) {

        SocialUserImgDO userImgDO = UserImgDOFactory.toUserImgDO(socialUserImgAddQO, mineUser);
//        userRepository.save(mineUser);
        userImgRepository.save(userImgDO);

        SocialuniMineUserDetailRO socialMineUserDetailRO = SocialuniMineUserDetailROFactory.getMineUserDetail(mineUser);

        return socialMineUserDetailRO;
    }

    public SocialuniMineUserDetailRO addUserAvatarImg(SocialUserImgAddQO socialUserImgAddQO, SocialuniUserDO mineUser) {

        SocialUserImgDO userImgDO = UserImgDOFactory.toUserImgDO(socialUserImgAddQO, mineUser);
        userRepository.save(mineUser);

        SocialuniMineUserDetailRO socialMineUserDetailRO = SocialuniMineUserDetailROFactory.getMineUserDetail(mineUser);

        return socialMineUserDetailRO;
    }
}
