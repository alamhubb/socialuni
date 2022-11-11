import request from "socialuni-common/src/request/request";

export default class SocialuniImUserAPI {
    static getImUserTokenAPI() {
        return request.get<string>('socialuni/imUser/getImUserToken')
    }
}
