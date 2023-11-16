import {reactive, UnwrapNestedRefs} from "vue";
import SocialuniMusicAPI from "../api/SocialuniMusicAPI";
import AgoraRTC, {IAgoraRTCClient, IBufferSourceAudioTrack, IRemoteAudioTrack} from "agora-rtc-sdk-ng"

export class SocialuniMusicStore {
    private _appId: string = null
    private _musicToken: string = null
    private _channelName: string = null
    private _localAudioTrack: IBufferSourceAudioTrack | IRemoteAudioTrack = null

    get channelName(): string {
        return this._channelName;
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

    get client(): any {
        return client;
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

