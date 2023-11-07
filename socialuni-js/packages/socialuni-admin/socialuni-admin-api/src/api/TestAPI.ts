/**
 * 举报内容的api
 */
import socialuniAdminRequest from '../socialuniAdminRequest'
import KeywordsDetailVO from '@/model/violateWord/KeywordsDetailVO'
import ObjectUtils from '@/utils/ObjectUtils'

export default class TestAPI {
  public static testKeywordsAPI(content: string, count = 1000) {
    return socialuniAdminRequest.post('keywords/test', ObjectUtils.toFormData({
      content, count
    }))
  }
}
