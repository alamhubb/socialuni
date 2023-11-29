<template>
    <div class="flex-row h100p overflow-hidden">
        <div class="w200 bd-radius shadow h100p flex-none">
            <socialuni-chat-view-h5></socialuni-chat-view-h5>
        </div>


        <div class="flex-1 overflow-hidden h100p bg-white ml-sm">
            {{ musicRoomInfo?.musicUrl }}
            <audio ref="audioPlayer" :src="musicRoomInfo?.musicUrl"></audio>
            <!--            <div class="w100p">
                            <audio id="local" :src="test1" controls="controls"
                                   style="height: 200px;width: 500px;"></audio>
                            <audio id="remote" style="height: 200px;width: 500px;" controls="controls"></audio>
                        </div>-->

            <div>
                <div></div>

                <!--        如果为roleid = ower或者admin，显示， 如果musicurl有值显示， 否则不显示-->


                {{ realPlayingValue }}--{{ musicMax }}
                <div v-if="musicRoomInfo?.musicUrl">
                    <div class="row-col-center">
                        <div>{{ formatTooltip(realPlayingValue) }}</div>
                        <el-slider v-model="realPlayingValue" @input="musicInput" @change="musicChange"
                                   :show-tooltip="false"
                                   :max="refMusicMax"></el-slider>
                        <div>{{ formatTooltip(musicMax) }}</div>
                    </div>
                    <div v-if="SocialuniMusicRoleId.hasOperateAuthList.includes(musicRoomInfo.musicRoleId)">
                        <main class="music">
                            <div class="music-button">
                                <!--                            <i @click="isChangeLike" v-if="!isLike" title="收藏" class="mdi mdi-star-outline"></i>-->
                                <!--                            <i @click="isChangeLike" v-else style="color: red;font-size: 22px;" title="已收藏"  class="mdi mdi-star"></i>-->
                                <i title="上一曲" @click="next(-1)" class="mdi  mdi-skip-previous"></i>
                                <i @click="continuePlay" style="font-size: 40px; color: #cc7013;" class="mdi"
                                   :class="[is ? 'mdi-pause' :'mdi-play']"></i>
                                <i title="下一曲" @click="next(1)" class="mdi mdi-skip-next"></i>
                                <svg class="svg" @click="openLyric" aria-hidden="true">
                                    <use xlink:href="#icon-minganci"></use>
                                </svg>
                            </div>
                        </main>
                    </div>
                </div>
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
            <el-table :data="tableData" stripe size="small"
                      highlight-current-row
                      @row-dblclick="handleCurrentChange" style="width: 100%;">
                <!--        <el-table-column width="45">
                          <template #default="scope">
                            <svg v-if="scope.row.id === playName" style="width: 15px;height: 15px;" class="svg" aria-hidden="true">
                              <use xlink:href="#icon-yangshengqi"></use>
                            </svg>
                            <span v-else>{{ scope.row.index }}</span>
                          </template>
                        </el-table-column>-->
                <el-table-column ref="dom" width="45">
                    <template #default="scope">
                        <!--            <i v-if="isLike(scope.row.id)" style="color: red; font-size: 14px;" class="iconfont icon-xihuan"></i>-->
                        <!--            <i v-else title="喜欢" class="iconfont icon-aixin" style="font-size: 15px;"></i>-->
                    </template>
                </el-table-column>
                <el-table-column width="40"><i title="下载" class="iconfont icon-xiazai" style="font-size: 15px;"></i>
                </el-table-column>
                <el-table-column prop="name" label="音乐标题" width="400">
                    <template #default="scope">
                        <!--            <span class="music-title" :class="{active:scope.row.id === playName}">{{ scope.row.name }}</span>-->
                        <!--            <el-tag @click="toDetail(scope.row.mv)" v-if="scope.row.mv" type="danger" size="mini">MV</el-tag>-->
                    </template>
                </el-table-column>
                <el-table-column label="歌手" width="275">
                    <template #default="scope">
                        {{ scope.row.ar?.map(item => item.name).join(' / ') }}
                    </template>
                </el-table-column>
                <el-table-column label="歌手专辑" width="300">
                    <template #default="scope">
                        {{ scope.row.al.name }}
                    </template>
                </el-table-column>
                <el-table-column prop="address" label="时长">
                    <template #default="scope">
                        <!--            {{ $formatTime(scope.row.dt).slice(-5) }}-->
                    </template>
                </el-table-column>
            </el-table>
        </div>
    </div>
</template>

<script lang="tsx">
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

let localStream
let localVideo = null

@Component({
    components: {SocialuniChatViewH5, SocialuniMsgViewH5}
})
export default class MessageView extends Vue {
    async init() {
        this.$refs.audioPlayer.onloadedmetadata = () => {
            this.musicMax = Math.ceil(this.$refs.audioPlayer.duration * 100)
        };
    }

    tableData = []

    musicMax = 0

    private timer = null

    @Watch('musicRoomInfo')
    onMusicRoomInfoChange() {
        if (this.musicRoomInfo) {
            this.computedRealPlayingValue()
            if (this.timer) {
                clearInterval(this.timer)
                this.timer = null
            }
            if (this.musicRoomInfo.playing) {
                this.timer = setInterval(() => {
                    this.computedRealPlayingValue()
                }, 10)
            }
        }
    }

    private _realPlayingValue = 0

    get realPlayingValue() {
        return this._realPlayingValue
    }

    computedRealPlayingValue() {
        const curDate = new Date().getTime()
        const playTime = new Date(this.musicRoomInfo.playingTimestamp).getTime()
        //得到已播放时间的时间差
        const diffTime = curDate - playTime

        console.log(diffTime)
        console.log(this.musicRoomInfo.playingTime)

        //什么情况下为0，是播放完成后
        //进度为0.01秒
        this._realPlayingValue = Math.ceil(diffTime / 10) + this.musicRoomInfo.playingTime * 100

        if (this._realPlayingValue >= this.musicMax) {
            socialuniMusicStore.setMusicRoomInfo({
                musicTime: this.musicMax,
                musicUrl: this.musicRoomInfo.musicUrl,
                playingTimestamp: new Date(),
                //单位秒
                playingTime: 0,
                playing: false,
                musicRoleId: socialuniMusicStore.musicRoomInfo.musicRoleId,
            })
        }
    }


    get refMusicMax() {
        return this.musicMax ? this.musicMax : 100000000
    }

    get SocialuniMusicRoleId() {
        return SocialuniMusicRoleId
    }

    get musicRoomInfo() {
        return socialuniMusicStore.musicRoomInfo
    }

    mounted() {
        localVideo = document.getElementById('localVideo');
        WebsocketWebRtcUtil.remoteVideo = document.getElementById('remoteVideo');
        this.queryMusicList()

        console.log(this.$route.query)
        if (this.$route.query.chatId) {
            console.log('触发通知')
            CommonEventUtil.emit(SocialuniImEventKey.socialuniImPageInit, this.$route.query)
        }

        this.init()
        // this.getMusic()
        // this.queryMusicRoomPlayer()
    }


    async queryMusicRoomPlayer() {
        const res = await SocialuniMusicAPI.queryMusicRoomPlayerInfoAPI(socialuniMusicStore.channelName)
    }

    created() {
        // this.getMusic()
    }


    audioPlayer = null

    async getMusic() {
        /*        this.audioPlayer = audioPlayer

                console.log(6666)
                console.log(audioPlayer)


                this.audioPlayer.onerror = (e) => {
                    console.log(e);
                };
                this.audioPlayer.canplay = (e) => {
                    console.log(e);
                };
                this.audioPlayer.canplaythrough = (e) => {
                    console.log(e);
                };

                audioPlayer.play()*/

        // 1398283847
        /*console.log(789798798)
        const res = await fetch(`music/song/media/outer/url?id=${1456890009}.mp3`)
        const blob = await res.blob();
        console.log(blob)
        const blobUrl = URL.createObjectURL(blob);
        console.log(blobUrl)
        audio.src = blobUrl
        console.log(323232)*/

    }

    async queryMusicList() {
        console.log('删除即构diamante')
        console.log('删除即构diamante')
        const data = await musicRequest.get('https://music-api.heheda.top/playlist/detail?_t=1699363992&id=3778678') as any
        console.log(data)
        console.log(data.playlist)
        console.log(data.playlist.tracks)
        this.tableData = data.playlist.tracks
    }

// 获取本地媒体流
    async start() {
        /*const audio = new Audio();
        localStream = await navigator.mediaDevices.getUserMedia({video: false, audio: true});
        WebsocketWebRtcUtil.easyWebRTC.pushStream(localStream)

        audio.srcObject = localStream
        audio.play()*/

        const audio = new Audio(test1);
        audio.oncanplaythrough = (async () => {
                localStream = audio.captureStream();
                WebsocketWebRtcUtil.easyWebRTC.pushStream(localStream)
            }
        )
        audio.play()
        /*try {
            localStream = await navigator.mediaDevices.getUserMedia({video: false, audio: true});
            console.log(localVideo)
            localVideo.srcObject = localStream;
            localStream.getTracks().forEach(track => {
                WebsocketWebRtcUtil.peerConnection.addTrack(track, localStream);
            });

            WebsocketWebRtcUtil.peerConnection.createOffer()
                .then(offer => WebsocketWebRtcUtil.peerConnection.setLocalDescription(offer))
                .then(() => {
                    // 发送本地描述到远程端
                    WebsocketWebRtcUtil.send({ 'offer': WebsocketWebRtcUtil.peerConnection.localDescription });
                });
        } catch (error) {
            console.error('Error accessing local media:', error);
        }*/
    }

    stop() {
        localStream.getTracks().forEach(track => {
            track.stop();
        });
        localVideo.srcObject = null;
        WebsocketWebRtcUtil.remoteVideo.srcObject = null;
        WebsocketWebRtcUtil.peerConnection.close();
    }


    songId = null

    handleCurrentChange(row) {
        console.log(row)
        this.songId = row.id
        // mucisRoomStore.publish(this.songId)
        this.playMusicAPI(this.songId)
    }

    formatTooltip(value) {
        console.log(88888)
        console.log(value)

        const time = Math.ceil(value / 100)
        const minute = Math.floor(time / 60)
        const second = time % 60
        const minuteStr = minute > 9 ? minute : '0' + minute
        const secondStr = second > 9 ? second : '0' + second
        return `${minuteStr}:${secondStr}`
    }

    checkRoleId() {
        if (!SocialuniMusicRoleId.hasOperateAuthList.includes(socialuniMusicStore.musicRoomInfo.musicRoleId)) {
            AlertUtil.error("您没有操作权限")
        }
    }

    musicInput(value) {
        this.checkRoleId()

        console.log(value)
        //秒，
        const playTime = Math.ceil(value / 100)
        //所以播放时间也要为秒
        this.$refs.audioPlayer.currentTime = playTime

        const curTime = new Date()

        socialuniMusicStore.setMusicRoomInfo({
            musicTime: 100000000,
            musicUrl: this.musicRoomInfo.musicUrl,
            playingTimestamp: curTime,
            //单位秒
            playingTime: playTime,
            playing: true,
            musicRoleId: socialuniMusicStore.musicRoomInfo.musicRoleId,
        })
    }


    musicChange() {
        this.checkRoleId()
        this.playMusicFun()
    }

    play() {
        this.$refs.audioPlayer.play()
    }

    continuePlay() {
        //如何判断是继续播放还是重新播放
        //根据playTime决定
        if (this.musicRoomInfo.musicUrl) {
            const playRoomInfo = {
                musicTime: this.musicRoomInfo.musicTime,
                musicUrl: this.musicRoomInfo.musicUrl,
                playingTimestamp: new Date(),
                //单位秒
                playingTime: 0,
                playing: true,
                musicRoleId: socialuniMusicStore.musicRoomInfo.musicRoleId,
            }
            socialuniMusicStore.setMusicRoomInfo(playRoomInfo)
        }
    }

    async playMusicAPI(songId) {
        if (!SocialuniMusicRoleId.hasOperateAuthList.includes(socialuniMusicStore.musicRoomInfo.musicRoleId)) {
            AlertUtil.error("您没有操作权限")
        }
        const musicUrl = `https://music.163.com/song/media/outer/url?id=${songId}.mp3`;
        const playRoomInfo = {
            musicTime: 100000000,
            musicUrl: musicUrl,
            playingTimestamp: new Date(),
            //单位秒
            playingTime: 0,
            playing: true,
            musicRoleId: socialuniMusicStore.musicRoomInfo.musicRoleId,
        }
        socialuniMusicStore.setMusicRoomInfo(playRoomInfo)

        //更新音乐时长
        this.$refs.audioPlayer.onloadedmetadata = () => {
            this.musicMax = Math.ceil(this.$refs.audioPlayer.duration * 100)
            const curTime = new Date()
            socialuniMusicStore.setMusicRoomInfo({
                musicTime: this.musicMax * 10,
                musicUrl: this.musicRoomInfo.musicUrl,
                playingTimestamp: curTime,
                //单位秒
                playingTime: 0,
                playing: true,
                musicRoleId: socialuniMusicStore.musicRoomInfo.musicRoleId,
            })
            this.playMusicFun()
        };

        // this.$refs.audioPlayer.currentTime = value / 100
        // this.$refs.audioPlayer.play()
        // mucisRoomStore.reqquetest(url)
    }

    playMusicFun() {
        this.$refs.audioPlayer.play()

        SocialuniMusicAPI.playMusicAPI(socialuniMusicStore.channelName, this.musicRoomInfo).then(res => {
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

    async queryAllplay() {
        return musicRequest.get<any>(`api/v1/projects/5e681410a7434ce9bba3e268226ce537/cloud-player/players`, {
            headers: {
                Authorization: "Basic MWE5N2IyZmU3NjY2NGVmNjhiZmRkZjcyNTZjZjkxZDM6OTk5YzA2ODljYzc5NDEyOGI0NTBjMWQ3MDJmMGUyZjM="
            },
            params: {
                filter: `channelName eq ${socialuniMusicStore.channelName}`
            }
        })
    }

    async destoryPlays() {
        const res = await this.queryAllplay()
        const plays = res.players

        if (plays && plays.length) {
            for (const play of plays) {
                musicRequest.delete<any>(`api/cn/v1/projects/5e681410a7434ce9bba3e268226ce537/cloud-player/players/${play.id}`, {
                    headers: {
                        Authorization: "Basic MWE5N2IyZmU3NjY2NGVmNjhiZmRkZjcyNTZjZjkxZDM6OTk5YzA2ODljYzc5NDEyOGI0NTBjMWQ3MDJmMGUyZjM="
                    }
                })
            }
        }
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

}
</script>
