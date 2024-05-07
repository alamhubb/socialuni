<template>
  <!--        隐私提示-->
    <div>
        <view v-if="!user" class="row-all-center row-wrap u-type-info">
            <checkbox  class="mt-sm" :checked="viewService.contractChecked" @click="viewService.contractCheckedChange()">
                <span>已阅读并同意</span>
            </checkbox>
            <navigator :url="userAgreementUrl" class="color-blue mt-sm">
                《用户协议》
            </navigator>
            <navigator :url="userPrivacyUrl" class="color-blue mt-sm">
                《隐私政策》
            </navigator>
            <navigator :url="childProtectUrl" class="color-blue mt-sm">
                《儿童个人信息保护规则及监护人须知》
            </navigator>
        </view>
    </div>
</template>

<script lang="ts">
import {Component, toNative, Prop, Vue} from 'vue-facing-decorator'
import {socialuniUserModule} from "socialuni-user-sdk/src/store/SocialuniUserModule";
import SkipUrlConst from "socialuni-app-sdk/src/constant/SkipUrlConst";
import SocialuniLoginViewService from "socialuni-user-sdk/src/logic/SocialuniLoginViewService";

@toNative
@Component({})
export default class UserPrivacyAgreement extends Vue {
    @Prop() viewService:SocialuniLoginViewService

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
