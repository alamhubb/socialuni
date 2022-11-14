<script>
import {socialUserModule} from "socialuni-user/src/store/store";

export default {
  onLaunch() {
    socialUserModule.initSocialuniUserModule()
  },
  onError(e) {
    // SocialuniAppAPI.sendErrorLogAPI('front page error', e.message)
  },
}
</script>
