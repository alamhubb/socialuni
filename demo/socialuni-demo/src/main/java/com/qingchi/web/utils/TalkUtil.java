package com.qingchi.web.utils;


import com.socialuni.social.entity.model.DO.talk.TalkDO;
import com.socialuni.social.sdk.repository.TalkRepository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class TalkUtil {
    private static TalkRepository talkRepository;

    @Resource
    public void setTalkRepository(TalkRepository talkRepository) {
        TalkUtil.talkRepository = talkRepository;
    }

    public static Integer getTalkIdByUnionId(String uid) {
        TalkDO talkDO = talkRepository.findOneBySocialuniUid(uid);
        if (talkDO != null) {
            return talkDO.getId();
        }
        return null;
    }
}
