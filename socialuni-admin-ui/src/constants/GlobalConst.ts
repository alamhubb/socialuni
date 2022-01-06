import { OperationType } from '@/constants/OperationType'
import DevAccountType from '@/constants/DevAccountType'
import ForbidStatus from '@/constants/audit/ForbidStatus'
import CosImgResultType from '@/constants/audit/CosImgResultType'

export default class GlobalConst {
    static readonly OperationType: typeof OperationType = OperationType
    static readonly DevAccountType: typeof DevAccountType = DevAccountType
    static readonly ForbidStatus: typeof ForbidStatus = ForbidStatus
    static readonly CosImgResultType: typeof CosImgResultType = CosImgResultType
}
