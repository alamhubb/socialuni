import GetSystemInfoResult = UniApp.GetSystemInfoResult
import SocialuniPlatformType from "socialuni-constant/constant/SocialuniPlatformType";
import SocialuniMpPlatformType from "socialuni-constant/constant/SocialuniMpPlatformType";
import SocialuniUniDeviceType from "socialuni-constant/constant/SocialuniUniDeviceType";
import SocialuniSystemType from "socialuni-constant/constant/SocialuniSystemType";
import {reactive} from "vue";
import PlatformType from "socialuni-constant/constant/PlatformType";

class SocialuniSystemModule {
    isUniApp = false
    //设备，pc，手机，ipad
    //@ts-ignore
    NODE_ENV = process.env.NODE_ENV
    //@ts-ignore
    isDev = process.env.NODE_ENV === 'development'
    //@ts-ignore
    isProd = process.env.NODE_ENV !== 'development'
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

    get isNotMp() {
        return !this.isMp
    }

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
    platform = PlatformType.h5
    // 小程序类型
    mpPlatform = null


    // 条件编译属性
    // ios android h5,默认安卓
    // 登录平台
    screenHeight = 0
    windowHeight = 0
    screenWidth = 0
    windowWidth = 0
    //减去状态栏和导航栏的高度
    contentHeight = 0
    statusBarHeight = 0
    navBarHeight = 44
    titleHeight = 0
    appVersion = 0


    constructor() {
        try {
            if (uni) {
                this.isUniApp = true
                //设置平台
                // #ifdef APP-PLUS
                this.isApp = true
                this.platform = SocialuniPlatformType.app
                //获取app版本号
                plus.runtime.getProperty(plus.runtime.appid, (widgetInfo) => {
                    this.appVersion = Number(widgetInfo.version.split('.').join(''))
                })
                const phoneInfo = plus.push.getClientInfo()
                this.clientid = phoneInfo.clientid
                // #endif

                // #ifdef MP
                this.isMp = true
                this.platform = SocialuniPlatformType.mp
                // #ifdef MP-WEIXIN
                this.isMpWx = true
                this.mpPlatform = SocialuniMpPlatformType.wx
                // #endif
                // #ifdef MP-QQ
                this.isMpQQ = true
                this.mpPlatform = SocialuniMpPlatformType.qq
                // #endif
                // #endif

                // #ifdef H5
                this.isH5 = true
                this.platform = SocialuniPlatformType.h5
                // #endif

                // 判断是否为ios平台
                const systemInfo: GetSystemInfoResult = uni.getSystemInfoSync()
                this.systemInfo = systemInfo
                console.log(this.systemInfo)
                console.log('执行了')
                //获取是否苹果平台
                const model: string = systemInfo.model
                //小程序开发工具时会为 devtools
                const platform: string = systemInfo.platform
                //设置系统
                if (model === SocialuniUniDeviceType.pc) {
                    this.isPc = true
                    this.isMobile = false
                    this.device = SocialuniUniDeviceType.pc
                } else {
                    this.isPc = false
                    this.isMobile = true
                    this.device = SocialuniUniDeviceType.phone
                }
                if ((platform && (platform === SocialuniSystemType.ios)) || (model && (model.indexOf('iPhone') > -1 || model.indexOf('iPad') > -1))) {
                    this.isIos = true
                    //必须有此判断，要不然会覆盖mp的值
                    this.system = SocialuniSystemType.ios
                } else {
                    this.isAndroid = true
                    this.system = SocialuniSystemType.android
                }

                this.screenHeight = systemInfo.screenHeight
                this.windowHeight = systemInfo.windowHeight
                this.statusBarHeight = systemInfo.statusBarHeight
                this.titleHeight = this.statusBarHeight + this.navBarHeight
                this.contentHeight = this.screenHeight - this.titleHeight
            }
        } catch (e) {
            window.addEventListener('resize', () => {
                this.windowWidth = window.innerWidth
                this.windowHeight = window.innerHeight
            })
        }
    }

    //ios和qq小程序禁止虚拟支付，统一判断
    get isIosOrMpQQ() {
        return this.isProd && (this.isIos || this.isMpQQ)
    }
}

export const socialuniSystemModule: SocialuniSystemModule = reactive(new SocialuniSystemModule())
