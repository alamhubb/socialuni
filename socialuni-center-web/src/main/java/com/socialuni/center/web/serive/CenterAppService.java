package com.socialuni.center.web.serive;


import com.socialuni.social.api.model.ResultRO;
import com.socialuni.social.constant.CommonStatus;
import com.socialuni.center.web.model.DO.HomeSwiperDO;
import com.socialuni.center.web.model.HomeSwiperVO;
import com.socialuni.center.web.model.QO.FrontErrorLogVO;
import com.socialuni.center.web.model.RO.app.SocialAppLaunchDataRO;
import com.socialuni.center.web.constant.AppConfigConst;
import com.socialuni.center.web.constant.ViolateType;
import com.socialuni.center.web.factory.SocialHomeSwiperROFactory;
import com.socialuni.center.web.repository.HomeSwiperRepository;
import com.socialuni.center.web.service.SocialFrontLogDomain;
import com.socialuni.center.web.utils.DevAccountUtils;
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
