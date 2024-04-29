package com.socialuni.social.userImg.logic.domain;

import com.socialuni.social.common.api.enumeration.ContentStatus;
import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
import com.socialuni.social.userImg.dao.DO.SocialuniUserImgDo;
import com.socialuni.social.userImg.dao.repository.SocialuniUserImgRepository;
import com.socialuni.social.userImg.model.SocialUserImgDeleteQO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

@Component
@Slf4j
public class SocialDeleteUserImgDomain {
    @Resource
    SocialuniUserImgRepository userImgRepository;

    public void deleteUserImg(SocialUserImgDeleteQO img, SocialuniUserDo mineUser) {
        SocialuniUserImgDo userImg = userImgRepository.getUserImgByUserIdAndUnionId(mineUser.getUnionId(), img.getUserImgId());
        userImg.setStatus(ContentStatus.delete);
        userImg.setUpdateTime(new Date());
        userImgRepository.savePut(userImg);

//        SocialuniMineUserDetailRO socialMineUserDetailRO = SocialuniMineUserDetailROFactory.getMineUserDetail(mineUser);

//        return socialMineUserDetailRO;
//        return null;
    }
}
