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

    static generateReactiveCss(rule: [RegExp, (match: string[]) => any]): [RegExp, (match: string[]) => any] {
        //返回的应该是newReg和包裹的方法
        const ruleReg = rule[0]
        const matchFun = rule[1]
        if (!ruleReg || !matchFun) {
            return rule
        }
        //第一个参数得到了
        const ruleStr = String(ruleReg)
        const newPointRuleStr = ruleStr.replace(/^\/\^(.+)\$\/$/, (inputText, matchVal) => {
            const newPointRule = '^((.+)\\:)?(' + matchVal + ')$'
            return newPointRule
        })

        const newPointRule = new RegExp(newPointRuleStr)

        //入参和返回参数都应该是两个方法
        function newMatchFun(matchStrAry: string[]) {
            console.log(111111111)
            console.log(matchStrAry)
            //过滤掉自定义的参数，保留原始的参数
            const originalMatchStrAry = matchStrAry.filter((item, index) => ![1, 2, 3].includes(index))
            if (matchStrAry.length < 3) {
                return matchFun(originalMatchStrAry)
            }
            const point = matchStrAry[2]
            if (!Breakpoints.points.includes(point)) {
                return matchFun(originalMatchStrAry)
            }


            console.log(originalMatchStrAry)
            console.log(222222222)

            const matchRes = matchFun(originalMatchStrAry)

            console.log(matchRes)
            console.log(33333)

            let resStr = matchRes

            if (typeof matchRes !== "string") {
                resStr = ObjectUtil.toJson(resStr).replace(/"/g, '')
            }

            console.log(resStr)
            console.log(44444444)

            const endResStr = `
    @media (min-width: 768px) {
      .${point}\\:${matchStrAry[3]}${resStr}
      }
    `
            console.log(endResStr)
            console.log(55555555)
            return endResStr
        }

        return [newPointRule, newMatchFun]
    }
}
