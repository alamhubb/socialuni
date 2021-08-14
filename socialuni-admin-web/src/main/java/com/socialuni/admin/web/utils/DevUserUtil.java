package com.socialuni.admin.web.utils;

import com.socialuni.admin.web.controller.DevAccountRepository;
import com.socialuni.admin.web.repository.DevTokenRepository;
import com.socialuni.entity.model.DevAccountDO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import social.web.sdk.utils.SocialTokenUtil;

import javax.annotation.Resource;
import java.util.Optional;

@Component
public class DevUserUtil {

    private static DevTokenRepository devTokenRepository;
    private static DevAccountRepository devAccountRepository;

    @Resource
    public void setDevTokenRepository(DevTokenRepository devTokenRepository) {
        DevUserUtil.devTokenRepository = devTokenRepository;
    }

    @Resource
    public void setDevAccountRepository(DevAccountRepository devAccountRepository) {
        DevUserUtil.devAccountRepository = devAccountRepository;
    }

    public static DevAccountDO getUserByToken() {
        String token = SocialTokenUtil.getToken();
        return DevUserUtil.getUserByToken(token);
    }

    //得到用户信息
    private static DevAccountDO getUserByToken(String token) {
        if (token != null) {
            if (SocialTokenUtil.isSuccess(token)) {
                String userId = SocialTokenUtil.getUserKeyByToken(token);
                if (StringUtils.isNotEmpty(userId)) {
                    Integer userIdInt = Integer.parseInt(userId);
                    //todo 这里需要校验有效期吧
                    String tokenCode = devTokenRepository.findFirstTokenCodeByUserId(userIdInt);
                    //token与用户token相等
                    if (token.equals(tokenCode)) {
                        Optional<DevAccountDO> userDOOptional = devAccountRepository.findById(userIdInt);
                        if (userDOOptional.isPresent()) {
                            return userDOOptional.get();
                        }
                    }
                }
            }
//        暂时注释掉错误日志，要考虑token为空的情况 iscorrect方法中的那几种情况
//        ErrorLogUtils.save(new ErrorLogDO(null, "有人使用错误的token来破解系统", token, ErrorLevel.urgency));
        }
        return null;
    }
}
