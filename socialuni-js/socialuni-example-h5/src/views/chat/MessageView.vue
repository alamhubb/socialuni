<template>
    <div class="flex-row h100p overflow-hidden">
        <div class="w200 bd-radius shadow h100p flex-none">
            <socialuni-chat-view-h5></socialuni-chat-view-h5>
        </div>

        <div class="flex-1 overflow-hidden h100p bg-white ml-sm">
            <!--            <div class="w100p">
                            <audio id="local" :src="test1" controls="controls"
                                   style="height: 200px;width: 500px;"></audio>
                            <audio id="remote" style="height: 200px;width: 500px;" controls="controls"></audio>
                        </div>-->

            <div>
                <div></div>

                <div>
                    <el-slider v-model="musicProcss" @change="musicChange" :max="muscMax"></el-slider>
                </div>
                <div>
                    <main class="music">
                        <div class="music-button">
                            <!--                            <i @click="isChangeLike" v-if="!isLike" title="收藏" class="mdi mdi-star-outline"></i>-->
                            <!--                            <i @click="isChangeLike" v-else style="color: red;font-size: 22px;" title="已收藏"  class="mdi mdi-star"></i>-->
                            <i title="上一曲" @click="next(-1)" class="mdi  mdi-skip-previous"></i>
                            <i @click="getMusic" style="font-size: 40px; color: #cc7013;" class="mdi"
                               :class="[is ? 'mdi-pause' :'mdi-play']"></i>
                            <i title="下一曲" @click="next(1)" class="mdi mdi-skip-next"></i>
                            <svg class="svg" @click="openLyric" aria-hidden="true">
                                <use xlink:href="#icon-minganci"></use>
                            </svg>
                        </div>
                    </main>
                </div>
                <div>
                    <el-button
                            @click="terstfasd('https://cdxapp-1257733245.file.myqcloud.com/opentest/M800000puzgO0yRX1o.mp3')">
                        创建
                    </el-button>
                    <div>
                        <audio ref="audioPlayer" autoplay muted controls id="audio" src="https://music.163.com/song/media/outer/url?id=1456890009.mp3"></audio>

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
                </div>
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

<script lang="ts">
import {Component, Vue} from 'vue-facing-decorator';
import SocialuniChatViewH5 from "socialuni-im-view-h5/src/views/SocialuniChatViewH5.vue"
import SocialuniMsgViewH5 from "socialuni-im-view-h5/src/views/SocialuniMsgViewH5.vue"
import musicRequest from "@/plugins/musicRequest";
import SocialuniMusicAPI from "socialuni-music-sdk/src/api/SocialuniMusicAPI";
import CommonEventUtil from "socialuni-native-util/src/util/CommonEventUtil";
import SocialuniImEventKey from "socialuni-im-api/src/constant/SocialuniMusicEventConst";
import socialuniMusicStore from "socialuni-music-sdk/src/store/SocialuniMusicStore";
import WebsocketWebRtcUtil from "socialuni-api-base/src/websocket/WebsocketWebRtcUtil";
import test1 from './test1.mp3'
import Axios from "axios";

let localStream
let localVideo = null

@Component({
    components: {SocialuniChatViewH5, SocialuniMsgViewH5}
})
export default class MessageView extends Vue {
    tableData = []

    musicProcss = 0
    muscMax = 0

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
    }

    created() {
        // this.getMusic()
    }

    async init() {
        this.$refs.audioPlayer.onloadedmetadata = () => {
            this.muscMax = Math.ceil(this.$refs.audioPlayer.duration)
        };

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
        this.terstfasd(this.songId)
    }


    terstfasd(url) {
        console.log(1111)
        console.log(socialuniMusicStore.channelName)
        console.log(222)
        SocialuniMusicAPI.playMusicAPI(socialuniMusicStore.channelName, {
            musicId: url,
            musicToken: socialuniMusicStore.musicToken
        })
        // mucisRoomStore.reqquetest(url)
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

    musicChange(value) {
        console.log(value)
        this.$refs.audioPlayer.currentTime = value
        this.$refs.audioPlayer.play()
        console.log(this.$refs.audioPlayer.currentTime)
        console.log(888)
    }

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
