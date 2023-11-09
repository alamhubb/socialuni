package com.socialuni.social.music.sdk.controller;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.common.sdk.platform.qq.HttpImgCheckResult;
import com.socialuni.social.common.sdk.platform.qq.QQConst;
import com.socialuni.social.common.sdk.platform.weixin.WxConst;
import com.socialuni.social.common.sdk.utils.RestUtil;
import com.socialuni.social.music.sdk.model.QO.AgoraPlayMusicPlayerQO;
import com.socialuni.social.music.sdk.model.QO.AgoraPlayMusicQO;
import com.socialuni.social.music.sdk.model.RO.AgoraPlayMusicRO;
import com.socialuni.social.music.sdk.model.RO.SocialuniMusicInfoRO;
import com.socialuni.social.report.sdk.utils.QQUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.text.MessageFormat;
import java.util.*;

@RequestMapping("socialuni/music")
@RestController
@Slf4j
public class SocialuniMusicController {

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
        String token = "007eJxTYMgv2u0/ccF5bxWvnNKKlSdnVxwQXezfc9RBUvjEB8VPTy0VGExTzSwMTQwNEs1NjE2SUy2TkhKNU43MLIyMzJJTTY3Ne/p9UhsCGRlOJF9kYmSAQBCfiSExkYEBABSmHsY=";
        AgoraPlayMusicQO agoraPlayMusicQO = new AgoraPlayMusicQO();
        AgoraPlayMusicPlayerQO agoraPlayMusicPlayerQO = new AgoraPlayMusicPlayerQO();
        agoraPlayMusicPlayerQO.setUid(0);
        agoraPlayMusicPlayerQO.setIdleTimeout(300);
        agoraPlayMusicPlayerQO.setToken(token);
        agoraPlayMusicPlayerQO.setChannelName(channel);
        agoraPlayMusicPlayerQO.setStreamUrl(musicUrl);

        agoraPlayMusicQO.setPlayer(agoraPlayMusicPlayerQO);

        // 2、使用postForEntity请求接口
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authorizationHeader);
//        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        HttpEntity<AgoraPlayMusicQO> httpEntity = new HttpEntity<>(agoraPlayMusicQO, headers);

        String postUrl = "https://api.sd-rtn.com/cn/v1/projects/{0}/cloud-player/players";
//        String postUrl = "https://localpc.socialuni.cn:8091/socialuni/music/testMusic";

//        String fullUrl = MessageFormat.format(postUrl, appId);
//        String fullUrl = "https://localpc.socialuni.cn:8091/socialuni/music/testMusic";
        String fullUrl = "https://api.sd-rtn.com/cn/v1/projects/5e681410a7434ce9bba3e268226ce537/cloud-player/players";

        JSONObject param = JSONUtil.createObj();

        param.put("streamUrl", "https://music.163.com/song/media/outer/url?id=1895330088.mp3");
        param.put("channelName", "aa");
        param.put("token", "007eJxTYMgv2u0/ccF5bxWvnNKKlSdnVxwQXezfc9RBUvjEB8VPTy0VGExTzSwMTQwNEs1NjE2SUy2TkhKNU43MLIyMzJJTTY3Ne/p9UhsCGRlOJF9kYmSAQBCfiSExkYEBABSmHsY=");
        param.put("uid", 0);
        param.put("idleTimeout", 300);

        JSONObject param1 = JSONUtil.createObj();
        param1.put("player", param);

        String httpResult = HttpRequest.post(fullUrl)
                .body(JSONUtil.toJsonStr(param1))
                .header("Authorization", authorizationHeader).execute().body();


        log.info(fullUrl);
//        String httpResult = restTemplate.postForEntity("https://api.sd-rtn.com/cn/v1/projects/5e681410a7434ce9bba3e268226ce537/cloud-player/players", httpEntity, String.class).getBody();
//        AgoraPlayMusicRO httpResult = restTemplate.postForEntity(fullUrl, httpEntity, AgoraPlayMusicRO.class).getBody();
        if (httpResult != null) {
            System.out.println(agoraPlayMusicQO.toString());
            System.out.println(JSONUtil.toJsonStr(agoraPlayMusicQO));
            System.out.println(JSONUtil.toJsonStr(param1));
            System.out.println(authorizationHeader.toString());
            System.out.println(httpResult.toString());
//            log.info(httpResult.getErrMsg());
//            log.info(httpResult.getErrCode().toString());
        }

        return ResultRO.success();
    }

}
