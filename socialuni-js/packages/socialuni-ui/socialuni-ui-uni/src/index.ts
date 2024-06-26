import {App} from "vue";
import QingUiUni from "qingjs-ui-uni/src";
import SocialuniUi from "socialuni-ui/src";
import "./styles/index.scss";

const SocialuniUiUni = {
    async install(app: App) {
        app.use(QingUiUni)
        app.use(SocialuniUi)
    }
}

export default SocialuniUiUni
