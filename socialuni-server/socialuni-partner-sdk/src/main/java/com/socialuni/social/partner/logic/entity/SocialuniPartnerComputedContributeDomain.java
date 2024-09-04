package com.socialuni.social.partner.logic.entity;

import com.socialuni.social.common.api.exception.exception.SocialSystemException;
import com.socialuni.social.common.api.utils.NumberUtils;
import com.socialuni.social.common.sdk.dao.facede.SocialuniRepositoryFacade;
import com.socialuni.social.common.sdk.utils.SocialuniDateUtils;
import com.socialuni.social.partner.config.SocialuniParterConfig;
import com.socialuni.social.partner.constant.SocialuniPartnerPointsConsumeType;
import com.socialuni.social.partner.dao.model.SocialuniPartnerPointsMainDO;
import com.socialuni.social.partner.dao.model.SocialuniPartnerUserContributeDO;
import com.socialuni.social.partner.dao.repository.SocialuniPartnerPointsMainRepository;
import com.socialuni.social.partner.dao.repository.SocialuniPartnerUserContributeRepository;
import com.socialuni.social.user.sdk.dao.DO.SocialuniPartnerUserOnlineDO;
import com.socialuni.social.user.sdk.dao.repository.SocialuniUserOnlineRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class SocialuniPartnerComputedContributeDomain {

    @Resource
    SocialuniUserOnlineRepository socialuniUserOnlineRepository;
    @Resource
    SocialuniPartnerPointsMainRepository socialuniPartnerPointsMainRepository;
    @Resource
    SocialuniPartnerConsumeEntity socialuniPartnerConsumeEntity;

    @Resource
    SocialuniPartnerUserContributeRepository socialuniPartnerUserContributeRepository;

    //计算使用贡献值
    public void computedTodayUserUseContribute() {
        Date curDate = new Date();
        String today = SocialuniDateUtils.simpleDateFormat.format(curDate);

        List<SocialuniPartnerUserOnlineDO> socialuniUserOnlineDOS = socialuniUserOnlineRepository.findByTodayDate(today);


        Map<Integer, Integer> userOnlineMap = new HashMap<>();

        for (SocialuniPartnerUserOnlineDO socialuniUserOnlineDO : socialuniUserOnlineDOS) {
            Long userId = socialuniUserOnlineDO.getUserId();

            Integer userOnlineMinute = userOnlineMap.get(userId);

            Integer itemUserOnlineMinute = socialuniUserOnlineDO.getOnlineMinute();

            if (userOnlineMinute == null) {
                userOnlineMap.put(userId, itemUserOnlineMinute);
            } else {
                userOnlineMap.put(userId, userOnlineMinute + itemUserOnlineMinute);
            }
        }


        SocialuniPartnerPointsMainDO socialuniPartnerPointsMainDO = socialuniPartnerPointsMainRepository.findFirstByTodayDateAndConsumeType(today, SocialuniPartnerPointsConsumeType.useAssign);
        if (socialuniPartnerPointsMainDO == null) {
            socialuniPartnerConsumeEntity.computedTodayPoints();
            socialuniPartnerPointsMainDO = socialuniPartnerPointsMainRepository.findFirstByTodayDateAndConsumeType(today, SocialuniPartnerPointsConsumeType.useAssign);
            if (socialuniPartnerPointsMainDO == null) {
                throw new SocialSystemException("缺少'" + curDate + "'的积分主表分配数据");
            }
        }

        socialuniPartnerPointsMainDO.setAssignNum(0);
        socialuniPartnerPointsMainDO.setDestroyNum(socialuniPartnerPointsMainDO.getConsume());

        //记录所有用户今日在线总分中
        Integer allUserOriginalContributeValue = 0;
        Integer allUserContributeValue = 0;

        List<Integer> originalValueList = new ArrayList<>();
        List<Integer> valueList = new ArrayList<>();

        List<SocialuniPartnerUserContributeDO> userValueList = new ArrayList<>();

        for (Long userId : userOnlineMap.keySet()) {
            Integer userOnlineMinute = userOnlineMap.get(userId);

            SocialuniPartnerUserContributeDO socialuniPartnerUserContributeDO = socialuniPartnerUserContributeRepository.findFirstByUserIdAndTodayDate(userId, today);

            if (socialuniPartnerUserContributeDO == null) {
                socialuniPartnerUserContributeDO = new SocialuniPartnerUserContributeDO();
            }

            userValueList.add(socialuniPartnerUserContributeDO);


            socialuniPartnerUserContributeDO.setUserId(userId);
            socialuniPartnerUserContributeDO.setTodayDate(today);
            socialuniPartnerUserContributeDO.setOnlineMinute(userOnlineMinute);

            //计算用户加成
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DAY_OF_MONTH, -11); // 设置为10天前
            calendar.set(Calendar.HOUR_OF_DAY, 0); // 设置为0点
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            Date tenDaysAgo = calendar.getTime();


            Calendar calendar1 = Calendar.getInstance();
            calendar.add(Calendar.DAY_OF_MONTH, -1); // 设置为10天前
            calendar1.set(Calendar.HOUR_OF_DAY, 23); // 设置为0点
            calendar1.set(Calendar.MINUTE, 59);
            calendar1.set(Calendar.SECOND, 59);

            Date oneDaysAgo = calendar1.getTime();

            List<SocialuniPartnerUserOnlineDO> list = socialuniUserOnlineRepository.findByUserIdAndCreateTimeBetween(userId, tenDaysAgo, oneDaysAgo);

            Integer userOriginalContributeValue = (userOnlineMinute * SocialuniParterConfig.musicContributeValue);
            Integer userUseMultiple = list.size();

            Integer userContributeValue = (userOriginalContributeValue * (10 + userUseMultiple)) / 10;

            allUserOriginalContributeValue += userOriginalContributeValue;

            originalValueList.add(userOriginalContributeValue);


            allUserContributeValue += userContributeValue;
            valueList.add(userContributeValue);


            socialuniPartnerUserContributeDO.setUseMultiple(userUseMultiple);
            socialuniPartnerUserContributeDO.setOriginalContributeValue(userOriginalContributeValue);
            socialuniPartnerUserContributeDO.setContributeValue(userContributeValue);

        }


        for (SocialuniPartnerUserContributeDO socialuniPartnerUserOnlineContributeDO : userValueList) {
            //方便计算，乘上积分的默认倍数, 因为最多保留两位小数，10.00%，所以乘以1w，再乘以100（百分比）
            Integer tempEasyComputed = SocialuniParterConfig.pointDefaultMultiple;

            Integer userOriginalContributeValue = socialuniPartnerUserOnlineContributeDO.getOriginalContributeValue();
            //方便计算值 * 个人值/总值/
            Integer userOriginalPercent = tempEasyComputed * userOriginalContributeValue / allUserOriginalContributeValue;
            socialuniPartnerUserOnlineContributeDO.setOriginalValuePercent(userOriginalPercent);

            Integer userContributeValue = socialuniPartnerUserOnlineContributeDO.getContributeValue();
            //得到百分比
            Integer userPercent = tempEasyComputed * userContributeValue / allUserContributeValue;
            socialuniPartnerUserOnlineContributeDO.setValuePercent(userPercent);

            Integer originalPointsAssignPercent = Math.min(SocialuniParterConfig.pointDefaultMultiple / 10, userOriginalPercent);
            Integer pointsAssignPercent = Math.min(SocialuniParterConfig.pointDefaultMultiple / 10, userPercent);

            socialuniPartnerUserOnlineContributeDO.setOriginalAssignPercent(originalPointsAssignPercent);
            socialuniPartnerUserOnlineContributeDO.setAssignPercent(pointsAssignPercent);


            //获取今日分配的积分，得到自己今日得到的积分

            Integer todayUseAllPoints = socialuniPartnerPointsMainDO.getConsume();

            Integer originalUserAssignPoints = todayUseAllPoints * originalPointsAssignPercent / SocialuniParterConfig.pointDefaultMultiple;
            socialuniPartnerUserOnlineContributeDO.setOriginalAssignPoints(originalUserAssignPoints);

            Integer userAssignPoints = todayUseAllPoints * pointsAssignPercent / SocialuniParterConfig.pointDefaultMultiple;
            socialuniPartnerUserOnlineContributeDO.setAssignPoints(userAssignPoints);


            //计算排名
//            userContributeValue,在一个数组中的排名
            Integer originalRank = NumberUtils.getRank(originalValueList, userOriginalContributeValue);
            Integer valueRank = NumberUtils.getRank(valueList, userContributeValue);

            socialuniPartnerUserOnlineContributeDO.setContributeRank(valueRank);
            socialuniPartnerUserOnlineContributeDO.setOriginalContributeRank(originalRank);

            Integer assignNum = socialuniPartnerPointsMainDO.getAssignNum();
            assignNum += userAssignPoints;
            socialuniPartnerPointsMainDO.setAssignNum(assignNum);

            Integer destroyNum = socialuniPartnerPointsMainDO.getDestroyNum();
            destroyNum -= userAssignPoints;

            socialuniPartnerPointsMainDO.setDestroyNum(destroyNum);

            SocialuniRepositoryFacade.save(socialuniPartnerPointsMainDO);
            SocialuniRepositoryFacade.save(socialuniPartnerUserOnlineContributeDO);
        }


        //计算贡献表
        // 获取今日日期

        //获取今日所有的在线时长

        //累加所有用户的在线时长

    }
}


