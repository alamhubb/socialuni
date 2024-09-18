<template>
 <div class="flex-row" v-bind="$attrs" v-on="$attrs">
   <slot></slot>
 </div>
</template>

<script lang="ts">
import { Component, Emit, Prop, Vue , toNative} from 'vue-facing-decorator'


@Component({})
class QRow extends Vue {
}
export default toNative(QRow)
</script>
