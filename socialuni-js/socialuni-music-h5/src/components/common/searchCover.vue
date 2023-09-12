<template>
  <section>
    <div class="item" v-for="item in data" :key="item.id" @click="goDetail(item.id)">
      <div class="left">
         <el-avatar shape="square" :size="50" :src="item.picUrl"></el-avatar>
         <span class="name">{{item.name}} <span v-if="item.alias?.length">-</span> <template v-for="(v,i) in item.alias" :key="i">{{v}}</template> </span>
      </div>
      <div v-if="item.artist?.name" style="color: #656161;">{{item.artist.name }}</div>
      <div class="right" v-if="item.accountId">
          <i class="el-icon-user"></i>
      </div>
    </div>
  </section>
</template>

<script setup>
defineProps({
  data:{
    type:Array
  }
})

const emit = defineEmits(['goDetail'])

const goDetail = id => {
  emit('goDetail',id)
}

</script>

<style scoped lang="less">
  .item{
    width: 100%;
    height: 60px;
    display: flex;
    align-items: center;
    justify-content: space-between;
    margin-top: 10px;
    &:hover{
      background: #ededed;
    }
    .left{
      display: flex;
      align-items: center;
    }
    .name{
      padding-left: 10px;
    }
    .right{
      padding-right: 20px;
      i{
        font-size: 30px;
        color: red;
      }
    }
  }
</style>