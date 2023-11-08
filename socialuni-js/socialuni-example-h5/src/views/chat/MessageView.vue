<template>
  <div class="flex-row h100p overflow-hidden">
    <div class="w200 bd-radius shadow h100p flex-none">
      <socialuni-chat-view-h5></socialuni-chat-view-h5>
    </div>

    <div class="flex-1 overflow-hidden h100p bg-white ml-sm">
      <div>
        <div></div>
        <div>
          <el-button @click="mucisRoomStore.leaveClick()">创建</el-button>
        </div>
      </div>

      <!--            <socialuni-msg-view-h5></socialuni-msg-view-h5>-->
      <el-table :data="tableData" stripe size="small"
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
        <el-table-column ref="dom" width="45">
          <template #default="scope">
            <!--            <i v-if="isLike(scope.row.id)" style="color: red; font-size: 14px;" class="iconfont icon-xihuan"></i>-->
            <!--            <i v-else title="喜欢" class="iconfont icon-aixin" style="font-size: 15px;"></i>-->
          </template>
        </el-table-column>
        <el-table-column width="40"><i title="下载" class="iconfont icon-xiazai" style="font-size: 15px;"></i>
        </el-table-column>
        <el-table-column prop="name" label="音乐标题" width="400">
          <template #default="scope">
            <!--            <span class="music-title" :class="{active:scope.row.id === playName}">{{ scope.row.name }}</span>-->
            <!--            <el-tag @click="toDetail(scope.row.mv)" v-if="scope.row.mv" type="danger" size="mini">MV</el-tag>-->
          </template>
        </el-table-column>
        <el-table-column label="歌手" width="275">
          <template #default="scope">
            {{ scope.row.ar?.map(item => item.name).join(' / ') }}
          </template>
        </el-table-column>
        <el-table-column label="歌手专辑" width="300">
          <template #default="scope">
            {{ scope.row.al.name }}
          </template>
        </el-table-column>
        <el-table-column prop="address" label="时长">
          <template #default="scope">
            <!--            {{ $formatTime(scope.row.dt).slice(-5) }}-->
          </template>
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script lang="ts">
import {Component, Vue} from 'vue-facing-decorator';
import SocialuniChatViewH5 from "@socialuni/socialuni-im-view-h5/src/views/SocialuniChatViewH5.vue"
import SocialuniMsgViewH5 from "@socialuni/socialuni-im-view-h5/src/views/SocialuniMsgViewH5.vue"
import musicRequest from "@/plugins/musicRequest";
import AgoraRTC from "agora-rtc-sdk-ng"
import {mucisRoomStore} from "@/views/chat/MusicRoom";


@Component({
  components: {SocialuniChatViewH5, SocialuniMsgViewH5}
})
export default class MessageView extends Vue {

  created() {
    this.queryMusicList()

  }

  tableData = []

  async queryMusicList() {
    const data = await musicRequest.get('/playlist/detail?_t=1699363992&id=3778678') as any
    console.log(data)
    console.log(data.playlist)
    console.log(data.playlist.tracks)
    this.tableData = data.playlist.tracks
  }


  songId = null

  handleCurrentChange(row) {
    console.log(row)
    this.songId = row.id
    mucisRoomStore.publish(this.songId)
  }

  get mucisRoomStore() {
    return mucisRoomStore;
  }

}
</script>
