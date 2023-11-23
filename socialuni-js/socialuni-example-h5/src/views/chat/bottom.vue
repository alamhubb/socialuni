<template>

    <audio ref="audio" loop id="audio"
           :src="`https://music.163.com/song/media/outer/url?id=${songDetail.id}.mp3`"></audio>
    <el-row :gutter="20" style="z-index: 10000000 !important;" :style="{background:color}">
        <el-col :span="7">
            <div class="title" @mouseenter="open = true" @mouseleave="open = false">
                <i v-show="open" @click="drawer = true" class="iconfont icon-top" style="color: white;"></i>
                <el-avatar :class="[open ? 'active' : '']" @click="drawer = true" shape="square" :size="50"
                           :src="songDetail.al.picUrl"></el-avatar>
                <div class="music-title">
                    {{ songDetail.name }}
                </div>
            </div>
        </el-col>
        <el-col :span="9">
            <main class="music">
                <div class="music-button">
                    <i @click="isChangeLike" v-if="!isLike" title="收藏" class="mdi mdi-star-outline"></i>
                    <i @click="isChangeLike" v-else style="color: red;font-size: 22px;" title="已收藏"
                       class="mdi mdi-star"></i>
                    <i title="上一曲" @click="next(-1)" class="mdi  mdi-skip-previous"></i>
                    <i @click="publish" style="font-size: 40px; color: #cc7013;" class="mdi"
                       :class="[is ? 'mdi-pause' :'mdi-play']"></i>
                    <i title="下一曲" @click="next(1)" class="mdi mdi-skip-next"></i>
                    <svg class="svg" @click="openLyric" aria-hidden="true">
                        <use xlink:href="#icon-minganci"></use>
                    </svg>
                </div>
            </main>
        </el-col>
        <el-col :span="3">
            <div> {{ $formatTime(currentTime).slice(-5) }} / {{ $formatTime(songDetail.dt).slice(-5) }}</div>
        </el-col>
        <el-col :span="5">
            <el-slider @change="sliderChange" style="margin-top: 12px;" v-model="value" :show-tooltip="false"
                       input-size="mini"></el-slider>
        </el-col>
    </el-row>
  <!--  弹出层-->
    <el-drawer :with-header="false" :append-to-body="true" v-model="drawer" direction="btt" :size="`${size}%`">
        <popup @scrollPlay="scrollPlay" ref="dom" @close="close" :currentTime="currentTime"></popup>
    </el-drawer>
</template>

<script lang="ts">
import {getLikeMusic} from "@/network/song.js";
import {ElMessage} from 'element-plus'
import {isLikeMusic} from "@/network/user.js";
import {debounce} from "@/utlis/debounce.js";
import eventBus from '@/utlis/eventbus.js'
import {defineAsyncComponent, computed, watch, ref, onMounted, nextTick} from "vue";


const popup = defineAsyncComponent(() => import('./children/popup.vue'))
import {useStore} from "vuex";
import SDialog from "socialuni-ui-h5/src/components/SComponents/SDialog.vue";
import {Component, Vue, Watch} from "vue-facing-decorator";

import AgoraRTC from "agora-rtc-sdk-ng"
import {mucisRoomStore} from "@/store/MusicRoom";

let rtc = {
    localAudioTrack: null,
    client: null
};


let options = {
    // Pass your App ID here.
    appId: "5e681410a7434ce9bba3e268226ce537",
    // Set the channel name.
    channel: "aaa",
    // Pass your temp token here.
    token: "007eJxTYNj+8OP7X44R8Zx/O2v4vzybYqbxf+en/7bqJ1ZLdK7zNFuuwGCaamZhaGJokGhuYmySnGqZlJRonGpkZmFkZJacamps/u+6RmpDICND5tS3DIxQCOIzMyQmJjIwAAAcYCIB",
    // Set the user ID.
    uid: 'web' + Math.floor(Math.random() * 2021428)
}


/*queueMicrotask(() => {
    drawer.value = false
    watch(drawer, newValue => {
        const el = document.querySelector('.el-drawer__body')
        el.style.padding = 0
        size.value = 100
        setTimeout(() => {
            if (newValue) {
                color.value = '#d7b0d9'
            } else {
                color.value = '#ffffff'
            }
        }, 200)
    })
})*/
//滑动


@Component({})
export default class HomePage extends Vue {
    $refs: {
        mapDialog: SDialog
        audio: HTMLAudioElement
    }

    store = useStore()

    is = false
    open = false
    isLike = false
    drawer = true
    dom = null
    color = '#ffffff'
    value = 0
    currentTime = 0
    size = 0


    created() {
        console.log(this.store)
        console.log(7879978)
        // this.queryMusicListAPI()
        //实现语音通话逻辑
        //参考以下步骤实现语音通话的逻辑：
        // 调用 createClient 方法创建 AgoraRTCClient 对象。
        // 调用 join 方法加入一个 RTC 频道，你需要在该方法中传入 App ID 、用户 ID、Token、频道名称。
        // 先调用 createMicrophoneAudioTrack 通过麦克风采集的音频创建本地音频轨道对象；然后调用 publish 方法，将本地音频轨道对象当作参数即可将音频发布到频道中。
        // 当一个远端用户加入频道并发布音频轨道时：
        // 监听 client.on("user-published") 事件。当 SDK 触发该事件时，在这个事件回调函数的参数中你可以获取远端用户 AgoraRTCRemoteUser 对象 。
        // 调用 subscribe 方法订阅远端用户 AgoraRTCRemoteUser 对象，获取远端用户的远端音频轨道 RemoteAudioTrack 对象。
        // 调用 play 方法播放远端音频轨道。
        eventBus.on('login1', () => {
            getLikeMusic(this.store.state.login.profile?.userId).then(res => {
                if (!res) return
                this.store.commit('setLikeMusic', res?.data.ids)
                this.isLike = this.store.state.singer.likeMusic.includes(this.songDetail.value.id)
            })
        })
        eventBus.on('playMusic', () => {
            setTimeout(() => {
                this.playMusic()
                this.$refs.audio.play()
            })
        })
    }

    close() {
        this.drawer = false
        console.log('关闭')
    }

    openLyric() {
        this.size = 100
        this.drawer = true
    }

//收藏
    isChangeLike() {
        if (this.store.state.login.profile) {
            this.isLike = !this.isLike
            isLikeMusic(this.songDetail.id, this.isLike).then(res => {
                if (this.isLike && res.data.code === 200) {
                    ElMessage.success({
                        type: 'success',
                        message: '收藏成功'
                    })
                } else {
                    ElMessage.success({
                        type: 'success',
                        message: '取消收藏'
                    })
                }
            })
        } else {
            ElMessage.error({
                type: 'error',
                message: '未登录'
            })
        }
    }

//播放音乐
    timer = null

    play() {
        this.currentTime = parseInt(this.$refs.audio.currentTime * 1000)
        this.value = parseInt(this.$refs.audio.currentTime / this.$refs.audio.duration * 100)
        //自动切换
        if (this.$refs.audio.currentTime >= this.$refs.audio.duration - 0.5) {
            console.log('播放结束')
            this.next(1)
        }
    }

    timer1 = null

    playMusic() {
        nextTick(() => {
            this.$refs.audio.addEventListener("error", () => {
                if (this.timer1) clearTimeout(this.timer1)
                this.timer1 = setTimeout(() => {
                    ElMessage.error('暂无音频已自动切换下一首')
                    this.next(1)
                }, 200)
            }, true);
        })
        console.log(1111111)
        if (this.$refs.audio.paused) {
            this.$refs.audio.play()
            this.is = true
            this.store.commit('setPlay')

            console.log('播放')
            this.timer = setInterval(this.play, 500)
        } else {
            console.log('暂停')
            this.$refs.audio.pause()
            this.is = false
            this.store.commit('setPlay')
            clearInterval(this.timer)
        }
    }

    sliderChange(value, boolean = true) {
        clearInterval(this.timer)
        setTimeout(() => {
            if (boolean) {
                this.$refs.audio.currentTime = this.$refs.audio.duration * value / 100
            } else {
                this.$refs.audio.currentTime = value / 1000
            }
            this.$refs.audio.play()
            this.is = true
            this.timer = setInterval(this.play, 500)
        })
    }

    scrollPlay(time) {
        this.sliderChange(time, false)
    }


    next(value) {
        let musicNum = computed(() => this.store.state.songDetail.songArray.length)
        if (musicNum.value > 1) {
            this.store.commit('change', value)
            this.is = true
            setTimeout(() => {
                this.playMusic()
                this.$refs.audio.play()
            })
        } else {
            ElMessage.warning({
                message: '无可播放的歌曲列表',
                type: 'warning',
            })
        }
    }


    get songDetail() {
        return this.store.state.songDetail.songDetail
    }

    @Watch('songDetail', {deep: true, immediate: true})
    songDetailWatch() {
        this.isLike = this.store.state.singer.likeMusic.includes(this.songDetail.id)
    }

    isPlaying = false

    localAudioTrack = null
    client = null
    value1 = null
    options = options


    async joinClick() {
        // Join an RTC channel.
        await mucisRoomStore.client.join(options.appId, options.channel, options.token, options.uid);
    }

    async publish() {
        const config = {
            // source: `https://cdxapp-1257733245.file.myqcloud.com/opentest/M800000puzgO0yRX1o.mp3`
            source: `music/song/media/outer/url?id=${this.songDetail.id}.mp3`
        }
        const track = await AgoraRTC.createBufferSourceAudioTrack(config);
        track.play();
        track.startProcessAudioBuffer({
            startPlayTime: 100
        });
        await mucisRoomStore.client.publish(track);
    }

    async leaveClick() {
        // Destroy the local audio track.
        rtc.localAudioTrack.close();

        // Leave the channel.
        await mucisRoomStore.client.leave();
    }
}


</script>

<style scoped lang="scss">
.svg {
  width: 30px;
  height: 30px;
}

.icon-aixin {
  color: red;
}

.active {
  transition: all 1s;
  transform: scale(1.1);
}

.title {
  position: relative;
  display: flex;
  align-items: center;

  .music-title {
    width: 90%;
    margin-left: 10px;
    overflow: hidden;
    white-space: nowrap;
    text-overflow: ellipsis;
  }

  i {
    position: absolute;
    top: 50%;
    transform: translate(-50%, -50%);
    left: 25px;
    font-size: 35px;
  }
}

.play {
  font-size: 30px;
  color: pink;
}

.music-button {
  display: flex;
  justify-content: space-evenly;
  align-items: center;
}

.iconfont {
  color: rgba(49, 48, 48, 0.8);
  z-index: 100;
}
</style>
