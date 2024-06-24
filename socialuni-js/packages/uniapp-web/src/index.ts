import {App} from "vue";
import QingUi from "qing-compat-js-ui/src";

const UniappWeb = {
    async install(app: App) {
        app.use(QingUi)
    }
}

export default UniappWeb
