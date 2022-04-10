<template>
  <div class="h100p">
    <div class="flex-none flex-row">
      <el-button class="w30p h50" type="primary" :disabled="!userIdentities.length" @click="auditIdentityList">审核全部
      </el-button>
      <!--      <el-button @click="testRequet">test测试</el-button>-->
    </div>

    <data-table checked class="mt h100p overflow-hidden" height="100" :table-data="userIdentities">
      <el-table-column
        label="用户"
        width="120"
      >
        <template #default="{row}">
          <div>
            <el-image
              style="width: 100px; height: 100px"
              fit="contain"
              :src="row.user.avatar"
              :preview-src-list="[row.user.avatar]"
              :z-index="999"
            />
            <div>
              {{ row.user.id }}--{{ row.user.status }}
            </div>
            --
            <div>
              {{ row.user.nickname }}
            </div>
          </div>
        </template>
      </el-table-column>

      <el-table-column
        label="分数"
        prop="preCheckScore"
        width="100"
      />
      <el-table-column
        label="年龄"
        prop="age"
        width="50"
      />
      <el-table-column
        label="审核结果"
        width="120"
      >
        <template #default="{row}">
          <el-radio-group v-model="row.success">
            <el-radio
              :label="true"
            >通过
            </el-radio>
            <el-radio
              class="pt"
              :label="false"
            >不通过
            </el-radio>
          </el-radio-group>
        </template>
      </el-table-column>

      <el-table-column
        label="身份证"
        width="300"
      >
        <template #default="{row}">
          <div class="flex-row">
            <el-image
              style="width: 200px; height: 200px"
              fit="contain"
              :src="row.userIdImgSrc"
              :preview-src-list="[row.userIdImgSrc]"
              :z-index="999"
            />
          </div>
        </template>
      </el-table-column>
      <el-table-column
        label="自拍"
        width="300"
      >
        <template #default="{row}">
          <div class="flex-row">
            <el-image
              style="width: 300px; height: 300px"
              fit="contain"
              :src="row.userSelfieImgSrc"
              :preview-src-list="[row.userSelfieImgSrc]"
              :z-index="999"
            />
          </div>
        </template>
      </el-table-column>
    </data-table>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
import UserIdentityAuditAPI from '@/api/UserIdentityAuditAPI'
import UserIdentityAuditRO from '@/model/RO/user/identity/UserIdentityAuditRO'
import DataTable from '@/components/dataTable/DataTable.vue'

@Component({
  components: { DataTable }
})
export default class UserIdentityAuditView extends Vue {
  userIdentities: UserIdentityAuditRO[] = []

  created() {
    this.queryTableData()
    /* TestAPI.testKeywordsAPI(this.content).then(res => {
      this.talks = res.data
    })*/
  }

  async queryTableData() {
    this.userIdentities = []
    const res = await UserIdentityAuditAPI.queryUserIdentityAuthAuditListAPI()
    this.userIdentities = res.data
  }

  async auditIdentityList() {
    await UserIdentityAuditAPI.auditUserIdentityListAPI(this.userIdentities)
    this.queryTableData()
  }
}
</script>

<style scoped>

</style>
