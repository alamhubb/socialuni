import RouterUtil from "socialuni-util/src/util/RouterUtil";
import ImPagePath from "../constant/ImPagePath";

export default class ImPageUtil {

    static toMessagePageByChatId(receiveId: string) {
        RouterUtil.navigateTo(ImPagePath.message + '?chatId=' + receiveId)
    }

}
