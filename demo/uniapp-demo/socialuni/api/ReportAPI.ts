import socialHttp from '../plugins/http/socialHttp'
import ReportAddVO from '../model/report/ReportAddVO'

export default class ReportAPI {
  static addReportAPI (reportAdd: ReportAddVO) {
    return socialHttp.post('report/addReport', reportAdd)
  }

  static queryReportTypesAPI () {
    return socialHttp.post<string[]>('report/queryReportTypes')
  }
}
