package com.socialuni.social.music.sdk.controller;

import com.socialuni.social.common.api.enumeration.SocialuniCommonStatus;
import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.music.sdk.dao.DO.SocialuniMusicRoomSongListDO;
import com.socialuni.social.music.sdk.factory.SocialuniMusicSongListItemROFactory;
import com.socialuni.social.music.sdk.model.QO.SocialuniMusicRoomPlaySongQO;
import com.socialuni.social.music.sdk.model.RO.SocialuniMusicInfoRO;
import com.socialuni.social.music.sdk.model.RO.SocialuniMusicSongListItemRO;
import com.socialuni.social.sdk.im.dao.facede.SocialuniChatRepositoryFacede;
import com.socialuni.social.sdk.im.utils.SocialuniChatDOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("socialuni/musicRoom")
@RestController
@Slf4j
public class SocialuniMusicRoomController {

    public SocialuniMusicRoomController(SocialuniChatDOUtil socialuniChatDOUtil) {
    }

    //加入歌单
    @PostMapping("joinSongList")
    public ResultRO<Void> joinSongList(@RequestBody SocialuniMusicRoomPlaySongQO playSongQO) {

        //需要发送消息，通知其他人，加入歌单了一首歌

        return ResultRO.success();
    }


    //下一首播放
    @PostMapping("nextPlayer/{songId}")
    public ResultRO<Void> nextPlayer(@PathVariable("songId") Integer songId) {

        //需要发送消息，通知其他人，加入歌单了一首歌

        return ResultRO.success();
    }


    //播放
    @PostMapping("playSong/{songId}")
    public ResultRO<Void> playSong(@PathVariable("songId") Integer songId) {

        //需要发送消息，通知其他人，加入歌单了一首歌

        return ResultRO.success();
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

        List<SocialuniMusicRoomSongListDO> socialuniMusicRoomSongListDOS = SocialuniChatRepositoryFacede.findAllByChatIdAndStatus(chatId, SocialuniCommonStatus.enable, SocialuniMusicRoomSongListDO.class);

        List<SocialuniMusicSongListItemRO> socialuniMusicSongListItemROS = SocialuniMusicSongListItemROFactory.getSongListItemROs(socialuniMusicRoomSongListDOS);

        if (socialuniMusicSongListItemROS.isEmpty()) {
            SocialuniMusicSongListItemRO socialuniMusicRoomSongListRO = new SocialuniMusicSongListItemRO();
            socialuniMusicRoomSongListRO.setSongId("1397345903");
            socialuniMusicRoomSongListRO.setMusicName("悬溺");
            socialuniMusicRoomSongListRO.setMusicSinger("葛东琪");
            socialuniMusicRoomSongListRO.setMusicTime(197083);
            socialuniMusicSongListItemROS.add(socialuniMusicRoomSongListRO);
        }

        //如果处于播放状态，后台需要有计时功能

        return ResultRO.success(socialuniMusicSongListItemROS);
    }
}
