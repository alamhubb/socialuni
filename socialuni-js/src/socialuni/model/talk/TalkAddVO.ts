import DistrictVO from '../DistrictVO'
import LocationUtil from '../../utils/LocationUtil'
import ImgAddQO from '../user/ImgAddQO'

export default class TalkAddVO {
  public content: string
  public adCode: string
  public visibleType: string
  public visibleGender: string
  public imgs: ImgAddQO []
  public tagNames: string[]
  // 经度,经度范围-180~180
  public lon: number = null
  // 纬度,纬度范围-90~90
  public lat: number = null
  public circleName: string = null

  constructor(content: string, imgs: ImgAddQO[], district: DistrictVO, visibleType: string, visibleGender: string, circleName: string, tagNames: string[]) {
    this.content = content
    this.adCode = LocationUtil.chinaAdCode
    this.visibleType = visibleType
    this.visibleGender = visibleGender
    this.imgs = imgs
    this.circleName = circleName
    this.tagNames = tagNames
    if (district) {
      this.adCode = district.adCode
      this.lon = district.lon
      this.lat = district.lat
    }
  }
}
