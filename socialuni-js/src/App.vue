<script lang="ts">
import Vue from 'vue'
import {socialAppModule, socialSystemModule} from "@/socialuni/store";
import UniUtil from "@/socialuni/utils/UniUtil";

export default Vue.extend({
  mpType: 'app',
  onLaunch() {
    socialSystemModule.getSystemInfo()
    console.log(socialSystemModule.systemInfo)
    //如果有跳转信息
    socialAppModule.appLunchAction()
    UniUtil.showShareMenu()
  }
})
</script>
<style lang="scss">
@import "socialuni/styles/index.scss";
</style>
