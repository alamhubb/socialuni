import request from '../request/request';;

export default class SocialuniImUserAPI {
    static getImUserTokenAPI() {
        return request.get<string>('socialuni/imUser/getImUserToken')
    }
}
