<template>
  <div class="flex-col h100p overflow-hidden pd bg-white">
    <div class="flex-row overflow-hidden flex-none">
      <div v-for="i in 20" class="flex-1 w200 h30" :class="[active===i?'bg-blue_light bb-2 bb-blue':'bg-default']"
           @click="active=i"  draggable="true">
        {{ i }}
      </div>
    </div>
    <div id="wrapper">
      <ul id="menuContainer">
        <li id="addMenuItem">Add another component</li>
      </ul>
      <div id="layoutContainer"></div>
    </div>
<!--    <div class="flex-1 overflow-hidden bg-green_light pd" @dragover="allowDrop">
      {{ active }}
    </div>-->
  </div>
</template>

<script lang="ts">
import {Component, Vue, Watch} from 'vue-facing-decorator';
import {ComponentContainer, ComponentItemConfig, GoldenLayout, ItemType, LayoutConfig} from "golden-layout";


class MyComponent {
  rootElement: HTMLElement;

  constructor(public container: ComponentContainer) {
    this.rootElement = container.element;
    this.rootElement.innerHTML = '<h2>' + 'Component Type: MyComponent' + '</h2>';
  }
}

@Component({
  components: {}
})
export default class MessageView extends Vue {

  active = 1

  mounted(){
    const myLayout: LayoutConfig = {
      root: {
        type: 'row',
        content: [
          {
            title: 'My Component 1',
            type: 'component',
            componentType: 'MyComponent',
            width: 50,
          },
          {
            title: 'My Component 2',
            type: 'component',
            componentType: 'MyComponent',
            // componentState: { text: 'Component 2' }
          }
        ]
      }
    };

    const menuContainerElement = document.querySelector('#menuContainer');
    const addMenuItemElement = document.querySelector('#addMenuItem');
    const layoutElement = document.querySelector('#layoutContainer');

    addMenuItemElement.addEventListener('click', (event) => {
      goldenLayout.addComponent('MyComponent', undefined, 'Added Component');
    });

    const goldenLayout = new GoldenLayout(layoutElement);

    goldenLayout.registerComponent( 'MyComponent', MyComponent);

    goldenLayout.loadLayout(myLayout);
  }

  allowDrop(ev) {
    ev.preventDefault();
  }

  drag(ev) {
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
h2{
  font: 14px Arial, sans-serif;
  color:#fff;
  padding: 10px;
  text-align: center;
}

html, body{
  height: 100%;
}

*{
  margin: 0;
  padding: 0;
  list-style-type:none;
}

#wrapper{
  display: flex;
  height: 100%
}

#menuContainer{
  flex: 0 0 auto;
  margin-right: 3px;
}

#menuContainer li{
  border-bottom: 1px solid #000;
  border-top: 1px solid #333;
  cursor: pointer;
  padding: 10px 10px;
  color: #BBB;
  background: #1a1a1a;
  font: 12px Arial, sans-serif;
}

#menuContainer li:hover{
  background: #111;
  color: #CCC;
}

#layoutContainer {
  flex: 1 1 auto;
  height: 100%;
}
</style>
