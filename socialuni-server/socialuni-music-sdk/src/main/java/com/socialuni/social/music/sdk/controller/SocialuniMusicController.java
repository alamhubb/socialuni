package com.socialuni.social.music.sdk.controller;

import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.socialuni.social.common.api.constant.DateTimeType;
import com.socialuni.social.common.api.exception.exception.SocialBusinessException;
import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.common.sdk.dao.facede.SocialuniRepositoryFacade;
import com.socialuni.social.im.dao.DO.SocialuniChatUserDO;
import com.socialuni.social.im.dao.repository.ChatUserRepository;
import com.socialuni.social.im.enumeration.SocialuniChatOperateType;
import com.socialuni.social.im.logic.check.SocialuniChatUserCheck;
import com.socialuni.social.music.sdk.check.SocialuniMusicOperateCheck;
import com.socialuni.social.music.sdk.dao.DO.SocialuniMusicRoomUserDO;
import com.socialuni.social.music.sdk.factory.SocialuniMusicRoomPlayerDOFactory;
import com.socialuni.social.music.sdk.factory.SocialuniMusicRoomPlayerInfoROFactory;
import com.socialuni.social.music.sdk.logic.entity.SocialuniMusicRoomUserEntity;
import com.socialuni.social.music.sdk.logic.manage.SocialuniMusicRoomManage;
import com.socialuni.social.music.sdk.logic.manage.SocialuniMusicRoomUserManage;
import com.socialuni.social.music.sdk.model.RO.SocialuniMusicOperateCheckRO;
import com.socialuni.social.music.sdk.dao.DO.SocialuniMusicRoomDO;
import com.socialuni.social.music.sdk.model.QO.AgoraPlayMusicQO;
import com.socialuni.social.music.sdk.model.QO.AgoraUpdateMusicQO;
import com.socialuni.social.music.sdk.model.QO.SocialuniPlayMusicQO;
import com.socialuni.social.music.sdk.model.RO.SocialuniMusicInfoRO;
import com.socialuni.social.music.sdk.model.RO.SocialuniMusicInitDataRO;
import com.socialuni.social.music.sdk.model.RO.SocialuniMusicRoomInfoRO;
import com.socialuni.social.music.sdk.utils.SocialuniMusicOperateRecordDOUtils;
import com.socialuni.social.tance.sdk.facade.SocialuniUnionIdFacede;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;
import io.agora.media.RtcTokenBuilder2;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.*;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.text.MessageFormat;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static com.socialuni.social.common.sdk.utils.RestUtil.*;

@RequestMapping("socialuni/music")
@RestController
@Slf4j
public class SocialuniMusicController {
    Integer sequence = 0;
    final static String region = "cn";
    final static String appId = "5e681410a7434ce9bba3e268226ce537";
    final static String appCertificate = "dc257b04c72f4850afb2e5d949535de6";
    final static String agoraRoot = "https://api.sd-rtn.com";
    // 客户 ID
    final static String customerKey = "1a97b2fe76664ef68bfddf7256cf91d3";
    // 客户密钥
    final static String customerSecret = "999c0689cc794128b450c1d702f0e2f3";

    @Resource
    SocialuniChatUserCheck socialuniChatUserCheck;
    @Resource
    SocialuniMusicRoomUserManage socialuniMusicRoomUserManage;

    //支持切歌功能，怎么做呢，停止目前播放的，然后播放一个新的
    //如果存在， 查询需要等10秒

    //我现在有一个场景， 就是播放一个以后，里面播放另一个，就是迅速切歌功能。
    //我只需要记录上一次创建成功的就可以。
    //切歌我就把上一个删除，然后重新创建一个

    //播放暂停功能，你咋知道播放完了没，通过时间和前端判断
    //协同编辑框架
    @GetMapping("queryMusicRoomPlayerInfo/{channel}")
    public ResultRO<SocialuniMusicRoomInfoRO> queryMusicRoomInfo(@PathVariable("channel") @Valid @NotBlank String channel) {
        Integer mineUserId = SocialuniUserUtil.getMineUserIdAllowNull();

        SocialuniMusicOperateCheckRO checkResult = socialuniMusicRoomUserEntity.getOrCreateMusicRoomUser(channel, mineUserId);

        SocialuniMusicRoomInfoRO socialuniMusicRoomPlayerInfoRO = SocialuniMusicRoomPlayerInfoROFactory.createSocialuniMusicRoomInfoRO(checkResult.getSocialuniMusicRoomDO(), checkResult.getRoleId());

        return ResultRO.success(socialuniMusicRoomPlayerInfoRO);
    }

    @Resource
    SocialuniMusicRoomUserEntity socialuniMusicRoomUserEntity;

    @GetMapping("getMusicToken/{channel}")
    public ResultRO<String> getMusicToken(@PathVariable("channel") String channel) {
        RtcTokenBuilder2 token = new RtcTokenBuilder2();

        Integer unionId = SocialuniUnionIdFacede.getChatUnionIdByUuidNotNull(channel);

        String result = token.buildTokenWithUid(appId, appCertificate, channel, 0, RtcTokenBuilder2.Role.ROLE_PUBLISHER, DateTimeType.day.intValue(), 0);
        log.info("Token with uid: {}", unionId);
        log.info("result: {}", result);
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

        socialuniMusicInfoRO.setMusicSrc("https://cdxapp-1257733245.file.myqcloud.com/opentest/M800000puzgO0yRX1o.mp3");
        socialuniMusicInfoRO.setMusicName("入秋");
        socialuniMusicInfoRO.setMusicAuthor("江南");

        return ResultRO.success(Collections.singletonList(socialuniMusicInfoRO));
    }

    @PostMapping("updateMusic/{channel}")
    public ResultRO<Void> updateMusic(@PathVariable("channel") String channel, @RequestBody AgoraUpdateMusicQO updateMusicQO) {
        this.sequence++;

        /*SocialuniMusicOperateCheckRO checkResult = socialuniMusicOperateCheck.checkRoleId(channel);

        Integer chatId = checkResult.getChatId();

        Boolean pause = updateMusicQO.getIsPause();


        SocialuniMusicRoomDO socialuniMusicRoomPlayerDO = SocialuniRepositoryFacade.findByCustomField("roomId", chatId, SocialuniMusicRoomDO.class);

        if (socialuniMusicRoomPlayerDO == null) {
            throw new SocialBusinessException("不存在的资源");
        }
        String playerId = socialuniMusicRoomPlayerDO.getMusicUrl();

        if (!ObjectUtils.isEmpty(updateMusicQO.getIsPause())) {
            if (updateMusicQO.getIsPause()) {
                SocialuniMusicOperateRecordDOUtils.createMusicRecord(
                        checkResult.getChatId(),
                        SocialuniChatOperateType.pause,
                        checkResult.getMineUserId(),
                        "歌曲名称",
                        checkResult.getRoleId()
                );
            } else {
                if (!socialuniMusicRoomPlayerDO.getPlaying()) {
                    throw new SocialBusinessException("不存在可播放的资源");
                }
                SocialuniMusicOperateRecordDOUtils.createMusicRecord(
                        checkResult.getChatId(),
                        SocialuniChatOperateType.play,
                        checkResult.getMineUserId(),
                        "歌曲名称",
                        checkResult.getRoleId()
                );
            }
            socialuniMusicRoomPlayerDO.setPlaying(pause);
        }

        System.out.println(updateMusicQO.toString());
        String postUrl = "https://api.sd-rtn.com/{0}/v1/projects/{1}/cloud-player/players/{2}?sequence={3}";

        String fullUrl = MessageFormat.format(postUrl, this.region, this.appId, playerId, this.sequence);

        String authorizationHeader = this.getAuthorization();

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
            SocialuniRepositoryFacade.save(socialuniMusicRoomPlayerDO);
            System.out.println(authorizationHeader.toString());
            System.out.println(httpResult.toString());
//            log.info(httpResult.getErrMsg());
//            log.info(httpResult.getErrCode().toString());
        }*/
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

    @Resource
    ChatUserRepository chatUserRepository;

    @Resource
    SocialuniMusicRoomManage socialuniMusicRoomManage;

    @Resource
    SocialuniMusicOperateCheck socialuniMusicOperateCheck;

    @PostMapping("playMusic/{channel}")
    public ResultRO<SocialuniMusicRoomInfoRO> playMusic(@PathVariable("channel") String channel, @RequestBody @Valid SocialuniPlayMusicQO playMusicQO) {
        if (StringUtils.isEmpty(channel)) {
            throw new SocialBusinessException("房间信息为空");
        }

        SocialuniMusicOperateCheckRO checkResult = socialuniMusicOperateCheck.checkRoleId(channel);


        Integer chatId = checkResult.getChatId();


        SocialuniMusicRoomDO socialuniMusicRoomDO = checkResult.getSocialuniMusicRoomDO();

        SocialuniMusicRoomPlayerDOFactory.createSocialuniMusicRoomPlayerDO(playMusicQO, socialuniMusicRoomDO);


        SocialuniRepositoryFacade.save(socialuniMusicRoomDO);


        if (socialuniMusicRoomDO.getPlaying()) {
            Date playingTimeStamp = socialuniMusicRoomDO.getPlayingTimeStamp();
            Integer musicTime = socialuniMusicRoomDO.getMusicTime();
            //获取时间戳
            long timestamp = playingTimeStamp.getTime();

            long endTime = timestamp + musicTime * 1000;

            //获取歌曲播放节点
            long playingTime = socialuniMusicRoomDO.getPlayingTime() * 1000;
            //获取多少秒后修改状态为暂停

            long afterTime = endTime - playingTime;

            Integer sequence = socialuniMusicRoomDO.getSequenceNum();

            ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

            executorService.schedule(() -> {

                SocialuniMusicRoomDO dbRoom = SocialuniRepositoryFacade.findByCustomField("roomId", chatId, SocialuniMusicRoomDO.class);
                if (dbRoom.getSequenceNum().equals(sequence)) {
                    dbRoom.setPlaying(Boolean.FALSE);
                    SocialuniRepositoryFacade.save(dbRoom);
                }

                // 这里放置你要延迟执行的代码
                System.out.println("延迟执行的任务");
            }, afterTime, TimeUnit.MILLISECONDS);

            executorService.shutdown();
        }


        SocialuniMusicRoomInfoRO socialuniMusicRoomPlayerInfoRO = SocialuniMusicRoomPlayerInfoROFactory.createSocialuniMusicRoomInfoRO(socialuniMusicRoomDO, checkResult.getRoleId());

        /*log.info(fullUrl);
//        String httpResult = restTemplate.postForEntity("https://api.sd-rtn.com/cn/v1/projects/5e681410a7434ce9bba3e268226ce537/cloud-player/players", httpEntity, String.class).getBody();
//        AgoraPlayMusicRO httpResult = restTemplate.postForEntity(fullUrl, httpEntity, AgoraPlayMusicRO.class).getBody();
        if (httpResult != null) {
            SocialuniMusicOperateRecordDOUtils.createMusicRecord(
                    checkResult.getChatId(),
                    SocialuniChatOperateType.switchMusic,
                    checkResult.getMineUserId(),
                    "歌曲名称",
                    checkResult.getRoleId()
            );
            System.out.println(JSONUtil.toJsonStr(param1));
            System.out.println(authorizationHeader.toString());
            System.out.println(httpResult.toString());
//            log.info(httpResult.getErrMsg());
//            log.info(httpResult.getErrCode().toString());
        }
*/
        return ResultRO.success(socialuniMusicRoomPlayerInfoRO);
    }

}
