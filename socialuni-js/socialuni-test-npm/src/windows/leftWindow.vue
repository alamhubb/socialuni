<template>
  <div>
    <left-window-view></left-window-view>
  </div>
</template>

<script lang="ts">
import { Options, Vue } from 'vue-property-decorator'
import LeftWindowView from 'socialuni-view/src/views/window/LeftWindowView.vue'

@Options({
  components: {
    LeftWindowView
  }
})
export default class LeftWindow extends Vue {
}
</script>
