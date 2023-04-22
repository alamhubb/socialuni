package com.socialuni.social.sdk.logic.domain.user;

import com.socialuni.social.common.api.enumeration.ContentStatus;
import com.socialuni.social.sdk.logic.factory.RO.user.SocialuniMineUserDetailROFactory;
import com.socialuni.social.user.sdk.model.QO.SocialUserImgDeleteQO;
import com.socialuni.social.common.api.model.user.SocialuniMineUserDetailRO;
import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
import com.socialuni.social.user.sdk.model.DO.SocialuniUserImgDo;
import com.socialuni.social.user.sdk.repository.SocialuniUserImgRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

@Component
@Slf4j
public class SocialDeleteUserImgDomain {
    @Resource
    SocialuniUserImgRepository userImgRepository;

    public SocialuniMineUserDetailRO deleteUserImg(SocialUserImgDeleteQO img, SocialuniUserDo mineUser) {
        SocialuniUserImgDo userImg = userImgRepository.getUserImgByUserIdAndUnionId(mineUser.getUnionId(), img.getUserImgId());
        userImg.setStatus(ContentStatus.delete);
        userImg.setUpdateTime(new Date());
        userImgRepository.savePut(userImg);

        SocialuniMineUserDetailRO socialMineUserDetailRO = SocialuniMineUserDetailROFactory.getMineUserDetail(mineUser);

        return socialMineUserDetailRO;
    }
}
