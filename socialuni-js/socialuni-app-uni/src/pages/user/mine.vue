<template>
  <mine-view class="h100p">

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

      <socialuni-user-school-info></socialuni-user-school-info>
    </div>

    <!--
        <div v-if="cpInfo" class="row-between-center use-click bg-white px-sm py-sm bd-radius-10 elevation-4 mx-sm mt-smm">
          <div class="row-col-center">
            cp:{{ cpInfo.nickname}}
          </div>
          &lt;!&ndash;      <q-icon icon="arrow-right" class="text-md margin-right-sm color-sub"></q-icon>&ndash;&gt;
        </div>
    -->

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
  </mine-view>
</template>

<script lang="ts">
import {Component, Vue, toNative} from 'vue-facing-decorator'
import MineView from "socialuni-user-view-uni/src/views/user/MineView.vue";
import QIcon from "qingjs-ui/src/components/QIcon.vue";
import SocialuniUserInfoImg from "socialuni-user-view-uni/src/views/user/SocialuniUserInfoImg.vue";
import {socialuniUserModule} from "socialuni-user-sdk/src/store/SocialuniUserModule";
import {socialuniAppUserModule} from "socialuni-user-sdk/src/store/SocialuniAppUserModule";
import UserPageUtil from "socialuni-user-sdk/src/util/UserPageUtil";
import {socialuniSystemModule} from "qing-util/src/store/SocialuniSystemModule";
import SocialuniUserSchoolInfo from "socialuni-user-view-uni/SocialuniUserSchoolInfo.vue";
import socialuniImRequest from "socialuni-im-api/src/api/socialuniImRequest";
import SocialuniUserRO from "socialuni-api-base/src/model/user/SocialuniUserRO";

@toNative
@Component({
  components: {
    SocialuniUserInfoImg, QIcon, SocialuniUserSchoolInfo,
    MineView
  }
})
export default class MinePage extends Vue {

  created() {
    this.queryCpInfo()
  }

  cpInfo: SocialuniUserRO = null

  async queryCpInfo() {
    if (this.mineUser) {
      const res = await socialuniImRequest.get('haxun/cp/acceptCp/getMineCp')
      this.cpInfo = res.data
    }
  }

  get mineUser() {
    return socialuniUserModule.mineUser
  }

  get userImgs() {
    return socialuniAppUserModule.userImgs
  }

  toCoinPage() {
    UserPageUtil.toCoinPage()
  }

  get isIosAndMpQQ() {
    return socialuniSystemModule.isIosOrMpQQ
  }

  get mineUserPhoneNum() {
    return socialuniAppUserModule.mineUserPhoneNum
  }

  async toPhonePage() {
    await UserPageUtil.toPhonePage()
  }

}
</script>
