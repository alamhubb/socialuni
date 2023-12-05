package com.socialuni.social.sdk.logic.service;


import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.AES;
import com.socialuni.social.common.api.utils.SocialTokenFacade;
import com.socialuni.social.common.api.utils.UUIDUtil;
import com.socialuni.social.sdk.constant.ViolateType;
import com.socialuni.social.sdk.logic.factory.SocialHomeSwiperROFactory;
import com.socialuni.social.sdk.dao.DO.SocialuniHomeSwiperDO;
import com.socialuni.social.community.sdk.model.HomeSwiperVO;
import com.socialuni.social.sdk.logic.domain.log.SocialFrontLogDomain;
import com.socialuni.social.user.sdk.model.FrontErrorLogVO;
import com.socialuni.social.user.sdk.model.SocialAppLaunchDataRO;
import com.socialuni.social.sdk.dao.repository.HomeSwiperRepository;
import com.socialuni.social.tance.sdk.config.SocialuniAppConfig;
import com.socialuni.social.tance.sdk.facade.DevAccountFacade;
import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.common.api.enumeration.SocialuniCommonStatus;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author qinkaiyuan
 * @date 2019-09-28 11:09
 * 前端初始化内容
 */
@Service
public class SocialuniAppService {
    @Resource
    HomeSwiperRepository homeSwiperRepository;
    @Resource
    SocialFrontLogDomain socialFrontLogDomain;

    public ResultRO<SocialAppLaunchDataRO> getAppConfig() {
        SocialAppLaunchDataRO appInitData = new SocialAppLaunchDataRO();
        appInitData.setAppConfig(SocialuniAppConfig.getAppConfig());
        appInitData.setAppMoreConfig(SocialuniAppConfig.getAppMoreConfig());
//        appInitData.setOnlineUsersCount(WebsocketServer.getOnlineCount());
        appInitData.setReportTypes(ViolateType.frontShowReportTypes);

        appInitData.setPublicKey(SocialTokenFacade.getPasswordPublicKey());

        return new ResultRO<>(appInitData);
    }

    public static void main(String[] args) {
        //先对秘钥进行加密，然后再对加密后的进行加密
        String uuidKey = UUIDUtil.getUUID();
        //秘钥
        AES aes = SecureUtil.aes(uuidKey.getBytes());
        String encrypted = aes.encryptHex(SocialTokenFacade.getPasswordPublicKey());
        String content = aes.decryptStr(encrypted);

        System.out.println(encrypted);
        System.out.println(content);
    }

    public ResultRO<List<HomeSwiperVO>> queryHomeSwipers() {
        //homeSwipers
        List<SocialuniHomeSwiperDO> homeSwiperDOS = homeSwiperRepository.findAllByStatusAndDevIdOrderByTopLevelAscIdDesc(SocialuniCommonStatus.enable, DevAccountFacade.getDevIdNotNull());
        List<HomeSwiperVO> homeSwiperVOS = SocialHomeSwiperROFactory.toVOS(homeSwiperDOS);
        return new ResultRO<>(homeSwiperVOS);
    }

    public ResultRO<Void> sendErrorLog(FrontErrorLogVO frontErrorLogVO) {
        return socialFrontLogDomain.sendErrorLog(frontErrorLogVO);
    }
}
