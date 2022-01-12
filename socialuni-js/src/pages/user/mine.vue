<template>
  <qc-mine-detail v-if="isAuthApp" class="h100p"></qc-mine-detail>
  <social-mine-detail-page v-else class="h100p"></social-mine-detail-page>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
import SocialMineDetailPage from '@/pages/user/SocialMineDetailPage.vue'
import SocialuniConfig from '@/socialuni/config/SocialuniConfig'
import QcMineDetail from '@/pages/user/QcMineDetail.vue'

@Component({
  components: {
    QcMineDetail,
    SocialMineDetailPage
  }
})
export default class MinePage extends Vue {
  isAuthApp = SocialuniConfig.authApp
}
</script>
