<template>
  <div class="h100p flex-col overflow-hidden">
    <div class="row-between-center px-sm bb h50 flex-none">
      <div>热门歌曲</div>

      <q-input v-model="musicSearchText" class="w150" @keydown.enter="searchSongList"
               @clear="clearSearch"></q-input>
    </div>
    <music-list v-if="songList.length" class="flex-1 overflow-hidden" :data="songList" @change="hotSongListMusicChange"
                :cur-music="musicRoomInfo"></music-list>

    <music-list v-else class="flex-1 overflow-hidden" :data="hotSongList" @change="hotSongListMusicChange"
                :cur-music="musicRoomInfo"></music-list>
  </div>
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
import MusicList from "socialuni-music-ui-h5/src/components/MusicList.vue";
import {socialuniChatModule} from "socialuni-im-sdk/src/store/SocialuniChatModule.ts";
import QInput from "qingjs-ui/src/components/QInput.vue";
import {socialAppModule} from "socialuni-app-sdk/src/store/SocialAppModule.ts";
import QDialog from "qingjs-ui-h5/src/components/QDialog.vue";

@toNative
@Component({
  components: {QDialog, QInput, SocialuniChatViewH5, SocialuniMsgViewH5, MusicList}
})
export default class MusicDialog extends Vue {
  $refs: {
    musicDialog: QDialog
  }

  showSearchSongList = false


  musicSearchText = ''

  hotSongList: MusicPlayerSongInfoRO[] = []

  songList: MusicPlayerSongInfoRO[] = []

  get onlineUsersCount() {
    return socialAppModule.onlineUsersCount
  }

  created() {
    this.querySongList()
    // this.searchSongList()
  }

  async initHandler() {
    await this.initRoomId()
  }

  //查询房间的播放信息

  async initRoomId() {
    // 如果没有房间id，则查询默认系统开放大厅id
    let chatId = this.$route.query.chatId
    if (!chatId) {
      const res = await SocialuniMusicAPI.getPublicRoomId();
      chatId = res.data
    }
    await socialuniChatModule.init({chatId})
    console.log(socialuniChatModule.chatId)
  }

  get hasOperateAuth() {
    console.log(23232323)
    console.log(socialuniMusicStore.musicRoleId)
    return SocialuniMusicRoleId.hasOperateAuthList.includes(socialuniMusicStore.musicRoleId)
  }


  get musicRoomId() {
    return socialuniMusicStore.musicRoomId
  }

  get musicRoomInfo() {
    if (socialuniMusicStore.musicRoomInfo) {
      return socialuniMusicStore.musicRoomInfo
    }
    return null
  }

  async searchSongList() {
    const res = await musicRequest.get('/cloudsearch?offset=0&limit=30&keywords=' + this.musicSearchText)
// this.searchData = res.result.songs
    const data = res.result.songs
    const songList: MusicPlayerSongInfoRO[] = data.map((row: any) => {
      const songRO: MusicPlayerSongInfoRO = new MusicPlayerSongInfoRO({
        songId: row.id,
        musicName: row.name,
        author: row.ar?.map(item => item.name).join(','),
        album: row.al.name,
        albumImg: row.al.picUrl,
        musicTime: row.dt,
        musicUrl: `https://music.163.com/song/media/outer/url?id=${row.id}.mp3`,
      })
      return songRO
    })
    this.songList = songList
  }

  clearSearch() {
    this.songList = []
  }

  async querySongList() {
    const data = await musicRequest.get('/playlist/detail?id=3778678') as any
    const songList: MusicPlayerSongInfoRO[] = data.playlist.tracks.map((row: any) => {
      const songRO: MusicPlayerSongInfoRO = new MusicPlayerSongInfoRO({
        songId: row.id,
        musicName: row.name,
        author: row.ar?.map(item => item.name).join(','),
        album: row.al.name,
        albumImg: row.al.picUrl,
        musicTime: row.dt,
        musicUrl: `https://music.163.com/song/media/outer/url?id=${row.id}.mp3`,
      })
      return songRO
    })
    this.hotSongList = songList
  }

  //热门歌曲事件，追加至播放列表
  async hotSongListMusicChange(musicRoomInfo: MusicPlayerSongPlayingInfoRO) {
    console.log('chufale host change')
    const newSongRO = new MusicPlayerSongInfoRO(musicRoomInfo)
    newSongRO.no = this.songList.length + 1
    socialuniMusicStore.songList.push(newSongRO)
    await SocialuniMusicAPI.joinSongListAPI(socialuniMusicStore.musicRoomId, musicRoomInfo)
    // this.querySongListNew()
    socialuniMusicStore.querySongList()
  }
}
</script>
