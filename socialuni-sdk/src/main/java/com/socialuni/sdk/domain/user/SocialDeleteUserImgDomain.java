package com.socialuni.sdk.domain.user;

import com.socialuni.sdk.factory.user.base.SocialMineUserDetailROFactory;
import com.socialuni.sdk.model.DO.user.SocialUserDO;
import com.socialuni.sdk.model.DO.user.SocialUserImgDO;
import com.socialuni.sdk.model.QO.user.SocialUserImgDeleteQO;
import com.socialuni.sdk.model.RO.user.SocialMineUserDetailRO;
import com.socialuni.social.constant.ContentStatus;
import com.socialuni.sdk.repository.UserImgRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

@Component
@Slf4j
public class SocialDeleteUserImgDomain {
    @Resource
    UserImgRepository userImgRepository;

    public SocialMineUserDetailRO deleteUserImg(SocialUserImgDeleteQO img, SocialUserDO mineUser) {
        SocialUserImgDO userImg = userImgRepository.getUserImgByUserIdAndUnionId(mineUser.getUnionId(), img.getUserImgId());
        userImg.setStatus(ContentStatus.delete);
        userImg.setUpdateTime(new Date());
        userImgRepository.save(userImg);

        SocialMineUserDetailRO socialMineUserDetailRO = SocialMineUserDetailROFactory.getMineUserDetail(mineUser);

        return socialMineUserDetailRO;
    }
}
