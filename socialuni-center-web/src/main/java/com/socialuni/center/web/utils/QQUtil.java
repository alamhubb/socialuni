package com.socialuni.center.web.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.socialuni.center.web.model.PushMsgDTO;
import com.socialuni.center.web.platform.PushMsgErrCode;
import com.socialuni.center.web.platform.WxErrCode;
import com.socialuni.center.web.platform.qq.HttpImgCheckResult;
import com.socialuni.center.web.platform.qq.QQConst;
import com.socialuni.center.web.platform.qq.QQPayResult;
import com.socialuni.center.web.platform.weixin.HttpResult;
import com.socialuni.center.web.platform.weixin.token.WxTokenResult;
import com.socialuni.center.web.utils.common.RestUtil;
import com.socialuni.social.constant.DateTimeType;
import com.socialuni.center.web.model.DO.NotifyDO;
import com.socialuni.social.exception.SocialBusinessException;
import com.socialuni.social.exception.SocialParamsException;
import com.socialuni.social.utils.UUIDUtil;
import com.thoughtworks.xstream.XStream;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;

/**
 * @author qinkaiyuan
 * @date 2019-10-24 13:30
 */
@Component
@Slf4j
public class QQUtil {
    private static ObjectMapper objectMapper;

    @Resource
    public void setObjectMapper(ObjectMapper objectMapper) {
        QQUtil.objectMapper = objectMapper;
    }

    private static String qq_mp_id;
    private static String qq_mp_secret;
    private static String qq_merchant_id;
    private static String qq_merchant_key;

    @Value("${socialuni.provider.qq.qq-mp-id}")
    public void setQq_mp_id(String qq_mp_id) {
        QQUtil.qq_mp_id = qq_mp_id;
    }

    @Value("${socialuni.provider.qq.qq-mp-secret}")
    public void setQq_mp_secret(String qq_mp_secret) {
        QQUtil.qq_mp_secret = qq_mp_secret;
    }

    @Value("${socialuni.provider.qq.qq-merchant-id}")
    public void setQq_merchant_id(String qq_merchant_id) {
        QQUtil.qq_merchant_id = qq_merchant_id;
    }

    @Value("${socialuni.provider.qq.qq-merchant-key}")
    public void setQq_merchant_key(String qq_merchant_key) {
        QQUtil.qq_merchant_key = qq_merchant_key;
    }


    /**
     * 获取微信token
     *
     * @return
     */
    public static String getAccessToken() {
        /*ResponseEntity<TokenDTO> responseEntity = restTemplate.getForEntity(tokenUrl + "getQQSession", TokenDTO.class);
        return Objects.requireNonNull(responseEntity.getBody()).getAccessToken();*/
        WxTokenResult qqToken = QQConst.getQQToken();
        if (qqToken.tokenIsValid()) {
            return qqToken.getAccess_token();
        }
        return refreshAccessToken();
    }

    /*public static String refreshAccessToken() {
        ResponseEntity<TokenDTO> responseEntity = restTemplate.getForEntity(tokenUrl + "refreshQQSession", TokenDTO.class);
        return Objects.requireNonNull(responseEntity.getBody()).getAccessToken();
    }  */

    public static String refreshAccessToken() {
        String url = QQConst.qqTokenUrl+"appid=" + qq_mp_id + "&secret=" + qq_mp_secret;
        Date curDate = new Date();
        log.info("从qq获取认证信息:{}", url);
        ResponseEntity<WxTokenResult> responseEntity = RestUtil.restTemplate().getForEntity(url, WxTokenResult.class);
        WxTokenResult qqToken = responseEntity.getBody();
        if (qqToken == null || qqToken.hasError()) {
            log.info("获取qq认证信息失败:{}", qqToken);
            return null;
        }
        qqToken.setCreateTime(curDate);
        //当前时间加上过期时间 ，减去5分钟余量
        qqToken.setExpiresTime(new Date(curDate.getTime() + (long) qqToken.getExpires_in() * DateTimeType.second - 5L * DateTimeType.minute));
        log.info("获取qq认证信息成功:{}", qqToken);
        QQConst.setQQToken(qqToken);
        return qqToken.getAccess_token();
    }

    private static HttpResult checkContentQQSecPost(String content) {
        HashMap<String, Object> postData = new HashMap<>();
        postData.put("content", content);
        String url = QQConst.qq_msg_sec_check_url + QQUtil.getAccessToken();
        return RestUtil.restTemplate().postForEntity(url, postData, HttpResult.class).getBody();
    }

    /**
     * 校验内容是否违规
     *
     * @param content
     */
    public static HttpResult checkContentQQSec(String content) {
        HttpResult qqResult = checkContentQQSecPost(content);
        assert qqResult != null;
        if (qqResult.hasError()) {
            if (WxErrCode.token_invalid.equals(qqResult.getErrcode())) {
                QQUtil.refreshAccessToken();
                qqResult = checkContentQQSecPost(content);
            }
        }
        return qqResult;
    }

    public static void checkImgSecPost(String imgUrl) {
        try {
            //获取文件后缀名
            String[] suffix = imgUrl.split("\\.");
            //不包含.
            String fileTypeName;
            if (suffix.length < 2) {
                fileTypeName = ".jpg";
            } else {
                fileTypeName = "." + suffix[suffix.length - 1];
            }
            File file = File.createTempFile("checkImgTemp", fileTypeName);

            Thumbnails.of(new URL(imgUrl)).size(700, 1300).toFile(file);

            FileSystemResource fileResource  = new FileSystemResource(file);

            // 封装参数，千万不要替换为Map与HashMap，否则参数无法传递
            MultiValueMap<String, Object> paramMap = new LinkedMultiValueMap<>();
            paramMap.add("appid", qq_mp_id);
            paramMap.add("media", fileResource);
            // 2、使用postForEntity请求接口
            HttpHeaders headers = new HttpHeaders();
            HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<>(paramMap, headers);

            String imgCheckUrl = QQConst.qq_img_sec_check_url + QQUtil.getAccessToken();

            HttpImgCheckResult httpResult = RestUtil.getFileRestTemplate().postForEntity(imgCheckUrl, paramMap, HttpImgCheckResult.class).getBody();
            if (httpResult != null) {
                log.info(httpResult.getErrMsg());
                log.info(httpResult.getErrCode().toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new SocialBusinessException("上传照片失败");
        }
    }


    public static void qqPushMsgCommon(String openId, String platform, PushMsgDTO pushMsgDTO, NotifyDO notify) {
        //如果评论
        //相同
        String accessToken = QQUtil.getAccessToken();

        pushMsgDTO.setAccess_token(accessToken);
        pushMsgDTO.setTouser(openId);

//         = new PushMsgDTO(accessToken, openId, template_id, page, data, emphasis_keyword)
        String url = QQConst.push_msg_url + accessToken;
        HttpResult result = RestUtil.restTemplate().postForEntity(url, pushMsgDTO, HttpResult.class).getBody();
        PushMessageUtils.savePushMsg(notify, pushMsgDTO, result, platform);
        if (result != null && result.hasError()) {
            Integer errCode = result.getErrcode();
            if (PushMsgErrCode.token_expired.equals(errCode)) {
                //刷新token后重试
                accessToken = QQUtil.refreshAccessToken();
                pushMsgDTO.setAccess_token(accessToken);
                url = QQConst.push_msg_url + accessToken;
                result = RestUtil.restTemplate().postForEntity(url, pushMsgDTO, HttpResult.class).getBody();
                PushMessageUtils.savePushMsg(notify, pushMsgDTO, result, platform);
            }
        }
    }


    //发起支付
    public static String postPayUrl(String deviceIp, String orderNo, String total_feeStr) throws IOException {
        Map<String, String> map = new HashMap<>();
        map.put("appid", qq_mp_id);
        String attachStr = "qingchiapp";
        map.put("attach", attachStr);
        String bodystr = "qingchiapp";
        map.put("body", bodystr);

        map.put("fee_type", "CNY");
        map.put("mch_id", qq_merchant_id);
        String nonce_strstr = UUIDUtil.getUUID();
        map.put("nonce_str", nonce_strstr);
        map.put("notify_url", QQConst.qq_pay_result_notify_url);
        map.put("out_trade_no", orderNo);
        map.put("spbill_create_ip", deviceIp);
        map.put("total_fee", total_feeStr);
        map.put("trade_type", "MINIAPP");
        HttpHeaders requestHeader = new HttpHeaders();
        requestHeader.setContentType(MediaType.APPLICATION_XML);
        StringBuilder xmlString = new StringBuilder();
        String appid = "<appid>" + qq_mp_id + "</appid>";
        String attach = "<attach>" + attachStr + "</attach>";
        String body = "<body>" + bodystr + "</body>";
        String mch_id = "<mch_id>" + qq_merchant_id + "</mch_id>";
        String nonce_str = "<nonce_str>" + nonce_strstr + "</nonce_str>";

        String sign = getSignToken(map);
        String signXmlStr = "<sign>" + sign + "</sign>";

        String notify = "<notify_url>" + QQConst.qq_pay_result_notify_url + "</notify_url>";
        String out_trade_no = "<out_trade_no>" + orderNo + "</out_trade_no>";
        String spbill_create_ip = "<spbill_create_ip>" + deviceIp + "</spbill_create_ip>";
        String total_fee = "<total_fee>" + total_feeStr + "</total_fee>";
        xmlString.append("<xml>")
                .append(appid)
                .append(attach)
                .append(body)
                .append("<fee_type>CNY</fee_type>")
                .append(mch_id)
                .append(nonce_str)
                .append(notify)
                .append(out_trade_no)
                .append(spbill_create_ip)
                .append(total_fee)
                .append("<trade_type>MINIAPP</trade_type>")
                .append(signXmlStr)
                .append("</xml>");
        // 创建 HttpEntity
        HttpEntity<String> requestEntity = new HttpEntity<>(xmlString.toString(), requestHeader);
        ResponseEntity<String> responseEntity = RestUtil.restTemplate().postForEntity(QQConst.qq_pay_url, requestEntity, String.class);
        XStream xstream = new XStream();
        xstream.alias("xml", QQPayResult.class);
        Object qqPayResult = xstream.fromXML(responseEntity.getBody());
        String result = objectMapper.writeValueAsString(qqPayResult);
        log.info(result);
        QQPayResult result1 = objectMapper.readValue(result, QQPayResult.class);
        if (result1.hasError()) {
            throw new SocialParamsException("支付失败");
        }
        return result1.getPrepay_id();
    }


    /**
     * Description:MD5工具生成token
     *
     * @param value
     * @return
     */
    public String getMD5Value(String value) {
        try {
            /*MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            byte[] md5ValueByteArray = messageDigest.digest(value.getBytes());
            BigInteger bigInteger = new BigInteger(1 , md5ValueByteArray);
            return bigInteger.toString(16).toUpperCase();*/
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 生成签名
     *
     * @param map
     * @return
     */
    public static String getSignToken(Map<String, String> map) {
        String result;
        List<Map.Entry<String, String>> infoIds = new ArrayList<>(map.entrySet());
        // 对所有传入参数按照字段名的 ASCII 码从小到大排序（字典序）
        infoIds.sort(Map.Entry.comparingByKey());
        // 构造签名键值对的格式
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> item : infoIds) {
            String itemKey = item.getKey();
            String itemVal = item.getValue();
            if (StringUtils.isNotEmpty(itemKey) && StringUtils.isNotEmpty(itemVal)) {
                sb.append(itemKey).append("=").append(itemVal).append("&");
            }
        }
        //key为密钥
        result = sb.toString() + "key=" + qq_merchant_key;
        //进行MD5加密
        result = DigestUtils.md5Hex(result).toUpperCase();
        return result;
    }
}
