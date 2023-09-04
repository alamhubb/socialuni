<template>
  <socialuni-follow-view class="h100p"></socialuni-follow-view>
</template>

<script lang="ts">
import {Vue, Options} from 'vue-facing-decorator'
import SocialuniFollowView from "@socialuni/socialuni-user-view-uni/src/views/user/follow/SocialuniFollowView.vue";

@Component({
  components: {SocialuniFollowView}
})
export default class FollowPage extends Vue {

}
</script>
