<template>
  <socialuni-coin-view></socialuni-coin-view>
</template>

<script lang="ts">
import { Component, Vue , toNative} from 'vue-facing-decorator'
import SocialuniCoinView from 'socialuni-user-view-uni/src/views/user/coin/SocialuniCoinView.vue'


@Component({
  components: {
    SocialuniCoinView
  }
})
class SocialuniCoinPage extends Vue {
}
export default toNative(SocialuniCoinPage)
</script>
