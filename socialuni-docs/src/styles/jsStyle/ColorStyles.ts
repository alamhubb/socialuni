import Breakpoints from "./Breakpoints";
import ObjectUtil from "../../utils/ObjectUtil";


export default class ColorStyles {
    static blueColors = {
        blue: '#409EFF',
        green1: '#2A86FF',
        green2: '#40A9FF',
        green3: '#69C0FF',
        green4: '#91D5FF',
        green5: '#BAE7FF',
        green6: '#69C0FF',
        green7: '#B3D8FF',
        green8: '#EDF5FF',
    }
    static greenColors = {
        green: '#67C23A',
        green1: '#82CC6B',
        green2: '#73D13D',
        green3: '#95DE64',
        green4: '#B7EB8F',
        green5: '#C2E7B0',
        green6: '#F0F9EB',
    }
    static orangeColors = {
        orange: '#E6A23C',
        orange1: '#FF9429',
        orange2: '#FFC069',
        orange3: '#FFAE52',
        orange4: '#FFC67A',
        orange5: '#FFDAA3',
        orange6: '#FFF6E6',
        orange7: '#F5DAB1',
        orange8: '#FDF6EC',
    }
    static redColors = {
        red: '#F56C6C',
        red1: '#CF1322',
        red2: '#D4353F',
        red3: '#F5222D',
        red4: '#FF4D4F',
        red5: '#FF7875',
        red6: '#FFA39E',
        red7: '#FBC4C4',
        red8: '#FEF0F0',
    }
    static grayColors: { [key in string]: string } = {
        gray: '#909399',
        gray1: '#141414',
        gray2: '#1f2329',
        gray3: '#282f3d',
        gray4: '#333333',
        gray5: '#434343',
        gray6: '#666666',
        gray7: '#999999',
        gray8: '#bfbfbf',
        gray9: '#d9d9d9',
        gray10: '#f0f0f0',
        gray11: '#f5f5f5',
        gray12: '#F3F4F7',
        gray13: '#f3f5fb',
        gray14: '#EAEBEC',
    }

    static themeColors: { [key in string]: string } = {
        theme: '#28a5dc',
        white: '#ffffff',
        black: '#000000',
        default: '#f9f9f9',
        border: '#e6e6e6',
        main: '#213547',
        content: '#3c3c3cb3',
        primary: this.blueColors.blue,
        danger: this.redColors.red,
        warning: this.orangeColors.orange,
        success: this.greenColors.green,
        info: this.grayColors.gray,
    }

    static colors: { [key in string]: string } = {
        ...ColorStyles.themeColors,
        ...ColorStyles.greenColors,
        ...ColorStyles.blueColors,
        ...ColorStyles.redColors,
        ...ColorStyles.grayColors,
        ...ColorStyles.orangeColors,
    }
}
