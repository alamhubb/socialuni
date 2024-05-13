import {ElLoading} from 'element-plus'
import {SocialuniLodingInterface} from "qingjs/src/interface/SocialuniLodingInterface";

class LoadingUtil implements SocialuniLodingInterface {

    loadingInstance = null

    loading(message: string) {
        this.loadingInstance = ElLoading.service({
            text: message
        })
    }

    hideLoading() {
        if (this.loadingInstance) {
            this.loadingInstance.close()
        }
    }

}

const loadingUtil = new LoadingUtil()
export default loadingUtil
