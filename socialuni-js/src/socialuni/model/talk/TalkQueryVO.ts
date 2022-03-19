import { socialLocationModule } from '../../store'

export default class TalkQueryVO {
  talkIds: number[]
  adCode: string
  lon: number
  lat: number
  tagIds: number[]
  homeTabType: string
  gender: string
  minAge: number
  maxAge: number
  queryTime: Date
  circleName: string
  tagNames: string[]

  constructor (talkIds: number[], tagIds: number[], tabType: string, gender: string, minAge: number, maxAge: number, queryDate: Date, circleName: string, tagNames: string[]) {
    this.talkIds = talkIds
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
    this.tagIds = tagIds
    this.homeTabType = tabType
    this.gender = gender
    this.minAge = minAge
    this.maxAge = maxAge
    this.queryTime = queryDate
    this.circleName = circleName
    this.tagNames = tagNames
  }
}
