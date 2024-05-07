<template>
  <div class="h100p row-all-center">
    <el-card class="w600">
      <el-form ref="devForm" :model="user" :rules="formRule" label-position="right" label-width="130px">
<!--        <el-form-item label="开发者Id：">
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
        </el-form-item>-->
        <el-form-item label="开发者密钥：">
          <div class="row-between-center">
            <div class="color-content">
              {{ user.secretKey || '填写app简称后生成秘钥' }}
            </div>
            <div v-if="userHasSecretKey">
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
        <!--        <el-form-item label="开发者类型：">
          <div class="row-between-center">
            <el-radio-group v-model="user.type">
              <el-radio border :label="$const.DevAccountType.company">企业</el-radio>
              <el-radio border :label="$const.DevAccountType.personal">个人</el-radio>
            </el-radio-group>
            <el-button type="primary" plain @click="updateUser">
              {{ userSecretKey ? '更新' : '生成秘钥' }}
            </el-button>
          </div>
        </el-form-item>-->
<!--        <el-form-item label="app简称：" prop="appName">
          <div class="row-between-center">
            <div>
              <el-input
                v-model="user.appName"
                placeholder="请输入app简称后生成秘钥"
                type="text"
                :maxlength="6"
                clearable
              />
            </div>
            <el-button type="primary" plain :disabled="!user.appName" @click="updateUser">
              {{ userHasSecretKey ? '更新' : '生成秘钥' }}
            </el-button>
          </div>
        </el-form-item>
        &lt;!&ndash;        <el-form-item :label="userTypeLabel+'：'" required>
          <el-input v-model="user.realName" />
        </el-form-item>&ndash;&gt;
        <el-form-item label="微信 appId：" prop="wxMpAppId">
          <el-input v-model="user.wxMpAppId" placeholder="微信和qq appId至少填写一个" clearable @input="clearValidate" />
        </el-form-item>
        <el-form-item label="微信小程序名称：">
          <el-input v-model="user.wxMpAppName" placeholder="不填写默认app简称" clearable @input="clearValidate" />
        </el-form-item>
        <el-form-item label="qq appId：" prop="qqMpAppId">
          <el-input v-model="user.qqMpAppId" placeholder="微信和qq appId至少填写一个" clearable />
        </el-form-item>
        <el-form-item label="qq小程序名称：">
          <el-input v-model="user.qqMpAppName" placeholder="不填写默认app简称" clearable />
        </el-form-item>-->
      </el-form>
    </el-card>
  </div>
</template>

<script lang="tsx">
import { Component, Vue } from 'vue-facing-decorator'
import DevAccountRO from '@/model/base/DevAccountRO'
import DevAccountType from '@/constant/DevAccountType'
import { OperationType } from '@/constant/OperationType'
import ToastUtil from '@/utils/ToastUtil'
import AlertUtil from '@/utils/AlertUtil'
import AdminUserAPI from '@/api/AdminUserAPI'
import { ElForm } from 'element-ui/types/form'

@toNative
@Component
export default class DevInfoPage extends Vue {
  $refs: {
    devForm: ElForm
  }

  operateType = OperationType.detail
  backUser: DevAccountRO

  formRule = {
    appName: [
      { required: true, trigger: 'blur', message: '请输入app中文简称' }, {
        required: true,
        trigger: 'blur',
        message: '请输入2-6个字符长度的app简称', min: 2, max: 6
      }],
    wxMpAppId: [{ validator: this.checkWxAndQQAppIdNotNull, trigger: 'blur' }],
    qqMpAppId: [{ validator: this.checkWxAndQQAppIdNotNull, trigger: 'blur' }]
  }

  clearValidate() {
    if (this.user.wxMpAppId || this.user.qqMpAppId) {
      this.$refs.devForm.clearValidate()
      this.$refs.devForm.validate()
    }
  }

  checkWxAndQQAppIdNotNull(rule, value: string, callback: Function) {
    if (this.user.wxMpAppId || this.user.qqMpAppId) {
      callback()
      return
    }
    callback(new Error('微信和qq appId至少填写一个'))
    return
  }

  get disabledEdit() {
    return this.operateType === OperationType.detail
  }

  get userTypeLabel() {
    return this.user.type === DevAccountType.personal ? '个人姓名' : '企业名称'
  }

  getSecretKey() {
    AlertUtil.confirm('是否确认重置开发者秘钥？').then(() => {
      AdminUserAPI.resetSecretKeyAPI().then(res => {
        this.user.secretKey = res.data
      })
      // 调用后台
      // 设置秘钥的值
    })
  }

  copySecretKey() {
    ToastUtil.success('复制成功')
  }

  async updateUser() {
    await this.$refs.devForm.validate()
    AlertUtil.confirm('是否确定要修改开发者信息').then(() => {
      AdminUserAPI.updateDevAccountAPI(this.user).then(res => {
        userModule.user = res.data
      })
    })
  }

  get userHasSecretKey() {
    return !!this.user.secretKey
  }
}
</script>
