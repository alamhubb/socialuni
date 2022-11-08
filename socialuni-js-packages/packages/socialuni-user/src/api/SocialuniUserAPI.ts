import SocialuniUserRO from "../model/SocialuniUserRO";
import UserEditVO from 'socialuni-api/src/model/user/UserEditVO'
import ImgAddQO from 'socialuni-api/src/model/user/ImgAddQO'
import CenterUserDetailRO from 'socialuni-api/src/model/social/CenterUserDetailRO'
import ImgFileVO from 'socialuni-api/src/model/ImgFileVO'
import UserPayResultVO from 'socialuni-api/src/model/user/UserPayResultVO'
import UserPayVO from 'socialuni-api/src/model/user/UserPayVO'
import UserQueryVO from 'socialuni-api/src/model/user/UserQueryVO'
import request from "socialuni-api/src/request/request";

export default class SocialuniUserAPI {
    static getMineUserInfoAPI() {
        return request.get<SocialuniUserRO>('socialuni/user/getMineUser')
    }

    static queryUserDetailAPI(userId: string) {
        return request.get('socialuni/user/queryUserDetail/' + userId)
    }

    static editUserAPI(user: UserEditVO) {
        return request.post('socialuni/user/editUser', user)
    }

    static addUserImgAPI(userImg: ImgAddQO) {
        return request.post<CenterUserDetailRO>('socialuni/user/addUserImg', userImg)
    }

    static addUserAvatarImgAPI(userImg: ImgAddQO) {
        return request.post<CenterUserDetailRO>('socialuni/user/addUserAvatarImg', userImg)
    }

    static deleteUserImgAPI(userImg: ImgFileVO) {
        return request.post('socialuni/user/deleteImg', userImg)
    }


    static deleteUserImgNewAPI(userImg: ImgFileVO) {
        return request.post('socialuni/user/deleteUserImg', {userImgId: userImg.id})
    }

    static updateAvatarAPI(avatar: string) {
        return request.post('socialuni/user/updateAvatar?avatar=' + avatar)
    }

    static getUserContactAPI(userId: string) {
        const user = new UserQueryVO(userId)
        return request.post<string>('socialuni/user/getUserContact', user)
    }

    static switchUserContactAPI(openContact: boolean) {
        return request.post<string>('socialuni/user/switchUserContact?openContact=' + openContact)
    }

    static userPayAPI(provider: string, payType: string, amount?: number) {
        const userPayVO = new UserPayVO(provider, payType, amount)
        return request.post<UserPayResultVO>('socialuni/user/pay', userPayVO)
    }

    static destroyAccountAPI() {
        return request.post('socialuni/user/destroyAccount')
    }

    static getUserImgListAPI(userId: string) {
        return request.get<ImgFileVO[]>('socialuni/user/getUserImgList/' + userId)
    }
}
