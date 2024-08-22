package com.socialuni.social.user.sdk.logic.domain;

import com.socialuni.social.common.api.constant.DateTimeType;
import com.socialuni.social.common.sdk.utils.SocialuniDateUtils;
import com.socialuni.social.user.sdk.dao.DO.SocialuniPartnerUserOnlineDO;
import com.socialuni.social.user.sdk.dao.repository.SocialuniUserOnlineRepository;
import com.socialuni.social.tance.sdk.facade.SocialuniUnionIdFacede;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.Date;

@Service
@Slf4j
public class SocialuniUserOnlineDomain {

    @Resource
    SocialuniUserOnlineRepository socialuniUserOnlineRepository;

    public void userOnline(String userIdStr) {
        Integer userId = SocialuniUnionIdFacede.getUnionIdByUuidAllowNull(userIdStr);
        //如果不为空，并且用户不在群组中，则不推送
        if (userId == null) {
            return;
        }
        SocialuniPartnerUserOnlineDO socialuniUserOnlineDO = socialuniUserOnlineRepository.findFirstByUserIdOrderByIdDesc(userId);
        if (socialuniUserOnlineDO == null) {
            socialuniUserOnlineDO = new SocialuniPartnerUserOnlineDO();
        } else {
            Date curTime = new Date();
            //得到离线时间，如果存在离线时间
            Date lastOnlineTime = socialuniUserOnlineDO.getUpdateTime();
            //如果已经是第二天，则新建一条数据
            if (curTime.getTime() - lastOnlineTime.getTime() > (10 * DateTimeType.minute)) {
                //如果已经过去了10分钟，则重新设置上线时间
                socialuniUserOnlineDO = new SocialuniPartnerUserOnlineDO();
            } else {
                socialuniUserOnlineDO = getOrUpdateUserOnline(socialuniUserOnlineDO);
            }
        }
        socialuniUserOnlineDO.setUserId(userId);

        //如果当前日期，与查询出来的不是同一个，则新建一个

        int diff = getDiffMinute(socialuniUserOnlineDO);

        //最低10分钟
        socialuniUserOnlineDO.setOnlineMinute(diff + 10);

        socialuniUserOnlineRepository.savePut(socialuniUserOnlineDO);
    }


    public int getDiffMinute(SocialuniPartnerUserOnlineDO socialuniUserOnlineDO) {
        long crateTime = socialuniUserOnlineDO.getCreateTime().getTime();
        long offlineTime = socialuniUserOnlineDO.getUpdateTime().getTime();

        long diffTime = offlineTime - crateTime;

        long diffMinute = diffTime / DateTimeType.minute;

        int diff = (int) diffMinute;
        if (diff < 1) {
            return 1;
        }
        return diff;
    }

    //用户离线，设置离线时间
    public void userOffline(String userIdStr) {
        Integer userId = SocialuniUnionIdFacede.getUnionIdByUuidAllowNull(userIdStr);
        //如果不为空，并且用户不在群组中，则不推送
        if (userId == null) {
            return;
        }
        SocialuniPartnerUserOnlineDO socialuniUserOnlineDO = socialuniUserOnlineRepository.findFirstByUserIdOrderByIdDesc(userId);
        if (socialuniUserOnlineDO == null) {
            socialuniUserOnlineDO = new SocialuniPartnerUserOnlineDO();
        } else {
            socialuniUserOnlineDO = getOrUpdateUserOnline(socialuniUserOnlineDO);
        }
        socialuniUserOnlineDO.setUserId(userId);
        int diff = getDiffMinute(socialuniUserOnlineDO);

        // +10 ，因为每日计算在线时间会在23.50分时进行
        socialuniUserOnlineDO.setOnlineMinute(diff + 10);
        socialuniUserOnlineRepository.savePut(socialuniUserOnlineDO);
    }


    //如果已经过了当天，则新建一条信息，并更新上一条结束时间为当天23.59.59
    private SocialuniPartnerUserOnlineDO getOrUpdateUserOnline(SocialuniPartnerUserOnlineDO socialuniUserOnlineDO) {
        Date curTime = new Date();
        String lastDate = socialuniUserOnlineDO.getTodayDate();
        String curDate = SocialuniDateUtils.simpleDateFormat.format(curTime);
        if (!lastDate.equals(curDate)) {
            //如果不为同一天，则设置上一条数据的更新时间为当天 23.59分59秒。
            try {
                Date newDate = SocialuniDateUtils.simpleDateFormat.parse(curDate);
                long diffNewDate = newDate.getTime() - DateTimeType.second;
                newDate = new Date(diffNewDate);
                socialuniUserOnlineDO.setUpdateTime(newDate);
                socialuniUserOnlineRepository.savePut(socialuniUserOnlineDO);
            } catch (ParseException e) {
                log.error(String.valueOf(e));
                throw new RuntimeException(e);
            }
            //如果已经过去了10分钟，则重新设置上线时间
            socialuniUserOnlineDO = new SocialuniPartnerUserOnlineDO();
            //如果上次离线时间，距离现在过去了10分钟，
        } else {
            //如果离线不足10分钟，则继续更新此数据
            socialuniUserOnlineDO.setUpdateTime(curTime);
        }
        return socialuniUserOnlineDO;
    }

}
