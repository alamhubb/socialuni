<template>
  <div class="flex-col h100p overflow-hidden color-black">
    <div class="flex-row h100p overflow-hidden">
      <div class="w200 bd-radius shadow h100p flex-none br bg-default">
        <socialuni-chat-view-h5></socialuni-chat-view-h5>
      </div>

      <div class="h100p flex-col flex-1 bg-white">
        <!--          <vue-drag-resize>-->
        <music-view class="shadow-bottom"></music-view>
        <!--          </vue-drag-resize>-->
        <div class="flex-1 overflow-hidden">
          <socialuni-msg-view-h5></socialuni-msg-view-h5>
        </div>
        <div>
        </div>
      </div>
    </div>
  </div>

  <!--      </vue-golden-layout-row>-->
  <!--    </vue-golden-layout>-->

  <!--    <el-button @click="active++">active&#45;&#45;</el-button>-->
  <!--    <child-com>123</child-com>
      <el-button @click="addc">add</el-button>
      <div ref="container"></div>
      <child-com ref="container2">{{active}}</child-com>-->


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
  <!--  </div>-->
</template>

<script lang="ts">
import {Component, Vue, Watch, toNative} from 'vue-facing-decorator';
import VueGoldenLayout from 'vue3-golden-layout/src/components/VueGoldenLayout.vue';
import VueGoldenLayoutRow from 'vue3-golden-layout/src/components/VueGoldenLayoutRow.vue';
import VueGoldenLayoutColumn from 'vue3-golden-layout/src/components/VueGoldenLayoutColumn.vue';
import 'golden-layout/dist/css/goldenlayout-base.css';
import 'golden-layout/dist/css/themes/goldenlayout-light-theme.css';
import ChildCom from "@/views/chat/ChildCom.vue";
import MusicView from "@/views/chat/MusicView.vue";
import SocialuniChatViewH5 from "socialuni-im-view-h5/src/views/SocialuniChatViewH5.vue";
import SocialuniMsgViewH5 from "socialuni-im-view-h5/src/views/SocialuniMsgViewH5.vue";
import VueDragResize from "vue-drag-resize3/src/components/VueDragResize.vue";
import VueInteract from "@/components/vue-interact/VueInteract.vue";
import MusicPlayer from "socialuni-music-ui-h5/src/components/MusicPlayer.vue";

@toNative
@Component({
  components: {
    VueDragResize,
    VueInteract,
    MusicPlayer,
    SocialuniChatViewH5,
    SocialuniMsgViewH5,
    VueGoldenLayoutColumn,
    MusicView,
    ChildCom,
    VueGoldenLayout,
    VueGoldenLayoutRow
  }
})
export default class MessageView extends Vue {


  mounted() {

  }
}
</script>
<style lang="scss">
h2 {
  font: 14px Arial, sans-serif;
  color: #fff;
  padding: 10px;
  text-align: center;
}

html, body {
  height: 100%;
}

* {
  margin: 0;
  padding: 0;
  list-style-type: none;
}

#wrapper {
  display: flex;
  height: 100%
}

#menuContainer {
  flex: 0 0 auto;
  margin-right: 3px;
}

#menuContainer li {
  border-bottom: 1px solid #000;
  border-top: 1px solid #333;
  cursor: pointer;
  padding: 10px 10px;
  color: #BBB;
  background: #1a1a1a;
  font: 12px Arial, sans-serif;
}

#menuContainer li:hover {
  background: #111;
  color: #CCC;
}

#layoutContainer {
  flex: 1 1 auto;
  height: 100%;
}
</style>
