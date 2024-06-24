import SocialuniViewService from "socialuni/src/interface/SocialuniViewService";
import {ComponentInternalInstance, nextTick} from "vue";
import SocialuniLoginFormDataVO from "../model/SocialuniLoginFormDataVO";
import PhoneAPI from "socialuni-user-api/src/api/PhoneAPI";
import QingAppUtil from "qing-compat-js/src/util/QingAppUtil";
import CommonUtil from "qing-util/src/util/CommonUtil";

interface SocialuniLoginViewServiceRefs {
    authCode: HTMLInputElement
    password: HTMLInputElement
}

export default class SocialuniLoginFormService extends SocialuniViewService<SocialuniLoginViewServiceRefs> {
    loginData = new SocialuniLoginFormDataVO()

    // 同意协议
    contractChecked = false

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


    initService(instance: Vue, params: any = {}) {
        super.initService(instance, params);
    }

    async phoneNumberOnInput() {
        if (!this.loginData.phoneNumHasError) {
            console.log(234234)
            const res = await PhoneAPI.checkRegistry(this.loginData.phoneNum)
            this.loginData.phoneNumRegistered = res.data
        }
    }


    sendCodeClick() {
        this.loginData.checkSendAuthCode()
        this.loginData.authCodeClear()
        this.loginData.computedCountDown()

        // 如果怕太频繁，就显示相同手机号每天只能发送几次，一小时内只能5次
        PhoneAPI.sendAuthCodeAPI(this.loginData.phoneNum).then(() => {
            // 提示验证码发送成功
            QingAppUtil.ToastUtil.success('验证码发送成功')
        })
    }
}
