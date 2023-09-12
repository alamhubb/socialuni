<template>
    <nav class="item">
      <el-row class="item-newSong" align="middle">
        <el-col :span="3">
          <div class="cover">
            <el-image :src="image" class="image"></el-image>
          </div>
        </el-col>
        <el-col :span="10">
          <div>{{name}}</div>
        </el-col>
        <el-col :span="9">
          <div class="label">{{label}}</div>
        </el-col>
        <el-col :span="2">
          <div class="label">{{album}}</div>
        </el-col>
      </el-row>
    </nav>
</template>

<script setup>
defineProps({
  image:{
    type:String
  },
  name:{
    type:String
  },
  label:{
    type:[String,Number]
  },
  album:{
    type:[String,Number]
  }
})
</script>

<style scoped lang="scss">
.iconfont{
  color: red;
}
.label{
  color: #656161;
}
.header{
  display: flex;
  justify-content: space-between;
  margin-bottom: 20px;
}
.item-newSong{
  margin-top: 5px;
}
.item :hover{
  background: #ededed;
  border-radius: 10px;
}

.cover{
  width: 80px;
  height: 80px;
  position: relative;
  .icon{
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%,-50%);
    width: 30px;
    height: 30px;
    background: white;
    border-radius: 50%;
  }
  .image{
    width: 80px;
    height: 80px;
    border-radius: 10px;
  }
}
</style>
