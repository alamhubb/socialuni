package com.socialuni.center.web.domain.talk;

import com.socialuni.api.model.QO.talk.CenterHomeTabTalkQueryQO;
import com.socialuni.api.model.RO.talk.CenterTalkRO;
import com.socialuni.center.web.factory.QO.SocialHomeTalkQueryQOFactory;
import com.socialuni.center.web.factory.RO.talk.CenterTalkROFactory;
import com.socialuni.center.web.utils.CenterUserUtil;
import com.socialuni.social.sdk.domain.talk.SocialHomeTalkQueryDomain;
import com.socialuni.social.entity.model.DO.user.UserDO;
import com.socialuni.social.model.model.QO.community.talk.SocialHomeTabTalkQueryQO;
import com.socialuni.social.model.model.RO.community.talk.SocialTalkRO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
@Slf4j
public class CenterHomeTalkQueryDomain {
    @Resource
    private SocialHomeTalkQueryDomain socialHomeTalkQueryDomain;

    //查询非关注tab的动态列表
    public List<CenterTalkRO> queryHomeTalks() {
        UserDO mineUser = CenterUserUtil.getMineUser();
        List<SocialTalkRO> talkRos = socialHomeTalkQueryDomain.queryHomeTalks(mineUser);
        List<CenterTalkRO> talkROS = CenterTalkROFactory.getTalkROS(talkRos, mineUser);
        return talkROS;
    }

    //查询非关注tab的动态列表
    public List<CenterTalkRO> queryHomeTabTalks(CenterHomeTabTalkQueryQO queryQO) {
        //获取当前用户
        UserDO mineUser = CenterUserUtil.getMineUser();

        SocialHomeTabTalkQueryQO socialHomeTabTalkQueryQO = SocialHomeTalkQueryQOFactory.getTalkQueryQO(queryQO);

        //转换为rolist
        List<SocialTalkRO> socialTalkROFactories = socialHomeTalkQueryDomain.queryHomeTabTalks(socialHomeTabTalkQueryQO, mineUser);

        List<CenterTalkRO> talkROS = CenterTalkROFactory.getTalkROS(socialTalkROFactories, mineUser);
        return talkROS;
    }
}