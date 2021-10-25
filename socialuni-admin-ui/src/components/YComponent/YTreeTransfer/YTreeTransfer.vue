<template>
  <div v-if="yoyoNode" class="h100p flex-row">
        <div class="w300 pd mr flex-col overflow-hidden">
          <!--        隐藏展示收起 icon-->
          <!-- 搜索框-->
          <el-input
            v-model="inputTreeFilterText"
            size="small"
            class="flex-none"
            placeholder="输入关键字进行过滤"
            prefix-icon="el-icon-search"/>
          <el-tree
            ref="leftTree"
            class="flex-1 overflow-auto mt"
            :data="leftTree"
            default-expand-all
            :filter-node-method="filterNode"
            @node-click="nodeClick"
          >
            <template #default="{data}">
              <div class="my-sm w100p">
                <div :class="[data.checked?'color-blue':'']">
                  {{ data.label }}
                  <i v-show="data.checked" class="el-icon-check"></i>
                </div>
              </div>
            </template>
          </el-tree>
        </div>
        <div class="row-all-center w200">
          <div class="flex-row">
            <el-button icon="el-icon-arrow-left" @click="rightToLeft"></el-button>
            <el-button icon="el-icon-arrow-right" @click="leftToRight"></el-button>
          </div>
        </div>
        <div class="w300 pd mr flex-col">
          <!--        隐藏展示收起 icon-->
          <!-- 搜索框-->
          <el-input
            v-model="inputTreeFilterText"
            size="small"
            placeholder="输入关键字进行过滤"
            prefix-icon="el-icon-search"/>
          <el-tree
            ref="leftTree"
            class="flex-1 overflow-auto mt"
            :data="rightTree"
            default-expand-all
            :filter-node-method="filterNode"
            @node-click="nodeClick"
          >
            <template #default="{data}">
              <div class="my-sm w100p">
                <div :class="[data.checked?'color-blue':'']">
                  {{ data.label }}
                  <i v-show="data.checked" class="el-icon-check"></i>
                </div>
              </div>
            </template>
          </el-tree>
        </div>

<!--    <el-tree-transfer
      ref="treeTransfer"
      :title="['待选', '已选']"
      :from_data="leftTree"
      :to_data="rightTree"
      mode="transfer"
      height="400px"
      filter
      open-all
      defaultTransfer
    />-->
  </div>
</template>

<script lang="ts">
import { Component, Prop, Vue, Watch } from 'vue-property-decorator'
import '@antv/x6-vue-shape'

import 'codemirror/lib/codemirror.css'
import ConnectionVO from '@/model/GraphXml/ConnectionVO'
import YoyoNode from '@/model/graph/node/YoyoNode'
import DataSourceAPI from '@/api/designer/DataSourceAPI'
import YoyoColumn from '@/model/graph/YoyoColumn'
import YTable from '@/components/YComponent/YTable/YTable.vue'
import YTableColumn from '@/components/YComponent/YTableColumn/YTableColumn.vue'
import YSelect from '@/components/YComponent/YSelect/YSelect.vue'
import DataTable from '@/components/dataTable/DataTable.vue'
import TransType from '@/constants/TransType'
import NodeType from '@/constants/NodeType'
import SqlUtil from '@/utils/SqlUtil'
import NodeInfo from '@/model/GraphXml/NodeInfo'
import { NodeOperateComponent } from '@/model/convertNode/NodeOperateComponent'
import DbTableVO from '@/model/mapping/DbTableVO'
import TreeVO from '@/model/common/TreeVO'
import data from './datatest'
import { ElTree } from 'element-ui/types/tree'
import Arrays from '@/utils/Arrays'
import JsonUtil from '@/utils/JsonUtil'
import ElTreeTransfer from 'el-tree-transfer' // import more codemirror resource...

// import more codemirror resource...

@Component({
  components: { YTable, YTableColumn, YSelect, DataTable, ElTreeTransfer }
})
export default class YTreeTransfer extends Vue implements NodeOperateComponent {
  $refs: {
    connectionInfo: YSelect;
    table: DataTable;
    fieldInfo: DataTable;
    leftTree: ElTree<any, any>;
  }
  yoyoNode: YoyoNode<any, any> = null
  TransType = TransType
  NodeType = NodeType
  // 切tab和切换表和勾选列都不能 改变 列，应该关闭弹窗的时候校验名称一样，还要赋值呢
  connectionInfo: ConnectionVO = null
  nodeInfo: NodeInfo = null

  @Prop() readonly nodeType: number

  connections: ConnectionVO[] = []

  inputTreeFilterText = ''

  @Watch('inputTreeFilterText')
  inputFilterTextWatch() {
    this.$refs.leftTree.filter(this.inputTreeFilterText)
  }

  fieldInfoTableData: YoyoColumn<any> [] = []
  checkedFieldInfoTableData: YoyoColumn<any> [] = []

  connectionDataLoading = true

  curConnection: ConnectionVO = null

  get preSql() {
    if (this.fieldInfoTableData.length) {
      return SqlUtil.toSql(this.fieldInfoTableData, this.nodeInfo.tableName)
    } else {
      return ''
    }
  }

  // 初始弹窗页面内的数据
  async initData(yoyoNode: YoyoNode<any, any>) {
    this.yoyoNode = yoyoNode

    this.nodeInfo = this.yoyoNode.nodeInfo
    this.curConnection = null
    // 设置选择的列为
    this.checkedFieldInfoTableData = this.yoyoNode.columns

    // 可选择的字段列表
    // 数据预览列表
    // await this.queryConnections()
    this.queryTableData()
    // 选择的数据库连接。
    // 表的列表
    // 选择的表
  }

  initConnectionsData() {
    // 初始默认选择connectionId
    this.connections = []
  }

  async queryConnections() {
    this.initConnectionsData()
    // 查询数据库连接列表
    this.connections = await DataSourceAPI.queryDriversAPI(this.yoyoNode.nodeInfo.dsTypeId, this.yoyoNode.nodeInfo.driverClass)
  }

  initTableData() {
    // this.curRowTable = null
    this.tableData = []
    // this.tableDataLoading = true
    // this.initColumnsTableData()
  }

  async queryTables(data) {
    // data.e1 = data.ip + '-' + data.connectionName todo 需要与后台确认是否需要这个字段
    // return DataSourceAPI.queryDatabaseAPI({ ...data }).then(res => {
    //   return res.data
    // })
  }

  tableData: DbTableVO[] = []

  // 查询连接下的表数据
  async queryTableData() {
    /* let connection: ConnectionVO
    if (this.yoyoNode.connectionInfo.connectionName) {
      connection = this.connections.find(item => item.connectionName === this.yoyoNode.connectionInfo.connectionName)
    } else {
      connection = this.connections[0]
    }

    const formData = JsonUtil.deepClone(this.getFormData(connection))*/
    // for (const connection1 of this.connections) {
    // formData.e1 = formData.connectionName
    this.initTableData()

    // const dbListObj = await this.queryTables(formData)
    /* const dbListObj = await this.queryTables({
      'dsTypeId': '2',
      'driverClass': 'com.mysql.jdbc.Driver',
      'ip': '172.16.150.79',
      'port': '3306',
      'userName': 'root',
      'passWord': 'root',
      'dbName': 'source_db',
      'characterEncoding': 'UTF-8'
    }
    )*/

    const dbListObj = data

    for (const dbName in dbListObj) {
      const db = {
        pid: 0,
        id: dbName,
        label: dbName,
        children: []
      }

      const tables = dbListObj[dbName]
      for (const table of tables) {
        db.children.push({
          pid: dbName,
          id: table.tableName,
          label: table.tableName,
          data: table,
          checked: false
        })
      }

      this.leftTree.push(
        db
      )
    }
  }

  oldData: DbTableVO[][] = data

  // 就一层
  leftData: TreeVO<DbTableVO>[] = this.tableDataToFlat(this.oldData)

  rightData: TreeVO<DbTableVO>[] = []

  tableDataToFlat(tableData) {
    const leftTree: TreeVO<DbTableVO>[] = []
    for (const dbName in tableData) {
      const db = {
        pid: 0,
        id: dbName,
        label: dbName
      }
      leftTree.push(db)

      const tables = tableData[dbName]
      for (const table of tables) {
        const child = {
          pid: dbName,
          id: dbName + '$' + table.tableName,
          label: table.tableName,
          data: table,
          checked: false
        }
        leftTree.push(child)
      }
    }
    return leftTree
  }

  get leftTree(): TreeVO<DbTableVO>[] {
    return this.treeData(this.leftData)
  }

  get rightTree(): TreeVO<DbTableVO>[] {
    return this.treeData(this.rightData)
  }

  treeData(tableData: TreeVO<DbTableVO>[]): TreeVO<DbTableVO>[] {
    let lastIndex = 0
    const result: TreeVO<DbTableVO>[] = []
    tableData.forEach(item => {
      if (item.data) {
        result[lastIndex].children.push(item)
      } else {
        item.children = []
        lastIndex = result.length
        result.push(item)
      }
    })
    return result
  }

  getFormData(dbData) {
    // 因后端问题，无法使用全属性，必须过滤得到后续处理的属性
    const attrAry = [
      'dsTypeId',
      'driverClass',
      'ip',
      'port',
      'userName',
      'passWord',
      'dbName',
      'characterEncoding'
    ]
    const data = {}
    for (const key in dbData) {
      if (attrAry.includes(key)) {
        this.$set(data, key, dbData[key])
      }
    }
    return data
  }

  confirm(): YoyoNode<any, any> {
    return undefined
  }

  /**
   * 左侧树中过滤掉已选择的和输入的条件
   * @param value
   * @param data
   */
  filterNode(value, data) {
    if (!this.inputTreeFilterText) return true
    return data.organizationName.indexOf(this.inputTreeFilterText) !== -1
  }

  curTreeNodeId = -1

  // 单击选择左侧tree选中资源
  nodeClick(treeNode) {
    treeNode.checked = !treeNode.checked
    /* if (this.curTreeNodeId === treeNode.id) {
      this.curTreeNodeId = -1
    } else {
      // 如果为最底层，表的级别
      this.curTreeNodeId = treeNode.id
    }
    this.queryTableData()*/
  }

  rightToLeft() {
    const checkedData = this.leftData.filter(item => item.checked)
    Arrays.deleteAry(this.leftData, checkedData)
    this.rightData.push(...checkedData)
  }

  leftToRight() {
    // 一次for
    const checkedData = this.leftData.filter(item => item.checked)
    // 两次for
    Arrays.deleteAry(this.leftData, checkedData)

    const leftIndexMap = new Map()
    this.leftData.forEach((item, index) => leftIndexMap.set(item.id, index))

    const rightMap = new Map()
    this.rightData.forEach(item => rightMap.set(item.id, 1))

    // 一次for
    for (const checkedDatum of checkedData) {
      // 一次双层for
      if (!rightMap.has(checkedDatum.pid)) {
        // 一次三层for
        const parent = this.leftData[leftIndexMap.get(checkedDatum.pid)]
        this.rightData.push(parent)
        rightMap.set(parent.id, 1)
      }
      checkedDatum.checked = false
      this.rightData.push(checkedDatum)
    }

    const map = new Map()
    for (const leftDatum of this.leftData) {
      map.set(leftDatum.pid, 1)
    }
    for (const leftDatum of this.leftData) {
      if (leftDatum.pid === 0 || !leftDatum.pid) {
        if (!map.has(leftDatum.id)) {
          this.leftData.splice(leftIndexMap.get(leftDatum.id), 1)
        }
      }
    }

    this.rightData = JsonUtil.deepClone(this.rightData)
    this.leftData = JsonUtil.deepClone(this.leftData)
  }
}
</script>
