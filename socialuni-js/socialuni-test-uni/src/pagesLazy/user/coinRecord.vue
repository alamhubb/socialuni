<template>
  <socialuni-coin-record-view></socialuni-coin-record-view>
</template>

<script lang="ts">
import { Options, Vue } from 'vue-property-decorator'
import SocialuniCoinRecordView from 'socialuni-view/src/views/user/coin/SocialuniCoinRecordView.vue'

@Options({
  components: {
    SocialuniCoinRecordView
  }
})
export default class SocialuniCoinRecordPage extends Vue {
}
</script>
