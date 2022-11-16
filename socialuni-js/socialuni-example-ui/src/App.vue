<script>
import {socialAppModule} from "socialuni-sdk/src/store/store";
import SocialuniAppNewAPI from "socialuni-api/src/api/SocialuniAppNewAPI";

export default {
  onLaunch() {
    console.log('App Launch')
    // socialSystemModule.initSystemInfo()
    //如果有跳转信息
    socialAppModule.appLunchAction()
  },
  onError(e) {
    // SocialuniAppNewAPI.sendErrorLogAPI('front page error', e.message)
  },
}
</script>

<style lang="scss">
@import "socialuni/styles/index.scss";
</style>
