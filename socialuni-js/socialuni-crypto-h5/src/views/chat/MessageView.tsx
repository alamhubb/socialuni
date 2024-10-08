/*
import {Component, Vue, Watch, toNative} from 'vue-facing-decorator';
import SocialuniChatViewH5 from "socialuni-im-view-h5/src/views/SocialuniChatViewH5.vue"
import SocialuniMsgViewH5 from "socialuni-im-view-h5/src/views/SocialuniMsgViewH5.vue"
import musicRequest from "@/plugins/musicRequest";
import SocialuniMusicAPI from "socialuni-music-sdk/src/api/SocialuniMusicAPI";
import CommonEventUtil from "qing-compat-js/src/util/CommonEventUtil";
import SocialuniImEventKey from "socialuni-im-api/src/constant/SocialuniMusicEventConst";
import socialuniMusicStore from "socialuni-music-sdk/src/store/SocialuniMusicStore";
import SocialuniMusicRoleId from "socialuni-music-sdk/src/constant/SocialuniMusicRoleId";
import AlertUtil from "qing-compat-js-h5/src/util/AlertUtil";
import {createApp} from "vue";
import * as CompilerDOM from '@vue/compiler-dom'
import {compileTemplate} from "@vue/compiler-sfc";


@Component({
    components: {SocialuniChatViewH5, SocialuniMsgViewH5},
})
export default class MessageView extends Vue {

    testV = 0

    render() {
        const vuesx = `
          <div class="flex-row h100p overflow-hidden">
       {{testV}}
    </div>
        `


        // 编译模板
        const {code} = CompilerDOM.compile(vuesx);


        // code：
        // const { createElementVNode: _createElementVNode, toDisplayString: _toDisplayString } = Vue
        // return function render(_ctx, _cache){return _createElementVNode("div", null, "hi, " + _toDisplayString(_ctx.message))}"

        // new Function有点类似于eval。最后一个参数是函数体字符串，前面是参数
        // runtime-core作为Vue，暴露createElementVNode和toDisplayString函数
        const render = new Function('Vue', code)(Vue);
        return 123
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
            this.$refs.audioPlayer.currentTime = Math.floor(this.realPlayingValue / this.secondPlayingUnit)
            this.frontPlay()
        }
    }

    mounted() {
        this.queryMusicList()

        if (this.$route.query.chatId) {
            CommonEventUtil.emit(SocialuniImEventKey.socialuniImPageInit, this.$route.query)
        }

        /!*if (this.musicRoomInfo) {
            if (this.musicRoomInfo.playing) {
                // this.frontPlay()
            }
        }*!/

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

            if (this._realPlayingValue >= this.musicMax && this.musicRoomInfo.playing) {
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
        SocialuniMusicAPI.playMusicAPI(socialuniMusicStore.musicRoomId, this.musicRoomInfo).then(res => {
            socialuniMusicStore.setMusicRoomInfo(res.data)
        })
    }

    terstfasd111() {
        // socialuniUserRequest.get('https://api.sd-rtn.com/api/dev/v1/channel/user/5e681410a7434ce9bba3e268226ce537/51b26fe57a9d4d148d9b7df536eeebfa')

        /!*musicRequest.get(`api/dev/v1/channel/user/5e681410a7434ce9bba3e268226ce537/51b26fe57a9d4d148d9b7df536eeebfa`, {
            headers: {
                Authorization: "Basic MWE5N2IyZmU3NjY2NGVmNjhiZmRkZjcyNTZjZjkxZDM6OTk5YzA2ODljYzc5NDEyOGI0NTBjMWQ3MDJmMGUyZjM="
            }
        })*!/

        SocialuniMusicAPI.queryMusicChannel(socialuniMusicStore.musicRoomId)
    }

    testvalue = 0

//设置当前时间和播放时间

    deleteYun111() {
        SocialuniMusicAPI.updateMusicAPI(socialuniMusicStore.musicRoomId, {
            isPause: true
        })
    }

    jixuYun111() {
        SocialuniMusicAPI.updateMusicAPI(socialuniMusicStore.musicRoomId, {
            isPause: false
        })
    }

}
*/
