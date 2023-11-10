package com.socialuni.social.music.sdk.controller;

import cn.hutool.core.net.URLDecoder;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.common.api.utils.JsonUtil;
import com.socialuni.social.common.sdk.platform.qq.HttpImgCheckResult;
import com.socialuni.social.common.sdk.platform.qq.QQConst;
import com.socialuni.social.common.sdk.platform.weixin.WxConst;
import com.socialuni.social.common.sdk.utils.RestUtil;
import com.socialuni.social.music.sdk.model.QO.AgoraPlayMusicPlayerQO;
import com.socialuni.social.music.sdk.model.QO.AgoraPlayMusicQO;
import com.socialuni.social.music.sdk.model.QO.AgoraUpdateMusicQO;
import com.socialuni.social.music.sdk.model.RO.AgoraPlayMusicRO;
import com.socialuni.social.music.sdk.model.RO.SocialuniMusicInfoRO;
import com.socialuni.social.report.sdk.utils.QQUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;
import java.util.*;

@RequestMapping("socialuni/music")
@RestController
@Slf4j
public class SocialuniMusicController {
    Integer sequence = 0;
    String playerId = "C5E78BCFA540BBE402EB1C938160FF5D";
    String region = "cn";
    String appId = "5e681410a7434ce9bba3e268226ce537";

    @PostMapping("testMusic")
    public ResultRO<String> testMusic(@RequestBody AgoraPlayMusicQO qo) {
        log.info(qo.toString());
        return ResultRO.success("chenggong");
    }

    @GetMapping("queryMusicList")
    public ResultRO<List<SocialuniMusicInfoRO>> queryMusicList() {
        SocialuniMusicInfoRO socialuniMusicInfoRO = new SocialuniMusicInfoRO();

        socialuniMusicInfoRO.setSrc("https://cdxapp-1257733245.file.myqcloud.com/opentest/M800000puzgO0yRX1o.mp3");
        socialuniMusicInfoRO.setMusicName("入秋");
        socialuniMusicInfoRO.setMusicAuthor("江南");

        return ResultRO.success(Collections.singletonList(socialuniMusicInfoRO));
    }

    @PostMapping("updateMusic")
    public ResultRO<Void> updateMusic(@RequestBody AgoraUpdateMusicQO updateMusicQO) {
        this.sequence++;
        SocialuniMusicInfoRO socialuniMusicInfoRO = new SocialuniMusicInfoRO();

        System.out.println(updateMusicQO.toString());
        String postUrl = "https://api.sd-rtn.com/{0}/v1/projects/{1}/cloud-player/players/{2}?sequence={3}";

        String fullUrl = MessageFormat.format(postUrl, this.region, this.appId, this.playerId, this.sequence);


        // 客户 ID
        final String customerKey = "1a97b2fe76664ef68bfddf7256cf91d3";
        // 客户密钥
        final String customerSecret = "999c0689cc794128b450c1d702f0e2f3";

        // 拼接客户 ID 和客户密钥并使用 base64 编码
        String plainCredentials = customerKey + ":" + customerSecret;
        String base64Credentials = new String(Base64.getEncoder().encode(plainCredentials.getBytes()));
        // 创建 authorization header
        String authorizationHeader = "Basic " + base64Credentials;

        JSONObject param = JSONUtil.createObj();

        if (!ObjectUtils.isEmpty(updateMusicQO.getIsPause())) {
            param.put("isPause", updateMusicQO.getIsPause());
        }
        if (!ObjectUtils.isEmpty(updateMusicQO.getStreamUrl())) {
            param.put("streamUrl", updateMusicQO.getStreamUrl());
        }
        if (!ObjectUtils.isEmpty(updateMusicQO.getSeekPosition())) {
            param.put("seekPosition", updateMusicQO.getSeekPosition());
        }

        JSONObject param1 = JSONUtil.createObj();
        param1.put("player", param);

        String jsonStr = JSONUtil.toJsonStr(param1);

        log.info("jsonStr:{}", jsonStr);

        String httpResult = HttpRequest.patch(fullUrl)
                .body(jsonStr)
                .header("Authorization", authorizationHeader).execute().body();

        log.info(fullUrl);
//        String httpResult = restTemplate.postForEntity("https://api.sd-rtn.com/cn/v1/projects/5e681410a7434ce9bba3e268226ce537/cloud-player/players", httpEntity, String.class).getBody();
//        AgoraPlayMusicRO httpResult = restTemplate.postForEntity(fullUrl, httpEntity, AgoraPlayMusicRO.class).getBody();
        if (httpResult != null) {
            System.out.println(authorizationHeader.toString());
            System.out.println(httpResult.toString());
//            log.info(httpResult.getErrMsg());
//            log.info(httpResult.getErrCode().toString());
        }
        return ResultRO.success();
    }

    @PostMapping("playMusic")
    public ResultRO<Void> playMusic(@RequestBody String musicUrl) {
        // 客户 ID
        final String customerKey = "1a97b2fe76664ef68bfddf7256cf91d3";
        // 客户密钥
        final String customerSecret = "999c0689cc794128b450c1d702f0e2f3";

        // 拼接客户 ID 和客户密钥并使用 base64 编码
        String plainCredentials = customerKey + ":" + customerSecret;
        String base64Credentials = new String(Base64.getEncoder().encode(plainCredentials.getBytes()));
        // 创建 authorization header
        String authorizationHeader = "Basic " + base64Credentials;


        String appId = "5e681410a7434ce9bba3e268226ce537";
        String channel = "aa";
        String token = "007eJxTYLjqsa6Y8fjv01aKmRpRD866m6eGNc458l1VJsW15U7uhh4FBtNUMwtDE0ODRHMTY5PkVMukpETjVCMzCyMjs+RUU2Nzo0e+qQ2BjAzSlpuYGBkgEMRnYkhMZGAAAPV5HZ4=";

        String postUrl = "https://api.sd-rtn.com/cn/v1/projects/{0}/cloud-player/players";
        String fullUrl = MessageFormat.format(postUrl, appId);

        JSONObject param = JSONUtil.createObj();

        String newMusicUrl = URLDecoder.decodeForPath(musicUrl, StandardCharsets.UTF_8);
        log.info(newMusicUrl);

        param.put("streamUrl", newMusicUrl);
        param.put("channelName", channel);
        param.put("token", token);
        param.put("uid", 0);
        param.put("idleTimeout", 300);

        JSONObject param1 = JSONUtil.createObj();
        param1.put("player", param);

        String httpResult = HttpRequest.post(fullUrl)
                .body(JSONUtil.toJsonStr(param1))
                .header("Authorization", authorizationHeader).execute().body();

        AgoraPlayMusicRO agoraPlayMusicRO = JsonUtil.parse(httpResult, AgoraPlayMusicRO.class);

        this.playerId = agoraPlayMusicRO.getPlayer().getId();

        System.out.println(playerId);

        log.info(fullUrl);
//        String httpResult = restTemplate.postForEntity("https://api.sd-rtn.com/cn/v1/projects/5e681410a7434ce9bba3e268226ce537/cloud-player/players", httpEntity, String.class).getBody();
//        AgoraPlayMusicRO httpResult = restTemplate.postForEntity(fullUrl, httpEntity, AgoraPlayMusicRO.class).getBody();
        if (httpResult != null) {
            System.out.println(JSONUtil.toJsonStr(param1));
            System.out.println(authorizationHeader.toString());
            System.out.println(httpResult.toString());
//            log.info(httpResult.getErrMsg());
//            log.info(httpResult.getErrCode().toString());
        }

        return ResultRO.success();
    }

}
