/**
 * 举报内容的api
 */
import request from '@/plugins/request'
import UserIdentityAuditRO from '@/model/RO/user/identity/UserIdentityAuditRO'

export default class UserIdentityAuditAPI {
  static queryUserIdentityAuthAuditListAPI() {
    return request.post('userIdentity/queryUserIdentityAuthAuditList')
  }

  static auditUserIdentityListAPI(auditROS: UserIdentityAuditRO[]) {
    return request.post('userIdentity/auditUserIdentityList', auditROS)
  }
}
