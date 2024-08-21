package com.socialuni.social.partner.logic.entity;

import com.socialuni.social.common.sdk.dao.facede.SocialuniRepositoryFacade;
import com.socialuni.social.common.sdk.utils.SocialuniDateUtils;
import com.socialuni.social.partner.config.SocialuniParterConfig;
import com.socialuni.social.partner.constant.SocialuniPartnerPointsConsumeType;
import com.socialuni.social.partner.dao.model.SocialuniPartnerPointsMainDO;
import com.socialuni.social.partner.dao.repository.SocialuniPartnerPointsMainRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class SocialuniPartnerConsumeEntity {
    @Resource
    SocialuniPartnerPointsMainRepository socialuniPartnerPointsMainRepository;


    public Integer getConsumePointByConsumeType(String consumeType) {
        if (SocialuniPartnerPointsConsumeType.destroy.equals(consumeType)) {
            return SocialuniParterConfig.dayDestroy;
        }
        Date date = new Date();
        String dayMonth = SocialuniDateUtils.simpleMonthFormat.format(date);
        Integer dayPoints = SocialuniParterConfig.monthPointsConfigMap.get(dayMonth);
        Integer useAssignPoints = dayPoints * SocialuniParterConfig.consumeTypeAssignValueMap.get(consumeType) / 100;

        return useAssignPoints;
    }

    public void computedTodayPointsByConsumeType(String consumeType) {
        Date date = new Date();

        String dayDate = SocialuniDateUtils.simpleDateFormat.format(date);
        System.out.println(dayDate);

        SocialuniPartnerPointsMainDO balancePoints = SocialuniRepositoryFacade.findById(1, SocialuniPartnerPointsMainDO.class);

        Integer balance = balancePoints.getBalance();


        SocialuniPartnerPointsMainDO socialuniPartnerPointsMainDO = socialuniPartnerPointsMainRepository.findFirstByTodayDateAndConsumeType(dayDate, consumeType);
        if (socialuniPartnerPointsMainDO == null) {
            socialuniPartnerPointsMainDO = new SocialuniPartnerPointsMainDO();
            socialuniPartnerPointsMainDO.setTodayDate(dayDate);
            socialuniPartnerPointsMainDO.setConsumeType(consumeType);
        } else {
            //将balance加回去，重新计算
            Integer consume = socialuniPartnerPointsMainDO.getConsume();
            balance = balance + consume;
        }

        Integer useAssignPoints = getConsumePointByConsumeType(consumeType);

        balance = balance - useAssignPoints;

        balancePoints.setBalance(balance);
        balancePoints.setUpdateTime(date);
        socialuniPartnerPointsMainRepository.save(balancePoints);

        socialuniPartnerPointsMainDO.setConsume(useAssignPoints);
        // 没有意义了，更新的时候会导致数据不正确，会拿最新的balance，而不是当时的，如果拿当时的去更新最新的也有错误，先这样，整体逻辑正确，无影响
        socialuniPartnerPointsMainDO.setBalance(balance);
        socialuniPartnerPointsMainDO.setUpdateTime(date);

        socialuniPartnerPointsMainRepository.save(socialuniPartnerPointsMainDO);
    }

    public void computedTodayDestroyPoints() {
        Date date = new Date();
        String dayMonth = SocialuniDateUtils.simpleMonthFormat.format(date);
        String dayDate = SocialuniDateUtils.simpleDateFormat.format(date);
        System.out.println(dayMonth);
        System.out.println(dayDate);

        Integer dayPoints = SocialuniParterConfig.monthPointsConfigMap.get(dayMonth);

        Integer useAssignPoints = dayPoints * SocialuniParterConfig.useAssign / 100;
        Integer payAssignPoints = dayPoints * SocialuniParterConfig.payAssign / 100;


        System.out.println(dayPoints);
        System.out.println(useAssignPoints);
        System.out.println(payAssignPoints);
    }
}
