<template>
  <q-dialog ref="musicDialog" title="鹿森音乐" width="1000px" hide-footer :show-close="false">
    <div class="flex-row h100p overflow-hidden">
      <music-discover class="h100p flex-1 overflow-hidden br"></music-discover>
      <music-playing-list class="h100p flex-1 overflow-hidden"></music-playing-list>
    </div>
  </q-dialog>
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
import MusicDiscover from "./MusicDiscover.vue";
import MusicPlayingList from "./MusicPlayingList.vue";

@toNative
@Component({
  components: {MusicPlayingList, MusicDiscover, QDialog, QInput, SocialuniChatViewH5, SocialuniMsgViewH5, MusicList}
})
export default class MusicDialog extends Vue {
  $refs: {
    musicDialog: QDialog
  }

  open() {
    this.$refs.musicDialog.open()
  }


  musicSearchText = ''

  hotSongList: MusicPlayerSongInfoRO[] = []

  get onlineUsersCount() {
    return socialAppModule.onlineUsersCount
  }

  created() {
    this.querySongList()
    // this.searchSongList()
  }

  get songList() {
    return socialuniMusicStore.songList
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

  searchData = []

  async searchSongList() {
    const res = await musicRequest.get('/cloudsearch?offset=0&limit=30&keywords=阿普的思念')
// this.searchData = res.result.songs
    const data = res.result.songs
    const songList: MusicPlayerSongInfoRO[] = data.map((row: any) => {
      const songRO: MusicPlayerSongInfoRO = new MusicPlayerSongInfoRO({
        songId: row.id,
        musicName: row.name,
        author: row.ar?.map(item => item.name),
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
    /*if (this.songList.length > 0) {
      console.log(1312312)
      console.log(this.musicRoomInfo)
      if (!this.musicRoomInfo) {
        //如果没有播放信息，则设置播放信息为第一首歌曲
        const musicRoomInfo = new MusicPlayerSongPlayingInfoRO({...this.songList[0]})
        musicRoomInfo.musicTime = Math.floor(musicRoomInfo.musicTime / 1000)
        console.log(this.songList[0])
        console.log(musicRoomInfo)
        console.log(this.musicRoomInfo)
        socialuniMusicStore.setMusicRoomInfo(musicRoomInfo)
        console.log(1312312)
        console.log(this.musicRoomInfo)
      }
    }*/
  }

  musicRoomInfoInput(musicRoomInfo: MusicPlayerSongPlayingInfoRO) {
    socialuniMusicStore.setMusicRoomInfo(musicRoomInfo)
  }

  //热门歌曲事件，追加至播放列表
  async hotSongListMusicChange(musicRoomInfo: MusicPlayerSongPlayingInfoRO) {
    console.log('chufale host change')
    const newSongRO = new MusicPlayerSongInfoRO(musicRoomInfo)
    newSongRO.no = this.songList.length + 1
    this.songList.push(newSongRO)
    await SocialuniMusicAPI.joinSongListAPI(socialuniMusicStore.musicRoomId, musicRoomInfo)
    // this.querySongListNew()
    socialuniMusicStore.querySongList()
  }

  //歌单播放歌曲
  async songListPlayMusic(musicRoomInfo: MusicPlayerSongPlayingInfoRO) {
    musicRoomInfo.playing = true
    this.musicRoomInfoInput(musicRoomInfo)
    await SocialuniMusicAPI.playMusicAPI(socialuniMusicStore.musicRoomId, musicRoomInfo).then(res => {
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
    // this.querySongListNew()
    socialuniMusicStore.querySongList()
  }

  //哪些操作会改变后端数据，切换会变
  async musicRoomInfoChange(musicRoomInfo: MusicPlayerSongPlayingInfoRO) {
    //必须深拷贝，不这么写会导致一致，导致不播放
    this.musicRoomInfoInput(musicRoomInfo)
    SocialuniMusicAPI.playMusicAPI(socialuniMusicStore.musicRoomId, musicRoomInfo).then(res => {
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

  next(num) {
    console.log('chufale next')
    /*const index = this.songList.findIndex(item => `https://music.163.com/song/media/outer/url?id=${item.songId}.mp3` === this.musicRoomInfo.musicUrl)
    const nextIndex = index + num
    if (!this.songList.length) {
      return;
    }
    let nextSong
    if (nextIndex >= 0 && nextIndex < this.songList.length) {
      nextSong = this.songList[nextIndex]
    } else {
      nextSong = this.songList[0]
    }
    const playRoomInfo = new MusicPlayerSongPlayingInfoRO({
      ...nextSong
    })*/
    // this.hotSongListMusicChange(playRoomInfo)
  }
}
</script>
