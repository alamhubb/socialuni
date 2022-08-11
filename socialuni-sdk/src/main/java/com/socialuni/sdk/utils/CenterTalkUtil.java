package com.socialuni.sdk.utils;

import com.socialuni.sdk.model.DO.talk.SocialTalkDO;
import org.springframework.stereotype.Component;

@Component
public class CenterTalkUtil {
    public static SocialTalkDO get(String talkId) {
        Integer id = UnionIdDbUtil.getTalkUnionIdByUidNotNull(talkId);
        return TalkUtils.get(id);
    }
}
