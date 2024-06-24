import SocialuniUserEventConst from "../constant/SocialuniUserEventConst";
import CommonEventUtil from "qing-compat-js/src/util/CommonEventUtil";

export default class SocialuniUserEventEmit {
    //每个系统，都应该有一个监听

    static toLogin() {
        CommonEventUtil.emit(SocialuniUserEventConst.toLogin)
    }


    static loginSuccess() {
        CommonEventUtil.emit(SocialuniUserEventConst.loginSucces)
    }


    static loginOut() {
        CommonEventUtil.emit(SocialuniUserEventConst.loginOut)
    }
}
