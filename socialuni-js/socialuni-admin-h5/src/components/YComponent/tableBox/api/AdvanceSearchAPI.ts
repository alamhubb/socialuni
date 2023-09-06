import request from '@/plugins/request'
import AddQueryDataQO from '@/components/YComponent/tableBox/api/AddQueryDataQO'
import ObjectUtil from '@/components/YComponent/utils/ObjectUtil'
import router from '@/router'

export default class AdvanceSearchAPI {
  static addQueryConditionQO(name: string, optionParams: string) {
    const queryData = new AddQueryDataQO()
    queryData.optionType = 1
    queryData.option = router.currentRoute.name
    queryData.name = name
    queryData.optionParams = optionParams
    return request.post<any>('/option/add', queryData)
  }

  static async queryConditions() {
    const queryData = new AddQueryDataQO()
    queryData.optionType = 1
    queryData.option = router.currentRoute.name
    const res = await request.get<any>('/option/list?' + ObjectUtil.toParamData(queryData))
    console.log(res.data)
    return res
  }

  static async deleteConditions(index:number) {
    const queryData = new AddQueryDataQO()
    queryData.optionType = 1
    queryData.option = router.currentRoute.name
    queryData.index = index
    return request.post<any>('/option/delete', queryData)
  }

  static updateQueryConditionQO(item: any, optionParams: string) {
    const queryData = new AddQueryDataQO()
    queryData.optionType = 1
    queryData.option = router.currentRoute.name
    queryData.name = item.name
    queryData.index = item.index
    queryData.optionParams = optionParams
    return request.post<any>('/option/update', queryData)
  }

}
