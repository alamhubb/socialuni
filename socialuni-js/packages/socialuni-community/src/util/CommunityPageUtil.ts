import RouterUtil from "socialuni-util/src/util/RouterUtil"
import CommunityPagePath from "../constant/CommunityPagePath";
import UserCheckUtil from "socialuni-user/src/util/UserCheckUtil";

export default class CommunityPageUtil {
    static goHome(): void {
        CommunityPageUtil.toTalkPage()
    }

    static toTalkDetail(talkId: string) {
        RouterUtil.navigateTo(CommunityPagePath.talkDetail + '?talkId=' + talkId)
    }

    static toTalkAddPage() {
        UserCheckUtil.checkUserBindSchool()
        RouterUtil.navigateTo(CommunityPagePath.talkAdd)
    }

    static toTalkPage() {
        RouterUtil.switchTab(CommunityPagePath.talk)
    }

    static reLaunchTalkPage() {
        RouterUtil.reLaunch(CommunityPagePath.talk + '?load=true')
    }


    static toNotifyPage() {
        RouterUtil.switchTab(CommunityPagePath.notify)
    }
}
