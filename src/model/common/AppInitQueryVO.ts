import { locationModule, systemModule } from '@/store'
import LocationUtil from '@/utils/LocationUtil'
import DistrictVO from '@/model/DistrictVO'
import TalkFilterUtil from '@/utils/TalkFilterUtil'
import TalkTabVO from '@/model/talk/TalkTabVO'
import GenderType from '@/const/GenderType'

export default class AppInitQueryVO {
  //上次退出时记录的adCode
  public adCode: string
  public homeType: string
  public gender: string
  public minAge: number
  public maxAge: number
  public lon: number
  public lat: number
  public openLocation: boolean
  public platform: string
  public standby: string

  constructor (tabObj: TalkTabVO, district: DistrictVO) {
    this.adCode = district.adCode
    this.homeType = tabObj.type
    //默认女生
    this.gender = GenderType.girl
    this.minAge = TalkFilterUtil.getMinAgeFilter()
    this.maxAge = TalkFilterUtil.getMaxAgeFilter()
    this.platform = systemModule.platform
  }
}
