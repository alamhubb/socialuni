import {TTTTAaaa} from "./TypeIoc/Testaa.ts";

function Service(number) {
    return (haha,dada)=>{
        console.log(typeof haha)
        console.log(haha)
        console.log(dada)
    }
}


function Resource(target, {kind, name}) {
    console.log('3333')
    console.log(target)
    console.log(kind)
    console.log(name)
    return function (initialValue) {
        console.log(222222) //1
        return 666;
    };
}

@Service(3)
export default class TestSerivce implements TTTTAaaa {
    @Resource
    testA;

    test() {
        console.log(11111);
        console.log(44444);
        console.log(this.testA);
    }

    test(ha: number) {
        console.log(2222);
        console.log(55555);
        console.log(this.testA);
    }
}


const tttt = new TestSerivce()
tttt.test()
tttt.test('dada')



