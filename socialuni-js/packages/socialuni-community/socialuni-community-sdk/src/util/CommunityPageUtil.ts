import CommunityPagePath from "../constant/CommunityPagePath";
import UserCheckUtil from "socialuni-user-sdk/src/util/UserCheckUtil";
import QingAppUtil from "qing-compat-js/src/util/QingAppUtil";

export default class CommunityPageUtil {
    static goHome(): void {
        CommunityPageUtil.toTalkPage()
    }

    static toTalkDetail(talkId: string) {
        QingAppUtil.RouterUtil.navigateTo(CommunityPagePath.talkDetail + '?talkId=' + talkId)
    }

    static toTalkAddPage() {
        UserCheckUtil.checkUserBindSchool()
        QingAppUtil.RouterUtil.navigateTo(CommunityPagePath.talkAdd)
    }

    static toTalkPage() {
        QingAppUtil.RouterUtil.switchTab(CommunityPagePath.talk)
    }

    static reLaunchTalkPage() {
        QingAppUtil.RouterUtil.reLaunch(CommunityPagePath.talk + '?load=true')
    }


    static toNotifyPage() {
        QingAppUtil.RouterUtil.switchTab(CommunityPagePath.notify)
    }
}
