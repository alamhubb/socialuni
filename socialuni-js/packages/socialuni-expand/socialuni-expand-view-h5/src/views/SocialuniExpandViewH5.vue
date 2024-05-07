<template>
  <div>
    <q-tabs
        v-model="tableValue"
        active-class="bg-white bb-blue-3"
        :tabs="[{value: 'tab1'},{value: 'tab2'}]"
        class="flex-none mt bg-white"
    />
  </div>
</template>

<script lang="ts">
import {Component, Vue, toNative} from 'vue-facing-decorator';
import QTabs from 'qing-ui-h5/src/components/QComponents/QTabs/QTabs.vue'

@toNative
@Component({components: {QTabs}})
export default class SocialuniExpandViewH5 extends Vue {
  tableValue = 'tab2'

}
</script>
