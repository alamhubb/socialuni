<template>
  <div class="h100p">
    <div>在线人数：{{onlineUsersCount}}</div>
    <div>总积分1个亿</div>

    <!--    <div class="flex-row row-wrap">
          <div v-for="i in 360">
            <div class="flex-col bd">
              <div>
                第{{ getData(i).monthIndex + 1 }}个月
              </div>
              <div>
                第{{ getData(i).dayIndex }}天
              </div>
              <div>
                分配{{ getData(i).oneMonthDayConsume }}
              </div>
              <div>
                奖池剩余{{ pool }}
              </div>
            </div>
          </div>
        </div>-->

    <!--    <q-input v-model="musicSearchText" class="w150" @keydown.enter="searchSongList" @clear="clearSearch"></q-input>
        {{searchData}}
        &lt;!&ndash;    <div>{{ musicRoomId }}</div>&ndash;&gt;
        &lt;!&ndash;    <div>{{ musicRoomInfo }}</div>&ndash;&gt;
        &lt;!&ndash;    <music-player ref="musicPlayer" :cur-music-info="musicRoomInfo" :data="songList" :has-operate-auth="true"&ndash;&gt;
        <music-player ref="musicPlayer" :cur-music-info="musicRoomInfo" :has-operate-auth="true"
                      @input="musicRoomInfoInput" @change="musicRoomInfoChange" @next="next"></music-player>
        <music-list class="h500" :data="songList" @change="listMusicChange" :cur-music="musicRoomInfo"></music-list>
        <music-list class="h500" :data="songList" @change="listMusicChange" :cur-music="musicRoomInfo"></music-list>-->
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

@toNative
@Component({
  components: {QInput, MusicPlayer, SocialuniChatViewH5, SocialuniMsgViewH5, MusicList}
})
export default class MusicHome extends Vue {

  musicSearchText = ''

  songList: MusicPlayerSongInfoRO[] = []

  get onlineUsersCount(){
    return socialAppModule.onlineUsersCount
  }

  created() {
    this.initRoomId()
    this.querySongList()
    this.pool = 600
    this.printPoint()
    // this.searchSongList()
  }

  printPoint() {
    const all = 18000


    let pool = all

    //总积分数 3100w
    //每日销毁 1w，1000天销毁 1000w

    const monthDay = 30

    //第一个月，他将获得0.1%的股权，  0.01的收益权，真的不多了。 1.1亿积分。每日销毁1w积分。


    const ary = [105, 75, 75, 50, 50, 50, 35, 35, 35, 30, 30, 30, 30, 30, 30, 25, 25, 25, 25, 25, 25, 20, 20, 20, 15, 15, 10, 10, 10, 10, 5, 5, 5, 5, 5, 5]

    console.log(ary.reduce((accumulator, currentValue) => accumulator + currentValue))
    console.log(1111111111111111)
    for (let i = 0; i < 1080; i++) {
      const monthIndex = Math.floor(i / 30)
      const dayIndex = (i % 30) + 1
      const monthAssign = ary[monthIndex] * all / 1000
      const oneMonthDayConsume = monthAssign / monthDay
      pool = pool - oneMonthDayConsume
      if (dayIndex === 30) {
        const curDate = new Date()
        const nextMonth = curDate.getMonth()
        const year = curDate.getFullYear()

        const month = ((nextMonth + monthIndex + 1))
        const monthStr = (month % 12) + 1

        const yearNum = Math.floor(month / 12)


        console.log(`${year + yearNum}年，${monthStr}月，每日分配${oneMonthDayConsume}w积分`)
        // console.log(`${year + yearNum}年，${monthStr}月，分配${oneMonthDayConsume}，奖池剩余${pool},本月消耗${all - pool}`)
      }
    }
    // const oneMonthDayConsume = oneMonth / monthDay
    //配置每个月的积分消耗
  }


  pool = 6000

  getData(i) {

    i = i - 1

    const all = 6000

    //总积分数 3100w
    //每日销毁 1w，1000天销毁 1000w

    const monthDay = 30

    const ary = [105, 75, 75, 50, 50, 50, 35, 35, 35, 30, 30, 30]

    const monthIndex = Math.floor(i / 30)
    const dayIndex = (i % 30) + 1
    const monthAssign = ary[monthIndex] * all / 1000
    const oneMonthDayConsume = monthAssign / monthDay
    this.pool = this.pool - oneMonthDayConsume

    console.log(`第${monthIndex + 1}月，第${dayIndex}天，分配${oneMonthDayConsume}，奖池剩余${this.pool}`)
    return {
      monthIndex, dayIndex, oneMonthDayConsume
    }
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

  searchData = []

  async searchSongList() {
    const res = await musicRequest.get('/cloudsearch?offset=0&limit=30&keywords=阿普的思念')
// this.searchData = res.result.songs
    const data = res.result.songs
    const songList: MusicPlayerSongInfoRO[] = data.map((row: any) => {
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
  }

  clearSearch() {

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
