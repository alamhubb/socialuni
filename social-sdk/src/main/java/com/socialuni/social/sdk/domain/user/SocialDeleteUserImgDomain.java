package com.socialuni.social.sdk.domain.user;

import com.socialuni.social.constant.ContentStatus;
import com.socialuni.social.entity.model.DO.user.UserDO;
import com.socialuni.social.entity.model.DO.user.UserImgDO;
import com.socialuni.social.model.model.RO.user.SocialMineUserDetailRO;
import com.socialuni.social.sdk.factory.user.base.SocialMineUserDetailROFactory;
import com.socialuni.social.sdk.model.QO.user.SocialUserImgDeleteQO;
import com.socialuni.social.sdk.repository.UserImgRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

@Component
@Slf4j
public class SocialDeleteUserImgDomain {
    @Resource
    UserImgRepository userImgRepository;

    public SocialMineUserDetailRO deleteUserImg(SocialUserImgDeleteQO img, UserDO mineUser) {
        UserImgDO userImg = userImgRepository.getUserImgByUserIdAndId(mineUser.getId(), img.getUserImgId());
        userImg.setStatus(ContentStatus.delete);
        userImg.setUpdateTime(new Date());
        userImgRepository.save(userImg);

        SocialMineUserDetailRO socialMineUserDetailRO = SocialMineUserDetailROFactory.getMineUserDetail(mineUser);

        return socialMineUserDetailRO;
    }
}
