<template>
  <div class="h100p flex-row bg-white">
    <div class="pd">
      <div class="flex-row">
        <div v-for="key in reversNumbers">{{ getChineseName(key) }}></div>
        <div>{{ getChineseName(1) }}, {{ getChineseName(1) }}>{{ getChineseName(reversNumbers[0]) }}</div>
        ,
        <div>{{ getChineseName(numbers[0]) }}可与任意棋子同归于尽</div>
      </div>
      <div>
        {{ checkerboard }}
      </div>
      <div>
        该红方先走,{{ countDown }}---{{ chooseIndex }}
      </div>

      <div @click="countDown.computedCountDown">fasdf</div>

    </div>
    <div class="h100p row-col-center ml">

      <div class="h500 w600 row-wrap">
        <div v-for="(level,index) in checkerboard" class="size100 row-all-center bd color-white"
             :class="['bg-'+getColor(level)+'', chooseIndex===index?'bd-dashed bg-active':'',canItBeTarget(level,index)?'opacity':'']"
             @click="choosePiece(index)">
          {{ getPieceChineseName(level) }}--{{ index }}
        </div>
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
import CountDownUtil from "@/util/CountDownUtil.ts";
import {reactive} from "vue";


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


@Component({
  components: {}
})
class BeastChess extends Vue {

  self = 1

  isSelf(index) {
    return (this.checkerboard[index] * this.self) > -1
  }


  choosePiece(index: number) {
    if (this.isSelf(index)) {
      this.chooseIndex = index
    }
  }

  chooseIndex = null

  countDown = new CountDownUtil(30)

  canItBeTarget(level: number, index: number) {
    if (this.chooseIndex || this.chooseIndex === 0) {
      const top = this.chooseIndex - 6
      const bottom = this.chooseIndex + 6
      const left = this.chooseIndex - 1
      const right = this.chooseIndex + 1
      //相乘为负数才为对方颜色
      const topCanBeTarget = (top > -1) && ((this.checkerboard[top] * this.checkerboard[this.chooseIndex]) < 1)
      const bottomCanBeTarget = (bottom < 30) && ((this.checkerboard[bottom] * this.checkerboard[this.chooseIndex]) < 1)
      const leftCanBeTarget = (left > -1) && ((this.checkerboard[left] * this.checkerboard[this.chooseIndex]) < 1)
      const rightCanBeTarget = (right < 30) && ((this.checkerboard[right] * this.checkerboard[this.chooseIndex]) < 1)
      if (top === index) {
        console.log('return true')
        console.log(level)
        console.log(index)
        console.log(top)
        console.log(this.checkerboard[top])
        console.log((this.checkerboard[top] > -1))
        console.log(((this.checkerboard[top] * this.checkerboard[this.chooseIndex]) < 1))
        console.log(topCanBeTarget)
      }
      if (topCanBeTarget && index === top) {
        return true
      } else if (bottomCanBeTarget && index === bottom) {
        return true
      } else if (leftCanBeTarget && index === left) {
        return true
      } else if (rightCanBeTarget && index === right) {
        return true
      }
      console.log('return fase')
    }
    return false
  }

  get numbers() {
    const newNums = Array.from(numbers.keys())
    return newNums.reverse()
  }

  get reversNumbers() {
    const sliceNums = this.numbers.slice(1, this.numbers.length - 1)
    return sliceNums
  }

  getChineseName(num) {
    return piece.get(num)
  }

  getPieceChineseName(number) {
    const num = Math.abs(number)
    const redBlue = number > 0 ? '红' : '蓝'
    if (number > 0) {
      const name = piece.get(num) + `(${redBlue})`
      return name
    }
    return '隐藏'
  }

  getColor(number) {
    const redBlue = number > 0 ? 'red' : 'blue'
    return redBlue
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
export default toNative(BeastChess)
</script>
