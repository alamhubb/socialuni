<template>
  <el-skeleton :loading="!Boolean(loading)" :count="count" :animated="true" style="display: flex;justify-content: space-between;flex-wrap: wrap;">
      <template #template>
        <div style="margin-top: 10px;">
          <el-skeleton-item variant="image" :style="size"/>
          <el-skeleton-item variant="p" :style="{width:size.width}" style="margin: 10px 0;"/>
          <br>
          <el-skeleton-item variant="p" :style="{width:size.width}" v-if="show"/>
        </div>
      </template>
      <template #default>
        <slot/>
      </template>
    </el-skeleton>
</template>

<script setup>
const props = defineProps({
  count:{
    type:Number,
    default:1
  },
  loading:{
    type:Number,
    default:0
  },
  size:{
    type:Object,
    default:{
      width: "180px",
      height: "180px"
    }
  },
  show:{
    type:Boolean,
    default:true
  }
})
</script>

<style scoped lang="scss">

</style>
