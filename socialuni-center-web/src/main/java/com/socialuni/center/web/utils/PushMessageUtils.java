package com.socialuni.center.web.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.socialuni.center.web.model.PushMsgDTO;
import com.socialuni.center.web.platform.weixin.HttpResult;
import com.socialuni.center.web.model.DO.NotifyDO;
import com.socialuni.center.web.model.DO.notify.PushMessageDO;
import com.socialuni.center.web.repository.PushMessageRepository;
import com.socialuni.social.web.sdk.utils.JsonUtil;
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
            pushMsgDTOString = JsonUtil.objectMapper.writeValueAsString(pushMsgDTO);
            resultString = JsonUtil.objectMapper.writeValueAsString(result);
        } catch (JsonProcessingException e) {
            log.error("发送消息，json格式化：{},{},{}", pushMsgDTO, result, e);
        }
        PushMessageDO pushMessageDO = new PushMessageDO(notify.getId(), pushMsgDTOString, resultString, platform);
        pushMessageRepository.save(pushMessageDO);
    }
}
