import socialuniUserRequest from "socialuni-user-api/src/request/socialuniUserRequest";
import socialuniMusicStore from "../store/SocialuniMusicStore";
import SocialuniMusicRoomPlayerInfoRO, {SocialuniMusicRoomInfoRO} from "../model/SocialuniMusicRoomPlayerInfoRO";
import MusicPlayerSongPlayingInfoRO from "../model/MusicPlayerSongPlayingInfoRO";
import {SocialuniMusicRoomUserInfoRO} from "../model/SocialuniMusicRoomUserInfoRO";

export default class SocialuniMusicAPI {
    static queryMusicRoomPlayerInfoAPI(channelName: string) {
        return socialuniUserRequest.get<MusicPlayerSongPlayingInfoRO>(`socialuni/music/queryMusicRoomPlayerInfo/${channelName}`)
    }

    static queryMusicRoomUserInfoAPI(channelName: string) {
        return socialuniUserRequest.get<SocialuniMusicRoomUserInfoRO>(`socialuni/music/queryMusicRoomUserInfo/${channelName}`)
    }


    static playMusicAPI(channelName: string, musicRoomInfo: SocialuniMusicRoomInfoRO) {
        return socialuniUserRequest.post<MusicPlayerSongPlayingInfoRO>(`socialuni/music/playMusic/${channelName}`, musicRoomInfo)
    }

    static queryMusicChannel(channelName) {
        return socialuniUserRequest.get<any>(`socialuni/music/queryMusicChannel/${channelName}`)
    }

    static updateMusicAPI(channelName: string, updateMusicQO) {
        return socialuniUserRequest.post<any>(`socialuni/music/updateMusic/${channelName}`, updateMusicQO)
    }

    static getMusicTokenAPI(channel) {
        return socialuniUserRequest.get<string>(`socialuni/music/getMusicToken/${channel}`)
    }

    static getMusicInitDataAPI() {
        return socialuniUserRequest.get<any>(`socialuni/music/getMusicInitData`)
    }

    static queryAllPlayers() {
        return socialuniUserRequest.get<any>(`api/v1/projects/5e681410a7434ce9bba3e268226ce537/cloud-player/players`, {
            params: {
                filter: `channelName eq ${socialuniMusicStore.channelName}`
            }
        })
    }
}
