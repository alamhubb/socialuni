<template>
  <div ref="interactContainer" draggable="true" class="position-absolute cursor-move">
    <div>
      <div class="bt-5 cursor-n-resize"></div>
      <div class="flex-row">
        <div class="bl-5 cursor-e-resize"></div>
        <slot></slot>
        <div class="br-5 cursor-e-resize"></div>
      </div>
      <div class="bb-5 cursor-n-resize"></div>
    </div>
  </div>
</template>

<script lang="ts">
import {Component, Emit, Model, Prop, Vue, Watch} from 'vue-facing-decorator';


function dragElement(elmnt) {
  var pos1 = 0, pos2 = 0, pos3 = 0, pos4 = 0;
  elmnt.onmousedown = dragMouseDown;

  function dragMouseDown(e) {
    e = e || window.event;
    e.preventDefault();
    // 获取鼠标光标的初始位置:
    pos3 = e.clientX;
    pos4 = e.clientY;
    document.onmouseup = closeDragElement;
    // 每当光标移动时，调用elementDrag函数:
    document.onmousemove = elementDrag;

    console.log(e.clientX)
    console.log(e.clientY)
  }

  function elementDrag(e) {
    e = e || window.event;
    e.preventDefault();
    // 计算光标的新位置:
    pos1 = pos3 - e.clientX;
    pos2 = pos4 - e.clientY;
    pos3 = e.clientX;
    pos4 = e.clientY;
    // 设置元素的新位置:

    console.log(elmnt.offsetTop)
    console.log(pos2)

    elmnt.style.top = (elmnt.offsetTop - pos2) + "px";
    elmnt.style.left = (elmnt.offsetLeft - pos1) + "px";

    console.log(e.clientX)
    console.log(e.clientY)
  }

  function closeDragElement() {
    // 停止移动时，移除鼠标事件
    document.onmouseup = null;
    document.onmousemove = null;
  }
}

@Component({
  components: {}
})
export default class VueInteract extends Vue {
  $refs: {
    interactContainer: HTMLDivElement
  }

  startX = null
  startY = null

  left = null
  top = null


  mounted() {
    console.log(this.$refs.interactContainer)

    let box = this.$refs.interactContainer

    dragElement(box)
  }
}
</script>
