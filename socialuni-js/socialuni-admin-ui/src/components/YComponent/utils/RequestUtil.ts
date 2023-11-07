import Pageable from '@/components/YComponent/YPageable/Pageable'
import ObjectUtil from '@/components/YComponent/utils/ObjectUtil'

export default class RequestUtil {
  static compatible(...args): any {
    const data: Pageable = ObjectUtil.spread(...args)
    data.pageNo = data.pageNum
    data.page = data.pageNum
    data.limit = data.pageSize
    return data
  }
}
