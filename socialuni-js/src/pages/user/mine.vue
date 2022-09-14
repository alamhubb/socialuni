<template>
  <view class="bg-default h100p flex-col">
    <view v-if="user">
      <!--      <view class="mr">
              <q-icon icon="setting" size="26" @click="showMoreListAction"></q-icon>
            </view>-->
      <user-info :user="user"></user-info>


      <q-popup v-model="showMoreList" bottom>
        <view class="w65vw flex-col py-xl mt-xl h100r">
          <div class="flex-row">
            <view class="row-center mt-xl font-bold text-lg w100r">
              清池 app
            </view>
          </div>
          <!--<q-row-item>
            <navigator :url="messageSettingUrl" class="row-col-center flex-auto">
              <view class="row-col-center flex-auto">
                消息设置
              </view>
              <q-icon icon="arrow-right" class="font-md margin-right-sm"></q-icon>
            </navigator>
          </q-row-item>-->
          <q-row-item>
            <navigator :url="suggestUrl" class="row-col-center flex-auto">
              <view class="row-col-center flex-auto">
                意见反馈
              </view>
              <q-icon icon="arrow-right" class="text-md margin-right-sm"></q-icon>
            </navigator>
          </q-row-item>
          <q-row-item>
            <navigator :url="contactUsUrl" class="row-col-center flex-auto">
              <view class="row-col-center flex-auto">
                联系我们
              </view>
              <q-icon icon="arrow-right" class="text-md margin-right-sm"></q-icon>
            </navigator>
          </q-row-item>
          <q-row-item>
            <navigator :url="homeUrl" class="row-col-center flex-auto">
              <view class="row-col-center flex-auto">
                关于我们
              </view>
              <q-icon icon="arrow-right" class="text-md margin-right-sm"></q-icon>
            </navigator>
          </q-row-item>
          <q-row-item>
            <view class="row-col-center flex-auto" @click="destroyAccount">
              <view class="row-col-center flex-auto">
                注销账号
              </view>
              <q-icon icon="arrow-right" class="text-md margin-right-sm"></q-icon>
            </view>
          </q-row-item>
          <q-row-item>
            <view class="row-col-center flex-auto" @click="logout">
              <view class="row-col-center flex-auto">
                退出登录
              </view>
              <q-icon icon="arrow-right" class="text-md margin-right-sm"></q-icon>
            </view>
          </q-row-item>
          <div class="flex-row">
            <view class="w100p row-grid">
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
          <div class="flex-row flex-auto col-end">
            <view class="row-center font-bold pb-xl text-lg w100r">
              <u-button size="medium" class="w30vw" @click="showMoreList=false">关闭</u-button>
            </view>
          </div>
        </view>
      </q-popup>
      <msg-input>
      </msg-input>
    </view>
    <!--      title="欢迎登录清池app"-->
    <login v-else class="h100p"></login>
  </view>
</template>

<script lang="ts">
import {Component, Vue} from 'vue-property-decorator'
import MsgInput from '@/components/MsgInput.vue'
import {socialUserModule, socialUserStore} from '@/socialuni/store'
import ToastUtil from '@/socialuni/utils/ToastUtil'
import SkipUrlConst from '@/socialuni/constant/SkipUrlConst'
import UniUtil from '@/socialuni/utils/UniUtil'
import CenterUserDetailRO from '@/socialuni/model/social/CenterUserDetailRO'
import UserInfo from '@/pages/user/UserInfo.vue'
import QRowItem from '@/qing-ui/components/QRowItem/QRowItem.vue'
import QNavbar from '@/qing-ui/components/QNavbar/QNavbar.vue'
import QIcon from '@/qing-ui/components/QIcon/QIcon.vue'
import QPopup from '@/qing-ui/components/QPopup/QPopup.vue'
import Login from "@/pages/login/login.vue";

@Component({
  components: {
    Login,
    QPopup,
    QIcon,
    QNavbar,
    QRowItem,
    UserInfo,
    MsgInput
  }
})
export default class QcMineDetail extends Vue {
  @socialUserStore.State('user') user: CenterUserDetailRO
  showMsgInput = false
  showMoreList = false
  // 登录
  disabledLoginBtn = false

  created() {
    UniUtil.showShareMenu()
  }

  onShow() {
    this.showMsgInput = true
  }

  onHide() {
    this.showMsgInput = false
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
    if (this.user) {
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
