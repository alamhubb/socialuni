package com.socialuni.social.sdk.logic.service;


import com.socialuni.social.sdk.model.QO.FrontErrorLogVO;
import com.socialuni.social.common.sdk.model.ResultRO;
import com.socialuni.social.common.sdk.constant.ErrorPlatformType;
import com.socialuni.social.web.sdk.model.ErrorRequestLogDO;
import com.socialuni.social.web.sdk.model.RequestLogDO;
import com.socialuni.social.web.sdk.utils.ErrorLogUtil;
import com.socialuni.social.web.sdk.utils.RequestLogUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SocialFrontLogDomain {
    public ResultRO<Void> sendErrorLog(FrontErrorLogVO frontErrorLogVO) {
        RequestLogDO requestLogDO = RequestLogUtil.get();
        ErrorRequestLogDO front = new ErrorRequestLogDO(requestLogDO);

        front.setFrontPage(frontErrorLogVO.getFrontPage());
        front.setInnerMsg(frontErrorLogVO.getDetail());
        front.setUri(frontErrorLogVO.getUri());
        front.setParams(frontErrorLogVO.getParams());
        front.setErrorPlatform(ErrorPlatformType.frontError);
//        front.setAppVersion(frontErrorLogVO.getAppVersion());

        ErrorLogUtil.save(front);
        return new ResultRO<>();
    }
}
