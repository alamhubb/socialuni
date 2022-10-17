<template>
  <div class="h100p flex-col">
    <div class="row-col-center mb">
      <el-button @click="addConfig">新增</el-button>
    </div>
    <div v-for="config in configs" class="row-col-center mb-sm">
      <div class="w30p mr">
        <el-input v-model="config.configKey"></el-input>
      </div>
      <div class="w60p">
        <el-input v-model="config.value"></el-input>
      </div>
    </div>
  </div>
</template>

<script lang="tsx">
import {Component, Vue} from 'vue-property-decorator'
import request from "@/plugins/request";
import ConfigVO from "@/model/config/ConfigVO";

@Component
export default class ConfigManageView extends Vue {
  configs: ConfigVO[] = []

  created() {
    this.queryConfigs()
  }

  async queryConfigs() {
    const res = await request.get('config/getAllConfigs')
    this.configs = res.data
  }

  addConfig() {
    this.configs.unshift(new ConfigVO())
  }
}
</script>

<style scoped>

</style>
