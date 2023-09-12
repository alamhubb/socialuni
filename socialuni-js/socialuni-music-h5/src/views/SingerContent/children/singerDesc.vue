<template>
    <header>
    <div><el-image class="cover" :src="singerDetail.artist.cover" alt="img"></el-image></div>
    <div class="content">
      <div class="A"><h2>{{singerDetail.artist.name}}</h2></div>
      <div class="B">
        <el-link type="info">{{singerDetail.identify?.imageDesc}}</el-link>
      </div>
      <div class="C">
        <el-button size="small" round icon="el-icon-folder-add">收藏</el-button>
        <el-button size="small" round icon="el-icon-user">个人主页</el-button>
      </div>
      <div class="D">
        <p><span style="margin-right: 25px;" v-for="item in singerDetail.secondaryExpertIdentiy"
                 :key="item.expertIdentiyId">
          {{item.expertIdentiyName}}:{{item.expertIdentiyCount}}</span>
        </p>
        <el-collapse>
          <el-collapse-item title="点击展开更多">
            {{singerDetail.artist.briefDesc}}
          </el-collapse-item>
        </el-collapse>
      </div>
    </div>
  </header>

</template>

<script setup>
defineProps({
  singerDetail:{
    type:Object
  }
})
</script>

<style scoped lang="less">
header{
  display: flex;
  justify-content: flex-start;
  padding: 10px;
  width: 100%;
  .cover{
    display: block;
    height: 200px;
    width: 210px;
    border-radius: 10px;
  }
  .content{
    margin-left: 20px;
    .A{
      width: 100%;
      height: 40px;
      display: flex;
      align-items: center;
      h2{
        display: inline;
        margin-left: 10px;
      }
    }
    .B{
      width: 100%;
      height: 50px;
      display: flex;
      align-items: center;
      span{
        font-size: 14px;
        color: #748aad;
      }
      a{
        margin: 0 7px;
        text-decoration:none
      }
    }
    .D{
      margin-left: 5px;
      .tags{
        color: #85b9c8;
      }
      p{
        font-size: 12px;
        color: #656161;
      }
    }
    
  }
}
</style>
