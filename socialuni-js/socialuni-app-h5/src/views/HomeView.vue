<template>
  <main class="h100p mt-sm row-all-center">

  </main>
</template>

<script lang="ts">
import {Component, Vue, toNative} from 'vue-facing-decorator';
import 'reflect-metadata';


function sealed(target, propertyKey) {
  console.log('safsadfdfsdf')
  console.log(target)
  console.log(propertyKey)
  console.log(Reflect)
  // 获取属性的类型元数据
  const propertyType = Reflect.getMetadata('design:type', target, propertyKey);
  if (propertyType) {
    console.log(`${propertyKey} type: ${propertyType.name}`);
  } else {
    console.log(`${propertyKey} 的类型未定义或元数据未正确生成`);
  }
}


interface People {
  eat()
}

class Girl implements People {
  eat() {
    console.log('girl eat')
  }
}

class Boy implements People {
  eat() {
    console.log('Boy eat')
  }
}


@toNative
@Component({})
export default class HomeView extends Vue {

  @sealed
  people: People = null

  created() {
  }


}
</script>
