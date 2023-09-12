<template>
  <el-skeleton :loading="!Boolean(loading)" :count="count" :animated="true" style="width: 100%; display: flex;justify-content: space-between;flex-wrap: wrap;">
        <template #template>
          <div style="width: 24%;">
            <el-skeleton-item variant="image" style="width: 100%; height: 150px;"/>
            <el-skeleton-item variant="p" style="width: 100%; margin: 10px 0;"/>
            <el-skeleton-item variant="p" style="width: 100%;"/>
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
