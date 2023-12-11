<template>
  <div ref="goldenLayoutContainer" id="goldenLayoutContainer" style="width: 100%; height: 100%;">
    <div v-show="false" ref="default">
      <slot></slot>
    </div>
  </div>
</template>

<script lang="ts">
import {Component, Vue, Watch} from 'vue-facing-decorator';
import {ComponentContainer, ComponentItemConfig, GoldenLayout, ItemType, LayoutConfig} from "golden-layout";
import 'golden-layout/dist/css/goldenlayout-base.css';
import 'golden-layout/dist/css/themes/goldenlayout-light-theme.css';
import {h, ref, render} from "vue";

@Component({
  components: {}
})
export default class VueGoldenLayout extends Vue {


  active = 1

  mounted() {
    var config: LayoutConfig = {
      root: {
        type: 'row',
        content: []
      }
    };

    config.root.content.push({
      type: 'component',
      componentName: 'vueComponent',
      componentState: { /* 你的状态数据 */}
    })
    console.log(12312312)
    console.log(this.$refs)
    console.log(this.$slots)
    console.log(this.$slots.default()[0])
    console.log(this.$refs.default.innerHTML)

    const slotContent = ref(null);


    const layout = new GoldenLayout(config, this.$refs.goldenLayoutContainer);

    layout.registerComponent('vueComponent', container => {

      // $mount(container.getElement()[0]);

      // console.log(container)
      // console.log(container.getElement())
      // console.log(this.$slots.default()[0])

      render(h(this.$slots.default()[0]), container.getElement())

      // container.getElement().innerHTML = this.$refs.default.innerHTML
    });

    layout.init();

    // 添加一个带有Vue组件的面板
  }

  allowDrop(ev) {
    ev.preventDefault();
  }

  drag(ev) {
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
    ev.dataTransfer.setData("Text", ev.target.id);
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
