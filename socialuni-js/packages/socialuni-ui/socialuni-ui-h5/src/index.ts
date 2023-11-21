import {App} from "vue";
import ElementPlus from 'element-plus'
import zhCn from 'element-plus/dist/locale/zh-cn.mjs'
import SocialuniScss from 'socialuni-scss/src/index'
import 'element-plus/dist/index.css'
import "./styles/index.scss";

const SocialuniUiH5 = {
    async install(app: App) {
        console.log(app)
        console.log('cheng gong shi yong')
        app.use(SocialuniScss)
        app.use(ElementPlus, {
            locale: zhCn,
        })
    }
}

export default SocialuniUiH5
