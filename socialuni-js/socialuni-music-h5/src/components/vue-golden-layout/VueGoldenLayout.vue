<template>
  <div ref="goldenLayoutContainer" id="goldenLayoutContainer" style="width: 100%; height: 100%;">
    <template v-for="(element, index) in glRenderElements">
      <teleport :to="element.data.elmId">
        <component :is="element.element"></component>
        <!--            {{ element.element }}-->
      </teleport>
    </template>
  </div>
</template>

<script lang="ts" setup>
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
import {onMounted, reactive, ref, useSlots} from "vue";
import UUIDUtil from "socialuni-util/src/util/UUIDUtil.ts";

class VueGoldenLayoutRenderElement {
  element: any
  type?: string
  data: {
    uuid: string,
    elmId: string,
  }
}


const goldenLayoutContainer = ref(null)
const glRenderElements = ref([])

onMounted(() => {
  glRenderElements.value = []

  const config: LayoutConfig = reactive({})

  const slots = useSlots()

  console.log(slots)

  if (slots.default) {
    const defaults = slots.default()
    console.log(defaults)
    if (defaults.length > 1) {
      config.root = {
        type: 'row',
        content: []
      }
      for (const defaultChild of defaults) {
        handlerChildren(defaultChild, config.root.content)
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
            handlerChildren(child, config.root.content)
          }
        } else if (rootData.layoutType === 'row') {
          config.root = {
            type: 'row',
            content: []
          }
          const children = defaultChild?.children.default()
          for (const child of children) {
            handlerChildren(child, config.root.content)
          }
        } else {
          config.root = {
            type: 'row',
            content: []
          }
          handlerChildren(defaultChild, config.root.content)
        }
      }
    }

    const layout = new GoldenLayout(goldenLayoutContainer.value);

    layout.registerComponentFactoryFunction('vueComponent', (container, state) => {
      container.element.id = state.data.uuid
      // this.keyMap.set(state.data.uuid, true)
    });

    // layout.init()

    layout.loadLayout(config)

    console.log(config)
  }
})


function handlerChildren(item, content: any[]) {
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
      handlerChildren(child, newContent.content)
    }
  } else if (itemData.layoutType === 'row') {
    const newContent = {
      type: 'row',
      content: []
    }
    content.push(newContent)
    const children = item.children.default()
    for (const child of children) {
      handlerChildren(child, newContent.content)
    }
  } else {
    const uuid = 'uuid_' + UUIDUtil.getUUID()

    console.log(item)
    const glRenderElement: VueGoldenLayoutRenderElement = {
      element: item,
      data: {
        uuid: uuid,
        elmId: '#' + uuid,
      }
    }
    glRenderElements.value.push(glRenderElement)
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
</script>
