import {SocialuniPlugin} from 'socialuni/src/interface/SocialuniPlugin'

const socialuniCommunityPlugin: SocialuniPlugin = {
    onLaunch() {
        console.log('触发了社区')
    }
}

export default socialuniCommunityPlugin
