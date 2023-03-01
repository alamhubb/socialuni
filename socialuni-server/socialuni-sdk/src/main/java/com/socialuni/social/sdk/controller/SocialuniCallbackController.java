package com.socialuni.social.sdk.controller;


import com.socialuni.social.common.api.utils.JsonUtil;
import com.socialuni.social.common.sdk.facade.SocialuniRepositoryFacade;
import com.socialuni.social.sdk.constant.business.SocialuniPayStatus;
import com.socialuni.social.sdk.dao.DO.bussiness.SocialuniCoinOrderDO;
import com.socialuni.social.sdk.dao.DO.bussiness.SocialuniPayCoinOrderDO;
import com.socialuni.social.sdk.dao.factory.SocialuniCoinOrderFactory;
import com.socialuni.social.sdk.dao.repository.bussiness.SocialuniPayOrderRepository;
import com.socialuni.social.sdk.dao.utils.user.SocialuniUserSocialCoinDOUtil;
import com.socialuni.social.user.sdk.model.DO.SocialuniUserCoinDo;
import com.socialuni.social.user.sdk.platform.WXPayNotifyResult;
import com.socialuni.social.user.sdk.platform.qq.QQPayResult;
import com.socialuni.social.user.sdk.utils.WxUtil;
import com.thoughtworks.xstream.XStream;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("socialuni/callback")
@RestController
@Slf4j
public class SocialuniCallbackController {
    @Resource
    SocialuniPayOrderRepository socialuniRechargeOrderRepository;

    @RequestMapping(value = "wxPayNotify", produces = MediaType.APPLICATION_XML_VALUE)
    public QQPayResult wxPayNotify(@RequestBody String notifyResult) throws IOException {
        log.info("接收到支付成功通知：" + notifyResult);

        //根据结果字符串生成对象
        XStream xstream = new XStream();
        xstream.alias("xml", WXPayNotifyResult.class);
        Object qqPayResult1 = xstream.fromXML(notifyResult);
        String resultStr = JsonUtil.objectMapper.writeValueAsString(qqPayResult1);
        HashMap resultMap = JsonUtil.objectMapper.readValue(resultStr, HashMap.class);
        String sign = (String) resultMap.get("sign");

        resultMap.remove("sign");
        Map<String, String> stringMap = new HashMap<>();

        resultMap.forEach((k, v) -> stringMap.put(String.valueOf(k), ObjectUtils.isEmpty(v) ? null : String.valueOf(v)));

        String validateSign = WxUtil.getSignToken(stringMap);

        log.info("校验签名为：" + validateSign);

        String out_trade_no = stringMap.get("out_trade_no");

        if (!validateSign.equals(sign)) {
            QQPayResult qqPayResult = new QQPayResult();
            log.error("接收支付成功通知，订单号：" + out_trade_no + ",订单签名不同异常,:" + sign);
            qqPayResult.setReturn_code("ERROR");
            qqPayResult.setReturn_msg("订单异常");
            return qqPayResult;
        }
        Integer total_fee = Integer.parseInt(stringMap.get("total_fee"));
        String transaction_id = stringMap.get("transaction_id");

        /*xstream.alias("xml", WXPayNotifyResult.class);
        Object qqPayResult1 = xstream.fromXML(notifyResult);
        String resultStr = JsonUtil.objectMapper.writeValueAsString(qqPayResult1);
        WXPayNotifyResult result = JsonUtil.objectMapper.readValue(resultStr, WXPayNotifyResult.class);*/
        return getQqPayResult(sign, out_trade_no, total_fee, transaction_id);
    }


    private QQPayResult getQqPayResult(String sign, String inside_order_no, Integer total_fee, String transaction_id) {
        QQPayResult qqPayResult = new QQPayResult();
        qqPayResult.setReturn_code("SUCCESS");

        SocialuniPayCoinOrderDO rechargeOrderDO = socialuniRechargeOrderRepository.findFirstByOrderNo(inside_order_no);

        if (rechargeOrderDO != null) {
            log.error("接收支付成功通知，订单号：" + inside_order_no + ",订单不存在");
            qqPayResult.setReturn_code("ERROR");
            qqPayResult.setReturn_msg("订单不存在");
            return qqPayResult;
        }

        Integer verifyMoney = rechargeOrderDO.getAmount();
        if (!verifyMoney.equals(total_fee)) {
            log.error("接收支付成功通知，订单号：" + inside_order_no + ",订单金额不相等异常,:" + total_fee);
            qqPayResult.setReturn_code("ERROR");
            qqPayResult.setReturn_msg("订单异常");
            return qqPayResult;
        }

        if (!SocialuniPayStatus.waitPay.equals(rechargeOrderDO.getStatus())) {
            log.error("接收支付成功通知，订单号：" + inside_order_no + ",订单失效");
            qqPayResult.setReturn_code("ERROR");
            qqPayResult.setReturn_msg("订单已失效");
            return qqPayResult;
        }
        rechargeOrderDO.setStatus(SocialuniPayStatus.success);
        rechargeOrderDO.setAmount(total_fee);
        rechargeOrderDO.setOutSysOrderNo(transaction_id);

        SocialuniUserCoinDo userCoinDo = SocialuniUserSocialCoinDOUtil.getNotNull(rechargeOrderDO.getUserId());
        //用户现有shell
        //增加的数量
        Integer plusShell = total_fee;
        userCoinDo.setCoin(userCoinDo.getCoin() + plusShell);

        //创建金币订单
        SocialuniCoinOrderDO socialuniCoinOrderDO = SocialuniCoinOrderFactory.createCoinOrderDOByRechargeSuccess(userCoinDo.getUserId(), plusShell, rechargeOrderDO.getId());
        //保存coin订单
        SocialuniRepositoryFacade.save(socialuniCoinOrderDO);
        //更新用户的coin数量
        SocialuniUserSocialCoinDOUtil.save(userCoinDo);
        //更新用户充值订单信息
        SocialuniRepositoryFacade.save(rechargeOrderDO);
        //用户现有经验值
        //用户增加经验值和贝壳
        //userDO.setExperience(userDO.getExperience() + plusShell);
        log.info("接收" + rechargeOrderDO.getPayType() + "支付成功通知，订单号：" + inside_order_no + ",用户：" + userCoinDo.getUserId());

        return qqPayResult;
    }

}
