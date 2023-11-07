<!--
  表格封装组件：
  1：表格头部title需要props传递，右侧按钮通过slot插槽传入（<slot name="table_tit-but-area"></slot>）
  2：普通搜索内容在组件内操作，搜索事件通过@emit传递（ordinarySearch）
  3：高级搜索部分,组件内只封装样式，逻辑部分在父组件完成，通过slot传入（<slot name="advanced-area"></slot>）
  4：高级搜索部分，搜索按钮、重置按钮事件在组件内操作，通过$emit出传递
  5：表格部分,组件内只封装样式，逻辑部分在父组件完成，通过slot传入（<slot class="table_area" name="table-area"></slot>）
  6：分页部分,组件内只封装样式，逻辑部分在父组件完成，通过slot传入（ <slot name="page-area"></slot>）

  注意点： ordinarySelectOptions
    普通搜索 select options数组，因为规定的实现逻辑、所以需要传入指定的数据格式：
    {
      defaultValue: 'name',
      defaultPlaceholder:'', // 模糊搜索的Placeholder （如果需要的话）
      optionList: [
      {
        value: "智能搜索", // 接口支持模糊匹配时，加入智能搜索项
        label: "智能搜索"
      },{
        value: 'name', // 搜索字段的值（接口需要此值）
        label: 'name', // 搜索字段的名称
      },{
        value: 'select', // 搜索字段的值（接口需要此值）
        label: 'select', // 搜索字段的名称
        selectArr: [{  // 此字段为，如果搜索字段不是简单的input，而是可选择的select时，需要该字段
          value: 'a',
          label: 'a'
        },{
          value: 'b',
          label: 'b'
        }]
      },{
        value: 'select2',
        label: 'select2',
        selectArr: []
      }]
    }

   注意点：
      高级搜索也封装到了插件里，需要传进来高级搜索的form值，格式如下：
      advancedSearchFrom:[
        {
          label:'property',     // form的属性值
          labelName:'名称',     // form 的label名称
          type:'input'         // form-item的类型
         },
        {
          label:'property',
          labelName:'ip',
          type:'select',
          multiple: true,  // select 是否支持多选
          options:[
            {
              label:'label1',
              value:'label1'
             },
             {
               label:'label2',
               value:'label2'
              }
            ]
          },
        {
          label:'cc',
          labelName:'日期',
          type: 'time'
        }
      ]
 -->
<template>
  <div
    id="table_layout_control"
    class="table_layout_control">
    <div
      id="handle_area"
      class="handle_area flex-none">
      <!-- 表格头部区域 -->
      <div
        class="mb px h50 row-between-center"
        :class="{ table_title_area_borderBottom: tableTitleBorderShow }"
        v-if="tableTitle || tableHeader"
      >
        <slot name="tableHeader">
          <div class="row-col-center">
            <slot name="title">
              <template v-if="tableTitle">
                <!--              <div class="y-piece mb-nn"></div>-->
                <span class="font-16 color-special font-bold ml-xs">{{ tableTitle }}</span>
              </template>
            </slot>
          </div>

          <!-- 表格头部右侧操作按钮区域，插槽 -->
          <div>
            <slot name="table_tit-but-area"></slot>
          </div>
        </slot>
      </div>

      <!-- 搜索区域 -->
      <div
        class="search_area"
        :class="{ paddingclass: paddingClass }"
        v-if="searchAreaShow">
        <slot name="tableSearch">
          <!-- 普通搜索 -->
          <div class="row-between" v-if="!onlyAdvancedSearch">
            <!--     搜索前的表格操作按钮       -->
            <div v-if="hasTableOperate" class="tableOperate_box">
              <slot name="tableOperate"></slot>
            </div>
            <div class="row-between" style="width: 100%">
              <div class="flex-row w50p  max-w700">
                <div class="flex-1 position-relative ordinarySearchBox">
                  <div class="mask_div" v-show="advancedShow"></div>
                  <!-- 自定义下拉组件 -->
                  <y-search
                    ref="searchInput"
                    v-model="ordinarySearchInput"
                    :select-options="ordinarySelectOptions"
                    class="mb-sm flex-1"
                    show-btn
                    label="label"
                    @click="ordinarySearch">
                    <template #searchPre>
                      <slot name="searchPre"></slot>
                    </template>
                  </y-search>
                </div>
                <div v-if="showSaveQueryData" class="ml-sm flex-none">
                  <el-popover
                    ref="queryHistoryPop"
                    placement="left"
                    width="250"
                    trigger="click">
                    <el-button slot="reference" size="mini" class="h30">
                      <i class="el-icon-time"></i>
                    </el-button>
                    <y-scrollbar class="h200">
                      <div class="pr-smm pb-xs">
                        <div v-if="advanceQueryDataList && advanceQueryDataList.length">
                          <div v-for="item in advanceQueryDataList" class="row-between-center px-sm bb py-xs"
                               @click="hideQueryHistoryPop">
                            <div class="flex-1 use-click" @click="chooseCurHistoryQueryData(item)">{{ item.name }}</div>
                            <div class="flex-none ml use-click" @click="deleteQueryHistoryData(item)">
                              <i class="el-icon-delete"></i>
                            </div>
                          </div>
                        </div>
                        <div v-else class="row-all-center h180">
                          <div class="color-sub font-12">暂无数据</div>
                        </div>
                      </div>
                    </y-scrollbar>
                  </el-popover>
                </div>
              </div>
              <!-- 高级搜索按钮 -->
              <div class="flex-none row-col-start mt-xs">
              <span
                class="color-blue bg-click"
                @click="advancedSwitch"
                v-if="advancedButShow && !onlyAdvancedSearch"
              >
<!--                  {{ advancedShow ? '普通搜索' : '高级搜索' }}-->
                高级搜索
              </span>
                <!-- 导出按钮，更多按钮插槽区域（一般不会有） -->
                <slot name="gengduo"></slot>
              </div>
            </div>
          </div>
          <!-- 高级搜索区域 -->
          <div
            class="advanced_search_box"
            v-if="advancedShow || onlyAdvancedSearch">
            <!-- 高级搜索插槽区域 -->
            <slot name="advanced-area"></slot>
            <el-form v-model="form">
              <el-form-item v-for="(item,index) in advancedSearchFrom" :key="index" :label="item.labelName">
                <el-input v-if="item.type==='input'" v-model="form[item.label]"
                          :placeholder="'请输入'+item.labelName"></el-input>
                <el-select v-if="item.type==='select'" :multiple="item.multiple" v-model="form[item.label]">
                  <el-option v-for="opt in item.options" :key="opt.value" :label="opt.label"
                             :value="opt.value"></el-option>
                </el-select>
                <el-date-picker
                  v-if="item.type==='time'"
                  v-model="form[item.label]"
                  type="datetimerange"
                  :format="item.format?item.format:'yyyy-MM-dd HH:mm:ss'"
                  :value-format="item.valueFormat?item.valueFormat:'yyyy-MM-dd HH:mm:ss'"
                  range-separator="至"
                  start-placeholder="开始日期"
                  end-placeholder="结束日期">
                </el-date-picker>
              </el-form-item>
            </el-form>
            <div class="submit_btu_box">
              <el-button @click="onSubmit" style="background:#C50F1A;color:white">搜 索</el-button>
              <el-button @click="advancedResetForm">重 置</el-button>
              <el-button v-if="advancedButShow && !onlyAdvancedSearch" type="text" @click="advancedSwitch">
                收起高级搜索
              </el-button>
            </div>
          </div>
          <div
            v-if="!hideTags"
            class="mt-sm font-12 color-content row-col-center"
            v-show="searchTags.length > 0">
            <i class="el-icon-s-operation"></i>
            <div class="ml-xs">搜索项：</div>
            <el-tag
              v-for="(tag, index) in searchTags"
              :key="index"
              closable
              @close="tagClose(tag)"
              size="small"
              type="info"
              class="ml-xs"
            >
                  <span class="color-content">
                    {{ tag.labelName }}{{ tag.labelName ? '：' : '' }}{{ tag.valueName }}
                  </span>
            </el-tag>
            <div class="ml cursor-pointer" @click="clearTags">清空</div>

            <div v-if="showSaveQueryData" class="h24 row-col-center">
              <el-popover
                ref="saveRef"
                placement="top"
                width="400"
                class="display-block h24 row-col-center"
                trigger="click">
                <div slot="reference" class="cursor-pointer ml" @click="clearSaveInfo">保存</div>
                <div class="flex-row pd-xs pb-df">
                  <div class="flex-1">
                    <el-input ref="savePopInput" v-model="saveAdvanceSearchName"
                              :placeholder="curChooseHistoryQueryData?'将快捷搜索另存为':'为快捷搜索命名'"></el-input>
                    <div class="px-xs mt-sm font-12 color-content">
                      {{
                        curChooseHistoryQueryData ? '另存为当前快捷搜索的筛选条件' : '给当前的筛选条件进行命名并保存，方便在今后进行快速过滤相关资源'
                      }}
                    </div>
                  </div>
                  <div class="flex-none">
                    <el-button type="primary" plain class="ml-sm" :disabled="!saveAdvanceSearchName"
                               @click="saveSearchValues">{{ curChooseHistoryQueryData ? '另存为' : '保存' }}
                    </el-button>
                  </div>
                </div>
              </el-popover>
            </div>
          </div>
        </slot>
      </div>
    </div>

    <!-- 自定义搜索区域 -->
    <div
      class="zdy_search_area"
      v-if="zdySearchShow">
      <slot name="ziDingYi-search-area"></slot>
    </div>

    <!-- 表格区域 -->
    <div
      id="table_area"
      class="table_area flex-col flex-1 overflow-hidden"
      :class="{
        paddingclass: paddingClass,
        no_marginTop: !tableTitle && !searchAreaShow
      }"
    >
      <slot
        class="table_area"
        name="table-area"></slot>
    </div>
    <!-- 分页区域 -->
    <div
      id="page_area"
      class="page_area flex-none"
      :class="{ paddingclass: paddingClass }"
      v-if="pageAreaShowReal"
    >
      <slot name="page-area"></slot>
    </div>
    <slot></slot>
  </div>
</template>

<script>
import YSearch from '../YSearch/YSearch'
import AdvanceSearchAPI from '@/components/YComponent/tableBox/api/AdvanceSearchAPI'
import JsonUtil from '@/components/YComponent/utils/JsonUtil'
import ToastUtil from '@/components/YComponent/utils/ToastUtil'
import AlertUtil from '@/components/YComponent/utils/AlertUtil'

export default {
  name: 'TableBox',
  data() {
    return {
      ordinarySearchInput: '', // 普通搜索input值
      searchAutocompleteHeight: 'auto', // 自定义下拉框的高度
      searchTags: [], // 检索项 标签数组
      advancedShow: false, // 高级搜索内容部分是否展示，
      // 高级搜索按钮是否展示
      advancedButShow: false,
      tableHeader: false,
      pageAreaShowReal: false,
      form: {},
      value1: '',
      saveAdvanceSearchName: null,
      saveAdvanceSearchValues: [],
      advanceQueryDataList: [],
      curChooseHistoryQueryData: null
    }
  },
  components: { YSearch },
  props: {
    // 是否展示保存搜索条件功能
    showSaveQueryData: {
      type: Boolean,
      default: false
    },
    // title部分是否显示底部边框和margin值
    tableTitleBorderShow: {
      type: Boolean,
      default: true
    },
    // 搜索、表格、分页部分是否有padding值
    paddingClass: {
      type: Boolean,
      default: true
    },

    // 表格title部分是否展示
    tableTitleShow: {
      type: Boolean,
      default: false
    },

    // 表格title值
    tableTitle: {
      type: String,
      default: ''
    },

    // 是否有搜索区域
    searchAreaShow: {
      type: Boolean,
      default: true
    },

    // 普通搜索 select options数组，
    ordinarySelectOptions: {
      type: Object,
      default: function() {
        return {}
      }
    },

    // 高级搜索 form数据，
    advancedSearchFrom: {
      type: Array,
      default: function() {
        return []
      }
    },

    //普通搜索前边是否有表格操作按钮（一般没有，console的几个列表有）
    hasTableOperate: {
      type: Boolean,
      default: false
    },

    // 自定义搜索部分是否存在
    zdySearchShow: {
      type: Boolean,
      default: false
    },

    // 是否有分页
    pageAreaShow: {
      type: Boolean,
      default: false
    },
    // 仅使用高级搜索
    onlyAdvancedSearch: {
      type: Boolean,
      default: false
    },
    // 不显示tag
    hideTags: {
      type: Boolean,
      default: false
    },
    // 不显示tag
    showAdvance: {
      type: Boolean,
      default: false
    }
  },
  watch: {
    ordinarySelectOptions: {
      handler: function() {
        //主要解决初始的时候的默认tags
        if (this.ordinarySelectOptions.searchTags && this.ordinarySelectOptions.searchTags.length) {
          //存在重设curValue导致值消失的情况
          if (!this.searchTags.length) {
            this.searchTags = [...this.ordinarySelectOptions.searchTags]
          }
        }
      },
      deep: true,
      immediate: true
    }
  },
  created() {
    if (this.pageAreaShow) {
      this.pageAreaShowReal = this.pageAreaShow
    }
    this.curChooseHistoryQueryData = null
    if (this.showSaveQueryData) {
      this.querySearchHistory()
    }
  },
  mounted() {
    if (this.$slots['advanced-area']) {
      this.advancedButShow = true
    }
    if (this.advancedSearchFrom.length > 0) {
      this.advancedButShow = true
    }
    if (this.showAdvance) {
      this.advancedButShow = true
    }
    if (this.$slots['tableHeader']) {
      this.tableHeader = true
    }
    if (this.$slots['page-area']) {
      this.pageAreaShowReal = true
    }
    this.advancedSearchFrom.forEach(item => {
      this.$set(this.form, item.label, '')
    })
  },
  methods: {
    async querySearchHistory() {
      const res = await AdvanceSearchAPI.queryConditions()
      this.advanceQueryDataList = res.data
    },
    hideQueryHistoryPop() {
      this.$refs.queryHistoryPop.handleBlur()
    },
    deleteQueryHistoryData(curQueryData) {
      AlertUtil.confirm(`是否确认删除历史搜索条件 ${curQueryData.name}`).then(() => {
        AdvanceSearchAPI.deleteConditions(curQueryData.index).then(() => {
          ToastUtil.success(`搜索条件 ${curQueryData.name} 删除成功`)
          if (this.curChooseHistoryQueryData && (this.curChooseHistoryQueryData.index === curQueryData.index)) {
            this.curChooseHistoryQueryData = null
          }
          this.querySearchHistory()
        })
      }).catch(() => {
        ToastUtil.info(`您选择了不删除`)
      })
    },
    chooseCurHistoryQueryData(curQueryData) {
      this.curChooseHistoryQueryData = curQueryData
      const option = JsonUtil.toParse(this.curChooseHistoryQueryData.option)
      this.searchTags = []
      for (const optionKey in option) {
        if (this.advancedShow) {
          this.$set(this.form, optionKey, option[optionKey].value)
        }
        this.searchTags.push({
          label: option[optionKey].label,
          labelName: option[optionKey].labelName,
          value: option[optionKey].value,
          valueName: option[optionKey].valueName
        })
      }
      this.$emit('tableOrdinarySearch', this.searchTags)
    },
    clearSaveInfo() {
      this.saveAdvanceSearchName = null
      this.saveAdvanceSearchValues = []
      setTimeout(() => {
        this.$nextTick(() => {
          if (this.$refs.savePopInput) {
            console.log(this.$refs.savePopInput)
            this.$refs.savePopInput.focus()
          }
        })
      }, 10)
    },
    saveSearchValues() {
      this.$refs.saveRef.handleBlur()
      const queryObj = {}
      for (const searchTag of this.searchTags) {
        queryObj[searchTag.label] = searchTag
      }
      AdvanceSearchAPI.addQueryConditionQO(this.saveAdvanceSearchName, JsonUtil.toJson(queryObj)).then(res => {
        if (this.curChooseHistoryQueryData) {
          ToastUtil.success(`快捷搜索另存为 ${this.saveAdvanceSearchName} 成功`)
          this.querySearchHistory()
        } else {
          ToastUtil.success(`快捷搜索 ${this.saveAdvanceSearchName} 保存成功`)
          this.querySearchHistory()
        }
      })
    },
    // 标签删除事件
    tagClose(val) {
      this.searchTags.forEach((item, index) => {
        if (item.label === val.label) {
          this.searchTags.splice(index, 1)
          if (this.$refs.searchInput.selectValue === val.label) {
            this.ordinarySearchInput = null
          }
        }
      })
      for (let pro in this.form) {
        if (pro === val.label) {
          this.form[pro] = ''
        }
      }
      this.$emit('tableOrdinarySearch', this.searchTags)
    },
    // 清除tag
    clearTags() {
      this.curChooseHistoryQueryData = null
      this.ordinarySearchInput = null
      this.form = {}
      this.searchTags = []
      this.$emit('tableOrdinarySearch', this.searchTags)
    },
    // 点击普通搜索时，向父组件传递事件
    ordinarySearch(obj) {
      console.log(obj)
      this.searchTags.forEach((item, index) => {
        if (item.label === obj.label) {
          this.searchTags.splice(index, 1)
        }
      })
      if (obj.value || obj.value === 0) {
        if (Array.isArray(obj.value)) {
          if (obj.value.length) {
            this.searchTags.push(obj)
          }
        } else {
          this.searchTags.push(obj)
        }
      }
      // 有值才添加
      this.$emit('tableOrdinarySearch', this.searchTags)
    },
    // 点击高级搜索按钮事件
    advancedSwitch() {
      this.advancedShow = !this.advancedShow
      // if (!this.advancedShow) {
      //   this.advancedResetForm()
      // }
      this.$emit('advancedSwitch', this.advancedShow)
    },

    // 点击高级搜索（搜索按钮）时，向父组件传递事件
    onSubmit() {
      const formArr = Object.keys(this.form)
      if (formArr.length) {
        console.log(formArr)
        // 遍历form的值 去除空值
        formArr.forEach(arr => {
          const type = Object.prototype.toString.call(this.form[arr])
          let hasValue = false
          if (type === '[object Array]') {
            this.form[arr].length < 1 ? hasValue = false : hasValue = true
          } else if (type === '[object Object]') {
            const keys = Object.keys(this.form[arr])
            keys.length < 1 ? hasValue = false : hasValue = true
          } else {
            hasValue = this.form[arr] !== ''
          }
          if (hasValue) {
            // 创建searchTag所需对象
            this.advancedSearchFrom.forEach(advanced => {
              if (advanced.label === arr) {
                const obj = {
                  label: arr,
                  labelName: advanced.labelName,
                  value: this.form[arr],
                  valueName: this.form[arr]
                }
                if (advanced.type === 'select') {
                  if (advanced.multiple) {
                    const name = []
                    const value = []
                    this.form[arr].forEach(val => {
                      for (let opt of advanced.options) {
                        if (val === opt.value) {
                          name.push(opt.label)
                          value.push(opt.value)
                          break
                        }
                      }
                    })
                    obj.valueName = name
                    obj.value = value
                  } else {
                    for (let opt of advanced.options) {
                      if (opt.value === this.form[arr]) {
                        obj.valueName = opt.label
                      }
                    }
                  }
                }
                this.searchTags.forEach((item, index) => {
                  if (item.label === obj.label) {
                    this.searchTags.splice(index, 1)
                  }
                })
                this.searchTags.push(obj)
              }
            })
          } else {
            this.searchTags.forEach((item, index) => {
              if (item.label === arr) {
                this.searchTags.splice(index, 1)
              }
            })
          }
        })
        this.$emit('tableOrdinarySearch', this.searchTags)
      } else {
        this.$emit('tableAdvancedSearch')
      }
    },

    // 点击重置按钮时，向父组件传递事件
    advancedResetForm() {
      this.searchTags = []
      this.ordinarySearchInput = ''
      this.form = {}
      // this.$emit('tableAdvancedResetForm')
      this.$emit('tableOrdinarySearch', this.searchTags)
      this.$nextTick(() => {
        if (this.$refs.searchInput) {
          this.$refs.searchInput.resetSelectValueToDefault()
          this.$refs.searchInput.inputAutoFocus()
        }
      })
    },
    // 点击重置按钮时，向父组件传递事件, 仅清空内容，
    onlyResetFrom() {
      this.searchTags = []
      this.curChooseHistoryQueryData = null
      this.ordinarySearchInput = ''
      this.$refs.searchInput.resetSelectValueToDefault()
      this.$nextTick(() => {
        this.$refs.searchInput.inputAutoFocus()
      })
    }
  }
}
</script>

<style lang="scss">
.table_title_area_borderBottom {
  border-bottom: #e1e6eb 1px solid;
  height: 50px;
}

.max-w700 {
  max-width: 700px;
}

.table_layout_control {
  width: 100%;
  height: 100%;
  // 这部分放开可能会导致canvas层级问题
  overflow: auto;
  background: white;

  .paddingclass {
    padding: 0 20px;
  }

  .handle_area {
    width: 100%;
    background-color: #fff;

    .search_area {
      width: 100%;
      margin-bottom: 20px;

      .tableOperate_box {
        margin-right: 10px;

        .el-button {
          font-size: 14px;
          padding: 7.1px 15px;
          border-radius: 5px;
          border: 1px solid #e6e6e6;
        }
      }

      .el-input__inner:focus {
        outline: none;
        border-color: #409eff;
      }

      .ordinarySearchBox {
        .mask_div {
          position: absolute;
          width: 100%;
          height: 30px;
          background: #EEEEEE;
          background: url("./tableSearch.png");
          z-index: 100;
          opacity: 0.4;
          cursor: not-allowed;
        }

        .el-select.left-arrow .el-input__inner {
          padding-left: 10px;
          padding-right: 25px;
          background: rgb(245, 247, 250);
          border-radius: 5px 0 0 5px;
        }

        .el-select.left-arrow .el-input__suffix {
          right: 5px;
          left: unset;
        }
      }

      .el-input-group__prepend {
        //width: 65px;
        font-size: 14px;
        background: white;
        color: black;
      }

      .search_autocomplete {
        width: 500px;
        max-height: 215px;
        position: absolute;
        //el-table zindex=1 设置2不被遮挡
        z-index: 2;
        top: 40px;
        left: 120px;
        border: 1px solid #e4e7ed;
        border-radius: 4px;
        background-color: #fff;
        box-shadow: 0 2px 12px rgba(0, 0, 0, 0.1);
        box-sizing: border-box;
        margin: 5px 0;
        overflow: hidden;

        ul {
          list-style: none;
          padding: 6px 0;
          margin: 0;
          box-sizing: border-box;

          li {
            font-size: 14px;
            padding: 0 20px;
            position: relative;
            white-space: nowrap;
            overflow: hidden;
            text-overflow: ellipsis;
            color: #606266;
            height: 34px;
            line-height: 34px;
            box-sizing: border-box;
            cursor: pointer;
          }

          li:hover {
            background-color: #f5f7fa;
          }
        }

        .noData_P {
          text-align: center;
          font-size: 14px;
        }
      }

      .advanced_search_box {
        width: 100%;
        height: max-content;
        font-size: 0;
        margin-top: 0;

        .el-form {
          display: flex;
          flex-direction: row;
          flex-wrap: wrap;
          //justify-content: space-between;
        }

        .el-form-item {
          // height: 32px;
          // line-height: 32px;
          width: calc(25% - 10px);
          display: inline-block;
          vertical-align: top;
          margin-bottom: 10px;
          margin-right: 10px;

          .el-form-item__label {
            text-align: left;
            color: #666666;
            font-size: 14px;
            font-weight: 600;
            line-height: 30px;
          }

          .el-form-item__content {
            line-height: 32px;
            margin-left: 0 !important;
            padding-right: 0;

            .el-input__inner {
              height: 32px;
              line-height: 32px;
              width: 100%;
            }

            .el-date-editor {
              .el-range-input {
                width: 50%;
                font-size: 12px;
              }

              .el-range-separator {
                visibility: hidden;
                position: relative;

                &:after {
                  color: #666666;
                  font-size: 12px;
                  content: "一";
                  visibility: visible;
                  position: absolute;
                  left: 0;
                  top: 0;
                }
              }

              .el-icon-time {
                display: none;
              }

              .el-range__close-icon {
                display: none;
              }
            }

            .el-input__icon {
              line-height: 24px;
            }

            .el-date-editor.el-input,
            .el-date-editor.el-input__inner {
              width: 100%;
            }

            .el-select {
              width: 100%;
            }

            .line {
              text-align: center;
            }
          }
        }

        //.el-form-item:nth-child(4){
        //  margin-right: 0;
        //}

        .submit_btu_box {
          margin-top: 10px;
          // padding-left: 150px;
        }
      }
    }
  }

  .handle_area_padding {
    padding: 20px;
  }

  .zdy_search_area {
    width: 100%;
    margin-bottom: 20px;
    padding: 0 20px;
  }

  .table_area {
    //margin: 20px 0;
    background-color: #fff;

    .el-table th {
      background: #f3f5fb !important;
      font-size: 14px;
      font-weight: bold;
      color: #444444;
      height: 48px;
    }

    .el-table tr {
      font-size: 14px;
      height: 48px;
    }

    .el-table tr td {
      padding: 11px 0;
    }

    .el-button--mini {
      padding: 5px 10px;
      border-radius: 2px;
      margin-right: 5px;
    }
  }

  .no_marginTop {
    margin-top: 0;
  }

  /*.el-button {
    padding: 9px 15px;
    font-size: 12px;
  }*/

  .page_area {
    padding-bottom: 20px;
    background-color: #fff;
    text-align: right;
    margin-top: 20px;
  }

  .searchBntDiv {
    border-top-right-radius: 4px;
    border-bottom-right-radius: 4px;
  }
}
</style>
