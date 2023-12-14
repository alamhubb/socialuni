import socialuniUserRequest from "socialuni-user-api/src/request/socialuniUserRequest.ts";
import MusicPlayerSongPlayingInfoRO from "socialuni-music-sdk/src/model/MusicPlayerSongPlayingInfoRO.ts";

export default class SocialuniDeployAPI {
    static checkProjectName(projectName: string) {
        return socialuniUserRequest.get<boolean>(`socialuni/deploy/queryProjectName/${projectName}`)
    }
}
