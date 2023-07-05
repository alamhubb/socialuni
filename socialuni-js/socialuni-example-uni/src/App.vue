<script>
import TestB from "testb/src/TestB";
import TestC from "testc/src/TestC";
import {socialuniPluginsModule} from "@socialuni/socialuni/src/store/SocialuniPluginsModule";

export default {
    async onLaunch() {
        console.log('app launch')
        console.log('app launch')
        TestB.test()
        TestC.test()
        console.log(777777777)
        console.log(socialuniPluginsModule.uid)
        // socialSystemModule.initSystemInfo()
        //如果有跳转信息
        //校验更新
        // PlatformUtils.checkUpdate()
        // await socialAppModule.appLunchAction()
    },
    /*onError(e) {
        SocialuniAppAPI.sendErrorLogAPI('front page error', SocialuniAppUtil.RouterUtil.getCurrentPageURI(), e.message)
        console.error(e)
    },*/
}
</script>
