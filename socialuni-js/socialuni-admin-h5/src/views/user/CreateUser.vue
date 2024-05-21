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
import {Component, Vue, toNative} from 'vue-facing-decorator'
import QDialog from "qing-ui-h5/src/components/QDialog.vue";
import QLabelItem from "qing-ui-h5/src/components/QLabelItem.vue";
import QTable from "qing-ui-h5/src/components/QTable.vue";
import QTableColumn from "qing-ui-h5/src/components/QTableColumn/QTableColumn.vue";
import QTableInput from "qing-ui-h5/src/components/QTableInput/QTableInput.vue";
import QTableSelect from "qing-ui-h5/src/components/QTableSelect/QTableSelect.vue";
import {Plus} from '@element-plus/icons-vue'
import QSelect from "qing-ui-h5/src/components/QSelect/QSelect.vue";
import ImgFileVO from "socialuni-admin-api/src/model/community/ImgFileVO";
import SocialuniCustomUserAPI from "socialuni-peiwan-api/src/api/SocialuniCustomUserAPI";
import ToastUtil from "@/utils/ToastUtil";

@toNative
@Component({
  components: {QSelect, QDialog, Plus, QTableColumn, QTableInput, QTable, QTableSelect, QLabelItem}
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
