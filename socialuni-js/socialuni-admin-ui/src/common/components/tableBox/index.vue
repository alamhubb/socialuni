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
      optionList: [{
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
 -->
<template>
  <div id="table_layout_control" class="table_layout_control">
    <div id="handle_area" class="handle_area clearfix">
      <!-- 表格头部区域 -->
      <div
        class="mb px h50 row-between-center"
        :class="{ table_title_area_borderBottom: tableTitleBorderShow }"
        v-if="tableTitle"
      >
        <div class="row-col-center">
          <template v-if="tableTitle">
            <div class="y-piece mb-nn"></div>
            <span class="font-lg color-special font-bold ml-xs">{{ tableTitle }}</span>
          </template>
        </div>

        <!-- 表格头部右侧操作按钮区域，插槽 -->
        <div>
          <slot name="table_tit-but-area"></slot>
        </div>
      </div>

      <!-- 搜索区域 -->
      <div
        class="search_area"
        :class="{ paddingclass: paddingClass }"
        v-if="searchAreaShow"
      >
        <!-- 普通搜索 -->
        <div class="row-between mb">
          <div class="flex-1">
            <div class="w70p max-w700 position-relative" v-if="!advancedShow && !onlyAdvancedSearch">
              <y-search
                v-model="ordinarySearchInput"
                :select-options="ordinarySelectOptions"
                class="mb-sm"
                show-btn
                label="label"
                @click="ordinarySearch"></y-search>
              <!-- 自定义下拉组件 -->
              <div v-if="!hideTags" class="searchTag_box" v-show="searchTags.length > 0">
                <i class="el-icon-s-operation"></i>
                <span>搜索项：</span>
                <el-tag
                  v-for="(tag, index) in searchTags"
                  :key="index"
                  closable
                  @close="tagClose(tag)"
                  size="small"
                  type="info"
                >
                  {{ tag.labelName }}：{{ tag.valueName }}
                </el-tag>
                <span
                  style="margin-left: 10px;cursor: pointer;"
                  @click="clearTags"
                >清除</span
                >
              </div>
            </div>
            <!-- 高级搜索区域 -->
            <transition name="fade-transform" mode="out-in">
              <div class="advanced_search_box" v-if="advancedShow || onlyAdvancedSearch">
                <!-- 高级搜索插槽区域 -->
                <slot name="advanced-area"></slot>
                <div class="submit_btu_box">
                  <el-button
                    @click="onSubmit"
                    style="background:#C50F1A;color:white"
                  >搜 索
                  </el-button
                  >
                  <el-button @click="advancedResetForm">重 置</el-button>
                </div>
              </div>
            </transition>
          </div>

          <!-- 高级搜索按钮 -->
          <div class="flex-none">
            <span
              class="color-blue bg-click"
              @click="advancedSwitch"
              v-if="advancedButShow && !onlyAdvancedSearch"
            >
              {{ advancedShow ? '普通搜索' : '高级搜索' }}
            </span>
            <!-- 导出按钮，更多按钮插槽区域（一般不会有） -->
            <slot name="gengduo"></slot>
          </div>
        </div>

      </div>
    </div>

    <!-- 自定义搜索区域 -->
    <div class="zdy_search_area" v-if="zdySearchShow">
      <slot name="ziDingYi-search-area"></slot>
    </div>

    <!-- 表格区域 -->
    <div
      id="table_area"
      class="table_area"
      :class="{
        paddingclass: paddingClass,
        no_marginTop: !tableTitle && !searchAreaShow
      }"
    >
      <slot class="table_area" name="table-area"></slot>
    </div>
    <!-- 分页区域 -->
    <div
      id="page_area"
      class="page_area"
      :class="{ paddingclass: paddingClass }"
      v-if="pageAreaShowReal"
    >
      <slot name="page-area"></slot>
    </div>
    <slot></slot>
  </div>
</template>

<script>
import '../ele.scss'
import YSearch from '../../../components/YComponent/YSearch/YSearch'

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
      pageAreaShowReal: false
    }
  },
  components: { YSearch },
  props: {
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
    }
  },
  watch: {
    ordinarySelectOptions: {
      handler: function() {
        if (this.ordinarySelectOptions.searchTags) {
          this.searchTags = [...this.ordinarySelectOptions.searchTags]
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
  },
  mounted() {
    if (this.$slots['advanced-area']) {
      this.advancedButShow = true
    }
    if (this.$slots['page-area']) {
      this.pageAreaShowReal = true
    }
  },
  methods: {
    // 标签删除事件
    tagClose(val) {
      this.searchTags.forEach((item, index) => {
        if (item.value === val.value) {
          this.searchTags.splice(index, 1)
        }
      })
      this.$emit('tableOrdinarySearch', this.searchTags)
    },
    // 清除tag
    clearTags() {
      this.searchTags = []
      this.$emit('tableOrdinarySearch', this.searchTags)
    },

    // 点击普通搜索时，向父组件传递事件
    ordinarySearch(obj) {
      this.searchTags.forEach((item, index) => {
        if (item.label === obj.label) {
          this.searchTags.splice(index, 1)
        }
      })
      if (obj.value || obj.value === 0) {
        this.searchTags.push(obj)
      }
      // 有值才添加
      this.$emit('tableOrdinarySearch', this.searchTags)
    },
    // 点击高级搜索按钮事件
    advancedSwitch() {
      if (this.advancedShow) {
        this.advancedResetForm()
      }
      this.advancedShow = !this.advancedShow
      this.$emit('advancedSwitch', this.advancedShow)
    },

    // 点击高级搜索（搜索按钮）时，向父组件传递事件
    onSubmit() {
      this.$emit('tableAdvancedSearch')
    },

    // 点击重置按钮时，向父组件传递事件
    advancedResetForm() {
      this.searchTags = []
      this.ordinarySearchInput = ''
      this.$emit('tableAdvancedResetForm')
    }
  }
}
</script>

<style lang="scss" scope>
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
  //overflow: auto; 会导致canvas层级问题
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

      .searchTag_box {
        margin-top: 10px;
        font-size: 12px;
        color: #666666;

        span {
          margin-left: 5px;
        }
      }

      .advanced_search_box {
        width: 100%;
        height: max-content;
        font-size: 0;

        .el-form-item {
          // height: 32px;
          // line-height: 32px;
          width: 33%;
          display: inline-block;
          vertical-align: top;
          margin-bottom: 10px;

          .el-form-item__label {
            text-align: left;
            color: #666666;
            font-size: 14px;
            font-weight: 400;
            line-height: 32px;
          }

          .el-form-item__content {
            padding-right: 15px;
            line-height: 32px;

            .el-input__inner {
              height: 32px;
              line-height: 32px;
              width: 100%;
            }

            .el-date-editor .el-range-separator {
              line-height: 24px;
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

        .submit_btu_box {
          margin-top: 20px;
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
    margin-top: 0px;
  }

  .el-button {
    padding: 9px 15px;
    font-size: 12px;
  }

  .page_area {
    padding-bottom: 20px;
    background-color: #fff;
    text-align: right;
    margin-top: 20px;
  }
}
</style>
