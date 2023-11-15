package com.socialuni.social.music.sdk.controller;

import cn.hutool.core.net.URLDecoder;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.socialuni.social.common.api.constant.DateTimeType;
import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.common.api.utils.JsonUtil;
import com.socialuni.social.common.sdk.platform.qq.HttpImgCheckResult;
import com.socialuni.social.common.sdk.platform.qq.QQConst;
import com.socialuni.social.common.sdk.platform.weixin.WxConst;
import com.socialuni.social.common.sdk.utils.DateUtils;
import com.socialuni.social.common.sdk.utils.RestUtil;
import com.socialuni.social.music.sdk.model.QO.AgoraPlayMusicPlayerQO;
import com.socialuni.social.music.sdk.model.QO.AgoraPlayMusicQO;
import com.socialuni.social.music.sdk.model.QO.AgoraUpdateMusicQO;
import com.socialuni.social.music.sdk.model.QO.SocialuniPlayMusicQO;
import com.socialuni.social.music.sdk.model.RO.AgoraPlayMusicRO;
import com.socialuni.social.music.sdk.model.RO.SocialuniMusicInfoRO;
import com.socialuni.social.music.sdk.model.RO.SocialuniMusicInitDataRO;
import com.socialuni.social.report.sdk.utils.QQUtil;
import com.socialuni.social.tance.sdk.facade.SocialuniUnionIdFacede;
import io.agora.education.EducationTokenBuilder2;
import io.agora.media.RtcTokenBuilder2;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;
import java.util.*;

import static com.socialuni.social.common.sdk.utils.RestUtil.*;

@RequestMapping("socialuni/music")
@RestController
@Slf4j
public class SocialuniMusicController {
    Integer sequence = 0;
    static String playerId = "C5E78BCFA540BBE402EB1C938160FF5D";
    final static String region = "cn";
    final static String appId = "5e681410a7434ce9bba3e268226ce537";
    final static String appCertificate = "dc257b04c72f4850afb2e5d949535de6";
    final static String agoraRoot = "https://api.sd-rtn.com";
    // 客户 ID
    final static String customerKey = "1a97b2fe76664ef68bfddf7256cf91d3";
    // 客户密钥
    final static String customerSecret = "999c0689cc794128b450c1d702f0e2f3";

    //支持切歌功能，怎么做呢，停止目前播放的，然后播放一个新的
    //如果存在， 查询需要等10秒

    //我现在有一个场景， 就是播放一个以后，里面播放另一个，就是迅速切歌功能。
    //我只需要记录上一次创建成功的就可以。
    //切歌我就把上一个删除，然后重新创建一个

    //播放暂停功能，你咋知道播放完了没，通过时间和前端判断
    //协同编辑框架

    public static void main(String[] args) {

    }

    @GetMapping("getMusicToken/{channel}")
    public ResultRO<String> getMusicToken(@PathVariable("channel") String channel) {
        RtcTokenBuilder2 token = new RtcTokenBuilder2();
        String result = token.buildTokenWithUid(appId, appCertificate, channel, 0, RtcTokenBuilder2.Role.ROLE_PUBLISHER, DateTimeType.day.intValue(), 0);
        System.out.println("Token with uid: " + result);
        return ResultRO.success(result);
    }

    @GetMapping("queryMusicChannel/{channel}")
    public ResultRO<Object> queryMusicChannelInfo(@PathVariable("channel") String channel) {

        String url = "{0}/dev/v1/channel/user/{1}/{2}";

        String fullUrl = MessageFormat.format(url, agoraRoot, appId, channel);

        log.info(fullUrl);

        RestTemplate restTemplate = getDefaultRestTemplate();

        String authorizationHeader = this.getAuthorization();

        HttpHeaders requestHeader = new HttpHeaders();
        requestHeader.set("Authorization", authorizationHeader);

        HttpEntity<String> requestEntity = new HttpEntity<>(requestHeader);
        ResponseEntity<String> responseEntity = restTemplate.exchange(fullUrl, HttpMethod.GET, requestEntity, String.class);

        String result = responseEntity.getBody();

        String httpResult = HttpRequest.get(fullUrl)
                .header("Authorization", authorizationHeader).execute().body();

        log.info(result);
        log.info(httpResult);
        return ResultRO.success(JSONUtil.parseObj(httpResult));
    }

    @GetMapping("getMusicInitData")
    public ResultRO<SocialuniMusicInitDataRO> getMusicInitData() {
        SocialuniMusicInitDataRO musicInitDataRO = new SocialuniMusicInitDataRO();
        musicInitDataRO.setAppId(appId);
        return ResultRO.success(musicInitDataRO);
    }

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

    private String getAuthorization() {
        // 拼接客户 ID 和客户密钥并使用 base64 编码
        String plainCredentials = customerKey + ":" + customerSecret;
        String base64Credentials = new String(Base64.getEncoder().encode(plainCredentials.getBytes()));
        // 创建 authorization header
        String authorizationHeader = "Basic " + base64Credentials;

        log.info("authorizationHeader:{}", authorizationHeader);
        return authorizationHeader;
    }

    @PostMapping("playMusic/{channel}")
    public ResultRO<Void> playMusic(@PathVariable("channel") String channel, @RequestBody @Valid SocialuniPlayMusicQO playMusicQO) {

        String postUrl = "https://api.sd-rtn.com/{0}/v1/projects/{1}/cloud-player/players";
        String fullUrl = MessageFormat.format(postUrl, region, appId);

        JSONObject param = JSONUtil.createObj();

        String musicId = playMusicQO.getMusicId();

        String musicUrl = "https://music.163.com/song/media/outer/url?id={0}.mp3";

        if (musicId.contains("http")) {
            musicUrl = musicId;
        } else {
            musicUrl = MessageFormat.format(musicUrl, musicId);
        }

        String newMusicUrl = URLDecoder.decodeForPath(musicUrl, StandardCharsets.UTF_8);
        log.info(newMusicUrl);

        param.put("streamUrl", newMusicUrl);
        param.put("channelName", channel);
        param.put("token", playMusicQO.getMusicToken());
        param.put("uid", 0);
        param.put("idleTimeout", 300);

        JSONObject param1 = JSONUtil.createObj();
        param1.put("player", param);


        String authorizationHeader = this.getAuthorization();

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
