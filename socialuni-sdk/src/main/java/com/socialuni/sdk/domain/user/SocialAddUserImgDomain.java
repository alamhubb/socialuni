package com.socialuni.sdk.domain.user;

import com.socialuni.sdk.constant.AppConfigConst;
import com.socialuni.sdk.factory.user.base.SocialMineUserDetailROFactory;
import com.socialuni.sdk.config.SocialAppConfig;
import com.socialuni.sdk.model.DO.user.SocialUserDO;
import com.socialuni.sdk.model.DO.user.SocialUserImgDO;
import com.socialuni.sdk.model.QO.user.SocialUserImgAddQO;
import com.socialuni.sdk.model.RO.user.SocialMineUserDetailRO;
import com.socialuni.social.constant.ContentStatus;
import com.socialuni.social.constant.ContentType;
import com.socialuni.sdk.repository.UserImgRepository;
import com.socialuni.sdk.repository.UserRepository;
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

    public SocialMineUserDetailRO addUserImg(SocialUserImgAddQO socialUserImgAddQO, SocialUserDO mineUser) {

        SocialUserImgDO userImgDO = new SocialUserImgDO();
        userImgDO.setSrc(socialUserImgAddQO.getSrc());
        userImgDO.setAspectRatio(socialUserImgAddQO.getAspectRatio());
        userImgDO.setQuality(socialUserImgAddQO.getQuality());
        userImgDO.setSize(socialUserImgAddQO.getSize());
        userImgDO.setUserId(mineUser.getUnionId());
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
