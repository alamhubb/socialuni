import { Action, Module, VuexModule } from 'vuex-class-modules'
import HomeSwiperVO from '../model/HomeSwiperVO'
import ReportAPI from '../api/ReportAPI'
import QingchiAPI from '../api/QingchiAPI'
import {
  socialAppModule,
  socialChatModule, socialCircleModule,
  socialConfigModule,
  socialLocationModule,
  socialNotifyModule,
  socialTagModule
} from './index'
import AppConfigAPI from '../api/AppConfigAPI'
import AppInitDataRO from '../model/common/AppInitDataRO'
import PlatformUtils from '@/socialuni/utils/PlatformUtils'
import TokenUtil from '@/socialuni/utils/TokenUtil'

@Module({ generateMutationSetters: true })
export default class SocialAppModule extends VuexModule {
  reportTypes: string[] = []
  homeSwipers: HomeSwiperVO[] = []
  onlineUsersCount = 0

  //app启动的方法
  @Action
  async appLunchAction () {
    //校验更新
    PlatformUtils.checkUpdate()
    // WebsocketUtil.websocketConnect(false)
    socialTagModule.getHotTagsAction()
    socialCircleModule.getHotCirclesAction()
    socialTagModule.getHotTagTypesAction()
    // socialCircleModule.getCircleTypesAction()
    socialLocationModule.getHotDistrictsAction()
    socialAppModule.getReportTypesAction()
    socialAppModule.getAppConfigAction()
    socialAppModule.getHomeSwipersAction()
    socialChatModule.getChatsAction()

    //如果有token获取
    if (TokenUtil.hasToken()) {
      //查询通知列表
      socialNotifyModule.queryNotifiesAction()
    }
    // 初始化数据看一下这些请求是否可以合并 登录之后也要链接websocket
    // appModule.initGlobalDataReadyAPI()
    // 测试时使用，生产时在talk也ready后查询
    // appModule.initGlobalDataReadyAPI()
    // 不为app平台在这里设置platform否则在systemInfo中设置
  }
  // actions
  @Action
  getReportTypesAction () {
    ReportAPI.queryReportTypesAPI().then(res => {
      this.reportTypes = res.data
    })
  }

  // actions
  @Action
  getHomeSwipersAction () {
    QingchiAPI.queryHomeSwipersAPI().then(res => {
      this.homeSwipers = res.data
    })
  }

  @Action
  getAppConfigAction () {
    AppConfigAPI.getAppConfigAPI().then(res => {
      const appInitData: AppInitDataRO = res.data
      socialConfigModule.appConfig = appInitData.appConfig
      this.onlineUsersCount = appInitData.onlineUsersCount
      this.reportTypes = appInitData.reportTypes
    })
  }
}
