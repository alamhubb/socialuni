import mitt from "mitt";
import SocialuniUserEventConst from "socialuni-user-sdk/src/constant/SocialuniUserEventConst";

export default class SocialuniEventUtil {
    private static event = mitt()

    static emit(name: string, data?: any) {
        this.event.emit(name, data)
    }

    static on(name: string, handler: (data: any) => void): void {
        this.event.on(name, handler)
    }
}
