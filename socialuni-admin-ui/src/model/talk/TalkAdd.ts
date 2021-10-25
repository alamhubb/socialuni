import TalkImg from "@/model/talk/TalkImg"
import District from "@/model/District"

export default class TalkAdd {
  public content: string
  public adCode: string
  public imgs: TalkImg []
  public tagIds: number[]
  //经度,经度范围-180~180
  public lon: number = null
  //纬度,纬度范围-90~90
  public lat: number = null
  public formId: string = null

  constructor(content: string, imgs: TalkImg[], district: District, tagIds: number[],formId: string) {
    this.content = content
    this.imgs = imgs
    this.adCode = district.adCode
    this.tagIds = tagIds
    this.lon = district.lon
    this.lat = district.lat
    this.formId = formId
  }
}
