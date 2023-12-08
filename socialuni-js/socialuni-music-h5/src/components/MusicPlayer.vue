<template>
  <div class="h800 w600 overflow-hidden phone bd-radius bg-white shadow-5 pd flex-col">
    <audio ref="audioPlayer" :src="modelValue?.musicUrl"></audio>

    <div class="flex-1 overflow-hidden">
      音量：{{ socialuniMusicStore.musicVolume }}
      <!--      <div>
              realPlayingValue：{{ realPlayingValue }}
            </div>
            <div>
              {{ modelValue }}
            </div>-->

      <el-table height="100%" :data="data" stripe highlight-current-row
                @row-dblclick="handleCurrentChange">
        <el-table-column prop="title" label="音乐标题" width="100" show-overflow-tooltip></el-table-column>
        <el-table-column label="歌手" show-overflow-tooltip>
          <template #default="scope">
            {{ scope.row.author.join(' / ') }}
          </template>
        </el-table-column>
        <el-table-column label="歌手专辑" show-overflow-tooltip>
          <template #default="scope">
            {{ scope.row.album }}
          </template>
        </el-table-column>
        <el-table-column label="时长" width="100">
          <template #default="scope">
            {{ $DateUtil.convertToTime(scope.row.musicTime) }}
          </template>
        </el-table-column>
      </el-table>

    </div>

    <div class="flex-none">
      <div>
        <!--      <div v-if="musicRoomInfo?.musicUrl">-->
        <div class="row-between-center">
          <div class="w20p flex-none">
            123
          </div>
          <div class="w40p flex-none">
            <div class="row-all-center">
              <div v-if="hasOperateAuth" class="row-col-center">
                <!--                            <i @click="isChangeLike" v-if="!isLike" title="收藏" class="mdi mdi-star-outline"></i>-->
                <!--                            <i @click="isChangeLike" v-else style="color: red;font-size: 22px;" title="已收藏"  class="mdi mdi-star"></i>-->
                <i title="上一曲" @click="next(-1)" class="mdi mdi-skip-previous"></i>
                <i @click="continuePlay" style="font-size: 40px; color: #cc7013;"
                   class="mdi"
                   :class="[musicPlaying ? 'mdi-pause' :'mdi-play']"></i>
                <i title="下一曲" @click="next(1)" class="mdi mdi-skip-next"></i>
              </div>

              <div v-else class="row-col-center">
                <i @click="mutedMusic" style="font-size: 40px; color: #cc7013;"
                   class="mdi"
                   :class="[musicMuted ? 'mdi-volume-mute' :'mdi-volume-medium']"></i>
              </div>
            </div>
            <div class="row-col-center">
              <div class="flex-none">{{ curPlayingTime }}</div>
              <el-slider class="flex-1" v-model="realPlayingValue" @input="musicInput" @change="musicChange"
                         :show-tooltip="false"
                         :disabled="!hasOperateAuth"
                         :max="musicMax"></el-slider>
              <div class="flex-none">{{ formatTooltip(musicMax) }}</div>
            </div>
          </div>
          <div class="w20p flex-none">
            <i class="mdi mdi-volume-mute flex-none" :class="[musicMuted ? 'mdi-volume-mute' :'mdi-volume-medium']" @click="setPlayerCurTimeAndPlay"></i>


            <el-slider class="flex-1" v-model="socialuniMusicStore.musicVolume" @input="musicVolumeInput"
                       :show-tooltip="false"></el-slider>
          </div>
        </div>
      </div>
    </div>


    <!--            <audio ref="audioPlayer" src="https://music.163.com/song/media/outer/url?id=2100329027.mp3" autoplay muted ></audio>-->

    <!--            <div class="w100p">
                    <audio id="local" :src="test1" controls="controls"
                           style="height: 200px;width: 500px;"></audio>
                    <audio id="remote" style="height: 200px;width: 500px;" controls="controls"></audio>
                </div>-->

    <div>
      <div></div>

      <!--        如果为roleid = ower或者admin，显示， 如果musicurl有值显示， 否则不显示-->

      <!--        <div>
                <el-button
                    @click="terstfasd('https://cdxapp-1257733245.file.myqcloud.com/opentest/M800000puzgO0yRX1o.mp3')">
                  创建
                </el-button>
                <div>
                  <audio ref="audioPlayer" autoplay muted controls id="audio"
                         src="https://music.163.com/song/media/outer/url?id=1456890009.mp3"></audio>

                  <audio id="localVideo" autoplay muted controls></audio>
                  <audio id="remoteVideo" autoplay controls></audio>
                  <div>
                    <el-button @click="start">播放</el-button>
                    <el-button @click="stop">Stop</el-button>
                  </div>
                </div>
                <el-button @click="terstfasd111">查询</el-button>
                <el-button @click="queryAllplay">查询播放器</el-button>
                <el-button @click="destoryPlays">销毁播放器</el-button>
                <el-button @click="deleteYun111">停止</el-button>
                <el-button @click="jixuYun111">继续</el-button>
              </div>-->
    </div>

    <!--            <socialuni-msg-view-h5></socialuni-msg-view-h5>-->

  </div>
</template>

<script lang="ts">
import {Component, Emit, Model, Prop, Vue, Watch} from 'vue-facing-decorator';
import CommonEventUtil from "socialuni-native-util/src/util/CommonEventUtil.ts";
import SocialuniImEventKey from "socialuni-im-api/src/constant/SocialuniMusicEventConst.ts";
import socialuniMusicStore from "socialuni-music-sdk/src/store/SocialuniMusicStore.ts";
import AlertUtil from "socialuni-native-h5/src/util/AlertUtil.ts";
import {nextTick} from "vue";
import MusicPlayerSongInfoRO from "socialuni-music-sdk/src/model/MusicPlayerSongInfoRO.ts";
import MusicPlayerSongPlayingInfoRO from "socialuni-music-sdk/src/model/MusicPlayerSongPlayingInfoRO.ts";
import ToastUtil from "socialuni-native-h5/src/util/ToastUtil.ts";
import DateUtil from "socialuni-util/src/util/DateUtil.ts";

@Component({
  components: {}
})
export default class MusicPlayer extends Vue {
  $refs: {
    audioPlayer: HTMLAudioElement
  }
  @Model('modelValue') modelValue: MusicPlayerSongPlayingInfoRO
  @Prop() data: MusicPlayerSongInfoRO []
  @Prop() hasOperateAuth: boolean

  get socialuniMusicStore() {
    return socialuniMusicStore
  }

  get watchModelValueObj() {
    return {
      modelValue: this.modelValue,
      dragging: this.dragging,
    }
  }

  @Watch('watchModelValueObj')
  watchModelValueChange() {
    console.log('111111111')
    if (this.modelValue) {
      this.computedRealPlayingValue()
      this.setPlayerCurTimeAndPlay()
      const volume = this.$refs.audioPlayer.volume
      socialuniMusicStore.musicVolume = Math.ceil(volume * 100)
      socialuniMusicStore.musicMuted = this.$refs.audioPlayer.muted
      if (this.timer) {
        clearInterval(this.timer)
        this.timer = null
      }
      //不为播放，且不为拖拽才定时增长
      if (this.modelValue.playing && !this.dragging) {
        this.timer = setInterval(() => {
          this.computedRealPlayingValue()
        }, this.playingUnit)
      }
    }
  }


  hintMusicPlayingNum = 0

  //创建时设置musicTime
  //然后不需要播放

  get musicMax() {
    if (this.modelValue) {
      return this.modelValue.musicTime * this.secondPlayingUnit
      // return 500 * 100
    }
    return 0
  }

  private timer = null

  get musicPlaying() {
    return this.modelValue && (this.modelValue.playing)
  }

  get musicMuted() {
    return socialuniMusicStore.musicMuted
  }


  setPlayerCurTimeAndPlay() {
    if (!this.dragging) {
      console.log('3333333333')
      console.log(this.modelValue?.playing)
      nextTick(() => {
        console.log('5555555555555')
        if (this.modelValue?.playing) {
          // console.log(this.$refs.audioPlayer.paused || this.$refs.audioPlayer.ended)
          // if (!this.$refs.audioPlayer.paused || !this.$refs.audioPlayer.ended) {
          console.log('shezhile  curtime')
          this.setMusicCurTime()
          this.frontPlay()
          // }
        } else {
          this.setMusicCurTime()
          this.frontPause()
        }
      })
    }
  }

  setMusicCurTime() {
    this.$refs.audioPlayer.currentTime = Math.floor(this.realPlayingValue / this.secondPlayingUnit)
  }


  mounted() {
    if (this.$route.query.chatId) {
      CommonEventUtil.emit(SocialuniImEventKey.socialuniImPageInit, this.$route.query)
    }

    /*if (this.musicRoomInfo) {
        if (this.musicRoomInfo.playing) {
        }
    }*/

    // this.init()
    // this.getMusic()
    // this.queryMusicRoomPlayer()
  }


  dragging = false

  musicVolumeInput(value) {
    this.$refs.audioPlayer.volume = value / 100
    socialuniMusicStore.musicVolume = value
  }

  musicInput(value) {

    this.checkRoleId()

    this.dragging = true
    //秒，
    const playTime = Math.floor(value / this.secondPlayingUnit)

    //所以播放时间也要为秒
    // if (this.modelValue.playing) {
    //   this.$refs.audioPlayer.currentTime = playTime
    // }

    const curTime = new Date()

    this.input({
      musicTime: this.modelValue.musicTime,
      musicUrl: this.modelValue.musicUrl,
      playingTimestamp: curTime,
      //单位秒
      playingTime: playTime,
      playing: this.modelValue.playing,
    })
    // this.computedRealPlayingValue(false)
  }


  musicChange() {
    this.checkRoleId()
    this.dragging = false
    this.change(this.modelValue)
  }

  //初始化的播放怎么做

  continuePlay() {
    const playing: boolean = !this.musicPlaying
    if (!this.hasOperateAuth) {
      ToastUtil.throwError('没有操作权限')
    }
    if (playing) {
      //如何判断是继续播放还是重新播放
      //根据playTime决定
      if (this.modelValue?.musicUrl) {
        console.log('chufale  chognxinjiaza2222')
        const playRoomInfo = {
          musicTime: this.modelValue.musicTime,
          musicUrl: this.modelValue.musicUrl,
          playingTimestamp: new Date(),
          //单位秒
          playingTime: this.realPlayingValue / this.secondPlayingUnit,
          playing: playing,
        }
        this.change(playRoomInfo)
      }
    } else {
      this.$refs.audioPlayer.pause()
      const playRoomInfo = {
        musicTime: this.modelValue.musicTime,
        musicUrl: this.modelValue.musicUrl,
        playingTimestamp: new Date(),
        //单位秒
        playingTime: this.realPlayingValue / this.secondPlayingUnit,
        playing: playing,
      }
      this.change(playRoomInfo)
    }
  }

  mutedMusic() {
    if (this.musicMuted) {
      this.$refs.audioPlayer.muted = false
      socialuniMusicStore.musicMuted = false
    } else {
      this.$refs.audioPlayer.muted = true
      socialuniMusicStore.musicMuted = true
    }
  }


  private _realPlayingValue = 0

  get realPlayingValue() {
    return this._realPlayingValue
  }

  computedRealPlayingValue() {
    // if (!dragging) {
    const curDate = new Date().getTime()
    const playTime = new Date(this.modelValue.playingTimestamp).getTime()
    //得到已播放时间的时间差
    const diffTime = curDate - playTime

    //什么情况下为0，是播放完成后
    //进度为0.01秒
    if (this.modelValue.playing) {
      this._realPlayingValue = Math.floor(diffTime / this.playingUnit) + this.modelValue.playingTime * this.secondPlayingUnit
    } else {
      this._realPlayingValue = this.modelValue.playingTime * this.secondPlayingUnit
    }
    if (!this.dragging) {
      if (this._realPlayingValue >= this.musicMax && this.modelValue.playing) {
        if (this.hasOperateAuth) {
          this.next(1)
        } else {
          /*this.input({
            musicTime: this.modelValue.musicTime,
            musicUrl: this.modelValue.musicUrl,
            playingTimestamp: new Date(),
            //单位秒
            playingTime: 0,
            playing: false,
          })*/
        }
      }
    }
    // }
  }


  created() {
    // this.getMusic()
  }

  handleCurrentChange(row) {
    // mucisRoomStore.publish(this.songId)
    this.playMusicAPI(row.songId)
  }

  get curPlayingTime() {
    return this.formatTooltip(this.realPlayingValue)
  }

  playingUnit = 10;

  get secondPlayingUnit() {
    return 1000 / this.playingUnit
  }

  formatTooltip(value) {
    const time = Math.floor(value / this.secondPlayingUnit) * 1000
    return DateUtil.convertToTime(time)
  }

  checkRoleId() {
    if (!this.hasOperateAuth) {
      AlertUtil.error("您没有操作权限")
    }
  }


  async playMusicAPI(songId) {
    this.checkRoleId()
    //主要是为了设置url
    const musicUrl = `https://music.163.com/song/media/outer/url?id=${songId}.mp3`;
    const playRoomInfo = {
      musicTime: 0,
      musicUrl: musicUrl,
      playingTimestamp: new Date(),
      //单位秒
      playingTime: 0,
      playing: false,
    }
    this.input(playRoomInfo)

    //更新音乐时长
    this.$refs.audioPlayer.onloadedmetadata = () => {
      console.log('2222222222')
      const curTime = new Date()
      const musicRoomInfo = new MusicPlayerSongPlayingInfoRO({
        musicTime: this.$refs.audioPlayer.duration,
        musicUrl: this.modelValue.musicUrl,
        playingTimestamp: curTime,
        //单位秒
        playingTime: 0,
        playing: true,
      })
      this.change(musicRoomInfo)
      // this.frontPlay()
    };
  }

  async frontPlay() {
    console.log('666666666666g')
    if (this.modelValue.playing) {
      try {
        if (this.$refs.audioPlayer.paused || this.$refs.audioPlayer.ended) {
          console.log('chufale bofang')
          await this.$refs.audioPlayer.play()
        }
      } catch (e) {
        if (this.hintMusicPlayingNum < 2) {
          this.hintMusicPlayingNum++
          AlertUtil.confirm('有音乐正在播放，是否打开声音').then(() => {
            this.setPlayerCurTimeAndPlay()
          })
        }
      }
    }
  }

  next(num: number) {
    this.checkRoleId()
    console.log(this.modelValue.title)
    const index = this.data.findIndex(item => `https://music.163.com/song/media/outer/url?id=${item.songId}.mp3` === this.modelValue.musicUrl)
    const nextIndex = index + num
    if (!this.data.length) {
      return;
    }
    let nextSong
    if (nextIndex >= 0 && nextIndex < this.data.length) {
      nextSong = this.data[nextIndex]
    } else {
      nextSong = this.data[0]
    }
    this.playMusicAPI(nextSong.songId)
  }

  async frontPause() {
    if (!this.$refs.audioPlayer.paused && !this.$refs.audioPlayer.ended) {
      this.$refs.audioPlayer.pause()
    }
  }

  @Emit()
  input(playingInfo: MusicPlayerSongPlayingInfoRO): MusicPlayerSongPlayingInfoRO {
    return playingInfo
  }

  @Emit()
  change(playingInfo: MusicPlayerSongPlayingInfoRO): MusicPlayerSongPlayingInfoRO {
    return playingInfo
  }

}
</script>
