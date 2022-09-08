package com.socialuni.sdk.logic.domain.talk;

import com.socialuni.sdk.factory.ListConvertUtil;
import com.socialuni.sdk.factory.SocialTalkROFactory;
import com.socialuni.sdk.dao.store.TalkQueryStore;
import com.socialuni.sdk.dao.DO.talk.SocialTalkDO;
import com.socialuni.sdk.dao.DO.user.SocialuniUserDO;
import com.socialuni.sdk.model.QO.community.talk.SocialUserTalkQueryQO;
import com.socialuni.sdk.model.RO.talk.SocialuniTalkRO;
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

    public List<SocialuniTalkRO> queryUserTalks(SocialUserTalkQueryQO queryQO, SocialuniUserDO mineUser) {
        //获取自己的user
        List<SocialTalkDO> talks = talkQueryStore.queryUserTalks(queryQO, mineUser);

        return ListConvertUtil.toList(SocialTalkROFactory::getTalkRO, talks, mineUser);
    }
}
