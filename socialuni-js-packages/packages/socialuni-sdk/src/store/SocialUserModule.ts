import SocialuniUserStorageUtil from '../utils/SocialuniUserStorageUtil'
import AlertUtil from '../utils/AlertUtil'
import ToastUtil from '../utils/ToastUtil'
import SocialuniTokenUtil from '../utils/SocialuniTokenUtil'

//用来存储当前用户的一些信息
import {Pinia, Store} from "pinia-class-component"
import CenterUserDetailRO from "socialuni-api/src/model/social/CenterUserDetailRO";
import SocialLoginRO from "socialuni-api/src/model/social/SocialLoginRO";
import UserService from "../service/UserService";
import SocialuniUserAPI from "socialuni-api/src/api/socialuni/SocialuniUserAPI";
import {socialUserModule} from "./store";

@Store
export default class SocialUserModule extends Pinia {
    private user: CenterUserDetailRO = SocialuniUserStorageUtil.get() || null
    private token: string = SocialuniTokenUtil.get() || null

    getUser() {
        return this.user
    }

    get hasToken(): boolean {
        return !!this.token
    }

    getToken() {
        return this.token
    }

    setUserAndToken(loginRO: SocialLoginRO<CenterUserDetailRO>) {
        if (loginRO) {
            this.setUser(loginRO.user)
            this.setToken(loginRO.token)
        } else {
            this.setToken(null)
            this.setUser(null)
        }
    }

    setToken(token: string) {
        this.token = token
        SocialuniTokenUtil.set(token)
    }

    isMine(user: CenterUserDetailRO) {
        return user && this.user && user.id === this.user.id
    }

    get hasPhoneNum() {
        return this.user && this.user.phoneNum
    }

    removeUser() {
        this.setUserAndToken(null)
    }

    setUser(user: CenterUserDetailRO) {
        this.user = user
        SocialuniUserStorageUtil.set(user)
    }

    get hasUser(): boolean {
        return !!this.user
    }

    get userId() {
        if (this.user) {
            return this.user.id
        }
        return null
    }

    loginOut() {
        return AlertUtil.confirm('是否退出登录').then(() => {
            UserService.clearUserInfoCom()
            ToastUtil.toast('用户退出')
        })
    }

    destroyAccount() {
        return AlertUtil.confirm('是否注销账号，7天内不再登录，账号将彻底清空无法使用').then(() => {
            SocialuniUserAPI.destroyAccountAPI().then(() => {
                UserService.clearUserInfoCom()
                ToastUtil.toast('注销成功')
            })
        })
    }

    /**
     * 调用后台仅获取user信息
     */

    getMineUserAction() {
        return SocialuniUserAPI.getMineUserInfoAPI().then((res: any) => {
            socialUserModule.setUser(res.data)
        })
    }
}
