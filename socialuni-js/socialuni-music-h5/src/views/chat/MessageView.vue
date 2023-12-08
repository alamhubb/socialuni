<template>
  <div class="flex-row h100p overflow-hidden">
    <div class="w200 bd-radius shadow h100p flex-none">
      <socialuni-chat-view-h5></socialuni-chat-view-h5>
    </div>


    <div class="flex-1 overflow-hidden h100p ml-sm row-all-center bg-grey9">
      <music-player :model-value="musicRoomInfo" :data="songList" :has-operate-auth="hasOperateAuth"
                    @change="musicRoomInfoChange"></music-player>
    </div>
  </div>
</template>

<script lang="ts">
import {Component, Vue, Watch} from 'vue-facing-decorator';
import SocialuniChatViewH5 from "socialuni-im-view-h5/src/views/SocialuniChatViewH5.vue"
import SocialuniMsgViewH5 from "socialuni-im-view-h5/src/views/SocialuniMsgViewH5.vue"
import musicRequest from "@/plugins/musicRequest";
import MusicPlayer from "@/components/MusicPlayer.vue";
import MusicPlayerSongPlayingInfoRO from "socialuni-music-sdk/src/model/MusicPlayerSongPlayingInfoRO.ts";
import socialuniMusicStore from "socialuni-music-sdk/src/store/SocialuniMusicStore.ts";
import SocialuniMusicRoleId from "socialuni-music-sdk/src/constant/SocialuniMusicRoleId.ts";
import MusicPlayerSongInfoRO from "socialuni-music-sdk/src/model/MusicPlayerSongInfoRO.ts";
import SocialuniMusicAPI from "socialuni-music-sdk/src/api/SocialuniMusicAPI.ts";

@Component({
  components: {MusicPlayer, SocialuniChatViewH5, SocialuniMsgViewH5}
})
export default class MessageView extends Vue {

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
        title: row.name,
        author: row.ar?.map(item => item.name),
        album: row.al.name,
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

  musicRoomInfoChange(musicRoomInfo: MusicPlayerSongPlayingInfoRO) {
    socialuniMusicStore.setMusicRoomInfo(musicRoomInfo)
  }

  playMusicApiFun() {
    SocialuniMusicAPI.playMusicAPI(socialuniMusicStore.channelName, this.modelValue).then(res => {
      //不为播放结束，或者不为暂停，则代表正在播放
      this.change(res.data)
    })
  }
}
</script>
