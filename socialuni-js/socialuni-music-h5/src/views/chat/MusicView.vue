<template>
  <music-player :model-value="musicRoomInfo" :data="songList" :has-operate-auth="hasOperateAuth"
                @input="musicRoomInfoInput" @change="musicRoomInfoChange"></music-player>
</template>

<script lang="ts">
import {Component, Vue, Watch, toNative} from 'vue-facing-decorator';
import SocialuniChatViewH5 from "socialuni-im-view-h5/src/views/SocialuniChatViewH5.vue"
import SocialuniMsgViewH5 from "socialuni-im-view-h5/src/views/SocialuniMsgViewH5.vue"
import MusicPlayerSongPlayingInfoRO from "socialuni-music-sdk/src/model/MusicPlayerSongPlayingInfoRO.ts";
import socialuniMusicStore from "socialuni-music-sdk/src/store/SocialuniMusicStore.ts";
import SocialuniMusicRoleId from "socialuni-music-sdk/src/constant/SocialuniMusicRoleId.ts";
import MusicPlayerSongInfoRO from "socialuni-music-sdk/src/model/MusicPlayerSongInfoRO.ts";
import SocialuniMusicAPI from "socialuni-music-sdk/src/api/SocialuniMusicAPI.ts";
import musicRequest from "socialuni-music-sdk/src/plugins/musicRequest";
import MusicPlayer from "socialuni-music-ui-h5/src/components/MusicPlayer.vue";

@toNative
@Component({
  components: {MusicPlayer, SocialuniChatViewH5, SocialuniMsgViewH5}
})
export default class MusicView extends Vue {

  songList: MusicPlayerSongInfoRO[] = []

  created() {
    this.querySongList()
  }

  get hasOperateAuth() {
    console.log(23232323)
    console.log(socialuniMusicStore.musicRoleId)
    return SocialuniMusicRoleId.hasOperateAuthList.includes(socialuniMusicStore.musicRoleId)
  }

  get musicRoomInfo() {
    return socialuniMusicStore.musicRoomInfo
  }

  async querySongList() {
    const data = await musicRequest.get('/playlist/detail?id=3778678') as any
    const songList: MusicPlayerSongInfoRO[] = data.playlist.tracks.map((row: any) => {
      const songRO: MusicPlayerSongInfoRO = {
        songId: row.id,
        name: row.name,
        author: row.ar?.map(item => item.name),
        album: row.al.name,
        albumImg: row.al.picUrl,
        musicTime: row.dt,
        musicUrl: `https://music.163.com/song/media/outer/url?id=${row.id}.mp3`,
      }
      return songRO
    })
    this.songList = songList
    if (this.songList.length > 0) {
      console.log(1312312)
      console.log(this.musicRoomInfo)
      if (!this.musicRoomInfo) {
        const musicRoomInfo = new MusicPlayerSongPlayingInfoRO(this.songList[0])
        musicRoomInfo.musicTime = Math.floor(musicRoomInfo.musicTime / 1000)
        console.log(this.songList[0])
        console.log(musicRoomInfo)
        console.log(this.musicRoomInfo)
        socialuniMusicStore.setMusicRoomInfo(musicRoomInfo)
        console.log(1312312)
        console.log(this.musicRoomInfo)
      }
    }
  }

  musicRoomInfoInput(musicRoomInfo: MusicPlayerSongPlayingInfoRO) {
    socialuniMusicStore.setMusicRoomInfo(musicRoomInfo)
  }

  musicRoomInfoChange(musicRoomInfo: MusicPlayerSongPlayingInfoRO) {
    //必须深拷贝，不这么写会导致一致，导致不播放
    this.musicRoomInfoInput(musicRoomInfo)
    SocialuniMusicAPI.playMusicAPI(socialuniMusicStore.channelName, musicRoomInfo).then(res => {
      const data: MusicPlayerSongPlayingInfoRO = res.data
      // 不相同才替换
      if (data.playingTime !== musicRoomInfo.playingTime
          || data.playing !== musicRoomInfo.playing
      ) {
        console.log(data.playing !== musicRoomInfo.playing)
        console.log('queshiyou buyizhi')
        socialuniMusicStore.setMusicRoomInfo(res.data)
      }
    })
  }
}
</script>
