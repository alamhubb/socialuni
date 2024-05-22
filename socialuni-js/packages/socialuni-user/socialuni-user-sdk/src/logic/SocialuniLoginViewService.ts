import QingAppUtil from "qingjs/src/util/QingAppUtil";
import SocialuniLoginService from "./SocialuniLoginService";
import {socialuniConfigModule} from "socialuni-app-sdk/src/store/SocialuniConfigModule";
import PasswordUtil from "../util/PasswordUtil";
import SocialuniLoginFormService from "./SocialuniLoginFormService";
import {socialuniUserModule} from "../store/SocialuniUserModule";
import PhoneService from "./PhoneService";
import {socialuniSystemModule} from "qing-util/src/store/SocialuniSystemModule";
import UserPageUtil from "../util/UserPageUtil";
import {socialuniAppUserModule} from "../store/SocialuniAppUserModule";


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
        if (!socialuniUserModule.hasUser) {
            this.checkContractChecked()
        }
        this.bindBtnDisabled = true

        console.log(55555)
        console.log(socialuniConfigModule.allConfig.publicKey)

        const password = await PasswordUtil.rsaEncode(socialuniConfigModule.allConfig.publicKey, this.loginData.password)

        console.log(6666)
        console.log(socialuniUserModule.hasUser)
        if (socialuniUserModule.hasUser) {
            try {
                await PhoneService.bindPhoneNum(this.loginData.phoneNum, password, this.loginData.authCode)
                this.loginAfterHint('绑定成功')
            } catch (e) {
                this.goToOAuthPage()
            } finally {
                this.bindBtnDisabled = true
            }
        } else if (this.loginData.phoneNumRegistered) {
            SocialuniLoginService.passwordLogin(this.loginData.phoneNum, password).then((data) => {
                console.log(this)
                console.log(1111)
                console.log(this.instance)
                console.log(2222)
                this.instance.$emit('loginSuccess', data)
            }).finally(() => {
                this.bindBtnDisabled = false
            })
        } else {
            SocialuniLoginService.phonePasswordLogin(this.loginData.phoneNum, password, this.loginData.authCode).then((data) => {
                console.log(this)
                console.log(1111)
                console.log(this.instance)
                console.log(2222)
                this.instance.$emit('loginSuccess', data)
            }).finally(() => {
                this.bindBtnDisabled = false
            })
        }
    }

    loginAfterHint(msg: string) {
        if (!socialuniAppUserModule.mineUserPhoneNum) {
            msg += '，绑定手机号后才可发布内容'
        }
        //qq小程序下ios系统存在输入框冲突问题，使用了一个输入框，另一个就无法出现
        if (socialuniSystemModule.isIosOrMpQQ) {
            msg += '，如遇无法弹出输入框，请重启应用'
        }
        QingAppUtil.AlertUtil.hint(msg).finally(() => {
            //有手机号才直接返回，没手机号继续提示绑定手机号
            if (socialuniAppUserModule.mineUserPhoneNum) {
                UserPageUtil.goBackOrMine()
            }
            /*if (socialOAuthModule.isThreeAuth) {
              if (this.hasPhoneNum) {
                PageUtil.toOAuthPage()
              } else {
                PageUtil.toPhonePage()
              }
            } else {

            }*/
        })
    }

    goToOAuthPage() {
        /*if (socialOAuthModule.isThreeAuth) {
          if (this.hasPhoneNum) {
            PageUtil.toOAuthPage()
          } else {
            PageUtil.toPhonePage()
          }
        }*/
    }
}
