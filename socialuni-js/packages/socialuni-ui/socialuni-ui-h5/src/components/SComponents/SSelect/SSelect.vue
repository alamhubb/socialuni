<template>
  {{ model }}
    <el-select
            ref="select"
            :model-value="model">
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
import {Component, Emit, Model, Prop, Vue} from 'vue-facing-decorator'

/**
 * @author 秦开远
 * @date 2021/1/21 15:25
 *
 * 封装select
 */
@Component
export default class SSelect extends Vue {
    $refs: {
        select: any;
    }

    @Model('change') readonly model!: any

    @Prop({default: '请选择'}) readonly hint: string

    // 区分两种使用模式
    // 第一种使用方式
    // @Prop() readonly option: DataTableColumnOptionVO
    // 第二种使用方式
    @Prop() readonly options: any []
    @Prop({default: 'name'}) readonly label: string
    @Prop({default: 'value'}) readonly value: string
    blurValue: string = null

    //如果没有这个字段，除了输入以后失去焦点，其他情况也会触发失去焦点方法，比如切换select
    canBlur = false

    created() {
        this.canBlur = false
    }

    @Emit()
    change(value) {
        return value
    }

    get optionsGet(): any[] {
        return this.options as any[]
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

    inputValue(v) {
        this.canBlur = true
    }

    blur() {
        this.$refs.select.blur()
    }

    blurSetValue(e: any) {
        if (this.canBlur) {
            this.canBlur = false
            this.blurValue = e.target.value
            this.$emit('blur', this.blurValue)
        }
        this.$emit('selectBlur')
    }

    selectFocus() {
        this.$emit('focus')
    }
}
</script>
