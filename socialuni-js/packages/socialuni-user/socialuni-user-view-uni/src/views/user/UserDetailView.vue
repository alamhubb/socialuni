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
              class="size65 bd-radius-xs mr-sm bd"
              mode="aspectFill"
              :src="user.avatar"
              @click="seeAvatarDetail"
            />
            <view class="flex-1 row-between h65 py-xs">
              <view class="flex-col flex-1">
                <view class="text-md font-bold" :class="{'color-red':user.vipFlag}">
                  {{ user.nickname }}
                </view>
                <view class="row-col-center mt-sm">
                  <social-gender-tag :user="user"></social-gender-tag>
                  <!--                <view v-if="userProp.vipFlag" class="cu-tag bg-red radius" @click="openVip">VIP</view>
                                  <view v-else class="cu-tag bg-grey radius" @click="openVip">VIP</view>-->
                </view>
              </view>

              <div class="col-center flex-none">
                <view class="row-end-center mr-xss">
                  <view v-if="user.hasBeFollowed && !user.hasFollowed"
                        class="px-5 py-1 bg-default">
                    <div class="color-content font-12">
                      对方关注了您
                    </div>
                  </view>
                </view>
                <!--              不为自己才展示-->
                <view class="row-col-center mt-sm">
                  <!--                不为自己且未关注-->
                  <!--            不为ios，或者不为付费，则显示-->
                  <!--              <q-button v-if="!isIos||!user.chat.needPayOpen" @click="toMessagePage">-->
                  <q-button @click="toMessagePage" class="mr-sm">
                    <q-icon icon="mdi-chat-outline" size="14"></q-icon>
                    私信
                  </q-button>
                  <socialuni-follow-tag :user="user"></socialuni-follow-tag>
                  <!--              <button v-else class="cu-btn round bd-gray bg-white" @click.stop="addFollow">已关注</button>-->
                </view>
              </div>
            </view>
          </view>

          <view class="row-between-center mt-sm py-xs pr-xs">
            <view class="flex-row flex-1 row-around">
              <view class="px-lg line-height-1 col-all-center">
                <text class="text-lg font-bold color-main row-center">
                  {{ user.followNum }}
                </text>
                <text class="font-12 color-content mt-xs">关注</text>
              </view>
              <view class="px-lg line-height-1 col-all-center">
                <text class="text-lg font-bold color-main row-center">
                  {{ user.fansNum }}
                </text>
                <text class="font-12 color-content mt-xs">被关注</text>
              </view>
            </view>
          </view>

          <div class="flex-row my-sm">
            <view v-if="user.city" class="row-col-center q-tag">
              <q-icon icon="map-fill" class="color-purple mr-mn" size="12" />
              {{ user.city || '' }}
            </view>
            <view v-if="user.schoolName" class="row-col-center q-tag">
              <q-icon class="color-blue mr-xs" icon="mdi-school" size="12" />
              {{ user.schoolName }}
            </view>
          </div>
        </view>
      </view>


      <div v-if="!isIos && user.contactInfo"
           class="row-between-center bg-white px-sm py-sm bd-radius-10 elevation-4 mx-sm mt-smm mb-sm">
        <div class="row-col-center">
          <q-icon class="color-sub mr-xs" prefix="uni-icons" icon="uniui-chat" size="14" />
          联系方式：
          <!--          如果开启了，则代表获取过，无需再次获取，点击为复制-->
          <div v-if="user.openContactInfo" class="use-click row-col-center">
            <q-button light @click="copyContactInfo">{{ user.contactInfo }}
              <div class="color-content ml-xs font-12">
                ( 点击复制 )
              </div>
            </q-button>
          </div>
          <div v-else class="use-click row-col-center">
            <q-button light @click="getOpenContactInfo" :disabled="showUserContactBtnDisabled">
              {{ user.contactInfo }}
              <div class="color-content ml-xs font-12">
                (点击获取)
              </div>
            </q-button>
          </div>
        </div>
      </div>

      <socialuni-user-info-img :user="user" v-if="user.imgs.length"></socialuni-user-info-img>

      <q-popup ref="applyUserFriendDialog" title="申请添加好友111" @confirm="addFriend">
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
      <!--      <ad v-if="talks.length>1" class="bg-white mb-5 w100vw" unit-id="adunit-65c8911d279d228f" ad-type="video"
                ad-theme="white"></ad>-->
      <!--  #endif -->
      <!--qq平台显示的广告-->
      <!--  #ifdef MP-QQ -->
      <!--      <ad v-if="talks.length>0" class="bg-white mb-5 w100vw" unit-id="72d8cb09a1bae9fa30d9e03e7cb8a25d"
                type="feeds" ad-height="160"></ad>-->
      <!--  #endif -->
      <!--  #ifdef APP-PLUS -->
      <!--      <ad v-if="talks.length>0" class="bg-white mb-5 w100vw" adpid="1890536227"></ad>-->
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
      <uni-list class="pb-xl">
        <uni-list-item title="解除好友" link @click="deleteFriend"></uni-list-item>
        <uni-list-item title="加入黑名单" link @click="addBlack"></uni-list-item>
        <uni-list-item title="从黑名单移除" link @click="removeBlack"></uni-list-item>
      </uni-list>
    </q-popup>
  </view>
</template>

<script lang="ts">
import { Options, Prop, Vue, Watch } from 'vue-property-decorator'
import CenterUserDetailRO from "socialuni-api-base/src/model/social/CenterUserDetailRO"
import UserSchoolEditDialog from "./UserSchoolEditDialog.vue"
import TalkVO from "socialuni-api-base/src/model/talk/TalkVO"
import FollowStatus from "socialuni-constant/constant/FollowStatus"
import SocialuniUserInfoImg from "./SocialuniUserInfoImg.vue"
import SocialuniFriendAPI from "socialuni-im-api/src/api/SocialuniFriendAPI"
import FriendAddQO from "socialuni-im-api/src/model/QO/firend/FriendAddQO"
import QPopup from "socialuni-ui-uni/src/components/QPopup/QPopup.vue"
import QIcon from "socialuni-ui-uni/src/components/QIcon/QIcon.vue"
import QInput from "socialuni-ui-uni/src/components/QInput/QInput.vue"
import QButton from "socialuni-ui-uni/src/components/QButton/QButton.vue"
import QSearch from "socialuni-ui-uni/src/components/QSearch/QSearch.vue"
import QNavbar from "socialuni-ui-uni/src/components/QNavbar/QNavbar.vue"
import SocialuniFollowTag from "socialuni-community-view-uni/src/components/SocialuniFollow/SocialuniFollowTag.vue"
import MsgInput from "socialuni-community-view-uni/src/components/MsgInput.vue"
import SocialGenderTag from "../../components/SocialGenderTag/SocialGenderTag.vue"
import QRowItem from "socialuni-ui-uni/src/components/QRowItem/QRowItem.vue"
import SocialuniUserAPI from "socialuni-user-api/src/api/SocialuniUserAPI"
import { socialChatModule } from "socialuni-im-sdk/src/store/SocialChatModule"
import { socialuniSystemModule } from "socialuni-util/src/store/SocialuniSystemModule"
import SocialuniTalkAPI from "socialuni-community-api/src/api/SocialuniTalkAPI"
import { onLoad } from "@dcloudio/uni-app"
import SocialuniAddFriendType from "socialuni-im-api/src/constant/SocialuniAddFriendType"
import SocialuniImUserDetailRO from 'socialuni-im-api/src/model/RO/SocialuniImUserDetailRO'
import SocialuniAppUtil from "socialuni-native-util/src/util/SocialuniAppUtil";
import {socialuniConfigModule} from "socialuni-app-sdk/src/store/SocialuniConfigModule";
import {socialuniUserModule} from "socialuni-user-sdk/src/store/SocialuniUserModule";

@Options({
  components: {
    SocialuniFollowTag,
    QPopup, QIcon, QNavbar,
    MsgInput,
    QInput,
    // TalkItem,
    // TalkOperate,
    QButton,
    UserSchoolEditDialog,
    QSearch,
    // QPcModel,
    SocialGenderTag,
    QRowItem,
    SocialuniUserInfoImg
  }
})
export default class UserDetailView extends Vue {
  $refs: {
    moreActionMenu: QPopup
    reportDialog: any;
    schoolEditDialog: UserSchoolEditDialog;
    applyUserFriendDialog: QPopup;
    // contactInfoEditDialog: UserContactInfoEditDialog;
  }

  user: CenterUserDetailRO = null
  showUserContactBtnDisabled: boolean = false
  imUserDetail: SocialuniImUserDetailRO = null

  openMoreMenu() {
    this.$refs.moreActionMenu.open()
  }

  created() {
    SocialuniAppUtil.UniUtil.showShareMenu()

    /*onShow(() => {
        this.showMsgInput = true
    })

    onHide(() => {
        this.showMsgInput = false
    })*/

    onLoad((params: {userId: string}) => {
      const userId = params.userId
      // 这里有问题，有时候直接进入页面没有userId
      SocialuniUserAPI.queryUserDetailAPI(userId).then((res: any) => {
        this.user = res.data
        if (!this.user.isMine) {
          //1. 是否被拉黑
          //1. 是否接收陌生人消息，如果接收，则

          // socialChatModule.checkFriend(this.user)
          // socialChatModule.setCurChatByUserId(this.user.id)
        }
      })
    })
  }


  /**
   * 从好友列表中删除用户。
   */
  async deleteFriend() {
    SocialuniAppUtil.AlertUtil.confirm('是否确认解除好友关系').then(async () => {
      (await socialChatModule.openIm()).deleteFriend(this.user.id).then(({ data }) => {
        console.log('deleteFriend', data)
        SocialuniAppUtil.ToastUtil.toast('成功解除好友关系')
        socialChatModule.checkFriend(this.user)
      }).catch(err => {
      })
    })
  }

  /**
   * 将用户添加到黑名单。
   */
  async addBlack() {
    SocialuniAppUtil.AlertUtil.confirm('是否确认添加到黑名单').then(async () => {
      (await socialChatModule.openIm()).addBlack(this.user.id).then(({ data }) => {
        socialChatModule.checkFriend(this.user)
      }).catch(err => {
      })
    })

  }

  /**
   * 将用户从黑名单移除。
   */
  async removeBlack() {
    SocialuniAppUtil.AlertUtil.confirm('是否确认从黑名单移除').then(async () => {
      (await socialChatModule.openIm()).removeBlack(this.user.id).then(({ data }) => {
        socialChatModule.checkFriend(this.user)
      }).catch(err => {
      })
    })

  }

  applyUserFriendContent: string = null


  get appConfig() {
    return socialuniConfigModule.appConfig
  }

  get isIos() {
    return socialuniSystemModule.isIos
  }

  talks: TalkVO[] = []

  get followStatus() {
    return FollowStatus.getFollowStatus(this.user)
  }

  async toMessagePage() {
    //无论什么情况都可以进入消息页面，比如要查看两个人的历史聊天消息呢
    socialChatModule.setChatIdToMessagePage(this.user.id)

    //未登录不能发消息
    //然后判断对方是否接收陌生人消息
    //不接收，判断是否是好友
    //不是好友则提示对方不接收陌生人消息，是否添加好友

    //不需要好友功能了，
    //可以付费消息。 每日可免费领取 1金豆？
    //

    //可以付费发送消息，默认关注对方，对方给你回消息免费。
    //如果你取消关注对方，


    //我有两个发消息的校验逻辑
    //用哪一个？

      //发起付费，则关注对方。
      //发送消息关注吗？

      //对方关注了你，你就能给对方发消息。
      //允许陌生人消息，谁都可以给你发消息。 你可以选择给对方拉黑， 也可以选择不接受陌生人消息
      //然后给你发消息，就需要付费。 付费以后默认关注了你，你给对方发消息就免费？

      //如果你给对方发了消息，如果对方为陌生人，则你必须要开启接收陌生人消息。
      //如果对方是收费的，则代表你关注了对方，付费就关注，然后对方可以免费给你发消息，付费发3条？， 然后呢你不需要打开陌生人消息

      //能不能发消息，

    //应该能进入页面的，
    // if (this.imUserDetail.blackUser){
    //   SocialuniAppUtil.ToastUtil.toast("您已被对方拉黑，无法发送消息")
    // }
    //未关注，但是也可以都是可以进入聊天页面的


    // if (this.user.hasBeFollowed) {
    //   this.applyUserFriendContent = null
    //   this.$refs.applyUserFriendDialog.open()
    // } else {
    // }
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
    /*const options: AddFriendParams = {
      toUserID: this.user.id,
      reqMsg: this.applyUserFriendContent
    };
*/
    console.log('123123')
    await SocialuniFriendAPI.addFriend(new FriendAddQO(this.user.id, this.applyUserFriendContent, SocialuniAddFriendType.apply))
    // await (await socialChatModule.openIm()).addFriend(options)
    SocialuniAppUtil.ToastUtil.toastLong('添加好友申请发送成功，请耐心等待对方回复')
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
    SocialuniAppUtil.UniUtil.textCopy(textCopy)
  }

  deleteTalk(talkId: string) {
    this.talks.splice(this.talks.findIndex(talk => talk.id === talkId), 1)
  }

  @Watch('user', {
    deep: true,
    immediate: true
  })
  watchUserChange(newUser: CenterUserDetailRO, oldUser: CenterUserDetailRO) {
    // 如果以前是null才查询
    if (!oldUser) {
      this.queryUserTalks()
    }
  }

  queryUserTalks() {
    if (this.user) {
      SocialuniTalkAPI.queryUserTalksAPI(this.user.id, this.talkIds).then((res: any) => {
        this.talks = res.data
      })
    }
  }

  seeAvatarDetail() {
    uni.previewImage({
      current: 0,
      urls: [this.user.avatar]
    })
  }

  copyContactInfo() {
    SocialuniAppUtil.UniUtil.textCopy(this.user.contactInfo)
  }

  get mineUser() {
    return socialuniUserModule.mineUser
  }

  async getOpenContactInfo() {
    //打开获取对方联系方式功能，支付贝壳
    this.showUserContactBtnDisabled = true
    try {
      await socialuniUserModule.getOpenContactInfo(this.user)
    } finally {
      this.showUserContactBtnDisabled = false
    }
  }

}
</script>
