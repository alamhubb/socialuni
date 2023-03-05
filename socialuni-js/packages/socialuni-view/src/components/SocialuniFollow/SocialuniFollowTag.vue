<template>
  <div>
    <q-button v-if="!isMine" @click="addFollow">
      {{ followStatus }}
    </q-button>
    <view v-else class="color-content" @click.stop="addFollow">{{ followStatus }}</view>
  </div>
</template>

<script lang="ts">
import {Options, Prop, Vue} from 'vue-property-decorator'
import CenterUserDetailRO from "socialuni-api/src/model/social/CenterUserDetailRO";
import UserUtil from "socialuni-sdk/src/utils/UserUtil";
import QIcon from "socialuni-view/src/components/QIcon/QIcon.vue";
import QButton from "socialuni-view/src/components/QButton/QButton.vue";
import FollowStatus from "socialuni-constant/constant/FollowStatus";
import SocialUserContentRO from "socialuni-api/src/model/social/SocialUserContentRO";

@Options({
  components: {
    QIcon,
    QButton
  }
})
export default class SocialuniFollowTag extends Vue {
  @Prop() user: SocialUserContentRO

  get followStatus() {
    return FollowStatus.getFollowStatus(this.user)
  }
}
</script>
