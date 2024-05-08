import {App} from "vue";
import QingScss from 'qing-scss/src/index'
import "./styles/index.scss";

const QingUi = {
    async install(app: App) {
        app.use(QingScss)
    }
}

export default QingUi
