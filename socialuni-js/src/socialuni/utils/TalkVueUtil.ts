import StorageUtil from './StorageUtil'
import TalkTabVO from '../model/talk/TalkTabVO'
import TalkTabType from '../constant/TalkTabType'
import AppConfig from "@/socialuni/config/AppConfig";

const talkTabFollowDefault = new TalkTabVO()
talkTabFollowDefault.name = TalkTabType.follow_name
talkTabFollowDefault.type = TalkTabType.follow_type

const talkTabHomeDefault = new TalkTabVO()
talkTabHomeDefault.name = TalkTabType.home_name
talkTabHomeDefault.type = TalkTabType.home_type

const talkTabCityDefault = new TalkTabVO()
talkTabCityDefault.name = TalkTabType.city_name
talkTabCityDefault.type = TalkTabType.city_type

let defaultTabs = []
if (AppConfig.isSchoolType) {
  defaultTabs = [
    new TalkTabVO(TalkTabType.follow_name, TalkTabType.follow_type),
    new TalkTabVO(TalkTabType.school_name, TalkTabType.school_type),
    new TalkTabVO(TalkTabType.city_name, TalkTabType.city_type)
  ]
} else {
  defaultTabs = [
    new TalkTabVO(TalkTabType.follow_name, TalkTabType.follow_type),
    new TalkTabVO(TalkTabType.home_name, TalkTabType.home_type),
    new TalkTabVO(TalkTabType.city_name, TalkTabType.city_type)
  ]
}

export default class TalkVueUtil {
  static readonly TalkTabsKey: string = 'talkTabs'
  static readonly TalkTabsDefault: TalkTabVO [] = defaultTabs


  static readonly talkTabIndexKey: string = 'talkTabIndex'
  static readonly talkTabIndexDefault: number = 1

  static readonly talkTabTypeKey: string = 'talkTabType'
  static readonly talkTabTypeDefault: string = TalkTabType.home_type


  static getTalkTabs(): TalkTabVO [] {
    const homeTypeTalks: TalkTabVO [] = StorageUtil.getObj(TalkVueUtil.TalkTabsKey) || TalkVueUtil.TalkTabsDefault

    if (homeTypeTalks.length < 4) {
      return TalkVueUtil.TalkTabsDefault
    }
    if (homeTypeTalks[1].name !== TalkVueUtil.TalkTabsDefault[1].name) {
      homeTypeTalks[1].name = TalkVueUtil.TalkTabsDefault[1].name
      homeTypeTalks[1].type = TalkVueUtil.TalkTabsDefault[1].type
      homeTypeTalks[1].talks = []
    }
    return homeTypeTalks
  }

  static getCurTalkTabIndex(): number {
    let index = StorageUtil.getObj(TalkVueUtil.talkTabIndexKey)
    if (index !== 0) {
      index = index || TalkVueUtil.talkTabIndexDefault
    }
    return index
  }
}
