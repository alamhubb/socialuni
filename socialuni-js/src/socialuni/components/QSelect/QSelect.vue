<template>
  <picker mode="selector" class="w100p" :value="pickerItemIndex" :range="list" :range-key="labelName" @change="change">
    <div class="row-col-center w100p">
      <div class="flex-1">
        <span v-if="pickerItemLabel===null" class="color-tip">请选择</span>
        <span v-else>{{ pickerItemLabel }}</span>
      </div>
      <q-icon class="mr-sm text-gray row-all-center flex-none color-sub" icon="mdi-chevron-down"
              size="24"></q-icon>
    </div>
  </picker>
</template>
<script lang="ts">
import { Component, Emit, Model, Prop, Vue } from 'vue-property-decorator'
import QIcon from '@/socialuni/components/QIcon/QIcon.vue'

/*
显示出来已经选了的城市，给她画上钩
* */
@Component({
  components: { QIcon }
})
export default class QSelect extends Vue {
  @Model('change') readonly value!: any
  @Prop() list: any[]
  @Prop() valueName: any[]
  @Prop() labelName: any[]

  get pickerItemIndex () {
    const pickerItemIndex = this.list.findIndex(item => item[this.valueName] === this.value)
    console.log(pickerItemIndex)
    return pickerItemIndex
  }

  get pickerItemLabel () {
    if (this.pickerItemIndex > -1) {
      return this.list[this.pickerItemIndex][this.labelName]
    }
    return null
  }

  @Emit()
  change ({ detail }: { detail: { value: number } }) {
    return this.list[detail.value][this.valueName]
  }
}
</script>
