// global.d.ts
// 注意，扩展vue的接口需要先import vue进来, 不导入原来的环境进来，直接扩展的话，就不是扩展了，变成覆盖
import Vue from 'vue'
import {ComponentCustomProperties} from 'vue'
import SocialuniUserUtil from "./util/SocialuniUserUtil";

declare module '@vue/runtime-core' {
    // 为 this 上的实例属性扩展类型声明
    interface ComponentCustomProperties {
        $userUtil: typeof SocialuniUserUtil
        $isMine: () => Boolean
    }
}
