import SocialPhoneNumLoginQO from "socialuni-api-base/src/model/phone/SocialPhoneNumLoginQO";
import NumberUtil from "socialuni-util/src/util/NumberUtil";
import PhoneAPI from "socialuni-user-api/src/api/PhoneAPI";
import SocialuniAppUtil from "socialuni-native-util/src/util/SocialuniAppUtil";
import {socialuniPluginsModule} from "socialuni/src/store/SocialuniPluginsModule";
import SocialuniLoginService from "./SocialuniLoginService";
import SocialuniViewService from "socialuni/src/interface/SocialuniViewService";

interface SocialuniLoginViewServiceRefs {
    authCode: HTMLInputElement
}

export default class SocialuniLoginViewService extends SocialuniViewService<SocialuniLoginViewServiceRefs> {


    loginUser: SocialPhoneNumLoginQO = new SocialPhoneNumLoginQO()

    authCodeInterval = 30

    // 同意协议
    contractChecked = true

    loginRules = {
        phoneNum: [{required: true, trigger: 'blur', message: '请输入正确的手机号', min: 11, max: 11}],
        authCode: [{required: true, trigger: 'blur', message: '请输入正确的验证码', min: 4, max: 4}]
    }

    countDown = 0

    bindBtnDisabled = false

    get sendAuthCodeBtnDisabled() {
        return !this.phoneNumberRight || Boolean(this.countDown)
    }

    get authCodeRight() {
        return this.loginUser.authCode && this.loginUser.authCode.length === 4 && NumberUtil.isNumber(this.loginUser.authCode)
    }

    get phoneNumberRight() {
        return this.loginUser.phoneNum && this.loginUser.phoneNum.length === 11 && NumberUtil.isNumber(this.loginUser.phoneNum)
    }

    // 手机号登陆和手机号绑定
    handleLogin() {
        if (this.bindBtnDisabled) {
            return SocialuniAppUtil.ToastUtil.warning('正在登陆中，请勿重复点击')
        }
        // 再次校验
        if (!this.phoneNumberRight) {
            return SocialuniAppUtil.ToastUtil.error('请输入正确的手机号')
        }
        if (!this.authCodeRight) {
            return SocialuniAppUtil.ToastUtil.error('请输入正确的验证码')
        }
        if (!this.contractChecked) {
            return SocialuniAppUtil.ToastUtil.warning('请仔细阅读用户协议、隐私政策等内容后勾选同意')
        }
        this.bindBtnDisabled = true
        SocialuniLoginService.phoneLogin(this.loginUser.phoneNum, this.loginUser.authCode).then(() => {
            socialuniPluginsModule.router.push({path: '/'})
        }).finally(() => {
            this.bindBtnDisabled = false
        })
    }


    sendCodeClick() {
        if (!this.loginUser.phoneNum) {
            return SocialuniAppUtil.ToastUtil.error('请输入正确的手机号')
        }
        if (this.countDown) {
            return SocialuniAppUtil.ToastUtil.error('验证码发送频繁，请等待')
        }

        this.loginUser.authCode = ''
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
