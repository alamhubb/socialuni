/**
 * 举报内容的api
 */
import request from '@/plugins/request'
import Identity from '@/model/user/Identity'

export default class ImgIdentityAPI {
  public static queryIdentities() {
    return http.post('user/getIdentities')
  }

  public static getIdentityPage(
    page: number, gender?: string
  ) {
    return http.post('user/getIdentityPage', {
      page, gender
    })
  }
}
