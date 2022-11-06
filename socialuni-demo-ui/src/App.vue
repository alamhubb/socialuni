<script>
import SocialuniAppAPI from "socialuni-api/src/api/socialuni/SocialuniAppAPI";
import request from "socialuni-api/src/request/request";
import UUIDUtil from "socialuni-sdk/src/utils/UUIDUtil";

export default {
  onLaunch() {
    console.log('App Launch')
  },
  onError(e) {
    SocialuniAppAPI.sendErrorLogAPI('front page error', e.message)
  },
}
</script>

<style lang="scss">
@import "socialuni/styles/index.scss";
</style>
