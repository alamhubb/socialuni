<template>
  <div class="h100p overflow-auto pt-sm" v-infinite-scroll="scrollToLower" :infinite-scroll-immediate="false"
       :infinite-scroll-distance="200"
       :infinite-scroll-delay="200"
  >
    <div class="row-col-start">
      <!--      <q-nav-menu/>-->
      <div class="flex-1 row-end-start position-sticky top-0">
        <el-menu
            class="w200 bd-none bd-radius flex-none br socialuni-community-view-left-menu mr-sm">
          <q-enum-link v-for="tab in talkTabs" :to="'/community?tab='+tab.name">
            <el-menu-item :index="tab.name">
              <q-icon icon="mdi-send" class="color-content mr-xs mdi-rotate-315" size="12"></q-icon>
              {{ tab.name }}
            </el-menu-item>
          </q-enum-link>
          <q-enum-link v-for="circle in mineCirclesTop10" :to="'/community?circle='+circle">
            <el-menu-item :index="circle">
              <q-icon icon="mdi-send" class="color-content mr-xs mdi-rotate-315" size="12"></q-icon>
              {{ circle }}
            </el-menu-item>
          </q-enum-link>
        </el-menu>
      </div>
      <div class="flex-none w600">
        <div v-show="selectTagNames.length" class="bg-grey16 bd-radius mb-sm pd-sm position-sticky index-sm" style="top:-10px">
          <div v-show="selectTagNames.length" class="row-col-center">
            已选话题：
            <div v-for="tag in selectTagNames" class="color-blue mr-sm font-18 row-all-center">
              <q-tag show-close type="white" size="16" @delete="setTagName(null)">
                {{ tag }}
              </q-tag>
            </div>
          </div>
        </div>

        <q-load-more v-show="pageQueryUtil.queryQO.firstLoad" class="my-sm" :status="pageQueryUtil.loadMore"
                     @click="forceLoadNextPage"></q-load-more>

        <!--          <q-load-more></q-load-more>-->
        <!--          <q-icon icon="mdi-loading mdi-spin"></q-icon>-->
        <!--          不放上面是因为，头部距离问题，这样会无缝隙，那样padding会在上面，始终空白-->
        <div v-for="(talk,index) in talksNew" :key="talk.id">
          <talk-item :talk="talk"
                     :talk-tab-type="tabName"
                     @delete-talk="deleteTalk"
          />
        </div>

        <q-load-more v-show="!pageQueryUtil.queryQO.firstLoad" class="my-sm" :status="pageQueryUtil.loadMore" @click="forceLoadNextPage"></q-load-more>

        <msg-input class="w600"></msg-input>
      </div>
      <div class="flex-1 position-sticky top-0">
        <div class="w300 ml-sm">
          <div class="w300 bg-white bd-radius pd">
            <div>
              <div v-for="tag in hotTagsTop10">
                <div class="mt-sm color-blue_dark mr-sm font-18" @click="setTagName(tag.name)">
                  <span class="color-blue mr-nn">#</span>
                  {{ tag.name }}
                </div>
              </div>
            </div>
          </div>
          <div class="pd-sm font-18 col-all-center mt">
            <div><a href="https://beian.miit.gov.cn/" target="_blank">冀ICP备19028721号-7</a></div>
            <el-divider class="my-sm"/>

            <div>
              ©2023-2024 鹿森
            </div>


            <el-divider class="my-sm"/>


            <div>
              清池科技滦州市有限公司
            </div>


            <el-divider class="my-sm"/>
            <div> 客服QQ：
              <a target="_blank"
                 href="https://wpa.qq.com/msgrd?v=3&amp;uin=491369310&amp;site=qq&amp;menu=yes">
                491369310 </a>
            </div>
          </div>
        </div>
      </div>

      <q-dialog ref="talkAddDialog" confirm-text="发布" @confirm="addTalk">
        <talk-add-view ref="talkAddView"></talk-add-view>
      </q-dialog>

      <talk-operate @deleteTalk="deleteTalk"></talk-operate>

      <social-talk-filter-dialog ref="talkFilterDialog"
                                 @confirm="startPullDown"></social-talk-filter-dialog>
      <!--      <socialuni-comment-input-dialog ref="commentDialog"></socialuni-comment-input-dialog>-->
    </div>
  </div>
</template>

<script lang="ts">
import {Component, Vue, Watch, toNative} from 'vue-facing-decorator'
import QTabs from 'qing-ui/src/components/QTabs/QTabs.vue'
import QIcon from 'qing-ui/src/components/QIcon.vue'
import QLoading from 'qing-ui/src/components/QLoading.vue'
import QLoadMore from 'qing-ui/src/components/QLoadMore.vue'
import TalkTabVO from "socialuni-api-base/src/model/talk/SocialuniTalkTabRO";
import LoadMoreType from "socialuni-constant/constant/LoadMoreType";
import CommonUtil from "qing-util/src/util/CommonUtil";
import TalkVO from "socialuni-api-base/src/model/talk/TalkVO";
import Constants from "socialuni-constant/constant/Constant";
import SocialuniTalkTabCircleRO from "socialuni-api-base/src/model/community/circle/SocialuniTalkTabCircleRO";
import SocialuniTalkAPI from "socialuni-community-api/src/api/SocialuniTalkAPI";
import {socialTalkModule} from "socialuni-community-sdk/src/store/SocialTalkModule";
import {socialuniTagModule} from "socialuni-community-sdk/src/store/SocialTagModule";
import TalkQOFactory from "socialuni-community-sdk/src/factory/TalkQOFactory";
import {socialLocationModule} from "socialuni-community-sdk/src/store/SocialLocationModule";
import {socialuniSystemModule} from "qing-util/src/store/SocialuniSystemModule";
import {socialuniConfigModule} from "socialuni-app-sdk/src/store/SocialuniConfigModule";
import UserMsgUtil from "socialuni-user-sdk/src/util/UserMsgUtil";
import UserPageUtil from "socialuni-user-sdk/src/util/UserPageUtil";
import QingAppUtil from "qingjs/src/util/QingAppUtil";
import {socialuniUserModule} from "socialuni-user-sdk/src/store/SocialuniUserModule";
import TalkItem from "socialuni-community-ui/src/components/talkItem/TalkItem.vue";
import SocialuniPageQueryUtil from "socialuni-api-base/src/model/common/SocialuniPageQueryUtil";
import SocialuniUserExtendDetailRO from "socialuni-expand-api/src/model/SocialuniUserExtendDetailRO";
import SocialuniUserExtendFriendQueryQO from "socialuni-api-base/src/model/user/SocialuniUserExtendFriendQueryQO";
import SocialuniExpandAPI from "socialuni-expand-api/src/api/SocialuniExpandAPI";
import TalkQueryVO from "socialuni-api-base/src/model/talk/TalkQueryVO";
import {socialCircleModule} from "socialuni-community-sdk/src/store/SocialCircleModule";
import TalkTabType from "socialuni-constant/constant/TalkTabType";
import QEnumLink from 'qing-ui-h5/src/components/QEnumLink.vue'
import CommonEventUtil from "qingjs/src/util/CommonEventUtil";
import SocialuniImEventKey from "socialuni-im-api/src/constant/SocialuniMusicEventConst";
import JsonUtil from "qing-util/src/util/JsonUtil";
import QNavMenu from "qing-ui-h5/src/components/QNavMenu.vue";
import CommunityEventConst from "socialuni-community-sdk/src/constant/CommunityEventConst";
import SocialuniCommentInputDialog from "./SocialuniCommentInputDialog.vue";
import MsgInput from "socialuni-ui/src/components/MsgInput.vue";
import QDialog from "qing-ui-h5/src/components/QDialog.vue";
import TalkAddView from "./TalkAddView.vue";
import SocialuniTalkListViewService from "socialuni-community-sdk/src/logic/service/SocialuniTalkListViewService";
import {getCurrentInstance, nextTick, watch} from "vue";
import QTag from "qing-ui/src/components/QTag.vue";


// todo 后台可控制是否显示轮播图
@toNative
@Component({
  components: {
    TalkAddView,
    QDialog,
    SocialuniCommentInputDialog,
    QNavMenu,
    QLoading,
    QLoadMore,
    QIcon,
    QTag,
    MsgInput,
    QTabs,
    QEnumLink,
    TalkItem
  }
})
export default class SocialuniTalkViewH5 extends Vue {
  $refs: {
    commentDialog: SocialuniCommentInputDialog
    talkAddDialog: QDialog
    talkAddView: TalkAddView
  }


  pageQueryUtil: SocialuniPageQueryUtil<TalkVO, TalkQueryVO> = new SocialuniPageQueryUtil(SocialuniTalkAPI.queryTalksAPI)

  async initQuery() {
    const talkQO = TalkQOFactory.getTalkQueryQO(socialTalkModule.curTabName, socialTalkModule.userGender, socialTalkModule.userMinAge, socialTalkModule.userMaxAge, socialuniTagModule.selectTagNames, socialCircleModule.circleName)
    await this.pageQueryUtil.initQuery(talkQO)
  }

  get selectTagNames() {
    return socialuniTagModule.selectTagNames
  }


  // 用户登录后重新查询
  @Watch('user')
  watchUserChange() {
    this.initQuery()
  }

  get talkTabs() {
    return socialTalkModule.talkTabs
  }


  get mineCirclesTop10() {
    console.log(socialCircleModule.mineCirclesTop10)
    return socialCircleModule.mineCirclesTop10
  }

  get hotTagsTop10() {
    return socialuniTagModule.hotTags
  }

  get talksNew() {
    return this.pageQueryUtil.listData
  }

  @Watch('$route.query')
  routerQueryWatch(newVal, oldVal) {
    if (JsonUtil.toJson(newVal) !== JsonUtil.toJson(oldVal)) {
      this.initQuery()
    }
  }

  setTabName() {
    socialTalkModule.curTabName = this.$route.query.tab as string
    if (this.$route.query.circle) {
      socialCircleModule.setCircleName(this.$route.query.circle as string)
    }
    console.log('111111')
    if (!socialTalkModule.curTabName) {
      if (!socialCircleModule.circleName) {
        console.log('chufale tiaozhuan')
        // window.open('/community?tab=首页', '_self')
        this.$router.push('/?tab=' + '首页')
        return
      }
      socialTalkModule.curTabName = '首页'
    }
  }

  mounted() {
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

    watch(() => this.$route.query, (newVal, oldVal) => {
      if (JsonUtil.toJson(newVal) !== JsonUtil.toJson(oldVal)) {
        console.log('chufale query')
        console.log(this)
        console.log(this.$route)
        console.log(this.$route.query.tab)
        console.log(this.$router)
        this.setTabName()
        this.initQuery()
      }
    })

    CommonEventUtil.on(CommunityEventConst.socialuniTalkAddEvent, () => {
      console.log('chufale jianting')
      this.$refs.talkAddDialog.open()
    })
  }

  async addTalk() {
    await this.$refs.talkAddView.addTalk()
    this.$refs.talkAddDialog.close()
    this.initQuery()
  }

  async scrollToLower() {
    const talkQO = TalkQOFactory.getTalkQueryQO(socialTalkModule.curTabName, socialTalkModule.userGender, socialTalkModule.userMinAge, socialTalkModule.userMaxAge, socialuniTagModule.selectTagNames, socialCircleModule.circleName)
    await this.pageQueryUtil.loadNextPage(talkQO)
  }


  get user() {
    return socialuniUserModule.mineUser
  }

  openTalkFilterDialog() {
    this.$refs.talkFilterDialog.open()
  }

  forceLoadNextPage() {
    const talkQO = TalkQOFactory.getTalkQueryQO(socialTalkModule.curTabName, socialTalkModule.userGender, socialTalkModule.userMinAge, socialTalkModule.userMaxAge, socialuniTagModule.selectTagNames, socialCircleModule.circleName)
    this.pageQueryUtil.forceLoadNextPage(talkQO)
  }

  // 被举报后前台删除talk
  deleteTalk(talkId: string) {
    this.pageQueryUtil.listData.splice(this.talks.findIndex(talk => talk.id === talkId), 1)
  }

  toLoginVue() {
    UserPageUtil.toMinePage()
  }

  joinCircleGroupChat(circle: SocialuniTalkTabCircleRO) {
    // 登录才可以点赞
    if (this.user) {
      // socialChatModule.joinCircleGroupChat(circle)
    } else {
      UserMsgUtil.unLoginMessage()
    }
  }

  setTagName(selectTagName) {
    socialuniTagModule.setSelectTagName(selectTagName)
    this.initQuery()
  }
}
</script>
<style>
.socialuni-community-view-left-menu .el-menu-item {
  font-size: 18px;
}
</style>
