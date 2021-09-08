<template>
   <social-tag-add></social-tag-add>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
import SocialTagAdd from 'socialuni/components/SocialTagAdd/SocialTagAdd.vue'

@Component({
  components: { SocialTagAdd }
})
export default class TagAddPage extends Vue {

}
</script>
