<template>
  <view class="bg-default">
    <user-detail-view ref="userDetail"></user-detail-view>
    <msg-input v-if="showMsgInput">
    </msg-input>
  </view>
</template>

<script lang="ts">
import {Options, Vue} from 'vue-property-decorator'
import UniUtil from "socialuni-sdk/src/utils/UniUtil";
import MsgInput from "socialuni-view/src/components/MsgInput.vue";
import {onHide, onLoad, onShow} from "@dcloudio/uni-app";
import UserDetailView from "socialuni-view/src/views/user/UserDetailView.vue";
import {onMounted} from "vue";

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
