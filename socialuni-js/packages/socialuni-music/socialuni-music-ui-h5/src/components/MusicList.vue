<template>
  <div class="h100p">
    <el-table ref="table" height="100%" :data="data" @row-dblclick="rowDbClick" :highlight-current-row="highlight">
      <el-table-column prop="musicName" label="歌曲名" width="180"/>
      <el-table-column prop="author" label="歌手" width="180"/>
      <el-table-column prop="musicTime" label="时长">
        <template #default="{row}">
          {{formatTooltip(row.musicTime)}}
<!--          {{row.musicTime}}-->
        </template>
      </el-table-column>
      <el-table-column prop="musicTime" label="更多">
        <template #default="{row}">
          {{formatTooltip(row.musicTime)}}
          <!--          {{row.musicTime}}-->
        </template>
      </el-table-column>
    </el-table>
  </div>
  <!--  <div class="w100p bg-white px-15 cursor-none">-->
  <!--  <div class="h100p overflow-auto pt-sm" v-infinite-scroll="scrollToLower" :infinite-scroll-immediate="false"
         :infinite-scroll-distance="200"
         :infinite-scroll-delay="200"
    >
      <div class="pd">
        <div class="bt">
          <div v-for="item in data" @dblclick="change(item)">
            <div class="flex-row bb  py-sm">
              <div class="br">
                {{ item.name }}
              </div>
              <div>
                {{ item.author.join(',') }}
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>-->
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
</template>

<script lang="ts">
import {Component, Emit, Model, Prop, Vue, Watch, toNative} from 'vue-facing-decorator';
import MusicPlayerSongInfoRO from "socialuni-music-sdk/src/model/MusicPlayerSongInfoRO";
import QDialog from "qingjs-ui-h5/src/components/QDialog.vue";
import MsgInput from "socialuni-ui/src/components/MsgInput.vue";
import TalkItem from "socialuni-community-ui/src/components/talkItem/TalkItem.vue";
import QTag from "qingjs-ui/src/components/QTag.vue";
import TalkAddView from "socialuni-community-view-h5/src/views/TalkAddView.vue";
import QEnumLink from "qingjs-ui-h5/src/components/QEnumLink.vue";
import QLoadMore from "qingjs-ui/src/components/QLoadMore.vue";
import QIcon from "qingjs-ui/src/components/QIcon.vue";
import MusicPlayerSongPlayingInfoRO from "socialuni-music-sdk/src/model/MusicPlayerSongPlayingInfoRO";
import DateUtil from "qing-util/src/util/DateUtil";


@Component({
  components: {QIcon, QLoadMore, QEnumLink, TalkAddView, QTag, TalkItem, MsgInput, QDialog}
})
class MusicList extends Vue {
  $refs: {
    table: ElTable
  }
  @Prop() data: MusicPlayerSongInfoRO[]
  @Prop() curMusic: MusicPlayerSongPlayingInfoRO


  @Watch('curMusic')
  curMusicWatch() {
    if (this.data.length) {
      const tableData = this.data.find(item => item.musicUrl === this.curMusic.musicUrl)
      this.$refs.table?.setCurrentRow(tableData)
    }
  }

  scrollToLower() {

  }

  rowDbClick(a) {
    this.change(a)
  }

  formatTooltip(value: number) {
    const time = Math.ceil((value / 1000)) * 1000
    return DateUtil.convertToTime(time)
  }

  @Emit()
  change(musicInfo: MusicPlayerSongInfoRO): MusicPlayerSongPlayingInfoRO {
    return new MusicPlayerSongPlayingInfoRO({...musicInfo})
  }

  highlight(...args) {
    console.log(args)
  }
}
export default toNative(MusicList)
</script>
