import SocialPhoneNumLoginQO from "socialuni-api-base/src/model/phone/SocialPhoneNumLoginQO";
import NumberUtil from "socialuni-util/src/util/NumberUtil";
import PhoneAPI from "socialuni-user-api/src/api/PhoneAPI";
import SocialuniAppUtil from "socialuni-native-util/src/util/SocialuniAppUtil";
import SocialuniLoginService from "./SocialuniLoginService";
import SocialuniViewService from "socialuni/src/interface/SocialuniViewService";
import {ComponentInternalInstance, nextTick} from "vue";
import {ElForm} from "element-plus";
import {socialuniConfigModule} from "socialuni-app-sdk/src/store/SocialuniConfigModule";
import PasswordUtil from "../util/PasswordUtil";


interface SocialuniLoginViewServiceRefs {
    loginForm: ElForm
    authCode: HTMLInputElement
    password: HTMLInputElement
}

export default class SocialuniLoginViewService extends SocialuniViewService<SocialuniLoginViewServiceRefs> {


    loginUser: SocialPhoneNumLoginQO = new SocialPhoneNumLoginQO()

    authCodeInterval = 30

    // 同意协议
    contractChecked = true

    //是否已经注册
    hasPassword = false

    get loginBtnText() {
        if (this.hasPassword) {
            return '登 录'
        }
        return '注 册 并 登 录'
    }

    get loginRules() {
        const rules = {
            phoneNum: [{required: true, trigger: 'blur', message: '请输入正确的手机号', min: 11, max: 11}],
            password: [
                {
                    required: true,
                    validator: (rule, value, callback) => {
                        const error = this.passwordHasError(value)
                        if (error) {
                            callback(new Error(error))
                        }
                        callback()
                    },
                    trigger: 'blur'
                },
            ]
        }
        if (this.hasPassword) {
            return {
                ...rules,
                authCode: [{required: true, trigger: 'blur', message: '请输入正确的验证码', min: 4, max: 4}]
            }
        }
        return rules
    }


    countDown = 0

    bindBtnDisabled = false

    initService(instance: ComponentInternalInstance, params: any = {}) {
        super.initService(instance, params);

    }

    phoneNumberOnInput() {
        if (this.phoneNumberRight) {
            PhoneAPI.checkRegistry(this.loginUser.phoneNum).then(res => {
                this.hasPassword = res.data
                nextTick(() => {
                    setTimeout(() => {
                        this.$refs.loginForm.clearValidate()
                        setTimeout(() => {
                            this.$refs.password.focus()
                            this.$refs.loginForm.clearValidate()
                            setTimeout(() => {
                                this.$refs.loginForm.clearValidate()
                            }, 100)
                        }, 500)
                    }, 50)

                })
            })
        }
    }

    passwordHasError(value: string) {
        if (!value) {
            return '密码不能为空'
        } else if (value.length < 8) {
            return '密码长度不能小于8个字符'
        } else if (value.length > 16) {
            return '密码长度不能大于20个字符'
        } else if (NumberUtil.isAllNumber(value)) {
            return '密码必须包含字母'
        } else if (NumberUtil.noHasNumber(value)) {
            return '密码必须包含数字'
        }
        return false
    }

    get sendAuthCodeBtnDisabled() {
        return !this.phoneNumberRight || !!this.passwordError || Boolean(this.countDown)
    }

    get authCodeRight() {
        return this.loginUser.authCode && this.loginUser.authCode.length === 4 && NumberUtil.isNumber(this.loginUser.authCode)
    }

    get passwordError() {
        return this.passwordHasError(this.loginUser.password)
    }

    get phoneNumberRight() {
        return this.loginUser.phoneNum && this.loginUser.phoneNum.length === 11 && NumberUtil.isNumber(this.loginUser.phoneNum)
    }

    // 手机号登陆和手机号绑定
    async handleLogin() {
        if (this.bindBtnDisabled) {
            return SocialuniAppUtil.ToastUtil.warning('正在登陆中，请勿重复点击')
        }
        // 再次校验
        if (!this.phoneNumberRight) {
            return SocialuniAppUtil.ToastUtil.error('请输入正确的手机号')
        }
        const passwordError = this.passwordError
        if (!!passwordError) {
            return SocialuniAppUtil.ToastUtil.error(passwordError)
        }
        //未注册
        if (!this.hasPassword && !this.authCodeRight) {
            return SocialuniAppUtil.ToastUtil.error('请输入正确的验证码')
        }
        if (!this.contractChecked) {
            return SocialuniAppUtil.ToastUtil.warning('请仔细阅读用户协议、隐私政策等内容后勾选同意')
        }
        this.bindBtnDisabled = true

        const password = await PasswordUtil.rsaEncode(socialuniConfigModule.allConfig.publicKey, this.loginUser.password)

        if (this.hasPassword) {
            SocialuniLoginService.passwordLogin(this.loginUser.phoneNum, password).then(() => {
                this.instance.emit('loginSuccess')
            }).finally(() => {
                this.bindBtnDisabled = false
            })
        } else {
            SocialuniLoginService.phonePasswordLogin(this.loginUser.phoneNum, password, this.loginUser.authCode).then(() => {
                this.instance.emit('loginSuccess')
            }).finally(() => {
                this.bindBtnDisabled = false
            })
        }
    }


    sendCodeClick() {
        console.log(123)
        if (!this.loginUser.phoneNum) {
            return SocialuniAppUtil.ToastUtil.error('请输入正确的手机号')
        }
        if (this.countDown) {
            return SocialuniAppUtil.ToastUtil.error('验证码发送频繁，请等待')
        }

        this.loginUser.authCode = ''
        console.log(this.$refs)
        console.log(this.$refs.authCode)
        this.$refs.authCode.focus()

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
        PhoneAPI.sendAuthCodeAPI(this.loginUser.phoneNum).then(() => {
            // 提示验证码发送成功
            SocialuniAppUtil.ToastUtil.success('验证码发送成功')
        })
    }
}
