<template>
  <div ref="vueDragSizeContainer" class="position-absolute">
    <slot></slot>

    <div class="resizer top-left"></div>
    <div class="resizer top-right"></div>
    <div class="resizer bottom-left"></div>
    <div class="resizer bottom-right"></div>
    <div class="resizer top"></div>
    <div class="resizer bottom"></div>
    <div class="resizer left"></div>
    <div class="resizer right"></div>
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


  style = null

  mounted() {
    const elmnt = this.$refs.vueDragSizeContainer;

    useDraggable(elmnt, {
      onMove(e) {
        console.log(e)
        elmnt.style.left = `${e.x}px`
        elmnt.style.top = `${e.y}px`
      }
    })

    console.log(elmnt)

    var resizers = elmnt.querySelectorAll(".resizer");
    var currentResizer;

    for (var i = 0; i < resizers.length; i++) {
      currentResizer = resizers[i];
      currentResizer.addEventListener('mousedown', function (e) {
        e.stopPropagation();
        currentResizer = e.target;
        document.addEventListener('mousemove', resize);
        document.addEventListener('mouseup', stopResize);
      });
    }

    function stopResize() {
      document.removeEventListener('mousemove', resize);
    }

    function resize(e) {
      var rect = elmnt.getBoundingClientRect();
      if (currentResizer.classList.contains("bottom-right")) {
        elmnt.style.width = e.clientX - rect.left + "px";
        elmnt.style.height = e.clientY - rect.top + "px";
      } else if (currentResizer.classList.contains("bottom-left")) {
        elmnt.style.width = rect.right - e.clientX + "px";
        elmnt.style.height = e.clientY - rect.top + "px";
        elmnt.style.left = e.clientX + "px";
      } else if (currentResizer.classList.contains("top-right")) {
        elmnt.style.width = e.clientX - rect.left + "px";
        elmnt.style.height = rect.bottom - e.clientY + "px";
        elmnt.style.top = e.clientY + "px";
      } else if (currentResizer.classList.contains("top-left")) {
        elmnt.style.width = rect.right - e.clientX + "px";
        elmnt.style.height = rect.bottom - e.clientY + "px";
        elmnt.style.left = e.clientX + "px";
        elmnt.style.top = e.clientY + "px";
      } else if (currentResizer.classList.contains("top")) {
        elmnt.style.height = rect.bottom - e.clientY + "px";
        elmnt.style.top = e.clientY + "px";
      } else if (currentResizer.classList.contains("bottom")) {
        elmnt.style.height = e.clientY - rect.top + "px";
      } else if (currentResizer.classList.contains("right")) {
        elmnt.style.width = e.clientX - rect.left + "px";
      } else if (currentResizer.classList.contains("left")) {
        elmnt.style.width = rect.right - e.clientX + "px";
        elmnt.style.left = e.clientX + "px";
      }
    }
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
