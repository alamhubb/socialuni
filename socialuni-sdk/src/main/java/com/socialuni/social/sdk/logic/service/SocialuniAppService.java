package com.socialuni.social.sdk.logic.service;


import com.socialuni.social.sdk.config.SocialuniAppConfig;
import com.socialuni.social.sdk.constant.ViolateType;
import com.socialuni.social.sdk.logic.factory.SocialHomeSwiperROFactory;
import com.socialuni.social.sdk.dao.DO.HomeSwiperDO;
import com.socialuni.social.sdk.model.RO.app.HomeSwiperVO;
import com.socialuni.social.sdk.model.QO.FrontErrorLogVO;
import com.socialuni.social.sdk.model.RO.app.SocialAppLaunchDataRO;
import com.socialuni.social.sdk.dao.repository.HomeSwiperRepository;
import com.socialuni.social.tance.sdk.facade.DevAccountFacade;
import com.socialuni.social.common.model.ResultRO;
import com.socialuni.social.common.enumeration.CommonStatus;
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
        appInitData.setAppConfig(SocialuniAppConfig.getAppConfig());
        appInitData.setAppMoreConfig(SocialuniAppConfig.getAppMoreConfig());
//        appInitData.setOnlineUsersCount(WebsocketServer.getOnlineCount());
        appInitData.setReportTypes(ViolateType.frontShowReportTypes);
        return new ResultRO<>(appInitData);
    }

    public ResultRO<List<HomeSwiperVO>> queryHomeSwipers() {
        //homeSwipers
        List<HomeSwiperDO> homeSwiperDOS = homeSwiperRepository.findAllByStatusAndDevIdOrderByTopLevelAscIdDesc(CommonStatus.enable, DevAccountFacade.getDevIdNotNull());
        List<HomeSwiperVO> homeSwiperVOS = SocialHomeSwiperROFactory.toVOS(homeSwiperDOS);
        return new ResultRO<>(homeSwiperVOS);
    }

    public ResultRO<Void> sendErrorLog(FrontErrorLogVO frontErrorLogVO) {
        return socialFrontLogDomain.sendErrorLog(frontErrorLogVO);
    }
}