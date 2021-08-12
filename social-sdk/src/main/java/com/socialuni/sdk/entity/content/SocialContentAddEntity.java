package com.socialuni.sdk.entity.content;

import com.socialuni.sdk.constant.ErrorMsg;
import com.socialuni.sdk.constant.config.AppConfigStatic;
import com.socialuni.sdk.constant.status.UserStatus;
import com.socialuni.sdk.exception.SocialBusinessException;
import com.socialuni.sdk.exception.SocialParamsException;
import com.socialuni.entity.model.DO.keywords.IllegalWordDO;
import com.socialuni.entity.model.DO.user.UserDO;
import com.socialuni.sdk.platform.weixin.HttpResult;
import com.socialuni.sdk.repository.IllegalWordRepository;
import com.socialuni.sdk.utils.QQUtil;
import com.socialuni.sdk.utils.SocialUserUtil;
import com.socialuni.sdk.utils.WxUtil;
import com.socialuni.social.model.model.QO.ContentAddQO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
@Slf4j
public class SocialContentAddEntity {
    @Resource
    IllegalWordRepository illegalWordRepository;

    public void paramsValidate(UserDO mineUser, ContentAddQO addQO) {
        String content = addQO.getContent();

        String userPhoneNum = SocialUserUtil.getUserPhoneNum(mineUser.getId());

        //如果不为系统管理员，只有管理员才能评论置顶内容
        //未绑定手机号，不能发表动态，正常用户应该无法访问，应为突破了内容，或者逻辑有问题
        if (StringUtils.isEmpty(userPhoneNum)) {
            throw new SocialParamsException(ErrorMsg.bindPhoneNumCan);
        }
        //如果用户状态不为可用
        if (!UserStatus.enable.equals(mineUser.getStatus())) {
            throw new SocialBusinessException(ErrorMsg.userMaybeViolation);
        }

        //不为空才进行校验
        if (StringUtils.isNotEmpty(content)) {
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

            HttpResult wxResult = WxUtil.checkContentWxSec(content);
            if (wxResult.hasError()) {
                throw new SocialBusinessException(ErrorMsg.CHECK_VIOLATION_ERR_MSG);
            }
            HttpResult qqResult = QQUtil.checkContentQQSec(content);
            if (qqResult.hasError()) {
                throw new SocialBusinessException(ErrorMsg.CHECK_VIOLATION_ERR_MSG);
            }
        }
    }
}
