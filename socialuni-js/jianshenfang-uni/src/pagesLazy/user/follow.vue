<template>
  <socialuni-follow-view class="h100p"></socialuni-follow-view>
</template>

<script lang="ts">
import {Vue, Component, toNative} from 'vue-facing-decorator'
import SocialuniFollowView from "socialuni-user-view-uni/src/views/user/follow/SocialuniFollowView.vue";


@Component({
  components: {SocialuniFollowView}
})
class FollowPage extends Vue {

}
export default toNative(FollowPage)
</script>
