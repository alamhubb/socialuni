<template>
  <view class="h100p">
    <view v-if="mineUser" class="bg-default h100p flex-col">
      <div class="bg-theme-gradual pd-smm">
        <div class="row-end-center">
          <uni-icons type="compose" size="24" class="use-click" @click="toEditUserInfo"></uni-icons>
          <q-icon class="ml" icon="setting" size="20" @click="showMoreListAction"></q-icon>
        </div>
        <view class="row-col-center mt">
          <image
            class="size65 bd-round bd-3 bd-white mr-sm"
            mode="aspectFill"
            :src="mineUser.avatar"
            @click="seeAvatarDetail"
            @longpress="moreAction"
          />
          <view class="flex-1 row-between">
            <view class="flex-col flex-1">
              <view class="text-md font-bold" :class="{'color-red':mineUser.vipFlag}">
                {{ mineUser.nickname }}
              </view>
              <view class="flex-row">
                <social-gender-tag class="mt-sm" :user="mineUser"></social-gender-tag>
                <!--                <view v-if="userProp.vipFlag" class="cu-tag bg-red radius" @click="openVip">VIP</view>
                                <view v-else class="cu-tag bg-grey radius" @click="openVip">VIP</view>-->
              </view>
            </view>

            <view class="row-col-center font-12 use-click" @click="toMineUserDetailPage">
              <!--          <q-button @click="toEditUserInfo" theme light round>-->
              <div class="mt-1">空间</div>
              <q-icon icon="arrow-right" class="ml-nn" size="12"></q-icon>
              <!--          </q-button>-->
            </view>
          </view>
        </view>

        <view class="row-between-center mt py-xs pr-xs">
          <view class="flex-row flex-1 row-around">
            <view class="px-lg line-height-1" @click.stop="toFollowVue(SocialuniFollowType.follow)">
              <text class="text-lg font-bold text-black row-center">
                {{ mineUser.followNum }}
              </text>
              <text class="text-sm text-gray">关注</text>
            </view>
            <view class="px-lg line-height-1" @click.stop="toFollowVue(SocialuniFollowType.fans)">
              <text class="text-lg font-bold text-black row-center">
                {{ mineUser.fansNum }}
              </text>
              <text class="text-sm text-gray">被关注</text>
            </view>
          </view>
        </view>

        <!--      <view class="mr">
                <q-icon icon="setting" size="26" @click="showMoreListAction"></q-icon>
              </view>-->


        <!--      <div class="h200">123</div>-->
      </div>

      <div class="bg-white px-sm bd-radius-10 elevation-4 mx-sm">
        <view class="row-col-center my">
          <q-icon icon="map-fill" class="color-sub mr-mn" size="12" />
          地区：{{ mineUser.city || '' }}
        </view>

        <view class="mb">
          <div class="row-col-center">
            <q-icon class="color-sub mr-xs" icon="mdi-cellphone-android" size="12" />
            手机号：
            <view v-if="mineUser.phoneNum" class="row-col-center">
              {{ mineUser.phoneNum }}
              <view class="ml-10 sm cu-tag bg-white bd-gray radius">
                已绑定
              </view>
            </view>
            <view v-else class="row-col-center">
              <button class="ml-xs q-tag bg-click"
                      @click="toPhonePage">绑定手机号
              </button>
            </view>
          </div>
        </view>

        <view class="row-col-center mb-smm">
          <q-icon class="color-sub mr-xs" icon="mdi-school" size="12" />
          学校名称：
          <div v-if="mineUser.schoolName" @click="openSetSchoolDialog">
            {{ mineUser.schoolName }}
          </div>
          <div v-else class="q-tag" @click="openSetSchoolDialog">设置大学名称</div>
        </view>
      </div>

      <div class="row-between-center use-click bg-white px-sm py-sm bd-radius-10 elevation-4 mx-sm mt-smm" @click="toCoinPage">
        <div class="row-col-center">
          <q-icon custom-prefix="uni-icons" icon="uniui-wallet-filled" class="color-orange"></q-icon>
          <div class="ml-xs">我的贝壳</div>
        </div>
        <q-icon icon="arrow-right" class="text-md margin-right-sm color-sub"></q-icon>
      </div>

      <socialuni-user-info-img class="mt-sm" :user="mineUser"></socialuni-user-info-img>

      <!--    动态-->

      <q-popup ref="moreActionList" bottom>
        <view class="flex-col h100p pb-50">
          <view class="row-all-center font-bold text-lg py-sm">
            清池 app
          </view>
          <!--<q-row-item>
            <navigator :url="messageSettingUrl" class="row-col-center flex-1">
              <view class="row-col-center flex-1">
                消息设置
              </view>
              <q-icon icon="arrow-right" class="font-md margin-right-sm"></q-icon>
            </navigator>
          </q-row-item>-->
          <navigator :url="suggestUrl" class="q-box-between bb bg-click">
            <view class="row-col-center flex-1">
              意见反馈
            </view>
            <q-icon icon="arrow-right" class="text-md margin-right-sm"></q-icon>
          </navigator>
          <navigator :url="contactUsUrl" class="q-box-between bb bg-click">
            <view class="row-col-center flex-1">
              联系我们
            </view>
            <q-icon icon="arrow-right" class="text-md margin-right-sm"></q-icon>
          </navigator>
          <navigator :url="homeUrl" class="q-box-between bb bg-click">
            <view class="row-col-center flex-1">
              关于我们
            </view>
            <q-icon icon="arrow-right" class="text-md margin-right-sm"></q-icon>
          </navigator>
          <q-row-item @click="destroyAccount">
            <view class="row-col-center flex-1">
              注销账号
            </view>
            <q-icon icon="arrow-right" class="text-md margin-right-sm"></q-icon>
          </q-row-item>
          <q-row-item @click="logout">
            <view class="row-col-center flex-1">
              退出登录
            </view>
            <q-icon icon="arrow-right" class="text-md margin-right-sm"></q-icon>
          </q-row-item>
          <view class="px-smm py-sm w100p row-wrap">
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
        </view>
      </q-popup>

      <user-school-edit-dialog ref="schoolEditDialog"></user-school-edit-dialog>
      <msg-input>
      </msg-input>
    </view>

    <!--      title="欢迎登录清池app"-->
    <login-view v-else class="h100p"></login-view>
  </view>
</template>

<script lang="ts">
import { Options, Prop, Vue } from 'vue-property-decorator'
import QPopup from 'socialuni-view/src/components/QPopup/QPopup.vue'
import QIcon from 'socialuni-view/src/components/QIcon/QIcon.vue'
import QNavbar from 'socialuni-view/src/components/QNavbar/QNavbar.vue'
import QRowItem from 'socialuni-view/src/components/QRowItem/QRowItem.vue'
import MsgInput from 'socialuni-view/src/components/MsgInput.vue'
import { socialChatModule, socialUserModule } from 'socialuni-sdk/src/store/store'
import UniUtil from 'socialuni-sdk/src/utils/UniUtil'
import SkipUrlConst from 'socialuni-constant/constant/SkipUrlConst'
import SocialuniFollowType from 'socialuni-constant/constant/user/SocialuniFollowType'
import ToastUtil from 'socialuni-sdk/src/utils/ToastUtil'
import { onHide, onLoad, onShow } from '@dcloudio/uni-app'
import LoginView from 'socialuni-view/src/views/login/LoginView.vue'
import UserInfo from 'socialuni-view/src/views/user/UserInfo.vue'
import UserService from 'socialuni-sdk/src/service/UserService'
import SocialuniUserInfoImg from './SocialuniUserInfoImg.vue'
import PageUtil from 'socialuni-sdk/src/utils/PageUtil'
import CosUtil from 'socialuni-sdk/src/utils/CosUtil'
import DomFile from 'socialuni-api/src/model/DomFile'
import TencentCosAPI from 'socialuni-api/src/api/TencentCosAPI'
import SocialuniMineUserAPI from 'socialuni-api/src/api/socialuni/SocialuniMineUserAPI'
import ImgAddQO from 'socialuni-api/src/model/user/ImgAddQO'
import QInput from '../../components/QInput/QInput.vue'
import TalkItem from '../talk/talkItem/TalkItem.vue'
import TalkOperate from '../talk/talkOperate.vue'
import QButton from '../../components/QButton/QButton.vue'
import UserSchoolEditDialog from './UserSchoolEditDialog.vue'
import QSearch from '../../components/QSearch/QSearch.vue'
import QPcModel from '../../components/QPcModel/QPcModel.vue'
import SocialGenderTag from '../../components/SocialGenderTag/SocialGenderTag.vue'
import RouterUtil from 'socialuni-sdk/src/utils/RouterUtil'
import PagePath from 'socialuni-constant/constant/PagePath'
import AlertUtil from 'socialuni-sdk/src/utils/AlertUtil'
import MsgUtil from 'socialuni-sdk/src/utils/MsgUtil'

@Options({
  components: {
    LoginView,
    QPopup,
    QIcon,
    QNavbar,
    QRowItem,
    UserInfo,
    SocialuniUserInfoImg,
    MsgInput,
    QInput,
    TalkItem,
    TalkOperate,
    QButton,
    UserSchoolEditDialog,
    QSearch,
    QPcModel,
    SocialGenderTag,
  }
})
export default class MineView extends Vue {
  $refs: {
    moreActionList: QPopup
    schoolEditDialog: UserSchoolEditDialog
  }

  get mineUser () {
    return socialUserModule.mineUser
  }

  SocialuniFollowType = SocialuniFollowType
  providerLogin = true
  showMsgInput = false
  showMoreList = false
  // 登录
  disabledLoginBtn = false

  created () {
    onLoad((params) => {
      UniUtil.showShareMenu()
    })
    onShow(() => {
      this.showMsgInput = true
      socialChatModule.computedChatsUnreadNumTotalAction()
    })
    onHide(() => {
      this.showMsgInput = false
    })
  }

  destroyAccount () {
    this.hideMoreList()
    socialUserModule.destroyAccount().catch(() => {
      this.showMoreListAction()
    })
  }

  logout () {
    this.hideMoreList()
    UserService.loginOut().catch(() => {
      this.showMoreListAction()
    })
  }

  showMoreListAction () {
    this.$refs.moreActionList.open()
  }

  hideMoreList () {
    this.$refs.moreActionList.close()
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
    if (this.mineUser) {
      socialUserModule.getMineUserAction().then(() => {
        ToastUtil.toast('刷新成功')
      }).finally(() => {
        this.stopPullDownRefresh()
      })
    }
  }

  stopPullDownRefresh () {
    uni.stopPullDownRefresh()
  }

  seeAvatarDetail () {
    uni.previewImage({
      current: 0,
      urls: [this.mineUser.avatar]
    })
  }

  moreAction () {
    const menuList: string [] = ['上传头像']
    UniUtil.actionSheet(menuList).then((index: number) => {
      if (index === 0) {
        this.uploadUserAvatarImg()
      }
    })
  }

  loginOut () {
    UserService.loginOut()
  }

  async uploadUserAvatarImg () {
    try {
      const cosAuthRO = await CosUtil.getCosAuthRO()
      const imgFiles: DomFile[] = await UniUtil.chooseImage(1)
      UniUtil.showLoading('上传中')
      const imgFile: DomFile = imgFiles[0]
      imgFile.src = cosAuthRO.uploadImgPath + 'img/' + imgFile.src
      const res = await Promise.all([TencentCosAPI.uploadFileAPI(imgFile, cosAuthRO), SocialuniMineUserAPI.addUserAvatarImgAPI(new ImgAddQO(imgFile))])
      socialUserModule.setUser(res[1].data)
    } catch (e) {
      console.error(e)
    } finally {
      UniUtil.hideLoading()
    }
  }

  toFollowVue (followType: string) {
    PageUtil.toUserFollowPage(followType)
  }

  toEditUserInfo () {
    PageUtil.toEditMineInfo()
  }

  toMineUserDetailPage () {
    PageUtil.toUserDetail(this.mineUser.id)
  }

  refreshMine () {
    AlertUtil.confirm('是否刷新用户信息').then(() => {
      socialUserModule.getMineUserAction().then(() => {
        ToastUtil.toast('刷新成功')
      })
    })
  }

  toFaceValuePage () {
    PageUtil.toFaceValuePage()
  }

  async toPhonePage () {
    await PageUtil.toPhonePage()
  }

  toLoveValuePage () {
    if (this.mineUser) {
      PageUtil.toLoveValuePage()
    } else {
      MsgUtil.unLoginMessage()
    }
  }

  openSetSchoolDialog () {
    this.$refs.schoolEditDialog.open()
  }

  toCoinPage(){
    PageUtil.toCoinPage()
  }

}
</script>
