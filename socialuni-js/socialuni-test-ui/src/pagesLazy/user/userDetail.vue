<template>
  <view class="bg-default">
    <user-detail-view ref="userDetail"></user-detail-view>
    <msg-input v-if="showMsgInput">
    </msg-input>
  </view>
</template>

<script lang="ts">
import {Options, Vue} from 'vue-property-decorator'
import {onHide, onLoad, onShow} from "@dcloudio/uni-app";
import {onMounted} from "vue";
import UniUtil from "socialuni-util/src/util/UniUtil";
import UserDetailView from "socialuni/src/view/user/UserDetailView.vue";

@Options({
  components: {MsgInput, UserDetailView}
})
export default class UserDetail extends Vue {
  $refs: {
    userDetail: UserDetailView
  }
  showMsgInput = false

  created() {
    UniUtil.showShareMenu()

    onShow(() => {
      this.showMsgInput = true
    })

    onHide(() => {
      this.showMsgInput = false
    })

    onLoad((params: any) => {
      onMounted(() => {
        this.$refs.userDetail.init(params)
      })
    })
  }
}
</script>
