import request from "socialuni-api/src/request/request";

export default class SocialuniImUserAPI {
    static getImUserTokenAPI() {
        return request.get<string>('socialuni/imUser/getImUserToken')
    }
}
