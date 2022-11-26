<script>
import {socialAppModule} from "socialuni-sdk/src/store/store";
import SocialuniAppAPI from "socialuni-api/src/api/socialuni/SocialuniAppAPI";

export default {
 async onLaunch() {
    console.log('App Launch')
    // socialSystemModule.initSystemInfo()
    //如果有跳转信息
    console.log(8888)
    await socialAppModule.appLunchAction()
  },
  onError(e) {
    // SocialuniAppAPI.sendErrorLogAPI('front page error', e.message)
  },
}
</script>

<style lang="scss">
@import "socialuni/styles/index.scss";
</style>
