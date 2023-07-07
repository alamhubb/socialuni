import {useResizeObserver} from '@vueuse/core'
import MediaConst from "./MediaConst";
import {ref} from "vue";

window.onresize = (e) => {
    MediaUtil.windowWidth.value = document.body.clientWidth
    // MediaUtil.windowHeight = document.body.clientHeight
}

export default class MediaUtil {
    static mediaStyles: { [key in string]: any[] } = {}
    static windowWidth = ref(document.body.clientWidth)

    // static windowHeight = document.body.clientHeight

    static get gtMd() {
        return this.windowWidth.value > MediaConst.md
    }


    /*
        static init(...styleArgs: any[]) {
            console.log(styleArgs)
            const mediaUtil: MediaUtil = new MediaUtil()
            window.onresize = (e) => {
                mediaUtil.windowWidth.value = document.body.clientWidth
                mediaUtil.windowHeight = document.body.clientHeight
                console.log(mediaUtil.windowWidth)
            }
            mediaUtil.mediaStyles['default'] = styleArgs
            return mediaUtil
        }

        sm(...styleArgs: any[]) {
            this.mediaStyles['sm'] = styleArgs
            return this
        }

        md(...styleArgs: any[]) {
            this.mediaStyles['md'] = styleArgs
            return this
        }

        get style() {
            const resStylesAry = []
            if (this.mediaStyles['default']) {
                resStylesAry.push(this.mediaStyles['default'])
            }
            if (this.windowWidth.value > MediaConst.sm && this.mediaStyles['sm']) {
                resStylesAry.push(this.mediaStyles['sm'])
            }
            if (this.windowWidth.value > MediaConst.md && this.mediaStyles['md']) {
                resStylesAry.push(this.mediaStyles['md'])
            }
            if (this.windowWidth.value > MediaConst.lg && this.mediaStyles['lg']) {
                resStylesAry.push(this.mediaStyles['lg'])
            }
            if (this.windowWidth.value > MediaConst.xl && this.mediaStyles['xl']) {
                resStylesAry.push(this.mediaStyles['xl'])
            }
            if (this.windowWidth.value > MediaConst.sl && this.mediaStyles['sl']) {
                resStylesAry.push(this.mediaStyles['sl'])
            }
            const resStyles = resStylesAry.flatMap(item => item.flat())

            console.log(resStyles)
            return Object.assign({}, ...resStyles)
        }*/
}
