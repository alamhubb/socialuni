<template>
  <div class="h100p row-all-center bg-white">
    <div class="h600 w600 row-wrap">
      <div v-for="i in checkerboard" class="size100 row-all-center bd">
        {{ getPieceChineseName(i) }}
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import {Component, Vue, Watch, toNative} from 'vue-facing-decorator';
import SocialuniChatViewH5 from "socialuni-im-view-h5/src/views/SocialuniChatViewH5.vue"
import SocialuniMsgViewH5 from "socialuni-im-view-h5/src/views/SocialuniMsgViewH5.vue"
import MusicPlayerSongPlayingInfoRO from "socialuni-music-sdk/src/model/MusicPlayerSongPlayingInfoRO.ts";
import socialuniMusicStore from "socialuni-music-sdk/src/store/SocialuniMusicStore.ts";
import SocialuniMusicRoleId from "socialuni-music-sdk/src/constant/SocialuniMusicRoleId.ts";
import MusicPlayerSongInfoRO from "socialuni-music-sdk/src/model/MusicPlayerSongInfoRO.ts";
import SocialuniMusicAPI from "socialuni-music-sdk/src/api/SocialuniMusicAPI.ts";
import musicRequest from "socialuni-music-sdk/src/plugins/musicRequest";
import MusicPlayer from "socialuni-music-ui-h5/src/components/MusicPlayer.vue";
import MusicList from "socialuni-music-ui-h5/src/components/MusicList.vue";
import {socialuniChatModule} from "socialuni-im-sdk/src/store/SocialuniChatModule.ts";


class PieceEnum {
  //外卖员
  static deliver = 1
  //保安
  static doorkeeper = 2
  //工人
  static worker = 3
  //卷王
  static juanwang = 4
  static pua = 5
  static hr = 6
  static boss = 7
  static diGouYou = 8
}

const numbers = new Map([
  [PieceEnum.deliver, 4],
  [PieceEnum.doorkeeper, 2],
  [PieceEnum.worker, 2],
  [PieceEnum.juanwang, 2],
  [PieceEnum.hr, 2],
  [PieceEnum.boss, 1],
  [PieceEnum.diGouYou, 2],
]);

const piece = new Map([
  [PieceEnum.deliver, '外卖员'],
  [PieceEnum.doorkeeper, '保安'],
  [PieceEnum.worker, '工人'],
  [PieceEnum.juanwang, '卷王'],
  [PieceEnum.pua, 'pua'],
  [PieceEnum.hr, 'hr'],
  [PieceEnum.boss, '老板'],
  [PieceEnum.diGouYou, '地沟油'],
]);

@toNative
@Component({
  components: {MusicPlayer, SocialuniChatViewH5, SocialuniMsgViewH5, MusicList}
})
export default class BeastChess extends Vue {


  getPieceChineseName(number) {
    const num = Math.abs(number)
    const redBlue = number > 0 ? '红' : '蓝'
    if (number > 0) {
      const name = piece.get(num) + `(${redBlue})`
      return name
    }
    return '隐藏'
  }

  pieces = []

  created() {
    this.createChessboard()
  }

  createChessboard() {
    const pieceSet = []

    numbers.forEach((value, key) => {
      for (let i = 0; i < value; i++) {
        pieceSet.push(key)
        pieceSet.push(-key)
      }
    })
    this.removeRandomElementWithDuplicates(pieceSet)
  }

  checkerboard = []

  removeRandomElementWithDuplicates(collection: []) {
    const arrayLength = collection.length;

    for (let i = 0; i < arrayLength; i++) {
      const randomIndex = Math.floor(Math.random() * collection.length);
      this.checkerboard.push(collection[randomIndex])
      collection.splice(randomIndex, 1);
    }
  }

}
</script>
