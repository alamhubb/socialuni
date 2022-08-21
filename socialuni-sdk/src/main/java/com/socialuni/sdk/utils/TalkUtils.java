package com.socialuni.sdk.utils;

import com.socialuni.sdk.model.DO.UniContentUnionIdDO;
import com.socialuni.sdk.model.DO.talk.SocialTalkDO;
import com.socialuni.sdk.repository.community.TalkRepository;
import com.socialuni.social.exception.SocialParamsException;
import com.socialuni.social.exception.SocialSystemException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Objects;

@Component
public class TalkUtils {
    private static TalkRepository talkRepository;

    @Resource
    public void setTalkRepository(TalkRepository talkRepository) {
        TalkUtils.talkRepository = talkRepository;
    }

    public static SocialTalkDO getNotNull(Integer talkUnionId) {
        SocialTalkDO talkDO = getAllowNull(talkUnionId);
        if (talkDO == null) {
            throw new SocialParamsException("不存在的动态内容");
        }
        return talkDO;
    }

    public static SocialTalkDO getAllowNull(Integer unionId) {
        UniContentUnionIdDO uniContentUnionIdDO = UnionIdDbUtil.getUnionDOByUnionIdNotNull(unionId);
        SocialTalkDO talkDO = talkRepository.findOneByUnionId(unionId);
        if (talkDO == null) {
            //用户写入的数据，不该出现不存在的情况
            if (Objects.equals(DevAccountUtils.getDevIdNotNull(), uniContentUnionIdDO.getFromDevId())) {
                throw new SocialSystemException("动态丢失了，请联系客服");
            }
        }
        return talkDO;
    }
}
