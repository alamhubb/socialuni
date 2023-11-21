import CommunityPagePath from "../constant/CommunityPagePath";
import UserCheckUtil from "socialuni-user-sdk/src/util/UserCheckUtil";
import SocialuniAppUtil from "socialuni-native-util/src/util/SocialuniAppUtil";

export default class CommunityPageUtil {
    static goHome(): void {
        CommunityPageUtil.toTalkPage()
    }

    static toTalkDetail(talkId: string) {
        SocialuniAppUtil.RouterUtil.navigateTo(CommunityPagePath.talkDetail + '?talkId=' + talkId)
    }

    static toTalkAddPage() {
        UserCheckUtil.checkUserBindSchool()
        SocialuniAppUtil.RouterUtil.navigateTo(CommunityPagePath.talkAdd)
    }

    static toTalkPage() {
        SocialuniAppUtil.RouterUtil.switchTab(CommunityPagePath.talk)
    }

    static reLaunchTalkPage() {
        SocialuniAppUtil.RouterUtil.reLaunch(CommunityPagePath.talk + '?load=true')
    }


    static toNotifyPage() {
        SocialuniAppUtil.RouterUtil.switchTab(CommunityPagePath.notify)
    }
}
