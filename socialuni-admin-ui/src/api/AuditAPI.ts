/**
 * 举报内容的api
 */
import request from '@/plugins/request'
import TencentCosAuditHistoryRO from '@/model/audit/TencentCosAuditHistoryRO'

export default class AuditAPI {
  public static queryImgAuditHistoryAPI() {
    return request.post<TencentCosAuditHistoryRO[]>('audit/queryImgAuditHistory')
  }
}
