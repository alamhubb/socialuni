<template>
  <view class="bg-default h100p">
    <friend-apply-list-view  class="h100p"></friend-apply-list-view>
  </view>
</template>

<script lang="ts">
import {Options, Vue} from 'vue-property-decorator'
import FriendApplyListView from 'socialuni-im/src/views/chat/FriendApplyListView.vue'

@Options({components: {FriendApplyListView}})
export default class FriendApplyListPage extends Vue {

}
</script>
