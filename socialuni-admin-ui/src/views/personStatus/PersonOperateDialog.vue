<template>
  <el-dialog
    :title="dialogTitle"
    :visible.sync="dialogVisible"
    :close-on-click-modal="false"
    top="3%"
    width="1200px"
  >
    <div class="h70vh flex-row mb">
      <div class="w900 border-right pr mr flex-col">
        <div class="flex-none">
          <div class="flex-row">
            <div class="flex-none w150 h190 mr">
              <!--              <img src="@/assets/img/personStatus/dialog/personPhoto.png">-->
            </div>
            <div class="flex-1">
              <el-form
                ref="form"
                class="flex-1"
                :rules="formRules"
                :model="formData"
                label-width="150px"
                label-position="top"
              >
                <div>
                  <el-row :gutter="100">
                    <el-col :span="8">
                      <el-form-item size="small" label="用户名：" prop="name" class="pb-0 mb-0">
                        <el-input v-model="formData.name" type="text" placeholder="请输入用户名" />
                      </el-form-item>
                    </el-col>
                    <el-col :span="8">
                      <el-form-item size="small" label="姓名：" prop="realName" class="pb-0 mb-0">
                        <el-input v-model="formData.realName" type="text" placeholder="请输入姓名" />
                      </el-form-item>
                    </el-col>
                    <el-col :span="8">
                      <el-form-item size="small" label="联系电话：" prop="phone" class="pb-0 mb-0">
                        <el-input v-model="formData.phone" type="text" placeholder="请输入联系电话" />
                      </el-form-item>
                    </el-col>
                  </el-row>
                  <el-row :gutter="100">
                    <el-col :span="8">
                      <el-form-item size="small" label="用户名：" prop="name" class="pb-0 mb-0">
                        <el-input v-model="formData.name" type="text" placeholder="请输入用户名" />
                      </el-form-item>
                    </el-col>
                    <el-col :span="8">
                      <el-form-item size="small" label="姓名：" prop="realName" class="pb-0 mb-0">
                        <el-input v-model="formData.realName" type="text" placeholder="请输入姓名" />
                      </el-form-item>
                    </el-col>
                    <el-col :span="8">
                      <el-form-item size="small" label="联系电话：" prop="phone" class="pb-0 mb-0">
                        <el-input v-model="formData.phone" type="text" placeholder="请输入联系电话" />
                      </el-form-item>
                    </el-col>
                  </el-row>
                  <el-row :gutter="100">
                    <el-col :span="8">
                      <el-form-item size="small" label="用户名：" prop="name" class="pb-0 mb-0">
                        <el-input v-model="formData.name" type="text" placeholder="请输入用户名" />
                      </el-form-item>
                    </el-col>
                    <el-col :span="8">
                      <el-form-item size="small" label="姓名：" prop="realName" class="pb-0 mb-0">
                        <el-input v-model="formData.realName" type="text" placeholder="请输入姓名" />
                      </el-form-item>
                    </el-col>
                    <el-col :span="8">
                      <el-form-item size="small" label="联系电话：" prop="phone" class="pb-0 mb-0">
                        <el-input v-model="formData.phone" type="text" placeholder="请输入联系电话" />
                      </el-form-item>
                    </el-col>
                  </el-row>
                </div>
              </el-form>
            </div>
          </div>
          <el-divider />
        </div>

        <div class="flex-none position-relative mb-sm">
          <div class="mb">
            <i class="el-icon-caret-right color-grey" />
            人员嘉奖
          </div>
          <el-input
            v-model="input3"
            type="textarea"
            placeholder="嘉奖内容"
            class="input-with-select"
            rows="10"
            resize="none"
          />
          <div class="position-absolute bottom-0 pa row-between w100p">
            <el-date-picker
              v-model="value1"
              type="date"
              placeholder="选择日期"
            />
            <div>
              <el-button type="text" class="mr">创建</el-button>
              <el-button type="text">取消</el-button>
            </div>
          </div>
        </div>

        <div class="flex-1 overflow-auto">
          <el-card class="mb-sm bg-hover" shadow="never">
            <div class="row-between">
              <div class="row-col-center">
                <!--                <img class="mr-sm" src="@/assets/img/home/table/rowLogo.png">-->
                保证部门的集中统一，成绩突出，有较大贡献，记<span class="color-blue">三等功</span>
              </div>
              <div>
                2020-10-8
              </div>
            </div>
          </el-card>
          <el-card class="mb-sm bg-hover" shadow="never">
            <div class="row-between">
              <div class="row-col-center">
                <!--                <img class="mr-sm" src="@/assets/img/home/table/rowLogo.png">-->
                保证部门的集中统一，成绩突出，有较大贡献，记<span class="color-blue">三等功</span>
              </div>
              <div>
                2020-10-8
              </div>
            </div>
          </el-card>
          <el-card class="mb-sm bg-hover" shadow="never">
            <div class="row-between">
              <div class="row-col-center">
                <!--                <img class="mr-sm" src="@/assets/img/home/table/rowLogo.png">-->
                保证部门的集中统一，成绩突出，有较大贡献，记<span class="color-blue">三等功</span>
              </div>
              <div>
                2020-10-8
              </div>
            </div>
          </el-card>
          <el-card class="mb-sm bg-hover" shadow="never">
            <div class="row-between">
              <div class="row-col-center">
                <!--                <img class="mr-sm" src="@/assets/img/home/table/rowLogo.png">-->
                保证部门的集中统一，成绩突出，有较大贡献，记<span class="color-blue">三等功</span>
              </div>
              <div>
                2020-10-8
              </div>
            </div>
          </el-card>
        </div>
      </div>
      <div class="w300 flex-col bg-grey pa">
        <div class="font-md mb-sm flex-none">关键管理</div>
        <el-form
          ref="form"
          class="flex-1"
          :rules="formRules"
          :model="formData"
          label-width="150px"
          label-position="top"
        >
          <el-form-item size="small" label="用户名：" prop="name" class="pb-0 mb-sm">
            <el-input v-model="formData.name" type="text" placeholder="请输入用户名" />
          </el-form-item>

          <el-form-item size="small" label="用户名：" prop="name" class="pb-0 mb-sm">
            <el-input v-model="formData.name" type="text" placeholder="请输入用户名" />
          </el-form-item>

          <el-form-item size="small" label="用户名：" prop="name" class="pb-0 mb-sm">
            <el-input v-model="formData.name" type="text" placeholder="请输入用户名" />
          </el-form-item>

          <el-form-item size="small" label="用户名：" prop="name" class="pb-0 mb-sm">
            <el-input v-model="formData.name" type="text" placeholder="请输入用户名" />
          </el-form-item>

          <el-form-item size="small" label="用户名：" prop="name" class="pb-0 mb-sm">
            <el-input v-model="formData.name" type="text" placeholder="请输入用户名" />
          </el-form-item>
        </el-form>
        <div class="row-end">
          <el-button
            class="w100p"
            type="primary"
            size="small"
            @click="confirmClick"
          >提交信息
          </el-button>
        </div>
      </div>
    </div>
  </el-dialog>
</template>

<script lang="ts">
import {
  Component, Emit, Prop, Vue, Watch
} from 'vue-property-decorator'

import { Message } from 'element-ui'
import UserVO from '@/model/UserManage/UserVO'
import UserManageAPI from '@/api/system/UserManageAPI'
import { OperationType } from '@/constants/OperationType'
import JsonUtils from '@/utils/JsonUtil'
import { ElTree } from 'element-ui/types/tree'
import { ElForm } from 'element-ui/types/form'

const num = /^1[3456789]\d{9}$/
const passwordReg = /^[0-9A-Za-z.*?@#￥%^&]{5,16}$/
const han = /^[\u4e00-\u9fa5]+$/

@Component
export default class PersonOperateDialog extends Vue {
  OperateType = OperationType
  operateType = ''
  dialogVisible = false
  formData = new UserVO()

  input3 = ''
  select = '1'
  $refs: {
    departmentTree: ElTree<any, any>;
    form: ElForm;
  }

  departmentTreeData = []
  getTreeId = []
  depDefaultProps = {
    children: 'childList',
    label: 'name'
  }
  formRules = {
    name: [
      { required: true, message: '用户名不能为空', trigger: 'blur' }
    ],
    password: [
      { required: true, validator: this.validatePass, trigger: 'blur' }
    ],
    rePassword: [
      { required: true, validator: this.validatePass2, trigger: 'blur' }
    ],
    realName: [
      { required: true, validator: this.validateRealName, trigger: 'blur' }
    ],
    phone: [
      { required: true, validator: this.phoneNumber, trigger: 'blur' }
    ]
  }

  phoneNumber(rule, value, callback) {
    if (!num.test(value)) {
      return callback(new Error('电话格式为11位，请正确输入'))
    } else {
      callback()
    }
  }

  validatePass(rule, value, callback) {
    if (value === '') {
      callback(new Error('请输入密码'))
    } else if (!passwordReg.test(value)) {
      return callback(new Error('密码必须为5-16位'))
    } else {
      if (this.formData.rePassword !== '') {
        this.$refs.form.validateField('rePassword')
      }
      callback()
    }
  }

  validatePass2(rule, value, callback) {
    if (value === '') {
      callback(new Error('请再次输入密码'))
    } else if (value !== this.formData.password) {
      callback(new Error('两次输入密码不一致!'))
    } else {
      callback()
    }
  }

  validateRealName(rule, value, callback) {
    if (value === '') {
      callback(new Error('姓名不能为空'))
    } else if (!han.test(value)) {
      callback(new Error('姓名只能是汉字'))
    } else {
      callback()
    }
  }

  get dialogTitle() {
    switch (this.operateType) {
      case OperationType.add:
        return '添加'
      case OperationType.edit:
        return '编辑'
      case OperationType.detail:
        return '查看'
      default :
        return '错误的类型'
    }
  }

  // 初始化需要的数据
  created() {
    UserManageAPI.getDeptTreeApi().then(response => {
      this.departmentTreeData = response.data.list
    })
      .catch(error => {
        console.log(error)
      })
  }

  openDialog(operationType: string, formData?: UserVO) {
    this.dialogVisible = true
    this.operateType = operationType
    if (operationType === OperationType.add) {
      this.formData = new UserVO()
    } else {
      this.formData = JsonUtils.deepClone(formData)
    }
  }

  closeDialog() {
    this.dialogVisible = false
  }

  // 确定
  confirmClick() {
    this.$refs.form.validate((valid) => {
      if (valid) {
        if (this.formData.deptId === 0) {
          this.$message({
            message: '请选择部门',
            type: 'warning'
          })
          return false
        }
        if (this.operateType === OperationType.add) {
          UserManageAPI.getAddUserApi(this.formData).then(response => {
            this.closeDialog()
            this.success()
          })
        } else if (this.operateType === OperationType.edit) {
          UserManageAPI.getUpdateUserApi(this.formData).then(response => {
            this.closeDialog()
            this.success()
          })
        }
      } else {
        console.log('error submit!!')
        return false
      }
    })
  }

  @Emit()
  success() {
    return
  }

  getTreeName() {
    // 获取当节点的值
    this.formData.deptId = 0
    const getlist = this.$refs.departmentTree.getCheckedNodes().concat(this.$refs.departmentTree.getHalfCheckedNodes())
    // 循环遍历当前节点的值
    if (getlist.length === 0) {
      this.formData.deptId = 0
    } else {
      for (const i in getlist) {
        // 判断子节点是否存在子节点 如果存在直接请求并且提示 false
        if (!getlist[i].hasOwnProperty('children')) {
          // 判断是否只选择一个 如果存在直接请求并且提示 false
          if (getlist.length === 1) {
            console.log(getlist[0].id)
            this.formData.deptId = getlist[0].id
          } else {
            this.$message.error('只能选择一个部门')
            this.$refs.departmentTree.setCheckedKeys([])
            return
          }
        } else {
          this.$message.error('只能选择当前部门最后的子部门')
          this.$refs.departmentTree.setCheckedKeys([])
          return
        }
      }
    }
  }
}
</script>
