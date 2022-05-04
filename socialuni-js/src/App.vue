<script lang="ts">
import Vue from 'vue'
import {socialSystemModule} from "@/socialuni/store";

export default Vue.extend({
  mpType: 'app',
  onLaunch(){
    socialSystemModule.getSystemInfo()
    console.log(socialSystemModule.systemInfo)
  }
})
</script>
<style lang="scss">
@import "socialuni/styles/index.scss";
</style>
