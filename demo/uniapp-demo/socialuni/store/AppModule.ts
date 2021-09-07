import { Action, Module, VuexModule } from 'vuex-class-modules'
import DistrictVO from '/socialuni/model/DistrictVO'
import TagVO from '/socialuni/model/tag/TagVO'
import HomeSwiperVO from '/socialuni/model/HomeSwiperVO'
import TagTypeVO from '/socialuni/model/tag/TagTypeVO'
import LocationUtil from '/socialuni/utils/LocationUtil'
import UnreadNotifyVO from '/socialuni/model/UnreadNotifyVO'
import SocialAuthType from '/socialuni/const/SocialAuthType'
import ReportAPI from '/socialuni/api/ReportAPI'
import QingchiAPI from '/socialuni/api/QingchiAPI'
import DevUserVO from '/socialuni/model/dev/DevUserVO'
import DevUserAPI from '/socialuni/api/DevUserAPI'
import { appModule, configModule } from './index'
import SocialUniAuthVO from '/socialuni/model/openData/SocialUniAuthVO'
import DevModeType from '/socialuni/const/DevModeType'
import AppConfigAPI from '/socialuni/api/AppConfigAPI'
import AppInitDataRO from '/socialuni/model/common/AppInitDataRO'

@Module({ generateMutationSetters: true })
export default class AppModule extends VuexModule {
  // 系统加载时通过getDistrictAction赋值
  //有记录的花记录上一次的，没有记录的话初始全国的
  openLocation: boolean = LocationUtil.getOpenLocation()
  district: DistrictVO = LocationUtil.getLocation()
  districts: DistrictVO [] = []
  notifies: UnreadNotifyVO [] = []
  reportTypes: string[] = []
  imgPath: string = ''
  tags: TagVO[] = []
  tagTypes: TagTypeVO[] = []
  homeSwipers: HomeSwiperVO[] = []
  // 动态页展示广告,设置一些默认值，在这里设置还是去使用的地方设置
  appConfig: any = {}
  onlineUsersCount = 0

  //三方授权时携带的参数
  threeSecretKey = ''
  threeUserId = '123'
  threeAppId = '123'
  threeAuthType = SocialAuthType.user
  // threeAuthType = ''
  // threeProviderType = ''
  threeDevUser: DevUserVO = null
  isDevMode: boolean = process.env.NODE_ENV === DevModeType.dev

  //是否为三方授权
  get isThreeAuth () {
    return !!this.threeSecretKey
  }

  get isAuthUser () {
    return this.isThreeAuth && this.threeAuthType === SocialAuthType.user
  }

  get isAuthPhone () {
    return this.isThreeAuth && this.threeAuthType === SocialAuthType.phone
  }

  setThreeAuthInfo (params) {
    if (params && params.referrerInfo) {
      const info = params.referrerInfo
      //获取三方的appid
      appModule.threeAppId = info.appId
      //如果有跳转信息
      const extraData: SocialUniAuthVO = info.extraData
      if (extraData) {
        appModule.threeSecretKey = extraData.appSecretKey
        appModule.threeUserId = extraData.appUserId
        appModule.threeAuthType = extraData.authType
        //支持非授权跳转
        /*if (!appModule.threeSecretKey) {
          const result: ResultVO<any> = new ResultVO<any>()
          result.errorCode = ErrorCode.business
          result.errorMsg = '社交联盟密钥错误'
          result.success = false
          UniUtil.showLoading('密钥错误，返回中...')
          uni.navigateBackMiniProgram({ extraData: result })
        }*/
      }
    }
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
  getThreeDevUserAction () {
    DevUserAPI.queryDevUserDetailAPI().then(res => {
      this.threeDevUser = res.data
    })
  }

  @Action
  getAppConfigAction () {
    AppConfigAPI.getAppConfigAPI().then(res => {
      const appInitData: AppInitDataRO = res.data
      configModule.appConfig = appInitData.appConfig
      this.onlineUsersCount = appInitData.onlineUsersCount
      this.reportTypes = appInitData.reportTypes
      // this.imgPath = appInitData.imgPath
    })
  }

  @Action
  getImgPathAction () {
    AppConfigAPI.getImgPathAPI().then(res => {
      this.imgPath = res.data
    })
  }
}
