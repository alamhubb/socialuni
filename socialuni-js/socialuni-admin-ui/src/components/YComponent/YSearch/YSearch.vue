<template>
  <div class="bd bd-radius row-col-center h30 position-relative">
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
          class="flex-none color-main row-col-center pl-sm pr-sm h100p">{{ singleTitle }}
      </div>
    </slot>
    <y-select
        ref="valueSelect"
        v-if="selectOptionSelectArr"
        size="mini"
        :model="model"
        :options="selectOptionSelectArr"
        class="flex-1 bd-none"
        :class="{'hide-suffix':!isSingle}"
        v-bind="$attrs"
        @change="change"
    />
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
        @change="change"
    />
    <el-input
        v-else
        ref="searchInput"
        class="flex-1 bd-none"
        size="mini"
        v-bind="$attrs"
        :value="model"
        clearable
        @keyup.enter.native="click"
        @input="change">
    </el-input>
    <div
        v-if="showBtn"
        class="flex-none h100p row-col-center bl px bg-click color-sub font-14"
        style="background: #F5F7FA;  border-left: 1px solid #dedede; border-radius: 0px 5px 5px 0px;"
        @click="click">{{ btnTitle }}
    </div>
  </div>
</template>

<script lang="ts">
import { Component, Emit, Model, Prop, Vue, Watch } from 'vue-property-decorator'
import YSelect from '@/components/YComponent/YSelect/YSelect.vue'
import TableSelect from '@/components/YComponent/TableGroup/TableSelect'
import DataTypeStrEnum from '@/components/YComponent/constant/DataTypeStrEnum'
import { ElInput } from 'element-ui/types/input'
import YCommonDateConst from '../constant/YCommonDateConst'

/**
 * @author 秦开远
 * @date 2021/1/21 15:25
 *
 * 在数据源业务基础上，封装基础table
 */
@Component({
  components: { YSelect }
})
export default class YSearch extends Vue {
  $refs: {
    valueSelect: YSelect;
    searchInput: ElInput;
  }

  @Model('change') readonly model!: any
  @Prop({ default: '检索' }) readonly title: string
  @Prop({ default: '搜索' }) readonly btnTitle: string
  @Prop({ default: false, type: Boolean }) readonly showBtn: boolean
  @Prop() selectOptions: TableSelect
  // 单独使用Ysearch时，不使用selectOptions时可通过这个传递下拉参数
  @Prop() options: any[]

  DataTypeStrEnum: typeof DataTypeStrEnum = DataTypeStrEnum
  CommonDateConst: typeof YCommonDateConst = YCommonDateConst
  dataValue = []

  update() {
    this.inputAutoFocus()
  }

  mounted() {
    this.inputAutoFocus()
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

  @Watch('selectOptions', { immediate: true })
  selectOptionsWatch() {
    this.resetSelectValueToDefault()
  }

  resetSelectValueToDefault() {
    if (this.selectOptions && this.selectOptions.defaultValue) {
      this.selectValue = this.selectOptions.defaultValue
    }
  }

  @Emit()
  change(value) {
    return value
  }

  @Emit()
  click() {
    let valueName
    if (this.selectOption && this.selectOption.selectArr) {
      const select = this.selectOption.selectArr.find(item => item.value === this.model)
      if (select) {
        valueName = select.label
      }
    } else {
      valueName = this.model
    }
    const obj = {
      label: this.selectOption && this.selectOption.value,
      labelName: this.selectOption && this.selectOption.label,
      valueName: valueName,
      value: this.model
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

  selectOptionClick(val) {
    const curOption = this.selectOptions.optionList.find(item => item.value === val)
    if (curOption.type === DataTypeStrEnum.Date) {
      this.change([])
      this.dataValue = []
    } else {
      this.change('')
    }
    if (curOption) {
      if (curOption.selectArr && curOption.selectArr.length) {
        this.$nextTick(() => {
          // @ts-ignore
          this.$refs.valueSelect.$children[0].focus()
        })
      }
    }
  }

  get selectOptionSelectArr() {
    if (this.selectOption && this.selectOption.selectArr) {
      return this.selectOption.selectArr
    }
    return this.options
  }
}
</script>
