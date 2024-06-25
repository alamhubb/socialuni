<template>
  <div class="h100p flex-col">
    <div class="row-col-center mb">
    </div>
    <div v-for="(config,index) in configs" class="row-col-center mb-sm">
      <div class="w30p mr">
        <el-input v-model="config.configKey"></el-input>
      </div>
      <div class="w60p mr-sm">
        <el-input v-model="config.value"></el-input>
      </div>
      <el-button @click="delConfigs(config)">设为默认值</el-button>
      <el-button @click="saveConfigs(config)">保存</el-button>
    </div>
  </div>
</template>

<script lang="ts">
import {Component, Vue, toNative} from 'vue-facing-decorator'
import SocialuniAdminConfigAPI from "socialuni-admin-api/src/api/SocialuniAdminConfigAPI";

@toNative
@Component({})
export default class ConfigManageView extends Vue {
  configs: ConfigVO[] = []

  created() {
    this.queryConfigs()
  }

  async queryConfigs() {
    const res = await SocialuniAdminConfigAPI.getAllConfigsAPI()
    this.configs = res.data;
  }
  delConfigs(row:ConfigVO) {
    // request.post('config/deleteById', row).then(res => {
    //     this.queryConfigs();
    // });

  }
  saveConfigs(row:ConfigVO){
    SocialuniAdminConfigAPI.saveConfig(row)
    //this.configs保存到后台
    // request.post('config/save', row).then(res => {
    //     this.queryConfigs();
    // });
  }
}
</script>

<style scoped>

</style>
