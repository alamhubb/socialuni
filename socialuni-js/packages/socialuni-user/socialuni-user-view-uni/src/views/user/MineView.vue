<template>
    <view class="h100p">
        <view v-if="mineUser" class="bg-default h100p flex-col">
            <div class="bg-theme-gradual px-smm pb-smm pt-100">
                <div class="row-end-center">
                    <uni-icons type="compose" size="24" class="use-click" @click="toEditUserInfo"></uni-icons>
                    <q-icon class="ml" icon="setting" size="20" @click="showMoreListAction"></q-icon>
                </div>
                <slot name="baseInfo" :mine="mineUser">
                    <view class="row-between mt">
                        <slot name="baseInfoLeft" :mine="mineUser">
                            <div class="row-col-center">
                                <image
                                        class="size65 bd-round bd-3 bd-white mr-sm"
                                        mode="aspectFill"
                                        :src="mineUser.avatar"
                                        @click="moreAction"
                                />
                                <view class="flex-col">
                                    <view class="text-md font-bold" :class="{'color-red':mineUser.vipFlag}">
                                        {{ mineUser.nickname }}
                                    </view>
                                    <view class="flex-row">
                                        <social-gender-tag class="mt-sm" :user="mineUser" border></social-gender-tag>
                                        <!--                <view v-if="userProp.vipFlag" class="cu-tag bg-red radius" @click="openVip">VIP</view>
                                                        <view v-else class="cu-tag bg-grey radius" @click="openVip">VIP</view>-->
                                    </view>
                                </view>
                            </div>
                        </slot>
                        <slot name="baseInfoRight" :mine="mineUser">
                            <view class="row-col-center font-12 use-click" @click="toMineUserDetailPage">
                                <!--          <q-button @click="toEditUserInfo" theme light round>-->
                                <div class="mt-1">空间</div>
                                <q-icon icon="arrow-right" class="ml-nn" size="12"></q-icon>
                                <!--          </q-button>-->
                            </view>
                        </slot>
                    </view>
                </slot>

                <view class="row-between-center mt py-xs pr-xs">
                    <view class="flex-row flex-1 row-around">
                        <view class="px-lg line-height-1 col-all-center use-click"
                              @click.stop="toFollowVue(SocialuniFollowType.follow)">
                            <text class="text-lg font-bold color-main row-center">
                                {{ mineUser.followNum }}
                            </text>
                            <text class="font-12 color-content mt-xs">关注</text>
                        </view>
                        <view class="px-lg line-height-1 col-all-center use-click"
                              @click.stop="toFollowVue(SocialuniFollowType.fans)">
                            <text class="text-lg font-bold color-main row-center">
                                {{ mineUser.fansNum }}
                            </text>
                            <text class="font-12 color-content mt-xs">被关注</text>
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
                    <q-icon icon="map-fill" class="color-sub mr-mn" size="12"/>
                    地区：{{ mineUser.city || '' }}
                </view>

                <view class="mb">
                    <div class="row-col-center">
                        <q-icon class="color-sub mr-xs" icon="mdi-cellphone-android" size="12"/>
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
                    <q-icon class="color-sub mr-xs" icon="mdi-school" size="12"/>
                    学校名称：
                    <div v-if="mineUser.schoolName" @click="openSetSchoolDialog">
                        {{ mineUser.schoolName }}
                    </div>
                    <div v-else class="q-tag" @click="openSetSchoolDialog">设置大学名称</div>
                </view>

                <!--        <view class="row-col-center mb-smm">
                          <q-icon class="color-sub mr-xs" prefix="uni-icons" icon="uniui-chat" size="14"/>
                          联系方式：
                          <div v-if="mineUser.contactInfo" @click="openSetSchoolDialog">
                            {{ mineUser.contactInfo }}
                          </div>
                          <div v-else class="q-tag" @click="openSetSchoolDialog">设置联系方式</div>
                        </view>-->

                <div class="row-col-center mb-smm">
                    <q-icon class="color-sub mr-xs" prefix="uni-icons" icon="uniui-chat" size="14"/>
                    联系方式：
                    <div v-if="mineUser.contactInfo" class="q-tag row-col-center" @click="openSetContactInfo">
                        {{ mineUser.contactInfo }}
                        <div class="ml-xs">({{ mineUser.openContactInfo ? '他人可获取' : '他人不可获取' }})</div>
                    </div>
                    <div v-else class="row-col-center q-tag" @click="openSetContactInfo">设置联系方式</div>
                </div>
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
                    <div class="ml-xs">我的贝壳</div>
                </div>
                <q-icon icon="arrow-right" class="text-md margin-right-sm color-sub"></q-icon>
            </div>

            <socialuni-user-info-img class="mt-sm" :user="mineUser"></socialuni-user-info-img>

            <q-popup ref="moreActionList" bottom title="清池 app">
                <view class="flex-col h100p pb-50">
                    <!--                    <q-row-item>
                                          <navigator :url="messageSettingUrl" class="row-col-center flex-1">
                                            <view class="row-col-center flex-1">
                                              消息设置
                                            </view>
                                            <q-icon icon="arrow-right" class="font-md margin-right-sm"></q-icon>
                                          </navigator>
                                        </q-row-item>-->
                    <!--                    <navigator :url="suggestUrl" class="q-box-between bb bg-click">
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
                                        </navigator>-->
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
                    <!--                    <view class="px-smm py-sm w100p row-wrap">
                                            <navigator :url="userAgreementUrl" class="color-blue">
                                                《用户协议》
                                            </navigator>
                                            <navigator :url="userPrivacyUrl" class="color-blue">
                                                《隐私政策》
                                            </navigator>
                                            <navigator :url="childProtectUrl" class="color-blue">
                                                《儿童个人信息保护规则及监护人须知》
                                            </navigator>
                                        </view>-->
                </view>
            </q-popup>

            <user-school-edit-dialog ref="schoolEditDialog"></user-school-edit-dialog>
            <user-contact-info-edit-dialog ref="contactInfoEditDialog"></user-contact-info-edit-dialog>
        </view>

        <!--      title="欢迎登录清池app"-->
        <login-view v-else class="h100p"></login-view>
    </view>
</template>

<script lang="ts">
import {Component, Prop, Vue} from 'vue-facing-decorator'
import LoginView from "../login/LoginView.vue";
import SocialuniFollowType from 'socialuni-constant/constant/user/SocialuniFollowType';
import UserContactInfoEditDialog from "./UserContactInfoEditDialog.vue";
import QPopup from "qing-ui-uni/src/components/QPopup/QPopup.vue";
import QIcon from "qing-ui-uni/src/components/QIcon/QIcon.vue";
import QNavbar from "qing-ui-uni/src/components/QNavbar/QNavbar.vue";
import QRowItem from "qing-ui-uni/src/components/QRowItem/QRowItem.vue";
import SocialuniUserInfoImg from "./SocialuniUserInfoImg.vue";
import UserSchoolEditDialog from "./UserSchoolEditDialog.vue";
import QInput from "qing-ui-uni/src/components/QInput/QInput.vue";
import QButton from "qing-ui-uni/src/components/QButton/QButton.vue";
import QSearch from "qing-ui-uni/src/components/QSearch/QSearch.vue";
import SocialuniMineUserAPI from "socialuni-user-api/src/api/SocialuniMineUserAPI";
import ImgAddQO from "socialuni-api-base/src/model/user/ImgAddQO";
import SocialGenderTag from "../../components/SocialGenderTag/SocialGenderTag.vue";
import {socialuniUserModule} from 'socialuni-user-sdk/src/store/SocialuniUserModule';
import {socialuniSystemModule} from "qing-util/src/store/SocialuniSystemModule";
import UserService from "socialuni-user-sdk/src/logic/UserService";
import UserPageUtil from "socialuni-user-sdk/src/util/UserPageUtil";
import QingAppUtil from "qingjs/src/util/QingAppUtil";
import CosService from "socialuni-app-sdk/src/util/CosService";
import DomFile from "socialuni-app-sdk/src/model/DomFile";
import TencentCosAPI from "socialuni-app-api/src/api/TencentCosAPI";
import SkipUrlConst from "socialuni-app-sdk/src/constant/SkipUrlConst";
import {getCurrentInstance} from "vue";
import {onLoad} from "@dcloudio/uni-app";
import {socialuniAppUserModule} from "socialuni-user-sdk/src/store/SocialuniAppUserModule";

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
        UserService.loginOut().catch(() => {
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
        UserService.loginOut()
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
