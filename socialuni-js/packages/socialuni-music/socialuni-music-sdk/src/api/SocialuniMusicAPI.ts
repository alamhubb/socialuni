import socialuniUserRequest from "socialuni-user-api/src/request/socialuniUserRequest";
import socialuniMusicStore from "../store/SocialuniMusicStore";

export default class SocialuniMusicAPI {
    static playMusicAPI(channelName, musicUrl) {
        return socialuniUserRequest.post<any>(`socialuni/music/playMusic/${channelName}`, musicUrl)
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
