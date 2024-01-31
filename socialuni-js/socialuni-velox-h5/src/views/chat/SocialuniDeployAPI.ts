import socialuniUserRequest from "socialuni-user-api/src/request/socialuniUserRequest.ts";
import MusicPlayerSongPlayingInfoRO from "socialuni-music-sdk/src/model/MusicPlayerSongPlayingInfoRO.ts";
import ObjectUtil from "qing-util/src/util/ObjectUtil.ts";

export default class SocialuniDeployAPI {
    static checkProjectName(projectName: string) {
        return socialuniUserRequest.get<boolean>(`socialuni/deploy/queryProjectName/${projectName}`)
    }

    static autoCreateCanUseProjectName() {
        return socialuniUserRequest.get<string>(`socialuni/deploy/autoCreateCanUseProjectName`)
    }

    static deployProject(formData: any) {
        return socialuniUserRequest.post<boolean>(`socialuni/deploy/deployProject`, ObjectUtil.toFormData(formData))
    }
}
