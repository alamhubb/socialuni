import socialuniUserRequest from "@socialuni/socialuni-user-api/src/request/socialuniUserRequest";

export default class SocialuniMusicAPI {
    static playMusicAPI(musicUrl) {
        return socialuniUserRequest.post<any>(`socialuni/music/playMusic`, musicUrl)
    }
}
