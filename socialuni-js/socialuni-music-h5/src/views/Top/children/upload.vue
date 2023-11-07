<template>
  <el-dialog
      title="头像上传"
      append-to-body
      v-model="dialogVisible"
      center
      width="25%">
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="dialogVisible = false">确 定</el-button>
      </span>
    </template>
    <div style="display: flex;justify-content: center;align-items: center;">
      <img v-if="src" style="width: 148px; height: 148px; margin: 0 10px;" :src="src" alt="">
      <el-upload

          action="https://jsonplaceholder.typicode.com/posts/"
          list-type="picture-card"
          :on-progress="upload">
        <i class="el-icon-plus"></i>
      </el-upload>
    </div>
  </el-dialog>
</template>

<script setup>
import {ref} from "vue";

let dialogVisible = ref(false)

let src = ref()
const upload = (event, file, fileList) => {
  console.log(file)
  src.value = file.url
}

defineExpose({
  dialogVisible
})
</script>

<style scoped lang="scss">

</style>
