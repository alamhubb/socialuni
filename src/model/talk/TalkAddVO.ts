import DistrictVO from '@/model/DistrictVO'
import ImgFileVO from '@/model/ImgFileVO'
import LocationUtil from '@/utils/LocationUtil'

export default class TalkAddVO {
  public content: string
  public adCode: string
  public visibleType: string
  public imgs: ImgFileVO []
  public tagIds: number[]
  // 经度,经度范围-180~180
  public lon: number = null
  // 纬度,纬度范围-90~90
  public lat: number = null

  constructor (content: string, imgs: ImgFileVO[], district: DistrictVO, tagIds: number[], visibleType: string) {
    this.content = content
    this.visibleType = visibleType
    this.imgs = imgs
    this.tagIds = tagIds
    this.adCode = LocationUtil.chinaAdCode
    if (district) {
      this.adCode = district.adCode
      this.lon = district.lon
      this.lat = district.lat
    }
  }
}
