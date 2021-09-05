package com.qingchi.web.domain.talk;

import com.socialuni.api.feignAPI.SocialuniTalkAPI;
import com.socialuni.api.model.RO.talk.CenterTalkRO;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.social.constant.GenderType;
import com.socialuni.social.entity.model.DO.user.UserDO;
import com.socialuni.social.model.model.QO.community.talk.SocialTalkPostQO;
import com.socialuni.social.sdk.domain.talk.SocialTalkPostDomain;
import com.socialuni.social.sdk.utils.SocialUserUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Slf4j
@Component
public class TalkPostDomain {
    @Resource
    SocialTalkPostDomain socialTalkPostDomain;
    @Resource
    SocialuniTalkAPI socialuniTalkAPI;

    public void postTalk(SocialTalkPostQO talkPostQO) {
        UserDO mineUser = SocialUserUtil.getMineUserAllowNull();

        //兼容旧版本
        if (StringUtils.isEmpty(talkPostQO.getVisibleGender())) {
            talkPostQO.setVisibleGender(GenderType.all);
        }

        //在社交联盟中添加动态
        ResultRO<CenterTalkRO> centerTalkROResultRO = socialuniTalkAPI.postTalk(talkPostQO);
        CenterTalkRO centerTalkRO = centerTalkROResultRO.getData();
        //设置社交联盟中的唯一id，便于以后确认此动态是否属于本应用
        talkPostQO.setSocialuniUid(centerTalkRO.getId());
        socialTalkPostDomain.postTalk(mineUser, talkPostQO);
    }
}
