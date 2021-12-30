<template>
  <view class="bg-default h100p flex-col">
    <view v-if="user">
      <q-navbar class="q-solid-bottom" custom-class="bg-default">
        <view class="ml-xl font-bold text-md flex-auto">
          个人资料
        </view>
        <view class="mr">
          <q-icon icon="setting" size="26" @click="showMoreListAction"></q-icon>
        </view>
      </q-navbar>
      <user-info :user="user"></user-info>

      <!-- #ifdef H5 -->
      <view class="h50 w100r"></view>
      <!-- #endif -->

      <u-popup v-model="showMoreList" mode="right" :border-radius="15">
        <view class="w65vw flex-col py-xl mt-xl h100p">
          <view class="q-box-row mt-xl font-bold text-lg">
            清池 app
          </view>
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
          <view class="q-box w100p row-grid">
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
          <view class="flex-auto col-end">
            <view class="row-center font-bold pb-xl text-lg w100r">
              <u-button size="medium" class="w30vw" @click="showMoreList = false">关闭</u-button>
            </view>
          </view>
        </view>
      </u-popup>
      <msg-input>
      </msg-input>
    </view>
    <!--      title="欢迎登录清池app"-->
    <social-login-page v-else class="h100p"></social-login-page>

    <!--    <u-popup v-model="showAuthThreeAuth" mode="bottom" :border-radius="30">
          <view v-if="user" class="pb-xl px-sm pt">
            <q-row class="font-bold">
              <view class="mr">
                集美小世界丨女孩生活分享交友
              </view>
              <view>申请</view>
            </q-row>

            <q-row class="font-md font-bold solid-bottom">
              获取您的昵称、头像、地区及性别
            </q-row>
            <view class="flex-row py-sm solid-bottom">
              <view class="w100r card-title pb-10">
                <image
                  class="card-title-avatar"
                  mode="aspectFill"
                  :src="user.avatar"
                />
                <view class="row-between flex-auto">
                  <view>
                    <view class="h25 row-col-center">
                      <text class="text-md">{{ user.nickname }}</text>
                    </view>
                    <view class="text-gray font-xs h25 row-col-center">
                      清池个人信息
                    </view>
                  </view>
                  &lt;!&ndash;                不为自己且未关注&ndash;&gt;
                  <view class="col-center">
                    <q-icon name="checkmark" color="success"></q-icon>
                  </view>
                </view>
              </view>
            </view>

            <q-row class="py-xl">
              <view class="row-center w100r pt-xl">
                <u-button class="mr-xl w30vw">取消</u-button>
                <u-button class="w30vw" type="success" @click="toThreeAuthUserInfo">允许</u-button>
              </view>
            </q-row>
          </view>
        </u-popup>-->
  </view>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
import TalkItem from '../SocialTalk/TalkItem.vue'
import CenterUserDetailRO from '../../../model/social/CenterUserDetailRO'
import TalkItemContent from '../SocialTalk/TalkItemContent.vue'
import UserEdit from './UserEdit.vue'
import UserInfo from './UserInfo.vue'
import UniUtil from '../../../utils/UniUtil'
import SkipUrlConst from '../../../const/SkipUrlConst'
import { socialAppModule, socialUserModule, socialUserStore } from '../../../store'
import QNavbar from '../../qing-ui/components/QNavbar/QNavbar.vue'
import QRowItem from '../../qing-ui/components/QRowItem/QRowItem.vue'
import OpenDataAPI from '../../../api/OpenDataAPI'
import Toast from '../../../utils/Toast'
import MsgInput from '../MsgInput.vue'
import QIcon from '../../qing-ui/components/QIcon/QIcon.vue'
import SocialLoginPage from '@/socialuni/components/social-ui/SocialPages/SocialLoginPage.vue'

@Component({
  components: {
    SocialLoginPage,
    QIcon,
    MsgInput,
    QRowItem,
    QNavbar,
    UserInfo,
    UserEdit,
    TalkItem,
    TalkItemContent
  }
})
export default class SocialMineDetail extends Vue {
  @socialUserStore.State('user') user: CenterUserDetailRO
  showMsgInput = false
  showMoreList = false
  // 登录
  disabledLoginBtn = false
  showAuthThreeAuth = false

  created () {
    UniUtil.showShareMenu()
    this.showAuthThreeAuth = !!socialAppModule.threeSecretKey
  }

  toThreeAuthUserInfo () {
    OpenDataAPI.authUserInfoAPI().then(res => {
      uni.navigateBackMiniProgram({
        extraData: {
          ...res.data
        }
      })
    })
  }

  onShow () {
    this.showMsgInput = true
  }

  onHide () {
    this.showMsgInput = false
  }

  destroyAccount () {
    this.hideMoreList()
    socialUserModule.destroyAccount().catch(() => {
      this.showMoreListAction()
    })
  }

  logout () {
    this.hideMoreList()
    socialUserModule.loginOut().catch(() => {
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
      socialUserModule.getMineUserAction().then(() => {
        Toast.toast('刷新成功')
      }).finally(() => {
        this.stopPullDownRefresh()
      })
    }
  }

  stopPullDownRefresh () {
    uni.stopPullDownRefresh()
  }
}
</script>
