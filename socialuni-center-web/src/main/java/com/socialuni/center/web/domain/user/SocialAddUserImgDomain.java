package com.socialuni.center.web.domain.user;

import com.socialuni.center.web.constant.AppConfigConst;
import com.socialuni.center.web.factory.user.base.SocialMineUserDetailROFactory;
import com.socialuni.social.constant.ContentStatus;
import com.socialuni.social.constant.ContentType;
import com.socialuni.center.web.model.DO.user.UserDO;
import com.socialuni.center.web.model.DO.user.UserImgDO;
import com.socialuni.center.web.model.QO.user.SocialUserImgAddQO;
import com.socialuni.center.web.model.RO.user.SocialMineUserDetailRO;
import com.socialuni.center.web.config.SocialAppConfig;
import com.socialuni.center.web.repository.UserImgRepository;
import com.socialuni.center.web.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

@Component
@Slf4j
public class SocialAddUserImgDomain {
    @Resource
    UserImgRepository userImgRepository;
    @Resource
    UserRepository userRepository;

    public SocialMineUserDetailRO addUserImg(SocialUserImgAddQO socialUserImgAddQO, UserDO mineUser) {

        UserImgDO userImgDO = new UserImgDO();
        userImgDO.setSrc(socialUserImgAddQO.getSrc());
        userImgDO.setAspectRatio(socialUserImgAddQO.getAspectRatio());
        userImgDO.setQuality(socialUserImgAddQO.getQuality());
        userImgDO.setSize(socialUserImgAddQO.getSize());
        userImgDO.setUserId(mineUser.getId());
        userImgDO.setStatus(ContentStatus.enable);
        userImgDO.setCreateTime(new Date());
        userImgDO.setContent(AppConfigConst.img_content);
        userImgDO.setReportContentType(ContentType.userImg);
        userImgDO.setReportNum(0);
        userImgDO.setIsSelfAuth(false);
        mineUser.setAvatar(SocialAppConfig.getStaticResourceUrl() + userImgDO.getSrc() + "!avatar");
        userRepository.save(mineUser);
        userImgRepository.save(userImgDO);

        SocialMineUserDetailRO socialMineUserDetailRO = SocialMineUserDetailROFactory.getMineUserDetail(mineUser);

        return socialMineUserDetailRO;
    }
}
