import {App} from "vue";
import ElementPlus from 'element-plus'
import zhCn from 'element-plus/dist/locale/zh-cn.mjs'
import SocialuniScss from 'socialuni-scss/src/index'
import 'element-plus/dist/index.css'

const SocialuniUiH5 = {
    async install(app: App) {
        app.use(SocialuniScss)
        app.use(ElementPlus, {
            locale: zhCn,
        })
    }
}

export default SocialuniUiH5
