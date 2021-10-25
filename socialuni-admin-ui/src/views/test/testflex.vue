<template>
  <el-container fluid>
    <div class="flex-row w100r h100">
      <div class="flex-none w100 bg-red">1</div>
      <div class="flex-auto bg-yellow flex-row">
        <div class="h50 w100 bg-green"></div>
        <div class="h50 w100 bg-green"></div>
        <div class="h50 w100 bg-green"></div>
        <div class="h50 w100 bg-green"></div>
        <div class="h50 w100 bg-green"></div>
        <div class="h50 w100 bg-green"></div>
        <div class="h50 w100 bg-green"></div>
        <div class="h50 w100 bg-green"></div>
        <div class="h50 w100 bg-green"></div>
        <div class="h50 w100 bg-green"></div>
        <div class="h50 w100 bg-green"></div>
        <div class="h50 w100 bg-green"></div>
        <div class="h50 w100 bg-green"></div>
      </div>
      <div class="flex-none w100 bg-red">3</div>
    </div>
  </el-container>
</template>

<script lang="ts">
  import {Vue, Component, Prop} from 'vue-property-decorator'
  import ReportAPI from '@/api/ReportAPI'
  import Talk from '@/model/talk/Talk'
  import ReportVO from '@/model/report/ReportVO'
  import ViolateType from '../audit/ViolateType'
  import TalkAPI from '@/api/TalkAPI'

  @Component
  export default class TestFlex extends Vue {


  }
</script>

<style scoped>

</style>
<!--<div class="h50 w100 bg-danger"></div>
      <div class="h50 w100 bg-danger"></div>
      <div class="h50 w100 bg-danger"></div>
      <div class="h50 w100 bg-danger"></div>
      <div class="h50 w100 bg-danger"></div>
      <div class="h50 w100 bg-danger"></div>
      <div class="h50 w100 bg-danger"></div>
      <div class="h50 w100 bg-danger"></div>
      <div class="h50 w100 bg-danger"></div>
      <div class="h50 w100 bg-danger"></div>
      <div class="h50 w100 bg-danger"></div>-->
