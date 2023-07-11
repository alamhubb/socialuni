<template>
    <view class="bg-default h100p">
        <!--    <input class="uni-input" focus placeholder="自动获得焦点" @confirm="addFriend"/>-->
        <uni-list>
            <uni-list-chat v-for="(applyData,index) in friendApplyList"
                           :title="applyData.nickname" :avatar="applyData.avatar"
                           :note="applyData.applyMsg"
                           @click="toUserDetail(applyData.id)">
                <template v-if="applyData.isMine">
                    <view class="chat-custom-right">
                        <text class="chat-custom-text">{{ formatTime(applyData.createTime) }}</text>

                        <!-- 需要使用 uni-icons 请自行引入 -->
                        <!--          <uni-icons type="star-filled" color="#999" size="18"></uni-icons>-->
                    </view>
                    <view class="chat-custom-right">
                        {{ friendRuestResult(applyData.friendApplyStatus) }}
                    </view>
                </template>
                <template v-else>
                    <view class="chat-custom-right">
                        <text class="chat-custom-text">{{ formatTime(applyData.createTime) }}</text>
                    </view>
                    <view class="chat-custom-right"
                          v-if="applyData.friendApplyStatus === SocialuniAddFriendStatus.init">
                        <uni-tag type="primary" class="mr-sm" text="同意"
                                 @click="acceptFriendApplication(applyData)"></uni-tag>
                        <!--            <uni-tag type="success" text="拒绝" @click="refuseFriendApplication(applyData)"></uni-tag>-->
                    </view>
                    <view class="chat-custom-right" v-else>
                        {{ friendRuestResult(applyData.friendApplyStatus) }}
                    </view>
                </template>
            </uni-list-chat>
        </uni-list>
        <!--    <uni-list v-if="type === 'black'">
              <uni-title type="h1" align="center" title="黑名单列表"></uni-title>
              <uni-list-chat v-for="(black,index) in blackList" :title="black.nickname" :avatar="black.fromFaceURL"
                             :note="black.reqMsg">
                &lt;!&ndash;        <view class="chat-custom-right">
                          <text class="chat-custom-text">{{  formatTime(black.createTime)   }}</text>
                        </view>&ndash;&gt;
                <view class="chat-custom-right">
                  <text class="chat-custom-text" @click.stop="removeBlack(black)">从黑名单移除</text>
                </view>
              </uni-list-chat>
            </uni-list>


            <uni-list>
              <uni-title type="h1" align="center" title="已添加的好友列表"></uni-title>
              <view v-for="(friend,index) in friendList">
                <uni-list-chat v-if="friend.friendInfo && !friend.blackInfo" :title="friend.friendInfo.nickname"
                               :avatar="friend.friendInfo.fromFaceURL">
                  <view class="chat-custom-right">
                    <text class="chat-custom-text">{{ formatTime(friend.friendInfo.createTime) }}</text>
                  </view>
                  <view class="chat-custom-right">
                    <uni-tag type="warning" text="添加到黑名单" @click="addBlack(friend.friendInfo)"></uni-tag>
                    <uni-tag type="error" text="删除好友" @click="deleteFriend(friend.friendInfo)"></uni-tag>
                  </view>
                </uni-list-chat>
                <uni-list-chat v-if="friend.blackInfo" :title="friend.blackInfo.nickname"
                               :avatar="friend.blackInfo.fromFaceURL">
                  <view class="chat-custom-right">
                    <text class="chat-custom-text" @click.stop="removeBlack(friend.blackInfo)">从黑名单移除</text>
                  </view>
                </uni-list-chat>
              </view>
            </uni-list>-->


        <!--    <view class="row-col-center" >
              <div class="font-xs">已添加的好友列表::::</div>
              <div v-for="(friend,index) in friendList" >
                <div class="font-xs" v-if="friend.friendInfo"> 没拉黑的好友: {{friend.friendInfo.nickname}}</div>
                <div class="font-xs" v-if="friend.blackInfo"> 被拉黑的好友: {{friend.blackInfo.nickname}}</div>
              </div>
            </view>-->

    </view>
</template>

<script lang="ts">
import {Options, Vue} from 'vue-property-decorator'
import {onLoad, onPullDownRefresh} from "@dcloudio/uni-app";
import SocialuniAppUtil from "@socialuni/socialuni-app-sdk/src/util/UniUtil";
import DateUtil from "@socialuni/socialuni-util/src/util/DateUtil";
import FriendApplyType from "@socialuni/socialuni-constant/constant/FriendApplyType";
import SocialuniFriendAPI from "@socialuni/socialuni-im-api/src/api/SocialuniFriendAPI";
import SocialuniFriendApplyUserRO from "@socialuni/socialuni-im-api/src/model/RO/SocialuniFriendApplyUserRO";
import SocialuniCommonStatus from "@socialuni/socialuni-constant/constant/status/SocialuniCommonStatus";
import ToastUtil from "@socialuni/socialuni-native-uni/src/util/ToastUtil";
import FriendAddQO from "@socialuni/socialuni-im-api/src/model/QO/firend/FriendAddQO";
import SocialuniAddFriendStatus from "@socialuni/socialuni-im-api/src/constant/SocialuniAddFriendStatus";
import PageUtil from "@socialuni/socialuni-util/src/util/PageUtil";
import OpenImFriendApplyRO from "@socialuni/socialuni-api-base/src/model/openIm/OpenImFriendApplyRO";
import {socialChatModule} from "../../store/SocialChatModule";
import QTabs from "@socialuni/socialuni-ui-uni/src/components/QTabs/QTabs.vue";
import SocialuniAddFriendType from "@socialuni/socialuni-im-api/src/constant/SocialuniAddFriendType";

@Options({
    components: {
        QTabs
    }
})
export default class FriendApplyListView extends Vue {
    sendFriendApplicationList: OpenImFriendApplyRO[] = []
    FriendApplyType = FriendApplyType
    recvFriendApplicationList: OpenImFriendApplyRO[] = []
    blackList: Object[] = []
    friendList: Object[] = []
    type = "";

    SocialuniAddFriendStatus = SocialuniAddFriendStatus


    friendApplyList: SocialuniFriendApplyUserRO [] = []

    created() {
        onPullDownRefresh(() => {
            this.queryList()
        })
        this.startQuery()
    }

    startQuery() {
        uni.startPullDownRefresh()
    }

    async queryList() {
        const res = await SocialuniFriendAPI.queryFriendApplyList()
        this.friendApplyList = res.data
        uni.stopPullDownRefresh()
    }


    applyRecordList() {

    }

    formatTime(dateStr) {
        return DateUtil.parseTime(dateStr)
    }

    toUserDetail(userId: string) {
        PageUtil.toUserDetail(userId)
    }

    // addFriend(e) {
    //   console.error('=================',e);
    //   socialChatFriendModule.addFriend(e.detail.value,"请求加好友");
    // }
    mounted() {
        this.refresh();
        /*setTimeout(() => {
          this.refresh();
        }, 1000)*/
        // 检查是否为好友
    }

    get dataList() {
        let dataList = [...this.sendFriendApplicationList, ...this.recvFriendApplicationList]
        // 逆序排序。
        dataList = dataList.sort((a, b) => b.createTime - a.createTime)
        return dataList
    }

    refresh() {
        this.getSendFriendApplicationList();
        this.getRecvFriendApplicationList();
    }

    getData(type: string) {
        this.getSendFriendApplicationList();
        this.getRecvFriendApplicationList();
        /*this.getFriendList();
        switch (type) {
          case "sendFriendApplication":
            this.getSendFriendApplicationList();
            break;
          case "recvFriendApplication":
            this.getRecvFriendApplicationList();
            break;
          case "black":
            this.getBlacklist();
            break;
          case "friend":
            this.getFriendList();
            break;
        }*/
    }

    /**
     * 获取好友列表。
     */
    async getFriendList() {
        (await socialChatModule.openIm()).getFriendList().then(({data}) => {
            this.friendList = JSON.parse(data);
            console.log('friendList', data, this.friendList);
        }).catch(err => {
        })
    }

    /**
     * 从好友列表中删除用户。
     */
    async deleteFriend(user) {
        (await socialChatModule.openIm()).deleteFriend(user.userID).then(({data}) => {
            console.log('deleteFriend', data);
            this.refresh();
            // socialChatModule.checkFriend(this.user);
        }).catch(err => {
        })
    }

    /**
     * 获取黑名单列表
     */
    async getBlacklist() {
        (await socialChatModule.openIm()).getBlackList().then(({data}) => {
            this.blackList = JSON.parse(data);
            console.log('getBlackList', data, this.blackList);
        }).catch(err => {
        })
    }

    /**
     * 将用户添加到黑名单。
     */
    async addBlack(black) {
        (await socialChatModule.openIm()).addBlack(black.userID).then(({data}) => {
            console.log('addBlack', data);
            this.refresh();
        }).catch(err => {
        })
    }

    /**
     * 将用户添加到黑名单。
     */
    async removeBlack(black) {
        (await socialChatModule.openIm()).removeBlack(black.userID).then(({data}) => {
            this.refresh();
        }).catch(err => {
        })
    }

    /**
     * 获取发出的好友请求列表
     */
    async getSendFriendApplicationList() {
        /*;(await socialChatModule.openIm()).getSendFriendApplicationList().then(({data}) => {
          console.log(data)
          const list = JSON.parse(data);
          const newList = []
          for (const datum of list) {
            const item = new OpenImFriendApplyRO(datum)
            item.type = FriendApplyType.sendFriendApply
            newList.push(item)
          }
          this.sendFriendApplicationList = newList
        })*/
    }

    /**
     * 获取收到的好友请求列表
     */
    async getRecvFriendApplicationList() {
        /*await socialChatModule.refreshRecvFriendApplicationList()
        this.recvFriendApplicationList = socialChatModule.recvFriendApplicationList;*/
    }

    /**
     * 接受好友请求。
     * @param item
     */
    async acceptFriendApplication(item: SocialuniFriendApplyUserRO) {
        await SocialuniFriendAPI.addFriend(new FriendAddQO(item.id, '接受您的好友请求', SocialuniAddFriendType.accept))
        SocialuniAppUtil.ToastUtil.toastLong('添加好友成功')
        this.startQuery()
    }

    /**
     * 拒绝好友请求。
     * @param item
     */
    async refuseFriendApplication(item) {
        const options: AccessFriendParams = {
                toUserID: item.fromUserID,
                handleMsg: "拒绝您的好友请求"
            }
        ;(await socialChatModule.openIm()).refuseFriendApplication(options).then(({data}) => {
            console.log('refuseFriendApplication', data);
            // 刷新请求列表
            this.refresh();
        }).catch(err => {
        })
    }

    friendRuestResult(status: string) {
        switch (status) {
            case SocialuniAddFriendStatus.init:
                return "待处理";
            case SocialuniAddFriendStatus.enable:
                return "添加成功";
        }
    }
}
</script>
