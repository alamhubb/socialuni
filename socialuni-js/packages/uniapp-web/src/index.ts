import {App} from "vue";
import QingUi from "qingjs-ui/src";

const UniappWeb = {
    async install(app: App) {
        app.use(QingUi)
    }
}

export default UniappWeb
