import { OperationType } from '@/constant/OperationType'
import DevAccountType from '@/constant/DevAccountType'
import ForbidStatus from '@/constant/audit/ForbidStatus'
import CosImgResultType from '@/constant/audit/CosImgResultType'
import ViolateType from '@/constant/ViolateType'
import UserStatus from '@/constant/UserStatus'
import DataTypeStrEnum from '@/constant/enum/DataTypeStrEnum'
import DataTableColumnTypeEnum from '@/components/dataTable/DataTableColumnTypeEnum'
import SocialuniAuditContentType from '@/constant/SocialuniAuditContentType'
import SkipType from '@/constant/SkipType'

export default class GlobalConst {
  static readonly OperationType: typeof OperationType = OperationType
  static readonly DevAccountType: typeof DevAccountType = DevAccountType
  static readonly ForbidStatus: typeof ForbidStatus = ForbidStatus
  static readonly CosImgResultType: typeof CosImgResultType = CosImgResultType
  static readonly ViolateType: typeof ViolateType = ViolateType
  static readonly UserStatus: typeof UserStatus = UserStatus
  static readonly DataTypeStrEnum: typeof DataTypeStrEnum = DataTypeStrEnum
  static readonly DataTableColumnTypeEnum: typeof DataTableColumnTypeEnum = DataTableColumnTypeEnum
  static readonly SocialuniAuditContentType: typeof SocialuniAuditContentType = SocialuniAuditContentType
  static readonly SkipType: typeof SkipType = SkipType
}
