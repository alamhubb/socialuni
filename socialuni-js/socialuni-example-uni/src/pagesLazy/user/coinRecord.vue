<template>
  <socialuni-coin-record-view></socialuni-coin-record-view>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-facing-decorator'
import SocialuniCoinRecordView from '@socialuni/socialuni-user-view-uni/src/views/user/coin/SocialuniCoinRecordView.vue'

@Component({
  components: {
    SocialuniCoinRecordView
  }
})
export default class SocialuniCoinRecordPage extends Vue {
}
</script>
