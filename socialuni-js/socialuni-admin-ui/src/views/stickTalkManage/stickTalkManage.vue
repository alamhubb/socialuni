<template>
  <div class="h100p flex-col">
    <div class="row-between-center flex-none px py-sm bb">
      <div class="font-bold font-16">
        置顶动态管理
      </div>
      <div>
        <y-button type="primary" @click="saveData">保存</y-button>
      </div>
    </div>

    <y-scrollbar class="flex-1">
      <div class="pd-sm mr-sm">
        <y-table :data="talkDataCom">
          <el-table-column width="140">
            <template #default="{row}">
              <div class="row-all-center">
                <div v-if="row.globalTop>0">
                  <el-button type="success" @click="row.globalTop = 0">取消置顶</el-button>
                </div>
                <div v-else>
                  <el-button type="danger" plain @click="row.globalTop = 1">置顶</el-button>
                </div>
              </div>
            </template>
          </el-table-column>
          <y-table-input label="置顶级别" prop="globalTop"  width="140"></y-table-input>
          <y-table-column label="动态" prop="content"></y-table-column>
          <y-table-column label="发表时间" prop="createTime"  width="140"
                          :prop-fun="(label,item)=>new Date(item.createTime).toLocaleString()"
          ></y-table-column>
        </y-table>
      </div>
    </y-scrollbar>
  </div>
</template>

<script lang="ts">
import { Component, Vue , toNative} from 'vue-facing-decorator'
import request from '@/plugins/request'
import YTableInput from '@/components/YComponent/YTableInput/YTableInput.vue'
import YTable from '@/components/YComponent/YTable/YTable.vue'
import YFormInput from '@/components/YComponent/YFormInput/YFormInput.vue'
import YFormSelect from '@/components/YComponent/YFormSelect/YFormSelect.vue'
import ToastUtil from '@/utils/ToastUtil'
import TalkVO from '@/model/community/TalkVO'


@Component({
  components: { YFormSelect, YFormInput, YTable, YTableInput }
})
class StickTalkManageView extends Vue {
  tableData: TalkVO[] = []

  get talkDataCom() {
    return this.tableData.sort((a, b) => b.globalTop - a.globalTop)
  }

  created() {
    this.queryData()
  }

  async queryData() {
    // const res = await request.get('socialuni/admin/stickTalk/updateAllDevPhoneNumUser')
    const res = await request.get('socialuni/admin/stickTalk/querySystemUserTalks')
    this.tableData = res.data
  }

  saveData() {
    //this.configs保存到后台
    request.post('socialuni/admin/stickTalk/updateStickTalks', this.tableData).then(res => {
      ToastUtil.success('更新成功')
      this.queryData()
    })
  }
}
export default toNative(StickTalkManageView)
</script>

<style scoped>

</style>
