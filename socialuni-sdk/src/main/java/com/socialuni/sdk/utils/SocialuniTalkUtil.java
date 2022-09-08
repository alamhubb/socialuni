package com.socialuni.sdk.utils;

import com.socialuni.sdk.dao.DO.talk.SocialTalkDO;
import org.springframework.stereotype.Component;

@Component
public class SocialuniTalkUtil {
    public static SocialTalkDO get(String talkId) {
        Integer id = UnionIdDbUtil.getUnionIdByUidNotNull(talkId);
        return TalkUtils.getNotNull(id);
    }
}
