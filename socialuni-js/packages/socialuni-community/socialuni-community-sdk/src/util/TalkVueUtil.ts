import TalkTabType from "@socialuni/socialuni-constant/constant/TalkTabType";
import SocialuniTalkTabRO from "@socialuni/socialuni-api-base/src/model/talk/SocialuniTalkTabRO";
import SocialuniAppUtil from "@socialuni/socialuni-native-util/src/util/SocialuniAppUtil";

const talkTabFollowDefault = new SocialuniTalkTabRO()
talkTabFollowDefault.name = TalkTabType.follow_name
talkTabFollowDefault.appDefaultTab = true
// talkTabFollowDefault.type = TalkTabType.follow_type

const talkTabHomeDefault = new SocialuniTalkTabRO()
talkTabHomeDefault.name = TalkTabType.home_name
talkTabHomeDefault.appDefaultTab = true
// talkTabHomeDefault.type = TalkTabType.home_type

const talkTabCityDefault = new SocialuniTalkTabRO()
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
  static readonly TalkTabsDefault: SocialuniTalkTabRO [] = defaultTabs
  static readonly TalkTabsDefaultNames: string [] = TalkTabsDefaultNames


  static readonly talkTabIndexKey: string = 'talkTabIndex'
  static readonly talkTabIndexDefault: number = 1

  static readonly talkTabTypeKey: string = 'talkTabType'
  static readonly talkTabTypeDefault: string = TalkTabType.home_type


  static getTalkTabs(): SocialuniTalkTabRO [] {
    const homeTypeTalks: SocialuniTalkTabRO [] = SocialuniAppUtil.StorageUtil.getObj(TalkVueUtil.TalkTabsKey)
    return homeTypeTalks || TalkVueUtil.TalkTabsDefault
  }

  static getCurTalkTabIndex(): number {
    let index = SocialuniAppUtil.StorageUtil.getObj(TalkVueUtil.talkTabIndexKey)
    if (index !== 0) {
      index = index || TalkVueUtil.talkTabIndexDefault
    }
    return index
  }
}
