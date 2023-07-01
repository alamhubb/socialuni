<template>
  <socialuni-coin-view></socialuni-coin-view>
</template>

<script lang="ts">
import { Options, Vue } from 'vue-property-decorator'
import SocialuniCoinView from 'socialuni-user-view-uni/src/views/user/coin/SocialuniCoinView.vue'

@Options({
  components: {
    SocialuniCoinView
  }
})
export default class SocialuniCoinPage extends Vue {
}
</script>
