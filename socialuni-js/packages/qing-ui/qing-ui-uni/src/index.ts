import {App} from "vue";
import QingUi from "qing-ui/src";
import "./styles/index.scss";

const QingUiUni = {
    async install(app: App) {
        app.use(QingUi)
    }
}

export default QingUiUni
