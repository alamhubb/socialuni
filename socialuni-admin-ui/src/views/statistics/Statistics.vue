<template>
  <el-container fluid>

    <el-row class="px-5">
      <el-col cols="12">
        <el-sparkline
          :value="counts"
          color="blue"
          line-width="0.4"
          padding="0"
        />
        <el-row class="px-3">
          <div v-for="i in dates" class="flex-auto">
            {{ i }}/
          </div>
        </el-row>
      </el-col>
    </el-row>
  </el-container>
</template>

<script lang="ts">
import { Vue, Component, Prop } from 'vue-property-decorator'
import ReportAPI from '@/api/ReportAPI'
import Talk from '@/model/talk/Talk'
import ReportVO from '@/model/report/ReportVO'
import ViolateType from '../audit/ViolateType'
import TalkAPI from '@/api/TalkAPI'
import Identity from '@/model/user/Identity'
import ImgIdentityAPI from '@/api/ImgIdentityAPI'

  @Component
export default class StatisticsVue extends Vue {
    talks: any[] = []
    num = 30

    created() {
      TalkAPI.queryTalkStatistics().then((res: any) => {
        this.talks = res.data
      })
    }

    get dates() {
      return this.talks.filter((item, index) => index % 7 == 0).map(item => new Date(item[1])
        .toLocaleDateString()
        .split('/').slice(1, 3)
        .reduce((pre, cur) => pre + ',' + cur))
    }

    get counts() {
      return this.talks.map(item => item[0])
    }
}
</script>

<style scoped>

</style>
