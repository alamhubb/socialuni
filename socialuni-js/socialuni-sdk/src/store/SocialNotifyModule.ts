//用来存储当前用户的一些信息
import {Pinia, Store} from "pinia-class-component"
import UnreadNotifyVO from "socialuni-api/src/model/notify/UnreadNotifyVO";
import CenterUserDetailRO from "socialuni-api/src/model/social/CenterUserDetailRO";
import NotifyAPI from "socialuni-api/src/api/socialuni/NotifyAPI";

@Store
export default class SocialNotifyModule extends Pinia {
    private notifies: UnreadNotifyVO [] = []

    clearNotifies() {
        this.notifies = []
    }

    get unreadNotifies(): UnreadNotifyVO [] {
        return this.notifies.filter(item => !item.hasRead)
    }

    addUnreadNotifies(user: CenterUserDetailRO) {
        const notify: UnreadNotifyVO = new UnreadNotifyVO()
        notify.hasRead = false
        notify.avatar = user.avatar
        notify.nickname = user.nickname
        // notify.vipFlag = user.vipFlag
        // 从列表中加入这个
        this.notifies.unshift(notify)
    }

    //应该查询前20条已读和未读通知，然后有未读通知推送

    queryNotifiesAction() {
        // 查询前20条，未读优先，如果没有未读，就是按时间排序
        return NotifyAPI.queryNotifiesAPI().then((res: any) => {
            this.notifies = res.data
        })
    }


    queryUnreadNotifiesAndUpdateHasReadAction() {
        this.notifies.forEach(item => {
            item.hasRead = true
        })
        // 通知后台和前台把消息改为已读
        return NotifyAPI.queryUnreadNotifiesAndUpdateHasReadAPI().then((res: any) => {
            this.notifies = res.data
        })
    }
}
