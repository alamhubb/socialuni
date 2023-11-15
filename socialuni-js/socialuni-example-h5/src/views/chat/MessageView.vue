<template>
  <div class="flex-row h100p overflow-hidden">
    <div class="w200 bd-radius shadow h100p flex-none">
      <socialuni-chat-view-h5></socialuni-chat-view-h5>
    </div>

    <div class="flex-1 overflow-hidden h100p bg-white ml-sm">
      <!--            <div class="w100p">
                      <audio id="local" :src="test1" controls="controls"
                             style="height: 200px;width: 500px;"></audio>
                      <audio id="remote" style="height: 200px;width: 500px;" controls="controls"></audio>
                  </div>-->

      <div>
        <div></div>
        <div>
          <el-button
              @click="terstfasd('https://cdxapp-1257733245.file.myqcloud.com/opentest/M800000puzgO0yRX1o.mp3')">
            创建
          </el-button>
          <el-button @click="terstfasd111">查询</el-button>
          <el-button @click="deleteYun111">停止</el-button>
          <el-button @click="jixuYun111">继续</el-button>
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
import SocialuniMusicAPI from "@socialuni/socialuni-music-sdk/src/api/SocialuniMusicAPI";
import CommonEventUtil from "@socialuni/socialuni-native-util/src/util/CommonEventUtil";
import SocialuniImEventKey from "@socialuni/socialuni-im-api/src/constant/SocialuniMusicEventConst";
import socialuniMusicStore from "@socialuni/socialuni-music-sdk/src/store/SocialuniMusicStore";
import socialuniUserRequest from "@socialuni/socialuni-user-api/src/request/socialuniUserRequest";

@Component({
  components: {SocialuniChatViewH5, SocialuniMsgViewH5}
})
export default class MessageView extends Vue {
  tableData = []

  mounted() {
    this.queryMusicList()

    console.log(this.$route.query)
    console.log('触发通知')
    CommonEventUtil.emit(SocialuniImEventKey.socialuniImPageInit, this.$route.query)
  }

  async queryMusicList() {
    console.log('删除即构diamante')
    console.log('删除即构diamante')
    const data = await musicRequest.get('https://music-api.heheda.top/playlist/detail?_t=1699363992&id=3778678') as any
    console.log(data)
    console.log(data.playlist)
    console.log(data.playlist.tracks)
    this.tableData = data.playlist.tracks
  }


  songId = null

  handleCurrentChange(row) {
    console.log(row)
    this.songId = row.id
    // mucisRoomStore.publish(this.songId)
    this.terstfasd(this.songId)
  }


  terstfasd(url) {
    console.log(1111)
    console.log(socialuniMusicStore.channelName)
    console.log(222)
    SocialuniMusicAPI.playMusicAPI(socialuniMusicStore.channelName, {musicId: url, musicToken: socialuniMusicStore.musicToken})
    // mucisRoomStore.reqquetest(url)
  }

  terstfasd111() {
    // socialuniUserRequest.get('https://api.sd-rtn.com/api/dev/v1/channel/user/5e681410a7434ce9bba3e268226ce537/51b26fe57a9d4d148d9b7df536eeebfa')

    musicRequest.get(`api/dev/v1/channel/user/5e681410a7434ce9bba3e268226ce537/51b26fe57a9d4d148d9b7df536eeebfa`, {
      headers: {
        Authorization: "Basic MWE5N2IyZmU3NjY2NGVmNjhiZmRkZjcyNTZjZjkxZDM6OTk5YzA2ODljYzc5NDEyOGI0NTBjMWQ3MDJmMGUyZjM="
      }
    })

    SocialuniMusicAPI.queryMusicChannel('51b26fe57a9d4d148d9b7df536eeebfa')
  }


  deleteYun111() {
    SocialuniMusicAPI.updateMusicAPI({
      isPause: true
    })
  }

  jixuYun111() {
    SocialuniMusicAPI.updateMusicAPI({
      isPause: false
    })
  }

}
</script>
