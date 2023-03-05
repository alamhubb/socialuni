<template>
  <div v-if="!user.isMine" class="row-col-center mr-xs">
    <view v-if="FollowStatus.eachFollow === userFollowStatus" class="color-content" @click.stop="addFollow">
      {{ userFollowStatus }}
    </view>
    <q-button v-else @click="addFollow">
      {{ userFollowStatus }}
    </q-button>
  </div>
</template>

<script lang="ts">
import {Options, Prop, Vue} from 'vue-property-decorator'
import QButton from "socialuni-view/src/components/QButton/QButton.vue";
import FollowStatus from "socialuni-constant/constant/FollowStatus";
import SocialUserContentRO from "socialuni-api/src/model/social/SocialUserContentRO";
import FollowAddVO from "socialuni-api/src/model/FollowAddVO";
import FollowAPI from "socialuni-api/src/api/socialuni/FollowAPI";

@Options({
  components: {
    QButton
  }
})
export default class SocialuniFollowTag extends Vue {
  @Prop() user: SocialUserContentRO

  FollowStatus = FollowStatus
  followBtnDisabled = false

  get userFollowStatus() {
    return FollowStatus.getFollowStatus(this.user)
  }

  async addFollow() {
    if (!this.followBtnDisabled) {
      this.followBtnDisabled = true
      const followAdd: FollowAddVO = new FollowAddVO(this.user.id)
      // 如果已经关注
      if (this.user.hasFollowed) {
        this.user.hasFollowed = false
        try {
          // 进行取消关注操作
          await FollowAPI.cancelFollowAPI(followAdd)
          this.$emit('change', this.user)
        } catch (e) {
          this.user.hasFollowed = true
        } finally {
          this.followBtnDisabled = false
        }
      } else {
        this.user.hasFollowed = true
        try {
          await FollowAPI.addFollowAPI(followAdd)

          this.$emit('change', this.user)
        } catch (e) {
          this.user.hasFollowed = false
        } finally {
          this.followBtnDisabled = false
        }
      }
    }
  }
}
</script>
