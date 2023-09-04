/**
 * 举报内容的api
 */
import socialuniAdminRequest from '../socialuniAdminRequest'
import UserIdentityAuditRO from '@/model/RO/user/identity/UserIdentityAuditRO'

export default class UserIdentityAuditAPI {
  static queryUserIdentityAuthAuditListAPI() {
    return socialuniAdminRequest.post('userIdentity/queryUserIdentityAuthAuditList')
  }

  static auditUserIdentityListAPI(auditROS: UserIdentityAuditRO[]) {
    return socialuniAdminRequest.post('userIdentity/auditUserIdentityList', auditROS)
  }
}
