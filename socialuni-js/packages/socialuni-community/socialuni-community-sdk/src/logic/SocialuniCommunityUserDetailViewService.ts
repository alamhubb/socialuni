import SocialuniFriendAPI from "socialuni-im-api/src/api/SocialuniFriendAPI";
import FriendAddQO from "socialuni-im-api/src/model/QO/firend/FriendAddQO";
import SocialuniAddFriendType from "socialuni-im-api/src/constant/SocialuniAddFriendType";
import SocialuniAppUtil from "socialuni-native-util/src/util/SocialuniAppUtil";
import TalkVO from "socialuni-api-base/src/model/talk/TalkVO";
import {socialChatModule} from "socialuni-im-sdk/src/store/SocialChatModule";
import {ComponentInternalInstance, reactive, watch, watchEffect} from "vue";
import {SocialuniViewServiceInterface} from "socialuni/src/interface/SocialuniViewServiceInterface";
import CenterUserDetailRO from "socialuni-api-base/src/model/social/CenterUserDetailRO";
import SocialuniTalkAPI from "socialuni-community-api/src/api/SocialuniTalkAPI";
import socialuniUserDetailViewService from "socialuni-user-sdk/src/logic/SocialuniUserDetailViewService";

class SocialuniCommunityUserDetailViewService implements SocialuniViewServiceInterface {

    private instance: ComponentInternalInstance = null

    initService(instance: ComponentInternalInstance) {
        this.instance = instance

        watch<CenterUserDetailRO>(() => socialuniUserDetailViewService.user, (value, oldValue) => {
            console.log(1232123)
            if (!oldValue) {
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
        await SocialuniFriendAPI.addFriend(new FriendAddQO(this.user.id, this.applyUserFriendContent, SocialuniAddFriendType.apply))
        // await (await socialChatModule.openIm()).addFriend(options)
        SocialuniAppUtil.ToastUtil.toastLong('添加好友申请发送成功，请耐心等待对方回复')
    }

    deleteTalk(talkId: string) {
        this.talks.splice(this.talks.findIndex(talk => talk.id === talkId), 1)
    }

    /**
     * 从好友列表中删除用户。
     */
    async deleteFriend() {
        SocialuniAppUtil.AlertUtil.confirm('是否确认解除好友关系').then(async () => {
            (await socialChatModule.openIm()).deleteFriend(socialuniUserDetailViewService.user.id).then(({data}) => {
                console.log('deleteFriend', data)
                SocialuniAppUtil.ToastUtil.toast('成功解除好友关系')
                socialChatModule.checkFriend(socialuniUserDetailViewService.user)
            }).catch(err => {
            })
        })
    }


    /**
     * 将用户添加到黑名单。
     */
    async addBlack() {
        SocialuniAppUtil.AlertUtil.confirm('是否确认添加到黑名单').then(async () => {
            (await socialChatModule.openIm()).addBlack(this.user.id).then(({data}) => {
                socialChatModule.checkFriend(this.user)
            }).catch(err => {
            })
        })

    }

    /**
     * 将用户从黑名单移除。
     */
    async removeBlack() {
        SocialuniAppUtil.AlertUtil.confirm('是否确认从黑名单移除').then(async () => {
            (await socialChatModule.openIm()).removeBlack(this.user.id).then(({data}) => {
                socialChatModule.checkFriend(this.user)
            }).catch(err => {
            })
        })

    }


}

const socialuniCommunityUserDetailViewService = reactive(new SocialuniCommunityUserDetailViewService())

export default socialuniCommunityUserDetailViewService
