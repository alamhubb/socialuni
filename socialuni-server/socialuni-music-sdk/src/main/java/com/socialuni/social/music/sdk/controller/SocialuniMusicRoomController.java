package com.socialuni.social.music.sdk.controller;

import com.socialuni.social.common.api.enumeration.SocialuniCommonStatus;
import com.socialuni.social.common.api.exception.exception.SocialBusinessException;
import com.socialuni.social.common.api.exception.exception.SocialParamsException;
import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
import com.socialuni.social.common.sdk.utils.SocialuniListUtil;
import com.socialuni.social.music.sdk.check.SocialuniMusicOperateCheck;
import com.socialuni.social.music.sdk.dao.DO.SocialuniMusicRoomDO;
import com.socialuni.social.music.sdk.dao.DO.SocialuniMusicRoomSongListDO;
import com.socialuni.social.music.sdk.factory.SocialuniMusicRoomPlayerInfoROFactory;
import com.socialuni.social.music.sdk.factory.SocialuniMusicSongListItemROFactory;
import com.socialuni.social.music.sdk.logic.manage.SocialuniMusicRoomManage;
import com.socialuni.social.music.sdk.model.QO.SocialuniPlayMusicQO;
import com.socialuni.social.music.sdk.model.RO.SocialuniMusicRoomInfoRO;
import com.socialuni.social.music.sdk.model.RO.SocialuniMusicSongListItemRO;
import com.socialuni.social.sdk.im.config.websocket.WebsocketServer;
import com.socialuni.social.sdk.im.dao.DO.SocialuniChatUserDO;
import com.socialuni.social.sdk.im.dao.facede.SocialuniMusicRoomRepositoryFacede;
import com.socialuni.social.sdk.im.enumeration.NotifyType;
import com.socialuni.social.sdk.im.logic.service.chat.ChatService;
import com.socialuni.social.sdk.im.notify.NotifyVO;
import com.socialuni.social.sdk.im.utils.SocialuniChatDOUtil;
import com.socialuni.social.tance.sdk.facade.SocialuniUnionIdFacede;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RequestMapping("socialuni/musicRoom")
@RestController
@Slf4j
public class SocialuniMusicRoomController {

    public SocialuniMusicRoomController(SocialuniChatDOUtil socialuniChatDOUtil) {
    }

    //加入歌单，所有人都拥有权限？
    @PostMapping("joinSongList/{roomId}")
    public ResultRO<Void> joinSongList(@PathVariable("roomId") String roomId, @RequestBody SocialuniMusicSongListItemRO playSongQO) {
        Integer chatId = SocialuniChatDOUtil.getChatId(roomId);

        List<SocialuniMusicRoomSongListDO> socialuniMusicRoomSongListDOS = SocialuniMusicRoomRepositoryFacede.findAllByRoomIdAndStatus(chatId, SocialuniCommonStatus.enable, SocialuniMusicRoomSongListDO.class);
        Integer no = socialuniMusicRoomSongListDOS.size() + 1;

        SocialuniMusicRoomSongListDO socialuniMusicRoomSongListDO = SocialuniMusicSongListItemROFactory.getSongDOByQO(playSongQO);
        SocialuniUserDo mineUser = SocialuniUserUtil.getMineUserNotNull();
        socialuniMusicRoomSongListDO.setUserId(mineUser.getUserId());
        socialuniMusicRoomSongListDO.setRoomId(chatId);
        socialuniMusicRoomSongListDO.setOrderNo(no);

        SocialuniMusicRoomRepositoryFacede.save(socialuniMusicRoomSongListDO);


        //需要发送消息，通知其他人，加入歌单了一首歌

        return ResultRO.success();
    }


    //下一首播放
    @PostMapping("nextPlayer/{songId}")
    public ResultRO<Void> nextPlayer(@PathVariable("songId") Integer songId) {

        //需要发送消息，通知其他人，加入歌单了一首歌

        return ResultRO.success();
    }


    //切歌, no 和 songId,
    @PostMapping("playMusic/{roomId}")
    public ResultRO<SocialuniMusicRoomInfoRO> playMusic(@PathVariable("roomId") String roomId, @RequestBody SocialuniPlayMusicQO playMusicQO) {
        Integer chatId = SocialuniChatDOUtil.getChatId(roomId);

        //获取歌单
        List<SocialuniMusicRoomSongListDO> socialuniMusicRoomSongListDOS = SocialuniMusicRoomRepositoryFacede.findAllByRoomIdAndStatus(chatId, SocialuniCommonStatus.enable, SocialuniMusicRoomSongListDO.class);

        if (socialuniMusicRoomSongListDOS.isEmpty()) {
            throw new SocialParamsException("歌曲信息错误30011");
        }

        Integer musicId = playMusicQO.getMusicId();

        if (ObjectUtils.isEmpty(musicId)) {
            throw new SocialParamsException("歌曲信息错误30111");
        }

        //判断歌曲是否在歌单中
        SocialuniMusicRoomSongListDO socialuniMusicRoomSongListDO = SocialuniListUtil.findById(socialuniMusicRoomSongListDOS, musicId);

        if (socialuniMusicRoomSongListDO == null) {
            throw new SocialParamsException("歌曲信息错误30012");
        }

        //如果存在
        SocialuniMusicRoomDO socialuniMusicRoomDO = socialuniMusicRoomManage.getOrCreateMusicPlayerDO(chatId);

        Integer roomNowMusicId = socialuniMusicRoomDO.getMusicId();

        if (socialuniMusicRoomDO.getPlaying()) {
            if (musicId.equals(roomNowMusicId)) {
                throw new SocialParamsException("歌曲正在播放中");
            }

            SocialuniMusicRoomSongListDO socialuniMusicRoomSongListDO1 = socialuniMusicRoomSongListDOS.get(0);

            Integer firstMusicId = socialuniMusicRoomSongListDO1.getId();
            //如果正在播放的不是歌单第一首，则逻辑错误报错
            if (!firstMusicId.equals(roomNowMusicId)) {
                throw new SocialParamsException("歌曲信息错误30013");
            }
        }

        //删除播放的歌曲，一会再加到头部
        socialuniMusicRoomSongListDOS.remove(socialuniMusicRoomSongListDO);
        SocialuniMusicRoomSongListDO removeSong = null;
        if (!socialuniMusicRoomSongListDOS.isEmpty()) {
            //删除正在播放的歌曲
            removeSong = socialuniMusicRoomSongListDOS.get(0);
            removeSong.setStatus(SocialuniCommonStatus.delete);
            socialuniMusicRoomSongListDOS.remove(0);
        }

        socialuniMusicRoomSongListDOS.add(0, socialuniMusicRoomSongListDO);

        for (int i = 0; i < socialuniMusicRoomSongListDOS.size(); i++) {
            SocialuniMusicRoomSongListDO socialuniMusicRoomSongListItem = socialuniMusicRoomSongListDOS.get(i);
            socialuniMusicRoomSongListItem.setOrderNo(i + 1);
        }

        if (removeSong != null) {
            socialuniMusicRoomSongListDOS.add(removeSong);
        }

        //更新序号
        SocialuniMusicRoomRepositoryFacede.saveAll(socialuniMusicRoomSongListDOS);

        //更新播放器
        socialuniMusicRoomManage.playMusic(musicId, playMusicQO, socialuniMusicRoomDO);


        //要不要推送给自己，不推送给自己了，直接返回，暂定而已，找不到合适理由

        SocialuniMusicRoomInfoRO socialuniMusicRoomPlayerInfoRO = SocialuniMusicRoomPlayerInfoROFactory.createSocialuniMusicRoomInfoRO(socialuniMusicRoomDO);

        return ResultRO.success(socialuniMusicRoomPlayerInfoRO);
    }

    //播放
    @PostMapping("deleteSong/{songId}")
    public ResultRO<Void> deleteSong(@PathVariable("songId") Integer songId) {

        //需要发送消息，通知其他人，加入歌单了一首歌

        return ResultRO.success();
    }


    @GetMapping("querySongList/{channel}")
    public ResultRO<List<SocialuniMusicSongListItemRO>> querySongList(@PathVariable("channel") String channel) {
        //根据房间id，获取歌单

        Integer chatId = SocialuniChatDOUtil.getChatId(channel);

        List<SocialuniMusicRoomSongListDO> socialuniMusicRoomSongListDOS = SocialuniMusicRoomRepositoryFacede.findAllByRoomIdAndStatus(chatId, SocialuniCommonStatus.enable, SocialuniMusicRoomSongListDO.class);

        List<SocialuniMusicSongListItemRO> socialuniMusicSongListItemROS = SocialuniMusicSongListItemROFactory.getSongListItemROs(socialuniMusicRoomSongListDOS);

        /*if (socialuniMusicSongListItemROS.isEmpty()) {
            SocialuniMusicSongListItemRO socialuniMusicRoomSongListRO = new SocialuniMusicSongListItemRO();
            socialuniMusicRoomSongListRO.setSongId("1397345903");
            socialuniMusicRoomSongListRO.setMusicName("悬溺");
            socialuniMusicRoomSongListRO.setAuthor("葛东琪");
            socialuniMusicRoomSongListRO.setMusicTime(197083);
            socialuniMusicRoomSongListRO.setNo(1);
            socialuniMusicSongListItemROS.add(socialuniMusicRoomSongListRO);
        }*/

        //如果处于播放状态，后台需要有计时功能

        return ResultRO.success(socialuniMusicSongListItemROS);
    }


    @Resource
    ChatService chatService;
    @Resource
    SocialuniMusicOperateCheck socialuniMusicOperateCheck;

    @Resource
    SocialuniMusicRoomManage socialuniMusicRoomManage;

    //更新房间的播放信息
    @PostMapping("updateRoomPlayerInfo/{roomId}")
    public ResultRO<SocialuniMusicRoomInfoRO> updateRoomPlayInfo(@PathVariable("roomId") @Valid @NotBlank String roomId, @RequestBody @Valid SocialuniPlayMusicQO playMusicQO) {
        if (StringUtils.isEmpty(roomId)) {
            throw new SocialBusinessException("房间信息为空");
        }

        Integer chatId = SocialuniUnionIdFacede.getUnionIdByUuidAllowNull(roomId);

        //创建 chatUser 的逻辑，点击进入页面，会话页加一条
        //发送消息，还有添加好友成功

        if (chatId == null) {
            SocialuniChatUserDO chatUserDO = chatService.getSocialuniChatUserDO(roomId);
            chatId = chatUserDO.getChatId();
        }
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
        notifyRONotifyVO.setChatId(roomId);
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
