<template>
  <div ref="vueDragSizeContainer" class="position-absolute" :style="{
    width:state.width+'px',
    height:state.height+'px',
    left:state.x+'px',
    top:state.y+'px',
  }">
    <slot></slot>

    <div>
      {{ state }}
    </div>

    <div v-for="direction in directions" :key="direction"  class="resizer" :class="direction"
         @mousedown="initResize(direction,$event)"></div>
  </div>
</template>


<script setup lang="ts">
import {useDraggable} from '@vueuse/core'
import {reactive, ref} from "vue";


const vueDragSizeContainer = ref(null)

useDraggable(vueDragSizeContainer, {
  onStart: () => {
    console.log('start')
    console.log(allowDrag)
    return allowDrag
  },
  onMove: (e) => {
    if (allowDrag) {
      state.x = e.x
      state.y = e.y
    }
  }
})

const directions = ['top-left', 'top-right', 'bottom-left', 'bottom-right', 'top', 'bottom', 'left', 'right'];

const state = reactive({
  x: 100,
  y: 100,
  width: 200,
  height: 200
})


let allowDrag = reactive(true)


function setWidth(width) {
  if (width >= 0) {
    state.width = width
  }
}

function setX(width, movementX) {
  if (width >= 0) {
    state.x += movementX
  }
}

function setY(height, movementY) {
  if (height >= 0) {
    state.y += movementY
  }
}

function setHeight(height) {
  if (height >= 0) {
    state.height = height
  }
}


function initResize(direction, event) {
  console.log(direction)
  allowDrag = false; // 开始调整大小
  event.stopPropagation(); // 阻止事件冒泡到拖拽处理程序
  function onMouseMove(e) {
    let width = null
    let height = null
    switch (direction) {
      case 'top-left':
        width = state.width - e.movementX
        setWidth(width)

        height = state.height - e.movementY
        setHeight(height)

        setX(width, e.movementX)
        setY(height, e.movementY)
        break;
      case 'top-right':
        width = state.width + e.movementX
        setWidth(width)

        height = state.height - e.movementY
        setHeight(height)

        setY(height, e.movementY)
        break;
      case 'bottom-left':
        width = state.width - e.movementX
        setWidth(width)

        height = state.height + e.movementY
        setHeight(height)

        setX(width, e.movementX)
        break;
      case 'bottom-right':
        width = state.width + e.movementX
        setWidth(width)

        height = state.height + e.movementY
        setHeight(height)
        break;
      case 'top':
        height = state.height - e.movementY
        setHeight(height)

        setY(height, e.movementY)
        break;
      case 'bottom':
        height = state.height + e.movementY
        setHeight(height)
        break;
      case 'left':
        width = state.width - e.movementX
        setWidth(width)

        setX(width, e.movementX)
        break;
      case 'right':
        width = state.width + e.movementX
        setWidth(width)
        break;
      default:
        break;
    }
  }

  const onMouseUp = () => {
    document.removeEventListener('mousemove', onMouseMove);
    document.removeEventListener('mouseup', onMouseUp);
    allowDrag = true
  };

  document.addEventListener('mousemove', onMouseMove);
  document.addEventListener('mouseup', onMouseUp);
}


</script>
<style scoped>
.resizer {
  width: 10px;
  height: 10px;
  background-color: blue;
  position: absolute;
}

.resizer.top-left,
.resizer.top-right,
.resizer.bottom-left,
.resizer.bottom-right {
  width: 15px;
  height: 15px;
}

.resizer.top,
.resizer.bottom {
  left: 50%;
  margin-left: -5px;
  width: 10px;
  height: 15px;
}

.resizer.right,
.resizer.left {
  top: 50%;
  margin-top: -5px;
  width: 15px;
  height: 10px;
}

.bottom-right {
  right: 0;
  bottom: 0;
  cursor: se-resize;
}

.bottom-left {
  left: 0;
  bottom: 0;
  cursor: sw-resize;
}

.top-right {
  right: 0;
  top: 0;
  cursor: ne-resize;
}

.top-left {
  left: 0;
  top: 0;
  cursor: nw-resize;
}

.top {
  top: 0;
  cursor: n-resize;
}

.bottom {
  bottom: 0;
  cursor: s-resize;
}

.right {
  right: 0;
  cursor: e-resize;
}

.left {
  left: 0;
  cursor: w-resize;
}
</style>
