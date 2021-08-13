package com.socialuni.center.web.utils;

import com.socialuni.social.entity.model.DO.talk.TalkDO;
import com.socialuni.social.sdk.utils.TalkUtils;
import org.springframework.stereotype.Component;

@Component
public class CenterTalkUtil {
    public static TalkDO get(String talkId) {
        Integer id = UnionIdDbUtil.getTalkIdByUnionId(talkId);
        return TalkUtils.get(id);
    }
}
