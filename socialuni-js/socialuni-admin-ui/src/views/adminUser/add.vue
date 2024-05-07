<template>
  <div>
    <div>
      <el-form label-position="top" label-width="80px">
        <el-form-item label="员工编号">
          <el-input v-model="employeeID" />
        </el-form-item>
        <el-form-item label="员工姓名">
          <el-input v-model="name" />
        </el-form-item>
      </el-form>
    </div>

    <bottom>
      <el-button @click="addAdminUser">新增用户</el-button>
    </bottom>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-facing-decorator'
import UserIdCard from '@/model/UserIdCard'
import Img from '@/model/Img'
import UserImg from '@/model/UserImg'

  @toNative
@Component
export default class Review extends Vue {
    name = ''
    employeeID = ''

    addAdminUser() {
      if (this.name && this.employeeID) {
        /* this.$post('adminUser/add', {
          name: this.name,
          employeeID: this.employeeID
        }).then(res => {
          this.$message.success('新增成功')
        })*/
      }
    }
}
</script>
