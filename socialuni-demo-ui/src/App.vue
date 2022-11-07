<script>
import SocialuniAppAPI from "socialuni-api/src/api/socialuni/SocialuniAppAPI";
import {socialAppModule, socialSystemModule} from "socialuni-sdk/src/store/store";

export default {
  onLaunch() {
    console.log('App Launch')
    socialSystemModule.initSystemInfo()
    //如果有跳转信息
    socialAppModule.appLunchAction()
  },
  onError(e) {
    SocialuniAppAPI.sendErrorLogAPI('front page error', e.message)
  },
}
</script>

<style lang="scss">
@import "socialuni/styles/index.scss";
</style>
