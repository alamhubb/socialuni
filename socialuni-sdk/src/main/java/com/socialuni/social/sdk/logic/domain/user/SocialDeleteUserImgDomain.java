package com.socialuni.social.sdk.logic.domain.user;

import com.socialuni.social.user.sdk.model.SocialuniUserDO;
import com.socialuni.social.user.sdk.model.SocialuniUserImgDO;
import com.socialuni.social.sdk.logic.factory.RO.user.SocialuniMineUserDetailROFactory;
import com.socialuni.social.sdk.model.QO.user.SocialUserImgDeleteQO;
import com.socialuni.social.sdk.model.RO.user.SocialuniMineUserDetailRO;
import com.socialuni.social.sdk.constant.socialuni.ContentStatus;
import com.socialuni.social.user.sdk.api.SocialuniUserImgRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

@Component
@Slf4j
public class SocialDeleteUserImgDomain {
    @Resource
    SocialuniUserImgRepository userImgRepository;

    public SocialuniMineUserDetailRO deleteUserImg(SocialUserImgDeleteQO img, SocialuniUserDO mineUser) {
        SocialuniUserImgDO userImg = userImgRepository.getUserImgByUserIdAndUnionId(mineUser.getUnionId(), img.getUserImgId());
        userImg.setStatus(ContentStatus.delete);
        userImg.setUpdateTime(new Date());
        userImgRepository.save(userImg);

        SocialuniMineUserDetailRO socialMineUserDetailRO = SocialuniMineUserDetailROFactory.getMineUserDetail(mineUser);

        return socialMineUserDetailRO;
    }
}
