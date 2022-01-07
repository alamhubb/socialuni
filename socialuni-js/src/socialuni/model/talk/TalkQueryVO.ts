import { socialLocationModule } from '../../store'

export default class TalkQueryVO {
  public talkIds: number[]
  public adCode: string
  public lon: number
  public lat: number
  public tagIds: number[]
  public homeTabType: string
  public gender: string
  public minAge: number
  public maxAge: number
  public queryDate: Date

  constructor (talkIds: number[], tagIds: number[], tabType: string, gender: string, minAge: number, maxAge: number, queryDate: Date) {
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
    this.queryDate = queryDate
  }
}
