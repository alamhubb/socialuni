import {SocialuniLodingInterface} from "qingjs/src/interface/SocialuniLodingInterface";

class LoadingUtil implements SocialuniLodingInterface {

    loadingInstance = null

    loading(message: string) {
        uni.showLoading(message)
    }

    hideLoading() {
        uni.hideLoading()
    }

}

const loadingUtil = new LoadingUtil()
export default loadingUtil
