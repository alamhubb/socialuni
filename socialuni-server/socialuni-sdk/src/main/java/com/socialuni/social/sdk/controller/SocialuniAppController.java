package com.socialuni.social.sdk.controller;

import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.common.api.utils.UUIDUtil;
import com.socialuni.social.common.sdk.dao.DO.SocialuniDeviceDO;
import com.socialuni.social.common.sdk.dao.facede.SocialuniRepositoryFacade;
import com.socialuni.social.community.sdk.constant.TalkTabType;
import com.socialuni.social.common.sdk.model.SocialuniGetAppInitDataQO;
import com.socialuni.social.tag.dao.DO.SocialuniCircleDO;
import com.socialuni.social.tag.logic.manage.SocialuniCircleChatManage;
import com.socialuni.social.tag.util.SocialuniCircleDOUtil;
import com.socialuni.social.common.sdk.feignAPI.SocialuniAppAPI;
import com.socialuni.social.sdk.logic.service.SocialuniAppService;
import com.socialuni.social.common.sdk.model.VO.FrontErrorLogVO;
import com.socialuni.social.common.sdk.model.VO.HomeSwiperVO;
import com.socialuni.social.common.sdk.model.RO.SocialuniTalkTabRO;
import com.socialuni.social.common.sdk.model.SocialAppLaunchDataRO;
import com.socialuni.social.tance.dev.api.SocialuniNoUseFeignAspect;
import com.socialuni.social.tance.dev.config.SocialuniAppConfig;
import com.socialuni.social.tance.dev.config.SocialuniDevConfig;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * @author qinkaiyuan
 * @date 2019-09-28 11:09
 * 前端初始化内容
 */
@RestController
@RequestMapping("socialuni/app")
public class SocialuniAppController implements SocialuniAppAPI {
    @Resource
    SocialuniAppService centerAppService;

    public ResultRO<SocialAppLaunchDataRO> getAppLaunchData() {
        return centerAppService.getAppConfig();
    }

    public ResultRO<List<HomeSwiperVO>> queryHomeSwipers() {
        return centerAppService.queryHomeSwipers();
    }

    @Resource
    SocialuniCircleChatManage socialuniCircleChatManage;

    public ResultRO<List<SocialuniTalkTabRO>> queryHomeTabs() {
        List<String> tabNames = SocialuniAppConfig.getAppConfig().getTabNames();
        List<SocialuniTalkTabRO> list = new ArrayList<>();
        for (String tabName : tabNames) {
            SocialuniTalkTabRO tabRO = new SocialuniTalkTabRO(tabName);
            //不为内置tab才为圈子
            if (!SocialuniAppConfig.innerDefaultTabNames.contains(tabName)) {
                SocialuniCircleDO socialuniCircleDO = SocialuniCircleDOUtil.getCircleEnableAllowNull(tabName);
                if (socialuniCircleDO != null) {
                    //获取groupId
//                    tabRO.setCircle(homeTabCircleRO);
                    tabRO.setType(TalkTabType.circle_type);
                }
            }
            list.add(tabRO);
        }
        return ResultRO.success(list);
    }

    public ResultRO<Void> sendErrorLog(@RequestBody FrontErrorLogVO frontErrorLogVO) {
        return centerAppService.sendErrorLog(frontErrorLogVO);
    }

    @Resource
    SocialuniAppAPI socialuniAppAPI;

    @SocialuniNoUseFeignAspect
    public ResultRO<String> getDeviceUid(@RequestBody @NotNull SocialuniGetAppInitDataQO deviceUidQO) {

        String uuid = UUIDUtil.getUUID();

        if (SocialuniDevConfig.hasCenterServer()) {
            ResultRO<String> stringResultRO = socialuniAppAPI.getDeviceUid(deviceUidQO);
            uuid = stringResultRO.getData();
        }
        SocialuniDeviceDO socialuniDeviceDO = new SocialuniDeviceDO();
        socialuniDeviceDO.setUuid(uuid);
        socialuniDeviceDO.setDevice(deviceUidQO.getDevice());
        socialuniDeviceDO.setDeviceOther(RequestUtil.getUserAgent());

        SocialuniRepositoryFacade.save(socialuniDeviceDO);
        return ResultRO.success(uuid);
    }
}
