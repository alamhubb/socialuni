import {socialLocationModule} from '../../store'

export default class TalkQueryVO {
  homeTabName: string
  gender: string
  minAge: number
  maxAge: number
  adCode: string
  lon: number
  lat: number
  queryTime: Date
  tagNames: string[]

  constructor(homeTabName: string, gender: string, minAge: number, maxAge: number, queryTime: Date, tagNames: string[]) {
    this.homeTabName = homeTabName
    const district = socialLocationModule.location
    //查询使用当前的
    if (district) {
      this.adCode = district.adCode
    }
    //经纬度始终使用同城的坐标
    if (socialLocationModule.cityLocation) {
      this.lon = socialLocationModule.cityLocation.lon
      this.lat = socialLocationModule.cityLocation.lat
    }
    this.gender = gender
    this.minAge = minAge
    this.maxAge = maxAge
    this.queryTime = queryTime
    this.tagNames = tagNames
  }
}
