package com.socialuni.social.sdk.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.socialuni.social.common.api.utils.JsonUtil;
import com.socialuni.social.common.sdk.platform.WXPayNotifyResult;
import com.socialuni.social.common.sdk.platform.qq.QQPayResult;
import com.thoughtworks.xstream.XStream;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class TestXml {
    public static void main(String[] args) throws JsonProcessingException {
        String notifyResult = "<xml>\n" +
                "    <appid><![CDATA[wx0bf5fe9ceeab514c]]></appid>\n" +
                "    <bank_type><![CDATA[OTHERS]]></bank_type>\n" +
                "    <cash_fee><![CDATA[1]]></cash_fee>\n" +
                "    <fee_type><![CDATA[CNY]]></fee_type>\n" +
                "    <is_subscribe><![CDATA[N]]></is_subscribe>\n" +
                "    <mch_id><![CDATA[1577008821]]></mch_id>\n" +
                "    <nonce_str><![CDATA[47bd1c54880943fe906e59ee3e28fdb8]]></nonce_str>\n" +
                "    <openid><![CDATA[odvC15Dq73pl7BjrWhie7-vYqSNQ]]></openid>\n" +
                "    <out_trade_no><![CDATA[WIM69802023030721343303100000001]]></out_trade_no>\n" +
                "    <result_code><![CDATA[SUCCESS]]></result_code>\n" +
                "    <return_code><![CDATA[SUCCESS]]></return_code>\n" +
                "    <sign><![CDATA[AED2D793064EEF7BC5E8107C2DA90251]]></sign>\n" +
                "    <time_end><![CDATA[20230307213453]]></time_end>\n" +
                "    <total_fee>1</total_fee>\n" +
                "    <trade_type><![CDATA[JSAPI]]></trade_type>\n" +
                "    <transaction_id><![CDATA[4200001775202303070868583535]]></transaction_id>\n" +
                "</xml>\n";
        XStream xstream = new XStream();
        xstream.alias("xml", WXPayNotifyResult.class);
        Object qqPayResult1 = xstream.fromXML(notifyResult);
        String resultStr = JsonUtil.objectMapper.writeValueAsString(qqPayResult1);
        HashMap resultMap = JsonUtil.objectMapper.readValue(resultStr, HashMap.class);
        String sign = (String) resultMap.get("sign");
        Map<String, String> stringMap = new HashMap<>();
        resultMap.forEach((k, v) -> stringMap.put(String.valueOf(k), ObjectUtils.isEmpty(v) ? null : String.valueOf(v)));
        String out_trade_no = stringMap.get("out_trade_no");
        System.out.println(out_trade_no);
//        SocialuniPayCoinOrderDO rechargeOrderDO = socialuniRechargeOrderRepository.findFirstByOrderNo(inside_order_no);
    }

    private QQPayResult getQqPayResult(String sign, String inside_order_no, Integer total_fee, String transaction_id) {
        QQPayResult qqPayResult = new QQPayResult();
        qqPayResult.setReturn_code("SUCCESS");

        System.out.println(inside_order_no);

//        SocialuniPayCoinOrderDO rechargeOrderDO = socialuniRechargeOrderRepository.findFirstByOrderNo(inside_order_no);

        /*if (rechargeOrderDO == null) {
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
        //用户增加经验值和金币
        //userDO.setExperience(userDO.getExperience() + plusShell);
        log.info("接收" + rechargeOrderDO.getPayType() + "支付成功通知，订单号：" + inside_order_no + ",用户：" + userCoinDo.getUserId());

        return qqPayResult;*/
        return null;
    }
}
