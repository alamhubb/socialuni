/**
 * 举报内容的api
 */
import socialuniAdminRequest from '../socialuniAdminRequest'
import ReportAudit from '@/model/report/ReportAudit'

export default class TalkAPI {
  public static queryTalkAPI(talkId: number) {
    return socialuniAdminRequest.post('talk/queryTalk?talkId=' + talkId)
  }

  public static queryUserTalksAPI(userId: number) {
    return socialuniAdminRequest.post('talk/queryUserTalks?userId=' + userId)
  }
  public static queryTalkStatistics() {
    return socialuniAdminRequest.post('talk/queryTalkStatistics')
  }
}
