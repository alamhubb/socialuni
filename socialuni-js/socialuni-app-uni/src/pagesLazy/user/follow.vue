<template>
  <socialuni-follow-view class="h100p"></socialuni-follow-view>
</template>

<script lang="ts">
import {Vue, Component} from 'vue-facing-decorator'
import SocialuniFollowView from "socialuni-user-view-uni/src/views/user/follow/SocialuniFollowView.vue";

@toNative
@Component({
  components: {SocialuniFollowView}
})
export default class FollowPage extends Vue {

}
</script>
