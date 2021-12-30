<template>
  <social-talk-add-page></social-talk-add-page>
</template>
<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
import SocialTalkAddPage from 'socialuni/components/social-ui/SocialPages/SocialTalkAddPage.vue'

@Component({
  components: {
    SocialTalkAddPage
  }
})
export default class TalkAddPage extends Vue {
}
</script>
