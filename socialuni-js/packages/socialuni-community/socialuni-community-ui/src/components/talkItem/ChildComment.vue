<template>
  <div v-if="comment && comment.childComments" class="pl-15">
    <block v-for="(childComment,index) in comment.childComments" :key="childComment.id">
      <div v-if="index<childCommentShowNum" class="flex-row py-mn">
        <!--            {{childComment.no}}#-->
        <span :class="comment.user.vipFlag?'color-red':'color-blue'" class="row-col-center"
              @click="toUserDetail(childComment.user.id)">{{ childComment.user.nickname }}
        </span>
        <div class="flex-sub row-col-center" @click="setReplyComment(talk,comment,childComment)">
          <span v-if="childComment.replyComment">
            <span class="mx-5">回复</span>
            <!--                {{childComment.replyComment.no}}#-->
            <span class="color-blue" @click.stop="toUserDetail(childComment.replyComment.user.id)">
              {{ childComment.replyComment.user.nickname }}
            </span>
          </span>
          ：
          <span selectable>{{ childComment.content }}</span>
        </div>
      </div>
    </block>
    <div v-show="comment.childCommentNum>childCommentShowNum || showOtherCommentClicked" class="pt-2">
      <div class="color-orange row-col-center" @click="toggleOtherComments">
        <div v-show="comment.childCommentNum>childCommentShowNum">
          查看其余{{ comment.childCommentNum - childCommentShowNum }}条回复
          <q-icon icon="arrow-down"></q-icon>
        </div>
        <div v-show="showOtherCommentClicked">
          收起回复
          <q-icon icon="arrow-up"></q-icon>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import {Component, Prop, Vue, Watch, toNative} from 'vue-facing-decorator'
import TalkVO from "socialuni-api-base/src/model/talk/TalkVO";
import QIcon from "qingjs-ui/src/components/QIcon.vue";
import CommentVO from "socialuni-api-base/src/model/comment/CommentVO";
import ObjectUtil from "qing-util/src/util/ObjectUtil";
import QingAppUtil from "qing-compat-js/src/util/QingAppUtil";
import UserPagePath from "socialuni-user-sdk/src/constant/UserPagePath";
import UserPageUtil from "socialuni-user-sdk/src/util/UserPageUtil";
import {socialTalkModule} from "socialuni-community-sdk/src/store/SocialTalkModule";

@toNative
@Component({
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
    if (QingAppUtil.RouterUtil.getCurrentPageURI() !== UserPagePath.userDetail || QingAppUtil.RouterUtil.getCurrentPage().options.userId !== String(userId)) {
      UserPageUtil.toUserDetail(userId)
    }
  }

  get childCommentShowNum() {
    return this.comment && (this.comment.childCommentNum > this.childCommentLimitNum ? (this.childCommentLimitNum - 1) : this.childCommentLimitNum)
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
