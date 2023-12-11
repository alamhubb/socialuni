<template>
  <div ref="goldenLayoutContainer" id="goldenLayoutContainer" style="width: 100%; height: 100%;">

    <!--    <div id="test1"></div>-->
    <!--    <div id="test2"></div>-->
    <div>
      1`2,{{
        glRenderElements.map(item => {
          return {hasRender: hasRender(item.data.uuid), uuid: item.data.uuid}
        })
      }}
      <template v-if="glRenderElements.length">
        <template v-for="(element, index) in this.glRenderElements">
          <teleport v-if="hasRender(element.data.uuid)" :to="element.data.elmId" :key="element.data.uuid">
            {{ element.element }}
          </teleport>
        </template>
      </template>
    </div>


    <!--    <div v-show="false" ref="default">
          <slot></slot>
        </div>-->
  </div>
</template>

<script lang="ts">
import {Component, Vue, Watch} from 'vue-facing-decorator';
import {
  ComponentContainer,
  ComponentItemConfig,
  GoldenLayout,
  ItemType,
  LayoutConfig,
  LayoutManager
} from "golden-layout";
import 'golden-layout/dist/css/goldenlayout-base.css';
import 'golden-layout/dist/css/themes/goldenlayout-light-theme.css';
import {h, ref, render} from "vue";
import UUIDUtil from "socialuni-util/src/util/UUIDUtil.ts";

class VueGoldenLayoutRenderElement {
  element: any
  type?: string
  data: {
    uuid: string,
    elmId: string,
  }
}

@Component({
  components: {}
})
export default class VueGoldenLayout extends Vue {
  active = 1

  teleportId = null


  glRenderElements: VueGoldenLayoutRenderElement[] = []

  keyMap: Map<string, boolean> = new Map()

  hasRender(uuid: string) {
    return this.keyMap.get(uuid)
  }

  /*  get slotElements() {
      const slotElements = this.$slots.default ? this.$slots.default() : []
      const slots = slotElements.map(element => {
        console.log(element)
        return {
          element,
          data: {
            uuid: null
          },
        }
      })
      console.log(slots)
      console.log(7787878)
      // 获取默认插槽的所有顶级节点
      return slots
    }*/

  mounted() {
    this.glRenderElements = []


    const config: LayoutConfig = {}

    if (this.$slots.default) {
      const root = this.$slots.default()[0]

      console.log(root)
      if (root.type.data) {
        const rootData = root.type.data()
        console.log(rootData.layoutType)
        if (rootData.layoutType === 'column') {
          config.root = {
            type: 'column',
            content: []
          }
        } else {
          config.root = {
            type: 'row',
            content: []
          }
        }

        const children = root.children.default()


        for (const child of children) {
          const uuid = 'uuid_' + UUIDUtil.getUUID()

          const glRenderElement: VueGoldenLayoutRenderElement = {
            element: child,
            data: {
              uuid: uuid,
              elmId: '#' + uuid,
            }
          }
          this.glRenderElements.push(glRenderElement)

          this.keyMap.set(uuid, false)

          config.root.content.push({
            type: 'component',
            componentType: 'vueComponent',
            componentState: {data: glRenderElement.data}
          })
        }

        const layout = new GoldenLayout(config, this.$refs.goldenLayoutContainer);

        layout.registerComponentFactoryFunction('vueComponent', (container, state) => {
          console.log(66666)
          console.log(state.data.uuid)
          container.element.id = state.data.uuid
          this.keyMap.set(state.data.uuid, true)
        });

        layout.init()
      }
    }

    /*const slotElements = this.$slots.default ? this.$slots.default() : []



    const slots = slotElements.map(element => {
      console.log(element)
      return {
        element,
        data: {
          uuid: null
        },
      }
    })
    console.log(slots)
    console.log(7787878)
    // 获取默认插槽的所有顶级节点
    return slots




    var config: LayoutConfig = {
      root: {
        type: 'row',
        content: []
      }
    };

    //获取子元素
    //有多少个就对对应多少个teleport

    this.slotElements.forEach((item, index) => {
      config.root.content.push({
        type: 'component',
        componentName: 'vueComponent',
        componentState: {data: item.data}
      })
    })

    const layout = new GoldenLayout(config, this.$refs.goldenLayoutContainer);

    layout.registerComponent('vueComponent', (container, state) => {
      console.log(66666)
      console.log(state)
      const uuid = 'uuid_' + UUIDUtil.getUUID()
      state.data.uuid = '#' + uuid
      container.element.id = state.uuid
    });

    layout.init();*/

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
