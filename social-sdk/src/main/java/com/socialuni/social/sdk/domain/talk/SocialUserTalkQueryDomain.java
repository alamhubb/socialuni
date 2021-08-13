package com.socialuni.social.sdk.domain.talk;

import com.socialuni.social.sdk.factory.ListConvertUtil;
import com.socialuni.social.sdk.factory.SocialTalkROFactory;
import com.socialuni.social.entity.model.DO.talk.TalkDO;
import com.socialuni.social.entity.model.DO.user.UserDO;
import com.socialuni.social.sdk.store.TalkQueryStore;
import com.socialuni.social.model.model.QO.community.talk.SocialUserTalkQueryQO;
import com.socialuni.social.model.model.RO.community.talk.SocialTalkRO;
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

    public List<SocialTalkRO> queryUserTalks(SocialUserTalkQueryQO queryQO, UserDO mineUser) {
        //获取自己的user
        List<TalkDO> talks = talkQueryStore.queryUserTalks(queryQO, mineUser);

        return ListConvertUtil.toList(SocialTalkROFactory::getTalkRO, talks, mineUser);
    }
}
