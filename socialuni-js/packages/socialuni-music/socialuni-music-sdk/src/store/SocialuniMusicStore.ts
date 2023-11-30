import {reactive, UnwrapNestedRefs} from "vue";
import SocialuniMusicAPI from "../api/SocialuniMusicAPI";
import AgoraRTC, {IAgoraRTCClient, IBufferSourceAudioTrack, IRemoteAudioTrack} from "agora-rtc-sdk-ng"
import {SocialuniMusicRoomInfoRO} from "../model/SocialuniMusicRoomPlayerInfoRO";

export class SocialuniMusicStore {
    private _appId: string = null
    private _musicToken: string = null
    private _musicRoomInfo: SocialuniMusicRoomInfoRO = null
    private _channelName: string = null
    private _localAudioTrack: IBufferSourceAudioTrack | IRemoteAudioTrack = null


    setMusicRoomInfo(value: SocialuniMusicRoomInfoRO) {
        this._musicRoomInfo = value;

        if (typeof value.playingTimestamp === 'string') {
            console.trace('后台返回时间')
            console.log(new Date(value.playingTimestamp).getTime())
        } else {
            console.trace('前台设置时间')
            console.log(value.playingTimestamp.getTime())
        }
    }

    get musicRoomInfo(): SocialuniMusicRoomInfoRO {
        return this._musicRoomInfo
    }

    get channelName(): string {
        if (this._channelName) {
            return this._channelName;
        }
        return ''
    }

    setChannelName(value: string) {
        console.log('设置了channel')
        this._channelName = value;
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

