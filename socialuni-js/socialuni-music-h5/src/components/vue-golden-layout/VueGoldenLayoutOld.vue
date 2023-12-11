<template>
  <div ref="goldenLayoutContainer" id="goldenLayoutContainer" style="width: 100%; height: 100%;">
    <template v-for="(element, index) in this.glRenderElements">
      <teleport :to="element.data.elmId">
        <component :is="element.element"></component>
        <!--            {{ element.element }}-->
      </teleport>
    </template>
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
      const defaults = this.$slots.default()
      if (defaults.length > 1) {
        config.root = {
          type: 'row',
          content: []
        }
        for (const defaultChild of defaults) {
          this.handlerChildren(defaultChild, config.root.content)
        }
      } else {
        const defaultChild = defaults[0]
        if (defaultChild.type.data) {
          const rootData = defaultChild.type.data()
          if (rootData.layoutType === 'column') {
            config.root = {
              type: 'column',
              content: []
            }
            const children = defaultChild?.children.default()
            for (const child of children) {
              this.handlerChildren(child, config.root.content)
            }
          } else if (rootData.layoutType === 'row') {
            config.root = {
              type: 'row',
              content: []
            }
            const children = defaultChild?.children.default()
            for (const child of children) {
              this.handlerChildren(child, config.root.content)
            }
          } else {
            config.root = {
              type: 'row',
              content: []
            }
            this.handlerChildren(defaultChild, config.root.content)
          }
        }
      }


      const layout = new GoldenLayout(this.$refs.goldenLayoutContainer);


      layout.registerComponentFactoryFunction('vueComponent', (container, state) => {
        container.element.id = state.data.uuid
        // this.keyMap.set(state.data.uuid, true)
      });

      layout.loadLayout(config)
    }
  }


  handlerChildren(item, content: any[]) {
    //如果为row或者column则继续获取child
    const itemData = item.type.data()
    if (itemData.layoutType === 'column') {
      const newContent = {
        type: 'column',
        content: []
      }
      content.push(newContent)
      const children = item.children.default()
      for (const child of children) {
        this.handlerChildren(child, newContent.content)
      }
    } else if (itemData.layoutType === 'row') {
      const newContent = {
        type: 'row',
        content: []
      }
      content.push(newContent)
      const children = item.children.default()
      for (const child of children) {
        this.handlerChildren(child, newContent.content)
      }
    } else {
      const uuid = 'uuid_' + UUIDUtil.getUUID()

      const glRenderElement: VueGoldenLayoutRenderElement = {
        element: item,
        data: {
          uuid: uuid,
          elmId: '#' + uuid,
        }
      }
      this.glRenderElements.push(glRenderElement)
      content.push({
        type: 'component',
        componentType: 'vueComponent',
        componentState: {data: glRenderElement.data}
      })
    }
    //需要遍历节点
    //然后申城render
    //config

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
