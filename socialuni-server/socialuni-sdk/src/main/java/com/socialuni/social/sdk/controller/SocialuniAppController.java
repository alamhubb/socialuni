package com.socialuni.social.sdk.controller;

import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.common.sdk.constant.SocialuniConst;
import com.socialuni.social.community.sdk.entity.SocialuniCircleChatDO;
import com.socialuni.social.community.sdk.entity.SocialuniCircleDO;
import com.socialuni.social.community.sdk.model.SocialuniTalkTabCircleRO;
import com.socialuni.social.community.sdk.repository.SocialuniCircleChatRepository;
import com.socialuni.social.sdk.constant.socialuni.TalkTabType;
import com.socialuni.social.sdk.dao.utils.SocialuniCircleDOUtil;
import com.socialuni.social.sdk.feignAPI.SocialuniAppAPI;
import com.socialuni.social.sdk.logic.service.SocialuniAppService;
import com.socialuni.social.tance.sdk.config.SocialuniAppType;
import com.socialuni.social.user.sdk.model.FrontErrorLogVO;
import com.socialuni.social.community.sdk.model.HomeSwiperVO;
import com.socialuni.social.community.sdk.model.SocialuniTalkTabRO;
import com.socialuni.social.user.sdk.model.SocialAppLaunchDataRO;
import com.socialuni.social.tance.sdk.config.SocialuniAppConfig;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
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
    @Resource
    SocialuniCircleChatRepository socialuniCircleChatRepository;

    public ResultRO<SocialAppLaunchDataRO> getAppLaunchData() {
        return centerAppService.getAppConfig();
    }

    public ResultRO<List<HomeSwiperVO>> queryHomeSwipers() {
        return centerAppService.queryHomeSwipers();
    }


    public ResultRO<List<SocialuniTalkTabRO>> queryHomeTabs() {
        List<String> tabNames = SocialuniAppConfig.getAppConfig().getTabNames();
        List<SocialuniTalkTabRO> list = new ArrayList<>();
        for (String tabName : tabNames) {
            SocialuniTalkTabRO tabRO = new SocialuniTalkTabRO(tabName);
            //不为内置tab才为圈子
            if (!SocialuniAppType.getDefaultTypeAppConfig().getTabNames().contains(tabName)){
                SocialuniCircleDO socialuniCircleDO = SocialuniCircleDOUtil.getCircleEnableAllowNull(tabName);
                if (socialuniCircleDO != null) {
                    SocialuniTalkTabCircleRO homeTabCircleRO = new SocialuniTalkTabCircleRO(socialuniCircleDO);
                    SocialuniCircleChatDO socialuniCircleChatDO = socialuniCircleChatRepository.findFirstByDevIdAndCircleName(SocialuniConst.centerDevId, tabName);
                    if (socialuniCircleChatDO != null) {
                        homeTabCircleRO.setGroupChatId(socialuniCircleChatDO.getGroupChatId());
                    }
                    tabRO.setCircle(homeTabCircleRO);
                    tabRO.setType(TalkTabType.circle);
                }
            }
            list.add(tabRO);
        }
        return ResultRO.success(list);
    }

    public ResultRO<Void> sendErrorLog(@RequestBody FrontErrorLogVO frontErrorLogVO) {
        return centerAppService.sendErrorLog(frontErrorLogVO);
    }
}
