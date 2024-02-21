import SocialuniViewService from "socialuni/src/interface/SocialuniViewService";
import {ComponentInternalInstance, nextTick} from "vue";
import SocialuniLoginFormDataVO from "../model/SocialuniLoginFormDataVO";
import PhoneAPI from "socialuni-user-api/src/api/PhoneAPI";
import QingAppUtil from "qingjs/src/util/QingAppUtil";
import CommonUtil from "qing-util/src/util/CommonUtil";
import SocialuniLoginFormService from "./SocialuniLoginFormService";

interface SocialuniLoginViewServiceRefs {
    authCode: HTMLInputElement
    password: HTMLInputElement
}

export default class SocialuniLoginFormServiceUni extends SocialuniLoginFormService {
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

    passwordFocus = false
    authCodeFocus = false
    phoneNumFocus = false

    phoneNumClear() {
        this.loginData.passwordClear()
        this.phoneNumInputFocus()
    }

    passwordClear() {
        this.loginData.passwordClear()
        this.passwordInputFocus()
    }

    authCodeClear() {
        this.loginData.authCodeClear()
        this.authCodeInputFocus()
    }
}
