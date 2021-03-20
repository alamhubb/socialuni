//动态可见范围类型
import EnumStrVO from '@/const/EnumStrVO'

export default class ContentVisibleType {
  // 系统
  static readonly fullNetwork: EnumStrVO = new EnumStrVO('fullNetwork', '全网可见')
  static readonly smartRecommend: EnumStrVO = new EnumStrVO('smartRecommend', '智能推荐 - 同类软件可见')
  static readonly selfSoft: EnumStrVO = new EnumStrVO('selfSoft', '本软件可见')

  static readonly Values = [ContentVisibleType.fullNetwork,
    ContentVisibleType.smartRecommend,
    ContentVisibleType.selfSoft]

  static readonly Map: Map<string, string> = new Map<string, string>()
}
ContentVisibleType.Values.forEach((item) => {
  ContentVisibleType.Map.set(item.value, item.label)
})
