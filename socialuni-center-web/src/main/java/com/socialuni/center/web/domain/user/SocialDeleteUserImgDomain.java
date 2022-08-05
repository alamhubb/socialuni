package com.socialuni.center.web.domain.user;

import com.socialuni.center.web.factory.user.base.SocialMineUserDetailROFactory;
import com.socialuni.social.constant.ContentStatus;
import com.socialuni.center.web.model.DO.user.SocialUserDO;
import com.socialuni.center.web.model.DO.user.SocialUserImgDO;
import com.socialuni.center.web.model.RO.user.SocialMineUserDetailRO;
import com.socialuni.center.web.model.QO.user.SocialUserImgDeleteQO;
import com.socialuni.center.web.repository.UserImgRepository;
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
        SocialUserImgDO userImg = userImgRepository.getUserImgByUserIdAndId(mineUser.getId(), img.getUserImgId());
        userImg.setStatus(ContentStatus.delete);
        userImg.setUpdateTime(new Date());
        userImgRepository.save(userImg);

        SocialMineUserDetailRO socialMineUserDetailRO = SocialMineUserDetailROFactory.getMineUserDetail(mineUser);

        return socialMineUserDetailRO;
    }
}
