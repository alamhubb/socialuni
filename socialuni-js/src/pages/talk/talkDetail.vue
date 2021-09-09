<template>
  <social-talk-detail-page></social-talk-detail-page>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
import SocialTalkDetailPage from 'socialuni/components/SocialPages/SocialTalkDetailPage.vue'

@Component({
  components: { SocialTalkDetailPage }
})
export default class TalkDetail extends Vue {

}
</script>
