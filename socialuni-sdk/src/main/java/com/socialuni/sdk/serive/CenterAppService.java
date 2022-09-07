package com.socialuni.sdk.serive;


import com.socialuni.sdk.constant.AppConfigConst;
import com.socialuni.sdk.constant.ViolateType;
import com.socialuni.sdk.factory.SocialHomeSwiperROFactory;
import com.socialuni.sdk.model.DO.HomeSwiperDO;
import com.socialuni.sdk.model.HomeSwiperVO;
import com.socialuni.sdk.model.QO.FrontErrorLogVO;
import com.socialuni.sdk.model.RO.app.SocialAppLaunchDataRO;
import com.socialuni.sdk.repository.HomeSwiperRepository;
import com.socialuni.sdk.service.SocialFrontLogDomain;
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
public class CenterAppService {
    @Resource
    HomeSwiperRepository homeSwiperRepository;
    @Resource
    SocialFrontLogDomain socialFrontLogDomain;

    public ResultRO<SocialAppLaunchDataRO> getAppConfig() {
        SocialAppLaunchDataRO appInitData = new SocialAppLaunchDataRO();
        appInitData.setAppConfig(AppConfigConst.appConfigMap);
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
