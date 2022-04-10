/**
 * 举报内容的api
 */
import request from '@/plugins/request'

export default class UserIdentityAuditAPI {
  static queryUserIdentityAuthAuditListAPI() {
    return request.post('userIdentity/queryUserIdentityAuthAuditList')
  }
}
