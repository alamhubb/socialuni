<template>
  <div class="flex-row h100p overflow-hidden">
    <div class="w200 bd-radius shadow h100p flex-none">
      <socialuni-chat-view-h5></socialuni-chat-view-h5>
    </div>


    <div class="flex-1 overflow-hidden h100p ml-sm row-all-center bg-grey9">
      <music-player :data="songList"></music-player>
    </div>
  </div>
</template>

<script lang="ts">
import {Component, Vue, Watch} from 'vue-facing-decorator';
import SocialuniChatViewH5 from "socialuni-im-view-h5/src/views/SocialuniChatViewH5.vue"
import SocialuniMsgViewH5 from "socialuni-im-view-h5/src/views/SocialuniMsgViewH5.vue"
import musicRequest from "@/plugins/musicRequest";
import SocialuniMusicAPI from "socialuni-music-sdk/src/api/SocialuniMusicAPI";
import CommonEventUtil from "socialuni-native-util/src/util/CommonEventUtil";
import SocialuniImEventKey from "socialuni-im-api/src/constant/SocialuniMusicEventConst";
import socialuniMusicStore from "socialuni-music-sdk/src/store/SocialuniMusicStore";
import WebsocketWebRtcUtil from "socialuni-api-base/src/websocket/WebsocketWebRtcUtil";
import test1 from './test1.mp3'
import SocialuniMusicRoleId from "socialuni-music-sdk/src/constant/SocialuniMusicRoleId";
import AlertUtil from "socialuni-native-h5/src/util/AlertUtil";
import {nextTick} from "vue";
import MusicPlayer from "@/components/MusicPlayer.vue";
import MusicPlayerSongInfoRO from "@/components/MusicPlayerSongInfoRO.ts";

@Component({
  components: {MusicPlayer, SocialuniChatViewH5, SocialuniMsgViewH5}
})
export default class MessageView extends Vue {

  songList: MusicPlayerSongInfoRO[] = []

  created() {
    this.querySongList()
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
  }
}
</script>
