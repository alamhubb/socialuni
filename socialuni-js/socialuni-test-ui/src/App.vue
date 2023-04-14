<script>

import PlatformUtils from "socialuni-util/src/util/PlatformUtils";

export default {
 async onLaunch() {
    console.log('App Launch')
    // socialSystemModule.initSystemInfo()
    //如果有跳转信息
   //校验更新
   PlatformUtils.checkUpdate()
    // await socialAppModule.appLunchAction()
  },
  onError(e) {
    // SocialuniAppAPI.sendErrorLogAPI('front page error', e.message)
  },
}
</script>
