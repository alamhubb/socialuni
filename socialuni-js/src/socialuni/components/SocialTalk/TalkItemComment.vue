<template>
  <view>
    <view class="card-actions pt-10px row-between">
      <view class="row-end flex-auto">
        <view class="row-col-center button-icon" @click="setTalk" hover-class="uni-list-cell-hover">
          <q-icon icon="mdi-comment-outline" size="24">
          </q-icon>
          <text v-if="talk.commentNum" class="ml-5px">
            {{talk.commentNum}}
          </text>
        </view>
      </view>
      <view class="row-end flex-auto">
        <!--                hover-class="uni-list-cell-hover"-->
        <view class="row-all-center">
          <view @click="addHug" class="text-df line-height-1 row-all-center px-0 border-none">
            <text>
              抱抱
            </text>
            <q-icon class="ml-5px" size="24"
                    :icon="getHugIcon(talk.hasHugged)"
                    :class="[getHugColor(talk.hasHugged)]"
            ></q-icon>
            <text v-if="talk.hugNum" class="ml-5px">
              {{talk.hugNum}}
            </text>
          </view>
        </view>
        <view class="ml-30px button-icon row-col-center" @click="openReportDialog"
              hover-class="uni-list-cell-hover">
          <q-icon icon="more-dot-fill"></q-icon>
        </view>
      </view>
    </view>
    <view class="card-text">
      <view class="flex-auto" v-if="talk.comments">
        <block v-for="(comment,index) in talk.comments" :key="comment.id">
          <view v-if="index < commentShowNum">
            <!--                        {{comment.no}}#-->
            <view class="flex-row py-mn" @click="toTalkDetailVue">
              <view class="flex-none" :class="comment.user.vipFlag?'color-red':'text-theme'"
                    @click.stop="toUserDetail(comment.user.id)">
                {{comment.user.nickname}}
              </view>
              ：
              <view @longpress="openCommentActionDialog(comment)"
                    @click.stop="setComment(talk,comment)">
                {{comment.content}}
              </view>
            </view>
            <child-comment :talk="talk" :comment-prop="comment"></child-comment>
          </view>
        </block>
        <view v-show="!showAllComment && (talk.commentNum>commentShowNum || showOtherCommentClicked)">
          <view class="row-col-center color-orange pt-mn" @click="toTalkDetailVue">
            <view v-show="talk.commentNum>commentShowNum">
              查看其余{{talk.commentNum - commentShowNum}}条评论
            </view>
            <view v-show="false">
              收起评论
              <q-icon icon="arrow-up"></q-icon>
            </view>
          </view>
        </view>
      </view>
    </view>
  </view>
</template>

<script lang="ts">
import { Component, Prop, Vue, Watch } from 'vue-property-decorator'
import TalkVO from '@/socialuni/model/talk/TalkVO'
import PagePath from '@/socialuni/const/PagePath'
import TalkAPI from '@/socialuni/api/TalkAPI'
import ReportContentType from '@/socialuni/const/ReportContentType'
import HugAddVO from '@/socialuni/model/HugAddVO'
import ChildComment from './ChildComment.vue'
import CenterUserDetailRO from '@/socialuni/model/social/CenterUserDetailRO'
import CommentVO from '@/socialuni/model/comment/CommentVO'
import TalkUtil from '@/socialuni/utils/TalkUtil'
import JsonUtils from '@/socialuni/utils/JsonUtil'
import { socialTalkModule, socialUserStore } from '@/socialuni/store'
import MsgUtil from '@/socialuni/utils/MsgUtil'
import RouterUtil from '@/socialuni/utils/RouterUtil'
import QIcon from '@/socialuni/components/q-icon/q-icon.vue'
import Toast from '@/socialuni/utils/Toast'

@Component({
  components: {
    QIcon,
    ChildComment
  }
})
export default class TalkItemComment extends Vue {
  @socialUserStore.State('user') user: CenterUserDetailRO
  // 因为无法直接修改，所以需要克隆一下
  @Prop() readonly talkProp!: TalkVO
  @Prop({ type: Boolean, default: false }) readonly showAllComment: boolean

  talk: TalkVO = JsonUtils.deepClone(this.talkProp)

  @Watch('talkProp')
  talkPropWatch () {
    this.talk = JsonUtils.deepClone(this.talkProp)
  }

  // 打开更多操作评论弹框，复制删除，举报
  openCommentActionDialog (comment: CommentVO) {
    socialTalkModule.talk = this.talk
    socialTalkModule.comment = comment
    socialTalkModule.commentActionShow = true
  }

  // 打开举报talk弹框
  openReportDialog () {
    if (this.user) {
      socialTalkModule.talk = this.talk
      socialTalkModule.reportContentType = ReportContentType.talk
      socialTalkModule.reportDialogShow = true
    } else {
      MsgUtil.unLoginMessage()
    }
  }

  setTalk () {
    socialTalkModule.setTalk(this.talk)
  }

  addHug () {
    // 登录才可以点赞
    if (this.user) {
      if (this.talk.hasHugged) {
        Toast.toast('已抱过不能取消')
        return
      }
      this.talk.hasHugged = true
      this.talk.hugNum++
      const hugAdd: HugAddVO = new HugAddVO(this.talk.id)
      TalkAPI.addHugAPI(hugAdd)
    } else {
      MsgUtil.unLoginMessage()
    }
  }

  toUserDetail (userId: string) {
    if (RouterUtil.getCurrentPageURI() !== PagePath.userDetail || RouterUtil.getCurrentPage().options.userId !== String(userId)) {
      RouterUtil.navigateTo(PagePath.userDetail + '?userId=' + userId)
    }
  }

  showOtherCommentClicked = false

  toTalkDetailVue () {
    if (RouterUtil.getCurrentPageURI() !== PagePath.talkDetail) {
      RouterUtil.navigateTo(PagePath.talkDetail + '?talkId=' + this.talk.id)
    }
  }

  get commentLimitNum () {
    if (this.showAllComment || this.showOtherCommentClicked) {
      return this.talk.commentNum
    } else {
      return 4
    }
  }

  get commentShowNum () {
    return this.talk.commentNum > this.commentLimitNum ? (this.commentLimitNum - 1) : this.commentLimitNum
  }

  setComment (talk, comment) {
    socialTalkModule.setComment({ talk, comment })
  }

  getHugIcon (hasHugged) {
    return TalkUtil.getHugIcon(hasHugged)
  }

  getHugColor (hasHugged) {
    return TalkUtil.getHugColor(hasHugged)
  }
}
</script>
