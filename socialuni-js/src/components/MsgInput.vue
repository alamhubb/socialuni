<template>
  <view v-if="inputContentFocus"
        class="cu-bar input fixed-footer elevation-4 flex-row"
  >
    <input
      v-model.trim="content"
      :adjust-position="true"
      class="solid-bottom text-black"
      confirm-type="send"
      :focus="inputContentFocusInput"
      maxlength="300"
      :cursor-spacing="8"
      :placeholder="msgInputPlaceholder"
      @blur="inputContentBlur"
    />
    <!--<u-field
        trim
        v-model="content"
        :adjust-position="true"
        class="solid-bottom flex-auto"
        confirm-type="send"
        :focus="inputContentFocus"
        maxlength="300"
        label-width="0"
        :cursor-spacing="8"
        :placeholder="msgInputPlaceholder"
        @blur="inputContentBlur"
        :clearable="false"
    ></u-field>-->
    <!--<view class="action">
        <text class="cuIcon-emojifill text-grey"></text>
    </view>-->
    <button class="cu-btn bg-cyan flex-none" @click="sendComment" :disabled="!content">发送</button>
  </view>
</template>

<script lang="ts">
import { Component, Vue, Watch } from 'vue-property-decorator'
import { socialTalkModule, socialTalkStore, socialUserStore } from '../socialuni/store'
import PlatformUtils from '../socialuni/utils/PlatformUtils'
import CenterUserDetailRO from '../socialuni/model/social/CenterUserDetailRO'
import MsgUtil from '../socialuni/utils/MsgUtil'
import ToastUtil from '../socialuni/utils/ToastUtil'

@Component
export default class MsgInput extends Vue {
  content = ''
  @socialTalkStore.State('currentContent') currentContent: string
  @socialUserStore.State('user') user: CenterUserDetailRO
  inputContentFocusInput = false

  get inputContentFocus(){
    return socialTalkModule.inputContentFocus
  }

  @Watch('inputContentFocus')
  inputContentFocusWatch () {
    setTimeout(() => {
      this.inputContentFocusInput = this.inputContentFocus
      console.log(this.inputContentFocusInput)
    }, 0)
  }

  get msgInputPlaceholder () {
    if (this.currentContent) {
      return '回复：' + this.currentContent
    } else {
      return ''
    }
  }

  // formid有坑，只能发送给自己openid
  sendComment () {
    // 登录才能发表评论
    if (this.user && this.user.phoneNum) {
      // 如果有值
      if (this.content) {
        /*if (!this.user.identityAuth) {
          ModelContentCheckUtil.hasUn18Content(this.content)
        }*/
        socialTalkModule.addComment({ content: this.content })
        this.content = ''
        // 申请订阅
        PlatformUtils.requestSubscribeComment()
      } else {
        ToastUtil.toast('不能发表内容为空的评论')
      }
    } else {
      MsgUtil.unBindPhoneNum()
    }
  }

  inputContentBlur () {
    socialTalkModule.inputContentBlur()
  }
}
</script>
