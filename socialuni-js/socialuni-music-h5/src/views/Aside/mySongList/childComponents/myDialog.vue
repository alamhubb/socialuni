<template>
<el-dialog
    center
    append-to-body
    title="新建歌单"
    v-model="isShow"
    width="30%">
  <template #default>
    <el-input v-model="inputValue" placeholder="请输入新歌单标题"></el-input>
    <el-checkbox v-model="checked" label="设置为隐私歌单"></el-checkbox>
  </template>
  <template #footer>
    <span class="dialog-footer">
      <el-button @click="isShow = false">取 消</el-button>
      <el-button type="primary" @click="confirm">确 定</el-button>
    </span>
  </template>
</el-dialog>
</template>

<script setup>
import {ElMessage} from "element-plus";
import {ref} from "vue";
import {createSongList} from "@/network/topList.js";

defineProps({
  id:{
    type:Number,
    default:7217824552
  }
})
const emit = defineEmits(['create'])
let isShow = ref(false)
let inputValue = ref('')
let checked = ref(false)
const confirm = () => {
  if (inputValue.value){
    isShow.value = false
    createSongList(inputValue.value,checked.value ? 10 : '').then(res => {
      if (res.data.code === 200){
        ElMessage.success({
          type:'success',
          message:'新建歌单成功'
        })
        emit('create')
      }
    })
  }else {
    ElMessage.warning({
      type:'warning',
      message:'歌单名不能为空'
    })
  }
}

defineExpose({
  isShow
})
</script>

<style scoped lang="less">

</style>
