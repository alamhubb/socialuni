package com.qingchi.web.service;


import com.qingchi.web.repsotiory.QcbOrderRepository;
import com.qingchi.web.repsotiory.VipOrderRepository;
import com.qingchi.web.repsotiory.VipSaleRepository;
import com.socialuni.social.sdk.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author qinkaiyuan
 * @date 2019-06-25 19:59
 */
@Service
public class VipService {
    @Resource
    private UserRepository userRepository;
    @Resource
    private VipSaleRepository vipSaleRepository;
    @Resource
    private VipOrderRepository vipOrderRepository;
    @Resource
    private QcbOrderRepository qcbOrderRepository;

    /*public void addVipOrder(String giftType, Integer userId, String inviteCode, UserDO inviteUser) {
        VipSaleDO vipSaleDO = vipSaleRepository.findFirstByEnabledAndActivityTypeAndGiftTypeAndMonthNumOrderByDiscount(true, VipActivityType.gift, giftType, 1);
        //如果有符合的活动
        if (vipSaleDO != null) {
            //如果已经是年会员，则不需要赠送会员了，直接赠送清池币
            if (user.getYearVipFlag() != null && user.getYearVipFlag()) {
                QcbOrderDO qcbOrderDO = new QcbOrderDO();
                qcbOrderDO.setUser(user);
                qcbOrderDO.setCreateDate(new Date());
                qcbOrderDO.setMoney(0);
                qcbOrderDO.setTakeSale(true);
                qcbOrderDO.setVipSale(vipSaleDO);
                qcbOrderDO.setActivityType(VipActivityType.gift);
                qcbOrderDO.setInviteCode(inviteCode);
                qcbOrderDO.setInviteUser(inviteUser);
                qcbOrderRepository.save(qcbOrderDO);
                if (user.getQcb() == null) {
                    //赠送30个
                    user.setQcb(CommonConst.VIP_QCB_VALUE);
                } else {
                    //用户清池币数量增加
                    user.setQcb(user.getQcb() + CommonConst.VIP_QCB_VALUE);
                }
            } else {
                //不是年会员，赠送会员
                VipOrderDO vipOrderDO = new VipOrderDO();
                //可前置相同内容
                vipOrderDO.setUser(user);
                vipOrderDO.setCreateTime(new Date());
                vipOrderDO.setMoney(0);
                vipOrderDO.setMonthNum(vipSaleDO.getMonthNum());
                vipOrderDO.setEnabled(true);
                vipOrderDO.setVipSale(vipSaleDO);
                vipOrderDO.setTakeSale(true);
                vipOrderDO.setActivityType(VipActivityType.gift);
                if (giftType.equals(VipGiftType.register)) {
                    //注册为新开会员
                    vipOrderDO.setRenew(false);
                    //如果是邀请的话，要看user现在是否是会员，是会员的话查询上一个订单，
                } else if (giftType.equals(VipGiftType.invite) || giftType.equals(VipGiftType.doInvite)) {
                    //不是年会员，赠送会员，
                    //如果用户是否已经是会员
                    if (user.getVipFlag() != null && user.getVipFlag()) {
                        //查询当前user启用的最后一个订单，
                        VipOrderDO lastUserOrder = vipOrderRepository.findFirstByEnabledAndUserOrderByIdDesc(true, user);
                        if (lastUserOrder != null) {
                            //为续费
                            vipOrderDO.setRenew(true);
                            //非续费情况下可以不填写
                            vipOrderDO.setLastVipOrder(lastUserOrder);
                        } else {
                            Logger.logger.error("系统异常，用户是会员但没有可用的上次订单");
                        }
                    } else {
                        //不是会员，为新开
                        vipOrderDO.setRenew(false);
                    }
                    vipOrderDO.setInviteCode(inviteCode);
                    vipOrderDO.setInviteUser(inviteUser);
                    //被邀请人才需要填写邀请码
                    if (giftType.equals(VipGiftType.invite)) {
                        user.setRegisterInviteCode(vipOrderDO.getInviteCode());
                        user.setRegisterInviteUser(vipOrderDO.getInviteUser());
                        //邀请人也要赠送会员，用户调换，邀请码不变
                        this.addVipOrder(VipGiftType.doInvite, inviteUser, inviteCode, user);
                    }
                }
                //如果续费
                if (vipOrderDO.getRenew()) {
                    VipOrderDO lastUserOrder = vipOrderDO.getLastVipOrder();
                    //上个订单的到期时间
                    vipOrderDO.setStartDate(lastUserOrder.getEndDate());
                    //开通月数
                    vipOrderDO.setTotalMonthNum(lastUserOrder.getTotalMonthNum() + vipSaleDO.getMonthNum());
                } else {
                    vipOrderDO.setStartDate(vipOrderDO.getCreateTime());
                    vipOrderDO.setTotalMonthNum(vipSaleDO.getMonthNum());
                }
                //后置相同内容，大于11个月就是年会员了
                if (vipOrderDO.getTotalMonthNum() > 11) {
                    vipOrderDO.setYearVipFlag(true);
                } else {
                    vipOrderDO.setYearVipFlag(false);
                }
                vipOrderDO.setEndDate(DateUtils.addMonths(vipOrderDO.getStartDate(), vipSaleDO.getMonthNum()));
                user.setVipFlag(true);
                user.setYearVipFlag(vipOrderDO.getYearVipFlag());
                user.setVipEndDate(vipOrderDO.getEndDate());
                //进行校验
                addVipOrderValidate(vipOrderDO, user);
                vipOrderRepository.save(vipOrderDO);
                userService.updateFaceContent(user);
            }
            userRepository.save(user);
        }
    }

    //校验顺序，前后，主次，主次没有意义只是定个标准
    public boolean addVipOrderValidate(VipOrderDO vipOrder, Integer userId) {
        if (vipOrder == null) {
            Logger.logger.error("订单信息为空");
        }
        if (user == null) {
            Logger.logger.error("订单信息为空");
        }
        if (vipOrder.getUser() == null) {
            Logger.logger.error("请填写用户");
        }
        if (!vipOrder.getUser().getId().equals(user.getId())) {
            Logger.logger.error("订单用户必须等于自己");
        }
        if (vipOrder.getEnabled() == null) {
            Logger.logger.error("请填写订单是否生效");
        }
        if (vipOrder.getCreateTime() == null) {
            Logger.logger.error("请填写创建时间");
        }
        if (vipOrder.getStartDate() == null) {
            Logger.logger.error("请填写会员起始时间");
        }
        if (vipOrder.getEndDate() == null) {
            Logger.logger.error("请填写会员到期时间");
        }
        if (vipOrder.getMonthNum() == null) {
            Logger.logger.error("请填写开通月数");
        }
        if (vipOrder.getTotalMonthNum() == null) {
            Logger.logger.error("请填写会员总月数");
        }
        if (vipOrder.getYearVipFlag() == null) {
            Logger.logger.error("请填写是否为年会员");
        }
        //本次花费多少钱
        if (vipOrder.getMoney() == null) {
            Logger.logger.error("请填写花费钱数");
        }
        if (vipOrder.getYearVipFlag()) {
            //年会员总月数不能小于12
            if (vipOrder.getTotalMonthNum() < 12) {
                Logger.logger.error("年会员总月数不能小于12");
            }
        }
        if (!vipOrder.getEnabled().equals(user.getVipFlag())) {
            Logger.logger.error("会员状态必须和用户会员状态相同");
        }
        if (!vipOrder.getYearVipFlag().equals(user.getYearVipFlag())) {
            Logger.logger.error("年会员状态必须和用户年会员状态相同");
        }
        //如果是续费
        VipOrderDO lastVipOrder = vipOrder.getLastVipOrder();
        if (vipOrder.getRenew()) {
            if (lastVipOrder == null) {
                Logger.logger.error("请填写上个订单");
            }
            //todo 有bug，不应该是上次的月数，应该是现在的时间到上次结束的时间的月数
            //续费会员，总月数必须等于之前的月数加上本次的月数
            if (!vipOrder.getTotalMonthNum().equals(lastVipOrder.getTotalMonthNum() + vipOrder.getMonthNum())) {
                Logger.logger.error("续费会员，总月数必须等于之前的月数加上本次的月数");
            }
            if (!vipOrder.getEndDate().equals(DateUtils.addMonths(vipOrder.getLastVipOrder().getEndDate(), vipOrder.getMonthNum()))) {
                Logger.logger.error("新订单结束日期必须等于老订单结束日期加上本次开通月数日期");
            }
        } else {
            if (lastVipOrder != null) {
                Logger.logger.error("不是续费，不能有上次订单");
            }
            //新充值
            if (!vipOrder.getCreateTime().equals(vipOrder.getStartDate())) {
                Logger.logger.error("新订单创建时间必须和开始时间相等");
            }
        }
        //必须有会员获取来源类型
        if (vipOrder.getActivityType() == null) {
            Logger.logger.error("请填写会员获取类型");
        }
        //如果充值获取会员
        if (vipOrder.getActivityType().equals(VipActivityType.pay)) {
            //来源类型为充值才需要
            if (vipOrder.getPayType() == null) {
                Logger.logger.error("请填写充值类型");
            }
            //充值类型才需要，订单号
            if (vipOrder.getOutOrderNo() == null) {
                Logger.logger.error("请填写第三方支付的订单号");
            }
            //充值的不能花小于1块钱
            if (vipOrder.getMoney() < 1) {
                Logger.logger.error("充值类型花费金额不能小于1元");
            }
        } else if (vipOrder.getActivityType().equals(VipActivityType.gift)) {
            //赠送获取类型
            if (!vipOrder.getMoney().equals(0)) {
                Logger.logger.error("赠送类型必须免费");
            }
            if (!vipOrder.getMonthNum().equals(1)) {
                Logger.logger.error("赠送会员必须为1个月");
            }
        } else {
            Logger.logger.error("系统异常，异常的活动类型");
        }

        //是否参加了活动
        if (vipOrder.getTakeSale() == null) {
            Logger.logger.error("请填写是否参加活动");
        }
        //如果参加了活动
        if (vipOrder.getTakeSale()) {
            //参加了活动但是没写活动信息
            if (vipOrder.getVipSale() == null) {
                Logger.logger.error("请填写活动信息，参加活动需要填写活动信息");
            } else {
                VipSaleDO vipOrderSale = vipOrder.getVipSale();
                //有活动信息
                //如果参加了活动，订单活动会员获取类型应该和活动会员获取类型相同
                if (!vipOrderSale.getActivityType().equals(vipOrder.getActivityType())) {
                    Logger.logger.error("请填写活动信息，参加活动需要填写活动信息");
                }
                if (!vipOrderSale.getMonthNum().equals(vipOrder.getMonthNum())) {
                    Logger.logger.error("活动会员数量必须等于订单会员数量");
                }
                //如果活动为注册类型
                if (vipOrderSale.getGiftType().equals(VipGiftType.register)) {
                    //如果是注册赠送，不能为续费
                    if (vipOrder.getRenew()) {
                        Logger.logger.error("注册类型不可为续费");
                    }
                    //注册类型，邀请码必须为空
                    if (StringUtils.isNotEmpty(vipOrder.getInviteCode())) {
                        Logger.logger.error("注册类型邀请码必须为空");
                    }
                    //注册类型，邀请码必须为空
                    if (vipOrder.getInviteUser() != null) {
                        Logger.logger.error("注册类型邀请用户必须为空");
                    }
                } else if (vipOrderSale.getGiftType().equals(VipGiftType.invite) || vipOrderSale.getGiftType().equals(VipGiftType.doInvite)) {
                    //邀请类型，邀请码不能为空
                    if (StringUtils.isEmpty(vipOrder.getInviteCode())) {
                        Logger.logger.error("邀请类型，邀请码不能为空");
                    }
                    //邀请类型，邀请用户不能为空
                    if (vipOrder.getInviteUser() == null) {
                        Logger.logger.error("邀请类型，邀请用户不能为空");
                    }
                    //如果自己邀请的别人
                    if (vipOrderSale.getGiftType().equals(VipGiftType.doInvite)) {
                        //自己邀请的用户的注册码必须等于自己的邀请码
                        if (!vipOrder.getInviteUser().getRegisterInviteCode().equals(user.getInviteCode())) {
                            Logger.logger.error("自己邀请的用户的注册码必须等于自己的邀请码");
                        }
                        if (!vipOrder.getInviteUser().getRegisterInviteUser().getId().equals(user.getId())) {
                            Logger.logger.error("自己邀请的用户的注册用户必须为自己");
                        }
                    } else {
                        //被邀请的
                        //订单的邀请码必须等于用户的注册码
                        if (!vipOrder.getInviteCode().equals(user.getRegisterInviteCode())) {
                            Logger.logger.error("订单的邀请码必须等于用户的注册码");
                        }
                        //订单的邀请用户必须等于用户的注册邀请用户
                        if (!vipOrder.getInviteUser().equals(user.getRegisterInviteUser())) {
                            Logger.logger.error("订单的邀请用户必须等于用户的注册邀请用户");
                        }
                        //订单的邀请码必须等于订单邀请用户的邀请码
                        if (!vipOrder.getInviteCode().equals(user.getRegisterInviteUser().getInviteCode())) {
                            Logger.logger.error("订单的邀请码必须等于订单邀请用户的邀请码");
                        }
                    }
                } else {
                    Logger.logger.error("会员赠送类型错误");
                }
            }
        }

        //需要校验各个值是否填写了
        //需要校验各个应该相等的值是否相等
        return true;
    }*/
}
