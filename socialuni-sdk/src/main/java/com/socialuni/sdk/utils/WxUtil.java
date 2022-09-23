package com.socialuni.sdk.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.socialuni.sdk.constant.platform.PlatformType;
import com.socialuni.sdk.constant.platform.UniappProviderType;
import com.socialuni.sdk.model.PushMsgDTO;
import com.socialuni.sdk.logic.platform.WxErrCode;
import com.socialuni.sdk.logic.platform.qq.QQPayResult;
import com.socialuni.sdk.logic.platform.weixin.HttpResult;
import com.socialuni.sdk.logic.platform.weixin.WxConst;
import com.socialuni.sdk.logic.platform.weixin.token.WxTokenResult;
import com.socialuni.sdk.utils.common.RestUtil;
import com.socialuni.sdk.constant.socialuni.DateTimeType;
import com.socialuni.sdk.dao.DO.NotifyDO;
import com.socialuni.sdk.dao.DO.user.SocialUserAccountDO;
import com.socialuni.social.common.exception.exception.SocialBusinessException;
import com.socialuni.social.common.exception.exception.SocialSystemException;
import com.socialuni.sdk.dao.repository.user.SocialUserAccountRepository;
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
public class WxUtil {
    private static ObjectMapper objectMapper;
    private static SocialUserAccountRepository socialUserAccountRepository;

    @Resource
    public void setObjectMapper(ObjectMapper objectMapper) {
        WxUtil.objectMapper = objectMapper;
    }

    @Resource
    public void setAccountRepository(SocialUserAccountRepository socialUserAccountRepository) {
        WxUtil.socialUserAccountRepository = socialUserAccountRepository;
    }

    private static String wx_mp_id;
    private static String wx_app_id;
    private static String wx_mp_secret;

    @Value("${socialuni.provider.wx.wx-app-id}")
    public void setWx_app_id(String wx_app_id) {
        WxUtil.wx_app_id = wx_app_id;
    }

    @Value("${socialuni.provider.wx.wx-mp-id}")
    public void setWx_mp_id(String wx_mp_id) {
        WxUtil.wx_mp_id = wx_mp_id;
    }

    @Value("${socialuni.provider.wx.wx-mp-secret}")
    public void setWx_mp_secret(String wx_mp_secret) {
        WxUtil.wx_mp_secret = wx_mp_secret;
    }

    /**
     * 获取微信token
     *
     * @return
     */
    /*public static String getAccessToken() {
        ResponseEntity<TokenDTO> responseEntity = restTemplate.getForEntity(tokenUrl + "getWxSession", TokenDTO.class);
        return Objects.requireNonNull(responseEntity.getBody()).getAccessToken();
    }*/

    /**
     * 获取微信token
     *
     * @return
     */
    public static String getAccessToken() {
        WxTokenResult wxToken = WxConst.getWxToken();
        if (wxToken.tokenIsValid()) {
            return wxToken.getAccess_token();
        }
        return refreshAccessToken();
    }

    /*public static String refreshAccessToken() {
        ResponseEntity<TokenDTO> responseEntity = restTemplate.getForEntity(tokenUrl + "refreshWxSession", TokenDTO.class);
        return Objects.requireNonNull(responseEntity.getBody()).getAccessToken();
    }*/


    public static String refreshAccessToken() {
        String appIDAndAppSecret = "appid=" + wx_mp_id + "&secret=" + wx_mp_secret;
        String url = WxConst.wxTokenUrl + appIDAndAppSecret;
        Date curDate = new Date();
        log.info("从微信获取认证信息:{}", url);
        ResponseEntity<WxTokenResult> responseEntity = RestUtil.getXmlRestTemplate().getForEntity(url, WxTokenResult.class);
        WxTokenResult wxToken = responseEntity.getBody();
        if (wxToken == null || wxToken.hasError()) {
            log.info("获取微信认证信息失败:{}", wxToken);
            throw new SocialSystemException("获取微信认证失败");
        }
        wxToken.setCreateTime(curDate);
        //当前时间加上过期时间 ，减去5分钟余量
        wxToken.setExpiresTime(new Date(curDate.getTime() + (long) wxToken.getExpires_in() * DateTimeType.second - 5L * DateTimeType.minute));
        log.info("获取微信认证信息成功:{}", wxToken);
        WxConst.setWxToken(wxToken);
        return wxToken.getAccess_token();
    }

    /**
     * 校验内容是否违规
     *
     * @param content
     */
    public static HttpResult checkTextWxSec(String content) {
        if (StringUtils.isEmpty(wx_mp_secret) || StringUtils.isEmpty(content)) {
            return new HttpResult();
        }
        HttpResult result = checkContentWxSecPost(content);
        assert result != null;
        if (result.hasError()) {
            if (WxErrCode.token_invalid.equals(result.getErrcode())) {
                WxUtil.refreshAccessToken();
                result = checkContentWxSecPost(content);
            }
        }
        return result;
    }

    private static HttpResult checkContentWxSecPost(String content) {
        HashMap<String, Object> postData = new HashMap<>();
        postData.put("content", content);
        String url = WxConst.wx_msg_sec_check_url + WxUtil.getAccessToken();
        return RestUtil.getXmlRestTemplate().postForEntity(url, postData, HttpResult.class).getBody();
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

            FileSystemResource fileResource = new FileSystemResource(file);

            // 封装参数，千万不要替换为Map与HashMap，否则参数无法传递
            MultiValueMap<String, Object> paramMap = new LinkedMultiValueMap<>();
            paramMap.add("media", fileResource);

            // 2、使用postForEntity请求接口
            String imgCheckUrl = WxConst.wx_img_sec_check_url + WxUtil.getAccessToken();

            HttpResult httpResult = RestUtil.getDefaultRestTemplate().postForEntity(imgCheckUrl, paramMap, HttpResult.class).getBody();
            if (httpResult != null) {
                log.info(httpResult.getErrmsg());
                log.info(httpResult.getErrcode().toString());
            }
        } catch (Exception e) {
            throw new SocialBusinessException("上传照片失败");
        }
    }


    public static void wxPushMsgCommon(String openId, String platform, PushMsgDTO pushMsgDTO, NotifyDO notify) {
        String accessToken = WxUtil.getAccessToken();
        pushMsgDTO.setAccess_token(accessToken);
        pushMsgDTO.setTouser(openId);
        //如果评论
        String url = WxConst.push_msg_url + accessToken;
        HttpResult result = RestUtil.getXmlRestTemplate().postForEntity(url, pushMsgDTO, HttpResult.class).getBody();
        PushMessageUtils.savePushMsg(notify, pushMsgDTO, result, platform);
    }

    private static String wx_merchant_id;
    private static String wx_merchant_key;

    @Value("${socialuni.provider.wx.wx-merchant-id}")
    public void setWx_merchant_id(String wx_merchant_id) {
        WxUtil.wx_merchant_id = wx_merchant_id;
    }

    @Value("${socialuni.provider.wx.wx-merchant-key}")
    public void setWx_merchant_key(String wx_merchant_key) {
        WxUtil.wx_merchant_key = wx_merchant_key;
    }

    //发起支付
    public static String postPayUrl(String platform, String deviceIp, String orderNo, String total_feeStr, Integer userId) throws IOException {
        SocialUserAccountDO socialUserAccountDO = socialUserAccountRepository.findByProviderAndUserId(UniappProviderType.wx, userId);

        String trade_type = WxConst.mp_pay_trade_type;
        String appId = wx_mp_id;

        Map<String, String> map = new HashMap<>();

        //不为小程序改为app
        if (!PlatformType.mp.equals(platform)) {
            trade_type = WxConst.app_pay_trade_type;
            appId = wx_app_id;
        }
        map.put("appid", appId);
        //指定微信
        String openId = socialUserAccountDO.getMpOpenId();
        //只有为小程序才有这行
        if (PlatformType.mp.equals(platform)) {
            map.put("openid", openId);
        }
        String bodystr = "qingchiapp";
        map.put("body", bodystr);
        map.put("mch_id", wx_merchant_id);
        String nonce_strstr = UUIDUtil.getUUID();
        map.put("nonce_str", nonce_strstr);
        map.put("notify_url", WxConst.wx_pay_result_notify_url);

        map.put("out_trade_no", orderNo);
        map.put("spbill_create_ip", deviceIp);
        //10元
        map.put("total_fee", total_feeStr);
        map.put("trade_type", trade_type);

        HttpHeaders requestHeader = new HttpHeaders();
        requestHeader.setContentType(MediaType.APPLICATION_XML);
        StringBuilder xmlString = new StringBuilder();

        String appIdStr = "<appid>" + appId + "</appid>";
        String openIdXml = "";
        if (PlatformType.mp.equals(platform)) {
            openIdXml = "<openid>" + openId + "</openid>";
        }
        String body = "<body>" + bodystr + "</body>";
        String mch_id = "<mch_id>" + wx_merchant_id + "</mch_id>";
        String nonce_str = "<nonce_str>" + nonce_strstr + "</nonce_str>";

        String sign = getSignToken(map);
        String signStr = "<sign>" + sign + "</sign>";

        String notify = "<notify_url>" + WxConst.wx_pay_result_notify_url + "</notify_url>";
        String out_trade_no_xml = "<out_trade_no>" + orderNo + "</out_trade_no>";
        String spbill_create_ip = "<spbill_create_ip>" + deviceIp + "</spbill_create_ip>";
        String total_fee = "<total_fee>" + total_feeStr + "</total_fee>";
        String trade_typeStr = "<trade_type>" + trade_type + "</trade_type>";

        xmlString.append("<xml>")
                .append(appIdStr);
        //只有小程序才有openid
        if (PlatformType.mp.equals(platform)) {
            xmlString.append(openIdXml);
        }
        xmlString.append(body)
                .append(mch_id)
                .append(nonce_str)
                .append(notify)
                .append(out_trade_no_xml)
                .append(spbill_create_ip)
                .append(total_fee)
                .append(trade_typeStr)
                .append(signStr)
                .append("</xml>");

        // 创建 HttpEntity
        HttpEntity<String> requestEntity = new HttpEntity<>(xmlString.toString(), requestHeader);
        ResponseEntity<String> responseEntity = RestUtil.getXmlRestTemplate().postForEntity(WxConst.wx_pay_url, requestEntity, String.class);
        String xmlStr = responseEntity.getBody();
        XStream xstream = new XStream();
        xstream.alias("xml", QQPayResult.class);
        Object qqPayResult = xstream.fromXML(xmlStr);
        String result = objectMapper.writeValueAsString(qqPayResult);
        log.info(result);
        QQPayResult result1 = objectMapper.readValue(result, QQPayResult.class);

        return result1.getPrepay_id();
    }

    //前端使用
    /*public static String getFrontPaySign(Map<String, String> map) {
        map.put("appId", WxConst.mp_app_id);
        map.put("signType", "MD5");
        return getSignToken(map);
    }*/

    public static String getFrontPaySign(String platform, String prepayid, String packageStr, String nonceStr, String timeStamp) {
        Map<String, String> map = new HashMap<>();
        if (PlatformType.mp.equals(platform)) {
            map.put("appId", wx_mp_id);
            map.put("signType", "MD5");
            map.put("timeStamp", timeStamp);
            map.put("nonceStr", nonceStr);
        } else {
            map.put("appid", wx_app_id);
            map.put("partnerid", wx_merchant_id);
            map.put("prepayid", prepayid);
            map.put("noncestr", nonceStr);
            map.put("timestamp", timeStamp);
        }
        map.put("package", packageStr);
        return getSignToken(map);
    }

    /**
     * 生成签名
     *
     * @param map
     * @return
     */
    public static String getSignToken(Map<String, String> map) {
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
        String result = sb.toString() + "key=" + wx_merchant_key;
        //进行MD5加密
        result = DigestUtils.md5Hex(result).toUpperCase();
        return result;
    }
}
