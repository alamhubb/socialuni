import SocialuniUserEventConst from "../constant/SocialuniUserEventConst";
import CommonEventUtil from "qing-compat-js/src/util/CommonEventUtil";

export default class SocialuniUserEventOn {
    //每个系统，都应该有一个监听

    static toLogin(hook: () => void) {
        CommonEventUtil.on(SocialuniUserEventConst.toLogin, hook)
    }


    static loginSuccess(hook: () => void) {
        CommonEventUtil.on(SocialuniUserEventConst.loginSucces, hook)
    }


    static loginOut(hook: () => void) {
        CommonEventUtil.on(SocialuniUserEventConst.loginOut, hook)
    }

    static initOrUserChange(hook: () => void) {
        CommonEventUtil.on(SocialuniUserEventConst.initOrUserChange, hook)
    }
}
