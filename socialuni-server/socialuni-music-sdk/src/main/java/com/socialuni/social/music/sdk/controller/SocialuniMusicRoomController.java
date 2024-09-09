package com.socialuni.social.music.sdk.controller;

import cn.hutool.core.lang.Console;
import com.socialuni.social.common.api.enumeration.SocialuniCommonStatus;
import com.qingchi.qing.common.exception.base.exception.SocialParamsException;
import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
import com.socialuni.social.common.sdk.dao.facede.SocialuniRepositoryFacade;
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
import com.socialuni.social.sdk.im.dao.facede.SocialuniMusicRoomRepositoryFacede;
import com.socialuni.social.sdk.im.enumeration.NotifyType;
import com.socialuni.social.sdk.im.logic.service.chat.ChatService;
import com.socialuni.social.sdk.im.notify.NotifyVO;
import com.socialuni.social.sdk.im.utils.SocialuniChatDOUtil;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@RequestMapping("socialuni/musicRoom")
@RestController
@Slf4j
public class SocialuniMusicRoomController {

    public SocialuniMusicRoomController(SocialuniChatDOUtil socialuniChatDOUtil) {
    }

    //加入歌单，所有人都拥有权限？
    @PostMapping("joinSongList/{roomId}")
    public ResultRO<Void> joinSongList(@PathVariable("roomId") String roomId, @RequestBody SocialuniMusicSongListItemRO playSongQO) {
        Long chatId = SocialuniChatDOUtil.getChatId(roomId);

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

    public SocialuniMusicRoomInfoRO playMusic1(Long chatId, Integer musicId, SocialuniPlayMusicQO playMusicQO) {
        if (ObjectUtils.isEmpty(musicId)) {
            throw new SocialParamsException("歌曲信息错误30111");
        }

        //获取歌单
        List<SocialuniMusicRoomSongListDO> socialuniMusicRoomSongListDOS = SocialuniMusicRoomRepositoryFacede.findAllByRoomIdAndStatus(chatId, SocialuniCommonStatus.enable, SocialuniMusicRoomSongListDO.class);


        if (socialuniMusicRoomSongListDOS.isEmpty()) {
            throw new SocialParamsException("歌曲信息错误30011");
        }


        //判断歌曲是否在歌单中
        SocialuniMusicRoomSongListDO socialuniMusicRoomSongListDO = SocialuniListUtil.findById(socialuniMusicRoomSongListDOS, musicId);

        Integer playMusicIndex = socialuniMusicRoomSongListDOS.indexOf(socialuniMusicRoomSongListDO);

        if (socialuniMusicRoomSongListDO == null) {
            throw new SocialParamsException("歌曲信息错误30012");
        }

        //如果存在
        SocialuniMusicRoomDO socialuniMusicRoomDO = socialuniMusicRoomManage.getOrCreateMusicPlayerDO(chatId);


        if (socialuniMusicRoomDO.getPlaying()) {
            Integer roomNowMusicId = socialuniMusicRoomDO.getMusicId();
            //不需要这个判断，因为有可能播放当前歌曲的时候，点击重新播放，或者拖拽进度
            //            if (musicId.equals(roomNowMusicId)) {
//                throw new SocialParamsException("歌曲正在播放中");
//            }

            SocialuniMusicRoomSongListDO socialuniMusicRoomSongListDO1 = socialuniMusicRoomSongListDOS.get(0);

            Integer firstMusicId = socialuniMusicRoomSongListDO1.getId();
            //如果正在播放的不是歌单第一首，则逻辑错误报错
            if (!firstMusicId.equals(roomNowMusicId)) {
                throw new SocialParamsException("歌曲信息错误30013");
            }
        }

        //如果index = 0

        log.info("下一首gequming:" + socialuniMusicRoomSongListDO.getMusicName());

        if (playMusicIndex == 0) {

        } else {
            //删除播放的歌曲，一会再加到头部
            socialuniMusicRoomSongListDOS.remove(socialuniMusicRoomSongListDO);
            SocialuniMusicRoomSongListDO removeSong = null;
            if (!socialuniMusicRoomSongListDOS.isEmpty()) {
                //删除正在播放的歌曲
                removeSong = socialuniMusicRoomSongListDOS.get(0);
                log.info("yichugequ:{}", removeSong.getMusicName());
                removeSong.setStatus(SocialuniCommonStatus.delete);
                socialuniMusicRoomSongListDOS.remove(0);
            }
            //如果index =1
            //如果等于其他
            socialuniMusicRoomSongListDOS.add(0, socialuniMusicRoomSongListDO);

            for (int i = 0; i < socialuniMusicRoomSongListDOS.size(); i++) {
                SocialuniMusicRoomSongListDO socialuniMusicRoomSongListItem = socialuniMusicRoomSongListDOS.get(i);
                socialuniMusicRoomSongListItem.setOrderNo(i + 1);
            }

            if (removeSong != null) {
                socialuniMusicRoomSongListDOS.add(removeSong);
            }
        }

        for (int i = 0; i < socialuniMusicRoomSongListDOS.size(); i++) {
            SocialuniMusicRoomSongListDO socialuniMusicRoomSongListItem = socialuniMusicRoomSongListDOS.get(i);
            socialuniMusicRoomSongListItem.setOrderNo(i + 1);
        }

        //更新序号
        SocialuniMusicRoomRepositoryFacede.saveAll(socialuniMusicRoomSongListDOS);

        //更新播放器
        socialuniMusicRoomManage.playMusic(musicId, playMusicQO, socialuniMusicRoomDO);

        SocialuniMusicRoomInfoRO socialuniMusicRoomPlayerInfoRO = SocialuniMusicRoomPlayerInfoROFactory.createSocialuniMusicRoomInfoRO(socialuniMusicRoomDO);

        SocialuniUserDo sysUser = SocialuniUserUtil.getSystemUserNotNull();

        NotifyVO<SocialuniMusicRoomInfoRO> notifyRONotifyVO = new NotifyVO<>(sysUser, NotifyType.music, socialuniMusicRoomPlayerInfoRO);
        notifyRONotifyVO.setChatId(chatId.toString());


        WebsocketServer.sendToAllUsers(notifyRONotifyVO);

        //不再需要后台自动暂停了，只有人工播放暂停
        if (socialuniMusicRoomDO.getPlaying()) {
            Date playingTimeStamp = socialuniMusicRoomDO.getPlayingTimestamp();
            long musicTime = socialuniMusicRoomSongListDO.getMusicTime();
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
            executorService.schedule(() -> {
                try {
                    Console.log("触发了定时器");
                    SocialuniMusicRoomDO dbRoom = socialuniMusicRoomManage.getOrCreateMusicPlayerDO(chatId);
                    if (dbRoom.getSequenceNum().equals(sequence)) {
                        dbRoom.setPlaying(Boolean.FALSE);
                        dbRoom.setPlayingTimestamp(new Date());
                        int playingTimePause = (int) ((musicTime / 1000) + 1);
                        dbRoom.setPlayingTime(playingTimePause);
                        dbRoom.sequencePlus();
                        SocialuniRepositoryFacade.save(dbRoom);
                        Console.log("执行了保存");


                        //发送暂停消息
                        SocialuniMusicRoomInfoRO socialuniMusicRoomPlayerInfoRO1 = SocialuniMusicRoomPlayerInfoROFactory.createSocialuniMusicRoomInfoRO(dbRoom);
                        NotifyVO<SocialuniMusicRoomInfoRO> notifyRONotifyVO1 = new NotifyVO<>(sysUser, NotifyType.music, socialuniMusicRoomPlayerInfoRO1);
                        notifyRONotifyVO1.setChatId(chatId.toString());
                        WebsocketServer.sendToAllUsers(notifyRONotifyVO1);

                        log.info(String.valueOf(1111111));
                        List<SocialuniMusicRoomSongListDO> socialuniMusicRoomSongListDOS1 = SocialuniMusicRoomRepositoryFacede.findAllByRoomIdAndStatus(chatId, SocialuniCommonStatus.enable, SocialuniMusicRoomSongListDO.class);

                        if (socialuniMusicRoomSongListDOS1.isEmpty()) {
                            return;
                        }
                        log.info(String.valueOf(2222));
                        Integer nowMusicId = socialuniMusicRoomSongListDOS1.get(0).getId();
                        if (!nowMusicId.equals(dbRoom.getMusicId())) {
                            throw new SocialParamsException("播放下一首音乐错误");
                        }
                        Integer nextMusicId;

                        log.info(String.valueOf(3333));
                        if (socialuniMusicRoomSongListDOS1.size() > 1) {
                            nextMusicId = socialuniMusicRoomSongListDOS1.get(1).getId();
                        } else {
                            nextMusicId = socialuniMusicRoomSongListDOS1.get(0).getId();
                        }

                        this.playMusic1(chatId, nextMusicId, null);

                        log.info(String.valueOf(4444));
                        // 这里放置你要延迟执行的代码
                        log.info("延迟执行的任务");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    log.error(e.getMessage());
                }

                executorService.shutdown();
            }, afterTime, TimeUnit.MILLISECONDS);
        }

        //需要发送消息，通知其他人，加入歌单了一首歌
        return socialuniMusicRoomPlayerInfoRO;
    }


    //切歌, no 和 songId,
    @PostMapping("playMusic/{roomId}")
    public ResultRO<SocialuniMusicRoomInfoRO> playMusic(@PathVariable("roomId") String roomId, @RequestBody SocialuniPlayMusicQO playMusicQO) {
        Long chatId = SocialuniChatDOUtil.getChatId(roomId);


        SocialuniMusicRoomInfoRO socialuniMusicRoomPlayerInfoRO = this.playMusic1(chatId, playMusicQO.getMusicId(), playMusicQO);

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

        Long chatId = SocialuniChatDOUtil.getChatId(channel);

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
        //创建 chatUser 的逻辑，点击进入页面，会话页加一条
        //发送消息，还有添加好友成功

        Long chatId = SocialuniChatDOUtil.getChatId(roomId);

        Long mineUserId = SocialuniUserUtil.getMineUserIdNotNull();

        socialuniMusicOperateCheck.checkRoleId(chatId, mineUserId);

        SocialuniMusicRoomDO socialuniMusicRoomDO = socialuniMusicRoomManage.updateMusicPlayerDO(chatId, playMusicQO);


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
            log.info(JSONUtil.toJsonStr(param1));
            log.info(authorizationHeader.toString());
            log.info(httpResult.toString());
//            log.info(httpResult.getErrMsg());
//            log.info(httpResult.getErrCode().toString());
        }
*/
        return ResultRO.success(socialuniMusicRoomPlayerInfoRO);
    }
}
