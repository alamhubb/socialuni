import {Pinia, Store} from "pinia-class-component"
import HomeSwiperVO from "socialuni-api/src/model/HomeSwiperVO";
import {socialUserModule} from "socialuni-user/src/store/store";
import {socialChatModule} from "socialuni-im/src/store/store";
import PlatformUtils from "socialuni-common/src/utils/PlatformUtils";
import UserService from "socialuni-user/src/service/UserService";
import {
    socialAppModule,
    socialCircleModule, socialConfigModule,
    socialLocationModule,
    socialTagModule,
    socialTalkModule
} from "./store";
import SocialuniTokenUtil from "socialuni-common/src/utils/SocialuniTokenUtil";
import ReportAPI from "socialuni-api/src/api/socialuni/ReportAPI";
import SocialuniAppAPI from "socialuni-api/src/api/socialuni/SocialuniAppAPI";
import AppInitDataRO from "socialuni-api/src/model/common/AppInitDataRO";

@Store
export default class SocialAppModule extends Pinia {
    reportTypes: string[] = []
    homeSwipers: HomeSwiperVO[] = []
    onlineUsersCount = 0

    //app启动的方法


    async appLunchAction() {
        console.log(1111)
        //校验更新
        PlatformUtils.checkUpdate()
        try {
            console.log(1111)
            //无论如何都要获取当前用户信息
            if (socialUserModule.hasToken) {
                console.log(1111)
                UserService.getAppLunchDataByHasUser()
            }
            console.log(1111)
            // WebsocketUtil.websocketConnect(false)
            socialTalkModule.getTalkTabs()
            socialTagModule.getHotTagsAction()
            socialTagModule.getHotTagTypesAction()
            socialCircleModule.getHotCirclesAction()
            socialCircleModule.getHotCircleTypesAction()
            socialLocationModule.getHotDistrictsAction()
            socialAppModule.getReportTypesAction()
            socialAppModule.getAppConfigAction()
            socialAppModule.getHomeSwipersAction()
        } finally {

        }


        // 初始化数据看一下这些请求是否可以合并 登录之后也要链接websocket
        // appModule.initGlobalDataReadyAPI()
        // 测试时使用，生产时在talk也ready后查询
        // appModule.initGlobalDataReadyAPI()
        // 不为app平台在这里设置platform否则在systemInfo中设置
    }

    // actions

    getReportTypesAction() {
        ReportAPI.queryReportTypesAPI().then(res => {
            this.reportTypes = res.data
        })
    }

    // actions

    getHomeSwipersAction() {
        SocialuniAppAPI.queryHomeSwipersAPI().then(res => {
            this.homeSwipers = res.data
        })
    }


    getAppConfigAction() {
        SocialuniAppAPI.getAppConfigAPI().then(res => {
            const appInitData: AppInitDataRO = res.data
            socialConfigModule.appConfig = appInitData.appConfig
            socialConfigModule.appMoreConfig = appInitData.appMoreConfig
            this.onlineUsersCount = appInitData.onlineUsersCount
            this.reportTypes = appInitData.reportTypes
        })
    }
}
