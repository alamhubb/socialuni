package com.socialuni.social.sdk.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.socialuni.social.entity.model.DO.NotifyDO;
import com.socialuni.social.entity.model.DO.notify.PushMessageDO;
import com.socialuni.social.sdk.model.PushMsgDTO;
import com.socialuni.social.sdk.platform.weixin.HttpResult;
import com.socialuni.social.sdk.repository.PushMessageRepository;
import com.socialuni.social.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author qinkaiyuan
 * @date 2020-03-21 21:45
 */
@Component
@Slf4j
public class PushMessageUtils {
    private static PushMessageRepository pushMessageRepository;

    @Resource
    public void setPushMessageRepository(PushMessageRepository pushMessageRepository) {
        PushMessageUtils.pushMessageRepository = pushMessageRepository;
    }

    public static void savePushMsg(NotifyDO notify, PushMsgDTO pushMsgDTO, HttpResult result, String platform) {
        String pushMsgDTOString = null;
        String resultString = null;
        try {
            pushMsgDTOString = JsonUtils.objectMapper.writeValueAsString(pushMsgDTO);
            resultString = JsonUtils.objectMapper.writeValueAsString(result);
        } catch (JsonProcessingException e) {
            log.error("发送消息，json格式化：{},{},{}", pushMsgDTO, result, e);
        }
        PushMessageDO pushMessageDO = new PushMessageDO(notify.getId(), pushMsgDTOString, resultString, platform);
        pushMessageRepository.save(pushMessageDO);
    }
}
