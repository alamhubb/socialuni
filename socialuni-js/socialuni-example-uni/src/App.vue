<script>
export default {
    async onLaunch() {
        console.log('app launch')
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
