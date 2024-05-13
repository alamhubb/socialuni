import SocialuniViewService from "socialuni/src/interface/SocialuniViewService";
import SocialuniPageQueryUtil from "socialuni-api-base/src/model/common/SocialuniPageQueryUtil";
import TalkVO from "socialuni-api-base/src/model/talk/TalkVO";
import TalkQueryVO from "socialuni-api-base/src/model/talk/TalkQueryVO";
import SocialuniTalkAPI from "socialuni-community-api/src/api/SocialuniTalkAPI";
import {socialCircleModule} from "../../store/SocialCircleModule";
import {socialLocationModule} from "../../store/SocialLocationModule";
import TalkQOFactory from "../../factory/TalkQOFactory";
import {socialTalkModule} from "../../store/SocialTalkModule";
import {socialuniTagModule} from "../../store/SocialTagModule";
import {nextTick, watch} from "vue";

export default class SocialuniTalkListViewService extends SocialuniViewService<SocialuniLoginViewServiceRefs> {

    pageQueryUtil: SocialuniPageQueryUtil<TalkVO, TalkQueryVO> = new SocialuniPageQueryUtil(SocialuniTalkAPI.queryTalksAPI)


    initService(instance: Vue, params: any = {}) {
        super.initService(instance, params);

        console.log(9999)
        console.log(instance)
        console.log(instance.$route)
        console.log(instance.$route.query.tab)
        console.log(instance.$router)
        console.log(888)


        console.log(9999)
        console.log(this.instance)
        console.log(this.instance.$route)
        console.log(this.instance.$route.query.tab)

        console.log(this.instance.$router)
        console.log(888)

        console.log(9999)
        console.log(this)
        console.log(this.$route)
        console.log(this.$route.query.tab)
        console.log(this.$router)
        console.log(888)


        console.log('chufa11111')
        this.pageQueryUtil = new SocialuniPageQueryUtil(SocialuniTalkAPI.queryTalksAPI)
        console.log('22232323555')
        // 获取元素高度，用来计算scroll-view高度
        // this.$refs.tabsTalk.initQuery()
        console.log(98989)
        nextTick(() => {
            console.log(12312)
            console.log(989898)
            //首次打开talk页面，获取用户位置用来查询
            // locationModule.appLunchInitDistrict().then(() => {
            //首次打开talk页面，获取用户位置用来查询
            socialLocationModule.appLunchInitDistrict().then(() => {
                this.initQuery()
            })
        })
        socialCircleModule.getHotCirclesAction()
        socialuniTagModule.getHotTagsAction()
    }

    async initQuery() {
        console.log('fasdflkasjdlf')
        console.log(this.tabName)
        const talkQO = TalkQOFactory.getTalkQueryQO(socialTalkModule.curTabName, socialTalkModule.userGender, socialTalkModule.userMinAge, socialTalkModule.userMaxAge, socialuniTagModule.selectTagNames, socialCircleModule.circleName)
        console.log(this.pageQueryUtil)
        console.log(6866868)
        console.log(this.pageQueryUtil.queryQO)
        await this.pageQueryUtil.initQuery(talkQO)
    }

    async queryNextPage() {
        const talkQO = TalkQOFactory.getTalkQueryQO(socialTalkModule.curTabName, socialTalkModule.userGender, socialTalkModule.userMinAge, socialTalkModule.userMaxAge, socialuniTagModule.selectTagNames, socialCircleModule.circleName)
        await this.pageQueryUtil.loadNextPage(talkQO)
    }

}
