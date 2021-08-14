package com.socialuni.admin.web.domain;

import com.socialuni.admin.web.controller.DevAuthCodeRepository;
import com.socialuni.cloud.tencent.TencentSmsServe;
import com.socialuni.entity.model.DevAuthCodeDO;
import com.socialuni.social.constant.StatusConst;
import com.socialuni.social.exception.SocialParamsException;
import com.socialuni.social.utils.IpUtil;
import com.socialuni.social.utils.PhoneNumUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author qinkaiyuan
 * @date 2019-02-17 14:14
 */
@Service
@Slf4j
public class AdminSendAuthCodeDomain {

}
