//动态可见范围类型
import EnumStrVO from './EnumStrVO'

export default class VisibleType {
  // 系统
  static readonly fullNetwork: EnumStrVO = new EnumStrVO('fullNetwork', '全网')
  static readonly selfSoft: EnumStrVO = new EnumStrVO('selfSoft', '仅本软件')

  static readonly enums = [
    VisibleType.fullNetwork,
    VisibleType.selfSoft
  ]
}
