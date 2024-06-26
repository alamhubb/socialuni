import SocialCircleRO from "socialuni-api-base/src/model/community/circle/SocialCircleRO";
import CircleTypeRO from "socialuni-api-base/src/model/community/circle/CircleTypeRO";
import {reactive} from "vue";
import SocialuniCircleAPI from "socialuni-community-api/src/api/SocialuniCircleAPI";
import TagStorageUtil from "../util/TagStorageUtil";
import SocialCircleStorageUtil from "../util/SocialCircleStorageUtil";
import JsonUtil from "qing-util/src/util/JsonUtil";
import CircleCreateChatQO from "socialuni-api-base/src/model/community/circle/CircleCreateChatQO";
import {socialTalkModule} from "./SocialTalkModule";
import SocialuniTalkTabCircleRO from "socialuni-api-base/src/model/community/circle/SocialuniTalkTabCircleRO";


class SocialCircleModule {
    circleName: string = null
    circles: SocialCircleRO[] = []
    circleInfo: SocialuniTalkTabCircleRO = null
    circleTypes: CircleTypeRO[] = []
    historyCircles: CircleTypeRO [] = []
    //最多存4个
    mineHistoryCircleNames: string[] = SocialCircleStorageUtil.getCircleNames()

    setMineHistoryCircleNames(circleName: string) {
        if (circleName) {
            this.mineHistoryCircleNames.unshift(circleName)
            this.mineHistoryCircleNames = this.mineHistoryCircleNames.slice(0, 4)
            SocialCircleStorageUtil.saveCircleNames()
        }
    }

    setCircleName(circleName: string) {
        this.circleName = circleName
        this.setMineHistoryCircleNames(circleName)
        if (this.circleName) {
            console.log('执行了查询圈子')
            SocialuniCircleAPI.queryCircleTalkTabInfoAPI(new CircleCreateChatQO(this.circleName, null)).then(res => {
                this.circleInfo = res.data
            })
        }
    }


    getHotCirclesAction() {
        // 查询前20条，未读优先，如果没有未读，就是按时间排序
        return SocialuniCircleAPI.queryHotCirclesAPI().then((res) => {
            this.circles = res.data
        })
    }

    //可以加一个使用过的circle列表
    get mineCirclesTop10() {
        const showCircleNames: string[] = []
        if (this.circleName) {
            showCircleNames.push(this.circleName)
        }
        showCircleNames.push(...this.mineHistoryCircleNames)
        showCircleNames.push(...this.circles.map(item => item.name))
        //当前选中的circle
        //历史的circle
        //热门的circle
        const circleSet = new Set(showCircleNames)
        const circles = Array.from(circleSet).slice(0, 10)
        if (!circles.length) {
            return [null]
        }
        return circles
    }


    //应该查询前20条已读和未读通知，然后有未读通知推送
    /*
    getCirclesAction () {
      // 查询前20条，未读优先，如果没有未读，就是按时间排序
      return CircleAPI.queryCirclesAPI().then((res: any) => {
        this.tags = res.data
      })
    }*/


    getHotCircleTypesAction() {
        // 查询前20条，未读优先，如果没有未读，就是按时间排序
        return SocialuniCircleAPI.queryHotCircleTypesAPI().then((res: any) => {
            this.circleTypes = res.data
        })
    }


    getCircleTypesAction() {
        if (!this.circleTypes[1] || !this.circleTypes[1].circles || !this.circleTypes[1].circles.length) {
            // 查询前20条，未读优先，如果没有未读，就是按时间排序
            return SocialuniCircleAPI.queryCircleTypesAPI().then((res) => {
                this.circleTypes = res.data
            })
        }
    }
}

export const socialCircleModule: SocialCircleModule = reactive(new SocialCircleModule())
