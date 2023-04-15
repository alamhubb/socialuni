import {App, defineComponent} from "vue";
import {onHide, onLoad, onShow} from "@dcloudio/uni-app";
import UniUtil from "socialuni-util/src/util/UniUtil";
import {SocialuniPlugin} from "./interface/SocialuniPlugin";

const Socialuni = {
    async install(app: App, socialuniPlugins: SocialuniPlugin[]) {
        // 社交联盟内置支持的插件
        const socialuniCommunityPlugin: { default: SocialuniPlugin } = await import('socialuni-community/src/index');
        if (socialuniCommunityPlugin) {
            socialuniCommunityPlugin.default.onLaunch()
        }
        //查询是否包含community模块，如果存在则加载

        if (socialuniPlugins && Array.isArray(socialuniPlugins) && socialuniPlugins.length) {
            for (const plugin of socialuniPlugins) {
                plugin.onLaunch()
            }
        }

        console.log(socialuniPlugins)
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
