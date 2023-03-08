<template>
  <top-window-view></top-window-view>
</template>

<script lang="ts">
import { Options, Vue } from 'vue-property-decorator'
import TopWindowView from 'socialuni-view/src/views/window/TopWindowView.vue'

@Options({
  components: {
    TopWindowView
  }
})
export default class TopWindow extends Vue {
}
</script>
