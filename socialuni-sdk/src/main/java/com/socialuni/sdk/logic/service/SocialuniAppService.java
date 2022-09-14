package com.socialuni.sdk.logic.service;


import com.socialuni.sdk.config.SocialuniAppConfig;
import com.socialuni.sdk.constant.AppConfigConst;
import com.socialuni.sdk.constant.ViolateType;
import com.socialuni.sdk.logic.factory.SocialHomeSwiperROFactory;
import com.socialuni.sdk.dao.DO.HomeSwiperDO;
import com.socialuni.sdk.model.RO.app.HomeSwiperVO;
import com.socialuni.sdk.model.QO.FrontErrorLogVO;
import com.socialuni.sdk.model.RO.app.SocialAppLaunchDataRO;
import com.socialuni.sdk.dao.repository.HomeSwiperRepository;
import com.socialuni.sdk.utils.DevAccountUtils;
import com.socialuni.social.web.sdk.model.ResultRO;
import com.socialuni.sdk.constant.socialuni.CommonStatus;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author qinkaiyuan
 * @date 2019-09-28 11:09
 * 前端初始化内容
 */
@Service
public class SocialuniAppService {
    @Resource
    HomeSwiperRepository homeSwiperRepository;
    @Resource
    SocialFrontLogDomain socialFrontLogDomain;

    public ResultRO<SocialAppLaunchDataRO> getAppConfig() {
        SocialAppLaunchDataRO appInitData = new SocialAppLaunchDataRO();
        appInitData.setAppConfig(SocialuniAppConfig.appMoreConfig);
//        appInitData.setOnlineUsersCount(WebsocketServer.getOnlineCount());
        appInitData.setReportTypes(ViolateType.frontShowReportTypes);
        return new ResultRO<>(appInitData);
    }

    public ResultRO<List<HomeSwiperVO>> queryHomeSwipers() {
        //homeSwipers
        List<HomeSwiperDO> homeSwiperDOS = homeSwiperRepository.findAllByStatusAndDevIdOrderByTopLevelAscIdDesc(CommonStatus.enable, DevAccountUtils.getDevIdNotNull());
        List<HomeSwiperVO> homeSwiperVOS = SocialHomeSwiperROFactory.toVOS(homeSwiperDOS);
        return new ResultRO<>(homeSwiperVOS);
    }

    public ResultRO<Void> sendErrorLog(FrontErrorLogVO frontErrorLogVO) {
        return socialFrontLogDomain.sendErrorLog(frontErrorLogVO);
    }
}
