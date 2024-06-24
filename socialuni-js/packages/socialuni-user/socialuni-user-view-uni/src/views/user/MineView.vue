<template>
  <div class="h100p">
    <div v-if="mineUser" class="bg-default h100p flex-col">
      <div class="bg-theme-gradual px-smm pb-smm pt-100">
        <div class="row-end-center">
          <uni-icons type="compose" size="24" class="use-click" @click="toEditUserInfo"></uni-icons>
          <q-icon class="ml" icon="setting" size="20" @click="showMoreListAction"></q-icon>
        </div>
        <slot name="baseInfo" :mine="mineUser">
          <div class="row-between mt">
            <slot name="baseInfoLeft" :mine="mineUser">
              <div class="row-col-center">
                <img
                    class="size65 bd-round bd-3 bd-white mr-sm"
                    mode="aspectFill"
                    :src="mineUser.avatar"
                    @click="moreAction"
                />
                <div class="flex-col">
                  <div class="text-md font-bold" :class="{'color-red':mineUser.vipFlag}">
                    {{ mineUser.nickname }}
                  </div>
                  <div class="flex-row">
                    <social-gender-tag class="mt-sm" :user="mineUser" border></social-gender-tag>
                    <!--                <div v-if="userProp.vipFlag" class="cu-tag bg-red radius" @click="openVip">VIP</div>
                                    <div v-else class="cu-tag bg-grey radius" @click="openVip">VIP</div>-->
                  </div>
                </div>
              </div>
            </slot>
            <slot name="baseInfoRight" :mine="mineUser">
              <div class="row-col-center font-12 use-click" @click="toMineUserDetailPage">
                <!--          <q-button @click="toEditUserInfo" theme light round>-->
                <div class="mt-1">空间</div>
                <q-icon icon="arrow-right" class="ml-nn" size="12"></q-icon>
                <!--          </q-button>-->
              </div>
            </slot>
          </div>
        </slot>

        <div class="row-between-center mt py-xs pr-xs">
          <div class="flex-row flex-1 row-around">
            <div class="px-lg line-height-1 col-all-center use-click"
                  @click.stop="toFollowVue(SocialuniFollowType.follow)">
              <span class="text-lg font-bold color-main row-center">
                {{ mineUserFansDetail?.followNum }}
              </span>
              <span class="font-12 color-content mt-xs">关注</span>
            </div>
            <div class="px-lg line-height-1 col-all-center use-click"
                  @click.stop="toFollowVue(SocialuniFollowType.fans)">
              <span class="text-lg font-bold color-main row-center">
                {{ mineUserFansDetail?.fansNum }}
              </span>
              <span class="font-12 color-content mt-xs">被关注</span>
            </div>
          </div>
        </div>

        <!--      <div class="mr">
                <q-icon icon="setting" size="26" @click="showMoreListAction"></q-icon>
              </div>-->


        <!--      <div class="h200">123</div>-->
      </div>

      <div class="bg-white px-sm bd-radius-10 elevation-4 mx-sm">
        <div class="row-col-center my">
          <q-icon icon="map-fill" class="color-sub mr-mn" size="12"/>
          地区：{{ mineUser.city || '' }}
        </div>

        <div class="mb">
          <div class="row-col-center">
            <q-icon class="color-sub mr-xs" icon="mdi-cellphone-android" size="12"/>
            手机号：
            <div v-if="mineUserPhoneNum" class="row-col-center">
              {{ mineUserPhoneNum }}
              <div class="ml-10 sm cu-tag bg-white bd-gray radius">
                已绑定
              </div>
            </div>
            <div v-else class="row-col-center">
              <button class="ml-xs q-tag bg-click"
                      @click="toPhonePage">绑定手机号
              </button>
            </div>
          </div>
        </div>

        <div class="row-col-center mb-smm">
          <q-icon class="color-sub mr-xs" icon="mdi-school" size="12"/>
          学校名称：
          <div v-if="mineUserExpandDetail?.schoolName" @click="openSetSchoolDialog">
            {{ mineUserExpandDetail?.schoolName }}
          </div>
          <div v-else class="q-tag" @click="openSetSchoolDialog">设置大学名称</div>
        </div>

        <!--        <div class="row-col-center mb-smm">
                  <q-icon class="color-sub mr-xs" prefix="uni-icons" icon="uniui-chat" size="14"/>
                  联系方式：
                  <div v-if="mineUser.contactInfo" @click="openSetSchoolDialog">
                    {{ mineUser.contactInfo }}
                  </div>
                  <div v-else class="q-tag" @click="openSetSchoolDialog">设置联系方式</div>
                </div>-->

        <!--                <div class="row-col-center mb-smm">-->
        <!--                    <q-icon class="color-sub mr-xs" prefix="uni-icons" icon="uniui-chat" size="14"/>-->
        <!--                    联系方式：-->
        <!--                    <div v-if="mineUser.contactInfo" class="q-tag row-col-center" @click="openSetContactInfo">-->
        <!--                        {{ mineUser.contactInfo }}-->
        <!--                        <div class="ml-xs">({{ mineUser.openContactInfo ? '他人可获取' : '他人不可获取' }})</div>-->
        <!--                    </div>-->
        <!--                    <div v-else class="row-col-center q-tag" @click="openSetContactInfo">设置联系方式</div>-->
        <!--                </div>-->
        <!--        <div v-if="mineUser.openContactInfo" class="row-col-center mb-smm">
                  <q-icon class="text-gray mr-xs" icon="mdi-school"/>
                  联系方式：
                  <div class="q-tag">{{ mineUser.contactInfo }}(点击获取联系方式)</div>
                </div>-->
      </div>

      <div v-if="!isIosAndMpQQ"
           class="row-between-center use-click bg-white px-sm py-sm bd-radius-10 elevation-4 mx-sm mt-smm"
           @click="toCoinPage">
        <div class="row-col-center">
          <q-icon prefix="uni-icons" icon="uniui-wallet-filled" class="color-orange"></q-icon>
          <div class="ml-xs">我的金币</div>
        </div>
        <q-icon icon="arrow-right" class="text-md margin-right-sm color-sub"></q-icon>
      </div>

      <socialuni-user-info-img class="mt-sm" :user="mineUser" :imgs="userImgs"></socialuni-user-info-img>

      <q-popup ref="moreActionList" bottom title="清池 app">
        <div class="flex-col h100p pb-50">
          <!--                    <q-row-item :border="true">
                                <navigator :url="messageSettingUrl" class="row-col-center flex-1">
                                  <div class="row-col-center flex-1">
                                    消息设置
                                  </div>
                                  <q-icon icon="arrow-right" class="font-md margin-right-sm"></q-icon>
                                </navigator>
                              </q-row-item>-->
          <!--                    <navigator :url="suggestUrl" class="q-box-between bb bg-click">
                                  <div class="row-col-center flex-1">
                                      意见反馈
                                  </div>
                                  <q-icon icon="arrow-right" class="text-md margin-right-sm"></q-icon>
                              </navigator>
                              <navigator :url="contactUsUrl" class="q-box-between bb bg-click">
                                  <div class="row-col-center flex-1">
                                      联系我们
                                  </div>
                                  <q-icon icon="arrow-right" class="text-md margin-right-sm"></q-icon>
                              </navigator>
                              <navigator :url="homeUrl" class="q-box-between bb bg-click">
                                  <div class="row-col-center flex-1">
                                      关于我们
                                  </div>
                                  <q-icon icon="arrow-right" class="text-md margin-right-sm"></q-icon>
                              </navigator>-->
          <q-row-item :border="true" @click="destroyAccount">
            <div class="row-col-center flex-1">
              注销账号
            </div>
            <q-icon icon="arrow-right" class="text-md margin-right-sm"></q-icon>
          </q-row-item>
          <q-row-item :border="true" @click="logout">
            <div class="row-col-center flex-1">
              退出登录
            </div>
            <q-icon icon="arrow-right" class="text-md margin-right-sm"></q-icon>
          </q-row-item>
          <!--                    <div class="px-smm py-sm w100p row-wrap">
                                  <navigator :url="userAgreementUrl" class="color-blue">
                                      《用户协议》
                                  </navigator>
                                  <navigator :url="userPrivacyUrl" class="color-blue">
                                      《隐私政策》
                                  </navigator>
                                  <navigator :url="childProtectUrl" class="color-blue">
                                      《儿童个人信息保护规则及监护人须知》
                                  </navigator>
                              </div>-->
        </div>
      </q-popup>

      <user-school-edit-dialog ref="schoolEditDialog"></user-school-edit-dialog>
      <user-contact-info-edit-dialog ref="contactInfoEditDialog"></user-contact-info-edit-dialog>
    </div>

    <!--      title="欢迎登录清池app"-->
    <login-view v-else class="h100p"></login-view>
  </div>
</template>

<script lang="ts">
import {Component, Prop, Vue, toNative} from 'vue-facing-decorator'
import LoginView from "../login/LoginView.vue";
import SocialuniFollowType from 'socialuni-constant/constant/user/SocialuniFollowType';
import UserContactInfoEditDialog from "./UserContactInfoEditDialog.vue";
import QPopup from "qing-ui/src/components/QPopup.vue";
import QIcon from "qing-ui/src/components/QIcon.vue";
import QNavbar from "qing-ui-uni/src/components/QNavbar/QNavbar.vue";
import QRowItem from "qing-ui/src/components/QRowItem.vue";
import SocialuniUserInfoImg from "./SocialuniUserInfoImg.vue";
import UserSchoolEditDialog from "./UserSchoolEditDialog.vue";
import QInput from "qing-ui/src/components/QInput.vue";
import QButton from "qing-ui/src/components/QButton.vue";
import QSearch from "qing-ui-uni/src/components/QSearch/QSearch.vue";
import SocialuniMineUserAPI from "socialuni-user-api/src/api/SocialuniMineUserAPI";
import ImgAddQO from "socialuni-api-base/src/model/user/ImgAddQO";
import SocialGenderTag from "socialuni-user-ui/src/components/SUserGenderTag.vue";
import {socialuniUserModule} from 'socialuni-user-sdk/src/store/SocialuniUserModule';
import {socialuniSystemModule} from "qing-util/src/store/SocialuniSystemModule";
import UserPageUtil from "socialuni-user-sdk/src/util/UserPageUtil";
import QingAppUtil from "qing-compat-js/src/util/QingAppUtil";
import CosService from "socialuni-app-sdk/src/util/CosService";
import DomFile from "socialuni-app-sdk/src/model/DomFile";
import TencentCosAPI from "socialuni-app-api/src/api/TencentCosAPI";
import SkipUrlConst from "socialuni-app-sdk/src/constant/SkipUrlConst";
import {getCurrentInstance} from "vue";
import {onLoad} from "@dcloudio/uni-app";
import {socialuniAppUserModule} from "socialuni-user-sdk/src/store/SocialuniAppUserModule";
import SocialuniUserService from "socialuni-user-sdk/src/logic/SocialuniUserService";

@toNative
@Component({
  components: {
    UserContactInfoEditDialog,
    LoginView,
    QPopup,
    QIcon,
    QNavbar,
    QRowItem,
    SocialuniUserInfoImg,
    QInput,
    QButton,
    UserSchoolEditDialog,
    QSearch,
    SocialGenderTag,
  }
})
export default class MineView extends Vue {
  $refs: {
    moreActionList: QPopup
    schoolEditDialog: UserSchoolEditDialog
    contactInfoEditDialog: UserContactInfoEditDialog;
  }

  get mineUser() {
    return socialuniUserModule.mineUser
  }
  get userImgs() {
    return socialuniAppUserModule.userImgs
  }


  get mineUserExpandDetail() {
    return socialuniAppUserModule.mineUserExpandDetail
  }

  get mineUserFansDetail() {
    return socialuniAppUserModule.mineUserFansDetail
  }

  get mineUserPhoneNum() {
    return socialuniAppUserModule.mineUserPhoneNum
  }

  get isIosAndMpQQ() {
    return socialuniSystemModule.isIosOrMpQQ
  }

  SocialuniFollowType = SocialuniFollowType
  providerLogin = true
  showMsgInput = false
  showMoreList = false
  // 登录
  disabledLoginBtn = false

  created() {
    onLoad((params) => {
      QingAppUtil.NativeUtil.showShareMenu()
    })
    /*onShow(() => {
        this.showMsgInput = true
        // socialChatModule.computedChatsUnreadNumTotalAction()
    })*/
  }

  destroyAccount() {
    this.hideMoreList()
    socialuniUserModule.destroyAccount().catch(() => {
      this.showMoreListAction()
    })
  }

  logout() {
    this.hideMoreList()
    SocialuniUserService.loginOut().catch(() => {
      this.showMoreListAction()
    })
  }

  showMoreListAction() {
    this.$refs.moreActionList.open()
  }

  hideMoreList() {
    this.$refs.moreActionList.close()
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
    if (socialuniUserModule.hasToken) {
      socialuniAppUserModule.getMineUserAction().then(() => {
        QingAppUtil.ToastUtil.toast('刷新成功')
      }).finally(() => {
        this.stopPullDownRefresh()
      })
    }
  }

  stopPullDownRefresh() {
    uni.stopPullDownRefresh()
  }

  seeAvatarDetail() {
    uni.previewImage({
      current: 0,
      urls: [this.mineUser.avatar]
    })
  }

  moreAction() {
    const menuList: string [] = ['查看头像', '上传头像']
    QingAppUtil.NativeUtil.actionSheet(menuList).then((index: number) => {
      if (index === 0) {
        this.seeAvatarDetail()
      } else if (index === 1) {
        this.uploadUserAvatarImg()
      }
    })
  }

  loginOut() {
    SocialuniUserService.loginOut()
  }

  async uploadUserAvatarImg() {
    try {
      let cosAuthRO
      CosService.getCosAuthRO().then(res => {
        cosAuthRO = res
      })
      const imgFiles: DomFile[] = await QingAppUtil.NativeUtil.chooseImage(1)
      QingAppUtil.NativeUtil.showLoading('上传中')
      const imgFile: DomFile = imgFiles[0]
      imgFile.src = cosAuthRO.uploadImgPath + 'img/' + imgFile.src
      const res = await Promise.all([TencentCosAPI.uploadFileAPI(imgFile, cosAuthRO), SocialuniMineUserAPI.addUserAvatarImgAPI(new ImgAddQO(imgFile))])
      socialuniUserModule.setUser(res[1].data)
    } catch (e) {
      console.error(e)
    } finally {
      QingAppUtil.NativeUtil.hideLoading()
    }
  }

  toFollowVue(followType: string) {
    UserPageUtil.toUserFollowPage(followType)
  }

  toEditUserInfo() {
    UserPageUtil.toEditMineInfo()
  }

  toMineUserDetailPage() {
    UserPageUtil.toUserDetail(this.mineUser.id)
  }

  refreshMine() {
    QingAppUtil.AlertUtil.confirm('是否刷新用户信息').then(() => {
      socialuniAppUserModule.getMineUserAction().then(() => {
        QingAppUtil.ToastUtil.toast('刷新成功')
      })
    })
  }

  toFaceValuePage() {
    UserPageUtil.toFaceValuePage()
  }

  async toPhonePage() {
    await UserPageUtil.toPhonePage()
  }

  toLoveValuePage() {
    if (this.mineUser) {
      UserPageUtil.toLoveValuePage()
    } else {
      // MsgUtil.unLoginMessage()
    }
  }

  openSetSchoolDialog() {
    this.$refs.schoolEditDialog.open()
  }

  toCoinPage() {
    UserPageUtil.toCoinPage()
  }

  openSetContactInfo() {
    this.$refs.contactInfoEditDialog.open()
  }
}
</script>
