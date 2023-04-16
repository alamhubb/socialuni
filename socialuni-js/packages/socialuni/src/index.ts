import {App, defineComponent} from "vue";
import {onHide, onLoad, onShow} from "@dcloudio/uni-app";
import UniUtil from "socialuni-util/src/util/UniUtil";
import {SocialuniPlugin} from "./interface/SocialuniPlugin";
import {ImportModule} from "./interface/ImportModule";
import {socialuniPluginsModule} from "./store/SocialuniPluginsModule";
import {socialuniUserModule} from "./store/SocialuniUserModule";
import UserService from "./service/UserService";
import {socialuniConfigModule} from "socialuni-base-api/src/store/SocialuniConfigModule";
import CosAPI from "socialuni-base-api/src/api/CosAPI";
import {socialAppModule} from "socialuni/src/store/SocialAppModule";


async function installSocialuniPluginIns() {
    try {
        //查询是否包含community模块，如果存在则加载
        const socialuniCommunityPlugin: ImportModule<SocialuniPlugin> = await import('socialuni-community/src/index');
        socialuniPluginsModule.addPlugin(socialuniCommunityPlugin.default)
    } catch (e) {
        // 如果导入失败，则不触发任何操作
    }// 社交联盟内置支持的插件
    try {
        //查询是否包含community模块，如果存在则加载
        const socialuniImPlugin: ImportModule<SocialuniPlugin> = await import('socialuni-im/src/index');
        socialuniPluginsModule.addPlugin(socialuniImPlugin.default)
    } catch (e) {
        // 如果导入失败，则不触发任何操作
    }
}


const Socialuni = {
    async install(app: App, socialuniPlugins: SocialuniPlugin[]) {
        await socialuniUserModule.initSocialuniUserModule()
        UserService.getAppLunchDataByHasUser()
        socialuniConfigModule.getAppConfigAction()
        socialuniConfigModule.getReportTypesAction()
        CosAPI.getCosPathAPI().then(res => {
            socialAppModule.cosHttpPath = res.data
        })
        socialAppModule.getHomeSwipersAction()

        // 社交联盟内置支持的插件
        await installSocialuniPluginIns()

        if (socialuniPlugins) {
            if (Array.isArray(socialuniPlugins)) {
                socialuniPluginsModule.addPlugin(...socialuniPlugins)
            } else {
                socialuniPluginsModule.addPlugin(socialuniPlugins)
            }
        }

        for (const plugin of socialuniPluginsModule.plugins) {
            plugin.onLaunch()
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
                onLoad((params) => {
                    UniUtil.showShareMenu()
                })
            }
        })
        app.mixin(shareComponent)
    }
}
export default Socialuni
