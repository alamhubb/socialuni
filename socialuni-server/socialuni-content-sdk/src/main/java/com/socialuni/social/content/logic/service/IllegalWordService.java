package com.socialuni.social.content.logic.service;


import com.socialuni.social.common.sdk.constant.ErrorMsg;
import com.socialuni.social.content.dao.store.AppConfigStatic;
import com.socialuni.social.content.dao.DO.IllegalWordDO;
import com.socialuni.social.content.utils.TextContentUtil;
import com.socialuni.social.common.api.exception.exception.SocialBusinessException;
import com.socialuni.social.content.dao.repository.IllegalWordRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class IllegalWordService {

    @Resource
    IllegalWordRepository illegalWordRepository;

    public void checkHasIllegals(String content) {
        if (StringUtils.isEmpty(content)) {
            return;
        }
        content = TextContentUtil.clearAllEmptyAndSpecialChart(content);

        List<IllegalWordDO> illegals = AppConfigStatic.getIllegals();

        List<IllegalWordDO> triggerWords = new ArrayList<>();

        StringBuilder words = new StringBuilder();

        //如果有触发违规关键词
        for (IllegalWordDO illegal : illegals) {
            String wordText = illegal.getWord();
            //关键词不为空，且包含
            if (StringUtils.isNotEmpty(wordText) && StringUtils.containsIgnoreCase(content, wordText)) {
                illegal.setTriggerCount(illegal.getTriggerCount() + 1);
                illegal.setUpdateTime(new Date());
                words.append(illegal.getWord()).append("，");
                triggerWords.add(illegal);
            }
        }
        //保存触发的，更新他们的次数
        if (triggerWords.size() > 0) {
            illegalWordRepository.saveAll(triggerWords);
            String wordMsg = words.substring(0, words.length() - 1);
            String errorMsg = MessageFormat.format(ErrorMsg.illegalWordMsg, wordMsg);
            throw new SocialBusinessException(errorMsg);
        }
    }
}
