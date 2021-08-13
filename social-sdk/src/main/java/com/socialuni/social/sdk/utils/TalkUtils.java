package com.socialuni.social.sdk.utils;

import com.socialuni.social.sdk.exception.SocialParamsException;
import com.socialuni.entity.model.DO.talk.TalkDO;
import com.socialuni.social.sdk.repository.TalkRepository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class TalkUtils {
    private static TalkRepository talkRepository;

    @Resource
    public void setTalkRepository(TalkRepository talkRepository) {
        TalkUtils.talkRepository = talkRepository;
    }

    public static TalkDO get(Integer talkId) {
        TalkDO talkDO = talkRepository.findOneById(talkId);
        if (talkDO == null){
            throw new SocialParamsException("不存在的动态内容");
        }
        return talkDO;
    }
}
