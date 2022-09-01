import Breakpoints from "./Breakpoints";
import ObjectUtil from "../../utils/ObjectUtil";

export default class StylesColor {
    static greenColors: { [key in string]: string } = {
        green: '#52C41A',
        green1: '#82CC6B',
        green2: '#73D13D',
        green3: '#95DE64',
        green4: '#B7EB8F',
        green5: '#C2E7B0',
        green6: '#F0F9EB',
    }
    static blueColors: { [key in string]: string } = {
        green: '#1F71F6',
        green1: '#2A86FF',
        green2: '#40A9FF',
        green3: '#69C0FF',
        green4: '#91D5FF',
        green5: '#BAE7FF',
        green6: '#69C0FF',
        green7: '#B3D8FF',
        green8: '#EDF5FF',
    }
    static greyColors: { [key in string]: string } = {
        green: '#282f3d',
        green1: '#141414',
        green2: '#1f2329',
        green3: '#282f3d',
        green4: '#333333',
        green5: '#434343',
        green6: '#666666',
        green7: '#999999',
        green8: '#EDF5FF',/*
        green8: '#d9d9d9',
        green8: '#EDF5FF',
        green8: '#f0f0f0',
        green8: '#f5f5f5',
        green8: '#F3F4F7',
        green8: '#f3f5fb',
        green8: '#EAEBEC',
        green8: '#e5e5e5',
        green8: '#ececec',*/
    }

    static colors: { [key in string]: string } = {
        ...StylesColor.greenColors
    }


    static getColorByName(colorName: string) {
        console.log(this.colors[colorName])
        return this.colors[colorName]
    }

    static generateReactiveCss(breakpoints: string, styleClass: any = {'font-weight': '12px'}) {
        console.log(breakpoints)
        console.log(breakpoints === Breakpoints.sm)
        if (breakpoints === Breakpoints.sm) {
           return  `
@media (min-width: 768px) {
  .md\:font-12 ${ObjectUtil.toJson(styleClass)}
}

            `
        }
    }
}
