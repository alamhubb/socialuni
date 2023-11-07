/**
 * 举报内容的api
 */
import socialuniAdminRequest from '../socialuniAdminRequest'
import ObjectUtils from '@/utils/ObjectUtils'

export default class KeywordsManageAPI {
  public static addKeywordsAPI(content: string, cause: string) {
    return socialuniAdminRequest.post('keywords/addKeywords?content=' + content + '&cause=' + cause)
  }

  public static testKeywordsAPI() {
    return socialuniAdminRequest.post('keywords/test')
  }

  public static openPinyinOrTextAPI(id: number, type: string, cause: string) {
    return socialuniAdminRequest.post('keywords/openPinyinOrText', ObjectUtils.toFormData({
      id, type, cause
    }))
  }

  public static closeKeywordsAPI(id: number, closeCause: string) {
    return socialuniAdminRequest.post('keywords/closeKeywords?id=' + id + '&closeCause=' + closeCause)
  }
}
