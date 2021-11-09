<template>
  <mine-detail></mine-detail>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
import MineDetail from '@/pages/user/MineDetail.vue'

@Component({
  components: { MineDetail }
})
export default class MinePage extends Vue {
  created () {
    console.log(this)
  }
}
</script>
