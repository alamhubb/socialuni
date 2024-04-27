import "./styles/index.scss";
import {App} from "vue";
import SocialuniUiH5 from "socialuni-ui-h5/src";

const SocialuniAppViewH5 = {
    async install(app: App) {
        app.use(SocialuniUiH5)
    }
}

export default SocialuniAppViewH5
