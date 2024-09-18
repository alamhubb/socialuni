<template>
  <socialuni-coin-record-view></socialuni-coin-record-view>
</template>

<script lang="ts">
import { Component, Vue , toNative} from 'vue-facing-decorator'
import SocialuniCoinRecordView from 'socialuni-user-view-uni/src/views/user/coin/SocialuniCoinRecordView.vue'


@Component({
  components: {
    SocialuniCoinRecordView
  }
})
class SocialuniCoinRecordPage extends Vue {
}
export default toNative(SocialuniCoinRecordPage)
</script>
