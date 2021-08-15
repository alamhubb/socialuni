package com.socialuni.social.sdk.service;


import com.socialuni.social.api.model.ResultRO;
import com.socialuni.social.entity.model.DO.log.FrontErrorLogDO;
import com.socialuni.social.entity.model.DO.user.UserDO;
import com.socialuni.social.model.model.QO.FrontErrorLogVO;
import com.socialuni.social.sdk.repository.SocialFrontErrorLogRepository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

@Component
public class SocialFrontLogDomain {
    @Resource
    private SocialFrontErrorLogRepository socialFrontErrorLogRepository;

    public ResultRO<Void> sendErrorLog(FrontErrorLogVO frontErrorLogVO, UserDO mineUser) {
        FrontErrorLogDO front = new FrontErrorLogDO();
        front.setCreateTime(new Date());
        if (mineUser != null) {
            front.setUserId(mineUser.getId());
        }
        front.setDetail(frontErrorLogVO.getDetail());
        front.setUri(frontErrorLogVO.getUri());
        front.setParams(frontErrorLogVO.getParams());
        front.setErrorMsg(frontErrorLogVO.getErrorMsg());
        front.setPlatform(frontErrorLogVO.getPlatform());
        front.setProvider(frontErrorLogVO.getProvider());
        front.setAppVersion(frontErrorLogVO.getAppVersion());
        front.setDevId(frontErrorLogVO.getDevId());
        socialFrontErrorLogRepository.save(front);
        return ResultRO.success();
    }
}
