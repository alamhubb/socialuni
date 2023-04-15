import socialuniAppRequest from "socialuni-base-api/src/request/socialuniAppRequest";
import ReportAddVO from "socialuni-base-api/src/model/report/ReportAddVO";

export default class ReportAPI {
  static addReportAPI(reportAdd: ReportAddVO) {
    return socialuniAppRequest.post('socialuni/report/addReport', reportAdd)
  }

  static queryReportTypesAPI() {
    return socialuniAppRequest.get<string[]>('socialuni/report/queryReportTypes')
  }
}
