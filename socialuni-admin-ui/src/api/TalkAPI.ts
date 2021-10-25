/**
 * 举报内容的api
 */
import request from '@/plugins/request'
import ReportAudit from '@/model/report/ReportAudit'

export default class TalkAPI {
  public static queryTalkAPI(talkId: number) {
    return http.post('talk/queryTalk?talkId=' + talkId)
  }

  public static queryUserTalksAPI(userId: number) {
    return http.post('talk/queryUserTalks?userId=' + userId)
  }
  public static queryTalkStatistics() {
    return http.post('talk/queryTalkStatistics')
  }
}
