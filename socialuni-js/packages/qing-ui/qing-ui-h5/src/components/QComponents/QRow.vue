<template>
 <div class="flex-row" v-bind="$attrs" v-on="$attrs">
   <slot></slot>
 </div>
</template>

<script lang="ts">
import { Component, Emit, Prop, Vue } from 'vue-facing-decorator'

@toNative
@Component({})
export default class QRow extends Vue {
}
</script>
