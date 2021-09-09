<template>
  <social-mine-detail-page></social-mine-detail-page>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
import SocialMineDetailPage from 'socialuni/components/SocialPages/SocialMineDetailPage.vue'

@Component({
  components: { SocialMineDetailPage }
})
export default class MinePage extends Vue {
  created () {
    console.log(this)
  }
}
</script>
