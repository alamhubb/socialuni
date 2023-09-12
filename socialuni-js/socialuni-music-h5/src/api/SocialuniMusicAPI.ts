import socialuniUserRequest from "@socialuni/socialuni-user-api/src/request/socialuniUserRequest";
import SocialuniMusicInfoRO from "@/model/SocialuniMusicInfoRO";

export default class SocialuniMusicAPI {
    static queryMusicListAPI() {
        return socialuniUserRequest.get<SocialuniMusicInfoRO[]>('socialuni/music/queryMusicList')
    }
}
