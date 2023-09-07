export default class WindowEventListener {
    static useKeydownListener(key: { ctrl?: boolean, key: string }, eventFun: (event) => void) {
        window.addEventListener('keydown', (event) => {
            // 检查是否按下了 ctrl+s
            if ((!key.ctrl || event.ctrlKey) && event.key === key.key) {
                eventFun(event)
            }
        })
    }
}
