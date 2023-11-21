import socialuniUserRequest from "socialuni-user-api/src/request/socialuniUserRequest";

export default class SocialuniCustomUserAPI {

    static createUserAPI(nickname: string, phoneNum: string) {
        return socialuniUserRequest.post<void>('custom/createUser', {nickname, phoneNum})
    }

}
