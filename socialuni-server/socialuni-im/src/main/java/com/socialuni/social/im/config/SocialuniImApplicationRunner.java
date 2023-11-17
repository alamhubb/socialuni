package com.socialuni.social.im.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.socialuni.social.im.dao.DO.SocialuniChatDO;
import com.socialuni.social.im.dao.repository.SocialuniChatRepository;
import com.socialuni.social.im.enumeration.ChatType;
import com.socialuni.social.im.logic.foctory.SocialuniChatDOFactory;
import com.socialuni.social.im.logic.manage.SocialuniChatManage;
import com.socialuni.social.tance.sdk.config.SocialuniAppConfigInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
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
public class SocialuniImApplicationRunner implements ApplicationRunner {

    //devId从0开始，可修改默认0的配置，开发者从1开始，0为默认值使用的
    @Resource
    SocialuniAppConfigInterface socialuniAppConfigInterface;
    @Resource
    SocialuniChatRepository chatRepository;

    @Resource
    SocialuniChatManage socialuniChatManage;

    @Override
    @Async
    public void run(ApplicationArguments args) {
        List<String> groups = socialuniAppConfigInterface.getAppConfig().getDefaultChatGroups();
        for (String group : groups) {
            socialuniChatManage.getOrCreateChat(group);
        }
    }
}
