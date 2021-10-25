/**
 * 举报内容的api
 */
import request from '@/plugins/request'
import ReportAudit from '@/model/report/ReportAudit'
import ReportVO from "@/model/report/ReportVO";
import ObjectUtils from "@/utils/ObjectUtils";
import KeywordsDetailVO from "@/model/violateWord/KeywordsDetailVO";
import keywordsVO from "@/model/violateWord/KeywordsVO";

export default class KeywordsQueryAPI {
  public static scanTalkKeywordsAPI() {
    return http.post('keywords/scanTalkKeywords')
  }

  public static scanCommentKeywordsAPI() {
    return http.post('keywords/scanCommentKeywords')
  }

  public static scanMessageKeywordsAPI() {
    return http.post('keywords/scanMessageKeywords')
  }


  public static queryKeywordAPI(content: string, count: number = 1000) {
    return http.post<KeywordsDetailVO>('keywords/queryKeyword', ObjectUtils.toFormData({
      content, count
    }))
  }

  public static keywordsAddList(keywordList: string[], count: number = 10000) {
    return http.post<keywordsVO[]>('keywords/keywordsAddList', ObjectUtils.toFormData({
      keywordList, count
    }))
  }

  public static queryKeywordsAPI() {
    return http.post('keywords/queryKeywords')
  }

}
