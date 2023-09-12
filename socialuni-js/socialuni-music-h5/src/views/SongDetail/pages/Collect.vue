<template>
  <br>
  <section class="collect">
    <main v-for="item in collect" :key="item.userId">
      <div>
        <el-avatar :size="100" :src="item.avatarUrl"></el-avatar>
      </div>
      <div class="user">
        <p>{{item.nickname}}</p>
        <p class="label">{{item.signature}}</p>
      </div>
    </main>
  </section>
  <br>
  <div class="pages">
    <el-pagination v-if="collect.length" background
                   @current-change="change"
                   layout="prev, pager, next"
                   :total="total">
    </el-pagination>
  </div>
</template>

<script setup>
import {computed, onMounted,ref} from "vue";
import {useStore} from "vuex";
import {getSongListCollect} from "@/network/songList.js";


const store = useStore()
const id = computed(() => store.state.songDetail.commentID)

let collect = ref([])
let params = {
  id:id.value,
  limit:15,
  offset:0
}
let total = ref()
onMounted(async () => {
  let res = await getSongListCollect(params)
  collect.value = res.data.subscribers
  total.value = res.data.total
})
const change = value => {
  params.offset = value
  getSongListCollect(params).then(res => {
    collect.value = res.data.subscribers
  })
}
</script>

<style scoped lang="scss">
.collect{
  display: flex;
  justify-content: space-between;
  flex-wrap: wrap;
  main{
    display: flex;
    justify-content: flex-start;
    align-items: center;
    width: 33%;
    height: 100%;
    margin: 10px 0;
    .user{
      margin-left: 15px;
      .label{
        color: silver;
        font-size: 13px;
      }
    }
  }
}

.pages{
  width: 100%;
  display: flex;
  justify-content: center;
}
</style>
