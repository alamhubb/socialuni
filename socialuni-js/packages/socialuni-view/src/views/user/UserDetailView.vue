<template>
  <view class="bg-default">
    <q-navbar show-back title="用户详情">
      <div class="row-end-center flex-1">
        <q-icon icon="list-dot" size="20" @click="openMoreMenu"></q-icon>
      </div>
    </q-navbar>
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
            <view class="flex-1 row-between">
              <view class="flex-col flex-1">
                <view class="text-md" :class="{'color-red':user.vipFlag}">
                  {{ user.nickname }}
                </view>
                <view class="flex-row">
                  <social-gender-tag class="mt-sm" :user="user"></social-gender-tag>
                  <!--                <view v-if="userProp.vipFlag" class="cu-tag bg-red radius" @click="openVip">VIP</view>
                                  <view v-else class="cu-tag bg-grey radius" @click="openVip">VIP</view>-->
                </view>
              </view>

              <!--              不为自己才展示-->
              <view class="row-col-center">
                <!--                不为自己且未关注-->
                <!--            不为ios，或者不为付费，则显示-->
                <!--              <q-button v-if="!isIos||!user.chat.needPayOpen" @click="toMessagePage">-->
                <q-button @click="toMessagePage" class="mr-sm">
                  <q-icon icon="mdi-chat-outline" size="14"></q-icon>
                  私信
                </q-button>
                <q-button v-if="!isMine" @click="addFollow">
                  {{ followStatus }}
                </q-button>
                <view v-else class="color-content" @click.stop="addFollow">{{ followStatus }}</view>
                <!--              <button v-else class="cu-btn round bd-gray bg-white" @click.stop="addFollow">已关注</button>-->
              </view>
            </view>
          </view>
          <view class="row-between-center py-xs pr-xs">
            <view v-if="user.hasBeFollowed" class="row-col-center">
              <view class="bg-default text-sm px-xs text-gray">
                对方关注了您
              </view>
            </view>
          </view>

          <view v-if="user.city" class="row-col-center my">
            <q-icon class="text-gray mr-xs" icon="map-fill"/>
            {{ user.city || '' }}
          </view>

          <view v-if="user.schoolName" class="row-col-center mb-smm">
            <q-icon class="text-gray mr-xs" icon="mdi-school"/>
            {{ user.schoolName }}
          </view>

          <user-school-edit-dialog ref="schoolEditDialog"></user-school-edit-dialog>

          <user-contact-info-edit-dialog ref="contactInfoEditDialog"></user-contact-info-edit-dialog>


        </view>
      </view>

      <socialuni-user-info-img :user="user" v-if="user.imgs.length"></socialuni-user-info-img>

      <q-popup ref="applyUserFriendDialog" title="申请添加好友" @confirm="addFriend">
        <div class="pd">
          <div>
            添加好友后才可发起会话，申请原因:
          </div>
          <div class="mt-sm mb">
            <q-input v-model="applyUserFriendContent"></q-input>
          </div>
        </div>
      </q-popup>

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

      <div class="px-smm mb-xs">动态</div>
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
    </view>

    <q-popup ref="moreActionMenu" bottom>
      <uni-list class="pb-xl mt-sm">
        <uni-list-item title="解除好友" link @click="deleteFriend"></uni-list-item>
        <uni-list-item title="加入黑名单" link @click="addBlack"></uni-list-item>
        <uni-list-item title="从黑名单移除" link @click="removeBlack"></uni-list-item>
      </uni-list>
    </q-popup>
  </view>
</template>

<script lang="ts">
import {Options, Prop, Vue, Watch} from 'vue-property-decorator'
import CenterUserDetailRO from "socialuni-api/src/model/social/CenterUserDetailRO";
import UniUtil from "socialuni-sdk/src/utils/UniUtil";
import MsgInput from "socialuni-view/src/components/MsgInput.vue";
import {onHide, onLoad, onShow} from "@dcloudio/uni-app";
import UserInfo from "socialuni-view/src/views/user/UserInfo.vue";
import {
  socialChatModule,
  socialConfigModule,
  socialSystemModule,
  socialUserModule
} from "socialuni-sdk/src/store/store";
import {AddFriendParams} from "open-im-sdk";
import {onMounted} from "vue";
import SocialuniUserAPI from "socialuni-api/src/api/socialuni/SocialuniUserAPI";
import QNavbar from "../../components/QNavbar/QNavbar.vue";
import QIcon from "../../components/QIcon/QIcon.vue";
import QPopup from "../../components/QPopup/QPopup.vue";
import AlertUtil from "socialuni-sdk/src/utils/AlertUtil";
import ToastUtil from "socialuni-sdk/src/utils/ToastUtil";
import UserSchoolEditDialog from "./UserSchoolEditDialog.vue";
import TalkVO from "socialuni-api/src/model/talk/TalkVO";
import FollowStatus from "socialuni-constant/constant/FollowStatus";
import PageUtil from "socialuni-sdk/src/utils/PageUtil";
import MsgUtil from "socialuni-sdk/src/utils/MsgUtil";
import SocialuniTalkAPI from "socialuni-api/src/api/socialuni/SocialuniTalkAPI";
import UserUtil from "socialuni-sdk/src/utils/UserUtil";
import FollowAddVO from "socialuni-api/src/model/FollowAddVO";
import FollowAPI from "socialuni-api/src/api/socialuni/FollowAPI";
import QInput from "../../components/QInput/QInput.vue";
import TalkItem from "../talk/talkItem/TalkItem.vue";
import TalkOperate from "../talk/talkOperate.vue";
import QButton from "../../components/QButton/QButton.vue";
import QSearch from "../../components/QSearch/QSearch.vue";
import QPcModel from "../../components/QPcModel/QPcModel.vue";
import SocialGenderTag from "../../components/SocialGenderTag/SocialGenderTag.vue";
import QRowItem from "../../components/QRowItem/QRowItem.vue";
import SocialuniUserInfoImg from "./SocialuniUserInfoImg.vue";

@Options({
  components: {
    QPopup, QIcon, QNavbar, MsgInput, UserInfo,
    QInput,
    TalkItem,
    TalkOperate,
    QButton,
    UserSchoolEditDialog,
    QSearch,
    QPcModel,
    SocialGenderTag,
    QRowItem,
    SocialuniUserInfoImg
  }
})
export default class UserDetailView extends Vue {
  $refs: {
    moreActionMenu: QPopup
  }

  user: CenterUserDetailRO = null

  openMoreMenu() {
    this.$refs.moreActionMenu.open()
  }

  init(params: { userId: string }) {
    const userId = params.userId
    // 这里有问题，有时候直接进入页面没有userId
    SocialuniUserAPI.queryUserDetailAPI(userId).then((res: any) => {
      this.user = res.data
      if (!this.user.isMine) {
        socialChatModule.checkFriend(this.user);
        // socialChatModule.setCurChatByUserId(this.user.id)
      }
    })
  }


  /**
   * 从好友列表中删除用户。
   */
  deleteFriend() {
    AlertUtil.confirm('是否确认解除好友关系').then(res => {
      socialChatModule.openIm.deleteFriend(this.user.id).then(({data}) => {
        console.log('deleteFriend', data);
        ToastUtil.toast('成功解除好友关系')
        socialChatModule.checkFriend(this.user);
      }).catch(err => {
      })
    })
  }

  /**
   * 将用户添加到黑名单。
   */
  addBlack() {
    AlertUtil.confirm('是否确认添加到黑名单').then(res => {
      socialChatModule.openIm.addBlack(this.user.id).then(({data}) => {
        socialChatModule.checkFriend(this.user);
      }).catch(err => {
      })
    })

  }

  /**
   * 将用户从黑名单移除。
   */
  removeBlack() {
    AlertUtil.confirm('是否确认从黑名单移除').then(res => {
      socialChatModule.openIm.removeBlack(this.user.id).then(({data}) => {
        socialChatModule.checkFriend(this.user);
      }).catch(err => {
      })
    })

  }

  applyUserFriendContent: string = null

  $refs!: {
    reportDialog: any;
    schoolEditDialog: UserSchoolEditDialog;
    applyUserFriendDialog: QPopup;
    // contactInfoEditDialog: UserContactInfoEditDialog;
  }

  get mineUser() {
    return socialUserModule.mineUser
  }

  get appConfig() {
    return socialConfigModule.appConfig
  }

  get isIos() {
    return socialSystemModule.isIos
  }

  followBtnDisabled = false
  talks: TalkVO[] = []
  sendFriendApplicationList: Object[] = []
  recvFriendApplicationList: Object[] = []
  blackList: Object[] = []
  friendList: Object[] = []
  showUserContactBtnDisabled = false

  get followStatus() {
    return FollowStatus.getFollowStatus(this.user)
  }

  async toMessagePage() {
    if (!this.user.hasFriend) {
      this.applyUserFriendContent = null
      this.$refs.applyUserFriendDialog.open()
    } else {
      socialChatModule.toMessagePageFromUserDetail(this.user.id)
    }
    // socialChatModule.setCurChatByUserId(this.user.id)
    //除了是否关注，还有是否已经发起过对话，chatuservo里面要保存还能再发几条
    //判断是否已经支付过了。3条，然后对方每次回复你都可以发三条，然后就需要再次支付，开启了支付
    //mock chat
    // chatModule.userDetailToMessagePage(this.userProp.chat)
    //如果有chat读取，如果没有创建读取
    // chatModule.setChatAction(chat)
  }


  /**
   * 添加好友申请。
   */
  async addFriend() {
    // socialChatFriendModule.addFriend(this.user.id, "请求加好友");
    const options: AddFriendParams = {
      toUserID: this.user.id,
      reqMsg: this.applyUserFriendContent
    };
    await socialChatModule.openIm.addFriend(options)
    ToastUtil.toastLong('添加好友申请发送成功，请耐心等待对方回复')
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
    return this.user.isMine
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

  async addFollow() {
    if (this.mineUser) {
      if (!this.followBtnDisabled) {
        this.followBtnDisabled = true
        const followAdd: FollowAddVO = new FollowAddVO(this.user.id)
        // 如果已经关注
        if (this.user.hasFollowed) {
          this.user.hasFollowed = false
          try {
            // 进行取消关注操作
            await FollowAPI.cancelFollowAPI(followAdd)
          } catch (e) {
            this.user.hasFollowed = true
          } finally {
            this.followBtnDisabled = false
          }
        } else {
          this.user.hasFollowed = true
          try {
            await FollowAPI.addFollowAPI(followAdd)
          } catch (e) {
            this.user.hasFollowed = false
          } finally {
            this.followBtnDisabled = false
          }
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


  //前往贝壳页面
  toUserShell() {
    PageUtil.toShellPage()
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
