package com.qingchi.web.service;

import com.qingchi.web.constant.ExpenseType;
import com.qingchi.web.constant.ShellOrderType;
import com.qingchi.web.model.DO.UserDetailDO;
import com.qingchi.web.model.DO.shell.ShellOrderDO;
import com.qingchi.web.repsotiory.ShellOrderRepository;
import com.qingchi.web.repsotiory.UserDetailRepository;
import com.qingchi.web.store.ShellOrderStore;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.social.entity.model.DO.chat.UserContactDO;
import com.socialuni.social.entity.model.DO.user.UserDO;
import com.socialuni.social.exception.SocialParamsException;
import com.socialuni.social.sdk.constant.AppConfigConst;
import com.socialuni.social.sdk.repository.UserContactRepository;
import com.socialuni.social.sdk.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class ShellOrderService {
    @Resource
    private UserDetailRepository userDetailRepository;

    @Resource
    private ShellOrderStore shellOrderStore;

    @Resource
    private ShellOrderRepository shellOrderRepository;

    @Resource
    private UserRepository userRepository;

    @Resource
    private UserContactRepository userContactRepository;

    /**
     * 创建 contact消费，和保存shell订单
     *
     * @param user
     * @param beUser
     * @param expenseType
     * @return
     */
    @Transactional
    public ResultRO<String> createAndSaveContactAndShellOrders(UserDO user, UserDO beUser, String expenseType) {
        Integer expense;
        String loseShellDetailType;
        String getShellDetailType;

        //分别获取开启chat和获取联系方式的贝壳消耗数量
        if (ExpenseType.contact.equals(expenseType)) {
            expense = (Integer) AppConfigConst.appConfigMap.get(AppConfigConst.contactExpenseShellKey);
            loseShellDetailType = "获取联系方式";
            getShellDetailType = "被获取联系方式";
        } else {
            expense = (Integer) AppConfigConst.appConfigMap.get(AppConfigConst.openChatExpenseShellKey);
            loseShellDetailType = "开启会话";
            getShellDetailType = "被开启会话";
        }

        Integer sysServiceReceiveRatio = (Integer) AppConfigConst.appConfigMap.get(AppConfigConst.sysServiceReceiveRatioKey);

        Integer sysTakeShell = expense * sysServiceReceiveRatio / 10;

        //用户获得的为 用户支付的减去系统佣金
        Integer userGiveShell = expense - sysTakeShell;

        //保存可观察
        UserContactDO userContactDO = new UserContactDO(user.getId(), beUser.getId(), expenseType);
        userContactDO = userContactRepository.save(userContactDO);

        //赠送用户
        ShellOrderDO shellOrderDO = new ShellOrderDO(user.getId(), -expense, ShellOrderType.expense, expenseType, loseShellDetailType, userContactDO.getId());
        //保存
        shellOrderDO = shellOrderRepository.save(shellOrderDO);

        //关联
        ShellOrderDO shellOrderGiveDO = new ShellOrderDO(beUser.getId(), userGiveShell, expenseType, getShellDetailType, userContactDO.getId(), shellOrderDO.getId());
        //保存关联
        shellOrderGiveDO = shellOrderRepository.save(shellOrderGiveDO);


        shellOrderDO.setRelatedOrderId(shellOrderGiveDO.getId());
        //保存上一个的关联
        shellOrderDO = shellOrderRepository.save(shellOrderDO);
//        Integer sessionUserShell = user.getShell();
        //保存用户
        //用户消耗
//        user.setShell(sessionUserShell - expense);
        //保存用户消耗
        user = userRepository.save(user);

//        beUser.setShell(beUser.getShell() + userGiveShell);
        //保存用户增加
        beUser = userRepository.save(beUser);

        Optional<UserDetailDO> userDetailDOOptional = userDetailRepository.findFirstOneByUserId(beUser.getId());
        if (userDetailDOOptional.isPresent()) {
            UserDetailDO userDetailDO = userDetailDOOptional.get();
            ResultRO<String> resultRO = new ResultRO<>();
            resultRO.setData(userDetailDO.getContactAccount());
            return resultRO;
        } else {
            throw new SocialParamsException("没有用户");
        }
    }


}
