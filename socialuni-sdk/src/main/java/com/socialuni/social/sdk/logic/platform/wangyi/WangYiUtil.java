package com.socialuni.social.sdk.logic.platform.wangyi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.socialuni.social.sdk.constant.WyCheckConst;
import com.socialuni.social.sdk.dao.DO.AntispamDO;
import com.socialuni.social.sdk.dao.DO.user.SocialUnionContentBaseDO;
import com.socialuni.social.sdk.dao.repository.AntispamRepository;
import com.socialuni.social.sdk.utils.UUIDUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.Consts;
import org.apache.http.client.HttpClient;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * @author qinkaiyuan
 * @date 2019-10-24 13:30
 */
@Component
public class WangYiUtil {
    private static ObjectMapper objectMapper;
    private static String tokenUrl;
    private static AntispamRepository antispamRepository;

    /**
     * 实例化HttpClient，发送http请求使用，可根据需要自行调参
     */
    private static HttpClient httpClient = HttpClient4Utils.createHttpClient(100, 20, 2000, 2000, 2000);


    public void setTokenUrl(String tokenUrl) {
        WangYiUtil.tokenUrl = tokenUrl;
    }

    @Resource
    public void setAntispamRepository(AntispamRepository antispamRepository) {
        WangYiUtil.antispamRepository = antispamRepository;
    }

    @Resource
    public void setObjectMapper(ObjectMapper objectMapper) {
        WangYiUtil.objectMapper = objectMapper;
    }


    public static AntispamDO checkWYContentSecPost(SocialUnionContentBaseDO baseModelDO) throws IOException {
        AntispamDO antispamDO = new AntispamDO();
        //设置内容主体相关
        antispamDO.setContentType(baseModelDO.getContentType());
        antispamDO.setContentId(baseModelDO.getUnionId());
        antispamDO.setContent(baseModelDO.getContent());
        antispamDO.setCreateTime(new Date());
        //执行校验
        WyCommonResultVO wyCommonResultVO = WangYiUtil.checkContentWYSecPostBase(baseModelDO.getContent());
        //校验结果
        if (wyCommonResultVO != null) {
            int code = wyCommonResultVO.getCode();
            //是否调用成功
            antispamDO.setCode(code);
            antispamDO.setMsg(wyCommonResultVO.getMsg());
            //成功
            if (code == 200) {
                WyTalkCheckResultVO resultObject = wyCommonResultVO.getResult();
                if (resultObject != null) {
                    WyTalkCheckAntispamVO antispamObject = resultObject.getAntispam();
                    if (antispamObject != null) {
                        //设置基础内容
                        antispamDO.setTaskId(antispamObject.getTaskId());

                        int action = antispamObject.getAction();
                        antispamDO.setAction(action);
                        antispamDO.setActionName(WyCheckConst.actionMap.get(action));

                        //是否违规
                        /*if (antispamDO.hasViolate()) {
                            List<String> labels = new ArrayList<>();
                            List<String> labelNames = new ArrayList<>();
                            List<String> hints = new ArrayList<>();
                            List<String> subLabels = new ArrayList<>();
                            List<String> subLabelNames = new ArrayList<>();

                            List<WyTalkCheckLabelVO> labelArray = antispamObject.getLabels();


                            for (WyTalkCheckLabelVO wyTalkCheckLabelVO : labelArray) {
                                //添加label
                                labels.add(WyCheckConst.labelMap.get(wyTalkCheckLabelVO.getLabel()));
                                //添加label name
                                labelNames.add(WyCheckConst.labelMap.get(wyTalkCheckLabelVO.getLabel()));
                                //添加hint
                                hints.add(Arrays.toString(wyTalkCheckLabelVO.getDetails().getHint().toArray()));

                                for (WyTalkCheckSubLabelVO subLabel : wyTalkCheckLabelVO.getSubLabels()) {
                                    //subLabels
                                    subLabels.add(subLabel.getSubLabel().toString());

                                    //subLabelNames
                                    subLabelNames.add(WyCheckConst.subLabelMap.get(subLabel.getSubLabel()));
                                }
                            }
                            antispamDO.setLabels(Arrays.toString(labels.toArray()));
                            antispamDO.setLabelNames(Arrays.toString(labelNames.toArray()));
                            antispamDO.setHints(Arrays.toString(hints.toArray()));
                            antispamDO.setSubLabels(Arrays.toString(subLabels.toArray()));
                            antispamDO.setSubLabelNames(Arrays.toString(subLabelNames.toArray()));

                            List<String> causeList = Arrays.asList(antispamDO.getLabelNames(), antispamDO.getSubLabelNames(), antispamDO.getHints());
                            antispamDO.setCause(Arrays.toString(causeList.toArray()));
                        }*/
                    }
                }
            }
        }
//        antispamRepository.save(antispamDO);
        return antispamDO;
    }

    private static String wy_security_id;
    private static String wy_security_secret;
    private static String wy_security_talk_business_id;

    public void setWy_security_id(String wy_security_id) {
        WangYiUtil.wy_security_id = wy_security_id;
    }

    public void setWy_security_secret(String wy_security_secret) {
        WangYiUtil.wy_security_secret = wy_security_secret;
    }

    public void setWy_security_talk_business_id(String wy_security_talk_business_id) {
        WangYiUtil.wy_security_talk_business_id = wy_security_talk_business_id;
    }

    public static WyCommonResultVO checkContentWYSecPostBase(String content) throws IOException {
        Map<String, String> params = new HashMap<>();
        // 1.设置公共参数
        params.put("secretId", wy_security_id);
        params.put("businessId", wy_security_talk_business_id);
        params.put("version", "v4");
        params.put("timestamp", String.valueOf(System.currentTimeMillis()));
        params.put("nonce", String.valueOf(new Random().nextInt()));
        params.put("signatureMethod", "MD5"); // MD5, SM3, SHA1, SHA256

        // 2.设置私有参数
        params.put("dataId", UUIDUtil.getUUID());
        params.put("content", content);
        // params.put("dataType", "1");
        // params.put("ip", "123.115.77.137");
        // params.put("account", "java@163.com");
        // params.put("deviceType", "4");
        // params.put("deviceId", "92B1E5AA-4C3D-4565-A8C2-86E297055088");
        // params.put("callback", "ebfcad1c-dba1-490c-b4de-e784c2691768");
        // params.put("publishTime", String.valueOf(System.currentTimeMillis()));
        // 主动回调地址url,如果设置了则走主动回调逻辑
        // params.put("callbackUrl", "http://***");

        // 3.生成签名信息
        String signature = genSignature(params);
        params.put("signature", signature);

        // 转换参数格式

        // 设置请求头
        /*HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        // 设置请求参数

        String url = WangYiConst.wy_talk_sec_check_url;

        HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(params, headers);

        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, requestEntity, String.class);*/

        String url = WangYiConst.wy_talk_sec_check_url;

        // 4.发送HTTP请求，这里使用的是HttpClient工具包，产品可自行选择自己熟悉的工具包发送请求
        String response = HttpClient4Utils.sendPost(httpClient, url, params, Consts.UTF_8);

        return objectMapper.readValue(response, WyCommonResultVO.class);
    }

    /**
     * 生成签名
     *
     * @param params
     * @return
     */
    private static String genSignature(Map<String, String> params) {
        // 1. 参数名按照ASCII码表升序排序
        String[] keys = params.keySet().toArray(new String[0]);
        Arrays.sort(keys);

        // 2. 按照排序拼接参数名与参数值
        StringBuilder sb = new StringBuilder();
        for (String key : keys) {
            sb.append(key).append(params.get(key));
        }
        // 3. 将secretKey拼接到最后
        sb.append(wy_security_secret);

        // 4. MD5是128位长度的摘要算法，转换为十六进制之后长度为32字符
        return DigestUtils.md5Hex(sb.toString().getBytes(StandardCharsets.UTF_8));
    }
}
