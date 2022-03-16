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
    if (district) {
      this.adCode = district.adCode
      this.lon = district.lon
      this.lat = district.lat
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
