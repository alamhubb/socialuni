import { OperationType } from '@/constants/OperationType'
import DevAccountType from '@/constants/DevAccountType'

export default class GlobalConst {
    static readonly OperationType: typeof OperationType = OperationType
    static readonly DevAccountType: typeof DevAccountType = DevAccountType
}
