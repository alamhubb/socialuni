/**
 * 举报内容的api
 */
import request from '@/plugins/request'
import KeywordsDetailVO from '@/model/violateWord/KeywordsDetailVO'
import ObjectUtils from '@/utils/ObjectUtils'

export default class TestAPI {
  public static testKeywordsAPI(content: string, count = 1000) {
    return request.post('keywords/test', ObjectUtils.toFormData({
      content, count
    }))
  }
}
