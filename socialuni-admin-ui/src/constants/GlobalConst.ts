import { OperationType } from '@/constants/OperationType'
import DevAccountType from '@/constants/DevAccountType'
import ForbidStatus from '@/constants/audit/ForbidStatus'
import CosImgResultType from '@/constants/audit/CosImgResultType'
import ViolateType from '@/constants/ViolateType'
import UserStatus from '@/constants/UserStatus'
import DataTypeStrEnum from '@/constants/enum/DataTypeStrEnum'

export default class GlobalConst {
    static readonly OperationType: typeof OperationType = OperationType
    static readonly DevAccountType: typeof DevAccountType = DevAccountType
    static readonly ForbidStatus: typeof ForbidStatus = ForbidStatus
    static readonly CosImgResultType: typeof CosImgResultType = CosImgResultType
    static readonly ViolateType: typeof ViolateType = ViolateType
    static readonly UserStatus: typeof UserStatus = UserStatus
    static readonly DataTypeStrEnum: typeof DataTypeStrEnum = DataTypeStrEnum
}
