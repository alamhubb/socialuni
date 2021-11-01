import BaseImg from '@/model/BaseImg'
import User from '@/model/user/User'
import Talk from '@/model/talk/Talk'
import ReportDetail from '@/model/report/ReportDetail'
import KeywordsTriggerDetailVO from '@/model/report/KeywordsTriggerDetailVO'

export default class ReportVO {
  public id: number
  public talk: Talk
  public checked = false
  public user: User
  public status: string
  public types: string[]
  public reportContentType: string
  public childReports: ReportDetail[]
  public content: string
  public imgs: BaseImg []
  public opposeRatio: number
  public supportRatio: number
  public memberCount: number
  public hasKeyword: boolean
  public violateType: string
  public auditNote: string
  public triggerKeywords: KeywordsTriggerDetailVO[]
}
