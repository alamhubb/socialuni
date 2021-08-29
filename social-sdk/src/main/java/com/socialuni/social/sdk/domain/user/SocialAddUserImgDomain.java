package com.socialuni.social.sdk.domain.user;

import com.socialuni.social.constant.ContentStatus;
import com.socialuni.social.constant.ContentType;
import com.socialuni.social.entity.model.DO.user.UserDO;
import com.socialuni.social.entity.model.DO.user.UserImgDO;
import com.socialuni.social.exception.SocialParamsException;
import com.socialuni.social.model.model.QO.user.SocialUserImgAddQO;
import com.socialuni.social.model.model.RO.user.SocialMineUserDetailRO;
import com.socialuni.social.sdk.config.SocialAppConfig;
import com.socialuni.social.sdk.constant.AppConfigConst;
import com.socialuni.social.sdk.factory.user.base.SocialMineUserDetailROFactory;
import com.socialuni.social.sdk.repository.UserImgRepository;
import com.socialuni.social.sdk.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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
        //为空则异常
        if (StringUtils.isEmpty(SocialAppConfig.staticResourceUrl)) {
            throw new SocialParamsException("图片路径错误异常");
        }
        mineUser.setAvatar(SocialAppConfig.staticResourceUrl + "/" + userImgDO.getSrc() + "!avatar");
        userRepository.save(mineUser);
        userImgRepository.save(userImgDO);

        SocialMineUserDetailRO socialMineUserDetailRO = SocialMineUserDetailROFactory.getMineUserDetail(mineUser);

        return socialMineUserDetailRO;
    }
}
