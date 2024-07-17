export default class CountDownUtil {
    countDownNum = 0

    value = 0

    constructor(countDownNum: number) {
        this.countDownNum = countDownNum;
    }
    resetAuthCodeCountDown() {
        this.value = 0
    }
    get countDown() {
        return this.countDownNum - this.value
    }
    computedCountDown() {
        this.value++
        const Timer = setInterval(() => {
            if (this.value === this.countDownNum) {
                clearInterval(Timer)
                this.resetAuthCodeCountDown()
                return
            }
            this.value++
            console.log(this.value)
        }, 1000)
    }
}
