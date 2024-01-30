export default class WindowUtil {
    static open(url: string, target?: string = '_blank') {
        window.open(url, target)
    }
}

