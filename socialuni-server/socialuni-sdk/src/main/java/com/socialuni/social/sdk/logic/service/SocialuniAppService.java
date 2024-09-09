package com.socialuni.social.sdk.logic.service;


import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.AES;
import com.socialuni.social.common.api.utils.SocialTokenFacade;
import com.socialuni.social.common.api.utils.UUIDUtil;
import com.socialuni.social.common.sdk.constant.ViolateType;
import com.socialuni.social.community.sdk.logic.factory.SocialHomeSwiperROFactory;
import com.socialuni.social.community.sdk.dao.DO.SocialuniHomeSwiperDO;
import com.socialuni.social.common.sdk.model.VO.HomeSwiperVO;
import com.socialuni.social.sdk.logic.domain.log.SocialFrontLogDomain;
import com.socialuni.social.common.sdk.model.VO.FrontErrorLogVO;
import com.socialuni.social.common.sdk.model.SocialAppLaunchDataRO;
import com.socialuni.social.community.sdk.repository.HomeSwiperRepository;
import com.socialuni.social.tance.dev.config.SocialuniAppConfig;
import com.socialuni.social.tance.dev.facade.DevAccountFacade;
import com.socialuni.social.common.api.model.ResultRO;
import com.qingchi.qing.constant.SocialuniCommonStatus;
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
        List<SocialuniHomeSwiperDO> homeSwiperDOS = homeSwiperRepository.findAllByStatusAndDevIdOrderByTopLevelAscIdDesc(SocialuniCommonStatus.enable, DevAccountFacade.getDevIdNullElseCenterDevId());
        List<HomeSwiperVO> homeSwiperVOS = SocialHomeSwiperROFactory.toVOS(homeSwiperDOS);
        return new ResultRO<>(homeSwiperVOS);
    }

    public ResultRO<Void> sendErrorLog(FrontErrorLogVO frontErrorLogVO) {
        return socialFrontLogDomain.sendErrorLog(frontErrorLogVO);
    }
}
