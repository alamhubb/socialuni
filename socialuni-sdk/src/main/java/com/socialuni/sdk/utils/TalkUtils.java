package com.socialuni.sdk.utils;

import com.socialuni.sdk.model.DO.talk.SocialTalkDO;
import com.socialuni.sdk.repository.community.TalkRepository;
import com.socialuni.social.exception.SocialParamsException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class TalkUtils {
    private static TalkRepository talkRepository;

    @Resource
    public void setTalkRepository(TalkRepository talkRepository) {
        TalkUtils.talkRepository = talkRepository;
    }

    public static SocialTalkDO get(Integer talkId) {
        SocialTalkDO talkDO = talkRepository.findOneByUnionId(talkId);
        if (talkDO == null) {
            throw new SocialParamsException("不存在的动态内容");
        }
        return talkDO;
    }
}
