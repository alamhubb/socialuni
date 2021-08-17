package com.socialuni.social.sdk.domain.user;

import com.socialuni.social.entity.model.DO.user.UserDO;
import com.socialuni.social.entity.model.DO.user.UserImgDO;
import com.socialuni.social.model.model.RO.user.SocialMineUserDetailRO;
import com.socialuni.social.sdk.factory.user.base.SocialMineUserDetailROFactory;
import com.socialuni.social.sdk.factory.user.img.UserImgDOFactory;
import com.socialuni.social.model.model.QO.user.SocialUserImgAddQO;
import com.socialuni.social.sdk.repository.UserImgRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Slf4j
public class SocialAddUserImgDomain {
    @Resource
    UserImgRepository userImgRepository;

    public SocialMineUserDetailRO addUserImg(SocialUserImgAddQO img, UserDO mineUser) {

        UserImgDO userImgDO = UserImgDOFactory.toUserImgDO(img, mineUser);
        userImgRepository.save(userImgDO);

        SocialMineUserDetailRO socialMineUserDetailRO = SocialMineUserDetailROFactory.getMineUserDetail(mineUser);

        return socialMineUserDetailRO;
    }
}
