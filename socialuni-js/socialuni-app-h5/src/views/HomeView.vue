<template>
  <div class="flex-row">
    <h1>This is an about 111</h1>
    <q-button>fasdfs</q-button>
    <div>使用qing-scss</div>
    <div>使用qing-ui</div>
    <div>使用socialuni</div>
  </div>
</template>

<style>
</style>

<script lang="ts">
import {Component, toNative, Vue} from 'vue-facing-decorator';
import QButton from "qing-ui-uni/src/components/QButton/QButton.vue";

@Component({
  components: {QButton}
})
class HomeView extends Vue {
}

export default toNative(HomeView)
</script>
