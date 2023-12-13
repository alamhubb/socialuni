<template>
  <div ref="vueDragSizeContainer" class="position-absolute">
    <slot></slot>

    <div class="resizer bottom-right size3 bg-red position-absolute"></div>
    <div class="resizer bottom-left size3 bg-red position-absolute"></div>
    <div class="resizer top-right size3 bg-red position-absolute"></div>
    <div class="resizer top-left size3 bg-red position-absolute"></div>
  </div>
</template>


<script lang="ts">
import {Vue, Component} from 'vue-facing-decorator';

@Component({})
export default class vueDragSize extends Vue {
  $refs: {
    vueDragSizeContainer: HTMLElement
  }

  mounted() {
    const elmnt = this.$refs.vueDragSizeContainer;

    console.log(elmnt)

    var resizers = document.querySelectorAll(".resizer");
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

    function resize(e) {
      if (currentResizer.classList.contains("bottom-right")) {
        elmnt.style.width = e.clientX - elmnt.getBoundingClientRect().left + "px";
        elmnt.style.height = e.clientY - elmnt.getBoundingClientRect().top + "px";
      } else if (currentResizer.classList.contains("bottom-left")) {
        elmnt.style.width = elmnt.getBoundingClientRect().right - e.clientX + "px";
        elmnt.style.height = e.clientY - elmnt.getBoundingClientRect().top + "px";
        elmnt.style.left = e.clientX + "px";
      } else if (currentResizer.classList.contains("top-right")) {
        elmnt.style.width = e.clientX - elmnt.getBoundingClientRect().left + "px";
        elmnt.style.height = elmnt.getBoundingClientRect().bottom - e.clientY + "px";
        elmnt.style.top = e.clientY + "px";
      } else if (currentResizer.classList.contains("top-left")) {
        elmnt.style.width = elmnt.getBoundingClientRect().right - e.clientX + "px";
        elmnt.style.height = elmnt.getBoundingClientRect().bottom - e.clientY + "px";
        elmnt.style.top = e.clientY + "px";
        elmnt.style.left = e.clientX + "px";
      }
    }

    function stopResize() {
      document.removeEventListener('mousemove', resize);
    }
  }
}
</script>
<style scoped>
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
</style>
