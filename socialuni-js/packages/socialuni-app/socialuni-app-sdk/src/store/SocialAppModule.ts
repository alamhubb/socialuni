import HomeSwiperVO from "socialuni-api-base/src/model/HomeSwiperVO";
import {reactive} from "vue";
import SocialuniAppAPI from "socialuni-app-api/src/api/SocialuniAppAPI";


class SocialAppModule {
    homeSwipers: HomeSwiperVO[] = []
    private _onlineUsersCount = 0
    private _onlineUsers: string[] = []
    cosHttpPath: string = null

    get onlineUsersCount(): number {
        return this._onlineUsersCount;
    }

    setOnlineUserCount(onlineUsersCount: onlineUsersCount) {
        this._onlineUsersCount = onlineUsersCount
    }

    get onlineUsers(): string[] {
        return this._onlineUsers;
    }

    setOnlineUsers(value: string[]) {
        this._onlineUsers = value;
    }

//app启动的方法

    async appLunchAction() {

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

export const socialAppModule: SocialAppModule = reactive(new SocialAppModule())
