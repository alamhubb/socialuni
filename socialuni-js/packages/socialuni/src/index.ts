import {App, defineComponent} from "vue"
import {socialuniPluginsModule} from "./store/SocialuniPluginsModule"
import {SocialuniPlugin} from "./interface/SocialuniPlugin"
import {ImportModule} from "./interface/ImportModule"
import {SocialuniOption} from "./interface/socialuniOption"
import SocialuniViewService from "./interface/SocialuniViewService";

try {
    await import('socialuni-ui-uni/src/styles/index.scss')
} catch (e) {
    console.log(e)
}

try {
    await import('socialuni-app-view-uni/src/styles/index.scss')
} catch (e) {
    console.log(e)
}

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

async function installSocialuniPluginIns() {

    socialuniPluginsModule.addPlugin(socialuniInitPlugin)
    try {
        //查询是否包含community模块，如果存在则加载
        const socialuniPlugin: ImportModule<SocialuniPlugin> = await import('socialuni-app-sdk/src/index')
        socialuniPluginsModule.addPlugin(socialuniPlugin.default)
    } catch (e) {
        console.error(e)
        // 如果导入失败，则不触发任何操作
    }
    try {
        //查询是否包含community模块，如果存在则加载
        const socialuniPlugin: ImportModule<SocialuniPlugin> = await import('socialuni-user-sdk/src/index')
        socialuniPluginsModule.addPlugin(socialuniPlugin.default)
    } catch (e) {
        console.error(e)
        // 如果导入失败，则不触发任何操作
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
            mounted() {
                socialuniPluginsModule.setRouter(this.$router)
                socialuniPluginsModule.setRoute(this.$route)
                const data = this
                for (const key in data) {
                    const socialuniViewServiceObj: SocialuniViewService = data[key]
                    if (socialuniViewServiceObj instanceof SocialuniViewService) {
                        socialuniViewServiceObj.initService(this)
                    }
                }
            }
        })
        app.mixin(shareComponent)

        // 社交联盟内置支持的插件
        await installSocialuniPluginIns()

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
