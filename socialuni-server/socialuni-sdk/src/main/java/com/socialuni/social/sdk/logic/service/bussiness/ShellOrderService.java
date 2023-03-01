package com.socialuni.social.sdk.logic.service.bussiness;

import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.common.sdk.facade.SocialuniRepositoryFacade;
import com.socialuni.social.sdk.constant.shell.SocialuniCoinOrderType;
import com.socialuni.social.sdk.dao.DO.bussiness.SocialuniCoinOrderDO;
import com.socialuni.social.sdk.dao.DO.bussiness.SocialuniGetUserContactRecordDO;
import com.socialuni.social.sdk.facade.SocialuniUserRepositoryFacede;
import com.socialuni.social.tance.sdk.config.SocialuniAppConfig;
import com.socialuni.social.user.sdk.model.DO.SocialuniUserCoinDo;
import com.socialuni.social.user.sdk.model.DO.SocialuniUserDo;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

//获取对方联系方式，扣减金币，和给对方添加金币的逻辑
@Service
public class ShellOrderService {
    @Transactional
    public ResultRO<String> saveShellOrders(SocialuniUserDo user, SocialuniUserDo beUser, Integer mineUserShell) {
        Integer expense = SocialuniAppConfig.getAppMoreConfig().getContactExpenseShell();

        Integer sysServiceReceiveRatio = SocialuniAppConfig.getAppMoreConfig().getSysServiceReceiveRatio();

        Integer sysTakeShell = expense * sysServiceReceiveRatio / 100;


        //用户获得的为 用户支付的减去系统佣金
        Integer userGiveShell = expense - sysTakeShell;

        //保存可观察
        SocialuniGetUserContactRecordDO userContactDO = new SocialuniGetUserContactRecordDO(user.getId(), beUser.getId());
        userContactDO = SocialuniRepositoryFacade.save(userContactDO);

        //赠送用户
        SocialuniCoinOrderDO shellOrderDO = new SocialuniCoinOrderDO(user.getId(), -expense, SocialuniCoinOrderType.expense, userContactDO.getId());
        //保存
        shellOrderDO = SocialuniRepositoryFacade.save(shellOrderDO);

        //关联
        SocialuniCoinOrderDO shellOrderGiveDO = new SocialuniCoinOrderDO(beUser.getId(), userGiveShell, SocialuniCoinOrderType.income, userContactDO.getId());
        //保存关联
        shellOrderGiveDO = SocialuniRepositoryFacade.save(shellOrderGiveDO);


        shellOrderDO.setRechargeOrderId(shellOrderGiveDO.getId());
        //保存上一个的关联
        shellOrderDO = SocialuniRepositoryFacade.save(shellOrderDO);

        SocialuniUserCoinDo socialuniUserCoinDo = SocialuniUserRepositoryFacede.findByUserId(user.getUserId(), SocialuniUserCoinDo.class);

        //保存用户
        //用户消耗
        socialuniUserCoinDo.setCoin(mineUserShell - expense);
        //保存用户消耗
        socialuniUserCoinDo = SocialuniRepositoryFacade.save(socialuniUserCoinDo);

        SocialuniUserCoinDo beUserCoinDo = SocialuniUserRepositoryFacede.findByUserId(beUser.getUserId(), SocialuniUserCoinDo.class);

        beUserCoinDo.setCoin(beUserCoinDo.getCoin() + userGiveShell);
        //保存用户增加
        beUser = SocialuniRepositoryFacade.save(beUser);

        /*Optional<UserDetailDO> userDetailDOOptional = userDetailRepository.findFirstOneByUserId(beUser.getId());
        if (userDetailDOOptional.isPresent()) {
            UserDetailDO userDetailDO = userDetailDOOptional.get();
            ResultVO<String> resultVO = new ResultVO<>();
            resultVO.setData(userDetailDO.getContactAccount());
            return resultVO;
        } else {
            return new ResultVO<>(ErrorCode.SYSTEM_ERROR);
        }*/
        return null;
    }
}
