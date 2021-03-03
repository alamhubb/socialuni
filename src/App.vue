<script lang="ts">
import Vue from 'vue'
import {appModule, systemModule} from '@/plugins/store'

export default Vue.extend({
  mpType: 'app',
  onLaunch () {
    //页面启动，启动函数
    systemModule.appInit()
    appModule.threeSecretKey = '123123123'
  }
})
</script>
<style lang="scss">
  @import "./styles/iconfont/index.css";
  @import "./styles/index.scss";
</style>
