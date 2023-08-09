import "./styles/index.scss";
import SocialuniAppUniUtil from "./utils/SocialuniAppUniUtil";

const SocialuniAppViewUni = {
    install() {
        SocialuniAppUniUtil.checkUpdate()
    }
}

export default SocialuniAppViewUni
