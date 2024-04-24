import QingAppUtil from "qingjs/src/util/QingAppUtil";
import SocialuniLoginService from "./SocialuniLoginService";
import {socialuniConfigModule} from "socialuni-app-sdk/src/store/SocialuniConfigModule";
import PasswordUtil from "../util/PasswordUtil";
import SocialuniLoginFormService from "./SocialuniLoginFormService";


interface SocialuniLoginViewServiceRefs {
    authCode: HTMLInputElement
    password: HTMLInputElement
}

export default class SocialuniLoginViewService extends SocialuniLoginFormService {

    bindBtnDisabled = false
    get loginBtnText() {
        if (this.loginData.phoneNumRegistered) {
            return '登 录'
        }
        return '注 册 并 登 录'
    }

    checkContractChecked() {
        if (!this.contractChecked) {
            return QingAppUtil.ToastUtil.throwError('请仔细阅读用户协议、隐私政策等内容后勾选同意')
        }
    }

    // 手机号登陆和手机号绑定
    async handleLogin() {
        if (this.bindBtnDisabled) {
            return QingAppUtil.ToastUtil.warning('正在登陆中，请勿重复点击')
        }
        this.loginData.checkPhoneNum()
        this.loginData.checkPassword()
        this.loginData.checkAuthCode()
        this.checkContractChecked()
        this.bindBtnDisabled = true

        console.log(socialuniConfigModule.allConfig.publicKey)
        console.log(this.loginData.password)

        const password = await PasswordUtil.rsaEncode(socialuniConfigModule.allConfig.publicKey, this.loginData.password)

        if (this.loginData.phoneNumRegistered) {
            SocialuniLoginService.passwordLogin(this.loginData.phoneNum, password).then((data) => {
                this.instance.emit('loginSuccess', data)
            }).finally(() => {
                this.bindBtnDisabled = false
            })
        } else {
            SocialuniLoginService.phonePasswordLogin(this.loginData.phoneNum, password, this.loginData.authCode).then((data) => {
                this.instance.emit('loginSuccess', data)
            }).finally(() => {
                this.bindBtnDisabled = false
            })
        }
    }
}
