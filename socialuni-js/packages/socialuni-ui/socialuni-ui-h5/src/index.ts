import {App} from "vue";
import QingUiH5 from "qing-ui-h5/src";

const SocialuniUiH5 = {
    async install(app: App) {
        app.use(QingUiH5)
    }
}

export default SocialuniUiH5
