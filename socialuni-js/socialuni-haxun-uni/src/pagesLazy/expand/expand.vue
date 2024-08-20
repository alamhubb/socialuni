<template>
  <socialuni-expand-view></socialuni-expand-view>
</template>

<script lang="ts">
import {Component, Vue, toNative} from 'vue-facing-decorator'
import SocialuniExpandView from "socialuni-expand-view-uni/src/views/SocialuniExpandView.vue";

@toNative
@Component({
  components: {SocialuniExpandView}
})
export default class SocialuniExpandPage extends Vue {

}
</script>
