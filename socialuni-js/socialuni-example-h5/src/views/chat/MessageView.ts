import {div} from "@/views/chat/VueRender";

export default {
    setup() {
        // 使用数组返回多个根节点
        const haha = () => [
            div(123,
                [
                    div(33),
                    div(333),
                    div(333),
                ]
            ),
        ]
        return haha
    }
}
