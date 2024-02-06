import CommonUtil from "qing-util/src/util/CommonUtil";
import NumberUtil from "qing-util/src/util/NumberUtil";
import {socialuniConfigModule} from "socialuni-app-sdk/src/store/SocialuniConfigModule";
import QingAppUtil from "qingjs/src/util/QingAppUtil";

export default class SocialuniLoginDataVO {
    constructor(phoneNum?: string) {
        this.phoneNum = phoneNum;
    }

    phoneNum: string = null
    authCode: string = null
    countDownInner: number = null

    authCodeFocus = false
    phoneNumFocus = false
    phoneNumFirstBlur = false


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

    get phoneNumError() {
        //有值，错误，才算错误
        return this.phoneNumFirstBlur && (!this.phoneNum || this.phoneNum.length !== 11 || NumberUtil.containNoNumber(this.phoneNum))
    }


    sendAuthCodeCheck() {
        if (this.phoneNumError) {
            return QingAppUtil.ToastUtil.throwError('请输入正确的手机号')
        }
        if (this.countDown) {
            return QingAppUtil.ToastUtil.throwError('验证码发送频繁，请等待')
        }
    }

    get authCodeError() {
        return this.phoneNumFirstBlur && !this.phoneNumError && (!this.authCode || this.authCode.length !== 4 || NumberUtil.containNoNumber(this.authCode))
    }

    get sendAuthCodeBtnDisabled() {
        return this.phoneNumError || !!this.countDownInner
    }

    phoneNumInputFocus() {
        this.phoneNumFocus = true
        CommonUtil.delayTime(100).then(() => {
            this.phoneNumFocus = true
        })
    }

    phoneNumInputBlur() {
        this.phoneNumFocus = false
        this.phoneNumFirstBlur = true
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

    resetAuthCodeCountDown() {
        this.countDownInner = 0
    }

    phoneNumClear() {
        this.phoneNum = null
        this.phoneNumInputFocus()
    }

    authCodeClear() {
        this.authCode = null
        this.authCodeInputFocus()
    }
}