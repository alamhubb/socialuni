import {reactive, UnwrapNestedRefs} from "vue";
import SocialuniMusicAPI from "../api/SocialuniMusicAPI";
import AgoraRTC, {IAgoraRTCClient, IBufferSourceAudioTrack, IRemoteAudioTrack} from "agora-rtc-sdk-ng"
import {SocialuniMusicRoomInfoRO} from "../model/SocialuniMusicRoomPlayerInfoRO";
import MusicPlayerSongPlayingInfoRO from "../model/MusicPlayerSongPlayingInfoRO";
import MusicPlayerSongInfoRO from "../model/MusicPlayerSongInfoRO";

export class SocialuniMusicStore {
    private _appId: string = null
    private _musicToken: string = null
    //为什么要放在store中，而不放在播放器中，需要传递
    private _musicRoomInfo: MusicPlayerSongPlayingInfoRO = null
    private _songList: MusicPlayerSongInfoRO[] = []
    private _musicRoomId: string = null
    private _musicRoleId: string = null
    musicMuted: boolean = null
    musicVolume: number = 50
    private _localAudioTrack: IBufferSourceAudioTrack | IRemoteAudioTrack = null

    get songList() {
        return this._songList
    }

    setSongList(songList: MusicPlayerSongInfoRO[]) {
        this._songList = songList;
    }

    async querySongList() {
        const res1 = await SocialuniMusicAPI.querySongListAPI(socialuniMusicStore.musicRoomId)
        const songList: MusicPlayerSongInfoRO[] = res1.data
        socialuniMusicStore.setSongList(songList)
    }


    get musicRoleId(): string {
        return this._musicRoleId;
    }

    setMusicRoleId(value: string) {
        this._musicRoleId = value;
    }

    async setMusicRoomInfo(value: MusicPlayerSongPlayingInfoRO) {
        console.log('chufale shezhi roomInfo')
        console.trace('chufale shezhi roomInfo')
        if (value) {
            const findData = this.songList.find(item => item.musicId === value.musicId)
            if (findData) {
                this._musicRoomInfo = new MusicPlayerSongPlayingInfoRO({
                    ...findData,
                    ...value
                })
            } else {
                await this.querySongList()
                const findData = this.songList.find(item => item.musicId === value.musicId)
                if (findData) {
                    this._musicRoomInfo = new MusicPlayerSongPlayingInfoRO({
                        ...findData,
                        ...value
                    })
                }
            }
            if (typeof value.playingTimestamp === 'string') {
                console.log('后台返回时间')
                console.log(value.playingTimestamp)
                console.log(new Date(value.playingTimestamp).toLocaleString())
            } else if (typeof value.playingTimestamp === 'number') {
                console.log('websocket返回时间')
                console.log(value.playingTimestamp)
                console.log(new Date(value.playingTimestamp).toLocaleString())
            } else {
                console.log('前台设置时间')
                console.log(value.playingTimestamp)
                console.log(value.playingTimestamp.toLocaleString())
            }
        }
    }

    get musicRoomInfo(): MusicPlayerSongPlayingInfoRO {
        return this._musicRoomInfo
    }

    get musicRoomId(): string {
        if (this._musicRoomId) {
            return this._musicRoomId;
        }
        return ''
    }

    setMusicRoomId(value: string) {
        this._musicRoomId = value;
    }

    get localAudioTrack(): IBufferSourceAudioTrack | IRemoteAudioTrack {
        return this._localAudioTrack;
    }

    setLocalAudioTrack(value: IBufferSourceAudioTrack | IRemoteAudioTrack) {
        this._localAudioTrack = value;
    }

    get appId(): any {
        return this._appId;
    }

    setAppId(value: any) {
        this._appId = value;
    }

    get musicToken(): any {
        return this._musicToken;
    }

    setMusicToken(value: any) {
        this._musicToken = value;
    }

    async getMusicInitDataAction() {
        const res = await SocialuniMusicAPI.getMusicInitDataAPI()
        this.setAppId(res.data.appId)
    }

    async getMusicTokenAction(channel) {
        const res = await SocialuniMusicAPI.getMusicTokenAPI(channel)
        this.setMusicToken(res.data)
        // this.setMusicToken('007eJxTYPgnM+sqs++9rVma4kWFYpGbT0oXSRaWLrl62WNxt1Ccj5ACg2mqmYWhiaFBormJsUlyqmVSUqJxqpGZhZGRWXKqqbF5RntoakMgI8Ntn99MjAwQCOIDdRomGZmlpZqaJ1qmmKQYmlikWCaZp6SZGpulpqYmpSUyMAAAe+slxg==')
    }
}

const socialuniMusicStore: UnwrapNestedRefs<SocialuniMusicStore> = reactive(new SocialuniMusicStore())

export default socialuniMusicStore

