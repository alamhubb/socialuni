import {App, defineComponent, getCurrentInstance, isReactive, nextTick, onMounted, toRaw} from "vue"
import {socialuniPluginsModule} from "./store/SocialuniPluginsModule"
import {SocialuniPlugin} from "./interface/SocialuniPlugin"
import {ImportModule} from "./interface/ImportModule"
import {SocialuniOption} from "./interface/socialuniOption"
import SocialuniViewService from "./interface/SocialuniViewService";
import {socialuniSystemModule} from "qing-util/src/store/SocialuniSystemModule";
import PlatformModuleLoadUtil from "qing-compat-js/src/util/PlatformModuleLoadUtil";
import JsonUtil from "qing-util/src/util/JsonUtil";
import ObjectUtil from "qing-util/src/util/ObjectUtil";
import {Router} from "vue-router";

const socialuniInitPlugin: SocialuniPlugin = {
    async onLaunch() {
        /*socialuniUserModule.initSocialuniUserModule()
        UserService.getAppLunchDataByHasUser()
        socialuniConfigModule.getAppConfigAction()
        socialuniConfigModule.getReportTypesAction()
        CosAPI.getCosPathAPI().then(res => {
          socialAppModule.cosHttpPath = res.data
        })
        socialAppModule.getHomeSwipersAction()*/
    }
}

async function installSocialuniPluginIns(app: App) {
    socialuniPluginsModule.addPlugin(socialuniInitPlugin)

    //查询是否包含community模块，如果存在则加载
    // const appModules = import.meta.glob('../../**/socialuni-app-sdk/src/index.ts',{eager:true})
    // const socialuniApp = PlatformModuleLoadUtil.getFirstModule(appModules)
    // console.log(111111)
    // console.log(socialuniApp)
    // console.log(socialuniApp.default)
    // if (socialuniApp && socialuniApp.default) {
    //     app.use(socialuniApp.default)
    // }
    //
    // const socialuniAppView = await PlatformModuleLoadUtil.dynamicImport("socialuni-app-view")
    // console.log(socialuniAppView)
    // app.use(socialuniAppView.default)
    //
    // /*const appViewModules = import.meta.glob('../../!**!/socialuni-app-view-*!/src/index.ts',{eager:true})
    // const socialuniAppView = PlatformModuleLoadUtil.getModuleDefault(appViewModules)
    // if (socialuniAppView) {
    //     app.use(socialuniAppView)
    // }*/
    // const userModules = import.meta.glob('../../**/socialuni-user-sdk/src/index.ts',{eager:true})
    // const socialuniUser = PlatformModuleLoadUtil.getFirstModule(userModules)
    // if (socialuniUser && socialuniUser.default) {
    //     app.use(socialuniUser.default)
    // }
    // //查询是否包含community模块，如果存在则加载
    // const communityModules = import.meta.glob('../../**/socialuni-socialuniCommunity-sdk/src/index.ts',{eager:true})
    // const socialuniCommunity = PlatformModuleLoadUtil.getFirstModule(communityModules)
    // if (socialuniCommunity && socialuniCommunity.default) {
    //     app.use(socialuniCommunity.default)
    // }
    // //查询是否包含Im模块，如果存在则加载
    // const imModules = import.meta.glob('../../**/socialuni-im-sdk/src/index.ts',{eager:true})
    // const socialuniIm = PlatformModuleLoadUtil.getFirstModule(imModules)
    // console.log(socialuniIm)
    // if (socialuniIm && socialuniIm.default) {
    //     app.use(socialuniIm.default)
    // }
}


const Socialuni = {
    async install(app: App, router: Router, socialuniOption?: SocialuniOption) {
        // const SocialuniUiUni = await import("../../qing-compat-js-ui/qing-compat-js-ui")
        // console.log(SocialuniUiUni)
        // app.use(SocialuniUiUni)
        // console.log(SocialuniUiUni)

        socialuniPluginsModule.setRouter(router)

        console.log(socialuniSystemModule.isDev)


        const shareComponent = defineComponent({
            onShareAppMessage() {
                const title = '年轻人生活分享社区'
                const path = '/pages/home/home"'
                const imageUrl = 'https://cdxapp-1257733245.file.myqcloud.com/qingchi/home/full.jpg!avatar'
                return {
                    title: title,
                    path: path,
                    imageUrl: imageUrl
                }
            },
            created() {
                socialuniPluginsModule.setRoute(this.$route)
            },
            onShow(){
                for (const plugin of socialuniPluginsModule.plugins) {
                    plugin && plugin.onShow && plugin.onShow()
                }
            }
        })
        app.mixin(shareComponent)
        if (socialuniOption && socialuniOption.plugins) {
            socialuniPluginsModule.addPlugin(...socialuniOption.plugins)
        }

        // 社交联盟内置支持的插件
        socialuniPluginsModule.addPlugin(socialuniInitPlugin)

        await nextTick()
        console.log('zhifale soci lunch')
        for (const plugin of socialuniPluginsModule.plugins) {
            plugin && plugin.onLaunch && plugin.onLaunch()
        }
    }
}

export default Socialuni
