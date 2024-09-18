<template>
  <!--  <div class="h100p">
      <q-input v-model="musicSearchText" class="w150" @keydown.enter="searchSongList" @clear="clearSearch"></q-input>
      {{ searchData }}
      &lt;!&ndash;    <div>{{ musicRoomId }}</div>&ndash;&gt;
      &lt;!&ndash;    <div>{{ musicRoomInfo }}</div>&ndash;&gt;
      &lt;!&ndash;    <music-player ref="musicPlayer" :cur-music-info="musicRoomInfo" :data="songList" :has-operate-auth="true"&ndash;&gt;
      <music-player ref="musicPlayer" :cur-music-info="musicRoomInfo" :has-operate-auth="true"
                    @input="musicRoomInfoInput" @change="musicRoomInfoChange" @next="next"></music-player>
      <div class="flex-row">
        <div class="flex-1 bd overflow-hidden">
          <div>热门歌曲</div>
          <music-list class="h500" :data="hotSongList" @change="hotSongListMusicChange"
                      :cur-music="musicRoomInfo"></music-list>
        </div>
        <div class="flex-1 bd overflow-hidden">
          <div>房间歌单</div>
          <music-list class="h500" :data="songList" @change="songListPlayMusic"
                      :cur-music="musicRoomInfo"></music-list>
        </div>
      </div>

      &lt;!&ndash;    <music-list class="h500" :data="songList" @change="listMusicChange" :cur-music="hotSongList"></music-list>&ndash;&gt;
      &lt;!&ndash;    <music-list class="h500" :data="songList" @change="listMusicChange" :cur-music="musicRoomInfo"></music-list>&ndash;&gt;
      &lt;!&ndash;    <music-list class="h500" :data="songList" @change="listMusicChange" :cur-music="musicRoomInfo"></music-list>&ndash;&gt;
    </div>-->
  <div class="flex-col h100p overflow-hidden color-black">
    <div class="flex-row h100p overflow-hidden">
      <div class="w240 bd-radius shadow h100p flex-none br bg-default">
        <socialuni-chat-view-h5></socialuni-chat-view-h5>
      </div>

      <div class="h100p flex-col flex-1 bg-white">
        <!--          <vue-drag-resize>-->
        <!--        <music-view class="shadow-bottom"></music-view>-->
        <!--          </vue-drag-resize>-->
        <music-player v-if="musicRoomInfo" class="bb flex-none" ref="musicPlayer" :cur-music-info="musicRoomInfo" :has-operate-auth="true"
                      @input="musicRoomInfoInput" @change="musicRoomInfoChange" @next="next"></music-player>
        <div class="bb row-col-center py-sm px-sm flex-none">在线人数（{{ onlineUsersCount }}）：{{onlineUsersNames}}</div>
        <div class="flex-1 overflow-hidden">
          <socialuni-msg-view-h5></socialuni-msg-view-h5>
        </div>
        <div>
        </div>
      </div>
    </div>
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


@Component({
  components: {QInput, MusicPlayer, SocialuniChatViewH5, SocialuniMsgViewH5, MusicList}
})
class SocialuniChatPage extends Vue {

  musicSearchText = ''

  hotSongList: MusicPlayerSongInfoRO[] = []

  get onlineUsersCount() {
    return socialAppModule.onlineUsersCount
  }

  get onlineUsersNames() {
    return socialAppModule.onlineUsers.join(',')
  }


  created() {
    this.initRoomId()
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
      socialuniChatModule.setChatIdToMessagePage(chatId)
      return
    }
    await socialuniChatModule.init({chatId})
  }

  get hasOperateAuth() {
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
      if (!this.musicRoomInfo) {
        //如果没有播放信息，则设置播放信息为第一首歌曲
        const musicRoomInfo = new MusicPlayerSongPlayingInfoRO({...this.songList[0]})
        musicRoomInfo.musicTime = Math.floor(musicRoomInfo.musicTime / 1000)
        socialuniMusicStore.setMusicRoomInfo(musicRoomInfo)
      }
    }*/
  }

  musicRoomInfoInput(musicRoomInfo: MusicPlayerSongPlayingInfoRO) {
    socialuniMusicStore.setMusicRoomInfo(musicRoomInfo)
  }

  //热门歌曲事件，追加至播放列表
  async hotSongListMusicChange(musicRoomInfo: MusicPlayerSongPlayingInfoRO) {
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
        socialuniMusicStore.setMusicRoomInfo(res.data)
      }
    })
  }

  next(num) {
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
export default toNative(SocialuniChatPage)
</script>
