import {App} from "vue";
import ElementPlus from 'element-plus'
import zhCn from 'element-plus/dist/locale/zh-cn.mjs'
import SocialuniScss from '@socialuni/socialuni-scss/src/index'
import "@socialuni/socialuni-scss/src/styles/index.scss";
import 'element-plus/dist/index.css'
import './styles/elm.scss'
import './components/YComponent/styles/index.scss'
import YComponents from "./components/YComponent";

const SocialuniUiH5 = {
    async install(app: App) {
        console.log(app)
        console.log('cheng gong shi yong')
        app.use(SocialuniScss)
        app.use(ElementPlus, {
            locale: zhCn,
        })
        app.use(YComponents)
    }
}

export default SocialuniUiH5
