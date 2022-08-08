package com.socialuni.center.web.utils;

import com.socialuni.center.web.model.DO.talk.SocialTalkDO;
import org.springframework.stereotype.Component;

@Component
public class CenterTalkUtil {
    public static SocialTalkDO get(String talkId) {
        Integer id = UnionIdDbUtil.getTalkIdByUnionId(talkId);
        return TalkUtils.get(id);
    }
}
