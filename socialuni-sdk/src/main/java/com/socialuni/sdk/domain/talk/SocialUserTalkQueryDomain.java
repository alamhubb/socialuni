package com.socialuni.sdk.domain.talk;

import com.socialuni.sdk.factory.ListConvertUtil;
import com.socialuni.sdk.factory.SocialTalkROFactory;
import com.socialuni.sdk.store.TalkQueryStore;
import com.socialuni.sdk.model.DO.talk.SocialTalkDO;
import com.socialuni.sdk.model.DO.user.SocialUserDO;
import com.socialuni.sdk.model.QO.community.talk.SocialUserTalkQueryQO;
import com.socialuni.sdk.model.RO.community.talk.SocialTalkRO;
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
        List<SocialTalkDO> talks = talkQueryStore.queryUserTalks(queryQO, mineUser);

        return ListConvertUtil.toList(SocialTalkROFactory::getTalkRO, talks, mineUser);
    }
}
