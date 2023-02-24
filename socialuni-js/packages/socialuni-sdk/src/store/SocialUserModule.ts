//用来存储当前用户的一些信息
import {Pinia, Store} from "pinia-class-component"
import SocialLoginRO from "socialuni-api/src/model/social/SocialLoginRO";
import {socialSystemModule, socialUserModule} from "./store";
import SocialuniTokenUtil from "socialuni-sdk/src/utils/SocialuniTokenUtil";
import SocialuniUserStorageUtil from "socialuni-sdk/src/utils/SocialuniUserStorageUtil";
import SocialuniImUserTokenUtil from "../utils/SocialuniImUserTokenUtil";
import AlertUtil from "socialuni-sdk/src/utils/AlertUtil";
import ToastUtil from "socialuni-sdk/src/utils/ToastUtil";
import UserService from "../service/UserService";
import SocialuniMineUserRO from "socialuni-api/src/model/user/SocialuniMineUserRO";
import SocialuniMineUserAPI from "socialuni-api/src/api/socialuni/SocialuniMineUserAPI";
import LoginAPI from "socialuni-api/src/api/socialuni/LoginAPI";

@Store
export default class SocialUserModule extends Pinia {
    private userInfo: SocialuniMineUserRO = SocialuniUserStorageUtil.get() || null
    private userToken: string = SocialuniTokenUtil.get() || null

    //查询用户信息
    getUserInfo(user: SocialuniMineUserRO) {
    }

    //注册用户
    registerUser(user: SocialuniMineUserRO) {
    }


    //登录成功后，设置用户，设置im
    //刷新用户的场景，手动刷新和登录后刷新用户的信息和imtoken？
    //传入user，就行了。
    async initSocialuniUserModule() {
        //判断是否已登录已有token,userId
        if (this.hasToken) {
            const {data}: { data: SocialuniMineUserRO } = await SocialuniMineUserAPI.getMineUserInfoAPI();
            //考虑清空缓存的情况
            //从后台根据api获取用户信息， 并且更新user。
            //并且设置
            this.setUser(data)
            //刷新token
            const tokenRo =  await LoginAPI.refreshToken();
            // 有才设置新的token.
            if(tokenRo?.data?.token){
                this.setToken(tokenRo?.data?.token);
            }
        }
    }

    //对外开放的只有一个的登录接口


    get mineUser() {
        return this.userInfo
    }

    get hasToken(): boolean {
        return !!this.userToken
    }

    get token() {
        return this.userToken
    }

    setUserAndToken(loginRO: SocialLoginRO<SocialuniMineUserRO>) {
        if (loginRO) {
            this.setUser(loginRO.user)
            this.setToken(loginRO.token)
        } else {
            this.setToken(null)
            this.setUser(null)
        }
    }

    private setToken(token: string) {
        this.userToken = token
        SocialuniTokenUtil.set(token)
    }

    isMine(user: SocialuniMineUserRO) {
        return user && this.mineUser && user.id === this.mineUser.id
    }

    get hasPhoneNum() {
        return this.mineUser && this.mineUser.phoneNum
    }

    removeUserAndToken() {
        this.setUserAndToken(null)
    }

    setUser(user: SocialuniMineUserRO) {
        this.userInfo = user
        SocialuniUserStorageUtil.set(user)
    }

    get hasUser(): boolean {
        return !!this.mineUser
    }

    get userId() {
        if (this.mineUser) {
            return this.mineUser.id
        }
        return null
    }

    destroyAccount() {
        return AlertUtil.confirm('是否注销账号，7天内不再登录，账号将彻底清空无法使用').then(() => {
            /*SocialuniMineUserAPI.destroyAccountAPI().then(() => {
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
