<template>
  <picker mode="selector" class="w100p" :value="pickerItemIndex" :range="list" :range-key="labelName" @change="change">
    <div class="row-col-center w100p">
      <div class="flex-1">
        <span v-if="pickerItemLabel===null" class="color-tip">请选择</span>
        <span v-else>{{ pickerItemLabel }}</span>
      </div>
      <q-icon v-if="pickerItemLabel!==null" class="mr-sm row-all-center flex-none color-tip"
              icon="mdi-close-circle"
              size="16" @click.stop="clearValue"></q-icon>
      <q-icon class="mr-sm row-all-center flex-none color-tip" icon="mdi-chevron-down"
              size="24"></q-icon>
    </div>
  </picker>
</template>
<script lang="ts">
import {Emit, Model, Component, Prop, Vue, toNative} from 'vue-facing-decorator'
import QIcon from "qingjs-ui/src/components/QIcon.vue";
/*
显示出来已经选了的城市，给她画上钩
* */

/*
显示出来已经选了的城市，给她画上钩
* */
@toNative
@Component({
  components: {QIcon}
})
export default class QSelect extends Vue {
  @Model('change') readonly value!: any
  @Prop() list: any[]
  @Prop() valueName: any[]
  @Prop() labelName: any[]

  get pickerItemIndex() {
    //@ts-ignore
    const pickerItemIndex = this.list.findIndex(item => item[this.valueName] === this.value)
    return pickerItemIndex
  }

  get pickerItemLabel() {
    if (this.pickerItemIndex > -1) {
      //@ts-ignore
      return this.list[this.pickerItemIndex][this.labelName]
    }
    return null
  }

  clearValue() {
    this.change({detail: {value: null}})
  }

  @Emit('update:modelValue')
  change({detail}: { detail: { value: number } }) {
    const index = detail.value
    if (index === null) {
      return null
    }
    //@ts-ignore
    return this.list[index][this.valueName]
  }
}
</script>
