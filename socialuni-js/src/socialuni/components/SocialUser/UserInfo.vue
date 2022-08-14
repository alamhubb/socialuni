<template>
  <view v-if="userProp" class="bg-default pb-100">
    <view class="bg-white mb-sm">
      <view>
        <swiper v-if="imgUrls.length" class="square-dot w100vw h230">
          <swiper-item v-for="(img,index) in imgUrls" :key="img">
            <image class="size100p" @longpress="showBottomMenuClick(index)"
                   :data-src="img"
                   @click="previewImage"
                   mode="aspectFill"
                   :src="img"
            ></image>
          </swiper-item>
        </swiper>
        <image v-else class="sizeUserImg" @click="showBottomMenuClick(0)"
               mode="aspectFill"
               src="https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/uploadimgmini.png"
        ></image>
        <view v-if="showUploadImgHint && imgUrls.length" class="row-col-center bg-orange">
          <view class="flex-auto flex-row px">
            单击图片预览，长按图片进行操作
          </view>
          <view class="flex-none mr-10">
            <q-icon icon="close-circle-fill" size="18" @click="closeUploadImgHint"></q-icon>
          </view>
        </view>
      </view>
      <view class="pd-sm">
        <view class="row-col-center py-sm">
          <image
            class="size65 bd-radius-xs mr-sm"
            mode="aspectFill"
            :src="userProp.avatar"
          />
          <view class="flex-auto row-between">
            <view class="flex-col flex-auto">
              <view class="text-md" :class="{'color-red':userProp.vipFlag}">
                {{ userProp.nickname }}
              </view>
              <view class="flex-row">
                <social-gender-tag class="mt-sm" :user="userProp"></social-gender-tag>
                <!--                <view v-if="userProp.vipFlag" class="cu-tag bg-red radius" @click="openVip">VIP</view>
                                <view v-else class="cu-tag bg-grey radius" @click="openVip">VIP</view>-->
              </view>
            </view>

            <view class="row-col-center" v-if="isMine">
              <q-icon icon="edit-pen" size="24" class="mr-xs" @click="moreAction"/>
            </view>
            <view v-else-if="userProp.beFollow" class="row-col-center">
              <view class="bg-default text-sm px-xs text-gray">
                对方关注了您
              </view>
            </view>
          </view>
        </view>
        <view class="row-between-center py-xs pr-xs">
          <view class="flex-row flex-auto" :class="{'row-around':isMine}">
            <view class="px-lg line-height-1" @click.stop="toFollowVue">
              <text class="text-lg font-bold text-black row-center">
                {{ userProp.followNum }}
              </text>
              <text class="text-sm text-gray">关注</text>
            </view>

            <view class="px-lg line-height-1" @click.stop="toFollowVue">
              <text class="text-lg font-bold text-black row-center">
                {{ userProp.fansNum }}
              </text>
              <text class="text-sm text-gray">被关注</text>
            </view>
          </view>

          <view v-if="!isMine" class="flex-row">
            <!--                不为自己且未关注-->
            <!--            不为ios，或者不为付费，则显示-->
            <!--            <button v-if="!isIos||!userProp.chat.needPayOpen" class="cu-btn round bd-gray bg-white mr-sm"
                                @click="toMessagePage">
                          私信
                          &lt;!&ndash; <text v-if="userProp.chat.needPayOpen" class="ml-2">(10B)</text>&ndash;&gt;
                        </button>-->
            <button v-if="followStatus==='关注'" class="color-content chunk q-box-nm bd-radius"
                    @click.stop="addFollow">
              {{ followStatus }}
            </button>
            <view v-else class="color-content" @click.stop="addFollow">{{ followStatus }}</view>
            <!--              <button v-else class="cu-btn round bd-gray bg-white" @click.stop="addFollow">已关注</button>-->
          </view>
        </view>

        <div class="flex-row pl-xs">
          <div v-if="userProp.identityAuth" class="q-tag-success q-box-nn" @click.stop="toIdentityAuth">
            <q-icon class="color-success mr-mn" size="14" icon="level"/>
            <div class="font-xs">成年</div>
          </div>
          <div v-else class="q-tag q-box-nn" @click.stop="toIdentityAuth">
            <q-icon class="color-sub mr-mn" size="14" icon="level"/>
            <div class="font-xs">成年</div>
          </div>
        </div>
        <!--        <view class="ml-5 cu-capsule radius" @click="hintJusticeInfo">
                  <view class='cu-tag bg-green'>
                    <q-icon size="18" icon="mdi-sword-cross"/>
                  </view>
                  <view class="cu-tag bg-white bd-green bd-r-radius">
                    {{ userProp.justiceValue }}
                  </view>
                </view>
                <view class="ml cu-capsule radius" @click="toLoveValuePage">
                  <view class='cu-tag bg-red'>
                    <q-icon size="18" icon="heart-fill"/>
                  </view>
                  <view class="cu-tag bg-white bd-red bd-r-radius">
                    {{ userProp.loveValue }}
                  </view>
                </view>
                <view class="ml-lg cu-capsule radius" @click="toFaceValuePage">
                  <view class='cu-tag bg-orange'>
                    <q-icon size="18" icon="mdi-face"/>
                  </view>
                  <view class="cu-tag bg-white bd-orange bd-r-radius">
                    {{ userProp.faceRatio }}
                  </view>
                </view>-->

        <view class="row-col-center my">
          <q-icon class="text-gray mr-xs" icon="map-fill"/>
          地区：{{ userProp.city || '' }}
        </view>

        <!--        <view v-if="isMine" class="py-sm q-solid-bottom">
                  <div class="row-col-center">
                    <image class="size20px mr-xs"
                           mode="aspectFit"
                           :src="require('socialuni/static/img/socialuni_logo.jpg')"
                    />
                    社交联盟：
                    <view v-if="userProp.bindSocialuni">
                      {{ userProp.socialuniNickname }}
                      <view class="ml-10 sm cu-tag bg-white bd-gray radius">
                        已绑定
                      </view>
                    </view>
                    <view v-else>
                      绑定后可发布动态
                      <button class="cu-btn radius sm bg-orange ml-xs"
                              @click="toBindSocialuni">绑定
                      </button>
                    </view>
                  </div>
                </view>-->
        <view v-if="isMine" class="mb">
          <div class="row-col-center">
            <q-icon class="text-gray mr-xs" icon="mdi-cellphone-android"/>
            手机号：
            <view v-if="userProp.phoneNum" class="row-col-center">
              {{ userProp.phoneNum }}
              <view class="ml-10 sm cu-tag bg-white bd-gray radius">
                已绑定
              </view>
            </view>
            <view v-else class="row-col-center">
              <button class="ml-xs q-tag-warn bg-click"
                      @click="toPhonePage">绑定手机号
              </button>
            </view>
          </div>
        </view>

        <!--        你看一个人的时候想看他的什么，看他的背景图，看他的关注动态。看他的图片。-->
        <!--        看他的性别等级-->

        <!-- #ifndef MP-WEIXIN -->
        <!--        <view class="row-col-center py-sm q-solid-bottom">
                  <q-icon class="text-gray mr-xs" icon="mdi-alpha-v-circle"/>
                  照片认证：
                  &lt;!&ndash; 为自己且未绑定&ndash;&gt;
                  <view class="row-between-center flex-auto" v-if="userProp.isMine && !userProp.isSelfAuth">
                    未认证
                    <button class="mr-xs cu-btn sm bd-none text-sm bd-box-radius bg-orange"
                            @click="toIdentityAuth">认证
                    </button>
                  </view>
                  <view v-else>
                    <view class="ml-10 sm cu-tag bg-blue radius" v-if="userProp.isSelfAuth">
                      已认证
                    </view>
                    <view class="ml-10 sm cu-tag bg-white bd-gray radius" v-else>
                      未认证
                    </view>
                  </view>
                </view>-->
        <!-- #endif -->
        <!--<view v-if="userProp.wxAccount" class="row-col-center q-solid-bottom">
          微信：
          <text selectable>{{userProp.wxAccount}}</text>
          <button class="cu-btn radius sm bd-blue ml-10 bg-white"
                  @click="copyText(userProp.wxAccount)">
            复制
          </button>
        </view>-->
        <!--        <view v-if="isMine && !userProp.contactAccount" class="row-col-center row-between-center bg-click"
                      @click="$pageUtil.toUserContactInfoPage">
                  <view class="row-col-center">
                    <text class="text-md text-orange">他人获取您的联系方式时，您就能获得贝壳</text>
                  </view>
                  <view class="text-gray row-col-center pr-xs">
                    <text class="text-md text-gray text-md">详情</text>
                    <q-icon class="text-gray" size="32" icon="arrow-right"/>
                  </view>
                </view>-->
        <!-- 如果自己的话-->
        <!-- 左边格式不变，如果未填写则可以填写，填写之后可以选择开启或者关闭，填写后可选择隐藏展示。-->
        <!--        todo qq不支持联系方式，改为判断qq一件加好友-->
        <!--        <view class="row-col-center row-between-center">
                  <view class="row-col-center">
                    <q-icon class="text-gray mr-xs" icon="account"/>
                    联系方式：
                    <text v-if="userProp.contactAccount">{{ userProp.contactAccount }}</text>
                    <text v-else>
                      未填写
                    </text>
                  </view>
                  <view v-if="isMine" class="row-between-center">
                    <view v-if="userProp.contactAccount" class="row-between-center">
                      <text v-if="userProp.openContact" class="mr-xs text-green">展示中</text>
                      <text v-else class="mr-xs text-gray">已隐藏</text>
                      <u-switch v-model="mineUser.openContact" active-color="#00C853" @change="switchOpenContact"></u-switch>
                    </view>
                    <button v-else class="mr-xs cu-btn sm bd-none font-xs bd-box-radius bg-orange"
                            @click="openEditDialog">
                      填写联系方式
                    </button>
                  </view>
                  <view v-else-if="userProp.contactAccount">
                    <view v-if="!userProp.openContact" class="mr-sm">
                      对方隐藏了联系方式
                    </view>
                    <button v-else-if="userProp.showUserContact" class="mr-xs cu-btn sm bd-none font-xs bd-box-radius bg-orange"
                            @click="$util.textCopy(userProp.contactAccount)">
                      复制
                    </button>
                    <button v-else :disabled="showUserContactBtnDisabled"
                            class="mr-xs cu-btn sm bd-none font-xs bd-box-radius bg-blue"
                            @click="shellPayForUserContact">
                      10 贝壳获取
                    </button>
                  </view>
                </view>-->
        <!--        todo 暂时注释掉支付功能，qq需要改用为米大师-->
        <!--<view v-if="isMine" class="q-solid-top row-col-center row-between-center" @click="toUserShell">
          <view class="row-col-center">
            <q-icon class="text-green mr-xs" icon="mdi-bitcoin"/>
            <text class="text-md">我的贝壳（{{ mineUser.shell }}）</text>
          </view>
          <view v-if="!isIos" class="text-gray row-col-center pr-xs">
            <text class="text-md text-gray text-md">充值</text>
            <q-icon class="text-gray" size="32" icon="arrow-right"/>
          </view>
        </view>-->
      </view>
    </view>

    <uni-popup ref="reportDialog" :custom="true" :mask-click="false">
      <view class="uni-tip">
        <view class="uni-tip-title">举报</view>
        <view class="uni-tip-content">
          <radio-group class="uni-list" @change="reportTypeChange">
            <label class="uni-list-cell flex-row uni-list-cell-pd"
                   v-for="report in reportTypes" :key="report">
              <view>
                <radio :id="report" :value="report" :checked="report===pornInfo"></radio>
              </view>
              <view>
                <label class="ml-10" :for="report">
                  <text>{{ report }}</text>
                </label>
              </view>
            </label>
          </radio-group>
        </view>
        <view class="uni-textarea bd-1 bd-radius-xs">
            <textarea placeholder="其他违规必填，其他情况选填，可详细陈述观点" v-model.trim="reportContent"
                      :show-confirm-bar="false"
            />
        </view>
        <view class="uni-tip-group-button">
          <button class="uni-tip-button w40p" type="default" @click="closeDialogAndInitData"
                  :plain="true">
            取消
          </button>
          <button class="uni-tip-button w40p" type="primary" @click="addReport" :disabled="!reportType">确定
          </button>
        </view>
      </view>
    </uni-popup>

    <talk-operate @deleteTalk="deleteTalk"></talk-operate>
    <!--  #ifdef MP-WEIXIN -->
    <ad v-if="talks.length>1" class="bg-white mb-5 w100vw" unit-id="adunit-65c8911d279d228f" ad-type="video"
        ad-theme="white"></ad>
    <!--  #endif -->
    <!--qq平台显示的广告-->
    <!--  #ifdef MP-QQ -->
    <ad v-if="talks.length>0" class="bg-white mb-5 w100vw" unit-id="72d8cb09a1bae9fa30d9e03e7cb8a25d"
        type="feeds" ad-height="160"></ad>
    <!--  #endif -->
    <!--  #ifdef APP-PLUS -->
    <ad v-if="talks.length>0" class="bg-white mb-5 w100vw" adpid="1890536227"></ad>
    <!--  #endif -->

    <view v-for="talk in talks" :key="talk.id" class="px-sm">
      <talk-item :talk="talk" @deleteTalk="deleteTalk"></talk-item>
    </view>

    <!--wx平台显示的广告-->
    <!--  #ifdef MP-WEIXIN -->
    <ad class="bg-white mt-10 w100vw" unit-id="adunit-ffa7bc1c73c7d46a"></ad>
    <!--  #endif -->
    <!--qq平台显示的广告-->
    <!--  #ifdef MP-QQ -->
    <ad class="bg-white mt-10 w100vw" unit-id="b10fe0e7c39b9ca9e7ce19660f6d0761" test-banner-type="one"></ad>
    <!--  #endif -->
    <!--  #ifdef APP-PLUS -->
    <ad class="bg-white mt-10 w100vw" adpid="1890536227"></ad>
    <!--  #endif -->
    <uni-popup ref="editPopup" type="center">
      <user-edit @close="closeUserEditPop"></user-edit>
    </uni-popup>
  </view>
</template>

<script lang="ts">
import {Component, PropSync, Vue, Watch} from 'vue-property-decorator'
import TalkAPI from '../../api/socialuni/TalkAPI'
import TalkItem from '../SocialTalk/TalkItem.vue'
import UserUtil from '../../utils/UserUtil'
import TalkItemContent from '../SocialTalk/TalkItemContent.vue'
import FollowAddVO from '../../model/FollowAddVO'
import UserEdit from './UserEdit.vue'
import UniUtil from '../../utils/UniUtil'
import PagePath from '../../constant/PagePath'
import FollowAPI from '../../api/socialuni/FollowAPI'
import FollowStatus from '../../constant/FollowStatus'
import PageUtil from '../../utils/PageUtil'
import ImgFileVO from '../../model/ImgFileVO'
import ImgUtil from '../../utils/ImgUtil'
import CosUtil from '../../utils/CosUtil'
import UserAPI from '../../api/socialuni/UserAPI'
import Constants from '../../constant/Constant'
import ReportContentType from '../../constant/ReportContentType'
import ReportType from '../../constant/ReportType'
import ReportAddVO from '../../model/report/ReportAddVO'
import ReportAPI from '../../api/socialuni/ReportAPI'
import TalkOperate from '../SocialTalk/talkOperate.vue'
import TalkVO from '../../model/talk/TalkVO'
import MsgUtil from '../../utils/MsgUtil'
import ConfigMap from '../../constant/ConfigMap'
import PlatformUtils from '../../utils/PlatformUtils'
import {socialAppStore, socialConfigStore, socialSystemStore, socialUserModule, socialUserStore} from '../../store'
import QRowItem from '../../../qing-ui/components/QRowItem/QRowItem.vue'
import AlertUtil from '../../utils/AlertUtil'
import ToastUtil from '../../utils/ToastUtil'
import RouterUtil from '../../utils/RouterUtil'
import CenterUserDetailRO from '../../model/social/CenterUserDetailRO'
import QIcon from '../../../qing-ui/components/QIcon/QIcon.vue'
import DomFile from '../../model/DomFile'
import ImgAddQO from '../../model/user/ImgAddQO'
import SocialGenderTag from '@/socialuni/components/SocialGenderTag/SocialGenderTag.vue'
import TencentCosAPI from '@/api/TencentCosAPI'


@Component({
  components: {
    SocialGenderTag,
    QIcon,
    QRowItem,
    TalkOperate,
    UserEdit,
    TalkItem,
    TalkItemContent
  }
})
export default class UserInfo extends Vue {
  $refs!: {
    reportDialog: any;
    editPopup: any;
  }

  @socialUserStore.State('user') mineUser: CenterUserDetailRO
  @socialAppStore.State('appConfig') readonly appConfig: object
  @socialAppStore.State('reportTypes') reportTypes: string[]
  @socialSystemStore.State('isIos') isIos: boolean
  @PropSync('user') userProp: CenterUserDetailRO
  followBtnDisabled = false
  hasFollowed = false
  followStatus: string = FollowStatus.follow
  imgIndex = 0
  showUploadImgHint: boolean = uni.getStorageSync(Constants.showUploadImgHintKey) !== 'false'
  readonly reportContentType: string = ReportContentType.userImg
  reportType: string = ReportType.pornInfo
  pornInfo: string = ReportType.pornInfo
  reportContent = ''
  @socialConfigStore.Getter(ConfigMap.reportHideCountKey) reportHideCount: number
  talks: TalkVO[] = []

  showUserContactBtnDisabled = false

  toMessagePage() {
    //除了是否关注，还有是否已经发起过对话，chatuservo里面要保存还能再发几条
    //判断是否已经支付过了。3条，然后对方每次回复你都可以发三条，然后就需要再次支付，开启了支付
    //mock chat
    // chatModule.userDetailToMessagePage(this.userProp.chat)
    //如果有chat读取，如果没有创建读取
    // chatModule.setChatAction(chat)
  }

  /*shellPayForUserContact () {
    if (!this.showUserContactBtnDisabled) {
      this.showUserContactBtnDisabled = true
      constant userShell = this.mineUser.shell
      if (userShell >= 10) {
        Alert.confirm('是否消耗10个贝壳查看用户：' + this.userProp.nickname + ' 的联系方式').then(() => {
          UserAPI.getUserContactAPI(this.userProp.id).then((res) => {
            this.userProp.contactAccount = res.data
            this.userProp.showUserContact = true
            this.mineUser.shell = userShell - 10
          })
        }).finally(() => {
          this.showUserContactBtnDisabled = false
        })
      } else {
        Alert.confirm('您没有贝壳了，是否直接使用现金支付').then(() => {
          constant provider = systemModule.isApp ? ProviderType.wx : systemModule.mpPlatform
          PlatformUtils.pay(provider, PayType.shell, 1).then(() => {
            UserAPI.getUserContactAPI(this.userProp.id).then((res) => {
              this.userProp.contactAccount = res.data
              this.userProp.showUserContact = true
            }).catch((e) => {
              Alert.error(e)
            })
          }).catch(() => {
            MsgUtil.notPay()
          })
        }).finally(() => {
          this.showUserContactBtnDisabled = false
        })
      }
    } else {
      Toast.toast('获取中，请稍等')
    }
  }*/


  openReportDialog() {
    if (this.userProp) {
      this.$refs.reportDialog.open()
    } else {
      MsgUtil.unLoginMessage()
    }
  }

  frontDeleteUserImg() {
    this.userProp.imgs.splice(this.imgIndex, 1)
  }

  reportTypeChange({target}) {
    this.reportType = target.value
  }

  addReport() {
    const reportAdd: ReportAddVO = new ReportAddVO(this.reportContentType, this.reportType, this.reportContent)
    const userImg: ImgFileVO = this.userProp.imgs[0]
    reportAdd.contentId = userImg.id
    if (ReportType.other === this.reportType && !this.reportContent) {
      AlertUtil.hint('选择其他违规时，请您补充观点')
    } else {
      ReportAPI.addReportAPI(reportAdd).then((res: any) => {
        // todo  举报过后，是否大于系统阀值，大于系统阀值隐藏
        const reportNum: number = userImg.reportNum + 1
        if (reportNum >= this.reportHideCount) {
          this.frontDeleteUserImg()
        }
        this.closeDialogAndInitData()
        AlertUtil.hint(res.data)
        PlatformUtils.requestSubscribeReport()
      })
    }
  }

  closeDialogAndInitData() {
    this.$refs.reportDialog.close()
    this.initReportData()
  }

  initReportData() {
    this.imgIndex = 0
    this.reportContent = ''
    this.reportType = ReportType.pornInfo
  }

  get bottomMenuItemList() {
    if (this.isMine) {
      return ['上传', '删除']
    } else {
      return ['举报']
    }
  }

  showBottomMenuClick(imgIndex: number) {
    this.imgIndex = imgIndex
    UniUtil.actionSheet(this.bottomMenuItemList).then((index: number) => {
      if (this.isMine) {
        if (index === 0) {
          this.chooseImg()
        } else if (index === 1) {
          this.deleteImg()
        }
      } else {
        if (index === 0) {
          this.openReportDialog()
        }
      }
    })
  }

  deleteImg() {
    if (this.userProp.imgs.length > 1) {
      AlertUtil.warning('请确认是否删除照片？').then(() => {
        const imgs: ImgFileVO[] = this.userProp.imgs.splice(this.imgIndex, 1)
        UserAPI.deleteUserImgNewAPI(imgs[0]).then((res: any) => {
          socialUserModule.setUser(res.data)
        })
      })
    } else {
      ToastUtil.toastLong('请至少保留一张照片')
    }
  }


  async chooseImg() {
    if (this.mineUser.imgs.length > 2) {
      ToastUtil.toastLong('最多上传3张照片，请删除后继续！')
      return
    }
    try {
      UniUtil.showLoading('上传中')
      const cosAuthRO = await CosUtil.getCosAuthRO()
      const imgFiles: DomFile[] = await UniUtil.chooseImage(1)
      const imgFile: DomFile = imgFiles[0]
      imgFile.src = cosAuthRO.uploadImgPath + 'img/' + imgFile.src
      const res = await Promise.all([TencentCosAPI.uploadFileAPI(imgFile, cosAuthRO), UserAPI.addUserImgAPI(new ImgAddQO(imgFile))])
      socialUserModule.setUser(res[1].data)
    } catch (e) {
      console.error(e)
    } finally {
      UniUtil.hideLoading()
    }
  }

  async toPhonePage() {
    await PageUtil.toPhonePage()
  }

  toIdentityAuth() {
    MsgUtil.identityAuthHint()
  }

  openVip() {
    PageUtil.toVipPage()
  }

  get isMine(): boolean {
    // 两个都有值，且两个都相等，才为自己
    return this.userProp && this.mineUser && this.userProp.id === this.mineUser.id
  }

  get talkIds() {
    if (this.talks.length) {
      return this.talks.map(item => item.id)
    }
    return []
  }

  copyText(textCopy: string) {
    UniUtil.textCopy(textCopy)
  }

  deleteTalk(talkId: number) {
    this.talks.splice(this.talks.findIndex(talk => talk.id === talkId), 1)
  }

  closeUserEditPop() {
    this.$refs.editPopup.close()
  }

  moreAction() {
    if (this.isMine) {
      const menuList: string [] = ['刷新', '编辑', '退出登录']
      UniUtil.actionSheet(menuList).then((index: number) => {
        if (index === 0) {
          this.refreshMine()
        } else if (index === 1) {
          this.openEditDialog()
        } else if (index === 2) {
          this.loginOut()
        }
      })
    }
  }

  /*mounted () {
    this.openEditDialog()
  }*/

  openEditDialog() {
    this.$refs.editPopup.open()
  }

  @Watch('user', {
    deep: true,
    immediate: true
  })
  watchUserChange(newUser: CenterUserDetailRO, oldUser: CenterUserDetailRO) {
    // 如果以前是null才查询
    if (!oldUser) {
      this.queryMineTalks()
    }
  }

  queryMineTalks() {
    if (this.userProp) {
      this.followStatus = FollowStatus.getFollowStatus(this.userProp)
      this.hasFollowed = this.userProp.hasFollowed
      TalkAPI.queryUserTalksAPI(this.userProp.id, this.talkIds).then((res: any) => {
        this.talks = res.data
      })
    }
  }


  getGenderIcon(user: CenterUserDetailRO) {
    return UserUtil.getGenderIcon(user)
  }

  getGenderBgColor(user: CenterUserDetailRO) {
    return UserUtil.getGenderBgColor(user)
  }

  toFollowVue() {
    if (this.isMine) {
      RouterUtil.navigateTo(PagePath.userFollow)
    }
  }

  toLoveValuePage() {
    if (this.mineUser) {
      PageUtil.toLoveValuePage()
    } else {
      MsgUtil.unLoginMessage()
    }
  }

  hintJusticeInfo() {
    ToastUtil.toastLong('正义值，正确举报会增加正义值')
  }

  hintBindTwice() {
    AlertUtil.hint('因本软件系统升级导致老用户绑定手机号需要操作两次，给您带来不便，我们在此致以歉意，望您能够谅解，我们会努力做的更好，谢谢您的支持')
  }

  addFollow() {
    if (this.mineUser) {
      if (!this.followBtnDisabled) {
        this.followBtnDisabled = true
        const followAdd: FollowAddVO = new FollowAddVO(this.userProp.id)
        // 如果已经关注
        if (this.followStatus === FollowStatus.follow) {
          this.hasFollowed = true
          if (this.userProp.hasBeFollowed) {
            // 进行关注操作
            this.followStatus = FollowStatus.eachFollow
          } else {
            this.followStatus = FollowStatus.followed
          }
          FollowAPI.addFollowAPI(followAdd).finally(() => {
            this.followBtnDisabled = false
          })
        } else {
          this.hasFollowed = false
          this.followStatus = FollowStatus.follow
          // 进行取消关注操作
          FollowAPI.cancelFollowAPI(followAdd).finally(() => {
            this.followBtnDisabled = false
          })
        }
      }
    } else {
      MsgUtil.unLoginMessage()
    }
  }

  getFollowStatusColor(followStatus: string) {
    return UserUtil.getFollowStatusColor(followStatus)
  }

  toFaceValuePage() {
    PageUtil.toFaceValuePage()
  }

  get imgUrls() {
    if (this.userProp && this.userProp.imgs) {
      return this.userProp.imgs.map(item => ImgUtil.getUserLargeImgUrl(item.src))
    } else {
      return []
    }
  }

  previewImage(e) {
    const current = e.target.dataset.src
    uni.previewImage({
      current: current,
      urls: this.imgUrls
    })
  }

  closeUploadImgHint() {
    this.showUploadImgHint = false
    uni.setStorageSync(Constants.showUploadImgHintKey, 'false')
  }

  refreshMine() {
    AlertUtil.confirm('是否刷新用户信息').then(() => {
      socialUserModule.getMineUserAction().then(() => {
        ToastUtil.toast('刷新成功')
      })
    })
  }

  loginOut() {
    socialUserModule.loginOut()
  }

  //前往贝壳页面
  toUserShell() {
    PageUtil.toShellPage()
  }

  /*switchOpenContact (openContact) {
    let actionMsg
    let hintMsg
    //修改后状态
    if (openContact) {
      actionMsg = '是否确定展示联系方式'
      hintMsg = '展示成功'
    } else {
      actionMsg = '是否确定隐藏联系方式'
      hintMsg = '隐藏成功'
    }
    Alert.confirm(actionMsg).then(() => {
      UserAPI.switchUserContactAPI(openContact).then(() => {
        Toast.toast(hintMsg)
      }).catch(() => {
        this.mineUser.openContact = !openContact
      })
    })
  }*/
}
</script>
