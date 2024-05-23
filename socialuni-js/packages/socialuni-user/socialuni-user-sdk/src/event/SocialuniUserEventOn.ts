import SocialuniUserEventConst from "../constant/SocialuniUserEventConst";
import CommonEventUtil from "qingjs/src/util/CommonEventUtil";

export default class SocialuniUserEventOn {
    //每个系统，都应该有一个监听

    static toLogin(hook: () => {}) {
        CommonEventUtil.on(SocialuniUserEventConst.toLogin, hook)
    }


    static loginSuccess(hook: () => {}) {
        CommonEventUtil.on(SocialuniUserEventConst.loginSucces, hook)
    }


    static loginOut(hook: () => {}) {
        CommonEventUtil.on(SocialuniUserEventConst.loginOut, hook)
    }
}