import {Module, VuexModule} from 'vuex-class-modules'
import LoginProvider from '../constant/LoginProvider'
import PlatformType from '../constant/PlatformType'
import UniSystemType from '../constant/uni/UniSystemType'
import UniPlatformType from '../constant/uni/UniPlatformType'
import DevModeType from '@/socialuni/constant/DevModeType'
import GetSystemInfoResult = UniApp.GetSystemInfoResult
import UniDeviceType from "@/socialuni/constant/uni/UniDeviceType";

//和终端相关的信息
@Module({generateMutationSetters: true})
export default class SocialSystemModule extends VuexModule {
  isDevMode: boolean = process.env.NODE_ENV === DevModeType.dev

  //设备，pc，手机，ipad
  device = null
  isPc = false
  isMobile = false
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
  // ios android windows,默认安卓
  system = ''
  systemInfo: GetSystemInfoResult = null
  //h5，小程序，app
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
  get isIosAndMpQQ() {
    return this.isIos && this.isMpQQ
  }


  // 动态页展示广告,设置一些默认值，在这里设置还是去使用的地方设置
  getSystemInfo() {
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
    this.mpPlatform = LoginProvider.wx
    // #endif
    // #ifdef MP-QQ
    this.isMpQQ = true
    this.mpPlatform = LoginProvider.qq
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
    if (model === UniDeviceType.pc) {
      this.isPc = true
      this.isMobile = false
      this.device = UniDeviceType.pc
    } else {
      this.isPc = false
      this.isMobile = true
      this.device = UniDeviceType.phone
    }
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
