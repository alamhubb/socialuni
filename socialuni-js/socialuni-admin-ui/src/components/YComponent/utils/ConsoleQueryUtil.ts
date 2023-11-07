import ObjectUtil from '@/components/YComponent/utils/ObjectUtil'
import CommonQueryQO from '@/components/YComponent/YPageable/CommonQueryQO'

export default class ConsoleQueryUtil {
  static compatible(...args): any {
    const data: CommonQueryQO = ObjectUtil.spread(...args)
    data.pageNo = data.pageNum
    data.page = data.pageNum
    data.limit = data.pageSize
    const queryData = args[0]
    const searchs = []
    for (const key in queryData) {
      const value = queryData[key]
      if (typeof value === 'object') {
        // console.log(`${key}:${value}是对象，不加入search`)
        continue
      }
      if (key !== 'fuzzyMatch' && value !== null) {
        searchs.push({ key, value })
      }
    }
    data.search = searchs
    return data
  }
}
