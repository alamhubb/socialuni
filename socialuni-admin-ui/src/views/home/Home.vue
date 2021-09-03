<template>
  <div class="h100p row-all-center">
    <el-card class="w600">
      <el-form label-position="right" label-width="120px">
        <el-form-item label="当前用户：">
          {{ user.phoneNum }}
        </el-form-item>
        <el-form-item label="开发者ID：">
          <div class="row-between-center">
            <div>
              {{ user.devNum }}
            </div>
            <div>
              <el-button
                v-clipboard:copy="user.devNum"
                type="text"
                class="mr-10"
                @click="copySecretKey"
              >复制开发者ID
              </el-button>
            </div>
          </div>
        </el-form-item>
        <el-form-item label="开发者密钥：">
          <div class="row-between-center">
            <div>
              {{ user.secretKey || '不记录显示，忘记点重置' }}
            </div>
            <div>
              <el-button
                v-clipboard:copy="user.secretKey"
                type="text"
                class="mr-10"
                @click="copySecretKey"
              >复制秘钥
              </el-button>
              <el-button type="text" class="mr-10" @click="getSecretKey">重置</el-button>

              <el-tooltip content="出于安全考虑，开发者秘钥不被明文保存，忘记密钥请重置" placement="bottom" effect="light">
                <i class="el-icon-question color-grey pointer" />
              </el-tooltip>
            </div>
          </div>

        </el-form-item>
        <el-form-item label="开发者类型">
          <div class="row-between-center">
            <el-radio-group v-model="user.type">
              <el-radio border :label="UserType.company">企业</el-radio>
              <el-radio border :label="UserType.person">个人</el-radio>
            </el-radio-group>

            <el-button type="primary" plain @click="updateUser">更新</el-button>
          </div>
        </el-form-item>
        <el-form-item :label="userTypeLabel" required>
          <el-input v-model="user.realName" />
        </el-form-item>
        <el-form-item label="微信小程序Id">
          <el-input v-model="user.wxMpAppId" />
        </el-form-item>
        <el-form-item label="微信小程序名称">
          <el-input v-model="user.wxMpAppName" />
        </el-form-item>
        <el-form-item label="qq小程序Id">
          <el-input v-model="user.qqMpAppId" />
        </el-form-item>
        <el-form-item label="qq小程序名称">
          <el-input v-model="user.qqMpAppName" />
        </el-form-item>
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
import AlertUtil from '@/utils/AlertUtil'
import UserAPI from '@/api/UserAPI'

const userStore = namespace('user')
@Component
export default class HomePage extends Vue {
  @userStore.State('user') user: UserVO

  operateType = OperationType.detail
  backUser: UserVO

  get disabledEdit() {
    return this.operateType === OperationType.detail
  }

  UserType = UserType

  get userTypeLabel() {
    return this.user.type === UserType.person ? '个人姓名' : '企业名称'
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

  updateUser() {
    AlertUtil.confirm('是否确定要修改开发者信息').then(() => {
      UserAPI.updateDevAccountAPI(this.user).then(res => {
        this.user = res.data
      })
    })
  }
}
</script>
