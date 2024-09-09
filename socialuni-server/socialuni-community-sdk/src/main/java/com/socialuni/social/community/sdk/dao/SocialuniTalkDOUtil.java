package com.socialuni.social.community.sdk.dao;

import com.qingchi.qing.common.exception.base.exception.SocialParamsException;
import com.socialuni.social.common.api.exception.exception.SocialSystemException;
import com.socialuni.social.community.sdk.dao.DO.SocialuniTalkDO;
import com.socialuni.social.community.sdk.repository.TalkRepository;
import com.socialuni.social.tance.dev.facade.SocialuniUnionIdFacede;
import com.socialuni.social.tance.dev.facade.DevAccountFacade;
import com.socialuni.social.tance.dev.entity.SocialuniUnionIdDo;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Objects;

@Component
public class SocialuniTalkDOUtil {

    private static TalkRepository talkApi;
    private static SocialuniTalkDORedis socialuniTalkDORedis;

    @Resource
    public void setSocialuniTalkDORediss(SocialuniTalkDORedis socialuniTalkDORediss) {
        SocialuniTalkDOUtil.socialuniTalkDORedis = socialuniTalkDORediss;
    }

    @Resource
    public void setTalkRepository(TalkRepository talkApi) {
        SocialuniTalkDOUtil.talkApi = talkApi;
    }

    public static SocialuniTalkDO save(SocialuniTalkDO talkDO) {
        talkDO.setUpdateTime(new Date());
        return socialuniTalkDORedis.save(talkDO);
    }

    public static SocialuniTalkDO getTalkNotNull(Long talkUnionId) {
        SocialuniTalkDO talkDO = getAllowNull(talkUnionId);
        if (talkDO == null) {
            throw new SocialParamsException("不存在的动态内容");
        }
        return talkDO;
    }

    //逻辑没问题，第一行是校验
    public static SocialuniTalkDO getAllowNull(Long unionId) {
        SocialuniUnionIdDo uniContentUnionIdDO = SocialuniUnionIdFacede.getUnionDOByUnionIdNotNull(unionId);
        SocialuniTalkDO talkDO = talkApi.findOneByUnionId(unionId);
        if (talkDO == null) {
            //只有开发者相等才进入， 用户写入的数据，不该出现不存在的情况
            if (Objects.equals(DevAccountFacade.getDevIdNullElseCenterDevId(), uniContentUnionIdDO.getFromDevId())) {
                throw new SocialSystemException("动态丢失了，请联系客服");
            }
        }
        return talkDO;
    }
}
