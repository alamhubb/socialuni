<template>
  <!--        隐私提示-->
  <div class="pt-sm h50px">
    <view v-if="!user" class="row-center row-grid u-type-info">
      <text class="mb-nn">
        登录即代表同意
      </text>
      <navigator :url="userAgreementUrl" class="text-blue">
        《用户协议》
      </navigator>
      <navigator :url="userPrivacyUrl" class="text-blue">
        《隐私政策》
      </navigator>
      <navigator :url="childProtectUrl" class="text-blue">
        《儿童个人信息保护规则及监护人须知》
      </navigator>
    </view>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
import SkipUrlConst from '@/const/SkipUrlConst'
import UserVO from '@/model/user/UserVO'
import { userStore } from '@/store'

@Component
export default class LoginFooterAppInfo extends Vue {
  @userStore.State('user') user: UserVO

  get userAgreementUrl (): string {
    return SkipUrlConst.userAgreementUrl()
  }

  get userPrivacyUrl (): string {
    return SkipUrlConst.userPrivacyUrl()
  }

  get childProtectUrl (): string {
    return SkipUrlConst.childProtectUrl()
  }
}
</script>
