package com.socialuni.social.partner.controller;

import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.common.sdk.utils.SocialuniDateUtils;
import com.socialuni.social.partner.config.SocialuniParterConfig;
import com.socialuni.social.partner.constant.SocialuniPartnerPointsConsumeType;
import com.socialuni.social.partner.dao.model.SocialuniPartnerPointsMainDO;
import com.socialuni.social.partner.dao.model.SocialuniPartnerUserContributeDO;
import com.socialuni.social.partner.dao.repository.SocialuniPartnerPointsMainRepository;
import com.socialuni.social.partner.dao.repository.SocialuniPartnerUserContributeRepository;
import com.socialuni.social.partner.logic.entity.SocialuniPartnerComputedContributeDomain;
import com.socialuni.social.partner.logic.entity.SocialuniPartnerConsumeEntity;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

@RestController
@RequestMapping("socialuni/partner")
public class SocialuniPartnerController {
    @Resource
    SocialuniPartnerConsumeEntity socialuniPartnerConsumeEntity;
    @Autowired
    private SocialuniUserUtil socialuniUserUtil;

    //计算今日积分分配
    @GetMapping("computedTodayPoints")
    public ResultRO<Void> computedTodayPoints() {
        socialuniPartnerConsumeEntity.computedTodayPoints();
        return ResultRO.success();
    }

    @Resource
    SocialuniPartnerComputedContributeDomain socialuniPartnerComputedContributeDomain;

    @GetMapping("computedTodayContribute")
    public ResultRO<Void> computedTodayContribute() {
        socialuniPartnerComputedContributeDomain.computedTodayUserUseContribute();
        return ResultRO.success();
    }

    @Resource
    SocialuniPartnerUserContributeRepository socialuniPartnerUserContributeRepository;


    @GetMapping("getMinePointsAssignDetail")
    public ResultRO<SocialuniPartnerUserContributeDO> getMinePointsAssignDetail() {
        Integer mineUserId = SocialuniUserUtil.getMineUserIdNotNull();
        String day = SocialuniDateUtils.getToday();
        SocialuniPartnerUserContributeDO socialuniPartnerUserContributeDO = socialuniPartnerUserContributeRepository.findFirstByUserIdAndTodayDate(mineUserId, day);
        return ResultRO.success(socialuniPartnerUserContributeDO);
    }
}
