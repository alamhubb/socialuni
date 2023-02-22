<template>
  <view class="bg-default h100p flex-col">
    <view v-if="mineUser" class="h100p">
      <!--      <view class="mr">
              <q-icon icon="setting" size="26" @click="showMoreListAction"></q-icon>
            </view>-->
      <user-info :user="mineUser">
        <template v-slot:list>


        </template>
      </user-info>

      <q-popup v-model="showMoreList" bottom>
        <view class="w65vw flex-col py-xl mt-xl h100p">
          <div class="flex-row">
            <view class="row-center mt-xl font-bold text-lg w100r">
              清池 app
            </view>
          </div>
          <!--<q-row-item>
            <navigator :url="messageSettingUrl" class="row-col-center flex-1">
              <view class="row-col-center flex-1">
                消息设置
              </view>
              <q-icon icon="arrow-right" class="font-md margin-right-sm"></q-icon>
            </navigator>
          </q-row-item>-->
          <q-row-item>
            <navigator :url="suggestUrl" class="row-col-center flex-1">
              <view class="row-col-center flex-1">
                意见反馈
              </view>
              <q-icon icon="arrow-right" class="text-md margin-right-sm"></q-icon>
            </navigator>
          </q-row-item>
          <q-row-item>
            <navigator :url="contactUsUrl" class="row-col-center flex-1">
              <view class="row-col-center flex-1">
                联系我们
              </view>
              <q-icon icon="arrow-right" class="text-md margin-right-sm"></q-icon>
            </navigator>
          </q-row-item>
          <q-row-item>
            <navigator :url="homeUrl" class="row-col-center flex-1">
              <view class="row-col-center flex-1">
                关于我们
              </view>
              <q-icon icon="arrow-right" class="text-md margin-right-sm"></q-icon>
            </navigator>
          </q-row-item>
          <q-row-item>
            <view class="row-col-center flex-1" @click="destroyAccount">
              <view class="row-col-center flex-1">
                注销账号
              </view>
              <q-icon icon="arrow-right" class="text-md margin-right-sm"></q-icon>
            </view>
          </q-row-item>
          <q-row-item>
            <view class="row-col-center flex-1" @click="logout">
              <view class="row-col-center flex-1">
                退出登录
              </view>
              <q-icon icon="arrow-right" class="text-md margin-right-sm"></q-icon>
            </view>
          </q-row-item>
          <div class="flex-row">
            <view class="w100p row-wrap">
              <navigator :url="userAgreementUrl" class="color-blue">
                《用户协议》
              </navigator>
              <navigator :url="userPrivacyUrl" class="color-blue">
                《隐私政策》
              </navigator>
              <navigator :url="childProtectUrl" class="color-blue">
                《儿童个人信息保护规则及监护人须知》
              </navigator>
            </view>
          </div>
          <div class="flex-row flex-1 col-end">
            <view class="row-center font-bold pb-xl text-lg w100r">
              <!--              <u-button size="medium" class="w30vw" @click="showMoreList=false">关闭</u-button>-->
            </view>
          </div>
        </view>
      </q-popup>
      <msg-input>
      </msg-input>
    </view>
    <!--      title="欢迎登录清池app"-->
    <login-view v-else class="h100p"></login-view>
  </view>
</template>

<script lang="ts">
import {Options, Prop, Vue} from 'vue-property-decorator'
import QPopup from "socialuni-view/src/components/QPopup/QPopup.vue";
import QIcon from "socialuni-view/src/components/QIcon/QIcon.vue";
import QNavbar from "socialuni-view/src/components/QNavbar/QNavbar.vue";
import QRowItem from "socialuni-view/src/components/QRowItem/QRowItem.vue";
import MsgInput from "socialuni-view/src/components/MsgInput.vue";
import {socialChatModule, socialUserModule} from 'socialuni-sdk/src/store/store';
import UniUtil from "socialuni-sdk/src/utils/UniUtil";
import SkipUrlConst from "socialuni-constant/constant/SkipUrlConst";
import ToastUtil from "socialuni-sdk/src/utils/ToastUtil";
import {onHide, onLoad, onShow} from "@dcloudio/uni-app";
import LoginView from "socialuni-view/src/views/login/LoginView.vue";
import UserInfo from "socialuni-view/src/views/user/UserInfo.vue";

@Options({
  components: {
    LoginView,
    QPopup,
    QIcon,
    QNavbar,
    QRowItem,
    UserInfo,
    MsgInput
  }
})
export default class MinePage extends Vue {

  get mineUser() {
    return socialUserModule.mineUser
  }
  providerLogin = true
  showMsgInput = false
  showMoreList = false
  // 登录
  disabledLoginBtn = false

  created() {
    onLoad((params) => {
      UniUtil.showShareMenu()
    })
    onShow(() => {
      this.showMsgInput = true
      socialChatModule.computedChatsUnreadNumTotalAction();
    })
    onHide(() => {
      this.showMsgInput = false
    })
  }

  destroyAccount() {
    this.hideMoreList()
    socialUserModule.destroyAccount().catch(() => {
      this.showMoreListAction()
    })
  }

  logout() {
    this.hideMoreList()
    socialUserModule.loginOut().catch(() => {
      this.showMoreListAction()
    })
  }

  showMoreListAction() {
    this.showMoreList = true
  }

  hideMoreList() {
    this.showMoreList = false
  }

  onPullDownRefresh() {
    this.initQuery()
  }

  get homeUrl(): string {
    return SkipUrlConst.homeUrl()
  }

  get suggestUrl(): string {
    return SkipUrlConst.suggestUrl()
  }

  get contactUsUrl(): string {
    return SkipUrlConst.contactUsUrl()
  }

  get userAgreementUrl(): string {
    return SkipUrlConst.userAgreementUrl()
  }

  get userPrivacyUrl(): string {
    return SkipUrlConst.userPrivacyUrl()
  }

  get childProtectUrl(): string {
    return SkipUrlConst.childProtectUrl()
  }

  // 初始查询，会清空已有talk
  initQuery() {
    if (this.mineUser) {
      socialUserModule.getMineUserAction().then(() => {
        ToastUtil.toast('刷新成功')
      }).finally(() => {
        this.stopPullDownRefresh()
      })
    }
  }

  stopPullDownRefresh() {
    uni.stopPullDownRefresh()
  }
}
</script>
