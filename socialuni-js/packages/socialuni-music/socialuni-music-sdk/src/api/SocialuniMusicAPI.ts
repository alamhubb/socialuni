import socialuniUserRequest from "@socialuni/socialuni-user-api/src/request/socialuniUserRequest";

export default class SocialuniMusicAPI {
    static playMusicAPI(channelName, musicUrl) {
        return socialuniUserRequest.post<any>(`socialuni/music/playMusic/${channelName}`, musicUrl)
    }

    static updateMusicAPI(updateMusicQO) {
        return socialuniUserRequest.post<any>(`socialuni/music/updateMusic`, updateMusicQO)
    }

    static getMusicTokenAPI(channel) {
        return socialuniUserRequest.get<string>(`socialuni/music/getMusicToken/${channel}`)
    }

    static getMusicInitDataAPI() {
        return socialuniUserRequest.get<any>(`socialuni/music/getMusicInitData`)
    }
}
