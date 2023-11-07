<template>
  <br>
  <section>
    <el-input
        :clearable="true"
        type="textarea"
        placeholder="请输入内容"
        v-model="value"
        maxlength="140"
        show-word-limit>
    </el-input>
    <div class="button">
      <el-button type="success" round>评论</el-button>
    </div>
    <el-descriptions title="精彩评论" direction="vertical" :column="2">
      <template v-for="item in comment" :key="item.commentId">
        <el-descriptions-item>
          <main class="left">
            <div><el-avatar :size="50" :src="item.user.avatarUrl"></el-avatar></div>
            <div class="comment">
              <p style="font-size: 12px;"><span class="user">{{item.user.nickname}}: </span>{{item.content}}</p>
              <p class="time">{{$formatTime(item.time)}}</p>
            </div>
          </main>
        </el-descriptions-item>
        <el-descriptions-item align="right">
          <div class="right">
            <i class="iconfont icon-zan"><span class="good">{{item.likedCount}}</span></i>
            <span class="solid">|</span><i class="iconfont icon-fenxiang1"></i>
            <span class="solid">|</span><i class="iconfont icon-pinglun"></i>
          </div>
        </el-descriptions-item>
      </template>
    </el-descriptions>
  </section>
  <div class="pages">
    <el-pagination background
                   @current-change="change"
                   layout="prev, pager, next"
                   :total="1000">

    </el-pagination>
  </div>
</template>

<script setup>
defineProps({
  comment:{
    type:Array
  }
})
import {ref} from 'vue'
let value = ref()

const emit = defineEmits(['pageChange'])
const change = value => {
  emit('pageChange',value)
}
</script>

<style scoped lang="scss">
.pages{
  display:  flex;
  justify-content: center;
}
section{
  .button{
    margin-top: 5px;
    text-align: right;
  }
  &:deep(.el-textarea__inner){
    min-height: 80px !important;
  }
  .left{
    height: 70px;
    display: flex;
    justify-content: flex-start;
    align-items: center;
    .comment{
      margin-left: 10px;
      display: flex;
      align-items: center;
      .user{
        color: #85b9e6;
        font-size: 12px;
      }
      .time{
        color: #949191;
        font-size: 12px;
      }
    }
  }
  .right{
    display: flex;
    align-items: center;
    justify-content: flex-end;
    .good{
      font-size: 12px;
    }
    .solid{
      color: silver;
    }
    .iconfont{
      font-size: 20px;
      margin: 0 8px;
    }
  }
}
</style>
