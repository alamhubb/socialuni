<template>
  <view class="bg-default">
    <user-detail-view ref="userDetail"></user-detail-view>
<!--    <msg-input v-if="showMsgInput">
    </msg-input>-->
  </view>
</template>

<script lang="ts">
import {Options, Vue} from 'vue-property-decorator'
import UserDetailView from "socialuni/src/view/user/UserDetailView.vue";

@Options({
  components: {UserDetailView}
})
export default class UserDetailPage extends Vue {
}
</script>
