import type {Rule} from "unocss";
import ColorStyles from "./ColorStyles";
import ObjectUtil from "../../utils/ObjectUtil";

export const directionMap: { [key in string]: string[] } = {
    'l': ['left'],
    'r': ['right'],
    't': ['top'],
    'b': ['bottom'],
    'x': ['left', 'right'],
    'y': ['top', 'bottom'],
    'd': [''],
    'g': [''],
}

export default class UnocssRuleUtil {
    static getStyles(propertyPrefix: string, match: string[]) {
        const direction = match[1]
        const valueNum = match[2]
        const valueUnit = match[3]


        const directionAry: string[] = directionMap[direction]
        console.log(11111111)
        console.log(match[0])
        console.log(propertyPrefix)
        console.log(direction)
        console.log(directionAry)
        console.log(2222222222)
        if (!directionAry) {
            return
        }


        console.log(directionAry)

        const styleValue = (valueNum !== '' ? valueNum : 20) + (valueUnit || 'px')

        const style: { [key in string]: string } = {}
        for (const string of directionAry) {
            if (string) {
                style[(propertyPrefix + '-' + string)] = styleValue
            } else {
                style[(propertyPrefix)] = styleValue
            }
        }
        return style
    }
}
