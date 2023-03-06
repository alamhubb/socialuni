package com.socialuni.social.sdk.logic.domain.business;

import com.socialuni.social.common.api.constant.DateTimeType;
import com.socialuni.social.common.api.constant.PlatformType;
import com.socialuni.social.common.api.constant.SystemType;
import com.socialuni.social.common.api.exception.exception.SocialSystemException;
import com.socialuni.social.common.api.utils.IpUtil;
import com.socialuni.social.common.api.utils.RequestUtil;
import com.socialuni.social.common.api.utils.UUIDUtil;
import com.socialuni.social.common.sdk.facade.SocialuniRepositoryFacade;
import com.socialuni.social.sdk.constant.business.SocialuniAllowPayCoinAmountType;
import com.socialuni.social.sdk.constant.business.SocialuniPayStatus;
import com.socialuni.social.sdk.constant.business.SocialuniPayProviderType;
import com.socialuni.social.sdk.constant.socialuni.ContentStatus;
import com.socialuni.social.sdk.dao.DO.bussiness.SocialuniPayCoinOrderDO;
import com.socialuni.social.sdk.model.QO.business.SocialuniPayCoinQO;
import com.socialuni.social.sdk.model.QO.business.SocialuniCoinPayRO;
import com.socialuni.social.sdk.utils.DateUtils;
import com.socialuni.social.user.sdk.constant.UniappProviderType;
import com.socialuni.social.user.sdk.model.DO.SocialuniUserDo;
import com.socialuni.social.user.sdk.utils.AuthCodeUtil;
import com.socialuni.social.user.sdk.utils.QQUtil;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;
import com.socialuni.social.user.sdk.utils.WxUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;

@Component
@Slf4j
public class SocialuniPayCoinDomain {
    //充值金币
    public SocialuniCoinPayRO payCoin(SocialuniPayCoinQO socialuniRechargeCoinQO) {
        SocialuniUserDo user = SocialuniUserUtil.getMineUserNotNull();
        if (ContentStatus.violation.equals(user.getStatus())) {
            log.warn("系统异常，用户被封禁，应该无法进入此页面");
            throw new SocialSystemException("用户已被封禁，无法进行支付");
        }
        try {
            String payType = socialuniRechargeCoinQO.getProvider();
            Integer payAmount = socialuniRechargeCoinQO.getAmount();
            if (!SocialuniAllowPayCoinAmountType.amountList.contains(payAmount)) {
                throw new SocialSystemException("错误的充值金额");
            }
            //前台为元，后台为金币分
            Integer amount = payAmount * 100;
            String userIp = IpUtil.getIpAddr();

            SocialuniPayCoinOrderDO rechargeOrder = new SocialuniPayCoinOrderDO();

            rechargeOrder.setPlatform(RequestUtil.getPlatform());
            rechargeOrder.setSystem(RequestUtil.getSystem());
            rechargeOrder.setProvider(RequestUtil.getProvider());
            rechargeOrder.setUserId(user.getId());
            rechargeOrder.setStatus(SocialuniPayStatus.waitPay);
            rechargeOrder.setPayType(payType);
            rechargeOrder.setAmount(amount);
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
                prepay_id = QQUtil.postPayUrl(userIp, orderNo, total_feeStr);
                packageStr = "prepay_id=" + prepay_id;
                userPayResultVO.setPackage_alias(packageStr);
            }
            rechargeOrder = SocialuniRepositoryFacade.save(rechargeOrder);
            return userPayResultVO;
        } catch (IOException e) {
            e.printStackTrace();
            throw new SocialSystemException("重置异常，请联系客服");
        }
    }

    public static String generateOrderNo(SocialuniPayCoinOrderDO socialuniCoinRechargeOrderDO) {
        String system = socialuniCoinRechargeOrderDO.getSystem();
        String platform = socialuniCoinRechargeOrderDO.getPlatform();
        String payProvider = socialuniCoinRechargeOrderDO.getPayType();
        Date createTime = socialuniCoinRechargeOrderDO.getCreateTime();
        Integer orderId = socialuniCoinRechargeOrderDO.getId();

        //第二位，支付渠道，qq,wx
        StringBuilder orderNo = new StringBuilder();
        if (UniappProviderType.wx.equals(payProvider)) {
            orderNo.append("W");
        } else if (UniappProviderType.qq.equals(payProvider)) {
            orderNo.append("Q");
        } else {
            throw new SocialSystemException("错误的支付渠道");
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
        orderNo.append(DateUtils.getTimeStrFormat(createTime));

        String zeroFill = "0000000";
        String out_trade_no = zeroFill + orderId.toString();
        //从倒数第8位截取，截取后8位
        out_trade_no = out_trade_no.substring(out_trade_no.length() - 8);

        orderNo.append(out_trade_no);
        return orderNo.toString();
    }
}
