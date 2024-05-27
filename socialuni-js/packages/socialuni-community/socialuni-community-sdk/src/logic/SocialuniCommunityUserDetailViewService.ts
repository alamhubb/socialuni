import TalkVO from "socialuni-api-base/src/model/talk/TalkVO";
import {ComponentInternalInstance, reactive, watch, watchEffect} from "vue";
import {SocialuniViewServiceInterface} from "socialuni/src/interface/SocialuniViewServiceInterface";
import CenterUserDetailRO from "socialuni-api-base/src/model/social/CenterUserDetailRO";
import SocialuniTalkAPI from "socialuni-community-api/src/api/SocialuniTalkAPI";
import {socialuniUserDetailViewService} from "socialuni-user-sdk/src/logic/SocialuniUserDetailViewService";
import SocialuniViewService from "socialuni/src/interface/SocialuniViewService";
import {Vue} from "vue-class-component";

export default class SocialuniCommunityUserDetailViewService extends SocialuniViewService<any> {

    initService(instance: Vue) {
        this.instance = instance

        watch<CenterUserDetailRO>(() => socialuniUserDetailViewService.user, (value, oldValue) => {
            if (!oldValue || value.id !== oldValue.id) {
                this.queryUserTalks()
            }
        })
    }

    get talkIds() {
        if (this.talks.length) {
            return this.talks.map(item => item.id)
        }
        return []
    }


    queryUserTalks() {
        if (socialuniUserDetailViewService.user) {
            SocialuniTalkAPI.queryUserTalksAPI(socialuniUserDetailViewService.user.id, this.talkIds).then((res: any) => {
                this.talks = res.data
            })
        }
    }

    talks: TalkVO[] = []

    applyUserFriendContent: string = null


    /**
     * 添加好友申请。
     */
    async addFriend() {
        // socialChatFriendModule.addFriend(this.user.id, "请求加好友");
        /*const options: AddFriendParams = {
          toUserID: this.user.id,
          reqMsg: this.applyUserFriendContent
        };
    */
        console.log('123123')
        /*await SocialuniFriendAPI.addFriend(new FriendAddQO(this.user.id, this.applyUserFriendContent, SocialuniAddFriendType.apply))
        // await (await socialChatModule.openIm()).addFriend(options)
        QingAppUtil.ToastUtil.toastLong('添加好友申请发送成功，请耐心等待对方回复')*/
    }

    deleteTalk(talkId: string) {
        this.talks.splice(this.talks.findIndex(talk => talk.id === talkId), 1)
    }
}

export const socialuniCommunityUserDetailViewService = reactive(new SocialuniCommunityUserDetailViewService())
