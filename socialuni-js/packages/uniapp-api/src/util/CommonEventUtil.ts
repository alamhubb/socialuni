import mitt from "mitt";

export default class CommonEventUtil {
    private static event = mitt()

    static emit(name: string, data?: any) {
        console.log('发布：' + name)
        this.event.emit(name, data)
    }

    static on(name: string, handler: (data: any) => void): void {
        console.log('收到通知：' + name)
        this.event.on(name, handler)
    }
}
