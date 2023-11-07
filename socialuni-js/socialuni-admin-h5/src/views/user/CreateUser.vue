<template>
  <div class="h100p">
    <div class="flex-row">

      <s-label-item label="昵称">
        <el-input v-model="nickname"></el-input>
      </s-label-item>

      <s-label-item label="手机号">
        <el-input v-model="phoneNum"></el-input>
      </s-label-item>

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
import SDialog from "@socialuni/socialuni-ui-h5/src/components/SComponents/SDialog.vue";
import SLabelItem from "@socialuni/socialuni-ui-h5/src/components/SComponents/SLabelItem.vue";
import STable from "@socialuni/socialuni-ui-h5/src/components/SComponents/STable.vue";
import STableColumn from "@socialuni/socialuni-ui-h5/src/components/SComponents/STableColumn/STableColumn.vue";
import STableInput from "@socialuni/socialuni-ui-h5/src/components/SComponents/STableInput/STableInput.vue";
import STableSelect from "@socialuni/socialuni-ui-h5/src/components/SComponents/STableSelect/STableSelect.vue";
import {Plus} from '@element-plus/icons-vue'
import SSelect from "@socialuni/socialuni-ui-h5/src/components/SComponents/SSelect/SSelect.vue";
import ImgFileVO from "@socialuni/socialuni-admin-api/src/model/community/ImgFileVO";
import SocialuniCustomUserAPI from "@socialuni/socialuni-peiwan-api/src/api/SocialuniCustomUserAPI";
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
