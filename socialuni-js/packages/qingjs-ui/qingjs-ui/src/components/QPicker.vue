<template>
  <div :class="[uuid]" class="flex-row h100p">
    <!--    样式涉及到左侧选中小蓝块，所以这么写的-->
    <q-scroll class="flex-1 h100p" scroll-y :scroll-top="oneScrollTop">
      <div v-for="(item,index) in dataList"
           :key="index" @click="oneLevelChange(index)"
           class="q-picker-one-level-item row-col-center"
           :class="{uuid,'q-picker-item-active':index === checkedOne}"
      >
        <q-row-item :border="true" class="flex-1">
          {{ item.adName }}
        </q-row-item>
      </div>
    </q-scroll>
    <q-scroll class="flex-1 h100p" scroll-y :scroll-top="twoScrollTop">
      <div v-for="(item,index) in twoLevelData"
           :key="index" @click="twoLevelChange(index)"
           class="q-picker-two-level-item row-col-center"
           :class="{uuid,'q-picker-item-active':index === checkedTwo}"
      >
        <q-row-item :border="true" class="flex-1">
          {{ item.adName }}
        </q-row-item>
      </div>
    </q-scroll>
    <q-scroll class="flex-1 h100p" scroll-y :scroll-top="threeScrollTop">
      <div v-for="(item,index) in threeLevelData"
           :key="index" @click="threeLevelChange(index)"
           class="q-picker-three-level-item row-col-center"
           :class="{uuid,'q-picker-item-active':index === checkedThree}"
      >
        <q-row-item :border="true" class="flex-1">
          {{ item.adName }}
        </q-row-item>
      </div>
    </q-scroll>
  </div>
</template>
<script lang="ts">
import {Emit, Model, Component, Prop, Vue, Watch, toNative} from 'vue-facing-decorator'
import QRowItem from 'qingjs-ui/src/components/QRowItem.vue'
import UUIDUtil from "qing-util/src/util/UUIDUtil";
import QScroll from "qingjs-ui/src/components/QScroll.vue";

/*
显示出来已经选了的城市，给她画上钩

默认当前选择城市，
如果选择了则展示选择的，
最后取消选择，还显示当前选择的

* */
@toNative
@Component({
  components: {
    QRowItem,  QScroll
  }
})
export default class QPicker extends Vue {
  readonly uuid: string = 'u' + UUIDUtil.getUUID()
  @Model('modelValue') readonly value!: any
  @Prop() readonly dataList: any []

  //这个组件有点问题，值改变的时候没有滚动到对应的位置

  //选中的一级
  checkedOne = 0
  //选中的二级
  checkedTwo = null
  //选中的二级
  checkedThree = null

  @Emit('update:modelValue')
  input() {
    if (this.checkedThree || this.checkedThree === 0) {
      return this.threeLevelData[this.checkedThree]
    } else if (this.checkedTwo || this.checkedTwo === 0) {
      return this.twoLevelData[this.checkedTwo]
    } else if (this.checkedOne) {
      return this.dataList[this.checkedOne]
    } else {
      return null
    }
  }

  get twoLevelData() {
    if ((this.checkedOne === 0 || this.checkedOne) && this.dataList[this.checkedOne]) {
      return this.dataList[this.checkedOne].childs
    } else {
      return []
    }
  }

  get threeLevelData() {
    if ((this.checkedTwo === 0 || this.checkedTwo) && this.twoLevelData[this.checkedTwo]) {
      return this.twoLevelData[this.checkedTwo].childs
    } else {
      return []
    }
  }

  //供外父组件使用，清空内部值
  initSelectData() {
    this.checkedOne = 0
    this.checkedTwo = null
    this.checkedThree = null
  }

  oneScrollTop = 0

  //点击菜单，如果重复点击则为取消选中
  oneLevelChange(index) {
    if (this.checkedOne === index) {
      this.checkedOne = 0
    } else {
      this.checkedOne = index
      this.oneScrollTop = this.oneTops[index]
    }
    this.checkedTwo = null
    this.checkedThree = null
    this.input()
  }

  twoScrollTop = 0

  twoLevelChange(index) {
    if (this.checkedTwo === index) {
      this.checkedTwo = null
    } else {
      this.checkedTwo = index
      this.twoScrollTop = this.twoTops[index]
    }
    this.checkedThree = null
    this.input()
  }

  threeScrollTop: number = 0

  threeLevelChange(index) {
    if (this.checkedThree === index) {
      this.checkedThree = null
    } else {
      this.checkedThree = index
      this.threeScrollTop = this.threeTops[index]
    }
    this.input()
  }
}
</script>
<style lang="scss">
.q-picker-box {
  flex-direction: row;
}

.q-picker-item {
  height: 55px;
  background: #f6f6f6;
  box-sizing: border-box;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 13px;
  color: #444;
  font-weight: 400;
  line-height: 1;
}

.q-picker-item-active {
  color: #000;
  font-size: 15px;
  font-weight: 600;
  background: #fff;
}

.q-picker-item-active::before {
  content: "";
  border-left: 4px solid #2979ff;
  height: 16px;
  left: 0;
}
</style>
