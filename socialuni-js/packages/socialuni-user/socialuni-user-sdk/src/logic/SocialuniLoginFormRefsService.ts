import SocialuniViewService from "socialuni/src/interface/SocialuniViewService";
import {ComponentInternalInstance, nextTick} from "vue";
import SocialuniLoginFormDataVO from "../model/SocialuniLoginFormDataVO";
import PhoneAPI from "socialuni-user-api/src/api/PhoneAPI";
import QingAppUtil from "qingjs/src/util/QingAppUtil";
import SocialuniLoginFormService from "./SocialuniLoginFormService";
import CommonUtil from "qing-util/src/util/CommonUtil";

interface SocialuniLoginViewServiceRefs {
    authCode: HTMLInputElement
    password: HTMLInputElement
}

export default class SocialuniLoginFormRefsService extends SocialuniLoginFormService {
    loginData = new SocialuniLoginFormDataVO()

    phoneNumFirstBlur = false

    // if (this.phoneNumHasError) {
    // return false
// } else

// 同意协议
    contractChecked = true

    contractCheckedChange() {
        this.contractChecked = !this.contractChecked
    }

    get loginRules() {
        const rules = {
            phoneNum: [{required: true, trigger: 'blur', message: '请输入正确的手机号', min: 11, max: 11}],
            password: [
                {
                    required: true,
                    validator: (rule, value, callback) => {
                        const error = this.loginData.passwordHasError
                        if (error) {
                            callback(new Error(error))
                        }
                        callback()
                    },
                    trigger: 'blur'
                },
            ]
        }
        if (!this.loginData.phoneNumRegistered) {
            return {
                ...rules,
                authCode: [{required: true, trigger: 'blur', message: '请输入正确的验证码', min: 4, max: 4}]
            }
        }
        return rules
    }



    initService(instance: ComponentInternalInstance, params: any = {}) {
        super.initService(instance, params);

    }

    phoneNumberOnInput() {
        if (!this.loginData.phoneNumHasError) {
            PhoneAPI.checkRegistry(this.loginData.phoneNum).then(res => {
                this.loginData.phoneNumRegistered = res.data
                nextTick(() => {
                    setTimeout(() => {
                        // this.$refs.loginForm.clearValidate()
                        setTimeout(() => {
                            this.$refs.password.focus()
                            // this.$refs.loginForm.clearValidate()
                            setTimeout(() => {
                                // this.$refs.loginForm.clearValidate()
                            }, 100)
                        }, 500)
                    }, 50)

                })
            })
        }
    }


    sendCodeClick() {
        this.loginData.checkPhoneNum()
        if (this.loginData.countDownInner) {
            return QingAppUtil.ToastUtil.error('验证码发送频繁，请等待')
        }

        this.loginData.authCodeClear()
        this.$refs.authCode.focus()

        // 如果怕太频繁，就显示相同手机号每天只能发送几次，一小时内只能5次
        PhoneAPI.sendAuthCodeAPI(this.loginData.phoneNum).then(() => {
            // 提示验证码发送成功
            QingAppUtil.ToastUtil.success('验证码发送成功')
        })
    }



    phoneNumInputFocus() {
        this.phoneNumFocus = true
        CommonUtil.delayTime(100).then(() => {
            this.phoneNumFocus = true
        })
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

    passwordInputFocus() {
        this.passwordFocus = true
        CommonUtil.delayTime(100).then(() => {
            this.passwordFocus = true
        })
    }

    authCodeInputBlur() {
        this.authCodeFocus = false
    }

    passwordInputBlur() {
        this.passwordFocus = false
    }


    phoneNumClear() {
        this.phoneNum = null
        this.phoneNumInputFocus()
    }

    passwordClear() {
        this.password = null
        this.passwordInputFocus()
    }

    authCodeClear() {
        this.authCode = null
        this.authCodeInputFocus()
    }
}
