import {reactive} from "vue";

class SocialuniSystemModule {
    // 条件编译属性
    // ios android h5,默认安卓
    // 登录平台
    screenHeight = 0
    windowHeight = 0
    screenWidth = 0
    windowWidth = 0

    constructor() {
        window.addEventListener('resize', () => {
            console.log(window.innerWidth)
            socialuniSystem.windowWidth = window.innerWidth
            socialuniSystem.windowHeight = window.innerHeight
            console.log(this.windowWidth)
        })
    }
}

export const socialuniSystem: SocialuniSystemModule = reactive(new SocialuniSystemModule())
