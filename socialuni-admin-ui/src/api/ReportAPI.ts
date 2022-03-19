/**
 * 举报内容的api
 */
import request from '@/plugins/request'
import ReportVO from '@/model/report/ReportVO'

export default class ReportAPI {
  public static queryReportTypesAPI() {
    return request.post('report/queryReportTypes')
  }

  public static queryReportsAPI() {
    return request.post('report/queryReports')
  }

  public static queryReportHistoryAPI() {
    return request.post('report/queryReportHistory')
  }

  public static queryViolationHistoryAPI() {
    return request.post('report/queryViolationHistory')
  }

  public static reviewReportsAPI(reports: ReportVO[]) {
    return request.post('report/reviewReports', reports)
  }

  public static queryUserReportsAPI(userId: number) {
    return request.post('report/queryUserReports?userId=' + userId)
  }

  public static queryUserContentsByPhoneNumAPI(phoneNum: number) {
    return request.post('report/queryUserContentsByPhoneNum?phoneNum=' + phoneNum)
  }

  public static reportAuditAPI(row:ReportVO) {
    return request.post('report/reportAudit', row)
  }

  public static reportAuditListAPI(reports: ReportVO []) {
    return request.post('report/reportAuditList', reports)
  }

  public static getViolationAPI() {
    return request.post('report/getViolation')
  }
}
