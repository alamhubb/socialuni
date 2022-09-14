<template>
  <login class="h100p"></login>
</template>


<script lang="ts">
import {Component, Vue} from 'vue-property-decorator'
import Login from "@/pages/login/login.vue";

@Component({
  components: {
    Login
  }
})
export default class PhonePage extends Vue {
}
</script>
