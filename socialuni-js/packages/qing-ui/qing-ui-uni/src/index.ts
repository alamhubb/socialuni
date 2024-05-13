import {App} from "vue";
import "./styles/index.scss";
import QingUi from "qing-ui/src";

const QingUiUni = {
    async install(app: App) {
        app.use(QingUi)
    }
}

export default QingUiUni
