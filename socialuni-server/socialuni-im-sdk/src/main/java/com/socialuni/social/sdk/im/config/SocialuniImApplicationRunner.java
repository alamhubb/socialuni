package com.socialuni.social.sdk.im.config;

import com.socialuni.social.common.api.constant.SocialuniSystemConst;
import com.socialuni.social.sdk.im.dao.repository.SocialuniChatRepository;
import com.socialuni.social.sdk.im.enumeration.ChatType;
import com.socialuni.social.sdk.im.logic.manage.SocialuniChatManage;
//import com.socialuni.social.common.api.config.SocialuniAppConfigInterface;
import com.socialuni.social.tance.dev.config.SocialuniAppConfig;
import com.socialuni.social.tance.dev.facade.DevAccountFacade;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author qinkaiyuan
 * @date 2019-10-24 11:57
 */
@Component
@Slf4j
@Order(11001)
public class SocialuniImApplicationRunner implements ApplicationRunner {

    //devId从0开始，可修改默认0的配置，开发者从1开始，0为默认值使用的
//    @Resource
//    SocialuniAppConfigInterface socialuniAppConfigInterface;
    @Resource
    SocialuniChatRepository chatRepository;

    @Resource
    SocialuniChatManage socialuniChatManage;

    @Override
    @Async
    public void run(ApplicationArguments args) {
        List<String> groups = SocialuniAppConfig.getAppConfig().getDefaultChatGroups();
        for (String group : groups) {
            Long sysUserId = SocialuniUserUtil.getSystemUserIdNotNull();
            socialuniChatManage.getOrCreateGroupChat(DevAccountFacade.systemDevId, sysUserId, group, ChatType.system_group);
        }
    }
}
