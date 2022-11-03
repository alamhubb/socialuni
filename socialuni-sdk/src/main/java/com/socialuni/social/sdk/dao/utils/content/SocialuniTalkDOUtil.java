package com.socialuni.social.sdk.dao.utils.content;

import com.socialuni.social.tance.sdk.model.SocialuniUnionIdModler;
import com.socialuni.social.community.sdk.model.SocialuniTalkModel;
import com.socialuni.social.community.sdk.api.TalkInterface;
import com.socialuni.social.tance.sdk.facade.DevAccountFacade;
import com.socialuni.social.sdk.facade.SocialuniUnionIdFacede;
import com.socialuni.social.common.sdk.exception.exception.SocialParamsException;
import com.socialuni.social.common.sdk.exception.exception.SocialSystemException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Objects;

@Component
public class SocialuniTalkDOUtil {

    private static TalkInterface talkApi;
    private static SocialuniTalkDORedis socialuniTalkDORedis;

    @Resource
    public void setSocialuniTalkDORediss(SocialuniTalkDORedis socialuniTalkDORediss) {
        SocialuniTalkDOUtil.socialuniTalkDORedis = socialuniTalkDORediss;
    }

    @Resource
    public void setTalkRepository(TalkInterface talkApi) {
        SocialuniTalkDOUtil.talkApi = talkApi;
    }

    public static SocialuniTalkModel save(SocialuniTalkModel talkDO) {
        talkDO.setUpdateTime(new Date());
        return socialuniTalkDORedis.save(talkDO);
    }

    public static SocialuniTalkModel getTalkNotNull(Integer talkUnionId) {
        SocialuniTalkModel talkDO = getAllowNull(talkUnionId);
        if (talkDO == null) {
            throw new SocialParamsException("不存在的动态内容");
        }
        return talkDO;
    }

    //逻辑没问题，第一行是校验
    public static SocialuniTalkModel getAllowNull(Integer unionId) {
        SocialuniUnionIdModler uniContentUnionIdDO = SocialuniUnionIdFacede.getUnionDOByUnionIdNotNull(unionId);
        SocialuniTalkModel talkDO = talkApi.findOneByUnionId(unionId);
        if (talkDO == null) {
            //只有开发者相等才进入， 用户写入的数据，不该出现不存在的情况
            if (Objects.equals(DevAccountFacade.getDevIdNotNull(), uniContentUnionIdDO.getFromDevId())) {
                throw new SocialSystemException("动态丢失了，请联系客服");
            }
        }
        return talkDO;
    }
}
