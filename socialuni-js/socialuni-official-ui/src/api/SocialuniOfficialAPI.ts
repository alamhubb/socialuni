import socialuniAppRequest from "socialuni-app-api/src/socialuniAppRequest.ts";

export default class SocialuniOfficialAPI {
    static statisticsAPI(){
        return socialuniAppRequest.get('socialuni/official/statistics')
    }

    static loginAPI(nickname){
        return socialuniAppRequest.post('socialuni/official/login',nickname)
    }

    static getMineUserAPI(){
        return socialuniAppRequest.get('socialuni/official/getMineUser')
    }
}
