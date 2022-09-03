import {useResizeObserver} from '@vueuse/core'
import MediaConst from "./MediaConst";
import {ref} from "vue";

export default class MediaUtil {
    private mediaStyles: { [key in string]: any[] } = {}
    private windowWidth = ref(document.body.clientWidth)
    private windowHeight = document.body.clientHeight

    static init(...styleArgs: any[]) {
        const mediaUtil: MediaUtil = new MediaUtil()
        window.onresize = (e) => {
            mediaUtil.windowWidth.value = document.body.clientWidth
            mediaUtil.windowHeight = document.body.clientHeight
            console.log(mediaUtil.windowWidth)
        }
        mediaUtil.mediaStyles['default'] = styleArgs
        return mediaUtil
    }

    md(...styleArgs: any[]) {
        this.mediaStyles['md'] = styleArgs
        return this
    }

    get style() {
        if (this.windowWidth.value > MediaConst.sl && this.mediaStyles['sl']) {
            return this.mediaStyles['sl']
        }
        if (this.windowWidth.value > MediaConst.xl && this.mediaStyles['xl']) {
            return this.mediaStyles['xl']
        }
        if (this.windowWidth.value > MediaConst.lg && this.mediaStyles['lg']) {
            return this.mediaStyles['lg']
        }
        if (this.windowWidth.value > MediaConst.md && this.mediaStyles['md']) {
            return this.mediaStyles['md']
        }
        if (this.windowWidth.value > MediaConst.sm && this.mediaStyles['sm']) {
            return this.mediaStyles['sm']
        }
        if (this.mediaStyles['default']) {
            return this.mediaStyles['default']
        } else {
            return {}
        }
    }
}
