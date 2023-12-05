import {Component, Vue, Watch} from 'vue-facing-decorator';
import SocialuniChatViewH5 from "socialuni-im-view-h5/src/views/SocialuniChatViewH5.vue"
import SocialuniMsgViewH5 from "socialuni-im-view-h5/src/views/SocialuniMsgViewH5.vue"
import musicRequest from "@/plugins/musicRequest";
import SocialuniMusicAPI from "socialuni-music-sdk/src/api/SocialuniMusicAPI";
import CommonEventUtil from "socialuni-native-util/src/util/CommonEventUtil";
import SocialuniImEventKey from "socialuni-im-api/src/constant/SocialuniMusicEventConst";
import socialuniMusicStore from "socialuni-music-sdk/src/store/SocialuniMusicStore";
import SocialuniMusicRoleId from "socialuni-music-sdk/src/constant/SocialuniMusicRoleId";
import AlertUtil from "socialuni-native-h5/src/util/AlertUtil";
import {createApp} from "vue";
import * as CompilerDOM from '@vue/compiler-dom'
import {compileTemplate} from "@vue/compiler-sfc";

@Component({
    components: {SocialuniChatViewH5, SocialuniMsgViewH5},
    render() {
    },
})
export default class MessageView extends Vue {

    render() {
        const vuesx = `
          <div class="flex-row h100p overflow-hidden">
        <div class="w200 bd-radius shadow h100p flex-none">
            <socialuni-chat-view-h5></socialuni-chat-view-h5>
        </div>


        <div class="flex-1 overflow-hidden h100p bg-white ml-sm">
            <!--            <audio ref="audioPlayer" src="https://music.163.com/song/media/outer/url?id=2100329027.mp3" autoplay muted ></audio>-->
            <audio ref="audioPlayer" :src="musicRoomInfo?.musicUrl"></audio>
            <!--            <div class="w100p">
                            <audio id="local" :src="test1" controls="controls"
                                   style="height: 200px;width: 500px;"></audio>
                            <audio id="remote" style="height: 200px;width: 500px;" controls="controls"></audio>
                        </div>-->

            <div>
                <div></div>

                <!--        如果为roleid = ower或者admin，显示， 如果musicurl有值显示， 否则不显示-->
                <div v-if="musicRoomInfo?.musicUrl">
                    <div class="row-col-center">
                        <div>{{ curPlayingTime }}</div>
                        <el-slider v-model="realPlayingValue" @input="musicInput" @change="musicChange"
                                   :show-tooltip="false"
                                   :max="musicMax"></el-slider>
                        <div>{{ formatTooltip(musicMax) }}</div>
                    </div>
                    <div>
                        <div v-if="SocialuniMusicRoleId.hasOperateAuthList.includes(musicRoomInfo.musicRoleId)">
                            <div>
                                <!--                            <i @click="isChangeLike" v-if="!isLike" title="收藏" class="mdi mdi-star-outline"></i>-->
                                <!--                            <i @click="isChangeLike" v-else style="color: red;font-size: 22px;" title="已收藏"  class="mdi mdi-star"></i>-->
                                <i title="上一曲" @click="next(-1)" class="mdi mdi-skip-previous"></i>
                                <i @click="continuePlay(!showPause)" style="font-size: 40px; color: #cc7013;"
                                   class="mdi"
                                   :class="[showPause ? 'mdi-pause' :'mdi-play']"></i>
                                <i title="下一曲" @click="next(1)" class="mdi mdi-skip-next"></i>
                            </div>
                        </div>
                        <div>
                            <i class="mdi mdi-volume-mute" @click="openSound"></i>
                            <i class="mdi mdi-volume-medium"></i>
                        </div>
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
        `


        // 编译模板
        const {code} = compileTemplate({
            source: vuesx,
            id: 'some-id',
            filename: 'some-file.vue',
        });


        console.log(12312313)
        console.log(code)
// 将编译后的代码字符串转换为渲染函数
// 注意：这里使用了 Function 构造函数，这可能带来安全风险
        const renderFunction = new Function('Vue', 'return ' + code)(Vue);

// 创建 Vue 应用

        return renderFunction
    }

    $refs: {
        audioPlayer: HTMLAudioElement
    }

    tableData = []

//创建时设置musicTime
//然后不需要播放

    get musicMax() {
        if (this.musicRoomInfo) {
            return this.musicRoomInfo.musicTime * this.secondPlayingUnit
            // return 500 * 100
        }
        return 0
    }

    private timer = null

    get showPause() {
        return this.musicRoomInfo && (this.musicRoomInfo.playing)
    }

    openSound() {
        if (this.musicRoomInfo.playing) {
            console.log('设置播放进度')
            console.log(Math.floor(this.realPlayingValue / this.secondPlayingUnit))
            this.$refs.audioPlayer.currentTime = Math.floor(this.realPlayingValue / this.secondPlayingUnit)
            this.frontPlay()
        }
    }

    mounted() {
        this.queryMusicList()

        if (this.$route.query.chatId) {
            CommonEventUtil.emit(SocialuniImEventKey.socialuniImPageInit, this.$route.query)
        }

        /*if (this.musicRoomInfo) {
            if (this.musicRoomInfo.playing) {
                // this.frontPlay()
            }
        }*/

        // this.init()
        // this.getMusic()
        // this.queryMusicRoomPlayer()
    }


    @Watch('musicRoomInfo')
    onMusicRoomInfoChange() {
        if (this.musicRoomInfo) {
            if (this.musicRoomInfo.playing) {
                // this.frontPlay()
            }
            console.log('触发了')
            this.computedRealPlayingValue()
            if (this.timer) {
                clearInterval(this.timer)
                this.timer = null
            }
            if (this.musicRoomInfo.playing) {
                this.timer = setInterval(() => {
                    this.computedRealPlayingValue()
                }, this.playingUnit)
            }
        }
    }


    dragging = false

    musicInput(value) {
        this.checkRoleId()

        this.dragging = true
        console.log(`input--:${value}`)
        //秒，
        const playTime = Math.floor(value / this.secondPlayingUnit)
        //所以播放时间也要为秒
        if (this.musicRoomInfo.playing) {
            this.$refs.audioPlayer.currentTime = playTime
        }

        const curTime = new Date()

        socialuniMusicStore.setMusicRoomInfo({
            musicTime: this.musicRoomInfo.musicTime,
            musicUrl: this.musicRoomInfo.musicUrl,
            playingTimestamp: curTime,
            //单位秒
            playingTime: playTime,
            playing: this.musicRoomInfo.playing,
            musicRoleId: socialuniMusicStore.musicRoomInfo.musicRoleId,
        })
        console.log(this.musicRoomInfo.playing)
        this.computedRealPlayingValue(false)
    }


    musicChange() {
        this.checkRoleId()
        this.dragging = false
        this.playMusicApiFun()
    }

//初始化的播放怎么做

    continuePlay(playing
                     :
                     boolean
    ) {
        if (playing) {
            //如何判断是继续播放还是重新播放
            //根据playTime决定
            if (this.musicRoomInfo?.musicUrl) {
                this.frontPlay()
                const playRoomInfo = {
                    musicTime: this.musicRoomInfo.musicTime,
                    musicUrl: this.musicRoomInfo.musicUrl,
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
                musicTime: this.musicRoomInfo.musicTime,
                musicUrl: this.musicRoomInfo.musicUrl,
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


    private _realPlayingValue = 0

    get realPlayingValue() {
        return this._realPlayingValue
    }

    computedRealPlayingValue(dragging = this.dragging) {
        if (!dragging) {
            const curDate = new Date().getTime()
            const playTime = new Date(this.musicRoomInfo.playingTimestamp).getTime()
            //得到已播放时间的时间差
            const diffTime = curDate - playTime

            //什么情况下为0，是播放完成后
            //进度为0.01秒
            if (this.musicRoomInfo.playing) {
                this._realPlayingValue = Math.floor(diffTime / this.playingUnit) + this.musicRoomInfo.playingTime * this.secondPlayingUnit
            } else {
                this._realPlayingValue = this.musicRoomInfo.playingTime * this.secondPlayingUnit
            }
            if (this._realPlayingValue <= 20) {
                console.log(`curDate:${curDate}`)
                console.log(`playTime:${playTime}`)
                console.log(`diffTime:${diffTime}`)
            }

            if (this._realPlayingValue >= this.musicMax && this.musicRoomInfo.playing) {
                console.log(`this._realPlayingValue:${this._realPlayingValue}`)
                console.log(`this.musicMax:${this.musicMax}`)
                socialuniMusicStore.setMusicRoomInfo({
                    musicTime: this.musicRoomInfo.musicTime,
                    musicUrl: this.musicRoomInfo.musicUrl,
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

    get musicRoomInfo() {
        return socialuniMusicStore.musicRoomInfo
    }


    async queryMusicList() {
        const data = await musicRequest.get('https://music-api.heheda.top/playlist/detail?_t=1699363992&id=3778678') as any
        this.tableData = data.playlist.tracks
    }


    handleCurrentChange(row) {
        console.log(row)
        // mucisRoomStore.publish(this.songId)
        this.playMusicAPI(row.id)
    }

    get curPlayingTime() {
        console.log(this.formatTooltip(this.realPlayingValue))
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
        if (!SocialuniMusicRoleId.hasOperateAuthList.includes(socialuniMusicStore.musicRoomInfo.musicRoleId)) {
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
                musicUrl: this.musicRoomInfo.musicUrl,
                playingTimestamp: curTime,
                //单位秒
                playingTime: 0,
                playing: true,
                musicRoleId: socialuniMusicStore.musicRoomInfo.musicRoleId,
            })
            this.frontPlay()
            this.playMusicApiFun()
        };

        // this.$refs.audioPlayer.currentTime = value / 100
        // mucisRoomStore.reqquetest(url)
    }

    frontPlay() {
        this.$refs.audioPlayer.play()
    }

    playMusicApiFun() {
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
