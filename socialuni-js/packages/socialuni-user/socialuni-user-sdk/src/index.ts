import {AxiosError, InternalAxiosRequestConfig} from "axios";
import {SocialuniPlugin} from "socialuni/src/interface/SocialuniPlugin";
import {socialuniUserModule} from "./store/SocialuniUserModule";
import {socialuniConfigModule} from "socialuni-app-sdk/src/store/SocialuniConfigModule";
import {socialuniTokenModule} from "./store/SocialuniTokenModule";
import ResultRO from "socialuni-api-base/src/model/social/ResultRO";
import ErrorConst from "socialuni-constant/constant/ErrorConst";
import SocialuniUserService from "./logic/SocialuniUserService";
import UserMsgUtil from "./util/UserMsgUtil";
import SocialuniAppAPI from "socialuni-app-api/src/api/SocialuniAppAPI";
import JsonUtil from "qing-util/src/util/JsonUtil";
import QingAppUtil from "qing-compat-js/src/util/QingAppUtil";
import MsgUtil from "socialuni-app-sdk/src/util/MsgUtil";
import WebsocketUtil from "socialuni-api-base/src/websocket/WebsocketUtil";
import {App, defineComponent} from "vue";
import {SocialuniOption} from "socialuni/src/interface/socialuniOption";
import {socialuniSystemModule} from "qing-util/src/store/SocialuniSystemModule";
import {socialuniPluginsModule} from "socialuni/src/store/SocialuniPluginsModule";
import SocialuniUserEventConst from "./constant/SocialuniUserEventConst";
import UserPageUtil from "./util/UserPageUtil";
import CommonEventUtil from "qing-compat-js/src/util/CommonEventUtil";
import {socialuniAppUserModule} from "./store/SocialuniAppUserModule";
import SocialuniUserEventOn from "./event/SocialuniUserEventOn";
import SocialuniUserUtil from "./util/SocialuniUserUtil";


class SocialuniUserPlugin implements SocialuniPlugin {
    onLaunch() {
        //这里未考虑到用户被强制登出的情况，感觉还是应该用watch来判断，用户为空，或者用户id不一致的情况，就触发
        socialuniUserModule.initSocialuniUserModule()
        SocialuniUserEventOn.loginSuccess(() => {
            socialuniUserModule.initSocialuniUserModule()
        })

        //用户被登出时，触发loginOut就好了
        SocialuniUserEventOn.loginOut(() => {
            socialuniUserModule.initSocialuniUserModule()
        })
    }

    onWebsocketInterceptors(config: any) {
        const token = socialuniTokenModule.token
        if (token) {
            config.token = token
        } else {
            config.token = null
        }
    }

    onRequestInterceptors(config: InternalAxiosRequestConfig) {
        const token = socialuniTokenModule.token
        if (token) {
            config.headers.token = token
        } else {
            config.headers.token = null
        }
        config.headers['qqe'] = 'fasdfasdf'
    }

    onResponseErrorInterceptors(error: AxiosError<ResultRO<any>>) {
        // uni
        // config: {transitional: {…}, transformRequest: Array(1), transformResponse: Array(1), timeout: 120000, adapter: ƒ, …}
        // cookies: []
        // data: {code: 607, errorCode: 607, success: false, errorMsg: '不能举报自己的评论', msg: '不能举报自己的评论', …}
        // errMsg: "request:ok"
        // header: {content-type: 'application/json'}
        // headers: {content-type: 'application/json'}
        // status: 607
        // statusCode: 607
        // statusText: "request:ok"

        QingAppUtil.NativeUtil.hideLoading()
        console.log(error)
        //第一步，先判断 有没有error
        //判断data类型，如果没类型，直接走
        //有内容则为本系统？也不一定，判断拿内容类型
        //然后本系统处理
        const result: ResultRO<any> = error?.response?.data
        console.log(result)
        if (result) {
            const errorMsg = result.errorMsg
            if (errorMsg) {
                console.log(result.errorCode)
                switch (result.errorCode) {
                    case ErrorConst.not_logged:
                        // 理论上不需要，因为token不会失效，也不会错误
                        // 已知可能，切换环境导致token不同
                        SocialuniUserService.clearUserInfo()
                        UserMsgUtil.unLoginMessage()
                        break
                    case ErrorConst.banned:
                        // 理论上不需要，因为token不会失效，也不会错误
                        // 已知可能，切换环境导致token不同
                        SocialuniUserService.clearUserInfo()
                        QingAppUtil.AlertUtil.hint(errorMsg)
                        break
                    case ErrorConst.custom:
                        break
                    default:
                        QingAppUtil.AlertUtil.hint(errorMsg)
                        SocialuniAppAPI.sendErrorLogAPI(error.config.url, QingAppUtil.RouterUtil.getCurrentPageURI(), errorMsg)
                        break
                }
            } else {
                MsgUtil.systemErrorMsg()
                SocialuniAppAPI.sendErrorLogAPI(error.config.url, QingAppUtil.RouterUtil.getCurrentPageURI(), result)
            }
            // 返回接口返回的错误信息
            return result
        }
        MsgUtil.systemErrorMsg()
        SocialuniAppAPI.sendErrorLogAPI(error.config.url, QingAppUtil.RouterUtil.getCurrentPageURI(), JsonUtil.toJson(error?.response))
        return error
    }
}


const socialuniUserPlugin: SocialuniPlugin = new SocialuniUserPlugin()

const SocialuniUser = {
    async install(app: App, socialuniOption: SocialuniOption) {
        app.config.globalProperties.$userUtil = SocialuniUserUtil;
        app.config.globalProperties.$isMine = SocialuniUserUtil.isMine;

        socialuniPluginsModule.addPlugin(socialuniUserPlugin)

        SocialuniUserEventOn.toLogin(() => {
            UserPageUtil.toMinePage()
        })

        if (socialuniSystemModule.isUniApp) {
            const shareComponent = defineComponent({
                created() {
                }
            })
            app.mixin(shareComponent)
        }
    }
}

export default SocialuniUser
