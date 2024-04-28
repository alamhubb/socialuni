import {App} from "vue";
import QingUiUni from "qing-ui-uni/src";

const SocialuniUiUni = {
    async install(app: App) {
        app.use(QingUiUni)
    }
}

export default SocialuniUiUni
