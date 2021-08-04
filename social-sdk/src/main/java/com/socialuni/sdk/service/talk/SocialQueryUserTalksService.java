package com.socialuni.sdk.service.talk;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author qinkaiyuan
 * @date 2020-05-16 20:11
 */
@Service
@Slf4j
public class SocialQueryUserTalksService {

    /*

    public ResultRO<List<SocialTalkRO>> queryUserTalks(SocialUserTalkQueryQO queryVO) {
        SocialUserAccountDO accountDO = SocialUserAccountUtil.getUserSocialAccount(Integer.valueOf(queryVO.getUserId()));
        String centerUserId = accountDO.getUnionId();

        SocialUserTalkQueryQO socialUserTalkQueryQO = new SocialUserTalkQueryQO(centerUserId, queryVO.getTalkIds());

        return talkAPI.queryUserTalks(socialUserTalkQueryQO);
    }*/
}
