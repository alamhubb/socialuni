import request from '../../plugins/http/request'
import ReportAddVO from '../../model/report/ReportAddVO'

export default class ReportAPI {
  static addReportAPI(reportAdd: ReportAddVO) {
    return request.post('socialuni/report/addReport', reportAdd)
  }

  static queryReportTypesAPI() {
    return request.post<string[]>('socialuni/report/queryReportTypes')
  }
}
