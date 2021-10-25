/**
 * 举报内容的api
 */
import request from '@/plugins/request'
import ObjectUtils from "@/utils/ObjectUtils";

export default class KeywordsManageAPI {
  public static addKeywordsAPI(content: string, cause: string) {
    return http.post('keywords/addKeywords?content=' + content + '&cause=' + cause)
  }

  public static testKeywordsAPI() {
    return http.post('keywords/test')
  }

  public static openPinyinOrTextAPI(id: number, type: string, cause: string) {
    return http.post('keywords/openPinyinOrText', ObjectUtils.toFormData({
      id, type, cause
    }))
  }

  public static closeKeywordsAPI(id: number, closeCause: string) {
    return http.post('keywords/closeKeywords?id=' + id + '&closeCause=' + closeCause)
  }
}
