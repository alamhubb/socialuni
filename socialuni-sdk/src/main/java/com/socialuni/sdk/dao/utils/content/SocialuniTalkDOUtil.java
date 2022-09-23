package com.socialuni.sdk.dao.utils.content;

import com.socialuni.sdk.dao.DO.SocialuniUnionIdDO;
import com.socialuni.sdk.dao.DO.community.talk.SocialuniTalkDO;
import com.socialuni.sdk.dao.repository.community.TalkRepository;
import com.socialuni.sdk.utils.DevAccountUtils;
import com.socialuni.sdk.utils.SocialuniUnionIdUtil;
import com.socialuni.social.common.exception.exception.SocialParamsException;
import com.socialuni.social.common.exception.exception.SocialSystemException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Objects;

@Component
public class SocialuniTalkDOUtil {

    private static TalkRepository talkRepository;
    private static SocialuniTalkDORedis socialuniTalkDORedis;

    @Resource
    public void setSocialuniTalkDORediss(SocialuniTalkDORedis socialuniTalkDORediss) {
        SocialuniTalkDOUtil.socialuniTalkDORedis = socialuniTalkDORediss;
    }

    @Resource
    public void setTalkRepository(TalkRepository talkRepository) {
        SocialuniTalkDOUtil.talkRepository = talkRepository;
    }

    public static SocialuniTalkDO save(SocialuniTalkDO talkDO) {
        talkDO.setUpdateTime(new Date());
        return socialuniTalkDORedis.save(talkDO);
    }

    public static SocialuniTalkDO getTalkNotNull(Integer talkUnionId) {
        SocialuniTalkDO talkDO = getAllowNull(talkUnionId);
        if (talkDO == null) {
            throw new SocialParamsException("不存在的动态内容");
        }
        return talkDO;
    }

    //逻辑没问题，第一行是校验
    public static SocialuniTalkDO getAllowNull(Integer unionId) {
        SocialuniUnionIdDO uniContentUnionIdDO = SocialuniUnionIdUtil.getUnionDOByUnionIdNotNull(unionId);
        SocialuniTalkDO talkDO = talkRepository.findOneByUnionId(unionId);
        if (talkDO == null) {
            //只有开发者相等才进入， 用户写入的数据，不该出现不存在的情况
            if (Objects.equals(DevAccountUtils.getDevIdNotNull(), uniContentUnionIdDO.getFromDevId())) {
                throw new SocialSystemException("动态丢失了，请联系客服");
            }
        }
        return talkDO;
    }
}
