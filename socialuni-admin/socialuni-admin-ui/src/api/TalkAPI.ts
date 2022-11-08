/**
 * 举报内容的api
 */
import request from '@/plugins/request'
import ReportAudit from '@/model/report/ReportAudit'

export default class TalkAPI {
  public static queryTalkAPI(talkId: number) {
    return request.post('talk/queryTalk?talkId=' + talkId)
  }

  public static queryUserTalksAPI(userId: number) {
    return request.post('talk/queryUserTalks?userId=' + userId)
  }
  public static queryTalkStatistics() {
    return request.post('talk/queryTalkStatistics')
  }
}
