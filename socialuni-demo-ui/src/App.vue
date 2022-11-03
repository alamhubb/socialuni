<script>
import SocialuniAppAPI from "socialuni-api/src/api/socialuni/SocialuniAppAPI";

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
