import { VuexModule, Module, Action } from 'vuex-class-modules'
import DistrictVO from '@/model/DistrictVO'
import TagVO from '@/model/tag/TagVO'
import HomeSwiperVO from '@/model/HomeSwiperVO'
import TagTypeVO from '@/model/tag/TagTypeVO'
import LocationUtil from '@/utils/LocationUtil'
import UnreadNotifyVO from '@/model/UnreadNotifyVO'
import SocialAuthType from '@/const/SocialAuthType'
import ReportAPI from '@/api/ReportAPI'
import QingchiAPI from '@/api/QingchiAPI'
import GenderType from '@/const/GenderType'

@Module({ generateMutationSetters: true })
export default class AppModule extends VuexModule {
  // 系统加载时通过getDistrictAction赋值
  //有记录的花记录上一次的，没有记录的话初始全国的
  openLocation: boolean = LocationUtil.getOpenLocation()
  district: DistrictVO = LocationUtil.getLocation()
  districts: DistrictVO [] = []
  notifies: UnreadNotifyVO [] = []
  reportTypes: string[] = []
  tags: TagVO[] = []
  tagTypes: TagTypeVO[] = []
  homeSwipers: HomeSwiperVO[] = []
  // 动态页展示广告,设置一些默认值，在这里设置还是去使用的地方设置
  appConfig: any = {}
  onlineUsersCount = 0

  appSocialSecretKey = process.env.VUE_APP_SOCAIL_SECRETKEY
  appQueryGender = GenderType.all

  //三方授权时携带的参数
  threeSecretKey = ''
  threeUserId = ''
  threeAppId = ''
  threeAuthType = ''
  // threeProviderType = ''

  //是否为三方授权
  get isThreeAuth () {
    console.log(this.threeSecretKey)
    return !!this.threeSecretKey
  }

  get isAuthUser () {
    return this.isThreeAuth && this.threeAuthType === SocialAuthType.user
  }

  get isAuthPhone () {
    return this.isThreeAuth && this.threeAuthType === SocialAuthType.phone
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
}
