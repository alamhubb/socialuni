<template>
  <div class="flex-col h100p overflow-hidden color-black">
    <!--    <div id="layoutContainer" style="width: 100%; height: 100%;"></div>-->

    <vue-golden-layout>
      <vue-golden-layout-row>
        <music-view></music-view>
        <music-view></music-view>
      </vue-golden-layout-row>
    </vue-golden-layout>

    <el-button @click="active++">active--</el-button>
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
  </div>
</template>

<script lang="ts">
import {Component, Vue, Watch} from 'vue-facing-decorator';
import VueGoldenLayout from 'vue3-golden-layout/src/components/VueGoldenLayout.vue';
import VueGoldenLayoutRow from 'vue3-golden-layout/src/components/VueGoldenLayoutRow.vue';
import VueGoldenLayoutColumn from 'vue3-golden-layout/src/components/VueGoldenLayoutColumn.vue';
import 'golden-layout/dist/css/goldenlayout-base.css';
import 'golden-layout/dist/css/themes/goldenlayout-light-theme.css';
import ChildCom from "@/views/chat/ChildCom.vue";
import {h, render} from "vue";
import MusicView from "@/views/chat/MusicView.vue";

@Component({
  components: {VueGoldenLayoutColumn, MusicView, ChildCom, VueGoldenLayout, VueGoldenLayoutRow}
})
export default class MessageView extends Vue {

  active = 1

  /*mounted() {
    var config: LayoutConfig = {
      content: [{
        type: 'row',
        content: [{
          type: 'component',
          componentName: 'testComponent',
          componentState: {label: 'A'}
        }, {
          type: 'column',
          content: [{
            type: 'component',
            componentName: 'testComponent',
            componentState: {label: 'B'}
          }, {
            type: 'component',
            componentName: 'testComponent',
            componentState: {label: 'd'}
          }, {
            type: 'component',
            componentName: 'testComponent',
            componentState: {label: 'C'}
          }]
        }]
      }]
    };

    const myLayout = new GoldenLayout(config, document.getElementById('layoutContainer'));

    myLayout.registerComponent('testComponent', function (container: ComponentContainer, componentState) {
      container.getElement().innerHTML = '<h2>' + componentState.label + '</h2>'
    });

    myLayout.init();
  }*/

  allowDrop(ev) {
    ev.preventDefault();
  }

  drag(ev) {
    ev.dataTransfer.setData("Text", ev.target.id);
  }

  addc() {
    // 创建子组件的 VNode
    // const childVNode = h(ChildCom, { someProp: 'value' });
    // 获取容器的引用
    const container = this.$refs.container;

    console.log(this.$refs.container2)
    console.log(this.$refs.container2._)
    console.log(this.$refs.container2._.vnode)
    // 渲染子组件到容器中
    render(h(this.$refs.container2._.vnode), container);
  }

  drop(ev) {
    var data = ev.dataTransfer.getData("Text");
    ev.target.appendChild(document.getElementById(data));
    ev.preventDefault();
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
