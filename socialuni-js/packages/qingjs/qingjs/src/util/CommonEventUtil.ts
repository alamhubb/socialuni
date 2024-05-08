import mitt from "mitt";

export default class CommonEventUtil {
    private static event = mitt()

    static emit(name: string, data?: any) {
        console.log('发布：' + name)
        this.event.emit(name, data)
    }

    static off(name: string) {
        console.log('取消监听：' + name)
        this.event.off(name)
    }

    static on(name: string, handler: (data: any) => void): void {
        console.log('订阅通知：' + name)
        this.event.on(name, async (data: any) => {
            console.log('触发订阅：' + name)
            await handler(data)
        })
    }
}
