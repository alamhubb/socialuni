<template>
  <socialuni-follow-view class="h100p"></socialuni-follow-view>
</template>

<script lang="ts">
import {Vue, Options} from 'vue-property-decorator'
import SocialuniFollowView from "socialuni-view/src/views/user/follow/SocialuniFollowView.vue";

@Options({
  components: {SocialuniFollowView}
})
export default class FollowPage extends Vue {

}
</script>
