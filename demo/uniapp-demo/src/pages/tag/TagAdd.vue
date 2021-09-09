<template>
  <social-tag-add-page></social-tag-add-page>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
import SocialTagAddPage from 'socialuni/components/SocialTagAdd/SocialTagAddPage.vue'

@Component({
  components: { SocialTagAddPage }
})
export default class TagAddPage extends Vue {

}
</script>
