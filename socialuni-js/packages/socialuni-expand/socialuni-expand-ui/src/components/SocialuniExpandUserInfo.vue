<template>
  <div class="flex-col px-smm py-sm bb"
       @click="toUserDetailVue(user)">
    <div class="row-col-center">
      <img
          class="card-title-avatar bd flex-none"
          mode="aspectFill"
          :src="user.avatar"
      />
      <div class="flex-1 row-between-center py-xs">
        <div class="flex-col flex-1">
          <div class="row-between-center">
            <div class="row-col-center">
              <span :class="{'color-red':user.vipFlag}">{{ user.nickname }}</span>
              <div v-if="user.vipFlag" class="ml-5px cu-tag bg-orange radius sm"
                   @click.stop="openVip">
                VIP
              </div>
              <social-gender-tag class="ml-xs" :user="user"></social-gender-tag>
            </div>
          </div>
          <!--          <div class="row-col-center mt-xss font-12 color-content">
                      &lt;!&ndash;                          {{ formatTime(user.updateTime) }}&ndash;&gt;
                      &lt;!&ndash;                          <div class="px-xs row-col-center">|</div>&ndash;&gt;
                      &lt;!&ndash;        有市区的名称就不显示省的名称&ndash;&gt;
                      <span v-if="!user.cityName || !user.districtName">{{ user.provinceName }}</span>
                      <span v-if="user.cityName">
                                      <span v-if="!user.districtName">-</span>
                                      {{ user.cityName.substring(0, 6) }}
                                    </span>
                      <span v-if="user.districtName">-{{ user.districtName }}</span>

                      <div class="row-col-center" v-if="user.distance|| user.distance===0">
                        <div class="px-xs row-col-center">|</div>
                        <span v-if="user.distance<0.5">{{ 0.5 }}公里</span>
                        <span v-else-if="user.distance<1">{{ 1 }}公里</span>
                        <span v-else-if="user.distance<5">{{ 5 }}公里</span>
                        <span v-else>{{ numFixed1(user.distance) }}公里</span>
                      </div>
                    </div>-->
        </div>
        <div class="col-center flex-none">
          <!--                      <div v-if="!isIos" class="col-center flex-none">-->
          <div v-if="user.openContactInfo" class="use-click row-col-center">
            <q-button light @click="copyContactInfo(user)">
              <div class="color-content ml-xs font-12">
                已获取( 点击复制 )
              </div>
            </q-button>
          </div>
          <div v-else class="use-click row-col-center">
            <q-button v-if="user.hasUserLike" @click="toMessagePage(user)" class="mr-sm">
              <q-icon icon="mdi-chat-outline" size="14"></q-icon>
              私信
            </q-button>
            <q-button v-else text @click="addLikeUser(user)">
              <q-icon icon="mdi-heart-outline" size="22"></q-icon>
            </q-button>
          </div>

          <!--                        <div v-else class="use-click row-col-center">-->
          <!--                          <q-button text @click="getOpenContactInfo(user)" :disabled="showUserContactBtnDisabled">-->
          <!--                            <q-icon prefix="uni-icons" icon="uniui-heart" size="22"></q-icon>-->
          <!--                          </q-button>-->
          <!--                        </div>-->
          <!--                    <socialuni-follow-tag :user="user" @change="userFollowChange"></socialuni-follow-tag>-->
        </div>
      </div>
    </div>
    <div class="ml-60 row-col-center mt-xs">
      <img v-for="img in imgUrls(user).slice(0,3)" class="size40 bd-radius bd mr-sm"
           mode="aspectFill"
           :data-src="img"
           @click.stop="previewImage(img,user)"
           :src="img"
      />
    </div>
  </div>
</template>

<script lang="ts">
import {Vue, Component, Prop, Emit, toNative} from 'vue-facing-decorator'
import SocialUserContentRO from "socialuni-api-base/src/model/social/SocialUserContentRO";
import QButton from "qing-ui/src/components/QButton.vue";
import QIcon from "qing-ui/src/components/QIcon.vue";
import SocialGenderTag from "socialuni-user-ui/src/components/SUserGenderTag.vue";
import SocialuniUserExtendDetailRO from "socialuni-expand-api/src/model/SocialuniUserExtendDetailRO";
import UserPageUtil from "socialuni-user-sdk/src/util/UserPageUtil";
import CenterUserDetailRO from "socialuni-api-base/src/model/social/CenterUserDetailRO";
import SocialuniImgUtil from "socialuni-user-sdk/src/util/SocialuniImgUtil";
import SocialuniUserExpandDetailRO from "socialuni-user-api/src/model/SocialuniUserExpandDetailRO";
import QingAppUtil from "qingjs/src/util/QingAppUtil";
import SocialuniUserLikeAPI from "socialuni-expand-api/src/api/SocialuniUserLikeAPI";
import {socialuniChatModule} from "socialuni-im-sdk/src/store/SocialuniChatModule";
import NumUtil from "qing-util/src/util/NumUtil";
import DateUtil from "qing-util/src/util/DateUtil";
import SocialuniUserExpandService from "socialuni-user-sdk/src/logic/SocialuniUserExpandService";
import {socialuniSystemModule} from "qing-util/src/store/SocialuniSystemModule";
import MessageAPI from "socialuni-im-api/src/api/MessageAPI";
import {socialuniAppUserModule} from "socialuni-user-sdk/src/store/SocialuniAppUserModule";
import {socialuniLikeConfigModule} from "socialuni-expand-sdk/src/store/SocialuniLikeConfigModule";
import PlatformUtils from "socialuni-user-sdk/src/util/PlatformUtils";
import SocialuniLikeService from "socialuni-expand-sdk/src/service/SocialuniLikeService";

@toNative
@Component({
  components: {QButton, QIcon, SocialGenderTag}
})
export default class SocialuniExpandUserInfo extends Vue {
  @Prop() user: SocialuniUserExtendDetailRO


  toUserDetailVue(user: SocialUserContentRO) {
    UserPageUtil.toUserDetail(user.id)
  }

  previewImage(current, user: SocialuniUserExpandDetailRO) {
    uni.previewImage({
      current: current,
      urls: this.imgUrls(user)
    })
  }

  imgUrls(user: SocialuniUserExpandDetailRO) {
    if (user && user.imgs) {
      return user.imgs.map(item => SocialuniImgUtil.getUserLargeImgUrl(item.src))
    } else {
      return []
    }
  }

  copyContactInfo(user: CenterUserDetailRO) {
    QingAppUtil.NativeUtil.textCopy(user.contactInfo)
  }

  async addLikeUser(user: SocialuniUserExtendDetailRO) {
    await SocialuniLikeService.checkUserCoinAndPay()
    user.hasUserLike = true
    const copyUserCoin = socialuniAppUserModule.userCoinNum

    socialuniAppUserModule.userCoinNum = copyUserCoin - socialuniLikeConfigModule.config.sendLikeMsgNeedPayCoinNum
    try {
      await SocialuniUserLikeAPI.addUserLikeAPI(user)
    } catch (e) {
      user.hasUserLike = false
      socialuniAppUserModule.userCoinNum = copyUserCoin
    }
    // await SocialuniUserLikeAPI.sendMsgAPI(socialuniChatModule.chat.id, "你好在干嘛")
    QingAppUtil.ToastUtil.success("打招呼成功")
    // this.toMessagePage(user)
  }

  async toMessagePage(user) {
    socialuniChatModule.setChatIdToMessagePage(user.id)
  }


  numFixed1(num) {
    return NumUtil.numFixed1(num)
  }

  formatTime(dateStr) {
    return DateUtil.formatTime(dateStr)
  }

  //同步更新粉丝和关注列表状态
  userFollowChange(user: SocialUserContentRO) {
    for (const socialuniPageQueryUtil of this.tabsPageQueryUtil) {
      for (const listDatum of socialuniPageQueryUtil.listData) {
        if (listDatum.id === user.id) {
          listDatum.hasFollowed = user.hasFollowed
        }
      }
    }
  }

  async getOpenContactInfo(user: CenterUserDetailRO) {
    //打开获取对方联系方式功能，支付金币
    user.getUserContactBtnDisabled = true
    try {
      await SocialuniUserExpandService.getOpenContactInfo(user)
    } finally {
      user.getUserContactBtnDisabled = false
    }
  }


  /*openVip() {
    PageUtil.toVipPage()
  }*/

  get isIos() {
    return socialuniSystemModule.isIos
  }

}
</script>
