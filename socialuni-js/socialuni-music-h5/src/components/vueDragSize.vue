<template>
  <div ref="draggable" class="draggable pd-lg"
       :style="{ width: state.width + 'px', height: state.height + 'px', top: state.y + 'px', left: state.x + 'px' }">
    {{ state.x }} x {{ state.y }}

<!--    <div v-for="direction in directions" :key="direction" :class="'resizer ' + direction"
         @mousedown="initResize(direction)"></div>-->
  </div>
</template>

<script setup>
import {ref, reactive} from 'vue';
import {useDraggable} from '@vueuse/core';

const draggable = ref(null);

const state = reactive({
  x: 100,
  y: 100,
  width: 200,
  height: 200
});

useDraggable(draggable, {
  onMove(e) {
    state.x = e.x
    state.y = e.y
    // console.log(e)
    // console.log(draggable)
    // console.log(draggable.style)
    // draggable.value.style.left = `${e.x}px`
    // draggable.value.style.top = `${e.y}px`
  }
})

/*
const directions = ['top-left', 'top-right', 'bottom-left', 'bottom-right', 'top', 'bottom', 'left', 'right'];

function initResize(direction) {
  const onMouseMove = (e) => {
    switch (direction) {
      case 'top-left':
        state.width -= e.movementX;
        state.height -= e.movementY;
        state.x += e.movementX;
        state.y += e.movementY;
        break;
      case 'top-right':
        state.width += e.movementX;
        state.height -= e.movementY;
        state.y += e.movementY;
        break;
      case 'bottom-left':
        state.width -= e.movementX;
        state.height += e.movementY;
        state.x += e.movementX;
        break;
      case 'bottom-right':
        state.width += e.movementX;
        state.height += e.movementY;
        break;
      case 'top':
        state.height -= e.movementY;
        state.y += e.movementY;
        break;
      case 'bottom':
        state.height += e.movementY;
        break;
      case 'left':
        state.width -= e.movementX;
        state.x += e.movementX;
        break;
      case 'right':
        state.width += e.movementX;
        break;
    }
  };

  const onMouseUp = () => {
    document.removeEventListener('mousemove', onMouseMove);
    document.removeEventListener('mouseup', onMouseUp);
  };

  document.addEventListener('mousemove', onMouseMove);
  document.addEventListener('mouseup', onMouseUp);
}*/
</script>

<style>
.draggable {
  position: absolute;
  background-color: red;
  cursor: move;
}

.resizer {
  width: 15px;
  height: 15px;
  position: absolute;
  background-color: blue;
}

.top-left {
  top: 0;
  left: 0;
  cursor: nw-resize;
}

.top-right {
  top: 0;
  right: 0;
  cursor: ne-resize;
}

.bottom-left {
  bottom: 0;
  left: 0;
  cursor: sw-resize;
}

.bottom-right {
  bottom: 0;
  right: 0;
  cursor: se-resize;
}

.top {
  top: 0;
  left: 50%;
  cursor: n-resize;
}

.bottom {
  bottom: 0;
  left: 50%;
  cursor: s-resize;
}

.left {
  left: 0;
  top: 50%;
  cursor: w-resize;
}

.right {
  right: 0;
  top: 50%;
  cursor: e-resize;
}
</style>
