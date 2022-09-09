package com.socialuni.sdk.logic.domain.user;

import com.socialuni.sdk.dao.DO.user.SocialuniUserDO;
import com.socialuni.sdk.dao.DO.user.SocialUserImgDO;
import com.socialuni.sdk.logic.factory.RO.user.SocialuniMineUserDetailROFactory;
import com.socialuni.sdk.model.QO.user.SocialUserImgDeleteQO;
import com.socialuni.sdk.model.RO.user.SocialuniMineUserDetailRO;
import com.socialuni.sdk.constant.socialuni.ContentStatus;
import com.socialuni.sdk.dao.repository.UserImgRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

@Component
@Slf4j
public class SocialDeleteUserImgDomain {
    @Resource
    UserImgRepository userImgRepository;

    public SocialuniMineUserDetailRO deleteUserImg(SocialUserImgDeleteQO img, SocialuniUserDO mineUser) {
        SocialUserImgDO userImg = userImgRepository.getUserImgByUserIdAndUnionId(mineUser.getUnionId(), img.getUserImgId());
        userImg.setStatus(ContentStatus.delete);
        userImg.setUpdateTime(new Date());
        userImgRepository.save(userImg);

        SocialuniMineUserDetailRO socialMineUserDetailRO = SocialuniMineUserDetailROFactory.getMineUserDetail(mineUser);

        return socialMineUserDetailRO;
    }
}
