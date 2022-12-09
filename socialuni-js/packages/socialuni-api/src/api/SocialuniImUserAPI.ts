import request from "socialuni-api/src/request/request";

export default class SocialuniUserImAPI {
    static getUserImTokenAPI() {
        return request.get<string>('socialuni/imUser/getUserImToken')
    }
}
