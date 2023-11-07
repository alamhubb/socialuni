/**
 * 举报内容的api
 */
import socialuniAdminRequest from '../socialuniAdminRequest'
import Identity from '@/model/user/Identity'

export default class ImgIdentityAPI {
  public static queryIdentities() {
    return socialuniAdminRequest.post('user/getIdentities')
  }

  public static getIdentityPage(
    page: number, gender?: string
  ) {
    return socialuniAdminRequest.post('user/getIdentityPage', {
      page, gender
    })
  }
}
