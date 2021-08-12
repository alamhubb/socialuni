<template>
  <div class="h100p row-all-center">
    <el-card class="w600">
      <el-form label-position="right" label-width="100px">
        <el-form-item label="当前用户：">
          {{ user.phoneNum }}
          <!--          <el-input :value="user.phoneNum"/>-->
        </el-form-item>
        <el-form-item label="开发者ID：">
          <!--          <el-input :value="user.devId"/>-->
          {{ user.devNum }}
        </el-form-item>
        <el-form-item label="开发者密钥：">
          <div class="flex-row">
            <div class="flex-1">
              {{ user.secretKey || '不记录显示，忘记点重置' }}
            </div>
            <div class="flex-none">
              <el-button
                v-clipboard:copy="user.secretKey"
                :disabled="!user.secretKey"
                type="text"
                class="mr-sm"
                @click="copySecretKey"
              >复制秘钥
              </el-button>
              <el-button type="text" class="mr-sm" @click="getSecretKey">重置</el-button>

              <el-tooltip content="出于安全考虑，开发者秘钥不被明文保存，忘记密钥请重置" placement="bottom" effect="light">
                <i class="el-icon-question color-grey pointer" />
              </el-tooltip>
            </div>
          </div>

        </el-form-item>
        <!--<el-form-item label="开发者类型">
          <el-radio-group v-model="user.type" :disabled="disabledEdit">
            <el-radio border :label="UserType.company">企业</el-radio>
            <el-radio border :label="UserType.person">个人</el-radio>
          </el-radio-group>
          <el-button v-if="disabledEdit">编辑</el-button>
          <div>
            <el-button>取消</el-button>
            <el-button>保存</el-button>
          </div>
        </el-form-item>
        <el-form-item :label="userTypeLabel">
          <el-input v-model="user.realName" :readonly="disabledEdit"/>
        </el-form-item>
        <el-form-item :label="userTypeLabel">
          <el-input v-model="user.realName"/>
        </el-form-item>-->

      </el-form>
    </el-card>
  </div>
</template>

<script lang="tsx">
import { Component, Vue } from 'vue-property-decorator'
import UserVO from '@/model/base/UserVO'
import { namespace } from 'vuex-class'
import UserType from '@/constants/UserType'
import { OperationType } from '@/constants/OperationType'
import ToastUtil from '@/utils/ToastUtil'
import { Alert } from 'element-ui'
import AlertUtil from '@/utils/AlertUtil'
import UserAPI from '@/api/UserAPI'

const userStore = namespace('user')
@Component
export default class HomePage extends Vue {
  @userStore.State('user') user: UserVO

  operateStatus = OperationType.detail
  OperationType = OperationType

  get disabledEdit() {
    return this.operateStatus === OperationType.detail
  }

  UserType = UserType

  get userTypeLabel() {
    return this.user.type === UserType.person ? '个人姓名' : '机构全称'
  }

  getSecretKey() {
    AlertUtil.confirm('是否确认重置开发者秘钥？').then(() => {
      UserAPI.resetSecretKeyAPI().then(res => {
        this.user.secretKey = res.data
      })
      // 调用后台
      // 设置秘钥的值
    })
  }

  copySecretKey() {
    ToastUtil.success('复制成功')
  }
}
</script>
