import { Action, Module, VuexModule } from 'vuex-class-modules'
import ProviderType from '../const/ProviderType'
import PlatformType from '../const/PlatformType'
import PlatformUtils from '../utils/PlatformUtils'
import TokenUtil from '../utils/TokenUtil'
import { socialAppModule, socialChatModule, socialLocationModule, socialNotifyModule, socialTagModule } from './index'
import UniSystemType from '../const/UniSystemType'
import UniPlatformType from '../const/UniPlatformType'
import GetSystemInfoResult = UniApp.GetSystemInfoResult;

//和终端相关的信息
@Module({ generateMutationSetters: true })
export default class SocialSystemModule extends VuexModule {
  isIos = false
  isAndroid = false

  //初始化以后第一步加载
  // 条件编译属性
  isApp = false
  //初始化以后第一步加载
  // 条件编译属性
  isMp = false
  isH5 = false

  isMpQQ = false
  isMpWx = false


  //推送使用，用于app端记录给谁推送，区分用户
  clientid = ''
  // 条件编译属性
  // ios android h5,默认安卓
  system = ''
  systemInfo: GetSystemInfoResult = null
  platform = ''
  // 小程序类型
  mpPlatform = ''


  // 条件编译属性
  // ios android h5,默认安卓
  // 登录平台
  screenHeight = 0
  windowHeight = 0
  //减去状态栏和导航栏的高度
  contentHeight = 0
  statusBarHeight = 0
  navBarHeight = 44
  titleHeight = 0
  appVersion = 0

  //ios和qq小程序禁止虚拟支付，统一判断
  get isIosAndMpQQ () {
    return this.isIos && this.isMpQQ
  }

  //app启动的方法
  @Action
  async appLunchAction () {
    //校验更新
    PlatformUtils.checkUpdate()
    // WebsocketUtil.websocketConnect(false)
    // appModule.initGlobalDataLoadAPI()
    // AppService.getHomeLoadAfterData()
    socialTagModule.getHotTagsAction()
    socialTagModule.getHotTagTypesAction()
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


  @Action
  // 动态页展示广告,设置一些默认值，在这里设置还是去使用的地方设置
  getSystemInfo () {
    //设置平台
    // #ifdef APP-PLUS
    this.isApp = true
    this.platform = UniPlatformType.app
    //获取app版本号
    plus.runtime.getProperty(plus.runtime.appid, (widgetInfo) => {
      this.appVersion = Number(widgetInfo.version.split('.').join(''))
    })
    const phoneInfo = plus.push.getClientInfo()
    this.clientid = phoneInfo.clientid
    // #endif

    // #ifdef MP
    this.isMp = true
    this.platform = UniPlatformType.mp
    // #ifdef MP-WEIXIN
    this.isMpWx = true
    this.mpPlatform = ProviderType.wx
    // #endif
    // #ifdef MP-QQ
    this.isMpQQ = true
    this.mpPlatform = ProviderType.qq
    // #endif
    // #endif

    // #ifdef H5
    this.isH5 = true
    this.platform = PlatformType.h5
    // #endif

    // 判断是否为ios平台
    const systemInfo: GetSystemInfoResult = uni.getSystemInfoSync()
    this.systemInfo = systemInfo
    //获取是否苹果平台
    const model: string = systemInfo.model
    //小程序开发工具时会为 devtools
    const platform: string = systemInfo.platform
    //设置系统
    if ((platform && (platform === UniSystemType.ios)) || (model && (model.indexOf('iPhone') > -1 || model.indexOf('iPad') > -1))) {
      this.isIos = true
      //必须有此判断，要不然会覆盖mp的值
      this.system = UniSystemType.ios
    } else {
      this.isAndroid = true
      this.system = UniSystemType.android
    }

    this.screenHeight = systemInfo.screenHeight
    this.windowHeight = systemInfo.windowHeight
    this.statusBarHeight = systemInfo.statusBarHeight
    this.titleHeight = this.statusBarHeight + this.navBarHeight
    this.contentHeight = this.screenHeight - this.titleHeight
  }
}
