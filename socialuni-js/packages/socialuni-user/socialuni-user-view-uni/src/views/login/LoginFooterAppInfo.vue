<template>
  <!--      底部客服信息-->
  <div class="row-between">
    <navigator v-if="homeUrl" :url="homeUrl" class="u-type-info u-border-bottom">
      <q-icon icon="home" size="15" class="mr-xs"></q-icon>
      查看官网
    </navigator>

    <view v-if="serviceWeChat" @click="copyServiceNum" class="flex-row u-type-info u-border-bottom">
      客服微信：{{ serviceWeChat }}
      <q-icon icon="attach" size="15" class="ml-xs"></q-icon>
    </view>
  </div>
</template>

<script lang="ts">
import {Component, Vue} from 'vue-facing-decorator'
import {socialuniSystemModule} from "@socialuni/socialuni-util/src/store/SocialuniSystemModule";
import QIcon from "@socialuni/socialuni-ui-uni/src/components/QIcon/QIcon.vue";
import SocialuniAppUtil from "@socialuni/socialuni-native-util/src/util/SocialuniAppUtil";
import SkipUrlConst from "@socialuni/socialuni-app-sdk/src/constant/SkipUrlConst";

@Component({
  components: {QIcon}
})
export default class LoginFooterAppInfo extends Vue {
  get isMpQQ() {
    return socialuniSystemModule.isMpQQ
  }

  get serviceWeChat() {
    // return socialuniConfigModule.appMoreConfig.serviceWeChat
  }

  get homeUrl(): string {
    return SkipUrlConst.homeUrl()
  }

  copyServiceNum() {
    SocialuniAppUtil.NativeUtil.textCopy(this.serviceWeChat, '客服联系方式已复制')
  }
}
</script>
