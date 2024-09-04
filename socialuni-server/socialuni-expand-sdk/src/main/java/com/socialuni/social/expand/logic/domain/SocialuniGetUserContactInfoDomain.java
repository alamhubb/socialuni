package com.socialuni.social.expand.logic.domain;

import com.socialuni.social.common.api.exception.exception.SocialBusinessException;
import com.socialuni.social.common.api.exception.exception.SocialParamsException;
import com.socialuni.social.common.sdk.dao.facede.SocialuniRepositoryFacade;
import com.socialuni.social.recharge.constant.SocialuniOrderDetailType;
import com.socialuni.social.recharge.constant.SocialuniCoinOrderType;
import com.socialuni.social.common.sdk.dao.DO.SocialuniGetUserContactRecordDO;
import com.socialuni.social.expand.utils.SocialuniUserExpandDOUtil;
import com.socialuni.social.recharge.logic.entity.SocialuniCreateCoinOrderEntity;
import com.socialuni.social.user.sdk.utils.SocialuniUserSocialCoinDOUtil;
import com.socialuni.social.common.sdk.dao.facede.SocialuniUserContactRepositoryFacede;
import com.socialuni.social.tance.sdk.config.SocialuniAppConfig;
import com.socialuni.social.user.sdk.dao.DO.SocialuniUserCoinDo;
import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
import com.socialuni.social.expand.dao.DO.SocialuniUserExpandDo;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

//获取对方联系方式，扣减金币，和给对方添加金币的逻辑
@Service
@Slf4j
public class SocialuniGetUserContactInfoDomain {

    @Resource
    SocialuniCreateCoinOrderEntity socialuniCreateCoinOrderEntity;

    @Transactional
    public String getUserContactInfo(String beUserId) {
        //获取对方联系方式
        //然后确认对方是否拥有联系方式。
        //首先需要确认，对方是否开启获取联系方式
        //如果都有则可以获取。然后就是付费逻辑了
        SocialuniUserDo mineUser = SocialuniUserUtil.getMineUserNotNull();
        SocialuniUserDo beUser = SocialuniUserUtil.getUserByUuid(beUserId);

        SocialuniUserCoinDo socialuniUserCoinDo = SocialuniUserSocialCoinDOUtil.getNotNull(mineUser.getUserId());

        //获取用户金币
        Integer mineUserCoin = socialuniUserCoinDo.getCoin();
        //获取联系方式需要的金币数量
        Integer expanseCoinNum = SocialuniAppConfig.getAppMoreConfig().getContactExpenseShell();

        if (mineUserCoin < expanseCoinNum) {
            log.error("系统被攻击，不该触发这里，用户不够" + expanseCoinNum + "金币");
            throw new SocialParamsException("系统被攻击，不该触发这里，用户不够" + expanseCoinNum + "金币");
        }
        SocialuniGetUserContactRecordDO socialuniGetUserContactRecordDO = SocialuniUserContactRepositoryFacede.findByUserIdAndBeUserId(mineUser.getUserId(), beUser.getUserId(), SocialuniGetUserContactRecordDO.class);
        //已经获取过了，不应该还能获取
        if (socialuniGetUserContactRecordDO != null) {
            log.error("已经获取过用户联系方式了，不应该还能获取");
            throw new SocialParamsException("已经获取过用户联系方式了，不应该还能获取");
        }

        //判断对方是否开启了允许他人获取联系方式
        SocialuniUserExpandDo socialuniUserExpandDo = SocialuniUserExpandDOUtil.getNotNull(beUser.getUserId());

        if (!socialuniUserExpandDo.getOpenContactInfo()) {
            log.info("用户未开启联系方式，无法获取");
            throw new SocialBusinessException("用户未开启联系方式，无法获取");
        }


        Integer sysServiceReceiveRatio = SocialuniAppConfig.getAppMoreConfig().getSysServiceReceiveRatio();

        Integer sysTakeShell = expanseCoinNum * sysServiceReceiveRatio / 100;

        //用户获得的为 用户支付的减去系统佣金
        Integer userGiveShell = expanseCoinNum - sysTakeShell;

        //保存可观察
        SocialuniGetUserContactRecordDO userContactDO = new SocialuniGetUserContactRecordDO(mineUser.getUserId(), beUser.getUserId(), socialuniUserExpandDo.getContactInfo());
        userContactDO = SocialuniRepositoryFacade.save(userContactDO);

        Long userId = mineUser.getUserId();

        socialuniCreateCoinOrderEntity.createCoinOrderByOrderType(userId, -expanseCoinNum, SocialuniCoinOrderType.consume, SocialuniOrderDetailType.contact, userContactDO.getId());

        //关联
//        SocialuniCoinOrderDO shellOrderGiveDO = new SocialuniCoinOrderDO(beUser.getUserId(), userGiveShell, SocialuniCoinOrderType.income, userContactDO.getId());
        //保存关联
//        shellOrderGiveDO = SocialuniRepositoryFacade.save(shellOrderGiveDO);


//        shellOrderDO.setRechargeOrderId(shellOrderGiveDO.getId());
        //保存上一个的关联
//        shellOrderDO = SocialuniRepositoryFacade.save(shellOrderDO);


//        SocialuniUserCoinDo beUserCoinDo = SocialuniUserSocialCoinDOUtil.getOrCreate(beUser.getUserId());

//        beUserCoinDo.setCoin(beUserCoinDo.getCoin() + userGiveShell);
        //保存用户增加
//        SocialuniUserSocialCoinDOUtil.save(beUserCoinDo);

        return socialuniUserExpandDo.getContactInfo();
    }
}
