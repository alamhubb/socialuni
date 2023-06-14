import {reactive, UnwrapNestedRefs} from "vue";
import SocialuniMineUserRO from "socialuni-api-base/src/model/user/SocialuniMineUserRO";
import SocialuniUserStorageUtil from "../util/SocialuniUserStorageUtil";
import SocialLoginRO from "socialuni-api-base/src/model/social/SocialLoginRO";
import LoginAPI from "socialuni-user-api/src/api/LoginAPI";
import {socialuniTokenModule} from "socialuni-user-sdk/src/store/SocialuniTokenModule";
import SocialuniMineUserAPI from "socialuni-user-api/src/api/SocialuniMineUserAPI";
import CenterUserDetailRO from "socialuni-api-base/src/model/social/CenterUserDetailRO";
import UserMsgUtil from "../util/UserMsgUtil";
import {socialuniConfigModule} from "socialuni-app-sdk/src/store/SocialuniConfigModule";
import ToastUtil from "socialuni-native-uni/src/util/ToastUtil";
import SocialuniAppUtil from "socialuni-native-util/src/util/SocialuniAppUtil";

class SocialuniUserModule {
    get token() {
        return socialuniTokenModule.token
    }

    private userInfo: SocialuniMineUserRO = SocialuniUserStorageUtil.get() || null

    get mineUser() {
        return this.userInfo
    }

    get hasPhoneNum() {
        return this.mineUser && this.mineUser.phoneNum
    }

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
        if (this.hasToken) {
            const {data}: { data: SocialuniMineUserRO } = await SocialuniMineUserAPI.getMineUserInfoAPI();
            //考虑清空缓存的情况
            //从后台根据api获取用户信息， 并且更新user。
            //并且设置
            this.setUser(data)
            //刷新token
            const tokenRo = await LoginAPI.refreshToken();
            // 有才设置新的token.
            if (tokenRo?.data?.token) {
                socialuniTokenModule.setToken(tokenRo?.data?.token);
            }
        }
    }

    //对外开放的只有一个的登录接口


    get hasToken(): boolean {
        return !!socialuniTokenModule.token
    }

    setUserAndToken(loginRO: SocialLoginRO<SocialuniMineUserRO>) {
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

    setUser(user: SocialuniMineUserRO) {
        this.userInfo = user
        console.log(this.userInfo)
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
        return SocialuniAppUtil.AlertUtil.confirm('是否注销账号，7天内不再登录，账号将彻底清空无法使用').then(() => {
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
            socialuniUserModule.setUser(res.data)
        })
    }
}

export const socialuniUserModule: UnwrapNestedRefs<SocialuniUserModule> = reactive(new SocialuniUserModule())
