<template>
  <!--      底部客服信息-->
  <div class="row-between">
    <navigator v-if="homeUrl" :url="homeUrl" class="u-type-info u-border-bottom">
      <q-icon icon="home" size="15" class="mr-xs"></q-icon>
      查看官网
    </navigator>

    <div v-if="serviceWeChat" @click="copyServiceNum" class="flex-row u-type-info u-border-bottom">
      客服微信：{{ serviceWeChat }}
      <q-icon icon="attach" size="15" class="ml-xs"></q-icon>
    </div>
  </div>
</template>

<script lang="ts">
import {Component, Vue, toNative} from 'vue-facing-decorator'
import {socialuniSystemModule} from "qing-util/src/store/SocialuniSystemModule";
import QIcon from "qing-compat-js-ui/src/components/QIcon.vue";
import QingAppUtil from "qing-compat-js/src/util/QingAppUtil";
import SkipUrlConst from "socialuni-app-sdk/src/constant/SkipUrlConst";

@toNative
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
    QingAppUtil.NativeUtil.textCopy(this.serviceWeChat, '客服联系方式已复制')
  }
}
</script>
