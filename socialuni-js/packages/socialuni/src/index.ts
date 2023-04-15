import {App, defineComponent} from "vue";
import {onHide, onLoad, onShow} from "@dcloudio/uni-app";
import UniUtil from "socialuni-util/src/util/UniUtil";

const Socialuni = {
    async install(app:App) {
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
