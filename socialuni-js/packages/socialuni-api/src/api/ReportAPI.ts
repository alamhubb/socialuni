import request from "socialuni-api/src/request/request";
import ReportAddVO from "../model/report/ReportAddVO";

export default class ReportAPI {
  static addReportAPI(reportAdd: ReportAddVO) {
    return request.post('socialuni/report/addReport', reportAdd)
  }

  static queryReportTypesAPI() {
    return request.get<string[]>('socialuni/report/queryReportTypes')
  }
}
