package com.socialuni.social.sdk.logic.service.bussiness;

import com.socialuni.social.common.api.constant.ErrorCode;
import com.socialuni.social.common.api.exception.exception.SocialBusinessException;
import com.socialuni.social.common.api.exception.exception.SocialParamsException;
import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.common.sdk.facade.SocialuniRepositoryFacade;
import com.socialuni.social.sdk.constant.shell.ExpenseType;
import com.socialuni.social.sdk.constant.shell.SocialuniCoinOrderType;
import com.socialuni.social.sdk.dao.DO.bussiness.SocialuniCoinOrderDO;
import com.socialuni.social.sdk.dao.DO.bussiness.SocialuniGetUserContactRecordDO;
import com.socialuni.social.sdk.dao.utils.user.SocialuniUserExpandDOUtil;
import com.socialuni.social.sdk.dao.utils.user.SocialuniUserSocialCoinDOUtil;
import com.socialuni.social.sdk.facade.SocialuniUserContactRepositoryFacede;
import com.socialuni.social.sdk.facade.SocialuniUserRepositoryFacede;
import com.socialuni.social.tance.sdk.config.SocialuniAppConfig;
import com.socialuni.social.user.sdk.model.DO.SocialuniUserCoinDo;
import com.socialuni.social.user.sdk.model.DO.SocialuniUserDo;
import com.socialuni.social.user.sdk.model.DO.SocialuniUserExpandDo;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

//获取对方联系方式，扣减金币，和给对方添加金币的逻辑
@Service
@Slf4j
public class SocialuniGetUserContactInfoDomain {
    @Transactional
    public String getUserContactInfo(String beUserId) {

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

        //消费
        SocialuniCoinOrderDO shellOrderDO = new SocialuniCoinOrderDO(mineUser.getUserId(), -expanseCoinNum, SocialuniCoinOrderType.expense, userContactDO.getId());
        //保存
        shellOrderDO = SocialuniRepositoryFacade.save(shellOrderDO);

        //关联
//        SocialuniCoinOrderDO shellOrderGiveDO = new SocialuniCoinOrderDO(beUser.getUserId(), userGiveShell, SocialuniCoinOrderType.income, userContactDO.getId());
        //保存关联
//        shellOrderGiveDO = SocialuniRepositoryFacade.save(shellOrderGiveDO);


//        shellOrderDO.setRechargeOrderId(shellOrderGiveDO.getId());
        //保存上一个的关联
        shellOrderDO = SocialuniRepositoryFacade.save(shellOrderDO);

        //保存用户
        //用户消耗
        socialuniUserCoinDo.setCoin(mineUserCoin - expanseCoinNum);
        //保存用户消耗
        socialuniUserCoinDo = SocialuniUserSocialCoinDOUtil.save(socialuniUserCoinDo);

//        SocialuniUserCoinDo beUserCoinDo = SocialuniUserSocialCoinDOUtil.getOrCreate(beUser.getUserId());

//        beUserCoinDo.setCoin(beUserCoinDo.getCoin() + userGiveShell);
        //保存用户增加
//        SocialuniUserSocialCoinDOUtil.save(beUserCoinDo);

        return socialuniUserExpandDo.getContactInfo();
    }
}
