function Service(){
    return
}


function Resource(target, {kind, name}) {
    console.log('3333')
    return function (initialValue) {
        console.log(222222) //1
        return 666;
    };
}

@Service
export default class TestSerivce {
    @Resource
    testA;

    test() {
        console.log(11111);
        console.log(44444);
        console.log(this.testA);
    }
}