import {App} from "vue";
import ElementPlus from 'element-plus'
import zhCn from 'element-plus/dist/locale/zh-cn.mjs'
import QingScss from 'qing-scss/src/index'
import 'element-plus/dist/index.css'
import "./styles/index.scss";

const QingUiH5 = {
    async install(app: App) {
        app.use(QingScss)
        app.use(ElementPlus, {
            locale: zhCn,
        })
    }
}

export default QingUiH5
