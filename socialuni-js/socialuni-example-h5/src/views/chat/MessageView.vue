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
                    <el-button
                            @click="terstfasd('https://cdxapp-1257733245.file.myqcloud.com/opentest/M800000puzgO0yRX1o.mp3')">
                        创建
                    </el-button>
                    <el-button @click="terstfasd111">查询</el-button>
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
import SocialuniChatViewH5 from "@socialuni/socialuni-im-view-h5/src/views/SocialuniChatViewH5.vue"
import SocialuniMsgViewH5 from "@socialuni/socialuni-im-view-h5/src/views/SocialuniMsgViewH5.vue"
import musicRequest from "@/plugins/musicRequest";
import AgoraRTC from "agora-rtc-sdk-ng"
import {mucisRoomStore} from "@/views/chat/MusicRoom";
import {ZegoExpressEngine} from "zego-express-engine-webrtc";
import UUIDUtil from "@socialuni/socialuni-util/src/util/UUIDUtil";
import test1 from "@/assets/test1.mp3"
import CryptoJS from "crypto-js"
import {request} from "axios";
import SocialuniMusicAPI from "@/api/SocialuniMusicAPI";

@Component({
    components: {SocialuniChatViewH5, SocialuniMsgViewH5}
})
export default class MessageView extends Vue {

    test1 = test1

    mounted() {
        this.queryMusicList()
        // this.initZegoYun()
        // this.initZego()
    }

    async initZegoYun() {
//Signature=md5(AppId + SignatureNonce + ServerSecret + Timestamp)
        function GenerateUASignature(appId, signatureNonce, serverSecret, timeStamp) {
            var str = appId + signatureNonce + serverSecret + timeStamp;
            // const hash = crypto.createHash('md5'); //规定使用哈希算法中的MD5算法
            // hash.update(str);
            // return hash.digest('hex');
            return CryptoJS.MD5(str).toString()
        }

        const random = UUIDUtil.getUUID()
        var signatureNonce = CryptoJS.MD5(random).toString().substring(0, 8)
//使用你的appId和serverSecret
        var appId = mucisRoomStore.zego.appId;
        var serverSecret = mucisRoomStore.zego.key;
        var timeStamp = Math.round(Date.now() / 1000);

        const Signature = GenerateUASignature(appId, signatureNonce, serverSecret, timeStamp)
        const url = `api/?Action=DeletePlayer&AppId=${mucisRoomStore.zego.appId}&SignatureNonce=${signatureNonce}&Timestamp=${timeStamp}&Signature=${Signature}&SignatureVersion=2.0&IsTest=false`
        console.log(url)
        musicRequest.post(url, {
            StreamUrl: 'https://cdxapp-1257733245.file.myqcloud.com/opentest/M800000puzgO0yRX1o.mp3',
            RoomId: '0001',
        })
    }

    async initZego() {
        // 初始化实例
        const zg = new ZegoExpressEngine(mucisRoomStore.zego.appId, mucisRoomStore.zego.server);
        zg.setDebugVerbose(false)
        const config = {
            logLevel: 'error',
        };

        zg.setLogConfig(config);

        // 登录房间，成功则返回 true
// userUpdate 设置为 true 会开启监听 roomUserUpdate 回调，默认情况下不会开启该监听
        const result = await zg.loginRoom(
            'room1',
            '04AAAAAGVNu1sAEDNqcnRtcG5sbm5pNDQ5ZnkAsGMEJdms34IXPtNaHCHSNmljXXYEYyN05RG9L+ZVWs6rCBPAIPMXXlyLZIrHBH8sme70e58DMCAaVWTo1Y6u5H9BClMxeZnjVT/FfFTVnSfJxRREp88qmAZ3sLtN99NfCnbLd04k7nJuE2q9c6c5AH9HOS+vTnkngq0LyqSHvLKGXDxDCAIl9nYeIrOUMgeIUDqfqn3+6P+O5s0zBWvziQAnjoBnEe8dXZsuAMhg+ClX',
            {
                userID: 'user1'
            },
            {userUpdate: true});
        console.log(777777777)
        console.log(result)

        // 调用 createZegoStream 接口后，需要等待 ZEGO 服务器返回 ZegoLocalStream 实例对象才能执行后续操作
        const localStream = await zg.createZegoStream({
            custom: {
                audio: {
                    source: document.querySelector("#local"),
                    channelCount: 2
                }
            }
        });

        /*const deviceInfo = await zg.enumDevices();
        const audioDeviceList = deviceInfo &&
            deviceInfo.microphones.map((item, index) => {
                if (!item.deviceName) {
                    item.deviceName = 'microphone' + index;
                }
                console.log('microphone: ' + item.deviceName);
                return item;
            });
        audioDeviceList.push({deviceID: 0, deviceName: '禁止'});
        const microphoneDevicesVal = audioDeviceList[0].deviceID;


        const localStream = await zg.createZegoStream({
            camera: {
                audio: {
                    input: microphoneDevicesVal
                }
            }
        });*/

        zg.startPublishingStream('streamID1', localStream)

        // localStream.playAudio(document.querySelector("#local"));


        zg.on('publisherStateUpdate', result => {
            // 推流状态更新回调
            // ...
            console.log('publisherStateUpdate')
        })

        zg.on('publishQualityUpdate', (streamID, stats) => {
            // 推流质量回调
            console.log('publishQualityUpdate')
            // ...
        })

// 调用 localStream 实例上 playAudio 方法播放推流前或者推流中音频预览
//         localStream.playAudio();
        console.log(9999999999)
        console.log(localStream)


        setTimeout(async () => {
            const remoteStream = await zg.startPlayingStream('streamID1');

            console.log(remoteStream)
            console.log(remoteStream)
            console.log(666666666)
// 创建媒体流播放组件
            const remoteView = zg.createRemoteStreamView(remoteStream);
            const romote = document.querySelector("#remote") as HTMLAudioElement
            console.log(remoteView)
            console.log('88888888')
            console.log('执行')
            console.log(romote.src)
            remoteView.play(romote, {enableAutoplayDialog: true});
        }, 2000)

    }

    tableData = []

    async queryMusicList() {
        console.log('删除即构diamante')
        console.log('删除即构diamante')
        const data = await musicRequest.get('https://music-api.heheda.top/playlist/detail?_t=1699363992&id=3778678') as any
        console.log(data)
        console.log(data.playlist)
        console.log(data.playlist.tracks)
        this.tableData = data.playlist.tracks
    }


    songId = null

    handleCurrentChange(row) {
        console.log(row)
        this.songId = row.id
        // mucisRoomStore.publish(this.songId)

        const url = `https://music.163.com/song/media/outer/url?id=${this.songId}.mp3`
        console.log(url)
        this.terstfasd(url)
    }

    get mucisRoomStore() {
        return mucisRoomStore;
    }

    terstfasd(url) {
        SocialuniMusicAPI.playMusicAPI(url)
        // mucisRoomStore.reqquetest(url)
    }

    terstfasd111() {
        mucisRoomStore.reqquetest111()
    }

    deleteYun111() {
        SocialuniMusicAPI.updateMusicAPI({
            isPause: true
        })
    }

    jixuYun111() {
        SocialuniMusicAPI.updateMusicAPI({
            isPause: false
        })
    }

}
</script>
