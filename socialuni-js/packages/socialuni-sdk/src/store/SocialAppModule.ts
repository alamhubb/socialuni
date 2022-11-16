import {Pinia, Store} from "pinia-class-component"
import HomeSwiperVO from "socialuni-api/src/model/HomeSwiperVO";
import PlatformUtils from "socialuni-sdk/src/utils/PlatformUtils";
import SocialuniAppAPI from "socialuni-api/src/api/socialuni/SocialuniAppAPI";
import {socialAppModule, socialCircleModule, socialLocationModule, socialTagModule, socialTalkModule} from "./store";

@Store
export default class SocialAppModule extends Pinia {
    homeSwipers: HomeSwiperVO[] = []
    onlineUsersCount = 0

    //app启动的方法

    async appLunchAction() {
        console.log(1111)
        //校验更新
        PlatformUtils.checkUpdate()
        try {
            // WebsocketUtil.websocketConnect(false)

        } finally {

        }


        // 初始化数据看一下这些请求是否可以合并 登录之后也要链接websocket
        // appModule.initGlobalDataReadyAPI()
        // 测试时使用，生产时在talk也ready后查询
        // appModule.initGlobalDataReadyAPI()
        // 不为app平台在这里设置platform否则在systemInfo中设置
    }

    // actions

    getHomeSwipersAction() {
        SocialuniAppAPI.queryHomeSwipersAPI().then(res => {
            this.homeSwipers = res.data
        })
    }
}
