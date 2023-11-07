<template>
  <div class="bd bd-radius row-col-center h30 position-relative" :style="{border: searchBoxBorder}">
    <slot name="searchPre">
    </slot>
    <slot>
      <div
        v-if="selectOptions && selectOptions.optionList.length>1"
        slot="prepend"
        class="row-col-center">
        <el-select
          class="bd-none left-arrow"
          size="mini"
          v-model="selectValue"
          placeholder="搜索条件"
          @change="selectOptionClick"
          :style="{ width: selectWidth }"
          filterable
        >
          <el-option
            v-for="item in selectOptions.optionList"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          >
          </el-option>
        </el-select>
      </div>
      <div
        v-else
        style="background-color: #f5f7fa"
        class="flex-none color-main row-col-center pl-sm pr-sm h100p bd-radius">{{ singleTitle }}
      </div>
    </slot>
    <y-select
      clearable
      v-if="selectOptionSelectArr && showFlag"
      ref="valueSelect"
      size="mini"
      :model="model"
      :options="selectOptionSelectArr"
      class="flex-1 bd-none"
      :class="{'hide-suffix':!isSingle}"
      v-bind="$attrs"
      :multiple="selectOption&&selectOption.multiple"
      @blur="blurSetValue"
      @selectBlur="inputBlur"
      @focus="inputFocus"
      @change="changeHandler"
    >
      <slot name="options" slot-scope="scope" :options="scope.options"></slot>
    </y-select>
    <el-date-picker
      v-else-if="selectOption&&selectOption.type === DataTypeStrEnum.Date"
      v-model="dataValue"
      class="flex-1 bd-none"
      size="mini"
      format="yyyy-MM-dd HH:mm:ss"
      value-format="yyyy-MM-dd HH:mm:ss"
      type="datetimerange"
      :picker-options="CommonDateConst.pickerOptions"
      range-separator="至"
      :editable="false"
      start-placeholder="开始日期"
      end-placeholder="结束日期"
      align="right"
      @focus="inputFocus"
      @blur="inputBlur"
      @change="changeHandler"
    />
    <el-input
      v-else
      ref="searchInput"
      class="flex-1 bd-none"
      size="mini"
      v-bind="$attrs"
      :value="model"
      clearable
      @focus="inputFocus"
      @blur="inputBlur"
      :placeholder="inputPlaceholder"
      @keyup.enter.native="click"
      @input="change">
    </el-input>
    <div
      v-if="showBtn"
      class="flex-none h100p row-col-center px bg-click font-14"
      style="background: #F5F7FA; border-radius: 0px 5px 5px 0px;"
      @click="click">{{ btnTitle }}
    </div>
  </div>
</template>

<script lang="ts">
import {Component, Emit, Model, Prop, Vue, Watch} from 'vue-facing-decorator'
import YSelect from '@/components/YComponent/YSelect/YSelect.vue'
import TableSelect from '@/components/YComponent/TableGroup/TableSelect'
import DataTypeStrEnum from '@/components/YComponent/constant/DataTypeStrEnum'
import YCommonDateConst from '../constant/YCommonDateConst'
import type {ElInput} from "element-ui/types/input";

/**
 * @author 秦开远
 * @date 2021/1/21 15:25
 *
 * 在数据源业务基础上，封装基础table
 */
@Component({
  components: {YSelect}
})
export default class YSearch extends Vue {
  $refs: {
    valueSelect: YSelect;
    searchInput: ElInput;
  }

  @Model('change') readonly model!: any
  @Prop({default: '检索'}) readonly title: string
  @Prop({default: '搜索'}) readonly btnTitle: string
  @Prop({default: false, type: Boolean}) readonly showBtn: boolean
  @Prop() selectOptions: TableSelect
  // 单独使用Ysearch时，不使用selectOptions时可通过这个传递下拉参数
  @Prop() options: any[]

  DataTypeStrEnum: typeof DataTypeStrEnum = DataTypeStrEnum
  CommonDateConst: typeof YCommonDateConst = YCommonDateConst
  dataValue = []
  showFlag = true
  searchBoxBorder = '1px solid #e6e6e6'

  update() {
    this.inputAutoFocus()
  }

  mounted() {
    this.inputAutoFocus()
    this.inputPlaceholder = this.selectOptions && this.selectOptions['defaultPlaceholder'] ? this.selectOptions['defaultPlaceholder'] : ''
  }

  inputAutoFocus() {
    this.$nextTick(() => {
      if (this.$refs.searchInput) {
        this.$refs.searchInput.focus()
      }
    })
  }

  get singleTitle() {
    if (this.selectOptions && this.selectOptions.optionList.length) {
      return this.selectOptions.optionList[0].label
    }
    return this.title
  }

  get isSingle() {
    if (this.selectOptions && this.selectOptions.optionList.length) {
      return false
    }
    return true
  }

  selectValue = ''
  inputPlaceholder = ''

  @Watch('selectOptions', {immediate: true})
  selectOptionsWatch() {
    this.resetSelectValueToDefault()
  }

  resetSelectValueToDefault() {
    if (this.selectOptions && this.selectOptions.defaultValue) {
      this.selectValue = this.selectOptions.defaultValue
    }
  }

  //为了用户输入以后，可以当做值使用
  blurSetValue(v) {
    let value: any = this.$refs.valueSelect.blurValue
    if (value) {
      if (this.selectOption && this.selectOption.selectArr) {
        const select = this.selectOption.selectArr.find(item => (item.value === value || item.label === value))
        if (select) {
          value = select.value
        }
      }
      this.change(value)
    }
  }

  changeHandler(value) {
    this.change(value)
    // 不为空和null才执行查询
    if (!['', null].includes(value)) {
      this.$nextTick(() => this.click())
    }
  }

  @Emit('update:modelValue')
  change(value: any) {
    return value
  }

  @Emit()
  click() {
    let valueName = this.model
    let value = this.model
    if (this.selectOption && this.selectOption.selectArr) {
      if (Array.isArray(value)) {
        const valueNameAry = []
        const valueAry = []
        for (const valueElement of value) {
          const select = this.selectOption.selectArr.find(item => (item.value === valueElement || item.label === valueElement))
          if (select) {
            valueNameAry.push(select.label)
            valueAry.push(select.value)
          } else {
            valueNameAry.push(valueElement)
            valueAry.push(valueElement)
          }
        }
        valueName = valueNameAry
        value = valueAry
      } else {
        const select = this.selectOption.selectArr.find(item => (item.value === this.model || item.label === this.model))
        if (select) {
          valueName = select.label
          value = select.value
        }
      }
    }
    const obj = {
      label: this.selectOption && this.selectOption.value,
      labelName: this.selectOption && this.selectOption.label,
      valueName: valueName,
      value: value
    }
    if (this.selectOption) {
      this.selectOption.curValue = value
    }
    return obj
  }

  get selectWidth() {
    if (this.selectOption) {
      return this.selectOption.label.length * 15 + 35 + 'px'
    }
    return '100px'
  }

  get selectOption() {
    if (this.selectValue) {
      return this.selectOptions.optionList.find(item => item.value === this.selectValue)
    }
    return null
  }

  isChineseChar(str) {
    const reg = /^[\u4E00-\u9FA5\uF900-\uFA2D]$/;
    for (let i = 0; i < str.length; i++) {
      const char = str.charAt(i)
      if (!reg.test(char)) {
        return false;
      }
    }
    return true;
  }

  selectOptionClick(val) {
    this.showFlag = false
    const fuzzySearchLabel = ['智能搜索', '智能匹配', '模糊匹配', '模糊搜索', '自动匹配']
    const curOption = this.selectOptions.optionList.find(item => item.value === val)
    if (fuzzySearchLabel.includes(curOption.label)) {
      this.inputPlaceholder = this.selectOptions['defaultPlaceholder']
    } else {
      this.inputPlaceholder = '请输入' + curOption.label
    }

    let initValue: any = ''
    if (curOption.type === DataTypeStrEnum.Date) {
      initValue = []
    }
    if (!['', null].includes(curOption.curValue)) {
      initValue = curOption.curValue
    }
    if (curOption.type === DataTypeStrEnum.Date) {
      this.dataValue = initValue
    }
    this.change(initValue)
    this.$nextTick(() => {
      this.showFlag = true
      if (curOption) {
        if (curOption.selectArr && curOption.selectArr.length) {
          this.$nextTick(() => {
            // @ts-ignore
            this.$refs.valueSelect.$children[0].focus()
          })
        }
      }
    })
  }

  get selectOptionSelectArr() {
    if (this.selectOption && this.selectOption.selectArr) {
      return this.selectOption.selectArr
    }
    return this.options
  }

  // 设置搜索框选中状态
  inputFocus() {
    this.searchBoxBorder = '1px solid #409eff'
  }

  inputBlur() {
    this.searchBoxBorder = '1px solid #e6e6e6'
  }
}
</script>
