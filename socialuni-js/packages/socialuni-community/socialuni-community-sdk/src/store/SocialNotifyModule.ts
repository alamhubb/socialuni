//用来存储当前用户的一些信息

import UnreadNotifyVO from "socialuni-api-base/src/model/notify/UnreadNotifyVO";
import CenterUserDetailRO from "socialuni-api-base/src/model/social/CenterUserDetailRO";
import {reactive} from "vue";
import NotifyAPI from "socialuni-app-api/src/api/NotifyAPI";


class SocialNotifyModule {
    notifies: UnreadNotifyVO [] = []

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
        return NotifyAPI.queryUnreadNotifiesAPI().then((res: any) => {
            this.notifies = res.data
        })
    }


    queryUnreadNotifiesAndUpdateHasReadAction() {
        this.notifies.forEach(item => {
            item.hasRead = true
        })
        // 通知后台和前台把消息改为已读
        return NotifyAPI.queryNotifiesAndUpdateHasReadAPI().then((res: any) => {
            this.notifies = res.data
        })
    }
}


export const socialNotifyModule: SocialNotifyModule = reactive(new SocialNotifyModule())
