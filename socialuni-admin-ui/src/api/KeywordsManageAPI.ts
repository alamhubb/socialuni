/**
 * 举报内容的api
 */
import request from '@/plugins/request'
import ObjectUtils from '@/utils/ObjectUtils'

export default class KeywordsManageAPI {
  public static addKeywordsAPI(content: string, cause: string) {
    return request.post('keywords/addKeywords?content=' + content + '&cause=' + cause)
  }

  public static testKeywordsAPI() {
    return request.post('keywords/test')
  }

  public static openPinyinOrTextAPI(id: number, type: string, cause: string) {
    return request.post('keywords/openPinyinOrText', ObjectUtils.toFormData({
      id, type, cause
    }))
  }

  public static closeKeywordsAPI(id: number, closeCause: string) {
    return request.post('keywords/closeKeywords?id=' + id + '&closeCause=' + closeCause)
  }
}
