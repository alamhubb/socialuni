<template>
  <socialuni-expand-view></socialuni-expand-view>
</template>

<script lang="ts">
import {Component, Vue} from 'vue-facing-decorator'
import SocialuniExpandView from "socialuni-expand-view-uni/src/views/SocialuniExpandView.vue";
@Component({
  components: {SocialuniExpandView}
})
export default class SocialuniExpandPage extends Vue {

}
</script>
