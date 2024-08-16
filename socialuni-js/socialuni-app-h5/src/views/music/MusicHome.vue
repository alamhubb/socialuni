<template>
  <div class="h100p">
    <q-input v-model="musicSearchText" class="w150" @keydown.enter="querySongList" @clear="clearSearch"></q-input>
    <!--    <div>{{ musicRoomId }}</div>-->
    <!--    <div>{{ musicRoomInfo }}</div>-->
    <!--    <music-player ref="musicPlayer" :cur-music-info="musicRoomInfo" :data="songList" :has-operate-auth="true"-->
    <music-player ref="musicPlayer" :cur-music-info="musicRoomInfo" :has-operate-auth="true"
                  @input="musicRoomInfoInput" @change="musicRoomInfoChange" @next="next"></music-player>
    <music-list class="h500" :data="songList" @change="listMusicChange" :cur-music="musicRoomInfo"></music-list>
    <music-list class="h500" :data="songList" @change="listMusicChange" :cur-music="musicRoomInfo"></music-list>
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

@toNative
@Component({
  components: {QInput, MusicPlayer, SocialuniChatViewH5, SocialuniMsgViewH5, MusicList}
})
export default class MusicHome extends Vue {

  musicSearchText = ''

  songList: MusicPlayerSongInfoRO[] = []

  created() {
    this.initRoomId()
    this.querySongList()
  }

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
      const curMusicRoom = this.songList.find(item => item.musicUrl === socialuniMusicStore.musicRoomInfo.musicUrl)
      const copyRoomInfo = new MusicPlayerSongPlayingInfoRO({
        ...curMusicRoom,
        ...socialuniMusicStore.musicRoomInfo
      })
      return copyRoomInfo
    }
    return null
  }

  clearSearch(){

  }

  async querySongList() {
    const data = await musicRequest.get('/playlist/detail?id=3778678') as any
    const songList: MusicPlayerSongInfoRO[] = data.playlist.tracks.map((row: any) => {
      const songRO: MusicPlayerSongInfoRO = new MusicPlayerSongInfoRO({
        songId: row.id,
        name: row.name,
        author: row.ar?.map(item => item.name),
        album: row.al.name,
        albumImg: row.al.picUrl,
        musicTime: row.dt,
        musicUrl: `https://music.163.com/song/media/outer/url?id=${row.id}.mp3`,
      })
      return songRO
    })
    this.songList = songList
    if (this.songList.length > 0) {
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
    }
  }

  musicRoomInfoInput(musicRoomInfo: MusicPlayerSongPlayingInfoRO) {
    socialuniMusicStore.setMusicRoomInfo(musicRoomInfo)
  }

  listMusicChange(musicRoomInfo: MusicPlayerSongPlayingInfoRO) {
    musicRoomInfo.playing = true
    console.log(musicRoomInfo)
    this.musicRoomInfoChange(musicRoomInfo)
  }

  //哪些操作会改变后端数据，切换会变
  musicRoomInfoChange(musicRoomInfo: MusicPlayerSongPlayingInfoRO) {
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
    const index = this.songList.findIndex(item => `https://music.163.com/song/media/outer/url?id=${item.songId}.mp3` === this.musicRoomInfo.musicUrl)
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
    })
    this.listMusicChange(playRoomInfo)
  }
}
</script>
