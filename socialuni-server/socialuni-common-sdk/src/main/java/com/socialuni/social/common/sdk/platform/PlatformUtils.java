package com.socialuni.social.common.sdk.platform;

import org.springframework.stereotype.Component;

/**
 * @author qinkaiyuan
 * @date 2020-03-21 21:45
 */
@Component
public class PlatformUtils {
    /*private static VipOrderRepository vipOrderRepository;

    @Resource
    public void setVipOrderRepository(VipOrderRepository vipOrderRepository) {
        PlatformUtils.vipOrderRepository = vipOrderRepository;
    }

    public static UserPayResultVO pay(String platform, String provider, String payType, Integer amount, UserDO user, HttpServletRequest request) throws IOException {
        String userIp = IpUtil.getIpAddr(request);

        VipOrderDO vipOrderDO = new VipOrderDO();
        if (ProviderType.wx.equals(provider)) {
            vipOrderDO.setChannel(provider);
        } else {
            vipOrderDO.setChannel("qq");
        }

        Date curDate = new Date();
        vipOrderDO.setPlatform(platform);
        vipOrderDO.setUserId(user.getId());
        vipOrderDO.setCreateTime(curDate);
        vipOrderDO.setStatus(CommonStatus.waitPay);
        vipOrderDO.setPayType(payType);
        vipOrderDO.setAmount(amount);
        vipOrderDO = vipOrderRepository.save(vipOrderDO);
        Integer orderId = vipOrderDO.getId();

        //'QISRand20200713005600548'+orderId
        String orderNo = generateOrderNo(provider, platform, payType, curDate, orderId);
        vipOrderDO.setOrderNo(orderNo);


        //31个0，要求32位
        log.info("订单号:" + orderNo);
        String prepay_id;
        String packageStr;
        String total_feeStr = amount.toString();

        UserPayResultVO userPayResultVO = new UserPayResultVO();

        if (ProviderType.wx.equals(provider)) {
            prepay_id = WxUtil.postPayUrl(platform, userIp, orderNo, total_feeStr, user.getId());
            String nonceStr = TokenUtils.getUUID();
            String dateStr = Long.toString(new Date().getTime() / CommonConst.second);
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

        } else {
            prepay_id = QQUtil.postPayUrl(userIp, orderNo, total_feeStr);
            packageStr = "prepay_id=" + prepay_id;
            userPayResultVO.setPackage_alias(packageStr);
        }
        vipOrderRepository.save(vipOrderDO);
        return userPayResultVO;
    }


    //'QISRand20200713005600548'+orderId
    public static String generateOrderNo(String provider, String platform, String payType, Date date, Integer orderId) {
        //第一位，支付渠道，qq,wx
        StringBuilder orderNo = new StringBuilder();
        if (ProviderType.wx.equals(provider)) {
            orderNo.append("W");
        } else {
            orderNo.append("Q");
        }
        //第二位，设备平台，ios，android，mp，h5
        if (PlatformType.app.equals(platform)) {
            orderNo.append("A");
        } else {
            orderNo.append("M");
        }
        //第3位，购买货物类型，shell，vip
        if (PayType.shell.equals(payType)) {
            orderNo.append("S");
        } else {
            orderNo.append("V");
        }
        //4567位随机字符串，+17位时间戳，24+8位订单id
        orderNo.append(AuthCodeUtil.getAuthCode());
        orderNo.append(DateUtils.getTimeStrFormat(date));

        String zeroFill = "0000000";
        String out_trade_no = zeroFill + orderId.toString();
        //从倒数第8位截取，截取后8位
        out_trade_no = out_trade_no.substring(out_trade_no.length() - 8);

        orderNo.append(out_trade_no);
        return orderNo.toString();
    }*/
}
