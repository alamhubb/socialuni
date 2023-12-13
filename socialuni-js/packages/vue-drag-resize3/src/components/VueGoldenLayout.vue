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
import {onMounted, reactive, ref, useSlots, defineExpose, getCurrentInstance} from "vue";
import {v4} from 'uuid'

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

function getUUID(): string {
  const randoms: number[] = []
  for (let i = 0; i < 16; i++) {
    randoms.push(Math.round(Math.random() * 255))
  }
  return v4({
    random: randoms
  }).replace(/-/g, '')
}

/*
onMounted(() => {
  glRenderElements.value = []

  const config: LayoutConfig = reactive({})

  const slots = useSlots()

  if (slots.default) {
    const defaults = slots.default()
    const defaultChild = defaults[0]

// What do I do if I want to get the defineExpose exported data in the defaultChild slot

  }
})
*/


onMounted(() => {
  glRenderElements.value = []

  const config: LayoutConfig = reactive({})

  const slots = useSlots()

  const instnce = getCurrentInstance()
  console.log(454554)
  console.log(instnce)
  console.log(instnce.slots.default())
  console.log(instnce.slotsProxy)

  if (slots.default) {
    const defaults = slots.default()
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

      console.log(6565656)
      console.log(defaults)
      console.log(defaultChild)
      if (defaultChild.type.data) {
        const itemData = defaultChild.type.data()
        if (itemData.layoutType === 'column') {
          config.root = {
            type: 'column',
            content: []
          }
          const children = defaultChild?.children.default()
          for (const child of children) {
            handlerChildren(child, config.root.content)
          }
        } else if (itemData.layoutType === 'row') {
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
  console.log(1231231)
  console.log(itemData)
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
    const uuid = 'uuid_' + getUUID()

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
