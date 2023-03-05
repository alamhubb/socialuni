export default class SocialuniPageQueryQO {
  firstLoad = true
  queryTime = new Date()
  listData = []
  queryData = null

  static queryByPageAPI (api: Function, queryQO: SocialuniPageQueryQO, queryData: any) {
    queryQO.queryData = queryData
    return api(queryQO)
  }
}
