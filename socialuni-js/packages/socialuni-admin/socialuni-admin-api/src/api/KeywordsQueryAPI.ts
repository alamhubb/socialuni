/**
 * 举报内容的api
 */
import socialuniAdminRequest from '../socialuniAdminRequest'
import ReportAudit from '@/model/report/ReportAudit'
import ReportVO from '@/model/report/ReportVO'
import ObjectUtils from '@/utils/ObjectUtils'
import KeywordsDetailVO from '@/model/violateWord/KeywordsDetailVO'
import keywordsVO from '@/model/violateWord/KeywordsVO'

export default class KeywordsQueryAPI {
  public static scanTalkKeywordsAPI() {
    return socialuniAdminRequest.post('keywords/scanTalkKeywords')
  }

  public static scanCommentKeywordsAPI() {
    return socialuniAdminRequest.post('keywords/scanCommentKeywords')
  }

  public static scanMessageKeywordsAPI() {
    return socialuniAdminRequest.post('keywords/scanMessageKeywords')
  }

  public static queryKeywordAPI(content: string, count = 1000) {
    return socialuniAdminRequest.post<KeywordsDetailVO>('keywords/queryKeyword', ObjectUtils.toFormData({
      content, count
    }))
  }

  public static keywordsAddList(keywordList: string[], count = 10000) {
    return socialuniAdminRequest.post<keywordsVO[]>('keywords/keywordsAddList', ObjectUtils.toFormData({
      keywordList, count
    }))
  }

  public static queryKeywordsAPI() {
    return socialuniAdminRequest.post('keywords/queryKeywords')
  }
}
