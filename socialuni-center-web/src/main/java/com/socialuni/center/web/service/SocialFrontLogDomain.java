package com.socialuni.center.web.service;


import com.socialuni.social.api.model.ResultRO;
import com.socialuni.social.model.model.QO.FrontErrorLogVO;
import com.socialuni.social.web.sdk.constant.ErrorPlatformType;
import com.socialuni.social.web.sdk.model.ErrorRequestLogDO;
import com.socialuni.social.web.sdk.model.RequestLogDO;
import com.socialuni.social.web.sdk.utils.ErrorLogUtil;
import com.socialuni.social.web.sdk.utils.RequestLogUtil;
import org.springframework.stereotype.Component;

@Component
public class SocialFrontLogDomain {
    public ResultRO<Void> sendErrorLog(FrontErrorLogVO frontErrorLogVO) {

        RequestLogDO requestLogDO = RequestLogUtil.get();
        ErrorRequestLogDO front = new ErrorRequestLogDO(requestLogDO);

        front.setInnerMsg(frontErrorLogVO.getDetail());
        front.setUri(frontErrorLogVO.getUri());
        front.setParams(frontErrorLogVO.getParams());
        front.setErrorPlatform(ErrorPlatformType.frontError);
//        front.setAppVersion(frontErrorLogVO.getAppVersion());

        ErrorLogUtil.save(front);
        return new ResultRO<>();
    }
}
