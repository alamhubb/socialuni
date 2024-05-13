import {App} from "vue";
import "./styles/index.scss";
import QingUiUni from "qing-ui-uni/src";
import SocialuniUi from "socialuni-ui/src";

const SocialuniUiUni = {
    async install(app: App) {
        app.use(souniui)
        app.use(SocialuniUi)
        app.use(QingUiUni)
    }
}

export default SocialuniUiUni
