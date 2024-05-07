<template>
  <el-tree
      class="yoyo-tree"
      ref="tree"
      :data="data"
      v-bind="$attrs"
  >
    <div
        class="w100p h38 row-col-center"
        slot-scope="{ node, data }">
      <div
          class="h28 flex-row w100p bd-radius px-sm"
          :class="data.active?'bg-grey14 color-special':'color-content'">
        <div
            class="row-col-center flex-none h100p">
          <div v-if="(data.children && data.children.length)||data.childs" @click="nodeExpand(data)">
            <i
                v-if="data.isExpand"
                class="el-icon-arrow-up"></i>
            <i
                v-else
                class="el-icon-arrow-down"></i>
          </div>
          <div
              v-else
              class="w14"></div>
        </div>
        <!--        是否点击的时候展开-->
        <div
            v-if="expandClick || (!data.active&&!data.isExpand)"
            @click="nodeClick(data,node)"
            class="row-between-center flex-1 h100p pl-xs overflow-hidden">
          <slot
              name="default"
              :node="node"
              :data="data">
            <div>
              {{ node.label }}
            </div>
            <!--            <div>
                          {{ data.num }}
                        </div>-->
          </slot>
        </div>
        <div
            v-else
            @click.stop="nodeClick(data,node)"
            class="row-between-center flex-1 h100p pl-xs overflow-hidden">
          <slot
              name="default"
              :node="node"
              :data="data">
            <div>
              {{ node.label }}
            </div>
            <!--            <div>
                          {{ data.num }}
                        </div>-->
          </slot>
        </div>
      </div>
    </div>
  </el-tree>
</template>

<script lang="ts">
import { Component, Prop, Vue } from 'vue-facing-decorator'
import TreeUtil from '@/components/YComponent/YTree/TreeUtil'
import type {ElTree} from "element-ui/types/tree";

/**
 * @author 秦开远
 * @date 2021/1/21 15:25
 *
 * 在数据源业务基础上，封装基础table
 */
@toNative
@Component
export default class YTree extends Vue {
  $refs: {
    tree: ElTree<any, any>
  }

  @Prop() data
  @Prop({ default: false, type: Boolean }) expandClick: boolean

  nodeExpand(rowData) {
    this.$set(rowData, 'isExpand', !rowData.isExpand)
  }

  nodeClick(rowData, node) {
    if (this.expandClick || (!rowData.active && !rowData.isExpand)) {
      this.$set(rowData, 'isExpand', !rowData.isExpand)
    }
    TreeUtil.recurseSetActiveFalse(this.data, this)
    this.$set(rowData, 'active', true)
    this.$emit('node-click', rowData, node)
  }

  filter(value) {
    this.$refs.tree.filter(value)
  }
}
</script>
