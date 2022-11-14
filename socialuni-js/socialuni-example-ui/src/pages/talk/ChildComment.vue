<template>
  <view v-if="comment && comment.childComments" class="pl-15">
    <block v-for="(childComment,index) in comment.childComments" :key="childComment.id">
      <view v-if="index<childCommentShowNum" class="flex-row py-mn">
        <!--            {{childComment.no}}#-->
        <text :class="comment.user.vipFlag?'text-red':'color-blue'" class="row-col-center"
              @click="toUserDetail(childComment.user.id)">{{ childComment.user.nickname }}
        </text>
        <view class="flex-sub row-col-center" @click="setReplyComment(talk,comment,childComment)">
          <text v-if="childComment.replyComment">
            <text class="mx-5">回复</text>
            <!--                {{childComment.replyComment.no}}#-->
            <text class="color-blue" @click.stop="toUserDetail(childComment.replyComment.user.id)">
              {{ childComment.replyComment.user.nickname }}
            </text>
          </text>
          ：
          <text selectable>{{ childComment.content }}</text>
        </view>
      </view>
    </block>
    <view v-show="comment.childCommentNum>childCommentShowNum || showOtherCommentClicked" class="pt-2">
      <view class="color-orange row-col-center" @click="toggleOtherComments">
        <view v-show="comment.childCommentNum>childCommentShowNum">
          查看其余{{ comment.childCommentNum - childCommentShowNum }}条回复
          <q-icon icon="arrow-down"></q-icon>
        </view>
        <view v-show="showOtherCommentClicked">
          收起回复
          <q-icon icon="arrow-up"></q-icon>
        </view>
      </view>
    </view>
  </view>
</template>

<script lang="ts">
import {Options, Prop, Vue, Watch} from 'vue-property-decorator'
import TalkVO from "socialuni-api/src/model/talk/TalkVO";
import QIcon from "socialuni-ui/src/components/QIcon/QIcon.vue";
import CommentVO from "socialuni-api/src/model/comment/CommentVO";
import ObjectUtil from "socialuni-use/src/utils/ObjectUtil";
import RouterUtil from "socialuni-use/src/utils/RouterUtil";
import PagePath from "socialuni-constant/constant/PagePath";
import PageUtil from "socialuni-user/src/utils/PageUtil";
import {socialTalkModule} from "socialuni-sdk/src/store/store";

@Options({
  components: {QIcon}
})
export default class ChildComment extends Vue {
  @Prop() readonly talk: TalkVO
  @Prop() readonly commentProp!: CommentVO
  comment: CommentVO = ObjectUtil.deepClone(this.commentProp)

  @Watch('commentProp', {deep: true})
  commentPropWatch() {
    this.comment = ObjectUtil.deepClone(this.commentProp)
  }

  childCommentLimitNum = 3
  showOtherCommentClicked = false

  toggleOtherComments() {
    if (this.showOtherCommentClicked) {
      this.childCommentLimitNum = 3
      this.showOtherCommentClicked = false
    } else {
      this.childCommentLimitNum = this.comment.childCommentNum
      this.showOtherCommentClicked = true
    }
  }

  toUserDetail(userId: string) {
    if (RouterUtil.getCurrentPageURI() !== PagePath.userDetail || RouterUtil.getCurrentPage().options.userId !== String(userId)) {
      PageUtil.toUserDetail(userId)
    }
  }

  get childCommentShowNum() {
    return this.comment.childCommentNum > this.childCommentLimitNum ? (this.childCommentLimitNum - 1) : this.childCommentLimitNum
  }

  setReplyComment(talk, comment, replyComment) {
    socialTalkModule.setReplyComment({talk, comment, replyComment})
  }

  promptDeleteContent() {
    /* if (this.$refs.msgInput.content) {
      this.$info('是否清空已输入内容').then(({result}) => {
        if (result) {
          this.$refs.msgInput.content = ''
        }
      })
    } */
  }
}
</script>
