package com.socialuni.social.music.sdk.controller;

import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONUtil;
import com.socialuni.social.common.api.constant.DateTimeType;
import com.socialuni.social.common.api.exception.exception.SocialBusinessException;
import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
import com.socialuni.social.music.sdk.check.SocialuniMusicOperateCheck;
import com.socialuni.social.music.sdk.dao.DO.SocialuniMusicRoomUserDO;
import com.socialuni.social.music.sdk.factory.SocialuniMusicRoomPlayerInfoROFactory;
import com.socialuni.social.music.sdk.logic.entity.SocialuniMusicRoomUserEntity;
import com.socialuni.social.music.sdk.logic.manage.SocialuniMusicRoomManage;
import com.socialuni.social.music.sdk.logic.manage.SocialuniMusicRoomUserManage;
import com.socialuni.social.music.sdk.model.RO.*;
import com.socialuni.social.music.sdk.dao.DO.SocialuniMusicRoomDO;
import com.socialuni.social.music.sdk.model.QO.AgoraPlayMusicQO;
import com.socialuni.social.music.sdk.model.QO.SocialuniPlayMusicQO;
import com.socialuni.social.sdk.im.config.websocket.WebsocketServer;
import com.socialuni.social.sdk.im.dao.DO.SocialuniChatDO;
import com.socialuni.social.sdk.im.dao.DO.SocialuniChatUserDO;
import com.socialuni.social.sdk.im.dao.repository.SocialuniChatUserRepository;
import com.socialuni.social.sdk.im.dao.repository.SocialuniChatRepository;
import com.socialuni.social.sdk.im.enumeration.ChatType;
import com.socialuni.social.sdk.im.enumeration.NotifyType;
import com.socialuni.social.sdk.im.logic.check.SocialuniChatUserCheck;
import com.socialuni.social.sdk.im.logic.service.chat.ChatService;
import com.socialuni.social.sdk.im.notify.NotifyVO;
import com.socialuni.social.sdk.im.utils.SocialuniChatDOUtil;
import com.socialuni.social.tance.sdk.config.SocialuniAppConfig;
import com.socialuni.social.tance.sdk.facade.SocialuniUnionIdFacede;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;
import io.agora.media.RtcTokenBuilder2;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.text.MessageFormat;
import java.util.*;

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

    @Resource
    SocialuniMusicRoomManage socialuniMusicRoomManage;

    @Resource
    SocialuniChatRepository chatRepository;

    @GetMapping("getPublicRoomId")
    public ResultRO<String> getPublicRoomId() {
        List<String> defaultChatGroups = SocialuniAppConfig.getAppConfig().getDefaultChatGroups();
        String groupName = defaultChatGroups.get(0);
        SocialuniChatDO chatDO = chatRepository.findFirstByTypeAndChatName(ChatType.system_group, groupName);
        String chatId = SocialuniUnionIdFacede.getUuidByUnionIdNotNull(chatDO.getUnionId());
        return ResultRO.success(chatId);
    }


    @GetMapping("queryMusicRoomPlayerInfo/{channel}")
    public ResultRO<SocialuniMusicRoomInfoRO> queryMusicRoomInfo(@PathVariable("channel") String channel) {
        Integer chatId = SocialuniChatDOUtil.getChatId(channel);

        SocialuniMusicRoomDO socialuniMusicRoomDO = socialuniMusicRoomManage.getOrCreateMusicPlayerDO(chatId);

        if (ObjectUtils.isEmpty(socialuniMusicRoomDO.getMusicId())) {
            return ResultRO.success();
        }

        SocialuniMusicRoomInfoRO socialuniMusicRoomPlayerInfoRO = SocialuniMusicRoomPlayerInfoROFactory.createSocialuniMusicRoomInfoRO(socialuniMusicRoomDO);

        return ResultRO.success(socialuniMusicRoomPlayerInfoRO);
    }

    /*@GetMapping("queryMusicUserInfo/{channel}")
    public ResultRO<SocialuniMusicRoomUserInfoRO> queryMusicUserInfo() {
        Integer mineUserId = SocialuniUserUtil.getMineUserIdNotNull();
        Integer chatId = SocialuniUnionIdFacede.getChatUnionIdByUuidNotNull(channel);

        //然后是查询roomUser
        SocialuniMusicRoomUserDO socialuniMusicRoomUserDO = socialuniMusicRoomUserEntity.checkAndGetOrCreateMusicRoomUserInfo(chatId, mineUserId);

        if (socialuniMusicRoomUserDO != null) {
            return ResultRO.success(new SocialuniMusicRoomUserInfoRO(socialuniMusicRoomUserDO.getRoomRoleId()));
        }

        return ResultRO.success();
    }*/

    @GetMapping("queryMusicRoomUserInfo/{channel}")
    public ResultRO<SocialuniMusicRoomUserInfoRO> queryMusicRoomUserInfo(@PathVariable("channel") @Valid @NotBlank String channel) {
        Integer chatId = SocialuniChatDOUtil.getChatId(channel);

        Integer mineUserId = SocialuniUserUtil.getMineUserIdNotNull();

        //然后是查询roomUser
        SocialuniMusicRoomUserDO socialuniMusicRoomUserDO = socialuniMusicRoomUserEntity.checkAndGetOrCreateMusicRoomUserInfo(chatId, mineUserId);

        if (socialuniMusicRoomUserDO != null) {
            return ResultRO.success(new SocialuniMusicRoomUserInfoRO(socialuniMusicRoomUserDO.getRoomRoleId()));
        }

        return ResultRO.success();
    }

    @Resource
    SocialuniMusicRoomUserEntity socialuniMusicRoomUserEntity;

    @GetMapping("getMusicToken/{channel}")
    public ResultRO<String> getMusicToken(@PathVariable("channel") String channel) {
        RtcTokenBuilder2 token = new RtcTokenBuilder2();

        Long unionId = SocialuniUnionIdFacede.getChatUnionIdByUuidNotNull(channel);

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
    public ResultRO<Void> updateMusic(@PathVariable("channel") String channel, @RequestBody SocialuniPlayMusicQO updateMusicQO) {
        //暂停？切歌，都走这个


        this.sequence++;


        Integer mineUserId = SocialuniUserUtil.getMineUserIdNotNull();

        //校验用户有没有修改权限
       /* SocialuniMusicOperateCheckRO checkResult = socialuniMusicOperateCheck.checkRoleId(mineUserId);

        Integer chatId = checkResult.getChatId();

        Boolean pause = updateMusicQO.getPlaying();


        SocialuniMusicRoomDO socialuniMusicRoomPlayerDO = SocialuniRepositoryFacade.findByCustomField("roomId", chatId, SocialuniMusicRoomDO.class);

        if (socialuniMusicRoomPlayerDO == null) {
            throw new SocialBusinessException("不存在的资源");
        }
        String playerId = socialuniMusicRoomPlayerDO.getMusicUrl();

        if (!ObjectUtils.isEmpty(updateMusicQO.getPlaying())) {
            if (updateMusicQO.getPlaying()) {
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

        if (!ObjectUtils.isEmpty(updateMusicQO.getPlaying())) {
            param.put("isPause", updateMusicQO.getPlaying());
        }
        if (!ObjectUtils.isEmpty(updateMusicQO.getMusicUrl())) {
            param.put("streamUrl", updateMusicQO.getMusicTime());
        }
        if (!ObjectUtils.isEmpty(updateMusicQO.getPlayingTime())) {
            param.put("seekPosition", updateMusicQO.getPlayingTime());
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
    SocialuniMusicOperateCheck socialuniMusicOperateCheck;

    @Resource
    ChatService chatService;

    @PostMapping("playMusic/{channel}")
    public ResultRO<SocialuniMusicRoomInfoRO> playMusic(@PathVariable("channel") @Valid @NotBlank String channel, @RequestBody @Valid SocialuniPlayMusicQO playMusicQO) {
        if (StringUtils.isEmpty(channel)) {
            throw new SocialBusinessException("房间信息为空");
        }

        Integer chatId = SocialuniChatDOUtil.getChatId(channel);
        
        Integer mineUserId = SocialuniUserUtil.getMineUserIdNotNull();

        socialuniMusicOperateCheck.checkRoleId(chatId, mineUserId);

        SocialuniMusicRoomDO socialuniMusicRoomDO = socialuniMusicRoomManage.updateMusicPlayerDO(chatId, playMusicQO);

        //不再需要后台自动暂停了，只有人工播放暂停
        /*if (socialuniMusicRoomDO.getPlaying()) {
            Date playingTimeStamp = socialuniMusicRoomDO.getPlayingTimestamp();
            long musicTime = socialuniMusicRoomDO.getMusicTime() * 1000;
            long playingTime = socialuniMusicRoomDO.getPlayingTime() * 1000;

            //获取时间戳
            long timestamp = playingTimeStamp.getTime();

            long endTime = timestamp + musicTime;
            long startTime = timestamp + playingTime;

            //获取歌曲播放节点
            //获取多少秒后修改状态为暂停

            long afterTime = endTime - startTime;

            Integer sequence = socialuniMusicRoomDO.getSequenceNum();

            ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

            Console.log("触发了定时器");
            Console.log("afterTime");
            Console.log(afterTime);
            Integer finalChatId = chatId;
            executorService.schedule(() -> {
                Console.log("触发了定时器");
                SocialuniMusicRoomDO dbRoom = SocialuniRepositoryFacade.findByCustomField("roomId", finalChatId, SocialuniMusicRoomDO.class);
                if (dbRoom.getSequenceNum().equals(sequence)) {
                    dbRoom.setPlaying(Boolean.FALSE);
                    SocialuniRepositoryFacade.save(dbRoom);
                    Console.log("执行了保存");
                }

                // 这里放置你要延迟执行的代码
                System.out.println("延迟执行的任务");
                executorService.shutdown();
            }, afterTime, TimeUnit.MILLISECONDS);
        }*/


        SocialuniMusicRoomInfoRO socialuniMusicRoomPlayerInfoRO = SocialuniMusicRoomPlayerInfoROFactory.createSocialuniMusicRoomInfoRO(socialuniMusicRoomDO);


        SocialuniUserDo socialuniUserDo = SocialuniUserUtil.getMineUserNotNull();

        NotifyVO<SocialuniMusicRoomInfoRO> notifyRONotifyVO = new NotifyVO<>(socialuniUserDo, NotifyType.music, socialuniMusicRoomPlayerInfoRO);
        notifyRONotifyVO.setChatId(channel);
//        WebsocketServer.sendToGroupUsers(notifyRONotifyVO);

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
