<template>
  <view :class="[uuid]" class="flex-row h100%">
    <!--    样式涉及到左侧选中小蓝块，所以这么写的-->
    <scroll-view class="flex-1 h100%" scroll-y :scroll-top="oneScrollTop">
      <view v-for="(item,index) in dataList"
            :key="index" @click="oneLevelChange(index)"
            class="q-picker-one-level-item row-col-center"
            :class="{uuid,'q-picker-item-active':index === checkedOne}"
      >
        <view class="flex-1 q-box-between bb bg-click">
          {{ item.adName }}
        </view>
      </view>
    </scroll-view>
    <scroll-view class="flex-1 h100%" scroll-y :scroll-top="twoScrollTop">
      <view v-for="(item,index) in twoLevelData"
            :key="index" @click="twoLevelChange(index)"
            class="q-picker-two-level-item row-col-center"
            :class="{uuid,'q-picker-item-active':index === checkedTwo}"
      >
        <view class="flex-1 q-box-between bb bg-click">
          {{ item.adName }}
        </view>
      </view>
    </scroll-view>
    <scroll-view class="flex-1 h100%" scroll-y :scroll-top="threeScrollTop">
      <view v-for="(item,index) in threeLevelData"
            :key="index" @click="threeLevelChange(index)"
            class="q-picker-three-level-item row-col-center"
            :class="{uuid,'q-picker-item-active':index === checkedThree}"
      >
        <view class="flex-1 q-box-between bb bg-click">
          {{ item.adName }}
        </view>
      </view>
    </scroll-view>
  </view>
</template>
<script setup lang="ts">
import SelectorQuery = UniApp.SelectorQuery
import NodesRef = UniApp.NodesRef
import CommonUtil from "socialuni-common/src/utils/CommonUtil";
import {computed, defineProps, nextTick, onMounted, ref, watch} from "vue";
import emit from "socialuni-use/src/use/useEmit";

/*
显示出来已经选了的城市，给她画上钩

默认当前选择城市，
如果选择了则展示选择的，
最后取消选择，还显示当前选择的

* */
const i = ref(0)

const uuid: string = 'u' + CommonUtil.getUUID()

const oneScrollTop = ref(0)

const props = defineProps({
  //如果是头顶或者底部则不为width100
  modelValue: Object,
  value: Object,
  dataList: []
})


const twoLevelData = computed(() => {
  console.log(checkedOne.value === 0)
  console.log(checkedOne.value)
  console.log((checkedOne.value === 0 || checkedOne.value) )
  console.log(props.dataList)
  console.log(props.dataList)
  console.log(props.dataList && props.dataList[checkedOne.value])
  console.log((checkedOne.value === 0 || checkedOne.value) && props.dataList && props.dataList[checkedOne.value])
  if ((checkedOne.value === 0 || checkedOne.value) && props.dataList && props.dataList[checkedOne.value]) {
    return props.dataList[checkedOne.value].childs
  } else {
    return []
  }
})
const threeLevelData = computed(() => {
  if ((checkedTwo.value === 0 || checkedTwo.value) && twoLevelData.value && twoLevelData.value[checkedTwo.value]) {
    return twoLevelData.value[checkedTwo.value].childs
  } else {
    return []
  }
})

console.log(props.dataList)

//选中的一级
const checkedOne = ref(0)
//选中的二级
const checkedTwo = ref(null)
//选中的二级
const checkedThree = ref(null)


watch(i, () => {
  console.log(11111)
  console.log(props.dataList)
  console.log(222222)
})

console.log(props.dataList)
watch(props.dataList, () => {
  console.log(11111)
  console.log(props.dataList)
  console.log(222222)
  getComponentsHeight()
  getOneNodeTops()
  getTwoNodeTops()
  getThreeNodeTops()
})


onMounted(() => {
})

/**
 * 记录右侧每个索引对应的滚动位置
 */
const oneTops = ref([])

function getOneNodeTops() {
  //获取整个元素的高度
  const query: SelectorQuery = uni.createSelectorQuery()
  //存储左侧菜单需要滚动到的点
  const nodeOne: NodesRef = query.selectAll('.' + uuid + '.q-picker-one-level-item')
  nodeOne.boundingClientRect((res: any) => {
    if (res.length) {
      oneTops.value = []
      res.forEach(item => {
        const top = item.top + item.height / 2 - scrollBoxHeight / 2 - res[0].top
        oneTops.value.push(top)
      })
    } else {
      CommonUtil.delayTime(100).then(() => {
        getOneNodeTops()
      })
    }
  }).exec()
}

//点击菜单，如果重复点击则为取消选中
function oneLevelChange(index) {
  if (checkedOne.value === index) {
    checkedOne.value = 0
  } else {
    checkedOne.value = index
    oneScrollTop.value = oneTops.value[index]
    nextTick(() => {
      getTwoNodeTops()
    })
  }
  checkedTwo.value = null
  checkedThree.value = null
  input()
}

/**
 * 记录左侧每个索引对应的滚动位置
 */
const twoTops = ref([])

function getTwoNodeTops() {
  //获取整个元素的高度
  const query: SelectorQuery = uni.createSelectorQuery()
  //存储左侧菜单需要滚动到的点
  const nodeTwo: NodesRef = query.selectAll('.' + uuid + '.q-picker-two-level-item')
  nodeTwo.boundingClientRect((res: any) => {
    if (res.length) {
      twoTops.value = []
      res.forEach(item => {
        const top = item.top + item.height / 2 - scrollBoxHeight / 2 - res[0].top
        twoTops.value.push(top)
      })
    } else {
      CommonUtil.delayTime(100).then(() => {
        getTwoNodeTops()
      })
    }
  }).exec()
}

/**
 * 获取整个元素高度
 */
const scrollBoxHeight = ref(0)

function getComponentsHeight() {
  //获取整个元素的高度
  const query: SelectorQuery = uni.createSelectorQuery()
  const nodeBox: NodesRef = query.select('.' + uuid + '.q-picker-box')
  nodeBox.boundingClientRect((res) => {
    if (res) {
      scrollBoxHeight.value = res.height
    } else {
      CommonUtil.delayTime(100).then(() => {
        getComponentsHeight()
      })
    }
  }).exec()
}

/**
 * 记录左侧每个索引对应的滚动位置
 */
const threeTops = ref([])

function getThreeNodeTops() {
  //获取整个元素的高度
  const query: SelectorQuery = uni.createSelectorQuery()
  //存储左侧菜单需要滚动到的点
  const nodeThree: NodesRef = query.selectAll('.' + uuid + '.q-picker-three-level-item')
  nodeThree.boundingClientRect((res: any) => {
    if (res.length) {
      threeTops.value = []
      res.forEach(item => {
        const top = item.top + item.height / 2 - scrollBoxHeight / 2 - res[0].top
        threeTops.value.push(top)
      })
    } else {
      CommonUtil.delayTime(100).then(() => {
        getThreeNodeTops()
      })
    }
  }).exec()
}

const twoScrollTop = ref(0)

function twoLevelChange(index) {
  if (checkedTwo === index) {
    checkedTwo.value = null
  } else {
    checkedTwo.value = index
    twoScrollTop.value = twoTops.value[index]
    nextTick(() => {
      getThreeNodeTops()
    })
  }
  checkedThree.value = null
  input()
}

const threeScrollTop = ref(0)

function threeLevelChange(index) {
  if (checkedThree.value === index) {
    checkedThree.value = null
  } else {
    checkedThree.value = index
    threeScrollTop.value = threeTops.value[index]
  }
  input()
}

function input() {
  if (checkedThree || checkedThree === 0) {
    return threeLevelData[checkedThree]
  } else if (checkedTwo || checkedTwo === 0) {
    emit('update:modelValue', twoLevelData.value[checkedTwo.value])
  } else if (checkedOne) {
    emit('update:modelValue', props.dataList[checkedOne.value])
  } else {
    emit('update:modelValue', null)
  }
}


</script>
