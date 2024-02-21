<template>
  <div class="w100p bg-white">

<div>
  <div v-for="item in tableData">
    {{ item}}
  </div>
</div>
    <!--    <div class="flex-1 overflow-hidden">
    &lt;!&ndash;
          音量：{{ socialuniMusicStore.musicVolume }}
          <div>
            realPlayingValue：{{ realPlayingValue }}
          </div>
          <div>
            {{ modelValue }}
          </div>
    &ndash;&gt;

          <el-table height="100%" :data="data" stripe highlight-current-row
                    @row-dblclick="handleCurrentChange">
            <el-table-column prop="title" label="音乐标题" width="100" show-overflow-tooltip></el-table-column>
            <el-table-column label="歌手" show-overflow-tooltip>
              <template #default="scope">
                {{ scope.row.author.join(' / ') }}
              </template>
            </el-table-column>
            <el-table-column label="歌手专辑" show-overflow-tooltip>
              <template #default="scope">
                {{ scope.row.album }}
              </template>
            </el-table-column>
            <el-table-column label="时长" width="100">
              <template #default="scope">
                {{ $DateUtil.convertToTime(scope.row.musicTime) }}
              </template>
            </el-table-column>
          </el-table>

        </div>-->

    歌曲列表
    <!--    <el-table height="100%" :data="data" stripe highlight-current-row
                  @row-dblclick="handleCurrentChange">
          <el-table-column prop="title" label="音乐标题" width="100" show-overflow-tooltip></el-table-column>
          <el-table-column label="歌手" show-overflow-tooltip>
            <template #default="scope">
              {{ scope.row.author.join(' / ') }}
            </template>
          </el-table-column>
          <el-table-column label="歌手专辑" show-overflow-tooltip>
            <template #default="scope">
              {{ scope.row.album }}
            </template>
          </el-table-column>
          <el-table-column label="时长" width="100">
            <template #default="scope">
              {{ $DateUtil.convertToTime(scope.row.musicTime) }}
            </template>
          </el-table-column>
        </el-table>-->
  </div>
</template>

<script lang="ts">
import {Component, Emit, Model, Prop, Vue, Watch} from 'vue-facing-decorator';
import musicRequest from "socialuni-music-sdk/src/plugins/musicRequest";

@Component({
  components: {}
})
export default class MusicList extends Vue {

  created(){
    this.queryMusicList()
  }

  tableData = []

  async queryMusicList() {
    const data = await musicRequest.get('/playlist/detail?_t=1699363992&id=3778678') as any
    this.tableData = data.playlist.tracks
  }

}
</script>
