

export default class FontSizeStyle {
    static font22 = {
        fontSize: '22px'
    }
    static font32 = {
        fontSize: '32px'
    }
    static font44 = {
        fontSize: '44px'
    }

    static fontSize(fontSize: number) {
        return {
            fontSize: fontSize + 'px'
        }
    }
}
