<template>
  <el-skeleton :loading="!Boolean(loading)" :count="count" :animated="true" style="width: 100%; display: flex;justify-content: space-between;flex-wrap: wrap; align-items: center;">
        <template #template>
          <el-skeleton-item v-if="show" variant="button" style="width: 50px;height: 50px; margin-top: 10px; border-radius: 50%;"/>
          <div :style="{width,marginTop:top}" style="display: flex;justify-content: flex-start;">
            <el-skeleton-item variant="image" :style="image"/>
            <div :style="margin" style="display: flex;flex-direction: column;justify-content: space-evenly;">
              <el-skeleton-item :variant="type" v-for="item in row" :key="item"/>
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
    type:[Number,String,Boolean],
    default:0
  },
  image:{
    type:Object,
    default:{
      width:'150px',
      height:'150px'
    }
  },
  margin:{
    type:Object,
    default:{
      width:'990px',
      marginLeft: '50px',
    }
  },
  width:{
    type:Object,
    default:{
      width:'100%'
    }
  },
  type:{
    type:String,
    default:'h1'
  },
  row:{
    type:Number,
    default:3
  },
  top:{
    type:String,
    default:'15px'
  },
  show:{
    type:Boolean,
    default:false
  }
})
</script>
