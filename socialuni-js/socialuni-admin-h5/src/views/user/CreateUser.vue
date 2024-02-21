<template>
  <div class="h100p">
    <div class="flex-row">

      <q-label-item label="昵称">
        <el-input v-model="nickname"></el-input>
      </q-label-item>

      <q-label-item label="手机号">
        <el-input v-model="phoneNum"></el-input>
      </q-label-item>

      <div class="flex-1 row-end">
        <el-button @click="commonSava">保存</el-button>
      </div>
    </div>

    <div class="mt-50">
      {{ user }}
    </div>
  </div>
</template>

<script lang="ts">
import {Component, Vue} from 'vue-facing-decorator'
import QDialog from "qing-ui-h5/src/components/QComponents/QDialog.vue";
import SLabelItem from "qing-ui-h5/src/components/QComponents/QLabelItem.vue";
import STable from "qing-ui-h5/src/components/QComponents/QTable.vue";
import STableColumn from "qing-ui-h5/src/components/QComponents/QTableColumn/STableColumn.vue";
import STableInput from "qing-ui-h5/src/components/QComponents/QTableInput/STableInput.vue";
import STableSelect from "qing-ui-h5/src/components/QComponents/QTableSelect/STableSelect.vue";
import {Plus} from '@element-plus/icons-vue'
import SSelect from "qing-ui-h5/src/components/QComponents/QSelect/SSelect.vue";
import ImgFileVO from "socialuni-admin-api/src/model/community/ImgFileVO";
import SocialuniCustomUserAPI from "socialuni-peiwan-api/src/api/SocialuniCustomUserAPI";
import ToastUtil from "@/utils/ToastUtil";

@Component({
  components: {SSelect, SDialog, Plus, STableColumn, STableInput, STable, STableSelect, SLabelItem}
})
export default class CreateUserView extends Vue {

  nickname = null
  phoneNum = null
  user = null

  async commonSava() {
    if (!this.nickname || !this.phoneNum) {
      ToastUtil.throwError('不能为空')
    }
    const res = await SocialuniCustomUserAPI.createUserAPI(this.nickname, this.phoneNum)
    this.user = res.data
    ToastUtil.success('创建成功')
  }

}
</script>
