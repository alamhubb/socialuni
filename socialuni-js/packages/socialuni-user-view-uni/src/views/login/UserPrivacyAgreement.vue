<template>
  <!--        隐私提示-->
  <div>
    <view v-if="!user" class="row-center row-wrap u-type-info">
      <text class="mb-nm">
        登录即代表同意
      </text>
      <navigator :url="userAgreementUrl" class="color-blue">
        《用户协议》
      </navigator>
      <navigator :url="userPrivacyUrl" class="color-blue">
        《隐私政策》
      </navigator>
      <navigator :url="childProtectUrl" class="color-blue">
        《儿童个人信息保护规则及监护人须知》
      </navigator>
    </view>
  </div>
</template>

<script lang="ts">
import {Options, Vue} from 'vue-property-decorator'
import SkipUrlConst from "socialuni-constant/constant/SkipUrlConst";

@Options({})
export default class UserPrivacyAgreement extends Vue {
  get user() {
    return socialuniUserModule.mineUser
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
}
</script>
