import {initSocialuniStore} from "./src/store/store";

const Socialuni = {
    async install() {
       await initSocialuniStore()
        // UniUtil.showShareMenu()
    }
}
export default Socialuni
