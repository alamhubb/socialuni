import TalkTabType from "socialuni-constant/constant/TalkTabType";
import SocialuniTalkTabRO from "socialuni-api-base/src/model/talk/SocialuniTalkTabRO";
import QingAppUtil from "qingjs/src/util/QingAppUtil";
import SocialuniPageQueryUtil from "socialuni-api-base/src/model/common/SocialuniPageQueryUtil";
import TalkVO from "socialuni-api-base/src/model/talk/TalkVO";
import TalkQueryVO from "socialuni-api-base/src/model/talk/TalkQueryVO";
import SocialuniTalkAPI from "socialuni-community-api/src/api/SocialuniTalkAPI";

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
    static readonly talkViewData: string = 'talkViewData'
    static readonly TalkTabsDefault: SocialuniTalkTabRO [] = defaultTabs
    static readonly TalkTabsDefaultNames: string [] = TalkTabsDefaultNames


    static readonly talkTabIndexKey: string = 'talkTabIndex'
    static readonly talkTabIndexDefault: number = 1

    static readonly talkTabTypeKey: string = 'talkTabType'
    static readonly talkTabTypeDefault: string = TalkTabType.home_type


    static getTalkTabs(): SocialuniTalkTabRO [] {
        const homeTypeTalks: SocialuniTalkTabRO [] = QingAppUtil.StorageUtil.getObj(TalkVueUtil.TalkTabsKey)
        const temps = homeTypeTalks || TalkVueUtil.TalkTabsDefault
        return temps
    }

    static getTalkTabQueryUtils(): SocialuniPageQueryUtil<TalkVO, TalkQueryVO, SocialuniTalkTabRO> [] {
        const temps = this.getTalkTabs().map(item => {
            const query = new SocialuniPageQueryUtil<TalkVO, TalkQueryVO, SocialuniTalkTabRO>(SocialuniTalkAPI.queryTalksAPI, null, item)
            query.queryQO.firstLoad = item.firstLoad
            query.queryQO.queryTime = item.queryTime
            query.queryQO.pageSize = item.pageSize
            query.queryQO.pageNum = item.pageNum
            query.listData = item.talks
            console.log(query.viewData)
            return query
        })
        return temps
    }


    static getCurTalkTabIndex(): number {
        let index = QingAppUtil.StorageUtil.getObj(TalkVueUtil.talkTabIndexKey)
        if (index !== 0) {
            index = index || TalkVueUtil.talkTabIndexDefault
        }
        return index
    }
}
