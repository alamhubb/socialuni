import { appModule, locationModule, tagModule } from '@/plugins/store/index'

export default class AppStoreCom {
  //获取首页加载需要的数据
  static getHomeLoadData () {
  }

  // 获取首页加载后需要的数据
  static getHomeLoadAfterData () {
    tagModule.getHotTagsAction()
    tagModule.getHotTagTypesAction()
    locationModule.getHotDistrictsAction()
    appModule.getReportTypesAction()
    appModule.getHomeSwipersAction()
  }
}
