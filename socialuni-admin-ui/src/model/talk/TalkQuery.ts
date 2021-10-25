import District from "@/model/District"

export default class TalkQuery {
  public talkIds: number[]
  public adCode: string
  public lon: number
  public lat: number
  public useNearby: number
  public tagIds: number[]
  public homeType: string

  constructor(talkIds: number[], district: District, tagIds: number[], homeType: string) {
    this.talkIds = talkIds
    this.adCode = district.adCode
    this.lon = district.lon
    this.lat = district.lat
    this.useNearby = district.useNearby
    this.tagIds = tagIds
    this.homeType = homeType
  }
}
