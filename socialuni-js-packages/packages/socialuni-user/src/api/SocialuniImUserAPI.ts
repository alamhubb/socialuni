import socialuniUserRequest from "../request/socialuniUserRequest";

export default class SocialuniImUserAPI {
    static getImUserTokenAPI() {
        return socialuniUserRequest.get<string>('socialuni/imUser/getImUserToken')
    }
}
