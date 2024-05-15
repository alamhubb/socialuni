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
          <div class="row-col-center mt-xss font-12 color-content">
            <!--                          {{ formatTime(user.updateTime) }}-->
            <!--                          <div class="px-xs row-col-center">|</div>-->
            <!--        有市区的名称就不显示省的名称-->
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
          </div>
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

@toNative
@Component({
  components: {QButton, QIcon, SocialGenderTag}
})
export default  class SocialuniExpandUserInfo extends Vue {
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
}
</script>
