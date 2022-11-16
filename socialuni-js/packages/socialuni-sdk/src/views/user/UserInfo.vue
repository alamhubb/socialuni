<template>
  <view v-if="user" class="bg-default pb-100">
    <view class="mb-sm pt-200 bg-no-repeat"
          style="background-image: url('https://cdxapp-1257733245.file.myqcloud.com/socialuni/common/app/userDefaultTopImg.jpg')">
      <view class="pd-sm bg-white bt-radius-10">
        <view class="row-col-center py-sm">
          <image
              class="size65 bd-radius-xs mr-sm"
              mode="aspectFill"
              :src="user.avatar"
              @click="moreAction"
          />
          <view class="flex-auto row-between">
            <view class="flex-col flex-auto">
              <view class="text-md" :class="{'color-red':user.vipFlag}">
                {{ user.nickname }}
              </view>
              <view class="flex-row">
                <social-gender-tag class="mt-sm" :user="user"></social-gender-tag>
                <!--                <view v-if="userProp.vipFlag" class="cu-tag bg-red radius" @click="openVip">VIP</view>
                                <view v-else class="cu-tag bg-grey radius" @click="openVip">VIP</view>-->
              </view>
            </view>

            <view class="row-col-center" v-if="isMine">
              <q-button @click="toEditUserInfo" theme light round>
                <q-icon icon="edit-pen" size="16" class="mr-xs q-box-xs"/>
                编辑资料
              </q-button>
            </view>
            <view v-else-if="user.beFollow" class="row-col-center">
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
                {{ user.followNum }}
              </text>
              <text class="text-sm text-gray">关注</text>
            </view>

            <view class="px-lg line-height-1" @click.stop="toFollowVue">
              <text class="text-lg font-bold text-black row-center">
                {{ user.fansNum }}
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
            <q-button v-if="followStatus==='关注'"
                      @click="addFollow">
              {{ followStatus }}
            </q-button>
            <view v-else class="color-content" @click.stop="addFollow">{{ followStatus }}</view>
            <!--              <button v-else class="cu-btn round bd-gray bg-white" @click.stop="addFollow">已关注</button>-->
          </view>
        </view>

        <div class="flex-row pl-xs">
          <div v-if="user.identityAuth" class="q-tag-success q-box-nn" @click.stop="toIdentityAuth">
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
          地区：{{ user.city || '' }}
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
            <view v-if="user.phoneNum" class="row-col-center">
              {{ user.phoneNum }}
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

        <view v-if="isMine||user.schoolName" class="row-col-center mb-smm">
          <q-icon class="text-gray mr-xs" icon="mdi-school"/>
          学校名称：
          <div v-if="user.schoolName" @click="openSetSchoolDialog">
            {{ user.schoolName }}
          </div>
          <div v-else class="q-tag" @click="openSetSchoolDialog">设置大学名称</div>
        </view>

        <!--        <div v-if="isMine" class="row-col-center mb-smm">
                  <q-icon class="text-gray mr-xs" icon="account-fill"/>
                  联系方式：
                  <div v-if="user.contactInfo" @click="openSetContactInfo" class="row-col-center ">
                    <div class="q-tag">{{ user.contactInfo }}
                      <div class="ml-xs">({{ user.openContactInfo ? '他人可获取' : '他人不可获取' }})</div>
                    </div>
                  </div>
                  <div v-else class="row-col-center">
                    <div class="q-tag" @click="openSetContactInfo">设置联系方式</div>
                  </div>
                </div>
                <div v-else-if="user.openContactInfo" class="row-col-center mb-smm">
                  <q-icon class="text-gray mr-xs" icon="mdi-school"/>
                  联系方式：
                  <div class="q-tag">{{ user.contactInfo }}(点击获取联系方式)</div>
                </div>-->

        <user-school-edit-dialog ref="schoolEditDialog"></user-school-edit-dialog>

        <user-contact-info-edit-dialog ref="contactInfoEditDialog"></user-contact-info-edit-dialog>

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
      <!--      <view>
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
            </view>-->

    </view>

    <user-info-img :user="user"></user-info-img>
  </view>
</template>

<script lang="ts">
import QButton from "socialuni-view/src/components/QButton/QButton.vue";
import UserSchoolEditDialog from "./UserSchoolEditDialog.vue";
import QSearch from "socialuni-view/src/components/QSearch/QSearch.vue";
import QPopup from "socialuni-view/src/components/QPopup/QPopup.vue";
import QPcModel from "socialuni-view/src/components/QPcModel/QPcModel.vue";
import QIcon from "socialuni-view/src/components/QIcon/QIcon.vue";
import SocialGenderTag from "socialuni-view/src/components/SocialGenderTag/SocialGenderTag.vue";
import QRowItem from "socialuni-view/src/components/QRowItem/QRowItem.vue";
import {socialConfigModule} from "socialuni-sdk/src/store/store";
import {socialUserModule} from 'socialuni-sdk/src/store/store';
import {socialSystemModule} from "socialuni-sdk/src/store/store";
import {Options, Prop, Vue, Watch} from "vue-property-decorator";
import CenterUserDetailRO from "socialuni-api/src/model/social/CenterUserDetailRO";
import FollowStatus from "socialuni-constant/constant/FollowStatus";
import TalkVO from "socialuni-api/src/model/talk/TalkVO";
import PageUtil from "socialuni-sdk/src/utils/PageUtil";
import MsgUtil from "socialuni-sdk/src/utils/MsgUtil";
import UniUtil from "socialuni-sdk/src/utils/UniUtil";
import UserUtil from "socialuni-sdk/src/utils/UserUtil";
import PagePath from "socialuni-constant/constant/PagePath";
import ToastUtil from "socialuni-sdk/src/utils/ToastUtil";
import RouterUtil from "socialuni-sdk/src/utils/RouterUtil";
import AlertUtil from "socialuni-sdk/src/utils/AlertUtil";
import DomFile from "socialuni-api/src/model/DomFile";
import CosUtil from "socialuni-sdk/src/utils/CosUtil";
import SocialuniMineUserAPI from "socialuni-api/src/api/SocialuniMineUserAPI";
import ImgAddQO from "socialuni-api/src/model/user/ImgAddQO";
import TencentCosAPI from "socialuni-api/src/api/TencentCosAPI";

@Options({
  components: {
    QButton,
    // UserSchoolEditDialog,
    QSearch,
    QPopup,
    QPcModel,
    SocialGenderTag,
    QIcon,
    QRowItem,
  }
})
export default class UserInfo extends Vue {
  @Prop() user: CenterUserDetailRO

  $refs!: {
    reportDialog: any;
    // schoolEditDialog: UserSchoolEditDialog;
    // contactInfoEditDialog: UserContactInfoEditDialog;
  }

  get mineUser() {
    return socialUserModule.user
  }

  get appConfig() {
    return socialConfigModule.appConfig
  }

  get isIos() {
    return socialSystemModule.isIos
  }

  followBtnDisabled = false
  hasFollowed = false
  followStatus: string = FollowStatus.follow
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
    return this.user && this.mineUser && this.user.id === this.mineUser.id
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

  deleteTalk(talkId: string) {
    this.talks.splice(this.talks.findIndex(talk => talk.id === talkId), 1)
  }

  moreAction() {
    if (this.isMine) {
      const menuList: string [] = ['上传头像', '退出登录']
      UniUtil.actionSheet(menuList).then((index: number) => {
        if (index === 0) {
          this.uploadUserAvatarImg()
        } else if (index === 1) {
          this.loginOut()
        }
      })
    }
  }

  toEditUserInfo() {
    PageUtil.toEditMineInfo()
  }

  /*mounted () {
    this.openEditDialog()
  }*/

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
    if (this.user) {
      this.followStatus = FollowStatus.getFollowStatus(this.user)
      this.hasFollowed = this.user.hasFollowed
      SocialuniTalkAPI.queryUserTalksAPI(this.user.id, this.talkIds).then((res: any) => {
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
        const followAdd: FollowAddVO = new FollowAddVO(this.user.id)
        // 如果已经关注
        if (this.followStatus === FollowStatus.follow) {
          this.hasFollowed = true
          if (this.user.hasBeFollowed) {
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

  async uploadUserAvatarImg() {
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

  //前往贝壳页面
  toUserShell() {
    PageUtil.toShellPage()
  }

  openSetSchoolDialog() {
    if (this.isMine) {
      this.$refs.schoolEditDialog.open()
    }
  }

  openSetContactInfo() {
    if (this.isMine) {
      this.$refs.contactInfoEditDialog.open()
    }
  }

  /*getUserContactInfo(){
    if (!this.showUserContactBtnDisabled) {
      this.showUserContactBtnDisabled = true
      const userShell = this.mineUser.socialCoin
      if (userShell >= 10) {
        AlertUtil.confirm('是否消耗10个贝壳查看用户：' + this.pageUser.nickname + ' 的联系方式').then(() => {
          UserAPI.getUserContactAPI(this.userProp.id).then((res) => {
            this.userProp.contactAccount = res.data
            this.userProp.showUserContact = true
            this.mineUser.shell = userShell - 10
          })
        }).finally(() => {
          this.showUserContactBtnDisabled = false
        })
      } else {
        AlertUtil.confirm('您没有贝壳了，是否直接使用现金支付').then(() => {
          const provider = socialSystemModule.isApp ? ProviderType.wx : systemModule.provider
          PlatformUtils.pay(provider, PayType.shell, 1).then(() => {
            UserAPI.getUserContactAPI(this.userProp.id).then((res) => {
              this.userProp.contactAccount = res.data
              this.userProp.showUserContact = true
            }).catch((e) => {
              UniUtil.error(e)
            })
          }).catch(() => {
            MsgUtil.notPay()
          })
        }).finally(() => {
          this.showUserContactBtnDisabled = false
        })
      }
    } else {
      UniUtil.toast('获取中，请稍等')
    }
  }*/


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
