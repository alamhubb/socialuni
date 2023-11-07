/**
 * 举报内容的api
 */
import socialuniAdminRequest from '../socialuniAdminRequest'
import TencentCosAuditHistoryRO from '@/model/audit/TencentCosAuditHistoryRO'

export default class AuditAPI {
  public static queryImgAuditHistoryAPI(data) {
    return socialuniAdminRequest.post<TencentCosAuditHistoryRO[]>('audit/queryImgAuditHistory', data)
  }

  public static auditImgListAPI(data) {
    return socialuniAdminRequest.post<TencentCosAuditHistoryRO[]>('audit/auditImgList', data)
  }
}
