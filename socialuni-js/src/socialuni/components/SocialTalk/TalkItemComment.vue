<template>
  <view>
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
    <view class="card-text">
      <view class="flex-1" v-if="talk.comments">
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
  </view>
</template>

<script lang="ts">
import { Component, Prop, Vue, Watch } from 'vue-property-decorator'
import TalkVO from '../../model/talk/TalkVO'
import PagePath from '../../constant/PagePath'
import TalkAPI from '../../api/TalkAPI'
import ReportContentType from '../../constant/ReportContentType'
import HugAddVO from '../../model/HugAddVO'
import ChildComment from './ChildComment.vue'
import CenterUserDetailRO from '../../model/social/CenterUserDetailRO'
import CommentVO from '../../model/comment/CommentVO'
import TalkUtil from '../../utils/TalkUtil'
import JsonUtils from '../../utils/ObjectUtil'
import { socialTalkModule, socialUserStore } from '../../store'
import MsgUtil from '../../utils/MsgUtil'
import RouterUtil from '../../utils/RouterUtil'
import QIcon from '../../../qing-ui/components/QIcon/QIcon.vue'
import QButton from '../../../qing-ui/components/QButton/QButton.vue'
import ToastUtil from '../../utils/ToastUtil'
import UniUtil from '@/socialuni/utils/UniUtil'

@Component({
  components: {
    QIcon,
    QButton,
    ChildComment
  }
})
export default class TalkItemComment extends Vue {
  @socialUserStore.State('user') user: CenterUserDetailRO
  // 因为无法直接修改，所以需要克隆一下
  @Prop() readonly talkProp!: TalkVO
  @Prop({
    type: Boolean,
    default: false
  }) readonly showAllComment: boolean

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
    console.log(123123)
    if (this.user) {
      socialTalkModule.talk = this.talk
      socialTalkModule.reportContentType = ReportContentType.talk
      socialTalkModule.reportDialogShow = true
    } else {
      MsgUtil.unLoginMessage()
    }
  }

  showShareMenu () {
    UniUtil.showShareMenu()
  }


  setTalk () {
    socialTalkModule.setTalk(this.talk)
  }

  addHug () {
    // 登录才可以点赞
    if (this.user) {
      if (this.talk.hasHugged) {
        ToastUtil.toast('已抱过不能取消')
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
    socialTalkModule.setComment({
      talk,
      comment
    })
  }

  getHugIcon (hasHugged) {
    return TalkUtil.getHugIcon(hasHugged)
  }

  getHugColor (hasHugged) {
    return TalkUtil.getHugColor(hasHugged)
  }
}
</script>
