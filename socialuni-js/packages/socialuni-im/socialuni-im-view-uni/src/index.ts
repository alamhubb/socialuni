import {SocialuniOption} from "socialuni/src/interface/socialuniOption";
import {App, watch} from "vue";
import {socialuniChatModule} from "socialuni-im-sdk/src/store/SocialuniChatModule";


const SocialuniImUni = {
    async install(app: App, socialuniOption: SocialuniOption) {
        watch(() => socialuniChatModule.chatsUnreadNumTotal, () => {
            const chatUnreadNum: number = this.chatsUnreadNumTotal
            // 如果未读数量为0了，则隐藏红点
            if (chatUnreadNum) {
                this.showTabBarRedDot()
            } else {
                this.hideTabBarRedDot()
            }
        })
    }
}

export default SocialuniImUni
