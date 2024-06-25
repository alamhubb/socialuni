<template>
  <div v-if="talk">
    <div class="px-15 pt-10 row-between-center">
      <div class="row-around-center flex-1">
        <div class="row-col-center" @click="showShareMenu">
          <q-button no-debounce text open-type="share">
            <q-icon icon="share-square" size="26" add-class="color-black">
            </q-icon>
            <span class="ml-mn text-df">
              分享
            </span>
          </q-button>
        </div>
        <div class="row-col-center" @click="setTalk">
          <q-icon icon="mdi-comment-outline" size="22">
          </q-icon>
          <span v-if="talk.commentNum" class="ml-5">
            {{ talk.commentNum }}
          </span>
        </div>
        <div class="row-all-center">
          <div @click="addHug" class="text-df line-height-1 row-all-center px-0 border-none">
            <q-icon v-if="talk.hasHugged" size="24"
                    icon="heart-fill"
                    class="color-red3"
            ></q-icon>
            <q-icon v-else size="24"
                    icon="heart"
            ></q-icon>
            <span class="ml-mn">
              抱抱
            </span>
            <span v-if="talk.hugNum" class="ml-5">
              {{ talk.hugNum }}
            </span>
          </div>
        </div>
      </div>
      <!--                hover-class="uni-list-cell-hover"-->

      <div v-if="!talk.globalTop" class="ml-sm flex-none button-icon row-col-center" @click="openReportDialog">
        <q-icon icon="more-dot-fill"></q-icon>
      </div>
    </div>
    <div class="px-15 mt-xs" v-if="talk.comments">
      <block v-for="(comment,index) in talk.comments" :key="comment.id">
        <div v-if="index < commentShowNum">
          <!--                        {{comment.no}}#-->
          <div class="flex-row py-mn" @click="toTalkDetailVue">
            <div class="flex-none" :class="comment.user.vipFlag?'color-red':'color-blue'"
                  @click.stop="toUserDetail(comment.user.id)">
              {{ comment.user.nickname }}
            </div>
            ：
            <div @longpress="openCommentActionDialog(comment)"
                  @click.stop="setComment(talk,comment)">
              {{ comment.content }}
            </div>
          </div>
          <child-comment :talk="talk" :comment-prop="comment"></child-comment>
        </div>
      </block>
      <div v-show="!showAllComment && (talk.commentNum>commentShowNum || showOtherCommentClicked)">
        <div class="row-col-center color-orange pt-mn" @click="toTalkDetailVue">
          <div v-show="talk.commentNum>commentShowNum">
            查看其余{{ talk.commentNum - commentShowNum }}条评论
          </div>
          <div v-show="false">
            收起评论
            <q-icon icon="arrow-up"></q-icon>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import {Component, Prop, Vue, Watch, toNative} from 'vue-facing-decorator'
import QIcon from "qingjs-ui/src/components/QIcon.vue";
import QButton from "qingjs-ui/src/components/QButton.vue";
import TalkVO from "socialuni-api-base/src/model/talk/TalkVO";
import ObjectUtil from "qing-util/src/util/ObjectUtil";
import ReportContentType from "socialuni-constant/constant/ReportContentType";
import MsgUtil from "socialuni-app-sdk/src/util/MsgUtil";
import CommentVO from "socialuni-api-base/src/model/comment/CommentVO";
import HugAddVO from "socialuni-api-base/src/model/HugAddVO";
import SocialuniTalkAPI from "socialuni-community-api/src/api/SocialuniTalkAPI";
import ChildComment from "./ChildComment.vue";
import UserMsgUtil from "socialuni-user-sdk/src/util/UserMsgUtil";
import QingAppUtil from "qing-compat-js/src/util/QingAppUtil";
import UserPageUtil from "socialuni-user-sdk/src/util/UserPageUtil";
import UserPagePath from "socialuni-user-sdk/src/constant/UserPagePath";
import {socialuniUserModule} from "socialuni-user-sdk/src/store/SocialuniUserModule";
import {socialTalkModule} from "socialuni-community-sdk/src/store/SocialTalkModule";
import CommunityPageUtil from "socialuni-community-sdk/src/util/CommunityPageUtil";
import CommunityPagePath from "socialuni-community-sdk/src/constant/CommunityPagePath";

@toNative
@Component({
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
    QingAppUtil.NativeUtil.showShareMenu()
  }


  setTalk() {
    socialTalkModule.setTalk(this.talk)
  }

  addHug() {
    // 登录才可以点赞
    if (this.user) {
      if (this.talk.hasHugged) {
          QingAppUtil.ToastUtil.toast('已抱过不能取消')
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
    if (QingAppUtil.RouterUtil.getCurrentPageURI() !== UserPagePath.userDetail || QingAppUtil.RouterUtil.getCurrentPage().options.userId !== String(userId)) {
      UserPageUtil.toUserDetail(userId)
    }
  }

  showOtherCommentClicked = false

  toTalkDetailVue() {
    if (QingAppUtil.RouterUtil.getCurrentPageURI() !== CommunityPagePath.talkDetail) {
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
