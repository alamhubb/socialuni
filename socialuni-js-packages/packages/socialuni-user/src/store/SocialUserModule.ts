import SocialuniUserStorageUtil from '../utils/SocialuniUserStorageUtil'
import AlertUtil from '../utils/AlertUtil'
import ToastUtil from '../utils/ToastUtil'
import SocialuniTokenUtil from '../utils/SocialuniTokenUtil'

//用来存储当前用户的一些信息
import {Pinia, Store} from "pinia-class-component"
import CenterUserDetailRO from "socialuni-api/src/model/social/CenterUserDetailRO";
import SocialLoginRO from "socialuni-api/src/model/social/SocialLoginRO";
import UserService from "../service/UserService";
import {socialUserModule} from "./store";
import SocialuniUserRO from "../model/SocialuniUserRO";
import SocialuniUserAPI from "../api/SocialuniUserAPI";

@Store
export default class SocialUserModule extends Pinia {
    private userInfo: SocialuniUserRO = null
    private userToken: string = SocialuniTokenUtil.get() || null


    get user() {
        return this.userInfo
    }

    get hasToken(): boolean {
        return !!this.userToken
    }

    get token() {
        return this.userToken
    }

    get imToken() {
        if (this.user) {
            return this.user.imToken
        }
        return null
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
        this.userToken = token
        SocialuniTokenUtil.set(token)
    }

    isMine(user: CenterUserDetailRO) {
        return user && this.user && user.id === this.user.id
    }

    get hasPhoneNum() {
        return this.user && this.user.phoneNum
    }

    removeUserAndToken() {
        this.setUserAndToken(null)
    }

    setUser(user: SocialuniUserRO) {
        this.userInfo = user
        SocialuniUserStorageUtil.set(user)
    }

    //查询用户信息
    getUserInfo(user: SocialuniUserRO) {
    }

    //注册用户
    registerUser(user: SocialuniUserRO) {
    }

    async initSocialuniUserModule(mineUser?: SocialuniUserRO) {
        //判断是否已登录已有token,userId
        if (this.userId) {
            const {data}: { data: SocialuniUserRO } = await SocialuniUserAPI.getMineUserInfoAPI();
            //考虑清空缓存的情况
            //从后台根据api获取用户信息， 并且更新user。
            //并且设置
            this.setUser(data)
        } else if (mineUser) {
            //调用登录或者注册接口，得到token和user
            //SocialLoginRO<CenterUserDetailRO>
            this.setUserAndToken({} as any)
        }
    }

    //对外开放的只有一个的登录接口


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
            UserService.userLogout()
            ToastUtil.toast('用户退出')
        })
    }

    destroyAccount() {
        return AlertUtil.confirm('是否注销账号，7天内不再登录，账号将彻底清空无法使用').then(() => {
            SocialuniUserAPI.destroyAccountAPI().then(() => {
                UserService.userLogout()
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
