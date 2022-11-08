package com.socialuni.social.sdk.logic.domain.talk;

import com.socialuni.social.community.sdk.entity.SocialuniTalkDO;
import com.socialuni.social.sdk.dao.store.TalkQueryStore;
import com.socialuni.social.sdk.logic.factory.ListConvertUtil;
import com.socialuni.social.sdk.logic.factory.SocialTalkROFactory;
import com.socialuni.social.sdk.model.QO.community.talk.SocialUserTalkQueryQO;
import com.socialuni.social.sdk.model.RO.talk.SocialuniTalkRO;
import com.socialuni.social.user.sdk.model.DO.SocialuniUserDo;
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

    public List<SocialuniTalkRO> queryUserTalks(SocialUserTalkQueryQO queryQO, SocialuniUserDo mineUser) {
        //获取自己的user
        List<?  extends SocialuniTalkDO>  talks = talkQueryStore.queryUserTalks(queryQO, mineUser);

        return ListConvertUtil.toList(SocialTalkROFactory::getTalkRO, talks, mineUser);
    }
}
