import {reactive, UnwrapNestedRefs} from "vue";
import SocialuniMineUserRO from "socialuni-api-base/src/model/user/SocialuniMineUserRO";
import SocialuniUserStorageUtil from "../util/SocialuniUserStorageUtil";
import SocialLoginRO from "socialuni-api-base/src/model/social/SocialLoginRO";
import LoginAPI from "socialuni-user-api/src/api/LoginAPI";
import SocialuniMineUserAPI from "socialuni-user-api/src/api/SocialuniMineUserAPI";
import QingAppUtil from "qing-compat-js/src/util/QingAppUtil";
import {socialuniTokenModule} from "./SocialuniTokenModule";
import SocialuniUserRO from "socialuni-api-base/src/model/user/SocialuniUserRO";
import {socialuniAppUserModule} from "./SocialuniAppUserModule";
import CommonEventUtil from "qing-compat-js/src/util/CommonEventUtil";
import SocialuniUserEventConst from "../constant/SocialuniUserEventConst";
import WebsocketUtil from "socialuni-api-base/src/websocket/WebsocketUtil";
import SocialuniUserEventEmit from "../event/SocialuniUserEventEmit";

class SocialuniUserModule {
    get token() {
        return socialuniTokenModule.token
    }

    private userInfo: SocialuniUserRO = SocialuniUserStorageUtil.get() || null

    get mineUser() {
        return this.userInfo
    }

    // get hasPhoneNum() {
    //     return this.mineUser && this.mineUser.phoneNum
    // }

    // private userToken: string = SocialuniTokenUtil.get() || null

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
        SocialuniUserEventEmit.initOrUserChange()
        if (this.hasToken) {
            await socialuniAppUserModule.getMineUserAction()
        }
        WebsocketUtil.createWebsocket()
    }

    //对外开放的只有一个的登录接口


    get hasToken(): boolean {
        return !!socialuniTokenModule.token
    }

    setUserAndToken(loginRO: SocialLoginRO<SocialuniUserRO>) {
        if (loginRO) {
            this.setUser(loginRO.user)
            socialuniTokenModule.setToken(loginRO.token)
        } else {
            socialuniTokenModule.setToken(null)
            this.setUser(null)
        }
    }

    isMine(user: SocialuniMineUserRO) {
        return user && this.mineUser && user.id === this.mineUser.id
    }


    removeUserAndToken() {
        this.setUserAndToken(null)
    }

    setUser(user: SocialuniUserRO) {
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
        return QingAppUtil.AlertUtil.confirm('是否注销账号，7天内不再登录，账号将彻底清空无法使用').then(() => {
            /*SocialuniMineUserAPI.destroyAccountAPI().then(() => {
                UserService.userLogout()
                QingAppUtil.ToastUtil.toast('注销成功')
            })*/
        })
    }

    /**
     * 调用后台仅获取user信息
     */

    /*getMineUserAction() {
        return SocialuniMineUserAPI.getMineUserInfoAPI().then((res: any) => {
            socialuniUserModule.setUser(res.data)
        })
    }*/
}

export const socialuniUserModule: UnwrapNestedRefs<SocialuniUserModule> = reactive(new SocialuniUserModule())
