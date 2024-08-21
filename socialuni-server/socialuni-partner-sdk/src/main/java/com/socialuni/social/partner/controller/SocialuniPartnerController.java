package com.socialuni.social.partner.controller;

import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.common.sdk.utils.SocialuniDateUtils;
import com.socialuni.social.partner.config.SocialuniParterConfig;
import com.socialuni.social.partner.constant.SocialuniPartnerPointsConsumeType;
import com.socialuni.social.partner.logic.entity.SocialuniPartnerConsumeEntity;
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

    //计算今日积分分配
    @GetMapping("computedTodayPoints")
    public ResultRO<Void> computedTodayPoints() {
        socialuniPartnerConsumeEntity.computedTodayPointsByConsumeType(SocialuniPartnerPointsConsumeType.destroy);
        socialuniPartnerConsumeEntity.computedTodayPointsByConsumeType(SocialuniPartnerPointsConsumeType.useAssign);
        socialuniPartnerConsumeEntity.computedTodayPointsByConsumeType(SocialuniPartnerPointsConsumeType.payAssign);
        return ResultRO.success();
    }
}
