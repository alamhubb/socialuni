<template>
  <div class="h800 w600 overflow-hidden phone bd-radius bg-white shadow-5 pd flex-col">
    <audio ref="audioPlayer" :src="modelValue?.musicUrl"></audio>

    <div class="flex-1 overflow-hidden">
      {{modelValue}}

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
        <div class="row-col-center">
          <div>{{ curPlayingTime }}</div>
          <el-slider v-model="realPlayingValue" @input="musicInput" @change="musicChange"
                     :show-tooltip="false"
                     :disabled="!hasOperateAuth"
                     :max="musicMax"></el-slider>
          <div>{{ formatTooltip(musicMax) }}</div>
        </div>
        <div>
          <div>
            <div v-if="hasOperateAuth">
              <!--                            <i @click="isChangeLike" v-if="!isLike" title="收藏" class="mdi mdi-star-outline"></i>-->
              <!--                            <i @click="isChangeLike" v-else style="color: red;font-size: 22px;" title="已收藏"  class="mdi mdi-star"></i>-->
              <i title="上一曲" @click="next(-1)" class="mdi mdi-skip-previous"></i>
              <i @click="continuePlay" style="font-size: 40px; color: #cc7013;"
                 class="mdi"
                 :class="[showPause ? 'mdi-pause' :'mdi-play']"></i>
              <i title="下一曲" @click="next(1)" class="mdi mdi-skip-next"></i>
            </div>

            <div v-else>
              <i @click="mutedMusic" style="font-size: 40px; color: #cc7013;"
                 class="mdi"
                 :class="[musicMuted ? 'mdi-volume-mute' :'mdi-volume-medium']"></i>
            </div>
          </div>
          <div>
            <i class="mdi mdi-volume-mute" @click="setPlayerCurTimeAndPlay"></i>
            <i class="mdi mdi-volume-medium"></i>
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
import SocialuniChatViewH5 from "socialuni-im-view-h5/src/views/SocialuniChatViewH5.vue"
import SocialuniMsgViewH5 from "socialuni-im-view-h5/src/views/SocialuniMsgViewH5.vue"
import SocialuniMusicAPI from "socialuni-music-sdk/src/api/SocialuniMusicAPI.ts";
import CommonEventUtil from "socialuni-native-util/src/util/CommonEventUtil.ts";
import SocialuniImEventKey from "socialuni-im-api/src/constant/SocialuniMusicEventConst.ts";
import socialuniMusicStore from "socialuni-music-sdk/src/store/SocialuniMusicStore.ts";
import SocialuniMusicRoleId from "socialuni-music-sdk/src/constant/SocialuniMusicRoleId.ts";
import AlertUtil from "socialuni-native-h5/src/util/AlertUtil.ts";
import {nextTick} from "vue";
import MusicPlayerSongInfoRO from "socialuni-music-sdk/src/model/MusicPlayerSongInfoRO.ts";
import MusicPlayerSongPlayingInfoRO from "socialuni-music-sdk/src/model/MusicPlayerSongPlayingInfoRO.ts";
import ToastUtil from "socialuni-native-h5/src/util/ToastUtil.ts";

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


  @Watch('modelValue')
  watchModelValueChange() {
    if (this.modelValue) {
      this.computedRealPlayingValue()
      this.setPlayerCurTimeAndPlay()
      console.log(66666)
      const volume = this.$refs.audioPlayer.volume
      socialuniMusicStore.musicVolume = Math.ceil(volume * 100)
      socialuniMusicStore.musicMuted = this.$refs.audioPlayer.muted
      console.log(socialuniMusicStore.musicVolume)
      console.log(socialuniMusicStore.musicMuted)
      if (this.timer) {
        clearInterval(this.timer)
        this.timer = null
      }
      if (this.modelValue.playing) {
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

  get showPause() {
    return this.modelValue && (this.modelValue.playing)
  }

  get musicMuted() {
    return socialuniMusicStore.musicMuted
  }


  setPlayerCurTimeAndPlay() {
    if (this.modelValue?.playing) {
      nextTick(() => {
        this.$refs.audioPlayer.currentTime = Math.floor(this.realPlayingValue / this.secondPlayingUnit)
        this.frontPlay()
      })
    } else {
      nextTick(() => {
        this.$refs.audioPlayer.currentTime = Math.floor(this.realPlayingValue / this.secondPlayingUnit)
        this.frontPause()
      })
    }
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

  musicInput(value) {
    this.checkRoleId()

    this.dragging = true
    //秒，
    const playTime = Math.floor(value / this.secondPlayingUnit)
    //所以播放时间也要为秒
    if (this.modelValue.playing) {
      this.$refs.audioPlayer.currentTime = playTime
    }

    const curTime = new Date()

    socialuniMusicStore.setMusicRoomInfo({
      musicTime: this.modelValue.musicTime,
      musicUrl: this.modelValue.musicUrl,
      playingTimestamp: curTime,
      //单位秒
      playingTime: playTime,
      playing: this.modelValue.playing,
      musicRoleId: socialuniMusicStore.musicRoomInfo.musicRoleId,
    })
    this.computedRealPlayingValue(false)
  }


  musicChange() {
    this.checkRoleId()
    this.dragging = false
    this.playMusicApiFun()
  }

  //初始化的播放怎么做

  continuePlay() {
    const playing: boolean = !this.showPause
    if (!this.hasOperateAuth) {
      ToastUtil.throwError('没有操作权限')
    }
    if (playing) {
      //如何判断是继续播放还是重新播放
      //根据playTime决定
      if (this.modelValue?.musicUrl) {
        this.frontPlay()
        const playRoomInfo = {
          musicTime: this.modelValue.musicTime,
          musicUrl: this.modelValue.musicUrl,
          playingTimestamp: new Date(),
          //单位秒
          playingTime: this.realPlayingValue / this.secondPlayingUnit,
          playing: playing,
          musicRoleId: socialuniMusicStore.musicRoomInfo.musicRoleId,
        }
        socialuniMusicStore.setMusicRoomInfo(playRoomInfo)
        this.playMusicApiFun()
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
        musicRoleId: socialuniMusicStore.musicRoomInfo.musicRoleId,
      }
      socialuniMusicStore.setMusicRoomInfo(playRoomInfo)
      this.playMusicApiFun()
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

  computedRealPlayingValue(dragging = this.dragging) {
    if (!dragging) {
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
      if (this._realPlayingValue >= this.musicMax && this.modelValue.playing) {
        socialuniMusicStore.setMusicRoomInfo({
          musicTime: this.modelValue.musicTime,
          musicUrl: this.modelValue.musicUrl,
          playingTimestamp: new Date(),
          //单位秒
          playingTime: 0,
          playing: false,
          musicRoleId: socialuniMusicStore.musicRoomInfo.musicRoleId,
        })
      }
    }
  }

  get SocialuniMusicRoleId() {
    return SocialuniMusicRoleId
  }


  created() {
    // this.getMusic()
  }

  handleCurrentChange(row) {
    // mucisRoomStore.publish(this.songId)
    this.playMusicAPI(row.id)
  }

  get curPlayingTime() {
    return this.formatTooltip(this.realPlayingValue)
  }

  playingUnit = 100;

  get secondPlayingUnit() {
    return 1000 / this.playingUnit
  }

  formatTooltip(value) {
    const time = Math.floor(value / this.secondPlayingUnit)
    const minute = Math.floor(time / 60)
    const second = time % 60
    const minuteStr = minute > 9 ? minute : '0' + minute
    const secondStr = second > 9 ? second : '0' + second
    return `${minuteStr}:${secondStr}`
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
      musicRoleId: socialuniMusicStore.musicRoomInfo.musicRoleId,
    }
    socialuniMusicStore.setMusicRoomInfo(playRoomInfo)

    //更新音乐时长
    this.$refs.audioPlayer.onloadedmetadata = () => {
      const curTime = new Date()
      socialuniMusicStore.setMusicRoomInfo({
        musicTime: this.$refs.audioPlayer.duration,
        musicUrl: this.modelValue.musicUrl,
        playingTimestamp: curTime,
        //单位秒
        playingTime: 0,
        playing: true,
        musicRoleId: socialuniMusicStore.musicRoomInfo.musicRoleId,
      })
      this.frontPlay()
      this.playMusicApiFun()
    };
  }

  async frontPlay() {
    try {
      await this.$refs.audioPlayer.play()
    } catch (e) {
      if (this.hintMusicPlayingNum < 2) {
        this.hintMusicPlayingNum++
        AlertUtil.confirm('有音乐正在播放，是否打开声音').then(() => {
          this.setPlayerCurTimeAndPlay()
        })
      }
    }
  }

  async frontPause() {
    this.$refs.audioPlayer.pause()
  }

  playMusicApiFun() {
    SocialuniMusicAPI.playMusicAPI(socialuniMusicStore.channelName, this.modelValue).then(res => {
      socialuniMusicStore.setMusicRoomInfo(res.data)
    })
  }

  terstfasd111() {
    // socialuniUserRequest.get('https://api.sd-rtn.com/api/dev/v1/channel/user/5e681410a7434ce9bba3e268226ce537/51b26fe57a9d4d148d9b7df536eeebfa')

    /*musicRequest.get(`api/dev/v1/channel/user/5e681410a7434ce9bba3e268226ce537/51b26fe57a9d4d148d9b7df536eeebfa`, {
        headers: {
            Authorization: "Basic MWE5N2IyZmU3NjY2NGVmNjhiZmRkZjcyNTZjZjkxZDM6OTk5YzA2ODljYzc5NDEyOGI0NTBjMWQ3MDJmMGUyZjM="
        }
    })*/

    SocialuniMusicAPI.queryMusicChannel(socialuniMusicStore.channelName)
  }

  testvalue = 0

  //设置当前时间和播放时间

  deleteYun111() {
    SocialuniMusicAPI.updateMusicAPI(socialuniMusicStore.channelName, {
      isPause: true
    })
  }

  jixuYun111() {
    SocialuniMusicAPI.updateMusicAPI(socialuniMusicStore.channelName, {
      isPause: false
    })
  }


  @Emit()
  input(): MusicPlayerSongPlayingInfoRO {

  }
}
</script>
