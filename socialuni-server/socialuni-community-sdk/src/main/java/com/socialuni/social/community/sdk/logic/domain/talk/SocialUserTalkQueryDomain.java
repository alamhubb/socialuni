package com.socialuni.social.community.sdk.logic.domain.talk;

import com.socialuni.social.community.sdk.dao.DO.SocialuniTalkDO;
import com.socialuni.social.community.sdk.dao.store.TalkQueryStore;
import com.socialuni.social.common.sdk.utils.ListConvertUtil;
import com.socialuni.social.community.sdk.logic.factory.SocialTalkROFactory;
import com.socialuni.social.community.sdk.model.QO.talk.SocialUserTalkQueryBO;
import com.socialuni.social.community.sdk.model.RO.talk.SocialuniTalkRO;
import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
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

    public List<SocialuniTalkRO> queryUserTalks(SocialUserTalkQueryBO queryQO, SocialuniUserDo mineUser) {
        //获取自己的user
        List<?  extends SocialuniTalkDO>  talks = talkQueryStore.queryUserTalks(queryQO, mineUser);

        return ListConvertUtil.toList(SocialTalkROFactory::getTalkRO, talks, mineUser);
    }
}
