//1_导入组件
//2_把vue组件编译成虚拟DOM
//3_准备一个dom容器

import {createVNode, render} from "vue";

//加载动画组件
import load from '@/components/loading.vue'
let section = document.createElement('section')
section.setAttribute('class','loading')
document.body.appendChild(section)

export function loading(flag){
    console.log(flag);
    if(flag){
        const vNode = createVNode(load)
        render(vNode,section)
    }else{
        render(null,section)
    }
    
}