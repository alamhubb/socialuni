import ReportAddVO from "@socialuni/socialuni-api-base/src/model/report/ReportAddVO";
import socialuniAppRequest from "../socialuniAppRequest";

export default class ReportAPI {
  static addReportAPI(reportAdd: ReportAddVO) {
    return socialuniAppRequest.post('socialuni/report/addReport', reportAdd)
  }

  static queryReportTypesAPI() {
    return socialuniAppRequest.get<string[]>('socialuni/report/queryReportTypes')
  }
}
