/**
 * 举报内容的api
 */
import socialuniAdminRequest from '../socialuniAdminRequest'
import ReportVO from '@/model/report/ReportVO'

export default class ReportAPI {
  public static queryReportTypesAPI() {
    return socialuniAdminRequest.post('report/queryReportTypes')
  }

  public static queryReportsAPI() {
    return socialuniAdminRequest.post('report/queryReports')
  }

  public static queryReportHistoryAPI() {
    return socialuniAdminRequest.post('report/queryReportHistory')
  }

  public static queryViolationHistoryAPI() {
    return socialuniAdminRequest.post('report/queryViolationHistory')
  }

  public static reviewReportsAPI(reports: ReportVO[]) {
    return socialuniAdminRequest.post('report/reviewReports', reports)
  }

  public static queryUserReportsAPI(userId: number) {
    return socialuniAdminRequest.post('report/queryUserReports?userId=' + userId)
  }

  public static queryUserContentsByPhoneNumAPI(phoneNum: number) {
    return socialuniAdminRequest.post('report/queryUserContentsByPhoneNum?phoneNum=' + phoneNum)
  }

  public static reportAuditAPI(row:ReportVO) {
    return socialuniAdminRequest.post('report/reportAudit', row)
  }

  public static reportAuditListAPI(reports: ReportVO []) {
    return socialuniAdminRequest.post('report/reportAuditList', reports)
  }

  public static getViolationAPI() {
    return socialuniAdminRequest.post('report/getViolation')
  }
}
