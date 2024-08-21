package com.socialuni.social.user.sdk.logic.domain;

import com.socialuni.social.common.api.constant.DateTimeType;
import com.socialuni.social.tance.sdk.facade.SocialuniUnionIdFacede;
import com.socialuni.social.user.sdk.dao.DO.SocialuniUserOnlineDO;
import com.socialuni.social.user.sdk.dao.repository.SocialuniUserOnlineRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class SocialuniUserOnlineDomain {

    @Resource
    SocialuniUserOnlineRepository socialuniUserOnlineRepository;

    public void userOnline(String userIdStr) {
        Integer userId = SocialuniUnionIdFacede.getUnionIdByUuidAllowNull(userIdStr);
        //如果不为空，并且用户不在群组中，则不推送
        if (userId == null) {
            return;
        }
        SocialuniUserOnlineDO socialuniUserOnlineDO = socialuniUserOnlineRepository.findFirstByUserIdOrderByIdDesc(userId);
        if (socialuniUserOnlineDO == null) {
            socialuniUserOnlineDO = new SocialuniUserOnlineDO();
        } else {
            Date curTime = new Date();
            //得到离线时间，如果存在离线时间
            Date lastOnlineTime = socialuniUserOnlineDO.getUpdateTime();
            //如果上次离线时间，距离现在过去了10分钟，
            if (curTime.getTime() - lastOnlineTime.getTime() > (10 * DateTimeType.minute)) {
                //如果已经过去了10分钟，则重新设置上线时间
                socialuniUserOnlineDO = new SocialuniUserOnlineDO();
            } else {
                //如果离线不足10分钟，则继续更新此数据
                socialuniUserOnlineDO.setUpdateTime(curTime);
            }
        }
        socialuniUserOnlineDO.setUserId(userId);
        socialuniUserOnlineRepository.savePut(socialuniUserOnlineDO);
    }


    //用户离线，设置离线时间
    public void userOffline(String userIdStr) {
        Integer userId = SocialuniUnionIdFacede.getUnionIdByUuidAllowNull(userIdStr);
        //如果不为空，并且用户不在群组中，则不推送
        if (userId == null) {
            return;
        }
        SocialuniUserOnlineDO socialuniUserOnlineDO = socialuniUserOnlineRepository.findFirstByUserIdOrderByIdDesc(userId);
        if (socialuniUserOnlineDO == null) {
            socialuniUserOnlineDO = new SocialuniUserOnlineDO();
        } else {
            Date curTime = new Date();
            socialuniUserOnlineDO.setUpdateTime(curTime);
        }
        socialuniUserOnlineDO.setUserId(userId);
        socialuniUserOnlineRepository.savePut(socialuniUserOnlineDO);
    }

}
