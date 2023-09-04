<template>
  <socialuni-expand-view></socialuni-expand-view>
</template>

<script lang="ts">
import {Components, Vue} from 'vue-facing-decorator'
import SocialuniExpandView from "@socialuni/socialuni-expand-view-uni/src/views/SocialuniExpandView.vue";
@Component({
  components: {SocialuniExpandView}
})
export default class SocialuniExpandPage extends Vue {

}
</script>
