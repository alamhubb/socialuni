<template>
  <div class="flex-col h100p overflow-hidden color-black row-all-center">
    <div class="h700 w500 overflow-hidden">
      <el-table class="w100p h100p" :data="tableData" stripe
                highlight-current-row
                @row-dblclick="handleCurrentChange" style="width: 100%;">
        <!--        <el-table-column width="45">
                  <template #default="scope">
                    <svg v-if="scope.row.id === playName" style="width: 15px;height: 15px;" class="svg" aria-hidden="true">
                      <use xlink:href="#icon-yangshengqi"></use>
                    </svg>
                    <span v-else>{{ scope.row.index }}</span>
                  </template>
                </el-table-column>-->
        <el-table-column ref="dom" width="45" show-overflow-tooltip>
          <template #default="scope">
            <!--            <i v-if="isLike(scope.row.id)" style="color: red; font-size: 14px;" class="iconfont icon-xihuan"></i>-->
            <!--            <i v-else title="喜欢" class="iconfont icon-aixin" style="font-size: 15px;"></i>-->
          </template>
        </el-table-column>
        <el-table-column width="40" show-overflow-tooltip><i title="下载" class="iconfont icon-xiazai" style="font-size: 15px;"></i>
        </el-table-column>
        <el-table-column prop="name" label="音乐标题" width="100" show-overflow-tooltip>
          <template #default="scope">
            <!--            <span class="music-title" :class="{active:scope.row.id === playName}">{{ scope.row.name }}</span>-->
            <!--            <el-tag @click="toDetail(scope.row.mv)" v-if="scope.row.mv" type="danger" size="mini">MV</el-tag>-->
          </template>
        </el-table-column>
        <el-table-column label="歌手" width="100" show-overflow-tooltip>
          <template #default="scope">
            {{ scope.row.ar?.map(item => item.name).join(' / ') }}
          </template>
        </el-table-column>
        <el-table-column label="歌手专辑" width="100" show-overflow-tooltip>
          <template #default="scope">
            {{ scope.row.al.name }}
          </template>
        </el-table-column>
        <el-table-column prop="address" label="时长" show-overflow-tooltip>
          <template #default="scope">
            <!--            {{ $formatTime(scope.row.dt).slice(-5) }}-->
          </template>
        </el-table-column>
      </el-table>
    </div>
    <!--    <div class="flex-row overflow-hidden flex-none">
          <div v-for="i in 20" class="flex-1 w200 h30" :class="[active===i?'bg-blue_light bb-2 bb-blue':'bg-default']"
               @click="active=i"  draggable="true">
            {{ i }}
          </div>
        </div>-->
    <!--    <div id="wrapper">
          <ul id="menuContainer">
            <li id="addMenuItem">Add another component</li>
          </ul>
          <div id="layoutContainer"></div>
        </div>-->
    <!--    <div class="flex-1 overflow-hidden bg-green_light pd" @dragover="allowDrop">
          {{ active }}
        </div>-->


  </div>
</template>

<script lang="ts">
import {Component, Vue, Watch} from 'vue-facing-decorator';
import 'golden-layout/dist/css/goldenlayout-base.css';
import 'golden-layout/dist/css/themes/goldenlayout-light-theme.css';
import musicRequest from "@/plugins/musicRequest";

@Component({
  components: {}
})
export default class MessageView extends Vue {
  created() {
    this.queryMusicList()
  }

  tableData = []

  handleCurrentChange(row) {
    // mucisRoomStore.publish(this.songId)
    // this.playMusicAPI(row.id)
  }


  async queryMusicList() {
    const res = await musicRequest.get('playlist/detail?id=3778678')
    console.log(res)
    this.tableData = res.playlist.tracks
  }
}
</script>
<style lang="scss">
</style>
