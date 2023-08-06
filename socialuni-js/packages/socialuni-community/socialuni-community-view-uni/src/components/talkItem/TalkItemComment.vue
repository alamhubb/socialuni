<template>
  <view v-if="talk">
    <view class="px-15 pt-10 row-between-center">
      <view class="row-around-center flex-1">
        <view class="row-col-center" @click="showShareMenu">
          <q-button no-debounce text open-type="share">
            <q-icon icon="share-square" size="26" add-class="color-black">
            </q-icon>
            <text class="ml-mn text-df">
              分享
            </text>
          </q-button>
        </view>
        <view class="row-col-center" @click="setTalk">
          <q-icon icon="mdi-comment-outline" size="22">
          </q-icon>
          <text v-if="talk.commentNum" class="ml-5">
            {{ talk.commentNum }}
          </text>
        </view>
        <view class="row-all-center">
          <view @click="addHug" class="text-df line-height-1 row-all-center px-0 border-none">
            <q-icon v-if="talk.hasHugged" size="24"
                    icon="heart-fill"
                    class="color-red3"
            ></q-icon>
            <q-icon v-else size="24"
                    icon="heart"
            ></q-icon>
            <text class="ml-mn">
              抱抱
            </text>
            <text v-if="talk.hugNum" class="ml-5">
              {{ talk.hugNum }}
            </text>
          </view>
        </view>
      </view>
      <!--                hover-class="uni-list-cell-hover"-->

      <view v-if="!talk.globalTop" class="ml-sm flex-none button-icon row-col-center" @click="openReportDialog">
        <q-icon icon="more-dot-fill"></q-icon>
      </view>
    </view>
    <view class="px-15 mt-xs" v-if="talk.comments">
      <block v-for="(comment,index) in talk.comments" :key="comment.id">
        <view v-if="index < commentShowNum">
          <!--                        {{comment.no}}#-->
          <view class="flex-row py-mn" @click="toTalkDetailVue">
            <view class="flex-none" :class="comment.user.vipFlag?'color-red':'color-blue'"
                  @click.stop="toUserDetail(comment.user.id)">
              {{ comment.user.nickname }}
            </view>
            ：
            <view @longpress="openCommentActionDialog(comment)"
                  @click.stop="setComment(talk,comment)">
              {{ comment.content }}
            </view>
          </view>
          <child-comment :talk="talk" :comment-prop="comment"></child-comment>
        </view>
      </block>
      <view v-show="!showAllComment && (talk.commentNum>commentShowNum || showOtherCommentClicked)">
        <view class="row-col-center color-orange pt-mn" @click="toTalkDetailVue">
          <view v-show="talk.commentNum>commentShowNum">
            查看其余{{ talk.commentNum - commentShowNum }}条评论
          </view>
          <view v-show="false">
            收起评论
            <q-icon icon="arrow-up"></q-icon>
          </view>
        </view>
      </view>
    </view>
  </view>
</template>

<script lang="ts">
import {Options, Prop, Vue, Watch} from 'vue-property-decorator'
import QIcon from "@socialuni/socialuni-ui-uni/src/components/QIcon/QIcon.vue";
import QButton from "@socialuni/socialuni-ui-uni/src/components/QButton/QButton.vue";
import TalkVO from "@socialuni/socialuni-api-base/src/model/talk/TalkVO";
import ObjectUtil from "@socialuni/socialuni-util/src/util/ObjectUtil";
import ReportContentType from "@socialuni/socialuni-constant/constant/ReportContentType";
import MsgUtil from "@socialuni/socialuni-app-sdk/src/util/MsgUtil";
import CommentVO from "@socialuni/socialuni-api-base/src/model/comment/CommentVO";
import HugAddVO from "@socialuni/socialuni-api-base/src/model/HugAddVO";
import SocialuniTalkAPI from "@socialuni/socialuni-community-api/src/api/SocialuniTalkAPI";
import ChildComment from "./ChildComment.vue";
import UserMsgUtil from "@socialuni/socialuni-user-sdk/src/util/UserMsgUtil";
import SocialuniAppUtil from "@socialuni/socialuni-native-util/src/util/SocialuniAppUtil";
import UserPageUtil from "@socialuni/socialuni-user-sdk/src/util/UserPageUtil";
import UserPagePath from "@socialuni/socialuni-user-sdk/src/constant/UserPagePath";
import {socialuniUserModule} from "@socialuni/socialuni-user-sdk/src/store/SocialuniUserModule";
import {socialTalkModule} from "@socialuni/socialuni-community-sdk/src/store/SocialTalkModule";
import CommunityPageUtil from "@socialuni/socialuni-community-sdk/src/util/CommunityPageUtil";
import CommunityPagePath from "@socialuni/socialuni-community-sdk/src/constant/CommunityPagePath";

@Options({
  components: {
    QIcon,
    QButton,
    ChildComment
  }
})
export default class TalkItemComment extends Vue {
  get user() {
    return socialuniUserModule.mineUser
  }

  // 因为无法直接修改，所以需要克隆一下
  @Prop() readonly talkProp!: TalkVO
  @Prop({
    type: Boolean,
    default: false
  }) readonly showAllComment: boolean

  //列表变化，内容没更新的问题
  talk: TalkVO = null

  created() {
    this.updateTalk()
  }

  updateTalk() {
    this.talk = ObjectUtil.deepClone(this.talkProp)
  }

  @Watch('talkProp')
  talkPropWatch() {
    this.updateTalk()
  }

  // 打开更多操作评论弹框，复制删除，举报
  openCommentActionDialog(comment: CommentVO) {
    socialTalkModule.talk = this.talk
    socialTalkModule.comment = comment
    socialTalkModule.commentActionShow = true
  }

  // 打开举报talk弹框
  openReportDialog() {
    if (this.user) {
      socialTalkModule.talk = this.talk
      socialTalkModule.reportContentType = ReportContentType.talk
      socialTalkModule.reportDialogShow = true
    } else {
      UserMsgUtil.unLoginMessage()
    }
  }

  showShareMenu() {
    SocialuniAppUtil.UniUtil.showShareMenu()
  }


  setTalk() {
    socialTalkModule.setTalk(this.talk)
  }

  addHug() {
    // 登录才可以点赞
    if (this.user) {
      if (this.talk.hasHugged) {
          SocialuniAppUtil.ToastUtil.toast('已抱过不能取消')
        return
      }
      this.talk.hasHugged = true
      this.talk.hugNum++
      const hugAdd: HugAddVO = new HugAddVO(this.talk.id)
      SocialuniTalkAPI.addHugAPI(hugAdd)
    } else {
      UserMsgUtil.unLoginMessage()
    }
  }

  toUserDetail(userId: string) {
    if (SocialuniAppUtil.RouterUtil.getCurrentPageURI() !== UserPagePath.userDetail || SocialuniAppUtil.RouterUtil.getCurrentPage().options.userId !== String(userId)) {
      UserPageUtil.toUserDetail(userId)
    }
  }

  showOtherCommentClicked = false

  toTalkDetailVue() {
    if (SocialuniAppUtil.RouterUtil.getCurrentPageURI() !== CommunityPagePath.talkDetail) {
      CommunityPageUtil.toTalkDetail(this.talk.id)
    }
  }

  get commentLimitNum() {
    if (this.showAllComment || this.showOtherCommentClicked) {
      return this.talk.commentNum
    } else {
      return 4
    }
  }

  get commentShowNum() {
    return this.talk && this.talk.commentNum > this.commentLimitNum ? (this.commentLimitNum - 1) : this.commentLimitNum
  }

  setComment(talk, comment) {
    socialTalkModule.setComment({
      talk,
      comment
    })
  }

  getHugIcon(hasHugged) {
    // return TalkUtil.getHugIcon(hasHugged)
  }

  getHugColor(hasHugged) {
    // return TalkUtil.getHugColor(hasHugged)
  }
}
</script>
