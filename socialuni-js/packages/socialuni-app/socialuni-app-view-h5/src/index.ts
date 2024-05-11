import {App} from "vue";
import SocialuniUiH5 from "socialuni-ui-h5/src";
import SocialuniApp from "socialuni-app-sdk/src";

const SocialuniAppViewH5 = {
    async install(app: App) {
        app.use(SocialuniUiH5)
        app.use(SocialuniApp)
    }
}

export default SocialuniAppViewH5
