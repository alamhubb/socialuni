<template>
  <div class="w100p bg-white px-15">
    <div>
      <div v-for="item in tableData">
        {{ item }}
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
import MusicPlayerSongInfoRO from "socialuni-music-sdk/src/model/MusicPlayerSongInfoRO";
import socialuniMusicStore from "socialuni-music-sdk/src/store/SocialuniMusicStore";
import SocialuniMusicRoleId from "socialuni-music-sdk/src/constant/SocialuniMusicRoleId";
import MusicPlayerSongPlayingInfoRO from "socialuni-music-sdk/src/model/MusicPlayerSongPlayingInfoRO";
import SocialuniMusicAPI from "socialuni-music-sdk/src/api/SocialuniMusicAPI";

@Component({
  components: {}
})
export default class MusicList extends Vue {


  songList: MusicPlayerSongInfoRO[] = []

  created() {
    this.querySongList()
  }

  get hasOperateAuth() {
    console.log(23232323)
    console.log(socialuniMusicStore.musicRoleId)
    return SocialuniMusicRoleId.hasOperateAuthList.includes(socialuniMusicStore.musicRoleId)
  }

  get musicRoomInfo() {
    return socialuniMusicStore.musicRoomInfo
  }

  //查询歌曲列表
  async querySongList() {
    const data = await musicRequest.get('/playlist/detail?id=3778678') as any
    const songList: MusicPlayerSongInfoRO[] = data.playlist.tracks.map((row: any) => {
      const songRO: MusicPlayerSongInfoRO = {
        songId: row.id,
        title: row.name,
        author: row.ar?.map(item => item.name),
        album: row.al.name,
        albumImg: row.al.picUrl,
        musicTime: row.dt,
        musicUrl: `https://music.163.com/song/media/outer/url?id=${row.id}.mp3`,
      }
      return songRO
    })
    this.songList = songList
    if (this.songList.length > 0) {
      console.log(1312312)
      console.log(this.musicRoomInfo)
      if (!this.musicRoomInfo) {
        const musicRoomInfo = new MusicPlayerSongPlayingInfoRO(this.songList[0])
        musicRoomInfo.musicTime = Math.floor(musicRoomInfo.musicTime / 1000)
        console.log(this.songList[0])
        console.log(musicRoomInfo)
        console.log(this.musicRoomInfo)
        socialuniMusicStore.setMusicRoomInfo(musicRoomInfo)
        console.log(1312312)
        console.log(this.musicRoomInfo)
      }
    }
  }

  musicRoomInfoInput(musicRoomInfo: MusicPlayerSongPlayingInfoRO) {
    socialuniMusicStore.setMusicRoomInfo(musicRoomInfo)
  }

  musicRoomInfoChange(musicRoomInfo: MusicPlayerSongPlayingInfoRO) {
    //必须深拷贝，不这么写会导致一致，导致不播放
    this.musicRoomInfoInput(musicRoomInfo)
    SocialuniMusicAPI.playMusicAPI(socialuniMusicStore.channelName, musicRoomInfo).then(res => {
      const data: MusicPlayerSongPlayingInfoRO = res.data
      // 不相同才替换
      if (data.playingTime !== musicRoomInfo.playingTime
          || data.playing !== musicRoomInfo.playing
      ) {
        console.log(data.playing !== musicRoomInfo.playing)
        console.log('queshiyou buyizhi')
        socialuniMusicStore.setMusicRoomInfo(res.data)
      }
    })
  }
}
</script>
