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

    <div v-for="direction in directions" :key="direction" :class="'resizer ' + direction"
         @mousedown="initResize(direction,$event)"></div>
  </div>
</template>


<script lang="ts">
import {Vue, Component} from 'vue-facing-decorator';
import {useDraggable} from '@vueuse/core'

@Component({})
export default class vueDragSize extends Vue {
  $refs: {
    vueDragSizeContainer: HTMLElement
  }

  directions = ['top-left', 'top-right', 'bottom-left', 'bottom-right', 'top', 'bottom', 'left', 'right'];

  style = null

  allowDrag = true

  state = {
    x: 100,
    y: 100,
    width: 200,
    height: 200
  }

  setWidth(width) {
    if (width >= 0) {
      this.state.width = width
    }
  }

  setX(width, movementX) {
    if (width >= 0) {
      this.state.x += movementX
    }
  }

  setY(height, movementY) {
    if (height >= 0) {
      this.state.y += movementY
    }
  }

  setHeight(height) {
    if (height >= 0) {
      this.state.height = height
    }
  }


  initResize(direction, event) {
    this.allowDrag = false; // 开始调整大小
    event.stopPropagation(); // 阻止事件冒泡到拖拽处理程序
    const onMouseMove = (e) => {
      let setWidth = null
      let setHeight = null
      switch (direction) {
        case 'top-left':
          setWidth = this.state.width - e.movementX
          this.setWidth(setWidth)

          setHeight = this.state.height - e.movementY
          this.setHeight(setHeight)


          this.setX(setWidth, e.movementX)
          this.setY(setHeight, e.movementY)
          break;
        case 'top-right':
          setWidth = this.state.width + e.movementX
          this.setWidth(setWidth)

          setHeight = this.state.height - e.movementY
          this.setHeight(setHeight)

          this.setY(setHeight, e.movementY)
          break;
        case 'bottom-left':
          setWidth = this.state.width - e.movementX
          this.setWidth(setWidth)

          setHeight = this.state.height + e.movementY
          this.setHeight(setHeight)

          this.setX(setWidth, e.movementX)
          break;
        case 'bottom-right':
          setWidth = this.state.width + e.movementX
          this.setWidth(setWidth)

          setHeight = this.state.height + e.movementY
          this.setHeight(setHeight)
          break;
        case 'top':
          setHeight = this.state.height - e.movementY
          this.setHeight(setHeight)

          this.setY(setHeight, e.movementY)
          break;
        case 'bottom':
          setHeight = this.state.height + e.movementY
          this.setHeight(setHeight)
          break;
        case 'left':
          setWidth = this.state.width - e.movementX
          this.setWidth(setWidth)

          this.setX(setWidth, e.movementX)
          break;
        case 'right':
          setWidth = this.state.width + e.movementX
          this.setWidth(setWidth)
          break;
        default:
          break;
      }
    };

    const onMouseUp = () => {
      document.removeEventListener('mousemove', onMouseMove);
      document.removeEventListener('mouseup', onMouseUp);
      this.allowDrag = true
    };

    document.addEventListener('mousemove', onMouseMove);
    document.addEventListener('mouseup', onMouseUp);
  }

  mounted() {
    const elmnt = this.$refs.vueDragSizeContainer;

    useDraggable(elmnt, {
      onStart: () => {
        console.log('start')
        console.log(this.allowDrag)
        return this.allowDrag
      },
      onMove: (e) => {
        if (this.allowDrag) {
          this.state.x = e.x
          this.state.y = e.y
        }
      }
    })
  }
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
