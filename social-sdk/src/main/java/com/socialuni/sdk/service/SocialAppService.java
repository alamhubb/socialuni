package com.socialuni.sdk.service;


import com.socialuni.sdk.constant.AppConfigConst;
import com.socialuni.sdk.constant.StatusConst;
import com.socialuni.sdk.constant.ViolateType;
import com.socialuni.sdk.factory.SocialHomeSwiperROFactory;
import com.socialuni.sdk.model.DO.HomeSwiperDO;
import com.socialuni.sdk.repository.HomeSwiperRepository;
import com.socialuni.social.model.model.HomeSwiperVO;
import com.socialuni.social.model.model.RO.ResultRO;
import com.socialuni.social.model.model.RO.app.SocialAppLaunchDataRO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author qinkaiyuan
 * @date 2019-09-28 11:09
 * 前端初始化内容
 */
@Service
public class SocialAppService {
    @Resource
    HomeSwiperRepository homeSwiperRepository;

    public ResultRO<SocialAppLaunchDataRO> getAppConfig() {
        SocialAppLaunchDataRO appInitData = new SocialAppLaunchDataRO();
        appInitData.setAppConfig(AppConfigConst.appConfigMap);
//        appInitData.setOnlineUsersCount(WebsocketServer.getOnlineCount());
        appInitData.setReportTypes(ViolateType.frontShowReportTypes);
        return new ResultRO<>(appInitData);
    }

    public ResultRO<List<HomeSwiperVO>> queryHomeSwipers() {
        //homeSwipers
        List<HomeSwiperDO> homeSwiperDOS = homeSwiperRepository.findAllByStatusOrderByTopLevelAscIdDesc(StatusConst.enable);
        List<HomeSwiperVO> homeSwiperVOS = SocialHomeSwiperROFactory.toVOS(homeSwiperDOS);
        return new ResultRO<>(homeSwiperVOS);
    }
}
