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
import {Options, Vue} from 'vue-property-decorator'
import {socialSystemModule} from "socialuni-sdk/src/store/store";
// import {socialConfigModule} from "socialuni-sdk/src/store/store";
import SkipUrlConst from "socialuni-constant/constant/SkipUrlConst";
import UniUtil from "socialuni-sdk/src/utils/UniUtil";
import QIcon from "socialuni-view/src/components/QIcon/QIcon.vue";

@Options({
  components: {QIcon}
})
export default class LoginFooterAppInfo extends Vue {
  get isMpQQ() {
    return socialSystemModule.isMpQQ
  }

  get serviceWeChat() {
    // return socialConfigModule.appMoreConfig.serviceWeChat
  }

  get homeUrl(): string {
    return SkipUrlConst.homeUrl()
  }

  copyServiceNum() {
    UniUtil.textCopy(this.serviceWeChat, '客服联系方式已复制')
  }
}
</script>
