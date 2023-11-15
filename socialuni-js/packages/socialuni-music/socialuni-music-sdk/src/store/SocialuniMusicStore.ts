import {reactive, UnwrapNestedRefs} from "vue";
import SocialuniMusicAPI from "../api/SocialuniMusicAPI";


const client = AgoraRTC.createClient({
    mode: "live",
    codec: "vp8"
});


export class SocialuniMusicStore {

    private _appId: string = null
    private _musicToken: string = null
    private _client: IAgoraRTCClient = client
    private _localAudioTrack: IBufferSourceAudioTrack | IRemoteAudioTrack = null


    get localAudioTrack(): IBufferSourceAudioTrack | IRemoteAudioTrack {
        return this._localAudioTrack;
    }

    setLocalAudioTrack(value: IBufferSourceAudioTrack | IRemoteAudioTrack) {
        this._localAudioTrack = value;
    }

    get client(): any {
        return this._client;
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
    }
}

const socialuniMusicStore: UnwrapNestedRefs<SocialuniMusicStore> = reactive(new SocialuniMusicStore())

export default socialuniMusicStore

