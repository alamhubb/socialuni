import {reactive} from "vue";

class TestA {
    uid = Math.random()
}

const testA = reactive(new TestA())
export default testA
