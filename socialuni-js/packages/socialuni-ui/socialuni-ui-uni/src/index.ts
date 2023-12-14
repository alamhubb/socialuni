import {App} from "vue";
import QingScss from 'qing-scss/src/index'

const SocialuniUiUni = {
    async install(app: App) {
        app.use(QingScss)
    }
}

export default SocialuniUiUni
