<template>
  <div class="bg-default h100p">
    <friend-apply-list-view  class="h100p"></friend-apply-list-view>
  </div>
</template>

<script lang="ts">
import {Component, Vue, toNative} from 'vue-facing-decorator'
import FriendApplyListView from "socialuni-im-view-uni/src/views/chat/FriendApplyListView.vue";

@toNative
@Component({components: {FriendApplyListView}})
export default class FriendApplyListPage extends Vue {

}
</script>
