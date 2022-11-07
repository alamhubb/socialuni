export default class NumUtil {
    static numFixed(num: number, place: number) {
        if (num) {
            return num.toFixed(place)
        }
    }

    static numFixed1(num: number) {
        if (num) {
            return num.toFixed(1)
        }
    }

    static numFixed2(num: number) {
        if (num) {
            return num.toFixed(2)
        }
    }
}
