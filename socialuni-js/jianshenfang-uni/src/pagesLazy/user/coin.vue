<template>
  <socialuni-coin-view></socialuni-coin-view>
</template>

<script lang="ts">
import { Component, Vue , toNative} from 'vue-facing-decorator'
import SocialuniCoinView from 'socialuni-user-view-uni/src/views/user/coin/SocialuniCoinView.vue'

@toNative
@Component({
  components: {
    SocialuniCoinView
  }
})
export default class SocialuniCoinPage extends Vue {
}
</script>
