import { Module, RegisterOptions, VuexModule } from 'vuex-class-modules'
import UniSystemType from '../constant/UniSystemType'
import UniPlatformType from '../constant/UniPlatformType'
import UniMpPlatformType from '../constant/UniMpPlatformType'
import GetSystemInfoResult = UniApp.GetSystemInfoResult

//用来存储当前用户的一些信息
@Module({ generateMutationSetters: true })
export default class UniSystemModule extends VuexModule {
  // 条件编译属性
  // ios android h5,默认安卓
  system = ''
  systemInfo: GetSystemInfoResult = null
  platform = ''
  // 小程序类型
  mpPlatform = ''

  isIos = false
  notIos = false

  isAndroid = false
  notAndroid = false

  //初始化以后第一步加载
  // 条件编译属性
  isApp = false
  notApp = false

  isMp = false
  notMp = false

  isH5 = false
  notH5 = false

  isQQ = false
  notQQ = false

  isWX = false
  notWX = false

  constructor (options: RegisterOptions) {
    super(options)
    const systemInfo: GetSystemInfoResult = uni.getSystemInfoSync()
    this.systemInfo = systemInfo
    const model: string = systemInfo.model
    const platform: string = systemInfo.platform

    //设置系统
    if ((platform && (platform === UniSystemType.ios)) || (model && (model.indexOf('iPhone') > -1 || model.indexOf('iPad') > -1))) {
      this.isIos = true
      this.notIos = !this.isIos
      //必须有此判断，要不然会覆盖mp的值
      this.system = UniSystemType.ios
    } else {
      this.isAndroid = true
      this.notAndroid = !this.isAndroid
      this.system = UniSystemType.android
    }

    //设置平台
    // #ifdef APP-PLUS
    this.isApp = true
    this.notApp = !this.isApp
    this.platform = UniPlatformType.app
    // #endif
    // #ifdef MP
    this.isMp = true
    this.notMp = !this.notMp
    this.platform = UniPlatformType.mp
    // #ifdef MP-WEIXIN
    this.isWX = true
    this.notWX = !this.isWX
    this.mpPlatform = UniMpPlatformType.wx
    // #endif
    // #ifdef MP-QQ
    this.isQQ = true
    this.notQQ = !this.isQQ
    this.mpPlatform = UniMpPlatformType.qq
    // #endif
    // #endif
    // #ifdef H5
    this.isH5 = true
    this.notH5 = !this.isH5
    this.platform = UniPlatformType.h5
    // #endif
  }
}
