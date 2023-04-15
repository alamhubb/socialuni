import {SocialuniPlugin} from 'socialuni/src/interface/SocialuniPlugin'

const socialuniImPlugin: SocialuniPlugin = {
    onLaunch() {
        console.log('触发了Im')
    }
}

export default socialuniImPlugin
