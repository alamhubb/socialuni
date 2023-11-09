package com.socialuni.social.music.sdk.controller;

import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.common.sdk.platform.qq.HttpImgCheckResult;
import com.socialuni.social.common.sdk.platform.qq.QQConst;
import com.socialuni.social.common.sdk.platform.weixin.WxConst;
import com.socialuni.social.common.sdk.utils.RestUtil;
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

import javax.annotation.Resource;
import java.text.MessageFormat;
import java.util.*;

@RequestMapping("socialuni/music")
@RestController
@Slf4j
public class SocialuniMusicController {


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

        Map<String, Object> hashMap = new HashMap<>();

        hashMap.put("streamUrl", musicUrl);
        hashMap.put("channelName", channel);
        hashMap.put("token", token);
        hashMap.put("uid", 0);
        hashMap.put("idleTimeout", 300);

        // 封装参数，千万不要替换为Map与HashMap，否则参数无法传递
        MultiValueMap<String, Object> paramMap = new LinkedMultiValueMap<>();
        paramMap.add("player", hashMap);
        // 2、使用postForEntity请求接口
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authorizationHeader);

        HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<>(paramMap, headers);

        String postUrl = "https://api.sd-rtn.com/cn/v1/projects/{0}/cloud-player/players";

        String fullUrl = MessageFormat.format(postUrl, appId);

        String httpResult = RestUtil.getDefaultRestTemplate().postForEntity(fullUrl, httpEntity, String.class).getBody();
        if (httpResult != null) {
            System.out.println(httpResult);
//            log.info(httpResult.getErrMsg());
//            log.info(httpResult.getErrCode().toString());
        }
        return ResultRO.success();
    }

}
