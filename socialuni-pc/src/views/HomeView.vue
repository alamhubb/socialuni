<script setup lang="ts">
import TalkAPI from "@/api/TalkAPI";
import TalkVO from "@/model/talk/TalkVO";

const talks: TalkVO[] = []
TalkAPI.queryTalksAPI().then((res) => {
  talks.push(...res.data)
})

</script>

<template>
  <v-btn>123</v-btn>
  <div>{{talks}}</div>
</template>

