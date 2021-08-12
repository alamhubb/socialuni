<template>
  <div>
    <div class="flex-row">
      <el-card style="height: calc(100% - 147px)" class="w300 mr flex-col position-fixed">
        <div class="h100p flex-col">
          <div class="row-between-center flex-none">
            <div class="font-md">
              指挥结构
            </div>
            <div>
              <el-button class="text-md pa-xs">
                <i class="mdi mdi-playlist-edit font-lg" />
              </el-button>
            </div>
          </div>
          <el-divider class="mt" />
          <!-- 搜索框-->
          <el-input
            v-model="treeFilterText"
            class="flex-none mb bg-grey"
            placeholder="搜索"
            prefix-icon="el-icon-search"
            @input.native="treeFilterInput"
          />
          <!--          strengthGroup_container 样式类决定选中后背景色-->
          <el-tree
            ref="dataTree"
            class="flex-1 overflow-auto strengthGroup_container"
            :data="treeData"
            :props="{ children: 'children', label: 'label' }"
            default-expand-all
            :expand-on-click-node="false"
            :filter-node-method="treeFilterMethod"
            @node-click="treeNodeClick"
          />
          <div class="row-center color-blue">
            数据更新时间：2021-1-29
          </div>
        </div>
      </el-card>
      <div class="w300 h100 mr flex-none" />

      <div class="flex-col flex-1">

        <div class="flex-row flex-none h140 mb">
          <el-card class="h100p flex-1 mr-md" :body-style="{padding:0}">
            <div
              class="bg-w100p h100p pa color-white pointer"
              @click="switchTableClick(1)"
            >
              <!--              :style="{backgroundImage: 'url(' + require('@/assets/img/personStatus/topLeftTitleBg.png') + ')' }"-->
              <div class="mb-md font-lg">施工指挥(总工/各级高工)</div>
              <div class="flex-row mb-xs">
                <div class="font-lg mr-lg">在位：394人</div>
                <div class="font-lg ml-lg">不在位：126人</div>
              </div>
              <div class="row-center">
                <!--                <img v-if="showTable===1" class="mt-sm" src="@/assets/img/personStatus/titleDownArrow.png">-->
                <!--                <img v-else class="mt" src="@/assets/img/personStatus/titleRadiusRect.png">-->
              </div>
            </div>
          </el-card>

          <el-card class="h100p flex-1 mr-md" :body-style="{padding:0}">
            <div
              class=" bg-w100p h100p pa color-white pointer"
              @click="switchTableClick(2)"
            >
              <!--              :style="{backgroundImage: 'url(' + require('@/assets/img/personStatus/topCenterTitleBg.png') + ')' }"-->
              <div class="mb-md font-lg">技术操作(关键岗位)</div>
              <div class="flex-row mb-xs">
                <div class="font-lg mr-lg">在位：394人</div>
                <div class="font-lg ml-lg">不在位：126人</div>
              </div>
              <div class="row-center">
                <!--                <img v-if="showTable===2" class="mt-sm" src="@/assets/img/personStatus/titleDownArrow.png">-->
                <!--                <img v-else class="mt" src="@/assets/img/personStatus/titleRadiusRect.png">-->
              </div>
            </div>
          </el-card>

          <el-card class="h100p flex-1" :body-style="{padding:0}">
            <div
              class=" bg-w100p h100p pa color-white pointer"
              @click="switchTableClick(3)"
            >
              <!--              :style="{backgroundImage: 'url(' + require('@/assets/img/personStatus/topRightTitleBg.png') + ')' }"-->
              <div class="mb-md font-lg">施工保障(后勤管理)</div>
              <div class="flex-row mb-xs">
                <div class="font-lg mr-lg">在位：394人</div>
                <div class="font-lg ml-lg">不在位：126人</div>
              </div>
              <div class="row-center">
                <!--                <img v-if="showTable===3" class="mt-sm" src="@/assets/img/personStatus/titleDownArrow.png">-->
                <!--                <img v-else class="mt" src="@/assets/img/personStatus/titleRadiusRect.png">-->
              </div>
            </div>
          </el-card>
        </div>

        <transition name="fade">
          <el-card
            v-show="isShowTable(1)"
            class="h420 mb"
            :body-style="{padding:0}"
          >
            <div class="h100p flex-col">
              <div class="row-between pa-sm pr flex-none bg-grey">
                <div class="row-col-center text-md">
                  <div class="y-piece-xs mr-sm" />
                  施工指挥(总工/各级高工)
                  <span class="ml font-xs">
                    已选数量：
                    {{ checkedData.length }} / {{ data.length }}
                  </span>
                </div>
                <div class="flex-row">
                  <el-input v-model="username" class="mr" size="small" prefix-icon="el-icon-search" placeholder="输入关键字" />

                  <el-button size="small">批量删除</el-button>
                </div>
              </div>

              <el-table
                ref="multipleTable"
                :data="data"
                height="100"
                class="flex-1 border-none"
                border
                stripe
                @row-click="tableRowClick"
              >
                <el-table-column
                  :render-header="colHeaderRender"
                  label="人员"
                  prop="key"
                  width="50"
                  align="center"
                >
                  <template #default="{row}">
                    <div>
                      <el-checkbox
                        v-model="row.checked"
                        @click.native.stop
                      />
                    </div>
                  </template>
                </el-table-column>
                <el-table-column prop="realName" label="人员名称" width="110" align="center" />
                <el-table-column prop="name" label="性别" width="70" align="center" />
                <el-table-column prop="realName" label="所在部门" width="180" align="center" />
                <el-table-column prop="deptName" label="职务" width="130" align="center" />
                <el-table-column prop="name" label="类型" width="110" align="center" />
                <el-table-column prop="name" label="当前状态" width="120" align="center" />
                <el-table-column prop="phone" label="入职时间" width="130" align="center" />
                <el-table-column prop="phone" label="编组内码" width="270" align="center" />
                <el-table-column label="操作" align="center">
                  <template #default="{row}">
                    <el-button type="text" @click="openDetailDialog(row)">
                      <span class="color-black">
                        <i class="el-icon-view" />
                        查看
                      </span>
                    </el-button>
                    <el-button type="text" @click="openEditDialog(row)">
                      <span class="color-black">
                        <i class="el-icon-edit-outline" />
                        编辑
                      </span>
                    </el-button>

                    <el-button type="text" @click="deleteDataClick(row)">
                      <span class="color-black">
                        <i class="el-icon-delete" />
                        删除
                      </span>
                    </el-button>
                  </template>
                </el-table-column>
              </el-table>
            </div>
          </el-card>

        </transition>

        <transition name="fade">
          <el-card
            v-show="isShowTable(2)"
            class="h420 mb"
            :body-style="{padding:0}"
          >
            <div class="h100p flex-col">
              <div class="row-between pa-sm pr flex-none bg-grey">
                <div class="row-col-center text-md">
                  <div class="y-piece-xs mr-sm" />
                  技术操作(关键岗位)
                  <span class="ml font-xs">
                    已选数量：
                    {{ checkedData.length }} / {{ data.length }}
                  </span>
                </div>
                <div class="flex-row">
                  <el-input v-model="username" class="mr" size="small" prefix-icon="el-icon-search" placeholder="输入关键字" />

                  <el-button size="small">批量删除</el-button>
                </div>
              </div>

              <el-table
                ref="multipleTable"
                :data="data"
                height="100"
                class="flex-1 border-none"
                border
                stripe
                @row-click="tableRowClick"
              >
                <el-table-column
                  :render-header="colHeaderRender"
                  label="人员"
                  prop="key"
                  width="50"
                  align="center"
                >
                  <template #default="{row}">
                    <div>
                      <el-checkbox
                        v-model="row.checked"
                        @click.native.stop
                      />
                    </div>
                  </template>
                </el-table-column>
                <el-table-column prop="realName" label="人员名称" width="110" align="center" />
                <el-table-column prop="name" label="性别" width="70" align="center" />
                <el-table-column prop="realName" label="所在部门" width="180" align="center" />
                <el-table-column prop="deptName" label="职务" width="130" align="center" />
                <el-table-column prop="name" label="类型" width="110" align="center" />
                <el-table-column prop="name" label="当前状态" width="120" align="center" />
                <el-table-column prop="phone" label="入职时间" width="130" align="center" />
                <el-table-column prop="phone" label="编组内码" width="270" align="center" />
                <el-table-column label="操作" align="center">
                  <template #default="{row}">
                    <el-button type="text" @click="openDetailDialog(row)">
                      <span class="color-black">
                        <i class="el-icon-view" />
                        查看
                      </span>
                    </el-button>
                    <el-button type="text" @click="openEditDialog(row)">
                      <span class="color-black">
                        <i class="el-icon-edit-outline" />
                        编辑
                      </span>
                    </el-button>

                    <el-button type="text" @click="deleteDataClick(row)">
                      <span class="color-black">
                        <i class="el-icon-delete" />
                        删除
                      </span>
                    </el-button>
                  </template>
                </el-table-column>
              </el-table>
            </div>
          </el-card>
        </transition>

        <transition name="fade">
          <el-card
            v-show="isShowTable(3)"
            class="h420 mb"
            :body-style="{padding:0}"
          >
            <div class="h100p flex-col">
              <div class="row-between pa-sm pr flex-none bg-grey">
                <div class="row-col-center text-md">
                  <div class="y-piece-xs mr-sm" />
                  技术操作(关键岗位)
                  <span class="ml font-xs">
                    已选数量：
                    {{ checkedData.length }} / {{ data.length }}
                  </span>
                </div>
                <div class="flex-row">
                  <el-input v-model="username" class="mr" size="small" prefix-icon="el-icon-search" placeholder="输入关键字" />

                  <el-button size="small">批量删除</el-button>
                </div>
              </div>

              <el-table
                ref="multipleTable"
                :data="data"
                height="100"
                class="flex-1 border-none"
                border
                stripe
                @row-click="tableRowClick"
              >
                <el-table-column
                  :render-header="colHeaderRender"
                  label="人员"
                  prop="key"
                  width="50"
                  align="center"
                >
                  <template #default="{row}">
                    <div>
                      <el-checkbox
                        v-model="row.checked"
                        @click.native.stop
                      />
                    </div>
                  </template>
                </el-table-column>
                <el-table-column prop="realName" label="人员名称" width="110" align="center" />
                <el-table-column prop="name" label="性别" width="70" align="center" />
                <el-table-column prop="realName" label="所在部门" width="180" align="center" />
                <el-table-column prop="deptName" label="职务" width="130" align="center" />
                <el-table-column prop="name" label="类型" width="110" align="center" />
                <el-table-column prop="name" label="当前状态" width="120" align="center" />
                <el-table-column prop="phone" label="入职时间" width="130" align="center" />
                <el-table-column prop="phone" label="编组内码" width="270" align="center" />
                <el-table-column label="操作" align="center">
                  <template #default="{row}">
                    <el-button type="text" @click="openDetailDialog(row)">
                      <span class="color-black">
                        <i class="el-icon-view" />
                        查看
                      </span>
                    </el-button>
                    <el-button type="text" @click="openEditDialog(row)">
                      <span class="color-black">
                        <i class="el-icon-edit-outline" />
                        编辑
                      </span>
                    </el-button>

                    <el-button type="text" @click="deleteDataClick(row)">
                      <span class="color-black">
                        <i class="el-icon-delete" />
                        删除
                      </span>
                    </el-button>
                  </template>
                </el-table-column>
              </el-table>
            </div>
          </el-card>
        </transition>

        <el-card class="h420" :body-style="{padding:0}">
          <div class="h100p flex-col">
            <div class="row-between pa-sm pr flex-none bg-grey">
              <div class="row-col-center text-md">
                <div class="y-piece-xs mr-sm" />
                全部数据列表
                <span class="ml font-xs">
                  已选数量：
                  {{ checkedData.length }} / {{ data.length }}
                </span>
              </div>

              <div class="flex-row">
                <el-input v-model="username" class="mr" size="small" prefix-icon="el-icon-search" placeholder="输入关键字" />

                <el-button size="small">批量删除</el-button>
              </div>
            </div>

            <el-table
              ref="multipleTable"
              :data="data"
              height="100"
              class="flex-1 border-none"
              border
              stripe
              @row-click="tableRowClick"
            >
              <el-table-column
                :render-header="colHeaderRender"
                label="人员"
                prop="key"
                width="50"
                align="center"
              >
                <template #default="{row}">
                  <div>
                    <el-checkbox
                      v-model="row.checked"
                      @click.native.stop
                    />
                  </div>
                </template>
              </el-table-column>
              <el-table-column prop="realName" label="人员名称" width="110" align="center" />
              <el-table-column prop="name" label="性别" width="70" align="center" />
              <el-table-column prop="realName" label="所在部门" width="180" align="center" />
              <el-table-column prop="deptName" label="职务" width="130" align="center" />
              <el-table-column prop="name" label="类型" width="110" align="center" />
              <el-table-column prop="name" label="当前状态" width="120" align="center" />
              <el-table-column prop="phone" label="入职时间" width="130" align="center" />
              <el-table-column prop="phone" label="编组内码" width="270" align="center" />
              <el-table-column label="操作" align="center">
                <template #default="{row}">
                  <el-button type="text" @click="openDetailDialog(row)">
                    <span class="color-black">
                      <i class="el-icon-view" />
                      查看
                    </span>
                  </el-button>
                  <el-button type="text" @click="openEditDialog(row)">
                    <span class="color-black">
                      <i class="el-icon-edit-outline" />
                      编辑
                    </span>
                  </el-button>

                  <el-button type="text" @click="deleteDataClick(row)">
                    <span class="color-black">
                      <i class="el-icon-delete" />
                      删除
                    </span>
                  </el-button>
                </template>
              </el-table-column>
            </el-table>
          </div>
        </el-card>
      </div>
    </div>

    <!-- detail or edit -->
    <person-operate-dialog ref="dialog" />
  </div>
</template>

<script lang="tsx">
import { Component, Vue, Watch } from 'vue-property-decorator'
import UserManageAPI from '@/api/system/UserManageAPI'
import UserOperateDialog from '@/views/system/user/UserOperateDialog.vue'
import UserVO from '@/model/UserManage/UserVO'
import { OperationType } from '@/constants/OperationType'
import { UserStatus } from '@/constants/UserStatus'
import AssignRoleDialog from '@/views/system/user/AssignRoleDialog.vue'
import Pinyin from 'pinyin-match'
import TreeVO from '@/model/base/TreeVO'
import PersonOperateDialog from '@/views/personStatus/PersonOperateDialog.vue'
import treeData from '@/views/personStatus/mockData'
import { ElTree } from 'element-ui/types/tree'

@Component({
  components: { PersonOperateDialog, AssignRoleDialog, UserOperateDialog }
})
export default class PersonStatusPage extends Vue {
  $refs: {
    dialog: PersonOperateDialog;
    dataTree: ElTree<any, any>;
  }

  // 0,1,2,3
  showTable = 0

  currentPage = 1
  pageSize = 10
  totalNum = 0

  treeFilterText = ''

  realName = ''
  username = ''
  userStatus = ''

  UserStatus = UserStatus

  treeData = treeData

  data = []

  get hasNoChecked(): boolean {
    return this.data.some(item => !item.checked)
    // return true
  }

  created() {
    this.queryData()
  }

  mounted() {
    // this.openDetailDialog(this.data[0])
  }

  count = 0

  switchTableClick(tableNum) {
    if (tableNum === this.showTable) {
      this.showTable = 0
    } else {
      this.showTable = tableNum
    }
  }

  colHeaderRender({ column, $index }) {
    return (
      <el-checkbox value={!this.hasNoChecked} onChange={this.checkedAllChange}
      >
      </el-checkbox>
    )
  }

  isShowTable(tableNum) {
    return this.showTable === tableNum
  }

  // 选中全部
  checkedAllChange() {
    console.log('触发了')
    if (this.hasNoChecked) {
      // 有未选中的全选
      this.data.forEach(item => item.checked = true)
    } else {
      // 全选时，取消全选
      this.data.forEach(item => item.checked = false)
    }
  }

  checkedData() {
    return this.data.filter(item => item.checked)
  }

  queryData() {
    const userObj = {
      name: this.username,
      realName: this.realName,
      userStatus: this.userStatus,
      intPage: this.currentPage,
      pageSize: this.pageSize
    }
    UserManageAPI.queryUsersAPI(userObj).then(res => {
      this.data = res.data.list
      this.totalNum = res.data.total
      // this.openDetailDialog(this.data[0])
    })
  }

  // 用户状态切换
  switchUserStatusClick(row: UserVO) {
    let status = UserStatus.disabled
    if (row.userStatus === UserStatus.disabled) {
      status = UserStatus.enabled
    }
    UserManageAPI.switchUserStatusAPI(row.id, status).then(() => {
      this.queryData()
    })
  }

  // 重置密码
  resetPasswordClick(row: UserVO) {
    UserManageAPI.resetPasswordAPI(row.id).then(response => {
      this.queryData()
    })
  }

  // 角色分配
  openAssignRoleDialog(row) {
    this.$refs.roleDialog.openDialog()
  }

  /**
   * 左侧树中过滤掉已选择的和输入的条件
   * @param value
   * @param data
   */
  treeFilterMethod(value, data) {
    return !this.treeFilterText || Pinyin.match(data.label, this.treeFilterText)
  }

  treeFilterInput(e) {
    this.treeFilterText = e.target.value
    this.$refs.dataTree.filter(this.treeFilterText)
  }

  // 单行点击取消和选中
  tableRowClick(row) {
    // 不为对比时才生效
    row.checked = !row.checked
    /* if (!this.showCompare) {
      this.drawDetailLine()
    }*/
  }

  // 单击选择左侧tree选中资源
  treeNodeClick(treeNode: TreeVO) {
    // 如果为最底层，表的级别
    this.curTreeNodeId = treeNode.rc_id
    this.queryData()
  }

  deleteDataClick(data) {
    this.$confirm('确定要删除所选应用吗？', '提示', {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning'
    }).then(() => {
      UserManageAPI.deleteUserAPI(data.id).then(res => {
        this.queryData()
      })
    }).catch(() => {
      this.$message({
        type: 'info',
        message: '已取消删除'
      })
    })
  }

  handleChange(roleValue, direction, movedKeys) {
    console.log(roleValue, direction, movedKeys)
  }

  /* handleSelectionChange(row) {
    this.tableSelectLength = row
  }*/

  currentPageChange(currentPage: number) {
    this.currentPage = currentPage
    // 重置数据，和查询
    this.initQueryData()
  }

  initQueryData() {
    this.realName = ''
    this.username = ''
    this.userStatus = ''
    this.queryData()
  }

  pageSizeChange(pageSize: number) {
    this.pageSize = pageSize
    // 重置数据，和查询
    this.initQueryData()
  }

  // 弹框操作部分

  // 编辑
  openEditDialog(row) {
    this.openDialog(OperationType.edit, row)
  }

  // 详情
  openDetailDialog(row) {
    this.openDialog(OperationType.detail, row)
  }

  // 打开弹框
  openDialog(operate: OperationType, row?) {
    this.$refs.dialog.openDialog(operate, row)
  }
}
</script>
