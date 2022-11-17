<template>
  <view class="bg-default h100p">
    <input class="uni-input" focus placeholder="自动获得焦点" @confirm="addFriend"/>
  </view>
</template>

<script lang="ts">
import {Options, Vue} from 'vue-property-decorator'
import {socialChatFriendModule} from "socialuni-sdk/src/store/store"

@Options({components: {}})
export default class ChatFriendPage extends Vue {
  addFriend(e) {
    console.error('=================',e);
    socialChatFriendModule.addFriend(e.detail.value,"请求加好友");
  }
}
</script>
