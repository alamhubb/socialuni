import {AxiosError, InternalAxiosRequestConfig} from "axios";
import {SocialuniPlugin} from "socialuni/src/interface/SocialuniPlugin";
import {socialuniUserModule} from "./store/SocialuniUserModule";
import {socialuniConfigModule} from "socialuni-app-sdk/src/store/SocialuniConfigModule";
import {socialuniTokenModule} from "./store/SocialuniTokenModule";
import ResultRO from "socialuni-api-base/src/model/social/ResultRO";
import ErrorConst from "socialuni-constant/constant/ErrorConst";
import UserService from "./logic/UserService";
import UserMsgUtil from "./util/UserMsgUtil";
import SocialuniAppAPI from "socialuni-app-api/src/api/SocialuniAppAPI";
import JsonUtil from "socialuni-util/src/util/JsonUtil";
import SocialuniAppUtil from "socialuni-native-util/src/util/SocialuniAppUtil";
import MsgUtil from "socialuni-app-sdk/src/util/MsgUtil";
import WebsocketUtil from "socialuni-api-base/src/websocket/WebsocketUtil";
import {App, defineComponent} from "vue";
import {SocialuniOption} from "socialuni/src/interface/socialuniOption";
import {socialuniSystemModule} from "socialuni-util/src/store/SocialuniSystemModule";
import {socialuniPluginsModule} from "socialuni/src/store/SocialuniPluginsModule";
import PlatformModuleLoadUtil from "socialuni-native-util/src/util/PlatformModuleLoadUtil";
import SocialuniViewService from "socialuni/src/interface/SocialuniViewService";
import CommonEventUtil from "uniapp-api/src/util/CommonEventUtil";
import SocialuniUserEventConst from "./constant/SocialuniUserEventConst";
import UserPageUtil from "./util/UserPageUtil";


class SocialuniUserPlugin implements SocialuniPlugin {
    onLaunch() {
        socialuniUserModule.initSocialuniUserModule()
        socialuniConfigModule.getReportTypesAction()
        // CosAPI.getCosPathAPI().then(res => {
        //     socialAppModule.cosHttpPath = res.data
        // })
        // socialAppModule.getHomeSwipersAction()
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

    onResponseErrorInterceptors(error: AxiosError) {
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

        SocialuniAppUtil.UniUtil.hideLoading()
        console.log(error)
        //第一步，先判断 有没有error
        //判断data类型，如果没类型，直接走
        //有内容则为本系统？也不一定，判断拿内容类型
        //然后本系统处理
        const result: ResultRO = error.response.data
        console.log(result)
        if (result) {
            const errorMsg = result.errorMsg
            if (errorMsg) {
                console.log(result.errorCode)
                switch (result.errorCode) {
                    case ErrorConst.not_logged:
                        // 理论上不需要，因为token不会失效，也不会错误
                        // 已知可能，切换环境导致token不同
                        UserService.clearUserInfo()
                        UserMsgUtil.unLoginMessage()
                        break
                    case ErrorConst.banned:
                        // 理论上不需要，因为token不会失效，也不会错误
                        // 已知可能，切换环境导致token不同
                        UserService.clearUserInfo()
                        SocialuniAppUtil.AlertUtil.hint(errorMsg)
                        break
                    case ErrorConst.custom:
                        break
                    default:
                        SocialuniAppUtil.AlertUtil.hint(errorMsg)
                        SocialuniAppAPI.sendErrorLogAPI(error.config.url, SocialuniAppUtil.RouterUtil.getCurrentPageURI(), errorMsg)
                        break
                }
            } else {
                MsgUtil.systemErrorMsg()
                SocialuniAppAPI.sendErrorLogAPI(error.config.url, SocialuniAppUtil.RouterUtil.getCurrentPageURI(), result)
            }
            // 返回接口返回的错误信息
            return result
        }
        MsgUtil.systemErrorMsg()
        SocialuniAppAPI.sendErrorLogAPI(error.config.url, SocialuniAppUtil.RouterUtil.getCurrentPageURI(), JsonUtil.toJson(response))
        return error
    }
}


const socialuniUserPlugin: SocialuniPlugin = new SocialuniUserPlugin()

const SocialuniUser = {
    async install(app: App, socialuniOption: SocialuniOption) {
        socialuniPluginsModule.addPlugin(socialuniUserPlugin)
        CommonEventUtil.on(SocialuniUserEventConst.toLogin, () => {
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
