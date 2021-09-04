import CenterUserDetailRO from '../social/CenterUserDetailRO'

export default class ReportVO {
  public user: CenterUserDetailRO
  public status: string
  public types: string[]
  public reportContentType: string
  public content: string
  public opposeRatio: number
  public supportRatio: number
  public memberCount: number
}
