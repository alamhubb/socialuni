import Breakpoints from "./Breakpoints";
import ObjectUtil from "../../utils/ObjectUtil";


export default class ColorStyles {
    static greenColors = {
        green: '#52C41A',
        green1: '#82CC6B',
        green2: '#73D13D',
        green3: '#95DE64',
        green4: '#B7EB8F',
        green5: '#C2E7B0',
        green6: '#F0F9EB',
    }
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
    static redColors = {
        red: '#F56C6C',
        green1: '#2A86FF',
        green2: '#40A9FF',
        green3: '#69C0FF',
        green4: '#91D5FF',
        green5: '#BAE7FF',
        green6: '#69C0FF',
        green7: '#B3D8FF',
        green8: '#EDF5FF',
    }

    static grayColors: { [key in string]: string } = {
        gray: '#f2f2f2',
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
        success: this.greenColors.green,
        info: this.grayColors.gray,
    }

    static colors: { [key in string]: string } = {
        ...ColorStyles.themeColors,
        ...ColorStyles.greenColors,
        ...ColorStyles.blueColors,
        ...ColorStyles.redColors,
        ...ColorStyles.grayColors,
    }
}
