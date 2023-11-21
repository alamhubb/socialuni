import {App, defineComponent, getCurrentInstance, isReactive, onMounted, toRaw} from "vue"
import {socialuniPluginsModule} from "./store/SocialuniPluginsModule"
import {SocialuniPlugin} from "./interface/SocialuniPlugin"
import {ImportModule} from "./interface/ImportModule"
import {SocialuniOption} from "./interface/socialuniOption"
import SocialuniViewService from "./interface/SocialuniViewService";
import {socialuniSystemModule} from "socialuni-util/src/store/SocialuniSystemModule";
import PlatformModuleLoadUtil from "socialuni-native-util/src/util/PlatformModuleLoadUtil";
import JsonUtil from "socialuni-util/src/util/JsonUtil";
import ObjectUtil from "socialuni-util/src/util/ObjectUtil";

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

    /* const moudles = import.meta.globEager('../../../../!**!/src/index.ts')
     for (const moudle in moudles) {
         console.log(moudle)
     }
     console.log(moudles)*/

    //查询是否包含community模块，如果存在则加载
    const appModules = import.meta.globEager('../../**/socialuni-app-sdk/src/index.ts')
    const socialuniApp = PlatformModuleLoadUtil.getFirstModule(appModules)
    if (socialuniApp && socialuniApp.default) {
        app.use(socialuniApp.default)
    }

    const SocialuniApp = await PlatformModuleLoadUtil.dynamicImport("socialuni-app-view")
    console.log(SocialuniApp)
    app.use(SocialuniApp.default)

    /*const appViewModules = import.meta.globEager('../../!**!/socialuni-app-view-*!/src/index.ts')
    const socialuniAppView = PlatformModuleLoadUtil.getModuleDefault(appViewModules)
    if (socialuniAppView) {
        app.use(socialuniAppView)
    }*/
    const userModules = import.meta.globEager('../../**/socialuni-user-sdk/src/index.ts')
    const socialuniUser = PlatformModuleLoadUtil.getFirstModule(userModules)
    if (socialuniUser && socialuniUser.default) {
        app.use(socialuniUser.default)
    }
    //查询是否包含community模块，如果存在则加载
    const communityModules = import.meta.globEager('../../**/socialuni-socialuniCommunity-sdk/src/index.ts')
    const socialuniCommunity = PlatformModuleLoadUtil.getFirstModule(communityModules)
    if (socialuniCommunity && socialuniCommunity.default) {
        app.use(socialuniCommunity.default)
    }
    //查询是否包含Im模块，如果存在则加载
    const imModules = import.meta.globEager('../../**/socialuni-im-sdk/src/index.ts')
    const socialuniIm = PlatformModuleLoadUtil.getFirstModule(imModules)
    console.log(socialuniIm)
    if (socialuniIm && socialuniIm.default) {
        app.use(socialuniIm.default)
    }
}


const Socialuni = {
    async install(app: App, socialuniOption?: SocialuniOption) {
        // const SocialuniUiUni = await import("../../socialuni-ui/socialuni-ui")
        // console.log(SocialuniUiUni)
        // app.use(SocialuniUiUni)
        // console.log(SocialuniUiUni)

        console.log(socialuniSystemModule.isDev)
        const SocialuniUiH5 = await PlatformModuleLoadUtil.dynamicImport("socialuni-ui")

        app.use(SocialuniUiH5.default)


        /*if (socialuniSystemModule.isUniApp) {
            const modules = import.meta.globEager('../../socialuni-ui/socialuni-ui-uni/src/index.ts')
            const SocialuniUiUni = PlatformModuleLoadUtil.getModuleDefault(modules)
            app.use(SocialuniUiUni)
        } else {
            const modules = import.meta.globEager('../../socialuni-ui/socialuni-ui-h5/src/index.ts')
            const SocialuniUiH5 = PlatformModuleLoadUtil.getModuleDefault(modules)
            app.use(SocialuniUiH5)
        }*/

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
                socialuniPluginsModule.setRouter(this.$router)
                socialuniPluginsModule.setRoute(this.$route)
            }
        })
        app.mixin(shareComponent)

        // 社交联盟内置支持的插件
        await installSocialuniPluginIns(app)

        // if (socialuniOptions) {
        if (socialuniOption && socialuniOption.plugins) {
            socialuniPluginsModule.addPlugin(...socialuniOption.plugins)
        }
        // }

        for (const plugin of socialuniPluginsModule.plugins) {
            plugin && plugin.onLaunch && plugin.onLaunch()
        }
    }
}

export default Socialuni
