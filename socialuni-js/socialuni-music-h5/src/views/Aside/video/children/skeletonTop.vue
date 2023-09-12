<template>
  <el-skeleton :loading="!Boolean(loading)" :count="count" :animated="true" style="width: 100%; display: flex;justify-content: space-between;flex-wrap: wrap;">
        <template #template>
          <div style="width: 49%;margin-top: 15px; display: flex;justify-content: flex-start;">
            <el-skeleton-item variant="image" style="width:234px; height: 130px;"/>
            <div style="margin-left: 10px; display: flex;flex-direction: column;justify-content: space-evenly;">
              <el-skeleton-item variant="h1" style="width: 321px;"/>
              <el-skeleton-item variant="h1" style="width: 250px;"/>
            </div>
          </div>
        </template>
        <template #default>
          <slot></slot>
        </template>
    </el-skeleton>
</template>

<script setup>
defineProps({
  count:{
    type:Number,
    default:1
  },
  loading:{
    type:Number,
    default:0
  }
})
</script>

<style scoped lang="less">

</style>
