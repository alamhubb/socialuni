export default class SizeStyles {

    private static none = 0
    private static nn = 1
    private static nm = 2
    private static mn = 3
    private static mnn = 4
    private static xs = 5
    private static xss = 8
    private static sm = 10
    private static ssm = 12
    private static smm = 15
    private static df = 20
    private static dff = 25
    private static md = 30
    private static mdd = 35
    private static lg = 40
    private static lgg = 45
    private static xl = 50
    private static xxl = 60
    private static sl = 70
    private static sxl = 80
    private static ssl = 90
    private static hun = 100

    static sizesMap: { [key in string]: number } = {
        none: 0,
        nn: 1,
        nm: 2,
        mn: 3,
        mnn: 4,
        xs: 5,
        xss: 8,
        sm: 10,
        ssm: 12,
        smm: 15,
        df: 20,
        dff: 25,
        md: 30,
        mdd: 35,
        lg: 40,
        lgg: 45,
        xl: 50,
        xxl: 60,
        sl: 70,
        sxl: 80,
        ssl: 90,
        hun: 100
    }
}
