export default class WindowEventListener {
    static useKeydownListener(key: { ctrl?: boolean, key: string }, eventFun: (event) => void) {
        window.addEventListener('keydown', (event) => {
            // 检查是否按下了 ctrl+s
            if ((!key.ctrl || event.ctrlKey) && event.key === key.key) {
                eventFun(event)
            }
        })
    }

    static useMessageListener(domain:string, eventFun: (event) => void) {
        window.addEventListener('message', (event) => {
            if (event.origin !== domain) {
                return; // 检查消息的来源
            }
            console.log("Message received in iframe B:", event.data);
            eventFun(event)
        })
    }
}
