//用来存储当前用户的一些信息
import {Pinia, Store} from "pinia-class-component"
import CenterUserDetailRO from "socialuni-api/src/model/social/CenterUserDetailRO";
import SocialLoginRO from "socialuni-api/src/model/social/SocialLoginRO";
import {socialUserModule} from "./store";
import SocialuniUserRO from "../model/SocialuniUserRO";
import SocialuniTokenUtil from "socialuni-common/src/utils/SocialuniTokenUtil";
import SocialuniUserStorageUtil from "socialuni-user/src/utils/SocialuniUserStorageUtil";
import SocialuniImUserTokenUtil from "../utils/SocialuniImUserTokenUtil";
import SocialuniImUserAPI from "../api/SocialuniImUserAPI";
import AlertUtil from "socialuni-use/src/utils/AlertUtil";
import ToastUtil from "socialuni-use/src/utils/ToastUtil";
import UserService from "../service/UserService";
import SocialuniMineUserAPI from "../api/SocialuniMineUserAPI";

@Store
export default class SocialUserModule extends Pinia {
    private userInfo: SocialuniUserRO = SocialuniUserStorageUtil.get() || null
    private userToken: string = SocialuniTokenUtil.get() || null
    private userImToken: string = SocialuniImUserTokenUtil.get() || null

    get imToken() {
        return this.userImToken
    }

    //查询用户信息
    getUserInfo(user: SocialuniUserRO) {
    }

    //注册用户
    registerUser(user: SocialuniUserRO) {
    }


    //登录成功后，设置用户，设置im
    //刷新用户的场景，手动刷新和登录后刷新用户的信息和imtoken？
    //传入user，就行了。
    async initSocialuniUserModule() {
        //判断是否已登录已有token,userId
        if (this.hasToken) {
            console.log(2222)
            const {data}: { data: SocialuniUserRO } = await SocialuniMineUserAPI.getMineUserInfoAPI();
            console.log(data)
            //考虑清空缓存的情况
            //从后台根据api获取用户信息， 并且更新user。
            //并且设置
            this.setUser(data)
            const res = await SocialuniImUserAPI.getImUserTokenAPI()
            this.setImToken(res.data)
        }
    }

    //对外开放的只有一个的登录接口


    get user() {
        return this.userInfo
    }

    get hasToken(): boolean {
        return !!this.userToken
    }

    get token() {
        return this.userToken
    }

    /*get imToken() {
        if (this.user) {
            return this.user.imToken
        }
        return null
    }*/

    setUserAndToken(loginRO: SocialLoginRO<CenterUserDetailRO>) {
        if (loginRO) {
            this.setUser(loginRO.user)
            this.setToken(loginRO.token)
        } else {
            this.setToken(null)
            this.setUser(null)
        }
    }

    private setImToken(token: string) {
        this.userImToken = token
        SocialuniImUserTokenUtil.set(token)
    }

    private removeImToken() {
        this.setImToken(null)
    }

    private setToken(token: string) {
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
        this.removeImToken()
    }

    setUser(user: SocialuniUserRO) {
        this.userInfo = user
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
            UserService.userLogout()
            socialUserModule.removeUserAndToken()
            ToastUtil.toast('用户退出')
        })
    }

    destroyAccount() {
        return AlertUtil.confirm('是否注销账号，7天内不再登录，账号将彻底清空无法使用').then(() => {
            /*SocialuniUserAPI.destroyAccountAPI().then(() => {
                UserService.userLogout()
                ToastUtil.toast('注销成功')
            })*/
        })
    }

    /**
     * 调用后台仅获取user信息
     */

    getMineUserAction() {
        return SocialuniMineUserAPI.getMineUserInfoAPI().then((res: any) => {
            socialUserModule.setUser(res.data)
        })
    }
}
