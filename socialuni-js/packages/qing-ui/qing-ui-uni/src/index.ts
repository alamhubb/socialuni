import {App} from "vue";
import QingScss from 'qing-scss/src/index'
import "./styles/index.scss";

const QingUiUni = {
    async install(app: App) {
        app.use(QingScss)
    }
}

export default QingUiUni
