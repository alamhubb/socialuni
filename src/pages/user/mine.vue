<template>
  <view class="bg-default h100r flex-col">
    <view class="flex-1 row-all-center">
      <button @click="showAuthThreeAuth=true">展示授权</button>
    </view>
<!--    <view v-if="user">
      <q-navbar class="q-solid-bottom">
        <view class="ml-xl text-bold text-lg flex-auto">
          个人资料
        </view>
        <view class="mr">
          <q-icon icon="setting" size="46" @click="showMoreListAction"></q-icon>
        </view>
      </q-navbar>
      <user-info :user="user"></user-info>

      &lt;!&ndash; #ifdef H5 &ndash;&gt;
      <view class="h50px w100r"></view>
      &lt;!&ndash; #endif &ndash;&gt;

      <u-popup v-model="showMoreList" mode="right" :border-radius="15">
        <view class="w65vw flex-col py-xl mt-xl h100r">
          <q-row>
            <view class="row-center mt-xl text-bold text-xl w100r">
              清池 app
            </view>
          </q-row>
          &lt;!&ndash;<q-row-item>
            <navigator :url="messageSettingUrl" class="row-col-center flex-auto">
              <view class="row-col-center flex-auto">
                消息设置
              </view>
              <q-icon icon="arrow-right" class="text-lg margin-right-sm"></q-icon>
            </navigator>
          </q-row-item>&ndash;&gt;
          <q-row-item>
            <navigator :url="suggestUrl" class="row-col-center flex-auto">
              <view class="row-col-center flex-auto">
                意见反馈
              </view>
              <q-icon icon="arrow-right" class="text-lg margin-right-sm"></q-icon>
            </navigator>
          </q-row-item>
          <q-row-item>
            <navigator :url="contactUsUrl" class="row-col-center flex-auto">
              <view class="row-col-center flex-auto">
                联系我们
              </view>
              <q-icon icon="arrow-right" class="text-lg margin-right-sm"></q-icon>
            </navigator>
          </q-row-item>
          <q-row-item>
            <navigator :url="homeUrl" class="row-col-center flex-auto">
              <view class="row-col-center flex-auto">
                关于我们
              </view>
              <q-icon icon="arrow-right" class="text-lg margin-right-sm"></q-icon>
            </navigator>
          </q-row-item>
          <q-row-item>
            <view class="row-col-center flex-auto" @click="destroyAccount">
              <view class="row-col-center flex-auto">
                注销账号
              </view>
              <q-icon icon="arrow-right" class="text-lg margin-right-sm"></q-icon>
            </view>
          </q-row-item>
          <q-row-item>
            <view class="row-col-center flex-auto" @click="logout">
              <view class="row-col-center flex-auto">
                退出登陆
              </view>
              <q-icon icon="arrow-right" class="text-lg margin-right-sm"></q-icon>
            </view>
          </q-row-item>
          <q-row>
            <view class="w100r row-grid">
              <navigator :url="userAgreementUrl" class="text-blue">
                《用户协议》
              </navigator>
              <navigator :url="userPrivacyUrl" class="text-blue">
                《隐私政策》
              </navigator>
              <navigator :url="childProtectUrl" class="text-blue">
                《儿童个人信息保护规则及监护人须知》
              </navigator>
            </view>
          </q-row>
          <q-row class="flex-auto col-end">
            <view class="row-center text-bold pb-xl text-xl w100r">
              <u-button size="medium" class="w30vw" @click="showMoreList=false">关闭</u-button>
            </view>
          </q-row>
        </view>
      </u-popup>
      <msg-input v-if="showMsgInput">
      </msg-input>
    </view>
    <view v-else class="h100r col-all-center bg-white">
      &lt;!&ndash;      title="欢迎登陆清池app"&ndash;&gt;
      <u-navbar :is-back="false" title-bold>
        <view class="flex-row w100vw flex-auto">
          <view class="ml-xl text-bold text-lg w100r">
            欢迎登陆清池app
          </view>
        </view>
      </u-navbar>
      &lt;!&ndash;  #ifndef MP &ndash;&gt;
      <login></login>
      &lt;!&ndash;  #endif &ndash;&gt;
      &lt;!&ndash; 小程序平台&ndash;&gt;
      &lt;!&ndash;  #ifdef MP &ndash;&gt;
      &lt;!&ndash; 小程序平台&ndash;&gt;
      <view class="text-black text-xl">未登录，点击登录按钮，进行登录操作</view>
      &lt;!&ndash;  #ifdef MP-TOUTIAO &ndash;&gt;
      &lt;!&ndash;            头条平台和其他平台处理方式不同&ndash;&gt;
      <button :disabled="disabledLoginBtn" @click="login" type="primary"
              class="v-btn mt-20px w70vw bg-green">
        登录
        <q-icon size="32" icon="account_box"></q-icon>
      </button>
      &lt;!&ndash;  #endif &ndash;&gt;
      &lt;!&ndash;  #ifndef MP-TOUTIAO &ndash;&gt;
      <button v-if="!user" :disabled="disabledLoginBtn"
              open-type="getUserInfo" @getuserinfo="login"
              class="cu-btn bg-cyan mt-20px w70vw">
        登录
        <q-icon size="32" icon="account_box"></q-icon>
      </button>
      &lt;!&ndash;  #endif &ndash;&gt;
      &lt;!&ndash;  #endif &ndash;&gt;
    </view>-->

    <u-popup v-model="showAuthThreeAuth" mode="bottom" :border-radius="30">
      <view class="h50vh pa">
        <view class="flex-row">
          <view class="mr">
            集美小世界丨女孩生活分享交友
          </view>
          <view>申请</view>
        </view>
        <view class="flex-row">
          获取您的昵称、头像、地区及性别
        </view>
        <view class="flex-row">
          获取您的昵称、头像、地区及性别
        </view>
      </view>
    </u-popup>
  </view>
</template>

<script lang="ts">
import { Vue, Component } from 'vue-property-decorator'
import TalkItem from '@/pages/talk/TalkItem.vue'
import UserVO from '@/model/user/UserVO'
import TalkItemContent from '@/pages/talk/TalkItemContent.vue'
import { namespace } from 'vuex-class'
import UserEdit from '@/pages/user/UserEdit.vue'
import UserInfo from '@/pages/user/UserInfo.vue'
import LoginService from '@/pages/user/LoginService'
import UniUtil from '@/utils/UniUtil'
import UserStore from '@/plugins/store/UserStore'
import CommonUtil from '@/utils/CommonUtil'
import SkipUrlConst from '@/const/SkipUrlConst'
import {appModule, systemModule} from '@/plugins/store'
import Constants from '@/const/Constant'
import QNavbar from '@/components/q-navbar/q-navbar.vue'
import PagePath from '@/const/PagePath'

const userStore = namespace('user')

@Component({
  components: { QNavbar, UserInfo, UserEdit, TalkItem, TalkItemContent }
})
export default class MineVue extends Vue {
  @userStore.State('user') user: UserVO
  showMsgInput = false
  showMoreList = false
  // 登录
  disabledLoginBtn = false
  showAuthThreeAuth = false

  onLoad () {
    CommonUtil.showShareMenu()
    this.showAuthThreeAuth = !!appModule.threeSecretKey
  }

  onShow () {
    this.showMsgInput = true
  }

  onHide () {
    this.showMsgInput = false
  }

  destroyAccount () {
    this.hideMoreList()
    UserStore.destroyAccount().catch(() => {
      this.showMoreListAction()
    })
  }

  logout () {
    this.hideMoreList()
    UserStore.loginOut().catch(() => {
      this.showMoreListAction()
    })
  }

  showMoreListAction () {
    this.showMoreList = true
  }

  hideMoreList () {
    this.showMoreList = false
  }

  onPullDownRefresh () {
    this.initQuery()
  }

  get homeUrl (): string {
    return SkipUrlConst.homeUrl()
  }

  get suggestUrl (): string {
    return SkipUrlConst.suggestUrl()
  }

  get contactUsUrl (): string {
    return SkipUrlConst.contactUsUrl()
  }

  get userAgreementUrl (): string {
    return SkipUrlConst.userAgreementUrl()
  }

  get userPrivacyUrl (): string {
    return SkipUrlConst.userPrivacyUrl()
  }

  get childProtectUrl (): string {
    return SkipUrlConst.childProtectUrl()
  }

  // 初始查询，会清空已有talk
  initQuery () {
    if (this.user) {
      UserStore.getMineUserAction().then(() => {
        UniUtil.toast('刷新成功')
      }).finally(() => {
        this.stopPullDownRefresh()
      })
    }
  }

  stopPullDownRefresh () {
    uni.stopPullDownRefresh()
  }

  login (result) {
    // #ifdef MP
    // #ifndef MP-TOUTIAO
    if (result.detail.errMsg !== Constants.loginSuccess) {
      UniUtil.toast('您取消了登录')
      return
    }
    // #endif
    this.disabledLoginBtn = true
    LoginService.platformLogin(systemModule.provider).finally(() => {
      this.disabledLoginBtn = false
    })
    // #endif
  }
}
</script>
<style lang="scss" scoped>
.user-setting {
  right: 18px;
  /*  #ifdef  MP  */
  top: 70px;
  /*  #endif  */
  /*  #ifndef  MP  */
  top: 10px;
  /*  #endif  */
}
</style>
