import SocialuniUserRO from "../model/SocialuniUserRO";
import UserEditVO from "socialuni-user/src/model/user/UserEditVO"
import ImgAddQO from "socialuni-user/src/model/user/ImgAddQO"
import request from "socialuni-common/src/request/request";
import SocialSendAuthCodeQO from "socialuni-user/src/model/phone/SocialSendAuthCodeQO";
import UniProviderLoginQO from "socialuni-user/src/model/login/UniProviderLoginQO";
import SocialLoginRO from "socialuni-user/src/model/social/SocialLoginRO";
import SocialPhoneNumLoginQO from "socialuni-user/src/model/phone/SocialPhoneNumLoginQO";

export default class SocialuniMineUserAPI {
    static getMineUserInfoAPI() {
        return request.get<SocialuniUserRO>('socialuni/mineUser/getMineUser')
    }

    static editUserAPI(user: UserEditVO) {
        return request.post('socialuni/mineUser/editUser', user)
    }

    static addUserAvatarImgAPI(userImg: ImgAddQO) {
        return request.post<SocialuniUserRO>('socialuni/mineUser/addUserAvatarImg', userImg)
    }

    static sendAuthCodeAPI (phoneNum: string) {
        return request.post('socialuni/mineUser/sendAuthCode', new SocialSendAuthCodeQO(phoneNum))
    }
    static providerLoginAPI (loginData: UniProviderLoginQO) {
        return request.post<SocialLoginRO<SocialuniUserRO>>('socialuni/mineUser/providerLogin', loginData)
    }

    static socialuniPhoneLoginAPI (loginData: UniProviderLoginQO) {
        //接口已作废
        return request.post<SocialLoginRO<SocialuniUserRO>>('socialuni/mineUser/socialuniPhoneLogin', loginData)
    }

    static phoneLoginAPI (loginData: SocialPhoneNumLoginQO) {
        return request.post<SocialLoginRO<SocialuniUserRO>>('socialuni/mineUser/phoneLogin', loginData)
    }
}
