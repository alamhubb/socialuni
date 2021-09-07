import { locationModule } from '/socialuni/store'

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

  constructor (talkIds: number[], tagIds: number[], tabType: string, gender: string, minAge: number, maxAge: number) {
    this.talkIds = talkIds
    const district = locationModule.location
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
  }
}
