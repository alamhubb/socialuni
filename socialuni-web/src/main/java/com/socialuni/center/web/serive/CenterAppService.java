package com.socialuni.center.web.serive;


import com.socialuni.center.web.utils.DevAccountUtils;
import com.socialuni.social.sdk.constant.AppConfigConst;
import com.socialuni.constant.StatusConst;
import com.socialuni.social.sdk.constant.ViolateType;
import com.socialuni.social.sdk.factory.SocialHomeSwiperROFactory;
import com.socialuni.entity.model.DO.HomeSwiperDO;
import com.socialuni.social.sdk.repository.HomeSwiperRepository;
import com.socialuni.social.model.model.HomeSwiperVO;
import com.socialuni.social.api.model.ResultRO;
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
public class CenterAppService {
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
        List<HomeSwiperDO> homeSwiperDOS = homeSwiperRepository.findAllByStatusAndDevIdOrderByTopLevelAscIdDesc(StatusConst.enable, DevAccountUtils.getDevId());
        List<HomeSwiperVO> homeSwiperVOS = SocialHomeSwiperROFactory.toVOS(homeSwiperDOS);
        return new ResultRO<>(homeSwiperVOS);
    }
}
