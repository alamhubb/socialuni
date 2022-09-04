export default class FontSizeStyle {
    static font12 = {
        fontSize: '12px'
    }
    static font14 = {
        fontSize: '14px'
    }
    static font16 = {
        fontSize: '16px'
    }
    static font18 = {
        fontSize: '18px'
    }
    static font20 = {
        fontSize: '20px'
    }
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
