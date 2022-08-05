package com.socialuni.center.web.domain.talk;

import com.socialuni.center.web.factory.ListConvertUtil;
import com.socialuni.center.web.factory.SocialTalkROFactory;
import com.socialuni.center.web.store.TalkQueryStore;
import com.socialuni.center.web.model.DO.talk.TalkDO;
import com.socialuni.center.web.model.DO.user.SocialUserDO;
import com.socialuni.center.web.model.QO.community.talk.SocialUserTalkQueryQO;
import com.socialuni.center.web.model.RO.community.talk.SocialTalkRO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author qinkaiyuan
 * @date 2020-05-16 20:11
 */
@Service
public class SocialUserTalkQueryDomain {
    @Resource
    TalkQueryStore talkQueryStore;

    public List<SocialTalkRO> queryUserTalks(SocialUserTalkQueryQO queryQO, SocialUserDO mineUser) {
        //获取自己的user
        List<TalkDO> talks = talkQueryStore.queryUserTalks(queryQO, mineUser);

        return ListConvertUtil.toList(SocialTalkROFactory::getTalkRO, talks, mineUser);
    }
}
