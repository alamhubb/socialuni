<template>
      <!-- 精选歌单入口展示 -->
    <div class="highqualityEntry">
      <el-image :src="songList[0]?.coverImgUrl" class="backgroundImg"></el-image>
      <div class="cover">
        <el-image :src="songList[0]?.coverImgUrl"></el-image>
      </div>
      <div class="EntryInfo">
        <div class="tag"><i class="el-icon-service"></i> 精品歌单</div>
        <div class="name">{{songList[0]?.name}}</div>
        <div class="desc"></div>
      </div>
    </div>
</template>

<script setup>
defineProps({
  songList:{
    type:Array
  }
})

</script>

<style scoped>
.highqualityEntry {
  position: relative;
  width: 100%;
  height: 140px;
  border-radius: 10px;
  overflow: hidden;
  display: flex;
}
.backgroundImg {
  position: absolute;
  left: 0;
  top: 0;
  z-index: -1;
  width: 100%;
  transform: translateY(-80px);
  filter: blur(30px) brightness(70%);
}

.cover {
  width: 120px;
  height: 120px;
  margin: 10px;
}

.cover img {
  border-radius: 10px;
  width: 100%;
}

.tag {
  color: #d59e54;
  border: 1px solid #d59e54;
  padding: 5px 10px;
  width: 88px;
  font-size: 12px;
  margin: 20px 10px 15px;
  border-radius: 15px;
}

.tag i {
  font-size: 14px;
}

.name {
  color: white;
  font-size: 14px;
  margin: 10px 0;
}

.desc {
  color: rgba(255, 255, 255, 0.5);
  /* 字体直接写小于12没有效果 */
  font-size: 12px;
  transform: scale(0.9, 0.9);
}

.musicListNavBar {
  padding: 10px 0;
  display: flex;
  justify-content: space-between;
  font-size: 12px;
}

.page {
  width: 100%;
  text-align: center;
  padding-bottom: 20px;
}
</style>
