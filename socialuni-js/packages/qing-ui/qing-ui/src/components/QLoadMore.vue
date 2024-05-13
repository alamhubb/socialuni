<template>
  <div class="row-all-center">
    <q-loading v-show="status === loadMoreType.loading"></q-loading>
    <span  v-show="status === loadMoreType.more">点击加载更多</span>
    <span  v-show="status === loadMoreType.noMore">没有更多数据了,点击刷新</span>
  </div>
</template>

<script lang="ts">
import {Emit, Component, Prop, Vue, toNative} from 'vue-facing-decorator'
import QLoading from "./QLoading.vue";
import LoadMoreType from "socialuni-constant/constant/LoadMoreType";

@toNative
@Component({
  components: {QLoading}
})
export default class QLoadMore extends Vue {

  @Prop({default: 'more', type: String}) status: string

  loadMoreType: LoadMoreType = LoadMoreType
}
</script>
