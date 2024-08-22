package com.socialuni.social.music.sdk.controller;

import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.music.sdk.model.QO.SocialuniMusicRoomPlaySongQO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RequestMapping("socialuni/musicRoom")
@RestController
@Slf4j
public class SocialuniMusicRoomController {

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



}
