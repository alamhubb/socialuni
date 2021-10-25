/**
 * 举报内容的api
 */
import request from '@/plugins/request'
import ReportAudit from '@/model/report/ReportAudit'
import ReportVO from "@/model/report/ReportVO";

export default class ReportAPI {
  public static queryReportTypesAPI() {
    return http.post('report/queryReportTypes')
  }

  public static queryReportsAPI() {
    return http.post('report/queryReports')
  }

  public static queryReportHistoryAPI() {
    return http.post('report/queryReportHistory')
  }

  public static queryViolationHistoryAPI() {
    return http.post('report/queryViolationHistory')
  }

  public static reviewReportsAPI(reports: ReportVO[]) {
    return http.post('report/reviewReports', reports)
  }

  public static queryUserReportsAPI(userId: number) {
    return http.post('report/queryUserReports?userId=' + userId)
  }

  public static reportAuditAPI(row:ReportVO) {
    return http.post('report/reportAudit', row)
  }

  public static reportAuditListAPI(reports: ReportVO []) {
    return http.post('report/reportAuditList', reports)
  }


  public static getViolationAPI() {
    return http.post('report/getViolation')
  }
}
