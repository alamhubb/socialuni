export default class MarginStyle {
    static mg(num: number) {
        return {
            margin: num + 'px'
        }
    }

    static mt(num: number) {
        return {
            marginTop: num + 'px'
        }
    }
    static mb(num: number) {
        return {
            marginBottom: num + 'px'
        }
    }
    static ml(num: number) {
        return {
            marginLeft: num + 'px'
        }
    }
    static mr(num: number) {
        return {
            marginRight: num + 'px'
        }
    }
    static mx(num: number) {
        return {
            marginLeft: num + 'px',
            marginRight: num + 'px'
        }
    }
    static my(num: number) {
        return {
            marginTop: num + 'px',
            marginBottom: num + 'px'
        }
    }

}
