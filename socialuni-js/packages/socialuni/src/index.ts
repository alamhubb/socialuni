import {App, defineComponent} from "vue";

const Socialuni = {
    async install(app:App) {
        const shareComponent = defineComponent({
            onShareAppMessage() {
                const title = '年轻人生活分享社区'
                const path = '/pages/talk/talk"'
                const imageUrl = 'https://cdxapp-1257733245.file.myqcloud.com/qingchi/home/full.jpg!avatar'
                return {
                    title: title,
                    path: path,
                    imageUrl: imageUrl
                }
            }
        })
        app.mixin(shareComponent)
    }
}
export default Socialuni
