package com.socialuni.social.recharge.logic.domain;

import com.socialuni.social.common.api.constant.DateTimeType;
import com.socialuni.social.common.api.constant.PlatformType;
import com.socialuni.social.common.api.constant.SystemType;
import com.qingchi.qing.common.exception.base.exception.SocialParamsException;
import com.socialuni.social.common.api.exception.exception.SocialSystemException;
import com.qingchi.qing.utils.IpUtil;
import com.socialuni.social.common.api.utils.UUIDUtil;
import com.socialuni.social.common.sdk.dao.facede.SocialuniRepositoryFacade;
import com.socialuni.social.recharge.constant.SocialuniCoinOrderType;
import com.socialuni.social.recharge.constant.SocialuniOrderDetailType;
import com.socialuni.social.recharge.dao.DO.SocialuniCoinConsumLogDO;
import com.socialuni.social.recharge.dao.DO.SocialuniCoinOrderDO;
import com.socialuni.social.recharge.dao.repository.SocialuniCoinOrderRepository;
import com.socialuni.social.recharge.logic.entity.SocialuniCreateCoinOrderEntity;
import com.socialuni.social.recharge.model.SocialuniCoinInfoRO;
import com.socialuni.social.recharge.model.SocialuniCoinPayRO;
import com.socialuni.social.recharge.model.SocialuniPayCoinQO;
import com.socialuni.social.common.api.constant.SocialuniSupportProviderType;
import com.socialuni.social.sdk.constant.business.SocialuniAllowPayCoinAmountType;
import com.socialuni.social.sdk.constant.business.SocialuniPayStatus;
import com.socialuni.social.user.sdk.constant.SocialuniPayProviderType;
import com.socialuni.social.common.api.enumeration.ContentStatus;
import com.socialuni.social.recharge.dao.DO.SocialuniPayCoinOrderDO;
import com.socialuni.social.common.sdk.utils.SocialuniDateUtils;
import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
import com.socialuni.social.user.sdk.dao.DO.SocialuniUserCoinDo;
import com.socialuni.social.user.sdk.utils.AuthCodeUtil;
import com.socialuni.social.content.utils.QQUtil;
import com.socialuni.social.user.sdk.utils.SocialuniUserSocialCoinDOUtil;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;
import com.socialuni.social.content.utils.WxUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Date;

@Component
@Slf4j
public class SocialuniPayCoinDomain {
    @Resource
    SocialuniCreateCoinOrderEntity socialuniCreateCoinOrderEntity;

    @Transactional
    public void consumCoinByType(Long mineUserId, Long beUserId, String type) {

        SocialuniUserCoinDo socialuniUserCoinDo = SocialuniUserSocialCoinDOUtil.getNotNull(mineUserId);

        if (socialuniUserCoinDo.getCoin() < 10) {
            throw new SocialSystemException("金币不足");
        }

        //获取用户金币
        Integer mineUserCoin = socialuniUserCoinDo.getCoin();
        //获取联系方式需要的金币数量
        Integer consumNum = 10;

        //保存用户
        //用户消耗
        socialuniUserCoinDo.setCoin(mineUserCoin - consumNum);
        //保存用户消耗
        socialuniUserCoinDo = SocialuniUserSocialCoinDOUtil.update(socialuniUserCoinDo);

        //记录日志
        SocialuniCoinConsumLogDO socialuniCoinConsumLogDO = new SocialuniCoinConsumLogDO(mineUserId, beUserId);
        socialuniCoinConsumLogDO.setType(type);
        socialuniCoinConsumLogDO = SocialuniRepositoryFacade.save(socialuniCoinConsumLogDO);

        socialuniCreateCoinOrderEntity.createCoinOrderByOrderType(mineUserId, -consumNum, SocialuniCoinOrderType.consume, SocialuniOrderDetailType.msg, Long.valueOf(socialuniCoinConsumLogDO.getId()));
    }

    @Resource
    SocialuniCoinOrderRepository socialuniCoinOrderRepository;

    public SocialuniCoinInfoRO getUserCoinInfo() {
        Long mineUserId = SocialuniUserUtil.getMineUserIdNotNull();

        SocialuniCoinOrderDO socialuniCoinOrderDO = socialuniCoinOrderRepository.findFirstByUserIdAndDetailType(mineUserId, SocialuniOrderDetailType.sysGive);

        if (socialuniCoinOrderDO == null) {
            socialuniCreateCoinOrderEntity.createCoinOrderByOrderType(mineUserId, 100, SocialuniCoinOrderType.recharge, SocialuniOrderDetailType.sysGive, null);
        }

        SocialuniUserCoinDo socialuniUserSocialCoinDo = SocialuniUserSocialCoinDOUtil.getOrCreate(mineUserId);
        SocialuniCoinInfoRO socialuniCoinInfoRO = new SocialuniCoinInfoRO();
        socialuniCoinInfoRO.setCoinNum(socialuniUserSocialCoinDo.getCoin());
        return socialuniCoinInfoRO;
    }

    //充值金币
    public SocialuniCoinPayRO payCoin(SocialuniPayCoinQO socialuniRechargeCoinQO) {
        SocialuniUserDo user = SocialuniUserUtil.getMineUserNotNull();
        if (ContentStatus.violation.equals(user.getStatus())) {
            log.warn("系统异常，用户被封禁，应该无法进入此页面");
            throw new SocialSystemException("用户已被封禁，无法进行支付");
        }
        try {
            String payType = socialuniRechargeCoinQO.getProvider();
            Integer amount = socialuniRechargeCoinQO.getAmount();
            if (!SocialuniAllowPayCoinAmountType.amountList.contains(amount)) {
                throw new SocialSystemException("错误的充值金额");
            }
            //前台为元，后台为金币分
            Integer payAmount = amount * 100;
            String userIp = IpUtil.getIpAddr();

            SocialuniPayCoinOrderDO rechargeOrder = new SocialuniPayCoinOrderDO();

            rechargeOrder.setPlatform(SocialuniRequestUtil.getPlatform());
            rechargeOrder.setUseSystem(SocialuniRequestUtil.getSystem());
            rechargeOrder.setProvider(SocialuniRequestUtil.getProvider());
            rechargeOrder.setUserId(user.getUserId());
            rechargeOrder.setStatus(SocialuniPayStatus.waitPay);
            rechargeOrder.setPayType(payType);
            rechargeOrder.setAmount(payAmount);
            rechargeOrder.setCoinNum(payAmount);
            rechargeOrder = SocialuniRepositoryFacade.save(rechargeOrder);

            //'QISRand20200713005600548'+orderId
            String orderNo = generateOrderNo(rechargeOrder);
            rechargeOrder.setOrderNo(orderNo);


            //31个0，要求32位
            log.info("订单号:" + orderNo);
            String prepay_id;
            String packageStr;
            String total_feeStr = payAmount.toString();

            SocialuniCoinPayRO userPayResultVO = new SocialuniCoinPayRO();

            String platform = rechargeOrder.getPlatform();

            log.info("payTYpe:{}", payType);
            if (SocialuniPayProviderType.wx.equals(payType)) {
                prepay_id = WxUtil.postPayUrl(platform, userIp, orderNo, total_feeStr, user.getUserId());
                String nonceStr = UUIDUtil.getUUID();
                String dateStr = Long.toString(new Date().getTime() / DateTimeType.second);
                if (PlatformType.mp.equals(platform)) {
                    packageStr = "prepay_id=" + prepay_id;
                } else {
                    packageStr = "Sign=WXPay";
                    userPayResultVO.setPrepayid(prepay_id);
                }
                String sign = WxUtil.getFrontPaySign(platform, prepay_id, packageStr, nonceStr, dateStr);
                userPayResultVO.setPaySign(sign);
                userPayResultVO.setPackage_alias(packageStr);
                userPayResultVO.setNonceStr(nonceStr);
                userPayResultVO.setTimeStamp(dateStr);

                rechargeOrder.setOutSysSign(sign);
            } else {
                log.info("orderNo:{}", orderNo);
                String mweb_url = QQUtil.postWxPayUrl(userIp, orderNo, total_feeStr);
//                packageStr = "prepay_id=" + prepay_id;
                userPayResultVO.setMweb_url(mweb_url);
            }
            rechargeOrder = SocialuniRepositoryFacade.save(rechargeOrder);
            return userPayResultVO;
        } catch (IOException e) {
            e.printStackTrace();
            throw new SocialSystemException("重置异常，请联系客服");
        }
    }

    public static String generateOrderNo(SocialuniPayCoinOrderDO socialuniCoinRechargeOrderDO) {
        String system = socialuniCoinRechargeOrderDO.getUseSystem();
        String platform = socialuniCoinRechargeOrderDO.getPlatform();
        String payProvider = socialuniCoinRechargeOrderDO.getPayType();
        Date createTime = socialuniCoinRechargeOrderDO.getCreateTime();
        Integer orderId = socialuniCoinRechargeOrderDO.getId();

        //第二位，支付渠道，qq,wx
        StringBuilder orderNo = new StringBuilder();
        if (SocialuniSupportProviderType.wx.equals(payProvider)) {
            orderNo.append("W");
        } else if (SocialuniSupportProviderType.qq.equals(payProvider)) {
            orderNo.append("Q");
        } else {
            throw new SocialParamsException("错误的支付渠道");
        }
        //第二位，设备平台，ios，android
        if (SystemType.ios.equals(system)) {
            orderNo.append("I");
        } else if (SystemType.android.equals(system)) {
            orderNo.append("A");
        } else {
            throw new SocialSystemException("错误的支付系统");
        }
        //第三位，设备平台，ios，android
        if (PlatformType.app.equals(platform)) {
            orderNo.append("A");
        } else if (PlatformType.mp.equals(platform)) {
            orderNo.append("M");
        } else if (PlatformType.h5.equals(platform)) {
            orderNo.append("H");
        } else {
            throw new SocialSystemException("错误的支付平台");
        }

        //4位随机字符串，+17位时间戳，24+8位订单id
        orderNo.append(AuthCodeUtil.getAuthCode());
        orderNo.append(SocialuniDateUtils.getTimeStrFormat(createTime));

        String zeroFill = "0000000";
        String out_trade_no = zeroFill + orderId.toString();
        //从倒数第8位截取，截取后8位
        out_trade_no = out_trade_no.substring(out_trade_no.length() - 8);

        orderNo.append(out_trade_no);
        return orderNo.toString();
    }
}
