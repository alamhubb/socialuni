import type {Rule} from "unocss";
import ColorStyles from "./ColorStyles";
import ObjectUtil from "../../utils/ObjectUtil";
import SizeStyles from "./SizeStyles";

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


    static getNumStyles(propertyPrefix: string, match: string[]) {
        const direction = match[1]
        const size = match[2]
        const valueUnit = match[3]
        return this.getStyles(propertyPrefix, direction, size, valueUnit)
    }

    static getSizeStyles(propertyPrefix: string, match: string[]) {
        const direction = match[1]
        const size = match[3]
        return this.getStyles(propertyPrefix, direction, size)
    }

    static getDirectionStyles(propertyPrefix: string, match: string[]) {
        const direction = match[1]
        const size = match[2]
        const valueUnit = match[3]
        return this.getStyles('', direction, size, valueUnit)
    }

    static getStyles(propertyPrefix: string, direction: string, size: any, valueUnit: string = '') {
        if (size === undefined || size === '' || size === null) {
            size = 20
        } else {
            if (SizeStyles.sizesMap[size] !== undefined) {
                size = SizeStyles.sizesMap[size]
            }
        }
        if (size > 0) {
            valueUnit = valueUnit || 'px'
        }

        const styleValue = size + (valueUnit)

        const directionAry: string[] = directionMap[direction]
        if (!directionAry) {
            return
        }
        const style: { [key in string]: string } = {}
        for (const string of directionAry) {
            if (propertyPrefix) {
                if (string) {
                    style[(propertyPrefix + '-' + string)] = styleValue
                } else {
                    style[propertyPrefix] = styleValue
                }
            } else {
                style[string] = styleValue
            }
        }
        console.log(style)
        return style
    }
}
