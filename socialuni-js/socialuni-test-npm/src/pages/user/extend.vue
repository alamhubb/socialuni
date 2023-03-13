<template>
  <socialuni-extend-view class="h100p"></socialuni-extend-view>
</template>

<script lang="ts">
import {Options, Vue} from 'vue-property-decorator'
import SocialuniExtendView from "socialuni-view/src/views/user/SocialuniExtendView.vue";

@Options({
  components: {
    SocialuniExtendView
  }
})
export default class ExtendPage extends Vue {
}
</script>
