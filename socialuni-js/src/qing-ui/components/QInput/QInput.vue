<template>
  <div class="row-col-center bd bd-round h30 px-sm">
    <input :value="value" class="flex-1 px-xs font-df" @input="inputEvent"/>
    <q-icon v-show="(!hideClear)&&value&&value.length" icon="close" class="color-sub pd-xs" size="12"
            @click="clear"></q-icon>
  </div>
</template>

<script lang="ts">
import { Component, Emit, Model, Prop, Vue } from 'vue-property-decorator'
import QIcon from '@/qing-ui/components/QIcon/QIcon.vue'

@Component({
  components: { QIcon }
})
export default class QInput extends Vue {
  @Model('input') readonly value: string

  @Prop({ default: false }) readonly hideClear: boolean

  inputEvent ({ detail }) {
    this.input(detail.value)
  }

  @Emit()
  input (value) {
    return value
  }

  @Emit()
  clear () {
    this.input('')
  }
}
</script>
