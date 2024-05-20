import {App} from "vue";
import QingUiH5 from "qing-ui-h5/src";
import SocialuniUi from "socialuni-ui/src";
import "./styles/index.scss";

const SocialuniUiH5 = {
    async install(app: App) {
        app.use(QingUiH5)
        app.use(SocialuniUi)
    }
}

export default SocialuniUiH5
