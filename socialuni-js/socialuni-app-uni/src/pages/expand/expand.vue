<template>
  <socialuni-expand-view></socialuni-expand-view>
</template>

<script lang="ts">
import {Component, toNative, Vue} from 'vue-facing-decorator'
import SocialuniExpandView from "socialuni-expand-view-uni/src/views/SocialuniExpandView.vue";

@toNative
@Component({
  components: {SocialuniExpandView}
})
class SocialuniExpandPage extends Vue {

}

export default toNative(SocialuniExpandPage)
</script>
