import CommonUtil from "qing-util/src/util/CommonUtil";
import NumberUtil from "qing-util/src/util/NumberUtil";
import {socialuniConfigModule} from "socialuni-app-sdk/src/store/SocialuniConfigModule";
import QingAppUtil from "qingjs/src/util/QingAppUtil";

export default class SocialuniLoginFormDataVO {
    constructor(phoneNum?: string) {
        this.phoneNum = phoneNum;
    }

    phoneNum: string = null
    //是否已经注册
    phoneNumRegistered: boolean = false
    password: string = null
    authCode: string = null

    countDownInner: number = 0


    get phoneNumHasError() {
        //有值，错误，才算错误
        return !this.phoneNum || this.phoneNum.length !== 11 || NumberUtil.containNoNumber(this.phoneNum)
    }

    get passwordHasError() {
        if (!this.password) {
            return '密码不能为空'
        } else if (this.password.length < 8) {
            return '密码长度不能小于8个字符'
        } else if (this.password.length > 16) {
            return '密码长度不能大于20个字符'
        } else if (NumberUtil.isAllNumber(this.password)) {
            return '密码必须包含字母'
        } else if (NumberUtil.noHasNumber(this.password)) {
            return '密码必须包含数字'
        }
        return false
    }

    get authCodeHasError() {
        return !this.phoneNumHasError && !this.passwordHasError && (!this.authCode || this.authCode.length !== 4 || NumberUtil.containNoNumber(this.authCode))
    }

    get sendAuthCodeBtnDisabled() {
        return this.phoneNumHasError || !!this.passwordHasError || this.countDownInner
    }


    get authCodeInterval() {
        return socialuniConfigModule.appMoreConfig.authCodeInterval || 30
    }

    get countDown() {
        return this.authCodeInterval - this.countDownInner
    }

    computedCountDown() {
        this.countDownInner++
        const Timer = setInterval(() => {
            if (this.countDownInner === this.authCodeInterval) {
                clearInterval(Timer)
                this.resetAuthCodeCountDown()
                return
            }
            this.countDownInner++
        }, 1000)
    }


    checkPhoneNum() {
        // 再次校验
        if (this.phoneNumHasError) {
            return QingAppUtil.ToastUtil.error('请输入正确的手机号')
        }
    }

    checkPassword() {
        if (this.passwordHasError) {
            return QingAppUtil.ToastUtil.error(this.passwordHasError)
        }
    }

    checkAuthCode() {
        if (!this.phoneNumRegistered && this.authCodeHasError) {
            return QingAppUtil.ToastUtil.error('请输入正确的验证码')
        }
    }

    checkSendAuthCode() {
        this.checkPhoneNum()
        if (this.countDownInner) {
            return QingAppUtil.ToastUtil.throwError('验证码发送频繁，请等待')
        }
    }

    resetAuthCodeCountDown() {
        this.countDownInner = 0
    }


    phoneNumClear() {
        this.phoneNum = null
    }

    passwordClear() {
        this.password = null
    }

    authCodeClear() {
        this.authCode = null
    }
}