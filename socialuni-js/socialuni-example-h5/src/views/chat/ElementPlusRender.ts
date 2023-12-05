import {h} from "vue";
import { ElSlider } from 'element-plus';

export function elSlider(...args: any[]) {
    return h(ElSlider, ...args)
}
