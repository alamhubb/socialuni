import {App} from "vue";
import ElementPlus from 'element-plus'
import zhCn from 'element-plus/dist/locale/zh-cn.mjs'
import 'element-plus/dist/index.css'
import "./styles/index.scss";
import QingUi from "qing-ui/src";
import UniappWeb from "uniapp-web/src";

const QingUiH5 = {
    async install(app: App) {
        app.use(QingUi)
        app.use(UniappWeb)
        app.use(ElementPlus, {
            locale: zhCn,
        })
    }
}

export default QingUiH5
