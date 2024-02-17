import SocialPhoneNumLoginQO from "socialuni-api-base/src/model/phone/SocialPhoneNumLoginQO";
import NumberUtil from "qing-util/src/util/NumberUtil";
import PhoneAPI from "socialuni-user-api/src/api/PhoneAPI";
import QingAppUtil from "qingjs/src/util/QingAppUtil";
import SocialuniLoginService from "./SocialuniLoginService";
import SocialuniViewService from "socialuni/src/interface/SocialuniViewService";
import {ComponentInternalInstance, nextTick} from "vue";
import {ElForm} from "element-plus";
import {socialuniConfigModule} from "socialuni-app-sdk/src/store/SocialuniConfigModule";
import PasswordUtil from "../util/PasswordUtil";
import {Emit, Model, Prop, Watch} from "vue-facing-decorator";
import PhoneNumFormData from "socialuni-user-view-uni/src/views/login/PhoneNumFormData";
import CommonUtil from "qing-util/src/util/CommonUtil";


interface SocialuniLoginViewServiceRefs {
    loginForm: ElForm
    authCode: HTMLInputElement
    password: HTMLInputElement
}

export default class SocialuniLoginDataService extends SocialuniViewService<SocialuniLoginViewServiceRefs> {

    countDown = 0

    authCodeFocus = false
    phoneNumFocus = false

    get phoneNumIsError() {
        //有值，错误，才算错误
        return this.loginData.phoneNum && PhoneNumFormData.phoneNumberError(this.loginData.phoneNum)
    }

    get authCodeIsError() {
        //手机号正确，验证码错误才为错误
        return !PhoneNumFormData.phoneNumberError(this.loginData.phoneNum) && PhoneNumFormData.authCodeError(this.loginData.authCode)
    }

    phoneNumInputFocus() {
        this.phoneNumFocus = true
    }

    phoneNumInputBlur() {
        this.phoneNumFocus = false
    }

    authCodeInputFocus() {
        this.authCodeFocus = true
        CommonUtil.delayTime(100).then(() => {
            this.authCodeFocus = true
        })
    }

    authCodeInputBlur() {
        this.authCodeFocus = false
    }

    get sendAuthCodeBtnDisabled() {
        return PhoneNumFormData.phoneNumberError(this.loginData.phoneNum) || Boolean(this.countDown)
    }

    resetAuthCodeCountDown(){
        this.countDown = 0
    }

    phoneNumClear() {
        console.log(this.loginData.phoneNum)
        this.loginData.phoneNum = ''
        console.log(this.loginData.phoneNum)
        this.input()
        CommonUtil.delayTime(100).then(() => {
            this.phoneNumInputFocus()
        })
    }

    authCodeClear() {
        this.loginData.authCode = ''
        this.input()
        this.authCodeInputFocus()
    }

    sendCodeClick() {
        if (PhoneNumFormData.phoneNumberError(this.loginData.phoneNum)) {
            return QingAppUtil.ToastUtil.error('请输入正确的手机号')
        }
        if (this.countDown) {
            return QingAppUtil.ToastUtil.error('验证码发送频繁，请等待')
        }

        this.loginUser.authCode = ''

        this.authCodeInputFocus()

        this.countDown++
        const Timer = setInterval(() => {
            if (this.countDown === this.authCodeInterval) {
                clearInterval(Timer)
                this.countDown = 0
                return
            }
            this.countDown++
        }, 1000)
        // 如果怕太频繁，就显示相同手机号每天只能发送几次，一小时内只能5次
        PhoneAPI.sendAuthCodeAPI(this.loginData.phoneNum).then(() => {
            // 提示验证码发送成功
            QingAppUtil.ToastUtil.success('验证码发送成功')
        })
    }
}
