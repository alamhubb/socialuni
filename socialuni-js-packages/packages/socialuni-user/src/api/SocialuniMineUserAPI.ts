import SocialuniUserRO from "../model/SocialuniUserRO";
import UserEditVO from 'socialuni-api/src/model/user/UserEditVO'
import ImgAddQO from 'socialuni-api/src/model/user/ImgAddQO'
import CenterUserDetailRO from 'socialuni-api/src/model/social/CenterUserDetailRO'
import ImgFileVO from 'socialuni-api/src/model/ImgFileVO'
import UserPayResultVO from 'socialuni-api/src/model/user/UserPayResultVO'
import UserPayVO from 'socialuni-api/src/model/user/UserPayVO'
import UserQueryVO from 'socialuni-api/src/model/user/UserQueryVO'
import request from "socialuni-common/src/request/request";
import SocialSendAuthCodeQO from "socialuni-api/src/model/phone/SocialSendAuthCodeQO";
import UniProviderLoginQO from "socialuni-api/src/model/UniProviderLoginQO";
import SocialLoginRO from "socialuni-api/src/model/social/SocialLoginRO";
import SocialPhoneNumLoginQO from "socialuni-api/src/model/phone/SocialPhoneNumLoginQO";

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
