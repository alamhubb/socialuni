<template>
    <view class="bg-default">
        <socialuni-community-user-detail-view>
            <template #navBarRight>
                <div class="row-end-center flex-1">
                    <q-icon icon="list-dot" size="20" @click="openMoreMenu"></q-icon>
                </div>
            </template>
            <template #userBaseInfoRightPre>
                <q-button @click="toMessagePage" class="mr-sm">
                    <q-icon icon="mdi-chat-outline" size="14"></q-icon>
                    私信
                </q-button>
            </template>
        </socialuni-community-user-detail-view>

        <q-popup ref="moreActionMenu" bottom>
            <uni-list class="pb-xl">
                <uni-list-item title="解除好友" link
                               @click="deleteFriend"></uni-list-item>
                <uni-list-item title="加入黑名单" link
                               @click="addBlack"></uni-list-item>
                <uni-list-item title="从黑名单移除" link
                               @click="removeBlack"></uni-list-item>
            </uni-list>
        </q-popup>
    </view>
</template>

<script lang="ts">
import SocialuniCommunityUserDetailView
    from "socialuni-community-view-uni/src/views/user/SocialuniCommunityUserDetailView.vue";
import socialuniUserDetailViewService from "socialuni-user-sdk/src/logic/SocialuniUserDetailViewService";
import QButton from "socialuni-ui-uni/src/components/QButton/QButton.vue";
import QIcon from "socialuni-ui-uni/src/components/QIcon/QIcon.vue";
import socialuniCommunityUserDetailViewService
    from "socialuni-community-sdk/src/logic/SocialuniCommunityUserDetailViewService";
import QPopup from "socialuni-ui-uni/src/components/QPopup/QPopup.vue";
import {Options, Vue} from "vue-property-decorator";
import SocialuniAppUtil from "socialuni-native-util/src/util/SocialuniAppUtil";

@Options({
    components: {
        QPopup,
        QButton,
        QIcon,
        SocialuniCommunityUserDetailView
    }
})
export default class UserDetailPage extends Vue {
    $refs: {
        moreActionMenu: QPopup
    }

    openMoreMenu() {
        this.$refs.moreActionMenu.open()
    }


    /**
     * 从好友列表中删除用户。
     */
    async deleteFriend() {
        SocialuniAppUtil.AlertUtil.confirm('是否确认解除好友关系').then(async () => {
            /*(await socialChatModule.openIm()).deleteFriend(socialuniUserDetailViewService.user.id).then(({data}) => {
                console.log('deleteFriend', data)
                SocialuniAppUtil.ToastUtil.toast('成功解除好友关系')
                socialChatModule.checkFriend(socialuniUserDetailViewService.user)
            }).catch(err => {
            })*/
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


    async toMessagePage() {
        //无论什么情况都可以进入消息页面，比如要查看两个人的历史聊天消息呢
        // socialChatModule.setChatIdToMessagePage(this.user.id)

        //未登录不能发消息
        //然后判断对方是否接收陌生人消息
        //不接收，判断是否是好友
        //不是好友则提示对方不接收陌生人消息，是否添加好友

        //不需要好友功能了，
        //可以付费消息。 每日可免费领取 1金豆？
        //

        //可以付费发送消息，默认关注对方，对方给你回消息免费。
        //如果你取消关注对方，


        //我有两个发消息的校验逻辑
        //用哪一个？

        //发起付费，则关注对方。
        //发送消息关注吗？

        //对方关注了你，你就能给对方发消息。
        //允许陌生人消息，谁都可以给你发消息。 你可以选择给对方拉黑， 也可以选择不接受陌生人消息
        //然后给你发消息，就需要付费。 付费以后默认关注了你，你给对方发消息就免费？

        //如果你给对方发了消息，如果对方为陌生人，则你必须要开启接收陌生人消息。
        //如果对方是收费的，则代表你关注了对方，付费就关注，然后对方可以免费给你发消息，付费发3条？， 然后呢你不需要打开陌生人消息

        //能不能发消息，

        //应该能进入页面的，
        // if (this.imUserDetail.blackUser){
        //   SocialuniAppUtil.ToastUtil.toast("您已被对方拉黑，无法发送消息")
        // }
        //未关注，但是也可以都是可以进入聊天页面的


        // if (this.user.hasBeFollowed) {
        //   this.applyUserFriendContent = null
        //   this.$refs.applyUserFriendDialog.open()
        // } else {
        // }
        // socialChatModule.setCurChatByUserId(this.user.id)
        //除了是否关注，还有是否已经发起过对话，chatuservo里面要保存还能再发几条
        //判断是否已经支付过了。3条，然后对方每次回复你都可以发三条，然后就需要再次支付，开启了支付
        //mock chat
        // chatModule.userDetailToMessagePage(this.userProp.chat)
        //如果有chat读取，如果没有创建读取
        // chatModule.setChatAction(chat)
    }

}
</script>
