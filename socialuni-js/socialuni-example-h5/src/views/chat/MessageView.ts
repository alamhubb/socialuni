import {div} from "@/views/chat/VueRender";

export default {
    setup() {
        // 使用数组返回多个根节点
        return () => [
            div([123, div('123'), div('123')]),
            div('123')
        ]
    }
}
