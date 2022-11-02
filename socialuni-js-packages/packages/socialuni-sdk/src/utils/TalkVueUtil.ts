import StorageUtil from './StorageUtil'
import TalkTabVO from "socialuni-api/src/model/talk/TalkTabVO";
import TalkTabType from "socialuni-constant/constant/TalkTabType";

const talkTabFollowDefault = new TalkTabVO()
talkTabFollowDefault.name = TalkTabType.follow_name
talkTabFollowDefault.appDefaultTab = true
// talkTabFollowDefault.type = TalkTabType.follow_type

const talkTabHomeDefault = new TalkTabVO()
talkTabHomeDefault.name = TalkTabType.home_name
talkTabHomeDefault.appDefaultTab = true
// talkTabHomeDefault.type = TalkTabType.home_type

const talkTabCityDefault = new TalkTabVO()
talkTabCityDefault.name = TalkTabType.city_name
talkTabCityDefault.appDefaultTab = true
// talkTabCityDefault.type = TalkTabType.city_type

const defaultTabs = [
  talkTabFollowDefault,
  talkTabHomeDefault,
  talkTabCityDefault
]

const TalkTabsDefaultNames = defaultTabs.map(item => item.name)

export default class TalkVueUtil {
  static readonly TalkTabsKey: string = 'talkTabs'
  static readonly TalkTabsDefault: TalkTabVO [] = defaultTabs
  static readonly TalkTabsDefaultNames: string [] = TalkTabsDefaultNames


  static readonly talkTabIndexKey: string = 'talkTabIndex'
  static readonly talkTabIndexDefault: number = 1

  static readonly talkTabTypeKey: string = 'talkTabType'
  static readonly talkTabTypeDefault: string = TalkTabType.home_type


  static getTalkTabs(): TalkTabVO [] {
    const homeTypeTalks: TalkTabVO [] = StorageUtil.getObj(TalkVueUtil.TalkTabsKey)
    return homeTypeTalks || TalkVueUtil.TalkTabsDefault
  }

  static getCurTalkTabIndex(): number {
    let index = StorageUtil.getObj(TalkVueUtil.talkTabIndexKey)
    if (index !== 0) {
      index = index || TalkVueUtil.talkTabIndexDefault
    }
    return index
  }
}
