<template>
  <div class="w100p bg-white">
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
  </div>
</template>

<script lang="ts">
import {Component, Emit, Model, Prop, Vue, Watch} from 'vue-facing-decorator';
import CommonEventUtil from "qingjs/src/util/CommonEventUtil.ts";
import SocialuniImEventKey from "socialuni-im-api/src/constant/SocialuniMusicEventConst.ts";
import socialuniMusicStore from "socialuni-music-sdk/src/store/SocialuniMusicStore.ts";
import AlertUtil from "qingjs-h5/src/util/AlertUtil.ts";
import {nextTick} from "vue";
import MusicPlayerSongInfoRO from "socialuni-music-sdk/src/model/MusicPlayerSongInfoRO.ts";
import MusicPlayerSongPlayingInfoRO from "socialuni-music-sdk/src/model/MusicPlayerSongPlayingInfoRO.ts";
import ToastUtil from "qingjs-h5/src/util/ToastUtil.ts";
import DateUtil from "qing-util/src/util/DateUtil.ts";

@Component({
  components: {}
})
export default class MusicList extends Vue {

}
</script>
