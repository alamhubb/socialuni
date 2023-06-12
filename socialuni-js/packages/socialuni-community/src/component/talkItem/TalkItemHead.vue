<template>
  <view class="card-title pb-10" @click="toUserDetailVue" v-if="talk">
    <image
      class="size40 bd-radius mr-sm"
      mode="aspectFill"
      :src="talk.user.avatar"
    />
    <view class="row-between flex-1">
      <view class="col-center">
        <view class="h20 row-col-center">
          <text class="text-df font-bold">{{ talk.user.nickname }}</text>
          <!--          <text class="text-md" :class="{'color-red':home.user.vipFlag}">{{ home.user.nickname }}</text>-->
          <template v-if="!talk.globalTop">
            <social-gender-tag class="ml-xs" :user="talk.user"></social-gender-tag>
            <!--            <div v-if="home.user.identityAuth" class="q-tag-success q-box-nn" @click.stop="toIdentityAuth">
                          <q-icon size="14" icon="level"/>
                        </div>-->
            <!--              <q-icon class="color-blue" size="18" icon="level" @click.stop="toIdentityAuth"/>-->
          </template>

          <!--          <view v-if="!home.globalTop" class="ml-5 cu-tag sm radius text-sm row-col-center"
                          :class="[getGenderBgColor(home.user)]">
                      {{ home.user.age }}
                      <q-icon class="ml-nm"
                              size="12"
                              :icon="getGenderIcon(home.user)"/>
                    </view>-->
          <!--          <view v-if="home.user.vipFlag" class="ml-5 cu-tag bg-red radius sm text-sm font-bold"
                          @click.stop="openVip">
                      VIP
                    </view>-->
          <!--    如果爱心值不为0，且大于正义值显示爱心值-->
          <!--          <view v-else-if="home.user.loveValue&& home.user.loveValue>home.user.justiceValue"
                          class="ml-5 cu-capsule radius"
                          @click.stop="toLoveValuePage">
                      <view class='cu-tag bg-red sm'>
                        <q-icon icon="heart"/>
                      </view>
                      <view class="cu-tag bg-white bd-red bd-r-radius sm">
                        {{ home.user.loveValue }}
                      </view>
                    </view>-->
          <!--    如果正义值不为0，且大于等于爱心值显示正义值-->
          <!--          <view v-else-if="home.user.justiceValue&& home.user.justiceValue >= home.user.loveValue"
                          class="ml-5 cu-capsule radius"
                          @click.stop="hintJusticeInfo">
                      <view class='cu-tag bg-green sm'>
                        <q-icon icon="mdi-sword-cross"/>
                      </view>
                      <view class="cu-tag bg-white bd-green bd-r-radius sm">
                        {{
                          home.user.justiceValue > 1000 ? Math.floor(home.user.justiceValue / 1000) + 'k' : home.user.justiceValue
                        }}
                      </view>
                    </view>-->
        </view>

        <!--        <view class="color-sub text-sm h20 row-col-end" v-if="home.user.identityAuth || home.globalTop ||isMine">-->
        <view class="color-sub text-sm h20 row-col-end line-h1">
          {{ formatTime(talk.updateTime) }}
          <view v-if="talk.globalTop" class="ml-sm q-tag-theme q-box-nn">
            官方
          </view>
          <!--              自己的帖子，或者系统管理员可以删除帖子-->
          <text v-if="isMine"
                class="ml-5 color-blue1 bg-click row-col-end line-h1"
                @click.stop="confirmDeleteTalk">
            删除
          </text>
          <text v-if="talk.status !== SocialuniCommonStatus.enable"
                class="ml-5 color-red bg-click row-col-end line-h1">
            审核中
          </text>
        </view>
      </view>
      <!--                不为自己且未关注-->
      <view v-if="talkTabType!==followType&&!isMine&&!isUserDetail" class="col-center">
        <div v-if="!talk.hasFollowed" class="color-content chunk q-box-mn bd-radius" @click.stop="addFollow">关注</div>
        <div v-else class="color-content" @click.stop="addFollow">已关注</div>
      </view>
    </view>
  </view>
</template>

<script lang="ts">
import { Options, Prop, Vue, Watch } from 'vue-property-decorator'
import TalkVO from "socialuni-api-base/src/model/talk/TalkVO"
import ObjectUtil from "socialuni-util/src/util/ObjectUtil"
import TalkTabType from "socialuni-constant/constant/TalkTabType"
import PagePath from "socialuni-constant/constant/PagePath"
import MsgUtil from "socialuni-app/src/util/MsgUtil"
import FollowAddVO from "socialuni-api-base/src/model/FollowAddVO"
import CenterUserDetailRO from "socialuni-api-base/src/model/social/CenterUserDetailRO"
import QIcon from "socialuni-ui-uni/src/components/QIcon/QIcon.vue"
import DateUtil from "socialuni-util/src/util/DateUtil"
import SocialuniTalkAPI from "socialuni-community-api/src/api/SocialuniTalkAPI"
import FollowAPI from "socialuni-community-api/src/api/FollowAPI"
import SocialuniCommonStatus from 'socialuni-constant/constant/status/SocialuniCommonStatus'
import {socialuniUserModule} from "socialuni-user/src/store/SocialuniUserModule";
import SocialuniAppUtil from "socialuni-native-util/src/util/SocialuniAppUtil";
import UserPagePath from "socialuni-user/src/constant/UserPagePath";
import UserPageUtil from "socialuni-user/src/util/UserPageUtil";
import UserMsgUtil from "socialuni-user/src/util/UserMsgUtil";
import UserUtil from "socialuni-user/src/util/UserUtil";

@Options({
  components: {
    SocialGenderTag,
    QIcon
  }
})
export default class TalkItemHead extends Vue {
  @Prop() talkProp!: TalkVO
  @Prop() talkTabType: string

  // 因为需要修改关注状态，所以需要克隆
  talk: TalkVO = null

  get user() {
    return socialuniUserModule.mineUser
  }

  followBtnDisabled = false
  isUserDetail = false
  followType: string = TalkTabType.follow_type

  SocialuniCommonStatus = SocialuniCommonStatus

  created() {
    if (SocialuniAppUtil.RouterUtil.getCurrentPageURI() === UserPagePath.userDetail) {
      this.isUserDetail = true
    }
    this.updateTalk()
  }

  @Watch('talkProp')
  talkPropWatch() {
    this.updateTalk()
  }

  formatTime(dateStr) {
    return DateUtil.formatTime(dateStr)
  }

  updateTalk() {
    this.talk = ObjectUtil.deepClone(this.talkProp)
  }

  toIdentityAuth() {
    MsgUtil.identityAuthHint()
  }

  hintJusticeInfo() {
      SocialuniAppUtil.ToastUtil.toastLong('正义值，正确举报会增加正义值')
  }

  // 自己不为null，且是自己
  get isMine(): boolean {
    return this.user && this.user.id === this.talk.user.id
  }

  toUserDetailVue() {
    if (SocialuniAppUtil.RouterUtil.getCurrentPageURI() !== UserPagePath.userDetail) {
      UserPageUtil.toUserDetail(this.talk.user.id)
    }
  }

  confirmDeleteTalk() {
      SocialuniAppUtil.AlertUtil.confirm('是否确定删除此条动态，此操作无法恢复').then(() => {
      this.$emit('deleteTalk', this.talk.id)
      SocialuniTalkAPI.deleteTalkAPI(this.talk.id).then(() => {
          SocialuniAppUtil.ToastUtil.toast('删除成功')
      })
    })
  }

  openVip() {
    UserPageUtil.toVipPage()
  }

  addFollow() {
    if (this.user) {
      if (!this.followBtnDisabled) {
        const followAdd: FollowAddVO = new FollowAddVO(this.talk.user.id)
        if (this.talk.hasFollowed) {
            SocialuniAppUtil.AlertUtil.confirm('是否取消关注用户：' + this.talk.user.nickname).then(() => {
            this.followBtnDisabled = true
            this.talk.hasFollowed = false
            FollowAPI.cancelFollowAPI(followAdd).finally(() => {
              this.followBtnDisabled = false
            })
          })
        } else {
          this.followBtnDisabled = true
          this.talk.hasFollowed = true
          FollowAPI.addFollowAPI(followAdd).catch(()=>{
            this.talk.hasFollowed = false
          }).finally(() => {
            this.followBtnDisabled = false
          })
        }
      }
    } else {
      UserMsgUtil.unLoginMessage()
    }
  }

  getGenderIcon(user:
                  CenterUserDetailRO
  ) {
    return UserUtil.getGenderIcon(user)
  }

  getGenderBgColor(user:
                     CenterUserDetailRO
  ) {
    return UserUtil.getGenderBgColor(user)
  }
}
</script>
