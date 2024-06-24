<template>
  <div class="flex-row">
    <h1>This is an about 6666</h1>
    <q-button>fasdfs</q-button>
    <div>使用qing-scss</div>
    <div>使用qing-compat-js-ui</div>
    <div>使用socialuni</div>
  </div>
</template>

<style>
</style>

<script lang="ts">
import {Component, Vue, toNative} from 'vue-facing-decorator';
import QButton from "qing-compat-js-ui/src/components/QButton.vue";

@toNative
@Component({
  components: {QButton}
})
export default class HomeView extends Vue {
}
</script>
