<template>
  <div>
    123
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import SocialTalkPage from "@/socialuni/components/SocialPages/SocialTalkPage.vue";
import TalkAPI from "@/socialuni/api/TalkAPI";

const title = ref('Hello123')
console.log(123)
console.log(import.meta)
console.log(import.meta.env.VITE_APP_SOCIALUNI_SECRETKEY)
TalkAPI.queryTalksAPI()
</script>
