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
import {nextTick} from "vue";
import QingAppUtil from "qingjs/src/util/QingAppUtil";
import Constants from "socialuni-constant/constant/Constant";
import CommonUtil from "qing-util/src/util/CommonUtil";
import {socialuniPluginsModule} from "socialuni/src/store/SocialuniPluginsModule";

export default class SocialuniTalkListViewService extends SocialuniViewService<SocialuniLoginViewServiceRefs> {

    pageQueryUtil: SocialuniPageQueryUtil<TalkVO, TalkQueryVO> = new SocialuniPageQueryUtil(SocialuniTalkAPI.queryTalksAPI)


    tabName = '首页'
    initService(instance: Vue, params: any = {}) {
        super.initService(instance, params);
        console.log('chufa11111')
        this.pageQueryUtil = new SocialuniPageQueryUtil(SocialuniTalkAPI.queryTalksAPI)
        console.log('chufa12342342311')
        this.tabName = this.$route.query.tab as string
        console.log('chuf666666')
        console.log(this.$route.query)
        console.log(this.$route.query.tab)
        console.log(this.tabName)
        if (this.$route.query.circle) {
            socialCircleModule.setCircleName(this.$route.query.circle as string)
        }
        console.log('111111')
        if (!this.tabName) {
            if (!socialCircleModule.circleName) {
                this.$router.push('/community?tab=' + '首页')
                return
            }
            this.tabName = '首页'
        }
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
        const talkQO = TalkQOFactory.getTalkQueryQO(this.tabName, socialTalkModule.userGender, socialTalkModule.userMinAge, socialTalkModule.userMaxAge, socialuniTagModule.selectTagNames, socialCircleModule.circleName)
        console.log(this.pageQueryUtil)
        console.log(6866868)
        console.log(this.pageQueryUtil.queryQO)
        await this.pageQueryUtil.initQuery(talkQO)
    }

    async queryNextPage() {
        const talkQO = TalkQOFactory.getTalkQueryQO(this.tabName, socialTalkModule.userGender, socialTalkModule.userMinAge, socialTalkModule.userMaxAge, socialuniTagModule.selectTagNames, socialCircleModule.circleName)
        await this.pageQueryUtil.loadNextPage(talkQO)
    }

}
