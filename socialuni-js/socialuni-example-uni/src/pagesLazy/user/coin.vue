<template>
  <socialuni-coin-view></socialuni-coin-view>
</template>

<script lang="ts">
import { Options, Vue } from 'vue-facing-decorator'
import SocialuniCoinView from '@socialuni/socialuni-user-view-uni/src/views/user/coin/SocialuniCoinView.vue'

@Component({
  components: {
    SocialuniCoinView
  }
})
export default class SocialuniCoinPage extends Vue {
}
</script>
