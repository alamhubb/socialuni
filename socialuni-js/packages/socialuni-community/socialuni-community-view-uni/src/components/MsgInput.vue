<template>
    <view
            v-if="inputContentFocus"
            class="bg-white row-between-center pr-sm input fixed-footer elevation-4 flex-row" style="min-height: 50px"
    >
        <input
                v-model.trim="content"
                :adjust-position="true"
                class="solid-bottom text-black flex-1 mx-10"
                style="line-height: 32px;height: 32px;min-height: 32px;"
                confirm-type="send"
                :focus="inputContentFocusInput"
                maxlength="300"
                :cursor-spacing="8"
                :placeholder="msgInputPlaceholder"
                @blur="inputContentBlur"
        />
        <!--<u-field
            trimqing
            v-model="content"
            :adjust-position="true"
            class="solid-bottom flex-1"
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
import {Component, Vue, Watch} from 'vue-facing-decorator'
import MsgUtil from "socialuni-app-sdk/src/util/MsgUtil";
import {socialTalkModule} from "socialuni-community-sdk/src/store/SocialTalkModule";
import {socialuniUserModule} from "socialuni-user-sdk/src/store/SocialuniUserModule";
import QingAppUtil from "qingjs/src/util/QingAppUtil";
import PlatformUtils from "socialuni-user-sdk/src/util/PlatformUtils";
import UserMsgUtil from "socialuni-user-sdk/src/util/UserMsgUtil";

@Component({})
export default class MsgInput extends Vue {
    content = ''

    get currentContent() {
        return socialTalkModule.currentContent
    }

    get user() {
        return socialuniUserModule.mineUser
    }

    inputContentFocusInput = false

    get inputContentFocus() {
        return socialTalkModule.inputContentFocus
    }

    @Watch('inputContentFocus')
    inputContentFocusWatch() {
        setTimeout(() => {
            this.inputContentFocusInput = this.inputContentFocus
            console.log(this.inputContentFocusInput)
        }, 0)
    }

    get msgInputPlaceholder() {
        if (this.currentContent) {
            return '回复：' + this.currentContent
        } else {
            return ''
        }
    }

    // formid有坑，只能发送给自己openid
    sendComment() {
        // 登录才能发表评论
        if (this.user && this.user.phoneNum) {
            // 如果有值
            if (this.content) {
                /*if (!this.user.identityAuth) {
                  ModelContentCheckUtil.hasUn18Content(this.content)
                }*/
                socialTalkModule.addComment({content: this.content})
                this.content = ''
                // 申请订阅
                PlatformUtils.requestSubscribeComment()
            } else {
                QingAppUtil.ToastUtil.toast('不能发表内容为空的评论')
            }
        } else {
            UserMsgUtil.unBindPhoneNum()
        }
    }

    inputContentBlur() {
        socialTalkModule.inputContentBlur()
    }
}
</script>
