import {App, defineComponent, onMounted} from "vue"
import {socialuniPluginsModule} from "./store/SocialuniPluginsModule"
import {SocialuniPlugin} from "./interface/SocialuniPlugin"
import {ImportModule} from "./interface/ImportModule"
import {SocialuniOption} from "./interface/socialuniOption"
import SocialuniViewService from "./interface/SocialuniViewService";
import {socialuniSystemModule} from "socialuni-util/src/store/SocialuniSystemModule";
import UniappAPI from "uniapp-api/src";
import PlatformModuleLoadUtil from "socialuni-native-util/src/util/PlatformModuleLoadUtil";
import {VueBase} from "vue-class-component";

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
    const appModules = import.meta.globEager('../../socialuni-app/socialuni-app-sdk/src/index.ts')
    const socialuniApp = PlatformModuleLoadUtil.getFirstModule(appModules)
    if (socialuniApp && socialuniApp.default) {
        app.use(socialuniApp.default)
    }
    const userModules = import.meta.globEager('../../socialuni-user/socialuni-user-sdk/src/index.ts')
    const socialuniUser = PlatformModuleLoadUtil.getFirstModule(userModules)
    if (socialuniUser && socialuniUser.default) {
        app.use(socialuniUser.default)
    }

    try {
        //查询是否包含community模块，如果存在则加载
        const socialuniPlugin: ImportModule<SocialuniPlugin> = await import('socialuni-community-sdk/src/index.ts')
        socialuniPluginsModule.addPlugin(socialuniPlugin.default)
    } catch (e) {
        console.error(e)
        // 如果导入失败，则不触发任何操作
    }
    // 社交联盟内置支持的插件
    try {
        //查询是否包含community模块，如果存在则加载
        const socialuniPlugin: ImportModule<SocialuniPlugin> = await import('socialuni-im-sdk/src/index')
        socialuniPluginsModule.addPlugin(socialuniPlugin.default)
    } catch (e) {
        console.error(e)
        // 如果导入失败，则不触发任何操作
    }
}


const Socialuni = {
    async install(app: App, socialuniOption: SocialuniOption) {
        app.use(UniappAPI)
        if (socialuniSystemModule.isUniApp) {
            const modules = import.meta.globEager('../../socialuni-ui/socialuni-ui-uni/src/index.ts')
            const SocialuniUiUni = PlatformModuleLoadUtil.getModule(modules)
            app.use(SocialuniUiUni)
        } else {
            const modules = import.meta.globEager('../../socialuni-ui/socialuni-ui-h5/src/index.ts')
            const SocialuniUiH5 = PlatformModuleLoadUtil.getModule(modules)
            app.use(SocialuniUiH5)
        }
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
                const data = this
                for (const key in data) {
                    const socialuniViewServiceObj: SocialuniViewService<any> = data[key]
                    if (socialuniViewServiceObj instanceof SocialuniViewService) {
                        socialuniViewServiceObj.initService(this)
                        socialuniViewServiceObj.created?.()
                    }
                }
            },
            mounted() {
                const data = this
                for (const key in data) {
                    const socialuniViewServiceObj: SocialuniViewService<any> = data[key]
                    if (socialuniViewServiceObj instanceof SocialuniViewService) {
                        socialuniViewServiceObj.mounted?.()
                    }
                }
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