<template>
  <el-select ref="select" :value="model" :placeholder="hint" v-bind="$attrs" @change="change" filterable>
    <!--    不能||item,因为存在null的情况会直接把对象赋值给value-->
    <el-option
        v-for="item in optionsGet"
        :key="item[value]!==undefined?item[value]:item"
        :label="optionLabel(item)"
        :value="item[value]!==undefined?item[value]:item"
    />
  </el-select>
</template>

<script lang="ts">
import { Component, Emit, Model, Prop, Vue } from 'vue-property-decorator'
import { ElSelect } from 'element-ui/types/select'

/**
 * @author 秦开远
 * @date 2021/1/21 15:25
 *
 * 封装select
 */
@Component
export default class YSelect extends Vue {
  $refs: {
    select: ElSelect;
  }

  @Model('change') readonly model!: any

  @Prop({ default: '请选择' }) readonly hint: string

  // 区分两种使用模式
  // 第一种使用方式
  // @Prop() readonly option: DataTableColumnOptionVO
  // 第二种使用方式
  @Prop() readonly options: []
  @Prop({ default: 'name' }) readonly label: string
  @Prop({ default: 'value' }) readonly value: string

  @Emit()
  change(value) {
    return value
  }

  get optionsGet() {
    return this.options
  }

  optionLabel(item) {
    if (item[this.label] !== undefined) {
      return item[this.label]
    } else if (item[this.value] !== undefined) {
      return item[this.value]
    } else {
      return item
    }
  }

  blur() {
    this.$refs.select.blur()
  }
}
</script>
