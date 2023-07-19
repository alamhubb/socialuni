<template>
  <socialuni-expand-view></socialuni-expand-view>
</template>

<script lang="ts">
import {Options, Vue} from 'vue-property-decorator'
import SocialuniExpandView from "@socialuni/socialuni-expand-view-uni/src/views/SocialuniExpandView.vue";
@Options({
  components: {SocialuniExpandView}
})
export default class SocialuniExpandPage extends Vue {

}
</script>
