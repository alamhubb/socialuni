import {App} from "vue";
import SocialuniScss from 'socialuni-scss/src/index'
import './styles/index.scss'

const SocialuniUiUni = {
    async install(app: App) {
        app.use(SocialuniScss)
    }
}

export default SocialuniUiUni
