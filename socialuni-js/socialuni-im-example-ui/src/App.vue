<script>
import {socialUserModule} from "socialuni-user/src/store/store";
import {socialAppModule} from "socialuni-sdk/src/store/store";

export default {
  onLaunch() {
    socialAppModule.appLunchAction()
  },
  onError(e) {
    // SocialuniAppAPI.sendErrorLogAPI('front page error', e.message)
  },
}
</script>
