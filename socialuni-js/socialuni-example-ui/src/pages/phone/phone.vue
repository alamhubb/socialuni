<template>
  <login class="h100p"></login>
</template>


<script lang="ts">
import {Options, Vue} from 'vue-property-decorator'
import Login from "@/pages/user/login/login.vue";

@Options({
  components: {
    Login
  }
})
export default class PhonePage extends Vue {
}
</script>
