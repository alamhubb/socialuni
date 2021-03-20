import { VuexModule, Module, Action } from 'vuex-class-modules'
import DistrictVO from '@/model/DistrictVO'
import TagVO from '@/model/tag/TagVO'
import { appModule, configModule, talkModule } from './index'
import TagAPI from '@/api/TagAPI'
import HomeSwiperVO from '@/model/HomeSwiperVO'
import TagTypeVO from '@/model/tag/TagTypeVO'
import AppInitAPI from '@/api/AppInitAPI'
import ResultVO from '@/model/ResultVO'
import AppInitDataVO from '@/model/common/AppInitDataVO'
import UserStore from '@/plugins/store/UserStore'
import LocationUtil from '@/utils/LocationUtil'
import DistrictAPI from '@/api/DistrictAPI'
import NotifyAPI from '@/api/NotifyAPI'
import UnreadNotifyVO from '@/model/UnreadNotifyVO'
import UserVO from '@/model/user/UserVO'
import AppInitQueryVO from '@/model/common/AppInitQueryVO'
import TalkVueUtil from '@/utils/TalkVueUtil'
import LoadMoreType from '@/const/LoadMoreType'
import UniUtil from '@/utils/UniUtil'
import CommonUtil from '@/utils/CommonUtil'
import ThreeAuthType from '@/const/ThreeAuthType'
import ReportAPI from '@/api/ReportAPI'

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

  //三方授权时携带的参数
  threeSecretKey = ''
  threeUserId = ''
  threeProviderAppId = ''
  threeAuthType = ''
  // threeProviderType = ''

  //是否为三方授权
  get isThreeAuth () {
    return !!this.threeSecretKey
  }

  get isAuthUser () {
    return this.isThreeAuth && this.threeAuthType === ThreeAuthType.user
  }

  get isAuthPhone () {
    return this.isThreeAuth && this.threeAuthType === ThreeAuthType.phone
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
    ReportAPI.queryReportTypesAPI().then(res => {
      this.reportTypes = res.data
    })
  }
}
