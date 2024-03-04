package com.socialuni.social.common.sdk.logic.domain.log;


import com.socialuni.social.user.sdk.model.FrontErrorLogVO;
import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.common.api.constant.ErrorPlatformType;
import com.socialuni.social.common.api.dao.DO.ErrorRequestLogDO;
import com.socialuni.social.common.api.dao.DO.RequestLogDO;
import com.socialuni.social.web.sdk.dao.utils.ErrorLogUtil;
import com.socialuni.social.common.sdk.utils.RequestLogUtil;
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
